package web106.site

import org.apache.commons.lang.builder.EqualsBuilder

class Template {

    static constraints = {
    }
	
	String name

    /*
    @Override
    String toString() {
        return 'Template:' + name
    }

    @Override
    boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Template))
            return false;

        Template template = (Template) obj;
        return new EqualsBuilder().
                append(name, template.name).
                isEquals();
    }

    @Override
    public int hashCode() {
        super.hashCode()
    }
*/
}
