package web106.auth

import org.springframework.security.core.GrantedAuthority

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled

    String email
    String firstName
    String lastName

    //e.g. tokens<Facebook,FBToken>
    def tokens = [:]

	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
		username blank: false, unique: true
		password blank: true, nullable: true
        email blank:false ,email:true, unique:true
        firstName blank:false, nullable: true
        lastName blank:false, nullable: true
        tokens  blank:true
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Collection<GrantedAuthority>
	}

    public Collection<GrantedAuthority> getGrantedAuthorities() {
        //make everyone ROLE_USER
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            //anonymous inner type
            public String getAuthority() {
                return "ROLE_USER";
            }
        };

        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
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
