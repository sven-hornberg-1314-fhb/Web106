package web106.site.component

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.apache.commons.lang.builder.EqualsBuilder

/**
 * class for linking pages in a menu
 */
@ToString
@EqualsAndHashCode
class MenuComponent extends AbstractComponent{

    String menuName
}
