package web106.auth

import org.scribe.exceptions.OAuthConnectionException
import org.scribe.model.Token
import org.scribe.model.Verifier
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import uk.co.desirableobjects.oauth.scribe.OauthProvider
import uk.co.desirableobjects.oauth.scribe.OauthService
import uk.co.desirableobjects.oauth.scribe.SupportedOauthVersion
import uk.co.desirableobjects.oauth.scribe.holder.RedirectHolder
import uk.co.desirableobjects.oauth.scribe.exception.MissingRequestTokenException
import grails.converters.JSON
import web106.ErrorsWeb106Controller
import web106.ResourceHolder

/**
 * OauthController for managing login redirection,
 * Oauth authentification with verifier exchange to provider
 * including callback and registration
 */
class OauthController {

    private static final Token EMPTY_TOKEN = new Token('', '')

    /**
     * external service for managing session specific attributes
     */
    OauthService oauthService

    /**
     * internal check for admin access when registering users
     * which is defined in Config.groovy oauth.admins
     * @param providername
     *      name of provider used for registration
     * @param username
     *      name of user which is to be registered
     * @return
     */
    private boolean checkAdmin(String providername, String username) {

        boolean returnValue = false

        if(providername=='twitter'){
            grailsApplication.config.oauth.admins.twitter.each{
                if(username.equals(it)){
                    returnValue = true
                }
            }
        }

        if(providername=='google'){
            grailsApplication.config.oauth.admins.google.each{
                if(username.equals(it)){
                    returnValue = true
                }
            }
        }
        return returnValue
    }


    /**
     *  turns response to JSON
     *  checks existence of user
     *  switches to registration or auth page
     * @return
     */
    def index(){

        def providername = session.getAttribute('providername').toString().capitalize()
        def res = resource()

        def response = JSON.parse(res.body)
        def method = ResourceHolder.greeting."${providername}"
        def username = response."$method" as String

        //look for User with same username as in oauth auth session
        def user_exists =  User.findByUsername(username)

        if(user_exists){

            Authentication auth = new UsernamePasswordAuthenticationToken (user_exists.username,null,user_exists.getGrantedAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

            session.removeAttribute('step')
            redirect(uri: "/")

        }else{

            def provider = session.getAttribute('providername')
            Token accessToken = session[oauthService.findSessionKeyForAccessToken(provider)]

            if(session.providername){
                session.setAttribute('step','Step2')
                def user = new User(username: username)
                user.tokens.put(provider,accessToken)

                render(view: "/oauth/register" ,model: [user: user])
            }else{
                session.setAttribute('step','Step1')
                render(view: "/oauth/register")
            }
        }
    }

    /**
     * gets providerspecific content of user information
     * @return
     *      res, provider content
     */
    def resource(){

        def provider = session.getAttribute('providername')
        Token accessToken = session[oauthService.findSessionKeyForAccessToken(provider)]

        provider = provider.toString().capitalize()
        def resourceURL = ResourceHolder.resource.get(provider)

        def args = [accessToken,resourceURL]
        def method =  "get"+provider+"Resource"
        def res = oauthService."$method"(*args)

        return res
    }

    /**
     * manages stepwise registration of users,
     * creates and specifies userroles depending on internal checkAdmin method,
     * redirects to main page
     * @return
     */
   def register() {
        // new user posts his registration details
        if (request.method == 'POST') {
            // create domain object and assign parameters using data binding
            def u = new User(params)
            u.enabled=true

            if (! u.save(flush: true)) {
                // validation failed, render registration page again
                return [user:u]
            } else {
                // validate/save ok, store user in session, redirect to homepage
                UserRole.create u, Role.findByAuthority('ROLE_USER'), true

                if(session.providername != "" && checkAdmin(session.providername, u.username)) {
                    UserRole.create u, Role.findByAuthority('ROLE_ADMIN'), true
                }
                // is the session used anymore? ,comes from former example
                session.removeAttribute('step')

                Authentication auth = new UsernamePasswordAuthenticationToken (u.username,null,u.getGrantedAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);

                redirect(uri:"/")

            }
        } else if (session.user) {
            // don't allow registration while user is logged in
            redirect(uri:"/")
        }
    }

    /**
     * redirection to login controller if registred
     * or else to main page
     */
    def login = {
        if(!session.user){
            redirect(controller: "login")
        }else{
            redirect(uri:"/")
        }
    }

    /**
     * gets Verifier and accessToken from requestToken and params
     * deletes requestToken
     * redirects to Config.groovy defined successUri for specific provider
     */
    def callback = {

        String providerName = params.provider
        OauthProvider provider = oauthService.findProviderConfiguration(providerName)

        //get verifiert from QueryString
        String ver =  params.get('oauth_verifier') as String

        Verifier verifier = new Verifier(ver)

        if (!verifier) {
            redirect(uri: provider.failureUri)
            return
        }

        Token requestToken = provider.oauthVersion == SupportedOauthVersion.TWO ?
            new Token(params?.code, "") :
            (Token) session[oauthService.findSessionKeyForRequestToken(providerName)]

        if (!requestToken) {
            throw new MissingRequestTokenException(providerName)
        }

        Token accessToken = oauthService.getAccessToken(providerName, requestToken, verifier)

        session[oauthService.findSessionKeyForAccessToken(providerName)] = accessToken
        session.removeAttribute(oauthService.findSessionKeyForRequestToken(providerName))

        return redirect(uri: provider.successUri)

    }

    /**
     * sends requestToken and credentials (Config.groovy - oauth.providers) to provider,
     * requests accessToken and verifier from provider
     * redirects to redirectURL of configured website application of provider
     */
    def authenticate = {

        try {
            String providerName = params.provider
            OauthProvider provider = oauthService.findProviderConfiguration(providerName)

            Token requestToken = EMPTY_TOKEN
            if (provider.oauthVersion == SupportedOauthVersion.ONE) {
                requestToken = provider.service.requestToken
            }

            session[oauthService.findSessionKeyForRequestToken(providerName)] = requestToken
            String url = oauthService.getAuthorizationUrl(providerName, requestToken)

            //set provider to session
            session.setAttribute("providername",providerName)

            RedirectHolder.setUri(params.redirectUrl)
            return redirect(url: url)
        } catch(OAuthConnectionException) {

            redirect(controller: 'errorsWeb106', action: 'oauth')
        }
    }

    /**
     * renders error page
     * @return
     */
    def error (){
        render(view: 'error')
    }

}
