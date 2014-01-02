package web106.site

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
}
