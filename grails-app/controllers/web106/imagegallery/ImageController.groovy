package web106.imagegallery

import access.s3.ImageService
import upload.convert.FileTypeConverter
import web106.UserUtils
import web106.auth.User

class ImageController {

    def index() { 
		def mail  = UserUtils.newInstance().emailFromCurrentUser
		User currentUser = User.find {email== mail}
		String newmail = FileTypeConverter.replaceAT(mail)
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
