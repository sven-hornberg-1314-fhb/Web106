<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <style type="text/css">
	    
	    .color {
	        background-color: grey;
	        border-style:solid;
	        border-width:medium;
	        border-color:white;
	    }
	    
	    
    </style>
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/grids-min.css">
    <r:layoutResources />
</head>
<body>

<div id="menu" class="pure-g">
    
    <div id="nav" class="pure-u-1-8">
    
		<g:render template="/shared/backendsidebar" />
	
	</div>
	<div id="main" class="pure-u-7-8">
	
		    <h3>Wähle eine Workgroup:</h3>
		    <g:each in="${workgroup}">
		    <g:form name="${it.name}" action="selectWorkGroup">
		       <g:hiddenField name="workId" value= "${it.id}"/>
		        <button type="submit" class="btn btn-default"> Workgroup: ${it.name}</button>
		    </g:form>
		    </g:each>
	
			<h3>eine neue Workgroup anlegen:</h3>
			<g:form name="newWorkgroup" action="create">
			
				<label>Name der Workgroup</label>
				<g:textField name="name" required="true" />
				<button type="submit" class="btn btn-default">Workgroup anlegen</button>
			</g:form>
	
	</div>
	
</div>
<r:layoutResources />
</body>
</html>