package web106.site

import grails.converters.JSON
import grails.web.RequestParameter

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PageController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Page.list(params), model:[pageInstanceCount: Page.count()]
    }

    def show(Page pageInstance) {
        respond pageInstance
    }

    def create() {
        respond new Page(params)
    }

    def create2(){

        //aktuellen user ermitteln

        //workgroup des users ermitteln

        //Boxen erstellen
        def boxes = []

        (["Menu","Sidebar","Content","Info1","Info2","Info3"]).each(){
            def box = Box.newInstance()
            box.setIdName(it)
            boxes.add(box)
        }

        def model = [
            //workgroup:workgroup,
                boxes:boxes,
                isPublic:false,
                title:"Test",
                visibleFrom:Date.newInstance(),
                visibleTo:Date.newInstance()
        ]

        print model as JSON

        render view: "/page/create2", model:model
    }

    @Transactional
    def save(Page pageInstance) {
        if (pageInstance == null) {
            notFound()
            return
        }

        if (pageInstance.hasErrors()) {
            respond pageInstance.errors, view:'create'
            return
        }

        pageInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pageInstance.label', default: 'Page'), pageInstance.id])
                redirect pageInstance
            }
            '*' { respond pageInstance, [status: CREATED] }
        }
    }

    def edit(Page pageInstance) {
        respond pageInstance
    }

    @Transactional
    def update(Page pageInstance) {
        if (pageInstance == null) {
            notFound()
            return
        }

        if (pageInstance.hasErrors()) {
            respond pageInstance.errors, view:'edit'
            return
        }

        pageInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Page.label', default: 'Page'), pageInstance.id])
                redirect pageInstance
            }
            '*'{ respond pageInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Page pageInstance) {

        if (pageInstance == null) {
            notFound()
            return
        }

        pageInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Page.label', default: 'Page'), pageInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pageInstance.label', default: 'Page'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
