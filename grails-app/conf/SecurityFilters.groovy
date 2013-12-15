import web106.site.Page

/**
* Our filter to sanitize URLs. Grails detects any Groovy class inside the
* grails-app/conf folder ending with 'Filters' to be a filter by convention.
*
* before - Applied before processing a request URL.
* after - Applied after processing a request URL, but before rendering the view.
* afterView - Applied after rendering the view.
*
* @author lasantha
*/
class SecurityFilters {
    // All filters are defined is this 'filters' block
    def filters = {
        // 'urlSanitizeFilter' is the name of our filter.Specify any name.
        // controller:'student',action:'*' means 
        // 'All actions in student controller'
        // If you want to capture all actions, use controller:'*',action:'*'
        urlSanitizeFilter(controller:'page',action:'*') {
            // can be one of 'before','after','afterView'
            before = {
                if(params?.id) { // if a parameter named 'id' is passed in.
                    // if id is not a number of 1-10 digits,
                    if(!(params?.id ==~ /\d{1,10}/)){
			            redirect(controller: 'errors', action: 'accessDenied', params:[message:'only numbers allowed'])
                        return false
		            }else if(!session.getAttribute('activeWorkGroup')){
		                redirect(controller: 'WorkGroup', action: 'listWorkGroups')
		            }else{
                        def activeWorkGroupSession = session.getAttribute('activeWorkGroup')
                        def activeWebsiteSession = session.getAttribute('activeWebsite')

                        if(params.id != null){
                            print params
                            //2. test ob aktuelle anzueigene page die workgroup, die selbe ist ,
                            // wie die aktuelle activworkgroup in der session -> falls nicht redirect auf not authorized
                        }



		            }

                }
            }


        }


        // You can add other filters here. The order in which filters 
        // are matched will be determined by the order in which they appear 
       // in the 'filters' block. Filters are matched from top to bottom.
   }
}

