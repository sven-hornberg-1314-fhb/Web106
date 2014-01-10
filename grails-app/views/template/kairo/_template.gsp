<%@page defaultCodec="none" %>
<!DOCTYPE html>
<html>
   <head>
	  <style type="text/css">

      .lightskyblue{
          background-color: lightskyblue;
      }
      .purple{
          background-color: purple;
      }
      .blue{
          background-color: blue;
      }
      .red{
          background-color: red;
      }
      .darkgreen{
          background-color: darkgreen;
      }
      .darkorange{
          background-color: darkorange;
      }

      .centermessagebox {
          margin: 0 auto;
      }

      .dropbox {
          min-height: 10em;
      }

      <!--
	  -->
	 </style>
       <g:render template="/shared/header" />

<g:javascript library="jquery" />
<r:require module="jquery-ui"/>
<r:layoutResources />

<script type="text/javascript">
        $(function() {

        	
        	if(document.URL.indexOf("template") == -1) {
            	$('.centermessagebox').hide();
            } 

		

            /*$('#header').on(
                   'drop', function(event, ui){
                        var draggableId = ui.draggable.attr("id");
                        var droppableId = $(this).attr("id");
                        alert("draggableId: "+draggableId+" droppableId: "+droppableId)
                                <g:remoteFunction controller="page" action="remote" params="[id:'1', draggableId:'test']"/>
                            }
                    );  */



        });
</script>
${web106.header}
</head>
<body>

		<g:if test="${centermessagebox==true}">
			<g:render template="/shared/centermessagebox" />
		</g:if>

		<div id="main" class="pure-u-1">
			
			
			
			<div class="pure-g-r">
				<div id="header" class="pure-u-1-3 lightskyblue dropbox">
					${header}
				</div>
				<div id="side1" class="pure-u-2-3 blue dropbox">
                    ${side1}
				</div>
			</div>
			<div class="pure-g-r">
				<div id="side2" class="pure-u-1-3 red dropbox">
                    ${side2}
				</div>
				<div id="content" class="pure-u-1-3 purple dropbox">
                    ${content}
				</div>
				<div id="side3" class="pure-u-1-3 darkorange dropbox">
                    ${side3}
				</div>
			</div>

			<div class="pure-g-r">
				<div id="footer" class="pure-u-1 darkgreen dropbox">
                    ${footer}
				</div>
			</div>	
		</div>

	<r:layoutResources />
</body>
</html>
	 
	 
	 
	 
	 
	