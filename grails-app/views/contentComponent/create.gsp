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
	
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
	
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

		<div id="nav" class="pure-u-1-8">

			<g:render template="/shared/backendsidebar" />

		</div>
		<div id="main" class="pure-u-7-8">
		<g:form name="createComponentForm" action="createComponent" >
	
			<br>
			<div id="menu" class="pure-g-r">
				<div class="pure-u-1-8">
					Legen Sie Ihren Inhalt an:
				</div>
			</div>
			<div id="menu" class="pure-g-r">
				<div class="pure-u-1-8">Name:</div>
				<div class="pure-u-1-8"><g:textField name="name" required="true" /></div>
			</div>
		
			<br>
			<div id="menu" class="pure-g-r">
				<div class="pure-u-1-2">
				<g:textArea name="text" rows="5" cols="40" required="true"></g:textArea>
				</div>
			</div>
			<div id="menu" class="pure-g-r">
				<div class="pure-u-1-2">
				<g:actionSubmit value="Anlegen" action="createComponent" />
				</div>
			</div>
			
		</g:form>
		</div>
	</div>
</body>
</html>