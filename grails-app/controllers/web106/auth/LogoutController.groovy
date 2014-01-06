package web106.auth

/**
 * redirection to SpringSecurityController for secure logout of sessions which
 * is not secured for admin access only, so ROLE_USER have access
 */
class LogoutController {

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		redirect uri: '/j_spring_security_logout'
	}
}
