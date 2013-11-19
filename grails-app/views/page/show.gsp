
<%@ page import="web106.site.Page" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'page.label', default: 'Page')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-page" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-page" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list page">
			
				<g:if test="${pageInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="page.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${pageInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${pageInstance?.visibleFrom}">
				<li class="fieldcontain">
					<span id="visibleFrom-label" class="property-label"><g:message code="page.visibleFrom.label" default="Visible From" /></span>
					
						<span class="property-value" aria-labelledby="visibleFrom-label"><g:formatDate date="${pageInstance?.visibleFrom}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${pageInstance?.visibleTo}">
				<li class="fieldcontain">
					<span id="visibleTo-label" class="property-label"><g:message code="page.visibleTo.label" default="Visible To" /></span>
					
						<span class="property-value" aria-labelledby="visibleTo-label"><g:formatDate date="${pageInstance?.visibleTo}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${pageInstance?.boxes}">
				<li class="fieldcontain">
					<span id="boxes-label" class="property-label"><g:message code="page.boxes.label" default="Boxes" /></span>
					
						<g:each in="${pageInstance.boxes}" var="b">
						<span class="property-value" aria-labelledby="boxes-label"><g:link controller="box" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${pageInstance?.isPublic}">
				<li class="fieldcontain">
					<span id="isPublic-label" class="property-label"><g:message code="page.isPublic.label" default="Is Public" /></span>
					
						<span class="property-value" aria-labelledby="isPublic-label"><g:formatBoolean boolean="${pageInstance?.isPublic}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${pageInstance?.workGroup}">
				<li class="fieldcontain">
					<span id="workGroup-label" class="property-label"><g:message code="page.workGroup.label" default="Work Group" /></span>
					
						<span class="property-value" aria-labelledby="workGroup-label"><g:link controller="workGroup" action="show" id="${pageInstance?.workGroup?.id}">${pageInstance?.workGroup?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:pageInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${pageInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
