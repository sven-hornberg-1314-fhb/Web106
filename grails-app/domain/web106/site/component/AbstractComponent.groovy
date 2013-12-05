package web106.site.component

import web106.site.Box
import web106.auth.WorkGroup

/**
 * baseclass for all components 
*/ 
 

class AbstractComponent {

    Box box
    String name

    static belongsTo = [workGroup: WorkGroup]
	

    static constraints = {
        box blank:true, nullable : true


    }
}
