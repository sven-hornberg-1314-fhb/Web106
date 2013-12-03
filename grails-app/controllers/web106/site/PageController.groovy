package web106.site

class PageController {

    def index() {
		
		//testen, ob user eine aktive Workgroup hat
		def activeWorkGroup = session.getAttribute('activeWorkGroup')
		if(activeWorkGroup == null && activeWorkGroup <= 0) {
			redirect(controller: "WorkGroup", action: "listWorkGroups")
		} 
		
		render view:'index'
		
	}

    def create(){
		
			
		
		render view:'create'
    }
	 
}
