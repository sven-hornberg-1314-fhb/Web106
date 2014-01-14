package web106.site

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
        activeWorkGroup = activeWorkGroupSession

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

    def edit(){
        def current = Website.find{
            id == params.id
        }
        if(current == null) {
            redirect action:  'index'
        } else {

            def title = current.title


            session.setAttribute('editwebsiteid',params.id)

            def model = [
                    title: title,
                    pages: current.page,
                    id:params.id
            ]

            render view:'edit' , model : model
        }
    }

    def delete(){
        //find and delete component
        def current = Website.find{
            id == params.id
        }

        //delete using services
        /*current?.page.each{
            pageService.delete(it)
        }

        websiteService.delete(current)*/

        //delete without services
        current?.page.each{
            Page.deleteAll(it)
        }

        current?.delete()

        //back to index
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

        def selectedWebsite = Website.find{id == website.id}


        def title = selectedWebsite.title
        session.setAttribute("activeWebsiteName",selectedWebsite.title)



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

    //todo test double usage ?
    def selectWebsites() {

        def websiteId = params.id


        session.setAttribute("activeWebsite",websiteId)




        def selectedWebsite = Website.find{id == websiteId}


        def title = selectedWebsite.title
        session.setAttribute("activeWebsiteName",selectedWebsite.title)

        render view: "SuccessWebsiteSelection", model:[title: title]


    }

    def remoteSave() {

        def currentWebsite = Website.find{
            id == session.getAttribute ('editwebsiteid')
        }

        currentWebsite.title = params.title

        currentWebsite.save(failOnError: true, flush: true)

        //render "200" // Statuscode besser setzen

        redirect view: index()
    }




}
