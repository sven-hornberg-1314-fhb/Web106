<!DOCTYPE html>
<html>
<head>

    <title>Textbaustein</title>

<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css" />
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.3.0/grids-min.css" />

    <link rel="stylesheet" type="text/css" href="${resource( absolute: true, dir: 'css', file: 'web106.css')}" />
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet" />

<g:javascript library="jquery" />
<r:require module="jquery-ui"/>
<r:layoutResources />

<script type="text/javascript">
        $(function() {

        	
   
                
        });
</script>


        
</head>
<body>

	<div id="menu" class="pure-g-r">

		<div id="nav" class="pure-u-1-6">

			<g:render template="/shared/backendsidebar" />

		</div>
		<div id="main" class="pure-u-5-6">
	        <div class="contentPadding">

                <div class="pure-u-1-6">
                    <g:link class="pure-button" action="create">neuer Text</g:link>
                </div>
                <div class="pure-u-1-2">
                </div>

                <hr>
                <h3>Liste Ihrer Texte</h3>
			<g:include action="listown" />

		
		</div>
            </div>
	</div>
	<r:layoutResources />
</body>
</html>