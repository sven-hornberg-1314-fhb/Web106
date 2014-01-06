<%@page defaultCodec="none" %>
<!DOCTYPE html>
<html>
   <head>
	  <style type="text/css">
      p{
          text-align: center;
          color:white;
          font-size: 20pt;
          padding-top:20pt;
      }

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
</head>
<body>

		<g:if test="${centermessagebox==true}">
			<g:render template="/shared/centermessagebox" />
		</g:if>

		<div id="main" class="pure-u-1">
			
			
			
			<div class="pure-g-r">
				<div id="header" class="pure-u-1-3 lightskyblue dropbox">
					<p>${header} </p>
				</div>
				<div id="side1" class="pure-u-2-3 blue dropbox">
                    <p>${side1}  </p>
				</div>
			</div>
			<div class="pure-g-r">
				<div id="side2" class="pure-u-1-3 red dropbox">
                    <p>${side2}  </p>
				</div>
				<div id="content" class="pure-u-1-3 purple dropbox">
                    <p>${content} </p>
				</div>
				<div id="side3" class="pure-u-1-3 darkorange dropbox">
                    <p>${side3}  </p>
				</div>
			</div>

			<div class="pure-g-r">
				<div id="footer" class="pure-u-1 darkgreen dropbox">
                    <p>${footer} </p>
				</div>
			</div>	
		</div>

	<r:layoutResources />
</body>
</html>
	 
	 
	 
	 
	 
	