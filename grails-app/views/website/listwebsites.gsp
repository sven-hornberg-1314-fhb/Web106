<!DOCTYPE html>
<html>
<head>
    <title>Webseiten Übersicht</title>
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
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css"/>
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/grids-min.css"/>
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet" />

    <r:layoutResources />
</head>
<body>

<div id="menu" class="pure-g">

    <div id="nav" class="pure-u-1-6">

        <g:render template="/shared/backendsidebar" />

    </div>
    <div id="main" class="pure-u-5-6"><table>
        <tr>
            <th>Name</th><th>Inhalt</th><th><!--Select --></th></th><th><!-- Edit --></th><th><!-- Delete --></th>
        </tr>
        <g:each var="website" in="${websites}">
        <tr>
            <td>${website.title}</td>
            <td><g:link action="selectWebsites" id="${website.id}" ><i class="fa fa-check"></i></g:link></td>

        </tr>
        </g:each>
        </table>
    </div>
</div>
</body>
</html>