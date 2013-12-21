import web106.auth.Role
import web106.auth.User
import web106.auth.UserRole
import web106.site.Template

class BootStrap {

  def init = { servletContext ->
      def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
      def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
  	
	  def berlintemplate = new Template(name:'Berlin').save(failOnError: true)
	  def kariotemplate = new Template(name:'Kairo').save(failOnError: true)
   }

  def destroy = {
  }
}


