package web106

/**
 *
 */
class ResourceHolder {


    def static greeting = [
            Twitter: 'screen_name',
            Google: 'name'

    ]

    def static resource =
        [Twitter : 'https://api.twitter.com/1.1/account/verify_credentials.json',
         Google: 'https://www.googleapis.com/oauth2/v3/userinfo',
         Live: '',
         Yahoo: '' ]

}
