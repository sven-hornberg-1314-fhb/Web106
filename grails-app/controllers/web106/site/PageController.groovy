package web106.site

import grails.converters.JSON
import web106.site.component.ContentComponent
import web106.auth.WorkGroup

class PageController {
	
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

    def create(){
		
		def model = [

		]
	
		
		render view:'create' , model : model
    }
	
	def create_step2() {
		
        print params
		
		
		//find ContentComponents
		def selectedWorkgroup = WorkGroup.find{id == activeWorkGroup}
		
		def contents = ContentComponent.findAll() {
		
			workGroup == selectedWorkgroup;
		}
		
		
		def model = [
				contents : contents

		]
		
		render view:'create_step2' , model : model
    }

    def remote(){
        print params
    }
	
	def selecttemplate() {
		params.template
		
		flash.template = params.template
		print params
	
	}

}
