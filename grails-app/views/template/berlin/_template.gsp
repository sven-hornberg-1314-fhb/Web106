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
	  <!--
	  -->
	 </style>
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
<link rel="stylesheet"
	href="http://yui.yahooapis.com/pure/0.3.0/grids-min.css">
<g:javascript library="jquery" />
<r:require module="jquery-ui"/>
<r:layoutResources />

<script type="text/javascript">
        $(function() {

			$('#centermessagebox').fadeIn();
                
        });
</script>
</head>
<body>

		<g:render template="/shared/centermessagebox" />


		<div id="main" class="pure-u-1">
			
			
			
			<div class="pure-g-r">
				<div class="pure-u-1 grey">
					<h3>Welcome to ${stadt}</h3>
				</div>
			</div>
			<div class="pure-g-r">
				<div class="pure-u-1-3 blue">
					Side
				</div>
				<div class="pure-u-1-3 grey">
					Content
				</div>
				<div class="pure-u-1-3 red">
					Side
				</div>
			</div>

			<div class="pure-g-r">
				<div class="pure-u-1-2 green">
					<h3>Footer</h3>
				</div>
				<div class="pure-u-1-2 green">
					<h3>Footer2</h3>
				</div>
			</div>	
		</div>
	<r:layoutResources />
</body>
</html>
	 
	 
	 
	 
	 
	