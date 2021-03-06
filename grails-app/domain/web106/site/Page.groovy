package web106.site

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 * a single page for a website
 */
@ToString
@EqualsAndHashCode
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

}
