package web106.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils

/**
 * User domain object
 * used for login and registration
 * is mapped to a role for specific access of contents
 */
class User {

	String username
	String password
	boolean enabled

    String email
    String firstName
    String lastName

    //e.g. tokens<Facebook,FBToken>, many tokens for later usage
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

    //only required for usual springsecurity username and password usage
	static mapping = {
		password column: '`password`'
	}

    /**
     * gets authorities of current user object
     * and maps them into grantedAuthorities
     * for use in oauth context without password
     * @return
     */
    public Collection<GrantedAuthority> getGrantedAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

        Set auth = UserRole.findAllByUser(this).collect { it.role.authority } as Set
        String sepUserRoles = auth.join(",")

        def commaSeparated = AuthorityUtils.commaSeparatedStringToAuthorityList(sepUserRoles)
        def mail = AuthorityUtils.createAuthorityList('email:'+email)

        grantedAuthorities.addAll(commaSeparated)
        grantedAuthorities.addAll(mail)

        return  grantedAuthorities
    }

}
