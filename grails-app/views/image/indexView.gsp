<g:javascript src="jquery-1.10.2.min.js" />
<g:javascript src="lightbox-2.6.min.js" />
<!DOCTYPE html>
<html>
	<head>
	<title>Image-Gallerie</title>
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'lightbox.css')}" type="text/css">	
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'ImageStylesheet.css')}" type="text/css">

        <g:render template="/shared/header" />
        <r:layoutResources />

    <style type="text/css">
        <!--
        .shrinkimg {
        width: inherit;  /* This makes the next two lines work in IE8. */
        max-width: 100%; /* Add !important if needed. */
        height: auto;    /* Add !important if needed. */
        }
        -->
    </style>
    </head>
	<body>
<div id="menu" class="pure-g">
    
    <div id="nav" class="pure-u-1-6">
    
		<g:render template="/shared/backendsidebar" />
	
	</div>

    <div id="main" class="pure-u-5-6">
        <div class="contentPadding">


        <h3>Bildergalerie</h3>

            <div class="pure-u-1" >
		        <g:each in="${list}" var="item">
                    <div class="pure-u-1-4" >
                    <a href="${item}" data-lightbox="roadtrip"><g:img class="shrinkimg" uri="${item}" /></a>
                    </div>
        		</g:each>
            </div>
        </div>
</div>
<r:layoutResources />
	</body>
</html>