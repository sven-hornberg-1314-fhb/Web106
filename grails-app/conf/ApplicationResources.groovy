modules = {
    application {
        resource url:'js/application.js'
    }

    customBootstrap {
        dependsOn 'bootstrap'
        resource url: 'css/scaffolding.css'
        resource id:'font-awesome-fix', url:[dir: 'less', file: 'font-awesome.less'], attrs:[rel: "stylesheet/less", type:'css', order:100], disposition: 'head'
    }
}