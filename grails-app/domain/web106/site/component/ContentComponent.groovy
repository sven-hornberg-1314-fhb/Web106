package web106.site.component

/**
 * class for content, like in an editor
 */
class ContentComponent extends AbstractComponent{


	@Override
	public String renderHTML() {
		return '<div>' + text + '</div>'
	}
	
    String text;
}
