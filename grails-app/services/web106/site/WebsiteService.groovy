package web106.site

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class WebsiteService {

    def PageService pageService


    def Map<String, String> createPagesForWebsite(Website website) {

        //pagename : html
        def sites = [:]

        for (it in website.page) {
            sites[it.title] = pageService.PageAsHtmlString(it.id)
        }

        return sites
    }
}
