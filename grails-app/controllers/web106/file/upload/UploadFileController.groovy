package web106.file.upload

import upload.convert.FileTypeConverter
import web106.UserUtils
import web106.auth.User

class UploadFileController {

    def index() {
        render(view:"create");
    }
	
	
	def upload() {
		def mail  = UserUtils.newInstance().emailFromCurrentUser
		User currentUser = User.find {email== mail}

		def file = request.getFile('file')
		if(file.empty) {
			flash.message = "File cannot be empty"
		} else {
			boolean returnmessage = FileTypeConverter.convert(file, mail)
			if(returnmessage == false){
				flash.message = "File must be .jpg or .png"
			}
			

		}

		redirect (action:'index')
	}
}
