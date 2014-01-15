package web106.site

import grails.plugin.cache.Cacheable
import grails.transaction.Transactional
import web106.ResourceHolder


@Transactional
class PageService {

    def groovyPageRenderer

    Page create(final Page page) {
	
		return page.save();	
	}

	Page saveOrUpdate(final Page page) {
        page.save()
	}

	void delete(final Page page) {
		page.delete()

	}

	void deleteById(final long id) {
		Page page = Page.findById(id)
        page.delete()
    }

    /**
     * Generates a String containing a template with components
     * @param id Page id
     * @return HTML String
     */
    String PageAsHtmlString(final long id ) {
        String content = ""
        def currentPage = Page.findById(id)

        if(null != currentPage) {

                def model = ModelforPageRendering(currentPage)

                content = groovyPageRenderer.render(template:'/template/'+model.template +'/template', model:model)

                //remove frist newline caused by template encoding
                if(content.startsWith('\n')) {
                    content = content.substring(1)
                }
        }
        return content
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
        model['web106header'] = ''
        model['web106title'] = page.title
        String header = UriHeaderModel()
        if(header) {
            model['web106header'] = UriHeaderModel()
        }
        return model
    }

    @Cacheable('StaticContent')
    def String UriHeaderModel() {
        //css, js into model
        def uriPrefix = 'http://s3-eu-west-1.amazonaws.com/' + ResourceHolder.bucketStaticContent + '/'
        def header = ''

        ResourceHolder.css.each {
            def item = it.tokenize('/').last()
            header += '<link rel="stylesheet" type="text/css" href="' + uriPrefix + item + '" >' + '\n'
        }

        ResourceHolder.js.each {
            def item = it.tokenize('/').last()
            header += '<script type="text/javascript" src="' + uriPrefix + item + '" ></script>' + '\n'
        }

        header
    }
}
