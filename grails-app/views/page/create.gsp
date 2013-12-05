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

        	
                $('.component').draggable({ revert: true });

                $(".dropbox").droppable(
                        {
                                drop : function(event, ui) {
                                        $(this).addClass("ui-state-highlight");
                                }
                });
                
        });
</script>


        
</head>
<body>

	<div id="menu" class="pure-g-r">

		<div id="nav" class="pure-u-1-8">

			<g:render template="/shared/backendsidebar" />

		</div>
		<div id="main" class="pure-u-7-8">


			<h3>Legen Sie Ihre Seite an</h3>

			<div class="pure-g-r">
				<div class="pure-u-1-8">Titel der Seite:</div>
				<div class="pure-u-1-4">
					<input type="text" placeholder="Titel">
				</div>
			</div>

			<br>
			<div class="pure-g-r">
				<div class="pure-u-1-8">Seite ist aktiv online</div>
				<div class="pure-u-1-4">
					<g:checkBox name="online"/>
				</div>
			</div>

			<br>
			<div class="pure-g-r">
				<div class="pure-u-1-8">Visable From:</div>
				<div class="pure-u-1-4">
					<g:datePicker precision="day" relativeYears="[0..2]"
						name="startDate" />
				</div>
				<div class="pure-u-1-8">Visable To:</div>
				<div class="pure-u-1-4">
					<g:datePicker precision="day" relativeYears="[0..10]"
						name="startDate" />
				</div>
				<div class="pure-u-1-8">Für immer:</div>
				<div class="pure-u-1-8">
					<g:checkBox name="immer" />
				</div>
			</div>
			<br>
			<hr>
			<div class="pure-g-r">
				<div class="pure-u-3-4">
				
						<g:render template="/template/berlin/template" />
				</div>
				<div class="pure-u-1-4">
					<div><b>Bausteine</b></div><br>
			
					<g:each var="con" in="${contents}">	
					
						<div id="dragtest" class="component">
							${con.name}<br>
							<g:link controller="ContentComponent">
							<i class="fa fa-cogs"></i>Komponente bearbeiten</g:link>
						</div>
					</g:each>
				</div>
			</div>

			
		</div>
	</div>
	<r:layoutResources />
</body>
</html>