<%@ page import="web106.site.Page" %>



<div class="fieldcontain ${hasErrors(bean: pageInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="page.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${pageInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pageInstance, field: 'visibleFrom', 'error')} required">
	<label for="visibleFrom">
		<g:message code="page.visibleFrom.label" default="Visible From" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="visibleFrom" precision="day"  value="${pageInstance?.visibleFrom}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: pageInstance, field: 'visibleTo', 'error')} required">
	<label for="visibleTo">
		<g:message code="page.visibleTo.label" default="Visible To" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="visibleTo" precision="day"  value="${pageInstance?.visibleTo}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: pageInstance, field: 'boxes', 'error')} ">
	<label for="boxes">
		<g:message code="page.boxes.label" default="Boxes" />
		
	</label>
	<g:select name="boxes" from="${web106.site.Box.list()}" multiple="multiple" optionKey="id" size="5" value="${pageInstance?.boxes*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: pageInstance, field: 'isPublic', 'error')} ">
	<label for="isPublic">
		<g:message code="page.isPublic.label" default="Is Public" />
		
	</label>
	<g:checkBox name="isPublic" value="${pageInstance?.isPublic}" />
</div>

<div class="fieldcontain ${hasErrors(bean: pageInstance, field: 'workGroup', 'error')} required">
	<label for="workGroup">
		<g:message code="page.workGroup.label" default="Work Group" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="workGroup" name="workGroup.id" from="${web106.auth.WorkGroup.list()}" optionKey="id" required="" value="${pageInstance?.workGroup?.id}" class="many-to-one"/>
</div>

