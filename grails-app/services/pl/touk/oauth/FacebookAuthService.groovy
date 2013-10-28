package pl.touk.oauth

import grails.converters.deep.JSON
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.FacebookApi
import org.scribe.model.OAuthRequest
import org.scribe.model.Verb
import org.scribe.model.Verifier

import org.scribe.oauth.OAuthService
import org.scribe.model.Token

class FacebookAuthService extends GrailsOAuthService {

    OAuthService createOAuthService(String callbackUrl) {
        def serviceBuilder = createServiceBuilder(FacebookApi,
                grailsApplication.config.auth.facebook.key as String,
                grailsApplication.config.auth.facebook.secret as String,
                callbackUrl)
        serviceBuilder.scope("email")
        serviceBuilder.build()
    }

    @Override
    AuthInfo getAuthInfo(String callbackUrl) {
        OAuthService authService = createOAuthService(callbackUrl)
        new AuthInfo(authUrl: authService.getAuthorizationUrl(null), service: authService)
	}

    @Override
	Token getAccessToken(OAuthService authService, Map params, Token requestToken){
		Verifier verifier = new Verifier(params.code as String)
		authService.getAccessToken(requestToken, verifier)
	}

    @Override
    OAuthProfile getProfile(OAuthService authService, Token accessToken){
        OAuthRequest request = new OAuthRequest(Verb.GET, 'https://graph.facebook.com/me')
        authService.signRequest(accessToken, request)
        def response = request.send()

        def user = JSON.parse( response.body )
        new OAuthProfile(username: user.username ?: "${user.first_name}.${user.last_name}",
                uid: user.id, email: user.email, picture: "http://graph.facebook.com/${user.id}/picture")
    }

}
