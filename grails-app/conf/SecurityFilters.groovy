import web106.site.Page
import web106.site.Website

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
                        def activeWorkGroupId = session.getAttribute('activeWorkGroup')
                        def activeWebsiteId = session.getAttribute('activeWebsite')

                        //get current page
                        def currentpage = Page.find {
                                              id == params.id
                        }

                        if(currentpage == null){
                            redirect(controller: 'errors', action: 'accessDenied', params:[message:'access denied'])
                        }

                        print "page exists "+currentpage

                        //get current website
                        def currentWebsite = Website.find{
                                     id == activeWebsiteId
                        }

                        if(currentWebsite == null){
                            redirect(controller: 'errors', action: 'accessDenied', params:[message:'access denied'])
                        }

                        //get pages of current website
                        currentWebsite.page
                        //check if website contain current page
                        if(!currentpage in currentWebsite.page){
                            redirect(controller: 'errors', action: 'accessDenied', params:[message:'access denied'])
                        }else{
                            print "currentpage is in current websites pages list"

                            //compare workgroup of current and session page, redirect if not equal
                            if(!currentWebsite.workGroupId ==  activeWebsiteId){
                                redirect(controller: 'errors', action: 'accessDenied', params:[message:'access denied'])
                            }

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

