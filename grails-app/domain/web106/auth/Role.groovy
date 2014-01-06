package web106.auth

/**
 * Role of a user
 * used for springSecurity plugin for role
 * specific access of contents
 * e.g. ROLE_ADMIN, ROLE_USER
 */
class Role {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
