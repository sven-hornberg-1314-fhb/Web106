package web106.export

import grails.converters.JSON
import web106.auth.WorkGroup
import web106.file.FileService
import web106.file.upload.UploadServiceS3
import web106.site.Website
import web106.site.WebsiteService


class ExportController {

    def WebsiteService websiteService

    def UploadServiceS3 uploadServiceS3
    def FileService fileService

    def index() {

        render view: "index"
    }

    def cloudS3() {
        def model = [:]

        render view: "cloudS3", model: model
    }

    def cloudS3export() {

        Website website = Website.find() {
            id == params.id
        }
        Map<String,String> mapFiles = websiteService.createPagesForWebsite(website)

        def bucketName = website.workGroup.name-website.title

        //test if bucket exists
        def bucketExist = uploadServiceS3.doesBucketExist(bucketName)

        if(!bucketExist) {
            //create bucket workgroup-websitetitle
            uploadServiceS3.createS3Bucket(bucketName)
        }

        //upload Files into bucket
        for (it in mapFiles.keySet()) {
            File file = null

            file = fileService.createTempFile(null, it, mapFiles.get(it))

            if (file != null) {
                uploadServiceS3.uploadFileToS3Bucket(bucketName, file)
                fileService.deleteTempFile(null, it)
            }

        }

        //make bucket to websitebucket
        uploadServiceS3.createWebsiteBucketS3Config(bucketName, 'index.html', 'error.html')

        URL url = uploadServiceS3.UrlForBucketObject(bucketName, 'index.html')

        render "done export s3*dummy*<br>" + url.query
    }

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
            item['id'] = it.id
            item['title'] = it.title
            websitesView.add(item)

        }

        model['websites'] = websitesView

        render view : "listown", model: model
    }
}
