package web106.site.component

import web106.auth.WorkGroup;
import grails.converters.JSON


class ContentComponentController {

	static allowedMethods = [createComponent:'POST']
	
	
	def index() {
		
		//testen, ob user eine aktive Workgroup hat
		def activeWorkGroup = session.getAttribute('activeWorkGroup')
		if(activeWorkGroup == null && activeWorkGroup <= 0) {
			redirect(controller: "WorkGroup", action: "listWorkGroups")
		}
		
		render view:'index'
	}
	
	def create() {
		render view:'create'
	}
	
	def createComponent() {
		
		
		ContentComponent conCom = ContentComponent.newInstance()
		
		
		conCom.name = params.name
		conCom.text = params.text
		//find Workgroup 
		
		def workGroup = WorkGroup.find(){
			id == 1
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
		
		
		
		render view: 'listown', model : ['text': 'meinCoolerText']
	}
}
