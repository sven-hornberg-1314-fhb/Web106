package web106.auth

import grails.plugins.springsecurity.Secured

/**
 * Controller for management of Workgroups which
 * is secured for ROLE_ADMIN access only
 * methods are scaffolded at runtime for WorkGroups
 * which include CREATE - READ/LIST - UPDATE - DELETE of Workgroups
 */
@Secured(['ROLE_ADMIN'])
class AdminWorkGroupController {

		static scaffold = WorkGroup

}
