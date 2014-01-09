package web106.file.upload

import web106.converter.FileTypeConverterService
import web106.site.Website

/**
 * Controller for uploading images
 */
class UploadFileController {

    def FileTypeConverterService fileTypeConverterService

    def index() {
        render(view:"create");
    }

	def uploadImage() {
		def activeWebsite
		
		def activeWebsiteSession = session.getAttribute('activeWebsite')
		activeWebsite = activeWebsiteSession
		Website website = Website.find {
			id == activeWebsite
		}

		String imagePath = website.workGroup.name + "." + website.title

		def file = request.getFile('file')
		if(file.empty) {
			flash.message = "File cannot be empty"
		} else {
			boolean returnmessage =   fileTypeConverterService.convert(file, imagePath)
			if(returnmessage == false){
				flash.message = "File must be .jpg or .png"
			}
			

		}

		redirect (action:'index')
	}
}
