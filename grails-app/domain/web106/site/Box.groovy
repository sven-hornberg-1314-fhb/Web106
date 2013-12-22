package web106.site

import web106.site.component.AbstractComponent

/**
 * A Container for Components
 */
class Box {

    String idName
    List component
    static hasMany = [component:AbstractComponent]
}
