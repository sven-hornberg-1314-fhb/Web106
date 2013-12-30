<!DOCTYPE html>
<html>
<head>
    <title>Auswahl Arbeitsgruppe</title>
    <meta charset="utf-8">

    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${resource( absolute: true, dir: 'css', file: 'web106.css')}" />
    <link rel="stylesheet" type="text/css" href="${resource( absolute: true, dir: 'css/shared', file: 'shared.css')}" />
    <r:layoutResources />
</head>
<body>

<div id="menu" class="pure-g">
    
    <div id="nav" class="pure-u-1-6">
    
		<g:render template="/shared/backendsidebar" />
	
	</div>
	<div id="main" class="pure-u-5-6">
	    <div class="contentPadding">
		    <h3>Wähle eine Workgroup:</h3>
		    <g:each in="${workgroup}">
		    <g:form name="${it.name}" action="selectWorkGroup">
		       <g:hiddenField name="workId" value= "${it.id}"/>
		        <button type="submit" class="pure-button"> Workgroup: ${it.name}</button>
		    </g:form>
		    </g:each>
	
			<h3>eine neue Workgroup anlegen:</h3>
			<g:form name="newWorkgroup" action="create">
			
				<label>Name der Workgroup</label>
				<g:textField name="name" required="true" />
				<button type="submit" class="pure-button">Workgroup anlegen</button>
			</g:form>
	    </div>
	</div>
	
</div>
<r:layoutResources />
</body>
</html>