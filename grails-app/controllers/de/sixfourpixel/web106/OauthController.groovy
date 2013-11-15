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

    def index(){

        def response = JSON.parse(resource().getBody())
        //render response.name
        //render response.id
        //render response.screen_name

        //look for User with same username as in oauth login session
        def user_exists =  User.findByUsername("${response.screen_name}")

        render user_exists

        if(user_exists){
            log.info user_exists
            //get user data and write to session e.g. username for greeting
            redirect(uri: "/")
        }else{

            //create dummy object of new user
            def user = new User(username: "${response.screen_name}")

            render(view: "/user/register" ,model: [user: user])
        }

    }

    def resource(){

        Token twitterAccessToken = session[oauthService.findSessionKeyForAccessToken('twitter')]

        def resourceURL = ResourceHolder.resource.twitter

        //def resourceURL = "https://api.twitter.com/1.1/account/verify_credentials.json"
        def res = oauthService.getTwitterResource(twitterAccessToken, resourceURL)

        return res
    }

    def logout(){
        session[oauthService.findSessionKeyForAccessToken(params.provider)]
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

        RedirectHolder.setUri(params.redirectUrl)
        return redirect(url: url)

    }

}
