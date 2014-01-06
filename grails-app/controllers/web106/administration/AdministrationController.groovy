package web106.administration

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import web106.auth.*

/**
 * Controller for all admin functions with roles and users
 */
@Secured(['ROLE_ADMIN'])
class AdministrationController {  
  
  def index() {
    render (view: "/controllers")
  }

  def manageUsers(){
      redirect controller: 'adminUser'
  }

  def activate(User u){
      User.findById(u.getId()).enabled=true
  }

    def manageRoles(){
        redirect controller: 'adminRole'
    }

    def manageUserRoles(){
        redirect controller: 'adminUserRole'
    }

    def clearSession(){

        def attributes = ['activeWebsite','activeWebsiteName','activeWorkGroup','activeWorkGroupName']

        attributes.each {
            session.removeAttribute(it)
        }

        redirect(uri: '/')

    }
}
