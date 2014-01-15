package web106.auth

import org.apache.commons.lang.builder.EqualsBuilder

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

    /*
    @Override
    String toString() {
        return "Role:" + id
    }

    @Override
    boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Role))
            return false;

        Role role = (Role) obj;
        return new EqualsBuilder().
        append(authority, role.authority).
        isEquals();
    }

    @Override
    public int hashCode() {
        super.hashCode()
    }
    */
}
