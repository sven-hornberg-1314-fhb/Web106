package web106.file.upload

import com.amazonaws.AmazonClientException
import com.amazonaws.AmazonServiceException
import web106.ResourceHolder
import web106.converter.FileTypeConverterService
import web106.site.Website

/**
 * Controller for uploading images
 */
class UploadFileController {


    def UploadS3Service uploadS3Service

    def FileTypeConverterService fileTypeConverterService

    def index() {
        render(view:"create");
    }

    /**
     * Upload an image for a workgroup + site in s3 bucket
     * @return index page
     */
	def uploadImage() {

        try {

            def activeWebsite

            def activeWebsiteSession = session.getAttribute('activeWebsite')
            activeWebsite = activeWebsiteSession
            Website website = Website.find {
                id == activeWebsite
            }

            def multiPartFile = request.getFile('file')
            if(multiPartFile.empty) {
                flash.message = "File cannot be empty"
            } else {
                File convertedFile = fileTypeConverterService.convert(multiPartFile)
                if(convertedFile == null){
                    flash.message = "File must be .jpg or .png"
                } else {

                    def prefix =  website.workGroup.name+ "/" + website.title + "/" + ResourceHolder.bucketprefixForImages + "/"
                    prefix = prefix.toLowerCase()

                    //upload to bucket/workgroup/site/imagebucket
                    uploadS3Service.uploadFileToS3Bucket(ResourceHolder.bucketGlobal,convertedFile, prefix)
                }

            }

		    redirect (action:'index')
        } catch (AmazonServiceException ex) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        } catch (AmazonClientException ex) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        } catch (UnexpectedRollbackException) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        }
	}

    /**
     * Shows an overview of all uploaded files
     * @return overview of files
     */
    def list() {

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

            def pictures= []

            list.each {
                def item = [:]
                item['name'] = it
                item['url'] ='http://s3-eu-west-1.amazonaws.com/'+ ResourceHolder.bucketGlobal + '/' + prefix + it
                item['fileNameID'] = it.replace('.', '_')

                pictures.add(item)
            }



            def model = [:]
            model['pictures'] = pictures
            render view : 'list' , model : model
        } catch (AmazonServiceException) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        } catch (AmazonClientException) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        } catch (UnexpectedRollbackException) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        }
    }

    /**
     * Deletes all files in a "subbucket" (files with prefix)
     * @return cleared subbucket
     */
    def delete() {

        try {

            if(params.id) {

                def activeWebsite

                def activeWebsiteSession = session.getAttribute('activeWebsite')
                activeWebsite = activeWebsiteSession
                Website website = Website.find {
                    id == activeWebsite
                }

                String keyFileName = params.id
                int last = keyFileName.lastIndexOf('_')
                String keyFileNameNew = new StringBuilder(keyFileName).replace(last, last+1,".").toString();

                def prefix =  website.workGroup.name+ "/" + website.title + "/" + ResourceHolder.bucketprefixForImages + "/"
                uploadS3Service.deleteFile(ResourceHolder.bucketGlobal,prefix, keyFileNameNew )
            }
            redirect action: 'list'

        } catch (AmazonServiceException) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        } catch (AmazonClientException) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        } catch (UnexpectedRollbackException) {
            redirect controller: 'errorsWeb106' ,view: 'aws'
        }

    }
}
