 <!DOCTYPE html>
<html>
   <head>
      <title>Template Auswahl</title>
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
       <g:render template="/shared/header" />
       <r:layoutResources />
   </head>
<body>

<div id="menu" class="pure-g-r">
    
    <div id="nav" class="pure-u-1-6">
    
		<g:render template="/shared/backendsidebar" />
	
	</div>
	<div id="main" class="pure-u-5-6">
        <div class="contentPadding">

	<h3>Wähle ein Template:</h3>

	
	<g:each var="template" in="${templates}">
		<div class="btn btn-default"><a href="${createLink(action: template.name.toLowerCase())}">${template.name}</a></div>
	</g:each>
 	</div>
 	
      </div>
</div>
	<r:layoutResources />
</body>
</html>