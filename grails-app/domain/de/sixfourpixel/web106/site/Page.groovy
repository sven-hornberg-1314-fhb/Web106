package de.sixfourpixel.web106.site

class Page {

	String title

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
