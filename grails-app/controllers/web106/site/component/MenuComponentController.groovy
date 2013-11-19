package web106.site.component



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MenuComponentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MenuComponent.list(params), model:[menuComponentInstanceCount: MenuComponent.count()]
    }

    def show(MenuComponent menuComponentInstance) {
        respond menuComponentInstance
    }

    def create() {
        respond new MenuComponent(params)
    }

    @Transactional
    def save(MenuComponent menuComponentInstance) {
        if (menuComponentInstance == null) {
            notFound()
            return
        }

        if (menuComponentInstance.hasErrors()) {
            respond menuComponentInstance.errors, view:'create'
            return
        }

        menuComponentInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'menuComponentInstance.label', default: 'MenuComponent'), menuComponentInstance.id])
                redirect menuComponentInstance
            }
            '*' { respond menuComponentInstance, [status: CREATED] }
        }
    }

    def edit(MenuComponent menuComponentInstance) {
        respond menuComponentInstance
    }

    @Transactional
    def update(MenuComponent menuComponentInstance) {
        if (menuComponentInstance == null) {
            notFound()
            return
        }

        if (menuComponentInstance.hasErrors()) {
            respond menuComponentInstance.errors, view:'edit'
            return
        }

        menuComponentInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MenuComponent.label', default: 'MenuComponent'), menuComponentInstance.id])
                redirect menuComponentInstance
            }
            '*'{ respond menuComponentInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MenuComponent menuComponentInstance) {

        if (menuComponentInstance == null) {
            notFound()
            return
        }

        menuComponentInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MenuComponent.label', default: 'MenuComponent'), menuComponentInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'menuComponentInstance.label', default: 'MenuComponent'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
