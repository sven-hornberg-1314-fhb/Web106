package web106.urlmapping

import web106.ResourceHolder
import web106.file.upload.UploadS3Service

class UrlredirectController {

    def UploadS3Service uploadS3Service


    def index() {
        render(status: 404, text: 'nicht gefunden')
    }

    /**
     * e.g. http://localhost:8080/Web106/live/testexportbucket-webseite/index.html
     *      Path: /live/testexportbucket-website/index.html
     */
    def live() {

        def workgroupName
        def websiteName

        if (params?.workgroup && params?.websitename) {
            workgroupName = params.workgroup
            websiteName = params.websitename

            def global = ResourceHolder.bucketGlobal

            def url = 'http://s3-eu-west-1.amazonaws.com/' + global + "/" + workgroupName + '/' + websiteName + '/index.html'
            url = url.toLowerCase()

            redirect(url: url)
        } else {
            render status: 404
        }

    }

    /**
     * POC Solution for errorpages and index for "subbuckets" NOT FOR LIVE MODE OR BENCHMARK ready!
     * Later after project with caching , ETags, maybe own ec2 balancer
     * @return pagecontent or errorcode
     */
    def stream() {
        try {
            def workgroupName
            def websiteName
            def pageName

            if (params?.workgroup && params?.websitename) {
                workgroupName = params.workgroup
                websiteName = params.websitename
                pageName = params.page

                def content = ""
                def global = ResourceHolder.bucketGlobal
                def prefix = workgroupName + "/" + websiteName + "/"
                prefix = prefix.toLowerCase()

                //check for indexSite : means Website is online
                boolean indexPage = uploadS3Service.fileExistsInBucket(global, "index.html", prefix)
                if (indexPage) {

                    boolean requestedPage = uploadS3Service.fileExistsInBucket(global, pageName, prefix)
                    if (requestedPage) {

                        //stream requestedPage
                        content = uploadS3Service.ContentFromFileInBucket(global, prefix + pageName)

                    } else {
                        //stream indexfile
                        content = uploadS3Service.ContentFromFileInBucket(global, prefix + 'index.html')

                    }

                } else {

                    //website not online
                    render(status: 404)
                }

                render(text: content, contentType: "text/html", encoding: "UTF-8")
            } else {

                //website not online
                render(status: 404)
            }


        } catch (AmazonServiceException) {
            redirect controller: 'errorsWeb106', view: 'aws'
        } catch (AmazonClientException) {
            redirect controller: 'errorsWeb106', view: 'aws'
        } catch (UnexpectedRollbackException) {
            redirect controller: 'errorsWeb106', view: 'aws'
        }
    }
}