package web106.site

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.apache.commons.lang.builder.EqualsBuilder
import web106.auth.WorkGroup


/**
 * whole website
 * holds one or many pages and a workgroup
 */
@ToString
@EqualsAndHashCode
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
