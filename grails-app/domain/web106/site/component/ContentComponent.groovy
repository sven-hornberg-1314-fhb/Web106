package web106.site.component

import org.apache.commons.lang.builder.EqualsBuilder

/**
 * class for content, like in an editor
 */
class ContentComponent extends AbstractComponent{


	@Override
	public String renderHTML() {
		return '<div>' + text + '</div>'
	}
	
    String text;

    /*
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
        if (!(obj instanceof ContentComponent))
            return false;

        ContentComponent contentComponent = (ContentComponent) obj;
        return new EqualsBuilder().
                append(name, contentComponent.name).
                append(box, contentComponent.box).
                isEquals();
    }

    @Override
    public int hashCode() {
        super.hashCode()
    }
    */
}
