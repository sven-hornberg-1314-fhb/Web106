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
    <style>


    </style>
</head>
<body>

<sec:ifNotLoggedIn>
    <g:link controller="login" action="auth">Login</g:link>
    <g:link controller="user" action="index">Register</g:link>
</sec:ifNotLoggedIn>


<sec:ifLoggedIn>

    <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_USER">Hallo,</sec:ifAnyGranted>

    <sec:ifAllGranted roles="ROLE_ADMIN">
        allmächtiger <sec:username /> <br/>
        <g:link controller="administration" action="index">Controller Overview</g:link><br/>
        <g:link controller="administration" action="listUsers">Users</g:link><br/>
        <g:link controller="administration" action="listRoles">Roles</g:link><br/>
        <g:link controller="administration" action="listUserRoles">UserRoles</g:link><br/>
    </sec:ifAllGranted>

    <sec:ifAnyGranted roles="ROLE_USER, ROLE_SUPERUSER">
        einfältiger <sec:username />
    </sec:ifAnyGranted>

     (<g:link controller="logout">sign out</g:link>)

</sec:ifLoggedIn>

<oauth:connect provider="google" id="google-connect-link" >Connect to Google</oauth:connect>
<oauth:connected provider="google">CONNECTED TO GOOGLE</oauth:connected>
<oauth:disconnected provider="google">NOT CONNECTED TO GOOGLE</oauth:disconnected>

<oauth:connect provider="twitter">CONENCT TO Twitter</oauth:connect>
<oauth:connected provider="twitter">CONNECTED</oauth:connected>
<oauth:disconnected provider="twitter">disCONNECTED</oauth:disconnected>

<br/>
<%

 Token accessToken = (Token) session.getAttribute("google:oasAccessToken")

%>
profile: ${session}

</body>
</html>