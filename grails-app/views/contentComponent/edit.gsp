<!DOCTYPE html>
<html>
<head>
<style type="text/css">
<!--

	.component {
		background-color: #c2c7c9;
		height: 50px;
		width: 95%;
		margin: 0 auto;
	}

-->
</style>
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.3.0/grids-min.css">
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
		<g:form name="editForm" action="edit2" >

            <div class="contentPadding">
			<br>
			<div id="menu1" class="pure-g-r">
				<div class="pure-u-1-8">
					Legen Sie Ihren Inhalt an:
				</div>
			</div>
			<div id="menu2" class="pure-g-r">
				<div class="pure-u-1-8">Name:</div>
				<div class="pure-u-1-8"><g:textField name="name" value="${name}" required="true" /></div>
			</div>
		
			<br>
			<div id="menu3" class="pure-g-r">
				<div class="pure-u-1-2">
				<g:textArea name="text" rows="5" cols="40" value="${text}" required="true" />
				</div>
			</div>
			<div id="menu4" class="pure-g-r">
				<div class="pure-u-1-2">
				<g:actionSubmit value="Bearbeiten" action="edit2" />
				</div>
			</div>

            <g:hiddenField name="hid" value="${id}" />
			
		</g:form>
            </div>
		</div>
	</div>
</body>
</html>