package web106.administration

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import web106.auth.*

@Secured(['ROLE_ADMIN'])
class AdministrationController {  
  
  def index() {
    render (view: "/controllers")
  }

  def listUsers(){
      render User.all as JSON
  }

  def activate(User u){
      User.findById(u.getId()).enabled=true
  }

    def listRoles(){
       render Role.all as JSON
    }

    def listUserRoles(){
        render UserRole.all as JSON
    }

    def clearSession(){
       //clear set session attributes website page ...
    }
}
