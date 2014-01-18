package web106.site

import grails.plugins.springsecurity.Secured

/**
 * Standard scaffolded controller from groovy for page Domainobject
 */
@Secured(['ROLE_ADMIN'])
class AdminPageController {

	static scaffold = Page
}		

