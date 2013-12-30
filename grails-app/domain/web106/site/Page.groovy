package web106.site

import web106.auth.WorkGroup


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
}
