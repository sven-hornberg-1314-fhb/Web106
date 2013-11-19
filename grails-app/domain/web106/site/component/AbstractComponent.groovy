package web106.site.component

import web106.site.Box
/**
 * baseclass for all components 
*/ 
 

abstract class AbstractComponent {

    Box box
    String id



    static constraints = {
        box blank:true


    }
}
