package web106.site

import web106.ResourceHolder
import web106.auth.WorkGroup
import web106.file.upload.UploadS3Service

/**
 * Controller for all methods that belongs to websites
 */
class WebsiteController {

    def UploadS3Service uploadS3Service

    def activeWorkGroup
    def activeWebsite

    static allowedMethods = [create2: 'POST']


    def beforeInterceptor = {

        def activeWorkGroupSession = session.getAttribute('activeWorkGroup')
        activeWorkGroup = activeWorkGroupSession

        def activeWebsiteSession = session.getAttribute('activeWebsite')
        activeWebsite = activeWebsiteSession

        try {
            if(params.id != null && !IsAllowed(params.id as long)) {
                redirect(controller: 'errorsWeb106', action: 'accessDenied')
                return false
            }
        }
        catch (NumberFormatException) {
            redirect(controller: 'errorsWeb106', action: 'accessDenied')
            return false
        }
    }

    /**
     * Check if user is allowed to view website
     * @param idValue Id of website
     * @return true is allowed , false not allowed
     */
    boolean IsAllowed(long idValue) {

        boolean returnVal = false

        def currentWebsite = Website.findById(idValue)

        if(currentWebsite?.workGroupId == activeWorkGroup){
            returnVal = true
        }

        return returnVal
    }

    /**
     * Index view
     * @return index view
     */
    def index() {
        render view: "index"
    }

    /**
     * Create view
     * @return create view
     */
    def create(){
        render view: 'create'
    }

    /**
     * 2 Step for create process
     * @return create view
     */
    def create2(){
        def aworkGroup = WorkGroup.find(){
            id == activeWorkGroup
        }

        def website = Website.newInstance()
		
        website.title = params.title
        website.workGroup = aworkGroup

        def websiteTitles =  []

        if (aworkGroup.website != null){
            websiteTitles = aworkGroup.website.title
        }

        List<String> websiteTitlesStringlist = websiteTitles.toList()
        def websiteTitlesUpper = []
        websiteTitlesStringlist.each {
            websiteTitlesUpper.add(it.toUpperCase())
        }
        websiteTitles = websiteTitlesUpper

        if(!(params.title.toUpperCase() in websiteTitles)){
            website.save(failOnError: true)

            params.id = website.id
            select()

        }
        else{
            flash.message = 'Website-Title exisitiert fuer Workgroup bereits'
            redirect(controller: 'website', action: 'create')
        }

    }

    /**
     * Delete website
     * @return
     */
    def delete(){

        try {

            //find and delete component
            def current = Website.find{
                id == params.id
            }

            if(current) {
            //delete without services
                current.page?.each{
                    Page.deleteAll(it)
                }

                def prefix =  current.workGroup.name+ "/" + current.title + "/"
                prefix = prefix.toLowerCase()

                current?.delete()

                uploadS3Service.deleteSubBucket(ResourceHolder.bucketGlobal, prefix)


                //delete from session
                session.removeAttribute('activeWebsite')
                session.removeAttribute('activeWebsiteName')
            }
            //back to index
            redirect controller: params.controller
        } catch (AmazonServiceException) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        } catch (AmazonClientException) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        } catch (UnexpectedRollbackException) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        }
    }

    /**
     * Lists all websites for a workgroup
     * @return listown view
     */
    def listown(){

        def aworkGroup = WorkGroup.find(){
            id == activeWorkGroup
        }

        def websites = Website.findAll() {
            workGroup == aworkGroup
        }


        def model =[
                websites : websites
        ]

        render view: 'listown', model : model

    }

    /**
     * Select website for view listown
     * @return redirect to index
     */
    def select() {

        Website website = Website.find {

            id == params.id
        }

        session.setAttribute("activeWebsite", website.id)

        def selectedWebsite = Website.find{id == website.id}

        session.setAttribute("activeWebsiteName",selectedWebsite.title)

        selectWebsites()

    }

    /**
     * Shows the active website in a view
     * @return view active website
     */
    def activeWebsite() {

        def model = [:]

        if(session.getAttribute('activeWebsite') != null) {
            def websiteid = session.getAttribute('activeWebsite')

            Website website = Website.find {

                id == websiteid
            }

            model['activewebsite'] = website.title
        }

        render view : 'activewebsite', model: model
    }

    /**
     * List a websites for list websites view
     * @return own websites
     */
    def listWebsites(){

        def aworkGroup = WorkGroup.find(){
            id == activeWorkGroup
        }

        def websites = Website.findAll() {
            workGroup == aworkGroup
        }

        def model = [
                websites:websites
        ]

        render view: 'listwebsites', model: model
    }

    /**
     * Select website for view listwebsites
     * @return view SuccessWebsiteSelection
     */
    def selectWebsites() {

        def websiteId = params.id

        session.setAttribute("activeWebsite",websiteId)

        def selectedWebsite = Website.find{id == websiteId}

        def title = selectedWebsite.title
        session.setAttribute("activeWebsiteName",selectedWebsite.title)

        render view: "SuccessWebsiteSelection", model:[title: title]

    }

    /**
     * Save for function, that can be called from javascript
     * @return saves the websites
     */
    def remoteSave() {

        def currentWebsite = Website.find{
            id == session.getAttribute ('editwebsiteid')
        }

        currentWebsite.title = params.title

        currentWebsite.save(failOnError: true, flush: true)

        redirect view: index()
    }
}
