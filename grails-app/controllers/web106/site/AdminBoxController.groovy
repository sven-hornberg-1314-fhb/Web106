package web106.site

import grails.plugins.springsecurity.Secured

/**
 * Standard scaffolded controller from groovy for box Domainobject
 */
@Secured(['ROLE_ADMIN'])
class AdminBoxController {

    static scaffold = Box

}
