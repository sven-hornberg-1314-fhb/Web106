package web106.imagegallery

import web106.ResourceHolder
import web106.file.upload.UploadS3Service
import web106.site.Website

class ImageController {

    def UploadS3Service uploadS3Service

    /**
     * display a gallery of all uploaded images
     */
    def index() {

        try {

            def activeWebsite

            def activeWebsiteSession = session.getAttribute('activeWebsite')
            activeWebsite = activeWebsiteSession
            Website website = Website.find {
                id == activeWebsite
            }

            def prefix =  website.workGroup.name+ "/" + website.title + "/" + ResourceHolder.bucketprefixForImages + "/"
            prefix = prefix.toLowerCase()

            def list = uploadS3Service.FileNamesInBucket(ResourceHolder.bucketGlobal, prefix)

            def listWithBucketprefix = []

            list.each {

                listWithBucketprefix.add('http://s3-eu-west-1.amazonaws.com/'+ ResourceHolder.bucketGlobal + '/' + prefix + it)

            }


            List<String> imagelist = listWithBucketprefix

            def model = [
                list:imagelist
            ]

            render view:"indexView", model : model
        } catch (AmazonServiceException) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        } catch (AmazonClientException) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        } catch (UnexpectedRollbackException) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        }
	}
}
