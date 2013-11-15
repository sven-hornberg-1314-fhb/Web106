package de.sixfourpixel.web106.login

import org.scribe.model.Token

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled

    String email
    String firstName
    String lastName

    //e.g. tokens<Facebook,FBToken>
    Map<String,Token> tokens;

	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
		username blank: false, unique: true
		password blank: false
        email blank:true
        firstName blank:true
        lastName blank:true
        tokens blank:true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
