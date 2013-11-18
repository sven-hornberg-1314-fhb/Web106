package web106.auth
/**
 * controls local registration process, access of oauth providers auth
 */
import uk.co.desirableobjects.oauth.scribe.OauthService

class UserController {
    OauthService oauthService


	def index = {

        //falls token in der session --> step2
		//render(view: "/user/register")
        redirect(uri: "/user/register")
	}

    def register = {
        // new user posts his registration details
        if (request.method == 'POST') {
            // create domain object and assign parameters using data binding
            def u = new User(params)
            //u.password = u.password.encodeAsSHA256()
            u.enabled=true

            //def provider = session.getAttribute('providername')
            //Token accessToken = session[oauthService.findSessionKeyForAccessToken(provider)]
            //u.tokens.put(provider,accessToken)

            if (! u.save(flush: true)) {
                // validation failed, render registration page again
                return [user:u]
            } else {
                // validate/save ok, store user in session, redirect to homepage
                UserRole.create u, Role.findByAuthority('ROLE_USER'), true

                // is the session used anymore? ,comes from former example
                session.user = u
                session.removeAttribute('step')
                redirect(uri:"/")

            }
        } else if (session.user) {
            // don't allow registration while user is logged in
            //TODO not working
            redirect(uri:"/")
        }
    }

    def login = {
        if(!session.user){
            redirect(controller: "login")
        }else{
            redirect(uri:"/")
        }

    }

    def logout = {
        redirect(controller: "logout")
    }

}
