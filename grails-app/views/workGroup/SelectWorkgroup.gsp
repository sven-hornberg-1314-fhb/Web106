<!DOCTYPE html>
<html>
<head>
    <title>Auswahl Arbeitsgruppe</title>
    <meta charset="utf-8">
    <g:render template="/shared/header" />

    <r:layoutResources />
</head>
<body>

<div id="menu" class="pure-g">
    
    <div id="nav" class="pure-u-1-6">
    
		<g:render template="/shared/backendsidebar" />
	
	</div>
	<div id="main" class="pure-u-5-6">

	    <div class="contentPadding">
		    <h3>Wähle eine Arbeitsgruppe:</h3>
		    <g:each in="${workgroup}">
		    <g:form name="${it.name}" action="selectWorkGroup">
		       <g:hiddenField name="workId" value= "${it.id}"/>
		        <button type="submit" class="pure-button"> Arbeitsgruppe: ${it.name}</button>
		    </g:form>
		    </g:each>


			<h3>eine neue Arbeitsgruppe anlegen:</h3>

            <g:if test="${flash.message}">
                <ul>
                    <li> ${flash.message}</li>
                </ul>
            </g:if>

			<g:form name="newWorkgroup" action="create">
			
				<label>Name der Arbeitsgruppe</label>
				<g:textField name="name" required="true" />
				<button type="submit" class="pure-button">Arbeitsgruppe anlegen</button>
			</g:form>
	    </div>
	</div>
	
</div>
<r:layoutResources />
</body>
</html>