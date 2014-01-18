package web106


class ErrorsWeb106Controller {

    /**
     * Index view
     * @return index view
     */
	def index(){
        defaultError()
    }

    /**
     * Default or unknown error
     * @return default error
     */
    def defaultError() {
        render view: 'default'
    }

    /**
     * Access denied
     * @return view access denied
     */
	def accessDenied(){
        render view: 'accessDenied'
	}

    /**
     * Statuscode 405
     */
    def MethodNotAllowed() {
        render view: '405'
    }

    /**
     * Oauth error
     * @return view oauth
     */
    def oauth() {
        render view: 'oauth'
    }

    /**
     * Amazon error
     * @return view aws
     */
    def aws () {
        render view: 'aws'
    }

}
