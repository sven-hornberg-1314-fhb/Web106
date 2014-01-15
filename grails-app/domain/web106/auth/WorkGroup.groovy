package web106.auth

import org.apache.commons.lang.builder.EqualsBuilder
import web106.site.Website
import web106.site.component.AbstractComponent


/**
 * Group of Users, who work together on a same Page
 *
 */
class WorkGroup {
	
	String name;
	
	static hasMany = [user:User, website:Website, abstractComponent: AbstractComponent]

    static constraints = {
    }

    /*
    @Override
    String toString() {
        return 'Workgroup:' + id
    }

    @Override
    boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof WorkGroup))
            return false;

        WorkGroup workGroup = (WorkGroup) WorkGroup;
        return new EqualsBuilder().
                append(name, workGroup.name).
                append(user, workGroup.user).
                isEquals();
    }

    @Override
    public int hashCode() {
        super.hashCode()
    }
    */
}
