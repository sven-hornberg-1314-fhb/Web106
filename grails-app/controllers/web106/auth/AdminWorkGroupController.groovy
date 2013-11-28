package web106.auth

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class AdminWorkGroupController {

		static scaffold = WorkGroup

}
