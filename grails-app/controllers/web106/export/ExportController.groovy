package web106.export

import grails.converters.JSON
import web106.auth.WorkGroup
import web106.site.Website
import web106.site.WebsiteService


class ExportController {

    def WebsiteService websiteService

    def index() {

        render view: "index"
    }

    def cloudS3() {
        def model = [:]

        render view: "cloudS3", model: model
    }

    def cloudS3export() {

        Website website = Website.find() {
            id == params.id
        }
        websiteService.createPagesForWebsite(website)

        render "done export s3*dummy*"
    }

    def listown() {
        def aWorkgroupId = session.getAttribute("activeWorkGroup")

        WorkGroup aWorkgroup = WorkGroup.find {
            id == aWorkgroupId
        }

        def websites = Website.findAll() {
            workGroup == aWorkgroup
        }

        def model = [:]

        def websitesView = []
        websites.each {

            def item = [:]
            item['id'] = it.id
            item['title'] = it.title
            websitesView.add(item)

        }

        model['websites'] = websitesView

        render view : "listown", model: model
    }
}
