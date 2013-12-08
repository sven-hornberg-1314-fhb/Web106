 <!DOCTYPE html>
<html>
   <head>
	  <style type="text/css">
	  .grey{
	  background-color: grey;
	  }
	  .blue{
	  background-color: blue;
	  }	
	  .red{
	  background-color: red;
	  }  
	  .green{
	  background-color: green;
	  }
	  
	  .centermessagebox {
	  		    margin: 0 auto;
	  }
	  
	  <!--
	  -->
	 </style>
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css" />
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.3.0/grids-min.css" />
<g:javascript library="jquery" />
<r:require module="jquery-ui"/>
<r:layoutResources />

<script type="text/javascript">
        $(function() {

        	
        	if(document.URL.indexOf("template") == -1) {
            	$('.centermessagebox').hide();
            } 


            $('#header').on(
                    'drop', function(event, ui){
                        var draggableId = ui.draggable.attr("id");
                        var droppableId = $(this).attr("id");
                        alert("draggableId: "+draggableId+" droppableId: "+droppableId)

                        jQuery.ajax({
                            url: "${createLink(controller: 'page', action: 'remote')}",
                            data: {draggableId:draggableId, droppableId:droppableId},
                            success: function (data) {
                                alert(data);
                            }
                        });

                    }
            );
        });
</script>
</head>
<body>

		<g:render template="/shared/centermessagebox" />


		<div id="main" class="pure-u-1">
			
			
			
			<div class="pure-g-r">
				<div id="header" class="pure-u-1 grey dropbox">
					<h3>Welcome to ${stadt}</h3>
				</div>
			</div>
			<div class="pure-g-r">
				<div id="sidebar1" class="pure-u-1-3 blue dropbox">
					Side
				</div>
				<div id="content" class="pure-u-1-3 grey dropbox">
					Content
				</div>
				<div id="sidebar2" class="pure-u-1-3 red dropbox">
					Side
				</div>
			</div>

			<div class="pure-g-r">
				<div id="footer" class="pure-u-1-2 green dropbox">
					<h3>Footer</h3>
				</div>
				<div id="footer2" class="pure-u-1-2 green dropbox">
					<h3>Footer2</h3>
				</div>
			</div>	
		</div>
	<r:layoutResources />
</body>
</html>
	 
	 
	 
	 
	 
	