<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Authentifizierungsfehler</title>

    <g:render template="/shared/header" />

    <r:layoutResources />
</head>

<body>

<div id="menu" class="pure-g">


    <div id="main" class="pure-u-1">
        <div class="contentPadding">
            <h3>Provider ist nicht erreichbar</h3>

            <p><i class="fa fa-exclamation-triangle"></i>&nbsp;Der gewählte Provider steht zur Zeit nicht zur Verfügung.</p>

            <a href="${createLink(uri: '/')}"><div class="pure-button">Startseite</div></a>
        </div>
    </div>
</div>
<r:layoutResources />
</body>
</html>