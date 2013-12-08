package web106.site

import grails.converters.JSON
import web106.site.component.ContentComponent
import web106.auth.WorkGroup
import grails.gsp.PageRenderer 
import groovy.util.slurpersupport.NodeChild;



class PageController {
	
	def activeWorkGroup
	PageRenderer groovyPageRenderer
	
	def boxService
	def pageService
	
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
		

		String contents = groovyPageRenderer.render(template:"/template/kairo/template", model:[])	
		//get all div ids with class dropbox
		
		def rootNode = new XmlSlurper().parseText(contents)
		List<NodeChild> divNodes = []
		def boxids = []

		//find all div tags
		rootNode.'**'.findAll { it.name() == 'div' }.each { divNodes.add(it) }
		
		//find all divs with class dropbox and add to boxids
		divNodes.each {
			
			if(it.@class.text().contains("dropbox")) {
				boxids.add(it.@id.text())
			}
		}
		
	
		
		//create page
		Page newPage = new Page()
		
		//create boxes and map to page
		
		//redirect to edit
		render boxids as JSON
		
	}
	
	def edit() {
		
		//page id
		//check rights
		
        print params 
		
		
		
		//find ContentComponents
		def selectedWorkgroup = WorkGroup.find{id == activeWorkGroup}
		
		def contents = ContentComponent.findAll() {
		
			workGroup == selectedWorkgroup;
		}
		
		
		def model = [
				contents : contents

		]
		
		render view:'edit' , model : model
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
