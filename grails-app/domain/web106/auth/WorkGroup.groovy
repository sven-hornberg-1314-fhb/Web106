package web106.auth

import web106.site.Website

/**
 * Group of Users, who work together on a same Page
 *
 */
class WorkGroup {
	
	String name;
	
	static hasMany = [user:User, website:Website]

    static constraints = {
    }
}
