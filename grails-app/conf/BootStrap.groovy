import de.sixfourpixel.web106.login.Role
import de.sixfourpixel.web106.login.User
import de.sixfourpixel.web106.login.UserRole

class BootStrap {

  def init = { servletContext ->
    def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
    def userRole = new Role(authority: 'ROLE_USER').save(flush: true)

    def testUser = new User(username: 'admin', enabled: true, password: 'admin')
    def testUser2 = new User(username: 'user', enabled: true, password: 'user')
    testUser.save(flush: true)
    testUser2.save(flush:true)

    UserRole.create testUser, adminRole, true
    UserRole.create testUser2, userRole, true

    //assert User.count() == 2
    //assert Role.count() == 2
   // assert UserRole.count() == 2
  }
  
  def destroy = {
  }
}


