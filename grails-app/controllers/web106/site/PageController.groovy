package web106.site

import grails.converters.JSON
import web106.site.component.ContentComponent
import web106.template.TemplateController;
import web106.auth.WorkGroup
import grails.gsp.PageRenderer 
import groovy.util.slurpersupport.NodeChild;
import java.text.SimpleDateFormat



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
		
		 
    	TemplateController templateController = new TemplateController()
			
		
		def model = [
            templateNameList : templateController.listNames(),
			modelberlin : templateController.defaultmodelBerlin(), 
			modelkairo: templateController.defaultmodelKairo()
		]
	
		
		render view:'create' , model : model
		
    }
	
	
	def create_step2() {

        String tempName = params.template
        String tempNameLower = tempName.toLowerCase()

		String contents = groovyPageRenderer.render(template:'/template/'+tempNameLower+'/template', model:[])
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

		//TODO startdate -> visibleFrom, endDate -> visibleTo
		
		//parse dates
		def startDate = new SimpleDateFormat("d/M/yyyy").parse(
			"${params.startDate_day}/${params.startDate_month}/${params.startDate_year}")
		print startDate
		
		def endDate = new SimpleDateFormat("d/M/yyyy").parse(
			"${params.endDate_day}/${params.endDate_month}/${params.endDate_year}")
		print endDate
		
		
		
		//create page
		Page newPage = new Page()
		newPage.boxes = []
		newPage.title = params.titel
		newPage.visibleFrom = startDate
		newPage.visibleTo = endDate


        Template template = Template.find {
            name == tempName
        }

        newPage.template = template
		 
		//create boxes and map to page
		boxids.each {
		
			def box = new Box()
			box.idName = it
			newPage.boxes.add(box)
			
			box.save()
		}
		newPage.save(failOnError: true)
		
		//redirect to edit
		redirect action: "edit", id: newPage.id 
 

		
 
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
