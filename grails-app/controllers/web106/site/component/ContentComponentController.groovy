package web106.site.component

import web106.auth.WorkGroup;
import grails.converters.JSON

/**
 * Controller for text component
 */
class ContentComponentController {

	static allowedMethods = [createComponent:'POST']

	def activeWorkGroup

    def activeWebsite

    def beforeInterceptor = {

        def activeWorkGroupSession = session.getAttribute('activeWorkGroup')
        activeWorkGroup = activeWorkGroupSession

        def activeWebsiteSession = session.getAttribute('activeWebsite')
        activeWebsite = activeWebsiteSession

        try {
            if(params.id != null && !IsAllowed(params.id as long)) {
                render status: 403, text: "Sie verfügen nicht über ausreichend Rechte um auf diesen Inhalt zuzugreifen."
                return false
            }
        }
        catch (NumberFormatException) {
            render status: 403, text: "Sie verfügen nicht über ausreichend Rechte um auf diesen Inhalt zuzugreifen."
            return false
        }
    }

    boolean IsAllowed(long idValue) {

        boolean returnVal = false

        def currentContentComponent = ContentComponent.findById(idValue)
        currentContentComponent.workGroup.id

        if(currentContentComponent.workGroup.id == activeWorkGroup){
            returnVal = true
        }

        return returnVal
    }
	
	def index() {
		render view:'index'
	}
	
	def create() {
		render view:'create'
	}
	
	def createComponent() {
		
			
			ContentComponent conCom = new ContentComponent()
			conCom.name = params.name
			conCom.text = params.text
			//find Workgroup 
			
			def workGroup = WorkGroup.find(){
				id == activeWorkGroup
			}
			
			conCom.workGroup = workGroup 
			
			
			conCom.save(FailonError: true, flush: true)
		
			redirect controller: params.controller
	}

	def list() {
		def contents = ContentComponent.findAll()
		
		render contents as JSON
	}
	
	def listown() {
		
		def aworkGroup = WorkGroup.find(){
			id == activeWorkGroup
		}
		
		def contents = ContentComponent.findAll() {
			workGroup == aworkGroup
		}
		
		
		def model =[
			contents : contents	
		]		
				
		render view: 'listown', model : model
	}
	
	def edit() {
        ContentComponent current = ContentComponent.find{
            id == params.id
        }


        def model = [
              id : current.id ,
              name : current.name ,
              text :  current.text

        ]

		//back to index
		render view:"edit", model:model
	}

    def edit2(){
        def current = ContentComponent.find{
            id == params.hid
        }

        current.name = params.name
        current.text = params.text

        current.save(failOnError: true)


        redirect controller: params.controller
    }
	
	def delete() {
        //find and delete component
        def current = ContentComponent.find{
             id == params.id
        }

        current.delete(failOnError: true)

		//back to index
		redirect controller: params.controller
	}
}
