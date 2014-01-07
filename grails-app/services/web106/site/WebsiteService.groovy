package web106.site

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class WebsiteService {

    def PageService pageService

    /**
     * Creates pages dictionary 'titel: content as String'
     * @param website Website for creation
     * @return dictionary 'titel: content as String'
     */
    def Map<String, String> createPagesForWebsite(Website websiteParam) {

        //pagename : html
        def sites = [:]

        List<Page> pages = Page.findAll() {
            website == websiteParam
        }

        pages.each {
            sites.put(it.title.toLowerCase(),pageService.PageAsHtmlString(it.id))
        }

        return sites
    }
}
