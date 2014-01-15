package web106.auth

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * Role of a user
 * used for springSecurity plugin for role
 * specific access of contents
 * e.g. ROLE_ADMIN, ROLE_USER
 */
@ToString
@EqualsAndHashCode
class Role {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
