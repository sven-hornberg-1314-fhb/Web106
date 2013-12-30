<g:javascript src="jquery-1.10.2.min.js" />
<g:javascript src="lightbox-2.6.min.js" />
<!DOCTYPE html>
<html>
	<head>
	<title>Image-Gallerie</title>
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'lightbox.css')}" type="text/css">	
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'ImageStylesheet.css')}" type="text/css">

    <link rel="stylesheet"
          href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css" />

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
	<h1>Images</h1>

		<g:each in="${list}" var="item">
		
		<div class="container">
		<p> 
		<a href="${item}" data-lightbox="roadtrip"><g:img uri="${item}" /></a>
		</p>
		</div>
		
		</g:each>
</div>
<r:layoutResources />
	</body>
</html>