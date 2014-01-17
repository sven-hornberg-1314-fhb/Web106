<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Zugriff AWS</title>

    <g:render template="/shared/header" />

    <r:layoutResources />
</head>

<body>

<div id="menu" class="pure-g">

    <div id="nav" class="pure-u-1-6">

        <g:render template="/shared/backendsidebar" />

    </div>
    <div id="main" class="pure-u-5-6">
        <div class="contentPadding">
            <h3>AWS Fehler</h3>

            <p><i class="fa fa-exclamation-triangle"></i>&nbsp; Bei der Verarbeitung ist ein Fehler aufgetreten.</p>

        </div>
    </div>
</div>
<r:layoutResources />
</body>
</html>