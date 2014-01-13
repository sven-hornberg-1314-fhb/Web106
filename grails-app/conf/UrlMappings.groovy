class UrlMappings {

	static mappings = {
        "/"(view:"/main")

        //view without auth
        "/live/$workgroup/$websitename"([controller: "urlredirect", action: 'live'])



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
