package pl.touk.oauth

import grails.converters.deep.XML
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.LinkedInApi
import org.scribe.model.OAuthRequest
import org.scribe.model.Token
import org.scribe.model.Verb
import org.scribe.model.Verifier
import org.scribe.oauth.OAuthService

class LinkedinAuthService extends GrailsOAuthService {

    @Override
    OAuthService createOAuthService(String callbackUrl) {
        return createServiceBuilder(LinkedInApi.class,
                   grailsApplication.config.auth.linkedin.key as String,
                   grailsApplication.config.auth.linkedin.secret as String,
                   callbackUrl).build()
    }

    AuthInfo getAuthInfo(String callbackUrl) {
        OAuthService authService = createOAuthService(callbackUrl)
		Token requestToken = authService.getRequestToken();
		new AuthInfo(authUrl: authService.getAuthorizationUrl(requestToken), requestToken: requestToken, service: authService)
	}

    Token getAccessToken(OAuthService authService, Map params, Token requestToken ){
		Verifier verifier = new Verifier(params.oauth_verifier)
		authService.getAccessToken(requestToken, verifier)
	}

	OAuthProfile getProfile(OAuthService authService, Token accessToken) {
		def profile = sendRequest(authService, accessToken, Verb.GET, "http://api.linkedin.com/v1/people/~")
        def m = profile.'*'.url =~ /.*key=(\d*)&.*/
        def uid = m[0][1]
        def login = "${profile.'first-name' as String}.${profile.'last-name' as String}".toLowerCase()
        new OAuthProfile(username: login, uid: uid)
	}

	private sendRequest(OAuthService authService, Token accessToken, Verb method, String url){
		OAuthRequest request = new OAuthRequest(method, url)
		authService.signRequest(accessToken, request)
		def response = request.send()
		return XML.parse(response.body)
	}

}