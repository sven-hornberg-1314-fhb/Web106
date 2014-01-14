package web106.urlmapping

import web106.ResourceHolder

class UrlredirectController {

    def index() {
        render(status: 404, text: 'nicht gefunden')
    }

    /**
     * e.g. http://localhost:8080/Web106/live/testexportbucket-webseite/index.html
     *      Path: /live/testexportbucket-website/index.html
     */
    def live() {

        def workgroupName
        def websiteName
        def pageName = params.website

        if(params?.workgroup && params?.websitename) {
            workgroupName = params.workgroup
            websiteName =  params.websitename

            def global = ResourceHolder.bucketGlobal

            def url = 'https://s3-eu-west-1.amazonaws.com/' + global+ "/" + workgroupName+'/'+websiteName+'/index.html'
            url = url.toLowerCase()

            redirect(url: url)
        } else {
            render status: 404
        }

    }
	
}
