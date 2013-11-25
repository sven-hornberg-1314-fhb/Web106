<%--
  Created by IntelliJ IDEA.
  User: marcman
  Date: 11.11.13
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@page import="org.scribe.model.OAuthRequest; org.scribe.model.Token" %>

<%@ page import="uk.co.desirableobjects.oauth.scribe.OauthService" contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title></title>
  <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
  <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/grids-min.css">
</head>
<body>

<sec:ifLoggedIn>
<div id="menu" class="pure-g">


    
    <div id="nav" class="pure-u-1-8">
    
		<g:render template="/shared/backendsidebar" />
	
	</div>

</sec:ifLoggedIn>

<sec:ifNotLoggedIn>
    <g:link controller="oauth" action="login"><button>Login</button></g:link>
    <g:link controller="oauth" action="register"><button>Register</button></g:link>
</sec:ifNotLoggedIn>


<sec:ifLoggedIn>
	<div id="main" class="pure-u-7-8">

    <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_USER">Hallo,</sec:ifAnyGranted>

    <sec:ifAllGranted roles="ROLE_ADMIN">
        admin: <sec:username /> <br/>
        <g:link controller="administration" action="index">Controller Overview</g:link><br/>
        <g:link controller="administration" action="listUsers">Users</g:link><br/>
        <g:link controller="administration" action="listRoles">Roles</g:link><br/>
        <g:link controller="administration" action="listUserRoles">UserRoles</g:link><br/>
    </sec:ifAllGranted>

    <sec:ifAnyGranted roles="ROLE_USER, ROLE_SUPERUSER">
        user: <sec:username />
    </sec:ifAnyGranted>

    <sec:ifAllGranted roles="ROLE_ADMIN, ROLE_USER">
        ADMIN & USER
    </sec:ifAllGranted>

</div>
</div>
</sec:ifLoggedIn>
<hr/>

${session}  <br/>

<hr/>

<g:link controller="logout">sign out</g:link>

</body>
</html>