<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
    .color{
        background-color: grey;
        border-style:solid;
        border-width:medium;
        border-color:white;
    }

    <!--
    -->
    </style>
    <link rel="stylesheet"
          href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css" />
    <link rel="stylesheet"
          href="http://yui.yahooapis.com/pure/0.3.0/grids-min.css" />

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="${resource( absolute: true, dir: 'css', file: 'web106.css')}" />
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet" />

    <r:layoutResources />
</head>
<body>

<div id="menu" class="pure-g">
    
    <div id="nav" class="pure-u-1-6">
    
		<g:render template="/shared/backendsidebar" />
	
	</div>
	<div id="main" class="pure-u-5-6">
        <div class="contentPadding">
        <h3>vorhandene Webseiten</h3>
       <g:include action="listown" />
        <hr>

        <h3>Sie arbeiten aktuell unter folgener Website</h3>
        <g:include action="activeWebsite" />
        <hr>
	
		<h3>Seitenfunktionen</h3>
		<div class="container">
			<g:link action="create">neue Seite anlegen</g:link>
		</div>
		</div>
        </div>
</div>
<r:layoutResources />
</body>
</html>