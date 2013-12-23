package web106.site

import grails.transaction.Transactional
import grails.gsp.PageRenderer


@Transactional
class PageService {

    PageRenderer groovyPageRenderer


    Page create(final Page page) {
	
		return page.save();	
	}
	
	Page saveOrUpdate(final Page page) {
		
	}
	
	boolean delete(final Page) {
		
	}
	
	boolean deleteById(final long id) {
		
	}

    /**
     * Generates a String containing a template with components
     * @param id Page id
     * @return HTML String
     */
    String PageAsHtmlString(final long id) {

        String content = ""
        def currentPage = Page.find{
            id == id
        }
        if(null != currentPage) {

            String tempName = currentPage.template.name
            String tempNameLower = tempName.toLowerCase()

            def model = [:]

            //iterate each box , render html of each component and add it to content
            currentPage.boxes.each {

                def html = ''

                it.component.each {
                    if(null != it) {
                        html += it.renderHTML()
                        model[it.idName] = html
                    }
                }


            }

            content = groovyPageRenderer.render(template:'/template/'+tempNameLower+'/template', model:model)
        }
    }
}
