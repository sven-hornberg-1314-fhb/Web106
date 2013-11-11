package de.sixfourpixel.web106.administration

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import de.sixfourpixel.web106.login.*

@Secured(['ROLE_ADMIN'])
class AdministrationController {  
  
  def index() {
    render (view: "/index")
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
}
