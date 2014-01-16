package web106.export

import static java.lang.Long.parseLong

import web106.ResourceHolder
import web106.auth.WorkGroup
import web106.file.FileService
import web106.file.upload.UploadS3Service
import web106.site.PageService
import web106.site.Website
import web106.site.WebsiteService

/**
 * Controller for exports [first only aws s3, later ftp or other places]
 */
class ExportController {

    def WebsiteService websiteService

    def UploadS3Service uploadS3Service

    def FileService fileService

    def PageService pageService


    def activeWorkGroup
    def activeWebsite

    def beforeInterceptor = {

        def activeWorkGroupSession = session.getAttribute('activeWorkGroup')
        activeWorkGroup = activeWorkGroupSession

        def activeWebsiteSession = session.getAttribute('activeWebsite')
        activeWebsite = activeWebsiteSession

        try {
            if(params.id != null && !IsAllowed(params.id as long)) {
                render status: 403, text: "Sie verfügen nicht über ausreichend Rechte um auf diesen Inhalt zuzugreifen."
                return false
            }
        }
        catch (NumberFormatException) {
            render status: 403, text: "Sie verfügen nicht über ausreichend Rechte um auf diesen Inhalt zuzugreifen."
            return false
        }
    }

    boolean IsAllowed(long idValue) {

        boolean returnVal = false

        def currentWebsite = Website.findById(idValue)

        if(currentWebsite?.id == activeWebsite && currentWebsite?.workGroupId == activeWorkGroup){
            returnVal = true
        }

        return returnVal
    }

    def index() {

        render view: "index"
    }

    def cloudS3() {
        def model = [:]

        render view: "cloudS3", model: model
    }

    /**
     * Exports files to bucket wih prefix
     * @return URL to websiteBucket
     */
    def cloudS3export() {

        Website website = Website.find() {
            id == params.id
        }

        //pageName : content
        Map<String,String> mapFiles = websiteService.createPagesForWebsite(website)

        def prefix =  website.workGroup.name+ "/" + website.title + "/"

        def bucketName = ResourceHolder.bucketGlobal



        //upload Files into bucket subdir
        for (it in mapFiles.keySet()) {
            File file = null

            def filename = it + ".html"

            file = fileService.createTempFile(null,filename , mapFiles.get(it))

            if (file != null) {

                //check MD5
                String MD5Local = fileService.MD5ofFile(file)
                String MD5bucket = uploadS3Service.MD5OfFileInBucket(bucketName,filename, prefix)

                if(!MD5bucket.equals(MD5Local)) {
                    uploadS3Service.uploadFileToS3Bucket(bucketName, file, prefix)
                }

                fileService.deleteTempFile(null, filename)
            }

        }

        //delete pages that a not existing anymore
        uploadS3Service.deleteNonExistingPages(bucketName,mapFiles.keySet().toList(), prefix)

        //dummy index html
        if(!mapFiles.keySet().contains('index')) {
            String content = pageService.createDummyIndexPage(mapFiles.keySet().toList())
            def filename = "index.html"

            File file = fileService.createTempFile(null,filename ,content)
            uploadS3Service.uploadFileToS3Bucket(bucketName, file, prefix)
            fileService.deleteTempFile(null, filename)
        }

        int version = 1

        //set version of bucketexport
        if(uploadS3Service.fileExistsInBucket(bucketName, ResourceHolder.bucketVersionFileName, prefix)) {
            def bucketVersion = uploadS3Service.getWebsiteBucketVersion(bucketName, prefix)
            version = bucketVersion.version + 1
        }
        uploadS3Service.setWebsiteBucketVersion(bucketName, prefix,version)


        URL url = uploadS3Service.UrlForBucketObject(bucketName, prefix + 'index.html')

        website.exported = true

        redirect url: url
    }

    /**
     * Lists all websites for export
     * @return
     */
    def listown() {
        def aWorkgroupId = session.getAttribute("activeWorkGroup")

        WorkGroup aWorkgroup = WorkGroup.find {
            id == aWorkgroupId
        }

        def websites = Website.findAll() {
            workGroup == aWorkgroup
        }

        def model = [:]

        def websitesView = []
        websites.each {

            def item = [:]
            def paramsModel = [:]

            def prefix =  it.workGroup.name+ "/" + it.title + "/"

            def bucketName = ResourceHolder.bucketGlobal

            if(uploadS3Service.fileExistsInBucket(bucketName, ResourceHolder.bucketVersionFileName, prefix)) {
                def bucketVersion = uploadS3Service.getWebsiteBucketVersion(bucketName, prefix)

                String dateS = bucketVersion.date
                Long dateLong = parseLong(dateS)

                def date = new Date(dateLong)
                def formattedDate = date.format('yyyy-MM-dd')
                item['date'] = formattedDate
            }


            paramsModel['workgroupName'] = it.workGroup.name
            paramsModel['websiteName'] = it.title

            item['id'] = it.id
            item['title'] = it.title
            item['exported'] = it.exported
            item['paramsModel'] = paramsModel
            websitesView.add(item)

        }


        model['websites'] = websitesView
        model['project'] = grails.util.Metadata.current.'app.name'

        render view : "listown", model: model
    }
}
