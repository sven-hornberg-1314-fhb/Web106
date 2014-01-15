package web106.site

import org.apache.commons.lang.builder.EqualsBuilder

/**
 * a single page for a website
 */
class Page {

	String title
	
	Template template

    static belongsTo = [website:Website]

    static hasMany=[boxes:Box]

    /**
     * site is rendered or not
     */
    boolean isPublic = false


    /**
     * Date for the first allowed view
     */
    Date visibleFrom

    /**
     * Date for the last allowed view
     */
    Date visibleTo


	
    static constraints = {
        title blank:false
        visibleFrom blank:true
        visibleTo blank:true
    }

    @Override
    String toString() {
        return 'Page:' + id + ', Title:' + title
    }

    @Override
    boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Page))
            return false;

        Page page = (Page) obj;
        return new EqualsBuilder().
                append(title, page.title).
                append(boxes, page.boxes).
                isEquals();
    }

    @Override
    public int hashCode() {
        super.hashCode()
    }

}
