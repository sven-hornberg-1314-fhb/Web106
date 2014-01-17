class UrlMappings {

	static mappings = {
        "/"(view:"/main")

        //view without auth
        "/live/$workgroup?/$websitename?"([controller: "urlredirect", action: 'live'])

        "/stream/$workgroup?/$websitename?/$page?"([controller: "urlredirect", action: 'stream'])


        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

        //"/"(view:"/index")
        "500"(view:'/error')
        "404"([controller: "errorsWeb106", action: 'accessDenied'])
        "405"([controller: "errorsWeb106", action: 'MethodNotAllowed'])

    }
}
