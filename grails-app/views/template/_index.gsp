 <!DOCTYPE html>
<html>
   <head>
      <r:require modules="bootstrap"/>
      <!-- <r:require modules="bootstrap, jquery, application, bootstrap-js"/> -->
	  <style type="text/css">
	  .color{
	  background-color: grey;
	  border-style:solid;
	  border-width:medium;
	  border-color:white;
	  }
	  
	  <!--
	  -->
	  </style>
	<r:layoutResources />
   </head>
<body>



<div class="container">
<h3>Wähle ein Template:</h3>

	
	<div class="btn btn-default"><a href="${createLink(action: 'berlin')}">Berlin</a></div>
	
	<div class="btn btn-default"><a href="${createLink(action: 'kairo')}">Kairo</a></div>

 
 	
      
</div>
	<r:layoutResources />
</body>
</html>