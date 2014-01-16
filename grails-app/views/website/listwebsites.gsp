<!DOCTYPE html>
<html>
<head>
    <title>Webseiten Übersicht</title>

    <g:render template="/shared/header" />
    <r:layoutResources />
</head>
<body>

<div id="menu" class="pure-g">

    <div id="nav" class="pure-u-1-6">

        <g:render template="/shared/backendsidebar" />

    </div>
    <div id="main" class="pure-u-5-6">

        <table>
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