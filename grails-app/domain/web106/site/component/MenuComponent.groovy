package web106.site.component

import org.apache.commons.lang.builder.EqualsBuilder

/**
 * class for linking pages in a menu
 */
class MenuComponent extends AbstractComponent{

    String menuName

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
        if (!(obj instanceof MenuComponent))
            return false;

        MenuComponent menuComponent = (MenuComponent) obj;
        return new EqualsBuilder().
                append(name, menuComponent.name).
                append(box, menuComponent.box).
                isEquals();
    }

    @Override
    public int hashCode() {
        super.hashCode()
    }
}
