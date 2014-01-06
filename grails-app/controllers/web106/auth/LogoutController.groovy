package web106.auth

/**
 * redirection to SpringSecurityController for secure logout of sessions
 */
class LogoutController {

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		redirect uri: '/j_spring_security_logout'
	}
}
