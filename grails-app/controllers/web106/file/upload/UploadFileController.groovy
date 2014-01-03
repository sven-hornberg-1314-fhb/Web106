package web106.file.upload

import upload.convert.FileTypeConverter
import web106.UserUtils
import web106.auth.User
import web106.site.Website

class UploadFileController {

    def index() {
        render(view:"create");
    }
	
	
	def upload() {
		def activeWebsite
		
		def activeWebsiteSession = session.getAttribute('activeWebsite')
		activeWebsite = activeWebsiteSession
		Website website = Website.find {
			id == activeWebsite
		}
		String websitename = website.workGroup.name + "." + website.title + "-Images"

		def file = request.getFile('file')
		if(file.empty) {
			flash.message = "File cannot be empty"
		} else {
			boolean returnmessage = FileTypeConverter.convert(file, websitename)
			if(returnmessage == false){
				flash.message = "File must be .jpg or .png"
			}
			

		}

		redirect (action:'index')
	}
}
