package web106.site

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class AdminBoxController {

    static scaffold = Box

}
