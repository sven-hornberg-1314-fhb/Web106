package web106.site.component

import org.apache.commons.lang.builder.EqualsBuilder
import web106.site.Box
import web106.auth.WorkGroup

/**
 * baseClass for all components
*/ 
 

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


    @Override
    String toString() {
        return 'AbstractComponent:' + id + ', Name:' + name
    }

    @Override
    boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof AbstractComponent))
            return false;

        AbstractComponent abstractComponent = (AbstractComponent) obj;
        return new EqualsBuilder().
                append(name, abstractComponent.name).
                append(box, abstractComponent.box).
                isEquals();
    }

    @Override
    public int hashCode() {
        super.hashCode()
    }

}
