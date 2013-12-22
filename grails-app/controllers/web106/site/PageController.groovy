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
    def activeWebsite
	PageRenderer groovyPageRenderer
	
	def boxService
	def pageService

    def beforeInterceptor = {

        def activeWorkGroupSession = session.getAttribute('activeWorkGroup')
        activeWorkGroup = activeWorkGroupSession
        def activeWebsiteSession = session.getAttribute('activeWebsite')
        activeWebsite = activeWebsiteSession
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
		
		def rootNode = new XmlSlurper(false,true,true).parseText(contents)
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

        def current = Page.find{
            id == params.id
        }
        def title = current.title


        def templatenameLowercase = current.template.name.toLowerCase()
		
		//find ContentComponents
		def selectedWorkgroup = WorkGroup.find{id == activeWorkGroup}
		
		def contents = ContentComponent.findAll() {
		
			workGroup == selectedWorkgroup;
		}


        session.setAttribute('editpageid',params.id)

		def model = [
				contents : contents,
                title: title,
                template:templatenameLowercase,
                id:params.id
		]
		
		render view:'edit' , model : model
    }

    //Todo Boxes löschen
    def delete(){
        //find and delete component
        def current = Page.find{
            id == params.id
        }

        current.delete(failOnError: true)

        //back to index
        redirect controller: params.controller
    }

    def remoteDrop(){

        print params

        //boxid
        def dropId = params.dropId

        //id of contentcomponent
        def dragId = params.dragId

        def currentPage = Page.find{
            id == session.getAttribute ('editpageid')
        }

        def boxes = currentPage.boxes
        Box box

        boxes.each {
            if(it.idName.equals(dropId)) {
                box = it
            }
        }

        def contentComponent = ContentComponent.find {
            id == dragId
        }
        try {
            box.component.add(contentComponent)
        }catch (Exception ex) {
            print ex.getMessage()
        }

        print "200"
    }

    def remoteSave() {


        def currentPage = Page.find{
            id == session.getAttribute ('editpageid')
        }


        currentPage.save(failOnError: true, flush: true)

        render "200" // Statuscode besser setzen
    }
	
	def selecttemplate() {
		params.template
		
		flash.template = params.template
		print params

	}


    def preview() {


        def currentPage = Page.find{
            id == session.getAttribute ('editpageid')
        }

        String tempName = currentPage.template.name
        String tempNameLower = tempName.toLowerCase()

        def model = [:]

        //jede box durchlaufen, html der componenten erzeugen und ins model stecken

        currentPage.boxes.each {

            def html = ''

            it.component.each {
                html += it.renderHTML()
            }


            model[it.idName] = html
        }

        String contents = groovyPageRenderer.render(template:'/template/'+tempNameLower+'/template', model:model)
        render contents
    }

    def listown(){
        def aworkGroup = WorkGroup.find(){
            id == activeWorkGroup
        }

        def pages = Page.findAll()

        //Todo: Filter by Workgroup
        def model =[
                pages : pages
        ]

        render view: 'listown', model : model
    }



}
