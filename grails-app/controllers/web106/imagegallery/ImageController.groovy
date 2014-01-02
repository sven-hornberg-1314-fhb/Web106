package web106.imagegallery

import access.s3.ImageService
import upload.convert.FileTypeConverter
import web106.UserUtils
import web106.auth.User
import web106.site.Website

class ImageController {

    def index() { 
		def activeWebsite
		
		def activeWebsiteSession = session.getAttribute('activeWebsite')
		activeWebsite = activeWebsiteSession
		Website website = Website.find {
			id == activeWebsite
		}
		String websitename = website.title + "-Images"
		print websitename

		def mail  = UserUtils.newInstance().emailFromCurrentUser
		User currentUser = User.find {email== mail}
		
		String newmail = FileTypeConverter.replaceAT(mail+ "." + websitename)
		print newmail
		
		ImageService progress = new ImageService(newmail);
		List<String> imagelist = progress.getFileList();
		print imagelist;
		
		def model = [
			list:imagelist
		]
		
		render view:"indexView", model : model
	}
}
