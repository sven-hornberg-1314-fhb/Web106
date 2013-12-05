package web106.site.component

import web106.auth.WorkGroup;
import grails.converters.JSON


class ContentComponentController {

	static allowedMethods = [createComponent:'POST']
	
	def activeWorkGroup
	
	def beforeInterceptor = {
		
		def activeWorkGroupSession = session.getAttribute('activeWorkGroup')
		if(activeWorkGroupSession == null && activeWorkGroupSession <= 0) {
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
			render conCom as JSON
		
		
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
		
		render contents as JSON
		
		render view: 'listown', model : ['text': 'meinCoolerText']
	}
}
