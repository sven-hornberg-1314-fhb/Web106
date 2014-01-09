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

    /**
     * Prefix for s3 buckets, because all bucketNames across s3 are unique
     */
    def static bucketprefix = "de.64pixel.web106"

    /**
     * Name for the file in a bucket which holds information of version and date
     */
    def static bucketVersionFileName = "web106.version"

    /**
     * Prefix for uploaded images e.g. gallery
     */
    def static bucketprefixForImages = "images"
}
