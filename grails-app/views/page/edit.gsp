<!DOCTYPE html>
<html>
<head>
<title>${title}</title>

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

                            //$(this).addClass("ui-state-highlight");
                            //remotefuntion eine method im controller aufgerufen werden und eine id gesendet werden

                            var draggableId = ui.draggable.attr("id");
                            var droppableId = $(this).attr("id");
                            alert("draggableId: "+draggableId+" droppableId: "+droppableId)

                            var box = new Object();
                            box.draggableId = draggableId;
                            box.droppableId = droppableId;
                            contentToBox(box);
                        }





                    });
                
        });

        function contentToBox(values) {
            <g:remoteFunction action="remote" params="values" ></g:remoteFunction>
        }
</script>


        
</head>
<body>
<div class="pure-g-r">
                <div class="pure-u-1">
                    <div class="pure-g-r">
                    <div class="pure-u-1-4">Menü Platzhalter</div>
                        <div class="pure-u-1-12"><g:link uri="/">Start</g:link></div>
                    <div class="pure-u-1-12"><g:link controller="page" action="preview" id="${id}" target="_blank">Vorschau</g:link></div>
                    </div>
                </div>

				<div class="pure-u-3-4">
				
						<g:render template="/template/${template}/template" />
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
						

	<r:layoutResources />
</body>
</html>