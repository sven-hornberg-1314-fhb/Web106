package web106.site

import web106.auth.WorkGroup

class Page {

	String title

    static hasMany=[boxes:Box]
	static belongsTo = [workGroup: WorkGroup]

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
    }
}
