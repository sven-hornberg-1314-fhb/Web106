package web106


class ErrorsWeb106Controller {


	def index(){
        defaultError()
    }

    def defaultError() {
        "es ist ein Fehler aufgetreten"
    }

	def accessDenied(){
		render 'error403 '+params
	}
		

}
