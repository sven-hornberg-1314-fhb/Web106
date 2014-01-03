package web106.site

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class WebsiteService {

    def PageService pageService

    //pagename : html
    def sites = [:]

    def createPagesForWebsite(Website website) {

        website.page.each {

            sites [it.title] = pageService.PageAsHtmlString(it.id)

        }

        print sites as JSON
    }
}
