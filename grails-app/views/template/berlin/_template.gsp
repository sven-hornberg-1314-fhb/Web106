<%@page defaultCodec="none" %>
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

      .dropbox {
          min-height: 10em;
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

        });
</script>
</head>
<body>

		<g:if test="${centermessagebox==true}">
			<g:render template="/shared/centermessagebox" />
		</g:if>

		<div id="main" class="pure-u-1">
			
			<div class="pure-g-r">
				<div id="header" class="pure-u-1 grey dropbox">
					${header}
				</div>
			</div>
			<div class="pure-g-r">
				<div id="sidebar1" class="pure-u-1-3 blue dropbox">
					${sidebar1}
				</div>
				<div id="content" class="pure-u-1-3 grey dropbox">
					${content}
				</div>
				<div id="sidebar2" class="pure-u-1-3 red dropbox">
				 	${sidebar2}
				</div>
			</div>

			<div class="pure-g-r">
				<div id="footer" class="pure-u-1-2 green dropbox">
				 	${footer}
				</div>
				<div id="footer2" class="pure-u-1-2 green dropbox">
				 	${footer2}
				</div>
			</div>	
		</div>
	<r:layoutResources />
</body>
</html>
	 
	 
	 
	 
	 
	