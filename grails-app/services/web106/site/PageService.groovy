package web106.site

import grails.transaction.Transactional
import grails.gsp.PageRenderer
import web106.ResourceHolder


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
    String PageAsHtmlString(final long id ) {
        String content = ""
        def currentPage = Page.find{
            id == id
        }
        if(null != currentPage) {

                def model = ModelforPageRendering(currentPage)

                content = groovyPageRenderer.render(template:'/template/'+model.template +'/template', model:model)
        }

    }

    /**
     * Creates the content for a dummy index page
     * @param pagesNames List of pageNames
     * @return html content
     */
    String createDummyIndexPage(List<String> pagesNames) {
        String returnVal = ""

        returnVal += '<!DOCTYPE HTML><html><body>'

        pagesNames.each {
            returnVal += '<a href="'+ it.toLowerCase() + '.html" >'+ it +'</a><br>'
        }

        returnVal += '</body></html>'

        return returnVal
    }

    /**
     * Generates Model for placeholder boxes in template , and contains the name of the template
     * (createt for testing rendering)
     * @param page Page
     * @return Model for rendering a template
     */
    def ModelforPageRendering(final Page page) {

        String tempName = page.template.name
        String tempNameLower = tempName.toLowerCase()
        def model = [:]
        model['template'] = tempNameLower

        if(tempName != null) {

            //iterate each box , render html of each component and add it to content
            page.boxes.each {

                def html = ''

                it.component.each {
                    if(null != it) {
                        html += it.renderHTML()

                    }
                }
                model[it.idName] = html

            }


        }

        //later in method and caching
        //css, js into model
        def uriPrefix = 'https://s3-eu-west-1.amazonaws.com/'+ ResourceHolder.bucketStaticContent +'/'
        def header = ''

        ResourceHolder.css.each {
            header += '<link rel="stylesheet" type="'+ uriPrefix + it +'" />' + '\n'
        }

        model['header'] = header

        return model
    }
}
