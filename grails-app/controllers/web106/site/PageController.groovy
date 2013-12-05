package web106.site

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
		
		def data
		
		
		
		render view:'create' , model : data
    }
	 
}
