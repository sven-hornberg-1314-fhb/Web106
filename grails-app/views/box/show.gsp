
<%@ page import="web106.site.Box" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'box.label', default: 'Box')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-box" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-box" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list box">
			
				<g:if test="${boxInstance?.component}">
				<li class="fieldcontain">
					<span id="component-label" class="property-label"><g:message code="box.component.label" default="Component" /></span>
					
						<g:each in="${boxInstance.component}" var="c">
						<span class="property-value" aria-labelledby="component-label"><g:link controller="abstractComponent" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${boxInstance?.idName}">
				<li class="fieldcontain">
					<span id="idName-label" class="property-label"><g:message code="box.idName.label" default="Id Name" /></span>
					
						<span class="property-value" aria-labelledby="idName-label"><g:fieldValue bean="${boxInstance}" field="idName"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:boxInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${boxInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
