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

<div class="row">
    <h3>Wähle eine Workgroup:</h3>
    <g:each in="${workgroup}">
    <g:form name="${it.name}" action="selectWorkGroup">
       <g:hiddenField name="workId" value= "${it.id}"/>
        <button type="submit" class="btn btn-default"> Workgroup: ${it.name}</button>
    </g:form>
    </g:each>
</div>

<div class="row">
	<h3>eine neue Workgroup anlegen:</h3>
	<g:form name="newWorkgroup" action="create">
	
		<label>Name der Workgroup</label>
		<g:textField name="name" required="true" />
		<button type="submit" class="btn btn-default">Workgroup anlegen</button>
	</g:form>
</div>




</div>
<r:layoutResources />
</body>
</html>