package web106.file.upload

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
     * @return
     */
	def uploadImage() {
		def activeWebsite
		
		def activeWebsiteSession = session.getAttribute('activeWebsite')
		activeWebsite = activeWebsiteSession
		Website website = Website.find {
			id == activeWebsite
		}

		String imagePath = website.workGroup.name + "." + website.title

		def multiPartFile = request.getFile('file')
		if(multiPartFile.empty) {
			flash.message = "File cannot be empty"
		} else {
			File convertedFile = fileTypeConverterService.convert(multiPartFile)
			if(returnmessage == false){
				flash.message = "File must be .jpg or .png"
			}
			

		}




		redirect (action:'index')
	}
}
