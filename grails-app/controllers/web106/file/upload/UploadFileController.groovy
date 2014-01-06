package web106.file.upload

import upload.convert.FileTypeConverter
import web106.UserUtils
import web106.auth.User
import web106.site.Website

/**
 * Controller for uploading images
 */
class UploadFileController {

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

        //TODO refac
		String websiteName = website.workGroup.name + "." + website.title + "-Images"

		def file = request.getFile('file')
		if(file.empty) {
			flash.message = "File cannot be empty"
		} else {
			boolean returnmessage = FileTypeConverter.convert(file, websiteName)
			if(returnmessage == false){
				flash.message = "File must be .jpg or .png"
			}
			

		}

		redirect (action:'index')
	}
}
