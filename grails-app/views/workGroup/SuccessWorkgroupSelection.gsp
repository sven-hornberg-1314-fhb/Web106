<!DOCTYPE html>
<html>
<head>
    <title>Auswahl Arbeitsgruppe erfolgreich</title>

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet" />

    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/grids-min.css">
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

    	<h3>Workgroup ${name} erfolgreich ausgewählt:</h3>
        <g:link controller="workGroup">Zurück</g:link>
	    </div>
    </div>
</div>





<r:layoutResources />
</body>
</html>