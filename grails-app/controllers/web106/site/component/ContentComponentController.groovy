package web106.site.component


class ContentComponentController {

	static allowedMethods = [createComponent:'POST']
	
	
	def index() {
		render view:'index'
	}
	
	def create() {
		render view:'create'
	}
	
	def createComponent() {
		render text:"done"
	}
	
	def listown() {
		render view: 'listown', model : ['text': 'meinCoolerText']
	}
}
