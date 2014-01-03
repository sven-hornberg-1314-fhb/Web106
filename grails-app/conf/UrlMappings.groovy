class UrlMappings {

	static mappings = {
        "/"(view:"/main")
		
		"/workgroup/$wgid/$website"([controller: 'urlredirect', action: 'mapping'])

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
