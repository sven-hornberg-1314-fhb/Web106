package web106.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils

class User {

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

    public Collection<GrantedAuthority> getGrantedAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

        Set auth = UserRole.findAllByUser(this).collect { it.role.authority } as Set
        String ja = auth.join(",")

        def jpi = AuthorityUtils.commaSeparatedStringToAuthorityList(ja)
        def mail = AuthorityUtils.createAuthorityList('email:'+email)

        grantedAuthorities.addAll(jpi)
        grantedAuthorities.addAll(mail)

        return  grantedAuthorities
    }


}
