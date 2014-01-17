package web106


class ErrorsWeb106Controller {


	def index(){
        defaultError()
    }

    def defaultError() {
        render "es ist ein Fehler aufgetreten"
    }

	def accessDenied(){
        render view: 'accessDenied'
	}

    def oauth() {
        render "Es scheint so, als wäre der Provider nicht erreichbar."
    }

}
