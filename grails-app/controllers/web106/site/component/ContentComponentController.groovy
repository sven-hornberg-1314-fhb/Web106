package web106.site.component

import web106.auth.WorkGroup;
import grails.converters.JSON

/**
 * Controller for text component
 */
class ContentComponentController {

	static allowedMethods = [createComponent:'POST', edit2: 'POST']

	def activeWorkGroup

    def activeWebsite

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

    /**
     * Tests if user is allowed to view contentcomponent
     * @param idValue Id of contentcomponent
     * @return true is allowed , false not allowed
     */
    boolean IsAllowed(long idValue) {

        boolean returnVal = false

        def currentContentComponent = ContentComponent.findById(idValue)
        currentContentComponent.workGroup.id

        if(currentContentComponent.workGroup.id == activeWorkGroup){
            returnVal = true
        }

        return returnVal
    }

    /**
     * Index view
     */
	def index() {
		render view:'index'
	}

    /**
     * Create view
     */
	def create() {
		render view:'create'
	}

    /**
     * Creates a new contentcomponent
     * @return controller index
     */
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

    /**
     * Returns all ContentComponents as JSON
     * @return JSON
     */
	def list() {
		def contents = ContentComponent.findAll()
		
		render contents as JSON
	}

    /**
     * Shows all contentcomponents which belongs to the same Workgroup
     * @return list of contentcomponents
     */
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

    /**
     * Edit for contentcomponent
     * @return view edit
     */
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

    /**
     * 2 Step for edit, for formdata
     * @return index view
     */
    def edit2(){
        def current = ContentComponent.find{
            id == params.hid
        }

        current.name = params.name
        current.text = params.text

        current.save(failOnError: true)


        redirect controller: params.controller
    }

    /**
     * Deletes a contentcomponent
     * @return index view
     */
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
