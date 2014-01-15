package web106.auth

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import web106.site.Website
import web106.site.component.AbstractComponent


/**
 * Group of Users, who work together on a same Page
 *
 */
@ToString
@EqualsAndHashCode
class WorkGroup {
	
	String name;
	
	static hasMany = [user:User, website:Website, abstractComponent: AbstractComponent]

    static constraints = {
    }
}
