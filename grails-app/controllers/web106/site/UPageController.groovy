package web106.site

class UPageController {

    def index() {
		
		//testen, ob user eine aktive Workgroup hat
		def activeWorkGroup = session.getAttribute('activeWorkGroup')
		if(activeWorkGroup == null && activeWorkGroup <= 0) {
			redirect(controller: "UWorkGroup", action: "listWorkGroups")
		} 
		
		render view:'index'
		
	}

    def create(){
		
			
		
		render "cool"
    }
	 
}
