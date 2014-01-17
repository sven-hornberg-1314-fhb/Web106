<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Fehler</title>

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
            <h3>Es ist ein Fehler aufgetreten.</h3>

            <p><i class="fa fa-exclamation-triangle"></i>&nbsp; Beschreiben Sie Ihre Aktion und melden Sie diesen Fehler.</p>

        </div>
    </div>
</div>
<r:layoutResources />
</body>
</html>