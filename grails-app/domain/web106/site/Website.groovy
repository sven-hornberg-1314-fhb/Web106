package web106.site

import org.apache.commons.lang.builder.EqualsBuilder
import web106.auth.WorkGroup


/**
 * whole website
 * holds one or many pages and a workgroup
 */
class Website {

    String title
	String websiteurl

    List page

    static hasMany = [page : Page]
    static belongsTo = [workGroup: WorkGroup]


    static constraints = {
        title blank:false
        page nullable:true
		websiteurl nullable:true

    }
/*
    @Override
    String toString() {
        return 'Website:' + id + ', Title:' + title
    }

    @Override
    boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Website))
            return false;

        Website website = (Website) obj;
        return new EqualsBuilder().
                append(title, website.title).
                append(page, website.page).
                isEquals();
    }

    @Override
    public int hashCode() {
        super.hashCode()
    }
*/
}
