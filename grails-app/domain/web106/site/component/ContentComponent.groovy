package web106.site.component

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.apache.commons.lang.builder.EqualsBuilder

/**
 * class for content, like in an editor
 */
@ToString
@EqualsAndHashCode
class ContentComponent extends AbstractComponent{


	@Override
	public String renderHTML() {
		return '<div>' + text + '</div>'
	}
	
    String text;

}
