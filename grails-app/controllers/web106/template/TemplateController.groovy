package web106.template

import grails.converters.JSON

class TemplateController {

	
    def index() { 
		
		render(template:"index")		
	}
	
	def kairo() {
		
		def model = defaultmodelKairo()
		
		render(template:"kairo/template", model: model)
	}
	
	def defaultmodelKairo() {
		def model = [
			centermessagebox:true,
			header: "header",
			content: "content",
			side1: "side1",
			side2: "side2",
			side3: "side3",
			footer: "footer"
		]
	}
	
	
	def berlin() {
		
		def model = defaultmodelBerlin()
		render(template:"berlin/template", model:model)
	}
	
	def defaultmodelBerlin() {
		def model = [
			centermessagebox:true,
			header: "header",
			sidebar1: "sidebar1",
			sidebar2: "sidebar2",
			content : "content",
			footer : "footer",
			footer2 : "footer2"
		]
	}

    def list() {

        def list = [

            1: "Berlin",
            2: "Kairo"
        ]

        render list;
    }

}
