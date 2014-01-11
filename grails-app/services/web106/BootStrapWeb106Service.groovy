package web106

import grails.transaction.Transactional
import org.codehaus.groovy.grails.core.io.ResourceLocator
import org.springframework.beans.factory.InitializingBean
import org.springframework.core.io.Resource
import web106.file.upload.UploadS3Service

@Transactional
class BootStrapWeb106Service {

    def UploadS3Service uploadS3Service
    ResourceLocator grailsResourceLocator

    def css = [
            '/css/web106.css',
            '/css/shared/shared.css'
    ]
    def js = [
            '/plugins/jquery-1.10.2.2/js/jquery/jquery-1.10.2.min.js'
    ]


    void init() {

        print 'BootStrapWeb106Service init '

        //1. check if cdn bucket exists
        if(!uploadS3Service.doesBucketExist(ResourceHolder.bucketCDNName)) {
            //try to create
            uploadS3Service.createS3Bucket(ResourceHolder.bucketCDNName)
        }

        //test && upload CSS
        uploadCSS(ResourceHolder.bucketCDNName)

        //test && upload JS
        uploadJS(ResourceHolder.bucketCDNName)
    }

    void uploadJS(String bucketName) {
        js.each {
            uploadResourceByURI(bucketName,it)
        }
    }

    void uploadCSS(String bucketName) {
        css.each {
            uploadResourceByURI(bucketName,it)
        }
    }

    void uploadResourceByURI(String bucketName, String uriValue) {
        final Resource uriResource = grailsResourceLocator.findResourceForURI(uriValue)
        if(uriResource.exists() && uriResource.isReadable()) {

            File cssFile = uriResource.getFile()
            String fileName = uriResource.getFilename()

            //later check for version
            if(!uploadS3Service.fileExistsInBucket(bucketName, fileName)) {
                uploadS3Service.uploadFileToS3Bucket(bucketName, cssFile)
            }

        }
    }

}
