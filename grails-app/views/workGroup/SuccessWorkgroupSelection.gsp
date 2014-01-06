<!DOCTYPE html>
<html>
<head>
    <title>Auswahl Arbeitsgruppe erfolgreich</title>

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

    	<h3>Arbeitsgruppe &nbsp;  <i>${name}</i>  &nbsp;  erfolgreich ausgewählt:</h3>
        <g:link controller="workGroup">Zurück</g:link>
	    </div>
    </div>
</div>





<r:layoutResources />
</body>
</html>