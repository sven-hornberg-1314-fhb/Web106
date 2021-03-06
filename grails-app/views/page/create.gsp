<%@page import="web106.site.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>Seite erstellen</title>
    <g:render template="/shared/header" />

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
    <g:form action="create_step2">


        <div class="pure-g-r">
        	<div class="pure-u-1-8"></div>
            <div class="pure-u-1-2"><h3>Legen Sie Ihre Seite an</h3></div>
        </div>

        <div class="pure-g-r">
        	<div class="pure-u-1-8"></div>
            <div class="pure-u-1-8">Titel der Seite:</div>
            <div class="pure-u-1-4">
                <g:textField name="titel" placeholder="Titel" required="true"/>
            </div>
        </div>

        <br>
        <div class="pure-g-r">
           	<div class="pure-u-1-8"></div>
        
            <div class="pure-u-1-8">Seite ist aktiv online</div>
            <div class="pure-u-1-4">
                <g:checkBox name="online"/>
            </div>
        </div>

        <br>
        <div class="pure-g-r">
          	<div class="pure-u-1-8"></div>
        
            <div class="pure-u-1-8">Visable From:</div>
            <div class="pure-u-1-4">
                <g:datePicker precision="day" relativeYears="[0..2]"
                              name="startDate" />
            </div>
        
            <div class="pure-u-1-8">Visable To:</div>
            <div class="pure-u-1-4">
                <g:datePicker precision="day" relativeYears="[0..10]"
                              name="endDate" />
            </div>
        </div>
         <div class="pure-g-r">
         
           	<div class="pure-u-1-8"></div>
         
            <div class="pure-u-1-8">Für immer:</div>
            <div class="pure-u-1-8">
                <g:checkBox name="immer" />
            </div>
            <div class="pure-u-1-8">Template</div>
            <div class="pure-u-1-8">
                <g:select name="template" from="${templateNameList}"/>
            </div>
            </div>
        <br>
        <div class="pure-g-r">
         
           	<div class="pure-u-1-8"></div>
         
            <div class="pure-u-1-8">
        <g:submitButton value="Speichern" name="Speichern"/>
        	</div>
        </div>
        
        </g:form>
        
        <hr>
        <div class="pure-g-r">            
        	<div class="pure-u-1-8"></div>
        
            <div class="pure-u-1-3">
                <g:render template="/template/berlin/template" model="${modelberlin}" />
                

            </div>
            
            <div class="pure-u-1-8"></div>
             
            <div class="pure-u-1-3">
                <g:render template="/template/kairo/template" model="${modelkairo}" />
                
    
            </div>
            
            <div class="pure-u-1-8"></div>
            
        </div>

    </div>
</div>
<r:layoutResources />
</body>
</html>
