package web106

import grails.transaction.Transactional
import org.codehaus.groovy.grails.core.io.ResourceLocator
import org.springframework.core.io.Resource
import web106.file.FileService
import web106.file.upload.UploadS3Service

@Transactional
class BootStrapWeb106Service {

    def UploadS3Service uploadS3Service
    def FileService fileService

    def grailsResourceLocator

    def js = ResourceHolder.js
    def css = ResourceHolder.css



    /**
     * 'BootStrapWeb106Service init '
     */
    void init() {

        //1. check if static bucket exists
        if(!uploadS3Service.doesBucketExist(ResourceHolder.bucketStaticContent)) {
            //try to create
            uploadS3Service.createS3Bucket(ResourceHolder.bucketStaticContent)
        }

        //test && upload CSS
        uploadCSS(ResourceHolder.bucketStaticContent)

        //test && upload JS
        uploadJS(ResourceHolder.bucketStaticContent)

        //2. check if global bucket exists
        if(!uploadS3Service.doesBucketExist(ResourceHolder.bucketGlobal)) {
            //try to create
            uploadS3Service.createS3Bucket(ResourceHolder.bucketGlobal)
            BucketS3ConfigIndexErrorPage(ResourceHolder.bucketGlobal)
        }
    }

    /**
     * Uploads defined javascript resources
     * @param bucketName Bucket for static content
     */
    void uploadJS(String bucketName) {
        js.each {
            uploadResourceByURI(bucketName,it)
        }
    }
    /**
     * Uploads defined css resources
     * @param bucketName Bucket for static content
     */
    void uploadCSS(String bucketName) {
        css.each {
            uploadResourceByURI(bucketName,it)
        }
    }

    /**
     * Makes a bucket to a website bucket, with error and index page
     * @param bucketName BucketName
     */
    void BucketS3ConfigIndexErrorPage(String bucketName) {

        uploadS3Service.createWebsiteBucketS3Config(bucketName, 'index.html', 'error.html')
    }

    /**
     * Uploads a Resource from the grails application to a bucket
     * @param bucketName BucketName
     * @param uriValue path to the resource in application
     */
    void uploadResourceByURI(String bucketName, String uriValue) {
        final Resource uriResource = grailsResourceLocator.findResourceForURI(uriValue)
        if(uriResource.exists() && uriResource.isReadable()) {

            File resourceFile = uriResource.getFile()
            String fileName = uriResource.getFilename()

            if(!uploadS3Service.fileExistsInBucket(bucketName, fileName)) {
                uploadS3Service.uploadFileToS3Bucket(bucketName, resourceFile)
            } else {
                //MD5 check
                String MD5LocalFile = fileService.MD5ofFile(resourceFile)
                String MD5BucketFile = uploadS3Service.MD5OfFileInBucket(bucketName,fileName)

                if(!MD5BucketFile.equals(MD5LocalFile)) {
                    uploadS3Service.uploadFileToS3Bucket(bucketName, resourceFile)
                }
            }

        }
    }

}
