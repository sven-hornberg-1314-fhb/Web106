package web106.site.component

import grails.converters.JSON
import org.springframework.security.core.context.SecurityContextHolder
import web106.auth.User
import web106.auth.WorkGroup
import web106.site.Page
import web106.site.Website
import web106.template.TemplateController
import web106.UserUtils


class WebsiteController {

    def activeWorkGroup

    def beforeInterceptor = {

        def activeWorkGroupSession = session.getAttribute('activeWorkGroup')
        if(activeWorkGroupSession == null && activeWorkGroupSession <= 0) {
            session.setAttribute("beforeUri", "${actionUri}")
            redirect(controller: "WorkGroup", action: "listWorkGroups")
        } else {
            activeWorkGroup = activeWorkGroupSession
        }
    }


    def index() {
        render view: "index"
    }

    def create(){
        render view: 'create'
    }

    def create2(){
        def aworkGroup = WorkGroup.find(){
            id == activeWorkGroup
        }

        def website = Website.newInstance()
        website.title = params.title
        website.workGroup = aworkGroup
        website.save(failOnError: true)

        redirect controller: params.controller

    }

    def listown(){

        def aworkGroup = WorkGroup.find(){
            id == activeWorkGroup
        }

        def websites = Website.findAll() {
            workGroup == aworkGroup
        }


        def model =[
                websites : websites
        ]

        render view: 'listown', model : model

    }

    def select() {
        Website website = Website.find {

            id == params.id
        }

        session.setAttribute("activeWebsite", website.id)

        redirect controller: params.controller

    }

    def activeWebsite() {

        def model = [:]

        if(session.getAttribute('activeWebsite') != null) {
            def websiteid = session.getAttribute('activeWebsite')

            Website website = Website.find {

                id == websiteid
            }

            model['activewebsite'] = website.title
        }

        render view : 'activewebsite', model: model
    }

    def listWebsites(){

        def aworkGroup = WorkGroup.find(){
            id == activeWorkGroup
        }

        def websites = Website.findAll() {
            workGroup == aworkGroup
        }

        def model = [
                websites:websites
        ]

        render view: 'listwebsites', model: model
    }

    def selectWebsites() {

        def websiteId = params.id


        session.setAttribute("activeWebsite",websiteId)



        def selectedWebsite = Website.find{id == websiteId}


        def title = selectedWebsite.title

        //back to old page
        if(session.getAttribute("beforeUri") != null && session.getAttribute("beforeUri") != "") {

            String uriPath = session.getAttribute("beforeUri");
            session.removeAttribute("beforeUri")
            redirect(uri: uriPath)

        } else {

            render view: "SuccessWebsiteSelection", model:[title: title]
        }

    }



}
