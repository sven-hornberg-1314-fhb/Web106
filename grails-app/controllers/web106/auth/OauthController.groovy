package web106.auth

import grails.plugins.springsecurity.SpringSecurityService
import org.scribe.model.Token
import org.scribe.model.Verifier
import uk.co.desirableobjects.oauth.scribe.OauthProvider
import uk.co.desirableobjects.oauth.scribe.OauthService
import uk.co.desirableobjects.oauth.scribe.SupportedOauthVersion
import uk.co.desirableobjects.oauth.scribe.holder.RedirectHolder
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import uk.co.desirableobjects.oauth.scribe.exception.MissingRequestTokenException
import grails.converters.JSON
import web106.ResourceHolder

import javax.servlet.http.HttpSession


class OauthController {

    SpringSecurityService springSecurityService

    private static final Token EMPTY_TOKEN = new Token('', '')

    OauthService oauthService


    private boolean checkAdmin(String providername, String username) {

        print username

        boolean returnValue = false

        if(providername=='twitter'){
            grailsApplication.config.oauth.admins.twitter.each{
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
            session.user = User.findByUsername(username)

            session.removeAttribute('step')
            redirect(uri: "/")
        }else{

            def provider = session.getAttribute('providername')
            Token accessToken = session[oauthService.findSessionKeyForAccessToken(provider)]

            if(session.providername){
                session.setAttribute('step','Step2')
                def user = new User(username: username )
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

   def register() {
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

                if(session.providername != "" && checkAdmin(session.providername, u.username)) {
                    UserRole.create u, Role.findByAuthority('ROLE_ADMIN'), true
                }
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

    /**
     * resets complete session
     * @return
     */
    def logout(){
        session.invalidate()
        redirect(uri:'/')
    }

    def callback = {

        String providerName = params.provider
        OauthProvider provider = oauthService.findProviderConfiguration(providerName)

        Verifier verifier = extractVerifier(provider, params)

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

    private Verifier extractVerifier(OauthProvider provider, GrailsParameterMap params) {

        String verifierKey = determineVerifierKey(provider)

        if (!params[verifierKey]) {
            log.error("Cannot authenticate with oauth: Could not find oauth verifier in ${params}.")
            return null
        }

        String verification = params[verifierKey]
        return new Verifier(verification)

    }

    private String determineVerifierKey(OauthProvider provider) {

        return SupportedOauthVersion.TWO == provider.oauthVersion ? 'code' : 'oauth_verifier'

    }

    def authenticate = {

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

    }

}
