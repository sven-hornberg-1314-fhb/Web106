package de.sixfourpixel.web106.administration
import grails.plugins.springsecurity.Secured


@Secured(['ROLE_ADMIN'])
class AdministrationController {  
  
  def index() {
    render "Some sensitive content"
  }
}
