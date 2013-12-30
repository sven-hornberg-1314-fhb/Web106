<!DOCTYPE html>
<html>
<head>
<title>Seite berabeiten: ${title}</title>

<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.3.0/grids-min.css">

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet" />
	
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

                            var box = new Object();
                            box.dragId = draggableId;
                            box.dropId = droppableId;
                            contentToBox(box);
                        }





                    });
                
        });

        function contentToBox(values) {
            <g:remoteFunction action="remoteDrop" params="values" asynchronous="true" ></g:remoteFunction>
        }

        function save(id) {
            var values = new Object()
            values.id = id;
            console.log(values)
            <g:remoteFunction action="remoteSave" params="values" asynchronous="true" ></g:remoteFunction>
        }
</script>


        
</head>
<body>
<div class="pure-g-r">
                <div class="pure-u-1">
                    <div class="pure-g-r">
                       <div class="pure-u-1-12"><g:link class="pure-button" uri="/">Start</g:link></div>
                       <div class="pure-u-1-6"><g:link class="pure-button" controller="page" action="preview" id="${id}" target="_blank">
                           <i class="fa fa-eye fa-3 "></i> Vorschau</g:link>
                       </div>
                       <div class="pure-u-1-12"><a class="pure-button" href="JavaScript:save(${id})" >
                           <i class="fa fa-floppy-o  fa-3"></i> Speichern</a></div>
                    </div>
                </div>

				<div class="pure-u-3-4">
				
						<g:render template="/template/${template}/template" />
				</div>
				<div class="pure-u-1-4">


					<div><b>Bausteine</b></div><br>
			
					<g:each var="con" in="${contents}">	
					
						<div id="${con.id}" class="component">
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