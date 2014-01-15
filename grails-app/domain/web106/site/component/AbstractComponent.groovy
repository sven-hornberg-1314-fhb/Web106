package web106.site.component

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import web106.site.Box
import web106.auth.WorkGroup

/**
 * baseClass for all components
*/

@ToString
@EqualsAndHashCode
class AbstractComponent {

    Box box
    String name

    static belongsTo = [workGroup: WorkGroup]
	
	/**
	 * Render HTML
	 * @return html
	 */
	String renderHTML() {
        new Exception("override RenderHTML")
	}

    static constraints = {
        box blank:true, nullable : true


    }
}
