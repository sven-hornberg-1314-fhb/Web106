package web106.site

class Page {

	String title

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


    }
}
