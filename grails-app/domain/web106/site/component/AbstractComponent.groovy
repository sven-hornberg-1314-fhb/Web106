package web106.site.component

import web106.site.Box
/**
 * Created with IntelliJ IDEA.
 * User: marcman
 * Date: 05.11.13
 * Time: 14:34
 * To change this template use File | Settings | File Templates.
 */
abstract class AbstractComponent {

    Box box
    String id



    static constraints = {
        box blank:true


    }
}
