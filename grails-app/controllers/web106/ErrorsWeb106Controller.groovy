package web106


class ErrorsWeb106Controller {


	def index(){
        defaultError()
    }

    def defaultError() {
        render view: 'default'
    }

	def accessDenied(){
        render view: 'accessDenied'
	}

    /**
     * Statuscode 405
     */
    def MethodNotAllowed() {
        render view: '405'
    }

    def oauth() {
        render view: 'oauth'
    }

    def aws () {
        render view: 'aws'
    }

}
