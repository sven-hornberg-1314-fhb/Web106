package web106.file.upload

import upload.convert.FileTypeConverter
import web106.UserUtils
import web106.auth.User

class UUploadFileController {

    def index() { }
	
	def create() {
	
    }
	
	def upload() {
		def mail  = UserUtils.newInstance().emailFromCurrentUser
		User currentUser = User.find {email== mail}

		def file = request.getFile('file')
		if(file.empty) {
			flash.message = "File cannot be empty"
		} else {
			FileTypeConverter.convert(file, mail)

		}
		
		redirect (action:'create')
	}
}
