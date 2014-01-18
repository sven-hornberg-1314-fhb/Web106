package web106.administration

import grails.plugins.springsecurity.Secured

import web106.auth.*

/**
 * Controller for all admin functions with roles and users
 */
@Secured(['ROLE_ADMIN'])
class AdministrationController {

    /**
     * Stanard grails controller list
     * @return view controllers
     */
    def index() {
        render(view: "/controllers")
    }

    /**
     * User overview
     * @return view adminuser
     */
    def manageUsers() {
        redirect controller: 'adminUser'
    }

    /**
     * Activate an user
     * @param u User
     * @return User is enabled
     */
    def activate(User u) {
        User.findById(u.getId()).enabled = true
    }

    /**
     * Role overview
     * @return view overview roles
     */
    def manageRoles() {
        redirect controller: 'adminRole'
    }

    /**
     * RoleUser overview
     * @return view roles for users
     */
    def manageUserRoles() {
        redirect controller: 'adminUserRole'
    }

    /**
     * Clearing sessiondata, which belongs to website and workgroup
     * @return cleared session
     */
    def clearSession() {

        def attributes = ['activeWebsite', 'activeWebsiteName', 'activeWorkGroup', 'activeWorkGroupName']

        attributes.each {
            session.removeAttribute(it)
        }

        redirect(uri: '/')

    }
}
