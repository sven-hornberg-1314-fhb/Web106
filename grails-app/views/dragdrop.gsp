
<!DOCTYPE html>
<html>
<head>
<title>Simple GSP page</title>
<g:javascript library="jquery" />
<r:layoutResources />
<jqui:resources theme="darkness" />

<style type="text/css">
<!--
#layout {
	background-color: red;
	width: 200px;
	height: 200px;
}

#contentcomponent {
	background-color: green;
	width: 300px;
	height: 300px;
}
-->
</style>

<script type="text/javascript">
	$(function() {

		$("#layout").draggable();

		$("#contentcomponent").droppable(
			{
				drop : function(event, ui) {
					$(this).addClass("ui-state-highlight").html(
							"Dropped!");
				}
		});
	});
</script>

</head>
<body>
	<h3>DragDrop Beispiel</h3>

	<div id="layout"></div>
	<div id="components">
		<div id="contentcomponent"></div>
	</div>

</body>
</html>