class UrlMappings {

	static mappings = {
        "/"(view:"/main")
		
		"/workgroup/$wgid/$website"([controller: 'urlredirect', action: 'mapping'])

        "/live/$user/$website"([controller: "urlredirect", action: 'live'])

        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

        //"/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/error')

    }
}
