<!DOCTYPE html>
<html>
<head>
<title>Seite berabeiten: ${title}</title>
    <g:render template="/shared/header" />

<g:javascript library="jquery" />
<r:require module="jquery-ui"/>
<r:layoutResources />
    <link rel="stylesheet" type="text/css" href="${resource( absolute: true, dir: 'css', file: 'web106.css')}" />
<script type="text/javascript">
        $(function() {

        	

            $('.component').draggable({
                revert: true,
                snap: ".dropbox",
                start: function() {
                    //this.style.opacity = '0.4';
                },
                drag: function() {
                    //
                },
                stop: function() {
                    //this.style.opacity = '1';
                }


            });

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

                            $(this).appendTo($('#'+draggableId).html())

                        }





                    });
                
        });

        function reload(){
            $.ajax({
                url: "",
                context: document.body,
                success: function(s){
                    $(this).html(s);
                }
            });
        }

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
<body onload="JavaScript:reload();">
<div class="pure-g-r">
                <div class="pure-u-1">
                    <div class="pure-g-r">
                       <div class="pure-u-1-8"><g:link class="pure-button" uri="/">Start</g:link></div>
                       <div class="pure-u-1-6"><g:link class="pure-button" controller="page" action="preview" id="${id}" target="_blank">
                           <i class="fa fa-eye "></i> Vorschau</g:link>
                       </div>
                       <div class="pure-u-1-6"><a class="pure-button" href="JavaScript:save(${id});" >
                           <i class="fa fa-floppy-o "></i> Speichern</a></div>

                        <div class="pure-u-1-12">

                            <a class="pure-button" href="JavaScript:reload();" >
                                <i class="fa fa-floppy-o "></i> Neu Anordnen</a>

                        </div>
                    </div>
                </div>

				<div class="pure-u-3-4">
				
						<g:render template="/template/${template}/template" />
				</div>
				<div class="pure-u-1-4">

                    <div class="contentPadding">

                        <div><b>Bausteine</b></div><br>

                        <g:each var="con" in="${contents}">

                            <div id="${con.id}" class="component" draggable="true">
                                ${con.name} <br/>

                                <g:link controller="ContentComponent">
                                <i title="Komponente bearbeiten" class="fa fa-cogs"></i></g:link>
                                <i title="${con.text}" class="fa fa-info-circle"></i>

                            </div>
                        </g:each>

                    </div>
				</div>
			</div>


	<r:layoutResources />
</body>
</html>
