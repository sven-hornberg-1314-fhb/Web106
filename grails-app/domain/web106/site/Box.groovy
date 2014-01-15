package web106.site

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import web106.site.component.AbstractComponent

/**
 * A Container for Components
 */
@ToString
@EqualsAndHashCode
class Box {

    String idName
    List component
    static hasMany = [component:AbstractComponent]

}
