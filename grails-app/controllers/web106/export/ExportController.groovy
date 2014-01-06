package web106.export

import grails.converters.JSON
import web106.ResourceHolder
import web106.auth.WorkGroup
import web106.file.FileService
import web106.file.upload.UploadS3Service
import web106.site.Website
import web106.site.WebsiteService

/**
 * Controller for exports [first only aws s3, later ftp or other places]
 */
class ExportController {

    def WebsiteService websiteService

    def UploadS3Service uploadS3Service

    def FileService fileService

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
        Map<String,String> mapFiles = websiteService.createPagesForWebsite(website)

        def prefix = ResourceHolder.bucketprefix
        def bucketName = prefix+ "-" + website.workGroup.name+ "-" + website.title

        //test if bucket exists
        def bucketExist = uploadS3Service.doesBucketExist(bucketName)

        if(!bucketExist) {
            //create bucket workgroup-websitetitle
            uploadS3Service.createS3Bucket(bucketName)
        }

        //upload Files into bucket
        for (it in mapFiles.keySet()) {
            File file = null

            def filename = it + ".html"

            file = fileService.createTempFile(null,filename , mapFiles.get(it))

            if (file != null) {
                uploadS3Service.uploadFileToS3Bucket(bucketName, file)
                fileService.deleteTempFile(null, filename)
            }

        }

        //make bucket to websitebucket
        uploadS3Service.createWebsiteBucketS3Config(bucketName, 'index.html', 'error.html')

        URL url = uploadS3Service.UrlForBucketObject(bucketName, 'index.html')

        redirect url: url
    }


    def version() {
        uploadS3Service.setWebsiteBucketVersion("bucccketversion",1)

        uploadS3Service.getWebsiteBucketVersion("bucccketversion")

        render "test"
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



            def paramsModel = [:]
            paramsModel['workgroupName'] = it.workGroup.name
            paramsModel['websiteName'] = it.title

            def item = [:]
            item['id'] = it.id
            item['title'] = it.title
            item['paramsModel'] = paramsModel
            websitesView.add(item)

        }


        model['websites'] = websitesView
        print model as JSON

        render view : "listown", model: model
    }
}
