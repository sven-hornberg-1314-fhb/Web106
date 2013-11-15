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

</head>
<body>

<sec:ifNotLoggedIn>
    <g:link controller="login" action="auth"><button>Login</button></g:link>
    <g:link controller="user" action="wizard"><button>Register</button></g:link>
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
<hr/>



<!--
<oauth:connect provider="google" id="google-connect-link" >Login with Google</oauth:connect> -->
<oauth:connected provider="google">CONNECTED to Google with  ${session.getAttribute("google:oasAccessToken")}
    <g:link controller="oauth" action="logout" params="[provider:'twitter']">Logout</g:link>
</oauth:connected>
                                                                                     <br/>

<!--<oauth:connect provider="twitter">Login with Twitter</oauth:connect>      -->
<oauth:connected provider="twitter"> CONNECTED to Twitter with ${session.getAttribute("twitter:oasAccessToken")}
    <g:link controller="oauth" action="logout" params="[provider:'twitter']">Logout</g:link>
</oauth:connected>

<hr/>

${session}



</body>
</html>