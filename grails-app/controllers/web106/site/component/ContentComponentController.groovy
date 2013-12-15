package web106.site.component

import web106.auth.WorkGroup;
import grails.converters.JSON


class ContentComponentController {

	static allowedMethods = [createComponent:'POST']
	
	def activeWorkGroup
	
	def beforeInterceptor = {
		
		def activeWorkGroupSession = session.getAttribute('activeWorkGroup')
		if(activeWorkGroupSession == null && activeWorkGroupSession <= 0) {
			session.setAttribute("beforeUri", "${actionUri}")
			redirect(controller: "WorkGroup", action: "listWorkGroups")
		} else {
			activeWorkGroup = activeWorkGroupSession
		}
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