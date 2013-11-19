<%@ page import="web106.site.Box" %>



<div class="fieldcontain ${hasErrors(bean: boxInstance, field: 'component', 'error')} ">
	<label for="component">
		<g:message code="box.component.label" default="Component" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${boxInstance?.component?}" var="c">
    <li><g:link controller="abstractComponent" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="abstractComponent" action="create" params="['box.id': boxInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'abstractComponent.label', default: 'AbstractComponent')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: boxInstance, field: 'idName', 'error')} ">
	<label for="idName">
		<g:message code="box.idName.label" default="Id Name" />
		
	</label>
	<g:textField name="idName" value="${boxInstance?.idName}"/>
</div>

