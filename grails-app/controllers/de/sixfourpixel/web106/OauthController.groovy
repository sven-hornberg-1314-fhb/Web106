package de.sixfourpixel.web106

import de.sixfourpixel.web106.login.User
import groovy.json.JsonSlurper
import org.scribe.builder.api.TwitterApi
import org.scribe.model.Token
import org.scribe.model.Verifier
import uk.co.desirableobjects.oauth.scribe.OauthProvider
import uk.co.desirableobjects.oauth.scribe.OauthService
import uk.co.desirableobjects.oauth.scribe.SupportedOauthVersion
import uk.co.desirableobjects.oauth.scribe.holder.RedirectHolder
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap
import uk.co.desirableobjects.oauth.scribe.exception.MissingRequestTokenException
import grails.converters.JSON
import de.sixfourpixel.web106.ResourceHolder


class OauthController {

    private static final Token EMPTY_TOKEN = new Token('', '')

    OauthService oauthService

    /**
     *  turns response to JSON
     *  checks existence of user
     *  switches to registration or login page
     * @return
     */
    def index(){

        def provider = session.getAttribute('providername').toString().capitalize()
        def res = resource()

        def response = JSON.parse(res.getBody())
        def method = ResourceHolder.greeting."${provider}"
        def username = response."$method" as String

        //look for User with same username as in oauth login session
        def user_exists =  User.findByUsername(username)


        if(user_exists){
            log.info user_exists
            //get user data and write to session e.g. username for greeting
            redirect(uri: "/")
        }else{
            //create dummy object of new user
            def user = new User(username: username)
            render(view: "/user/register" ,model: [user: user])
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

        //def res = oauthService.get@provider"Resource(accessToken, resourceURL)

        def args = [accessToken,resourceURL]
        def method =  "get"+provider+"Resource"
        def res = oauthService."$method"(*args)

        return res
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
