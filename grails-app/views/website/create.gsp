<%@page import="web106.site.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>Webseite erstellen</title>
    
    <link rel="stylesheet"
          href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
    <link rel="stylesheet"
          href="http://yui.yahooapis.com/pure/0.3.0/grids-min.css">

    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">

    <g:javascript library="jquery" />
    <r:require module="jquery-ui"/>
    <r:layoutResources />

    <script type="text/javascript">
        $(function() {

        });
    </script>



</head>
<body>


<div id="menu" class="pure-g-r">

    <div id="nav" class="pure-u-1-6">

        <g:render template="/shared/backendsidebar" />

    </div>
    <div id="main" class="pure-u-5-6">
    <g:form action="create2">


        <div class="pure-g-r">
        	<div class="pure-u-1-8"></div>
            <div class="pure-u-1-2"><h3>Legen Sie Ihre Webseite an</h3></div>
        </div>

        <div class="pure-g-r">
        	<div class="pure-u-1-8"></div>
            <div class="pure-u-1-8">Titel der Webseite:</div>
            <div class="pure-u-1-4">
                <g:textField name="title" placeholder="Titel" required="true"/>
            </div>
        </div>



        <div class="pure-g-r">
         
           	<div class="pure-u-1-8"></div>
         
            <div class="pure-u-1-8">
            <g:submitButton value="Speichern" name="Speichern"/>
        	</div>
        </div>
        
       </g:form>
        


    </div>
</div>
<r:layoutResources />
</body>
</html>
