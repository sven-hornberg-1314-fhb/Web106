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
    <h3>Wähle eine Workgroup:</h3>


    <g:each in="${workgroup}">
    <g:form name="${it.name}" action="selectWorkGroup">
       <g:hiddenField name="workId" value= "${it.id}"/>
        <input type="submit" class="btn btn-default"> Workgroup: ${it.name}</input>
    </g:form>
    </g:each>





</div>
<r:layoutResources />
</body>
</html>