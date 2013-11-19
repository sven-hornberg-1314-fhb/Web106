package web106.auth

import web106.auth.User

/**
 * Group of Users, who work together on a same Page
 *
 */
class WorkGroup {
	
	String name;
	
	static hasMany = [user:User]

    static constraints = {
    }
}
