package web106.template

import grails.converters.JSON

class TemplateController {

	
    def index() { 
		
		render(template:"index")		
	}
	
	def kairo() {
		
		def model = [
			centermessagebox:true, 
			header: "header", 
			content: "content",
			side1: "side1",
			side2: "side2",
			side3: "side3",
			footer: "footer"
		]
		
		render(template:"kairo/template", model: model)
	}
	def berlin() {
		
		def model = [
			centermessagebox:true,
			header: "header", 
			sidebar1: "sidebar1",
			sidebar2: "sidebar2",
			content : "content",
			footer : "footer",
			footer2 : "footer2"
		]
		render(template:"berlin/template", model:model)
	}

    def list() {

        def list = [

            1: "Berlin",
            2: "Kairo"
        ]

        render list;
    }

}
