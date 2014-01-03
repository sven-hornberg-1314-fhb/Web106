<!DOCTYPE html>
<html>
<head>
    <title>Seite</title>

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

            <div class="pure-u-1">
                <div class="pure-u-1-6">
                    <g:link class="pure-button" action="create">neue Seite</g:link>
                </div>
                <div class="pure-u-1-2">
                </div>

            </div>

            <hr>
            <h3>Liste Ihrer Seiten</h3>
            <g:include action="listown" />





		</div>
        </div>
</div>
<r:layoutResources />
</body>
</html>