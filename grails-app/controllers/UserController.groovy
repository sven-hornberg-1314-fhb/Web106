/**
 * controls local registration process, access of oauth providers login
 */
import de.sixfourpixel.web106.login.Role
import de.sixfourpixel.web106.login.User
import de.sixfourpixel.web106.login.UserRole
import grails.converters.JSON
import grails.plugins.springsecurity.Secured

class UserController {



	def index = {
		render(view: "/user/register")
	}

    def register = {
        // new user posts his registration details
        if (request.method == 'POST') {
            // create domain object and assign parameters using data binding
            def u = new User(params)
            //u.passwordHashed = u.password.encodeAsPassword()
            u.enabled=true

            if (! u.save(flush: true)) {
                // validation failed, render registration page again
                return [user:u]
            } else {
                // validate/save ok, store user in session, redirect to homepage
                UserRole.create u, Role.findByAuthority('ROLE_USER'), true
                session.user = u
                redirect(uri:"/")
            }
        } else if (session.user) {
            // don't allow registration while user is logged in
            redirect(uri:"/")
        }
    }

    def login = {
        redirect(controller: "login")
    }

    def logout = {
        redirect(controller: "logout")
    }


    def list = {
        render User.all as JSON
    }
}
