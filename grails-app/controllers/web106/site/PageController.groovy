package web106.site

import web106.site.component.ContentComponent
import web106.template.TemplateController;
import web106.auth.WorkGroup
import grails.gsp.PageRenderer 
import groovy.util.slurpersupport.NodeChild;
import java.text.SimpleDateFormat


class PageController {
	
	def activeWorkGroup
    def activeWebsite
	def pageService

	PageRenderer groovyPageRenderer


    def beforeInterceptor = {

        def activeWorkGroupSession = session.getAttribute('activeWorkGroup')
        activeWorkGroup = activeWorkGroupSession
        def activeWebsiteSession = session.getAttribute('activeWebsite')
        activeWebsite = activeWebsiteSession

        try {
        if(params.id != null && !IsAllowed(params.id as long)) {
            redirect(controller: 'errorsWeb106', action: 'accessDenied')
            return false
        }
        }
        catch (NumberFormatException) {
            redirect(controller: 'errorsWeb106', action: 'accessDenied')
            return false
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

		def endDate = new SimpleDateFormat("d/M/yyyy").parse(
			"${params.endDate_day}/${params.endDate_month}/${params.endDate_year}")


        Website website = Website.find {
            id == activeWebsite
        }

		
		//create page
		Page newPage = new Page()
		newPage.boxes = []
		newPage.title = params.titel
		newPage.visibleFrom = startDate
		newPage.visibleTo = endDate

        website.page = []
        website.page.add(newPage)

        newPage.website = website

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

        def current = Page.find{
            id == params.id
        }
        if(current == null) {
            redirect action:  'index'
        } else {

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
    }

    def delete(){
        //find and delete component
        def current = Page.find{
            id == params.id
        }

        if(current) {
            current.boxes?.each {
                Box.deleteAll(it)
            }


            current.delete(failOnError: true)

        }
        redirect controller: params.controller
    }

    def remoteDrop(){

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

        box?.component?.add(contentComponent)


        render status: 200
    }

    def remoteSave() {


        def currentPage = Page.find{
            id == session.getAttribute ('editpageid')
        }


        currentPage.save(failOnError: true, flush: true)

        render status: 200
    }
	
	def selecttemplate() {
		params.template
		
		flash.template = params.template
	}


    def preview() {

        long id = session.getAttribute ('editpageid') as Long
        def contents = pageService.PageAsHtmlString(id)
        render contents
    }

    def listown(){

        def pages = Page.findAll{
            website.id == activeWebsite
        }

        def model =[
                pages : pages
        ]

        render view: 'listown', model : model
    }


    boolean IsAllowed(long idValue) {

        boolean returnVal = false

        def currentPage = Page.findById(idValue)
        def websiteOfCurrentPage = currentPage?.website

        Long aLong = activeWebsite.toLong()
        if(websiteOfCurrentPage?.id.equals(aLong) && websiteOfCurrentPage?.workGroupId == activeWorkGroup){
            returnVal = true
        }
        return returnVal
    }

}
