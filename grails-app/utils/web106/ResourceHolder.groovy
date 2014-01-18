package web106

/**
 * Holds some important defined values for the application
 */
class ResourceHolder {

    /**
     * CSS resources
     */
    def static css = [
            '/css/web106.css',
            '/css/shared/shared.css'
    ]

    /**
     * JS resources
     */
    def static js = [
            '/plugins/jquery-1.10.2.2/js/jquery/jquery-1.10.2.min.js'
    ]

    /**
     * Oauth names
     */
    def static greeting = [
            Twitter: 'screen_name',
            Google: 'name'

    ]

    /**
     * Information of user object from oauth provider
     */
    def static resource =
        [Twitter : 'https://api.twitter.com/1.1/account/verify_credentials.json',
         Google: 'https://www.googleapis.com/oauth2/v3/userinfo',
         Live: '',
         Yahoo: '' ]

    /**
     * Bucket for all Data
     */
    static String bucketGlobal = "de-64pixel-web106"

    /**
     * Name for the file in a bucket which holds information of version and date
     */
    static String bucketVersionFileName = "web106.version"

    /**
     * Prefix for uploaded images e.g. gallery
     */
    static String bucketprefixForImages = "images"

    /**
     * Bucket for storing css and js and other needed files
     */
    static String bucketStaticContent = 'web106static'
}
