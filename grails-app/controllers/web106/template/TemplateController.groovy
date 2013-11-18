package web106.template

class TemplateController {

	
    def index() { 
		
		render(template:"index")		
	}
	
	def kairo() {
		
		render(template:"kairo/template", model:[stadt:"Kairo"])
	}
	def berlin() {
		
		render(template:"berlin/template", model:[stadt:"Berlin"])
	}

}
