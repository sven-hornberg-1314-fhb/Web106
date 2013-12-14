package web106


class ErrorsController {

	def index(){}
	def accessDenied(){
		render 'error403 '+params
	}
		

}
