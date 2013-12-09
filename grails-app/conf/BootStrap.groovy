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
    /*
    def testUser = new User(username: 'admin', enabled: true, password: 'admin', email:'hinderli@fh-brandenburg.de', firstName: 'marcel', lastName: 'test')
    def testUser2 = new User(username: 'user', enabled: true, password: 'user', email:'hinderlo@fh-brandenburg.de', firstName: 'marcel', lastName: 'test')
    testUser.save(flush: true)
    testUser2.save(flush:true)

    UserRole.create testUser, adminRole, true
    UserRole.create testUser2, userRole, true

    //assert User.count() == 2
    //assert Role.count() == 2
   // assert UserRole.count() == 2
  */}
  
  def destroy = {
  }
}


