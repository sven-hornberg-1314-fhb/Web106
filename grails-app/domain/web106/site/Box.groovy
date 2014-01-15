package web106.site

import org.apache.commons.lang.builder.EqualsBuilder
import web106.site.component.AbstractComponent

/**
 * A Container for Components
 */
class Box {

    String idName
    List component
    static hasMany = [component:AbstractComponent]

    /*
    @Override
    String toString() {
        return 'Box:' + idName
    }

    @Override
    boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Box))
            return false;

        Box box = (Box) obj;
        return new EqualsBuilder().
                append(idName, box.idName).
                append(component, box.component).
                isEquals();
    }

    @Override
    public int hashCode() {
        super.hashCode()
    }
    */
}
