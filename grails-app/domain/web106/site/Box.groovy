package web106.site

import web106.site.component.AbstractComponent

/**
 * Created with IntelliJ IDEA.
 * User: marcman
 * Date: 05.11.13
 * Time: 14:40
 * To change this template use File | Settings | File Templates.
 */
class Box {

    String id
    static hasOne = [component:AbstractComponent]
}
