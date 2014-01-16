package web106.site

import web106.auth.WorkGroup


class WebsiteController {

    def activeWorkGroup

    def beforeInterceptor = {

        def activeWorkGroupSession = session.getAttribute('activeWorkGroup')
        activeWorkGroup = activeWorkGroupSession

    }


    def index() {
        render view: "index"
    }

    def create(){
        render view: 'create'
    }

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

        for(int i=0; i< websiteTitles.size(); i++) {
            websiteTitles[i] = websiteTitles[i].toString().toUpperCase()
        }


        if(!(params.title.toUpperCase() in websiteTitles)){
            website.save(failOnError: true)

            redirect controller: params.controller
        }
        else{
            flash.message = 'Website-Title exisitiert fuer Workgroup bereits'
            redirect(controller: 'website', action: 'create')
        }



    }

    def delete(){
        //find and delete component
        def current = Website.find{
            id == params.id
        }

        //delete without services
        current?.page?.each{
            Page.deleteAll(it)
        }

        current?.delete()

        //delete from session
        session.removeAttribute('activeWebsite')
        session.removeAttribute('activeWebsiteName')

        //back to index
        redirect controller: params.controller
    }

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

    def select() {
        Website website = Website.find {

            id == params.id
        }

        session.setAttribute("activeWebsite", website.id)

        def selectedWebsite = Website.find{id == website.id}

        session.setAttribute("activeWebsiteName",selectedWebsite.title)



        redirect controller: params.controller

    }

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

    //todo test double usage ?
    def selectWebsites() {

        def websiteId = params.id

        session.setAttribute("activeWebsite",websiteId)

        def selectedWebsite = Website.find{id == websiteId}

        def title = selectedWebsite.title
        session.setAttribute("activeWebsiteName",selectedWebsite.title)

        render view: "SuccessWebsiteSelection", model:[title: title]

    }

    def remoteSave() {

        def currentWebsite = Website.find{
            id == session.getAttribute ('editwebsiteid')
        }

        currentWebsite.title = params.title

        currentWebsite.save(failOnError: true, flush: true)

        redirect view: index()
    }
}
