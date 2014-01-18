package web106.template

import web106.site.Template;
import grails.converters.JSON

class TemplateController {

    def activeWorkGroup

    def beforeInterceptor = {

        def activeWorkGroupSession = session.getAttribute('activeWorkGroup')
        activeWorkGroup = activeWorkGroupSession

    }

    /**
     * Index view of all Templates
     * @return overview
     */
    def index() { 
		
		def model = [
			templates : Template.all 
		]
		render(template:"index", model : model )		
	}

    /**
     * Example for Kairo template
     * @return
     */
	def kairo() {
		
		def model = defaultmodelKairo()
		
		render(template:"kairo/template", model: model)
	}

    /**
     * Default content for Kairo template
     * @return content for Kairo
     */
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
        return model
	}

    /**
     * Example for Berlin template
     * @return content for Berlin
     */
	def berlin() {
		
		def model = defaultmodelBerlin()
		render(template:"berlin/template", model:model)
	}

    /**
     * Default content for Berlin template
     * @return conent for Berlin
     */
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
        return model
	}

    /**
     * JSON for All Tempaltes
     * @return JSON
     */
    def list() {
		
        render Template.all as JSON;
    }

    /**
     * Returns all templatenames as list
     * @return list of names
     */
    def listNames() {

         def templates = Template.findAll()

         def tempList = []

        tempList.addAll(templates.name)

        return tempList
    }
}
