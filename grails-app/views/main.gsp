<%--
  Created by IntelliJ IDEA.
  User: marcman
  Date: 11.11.13
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
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
        allmächtiger <sec:username />
    </sec:ifAllGranted>

    <sec:ifAnyGranted roles="ROLE_USER, ROLE_SUPERUSER">
        einfältiger <sec:username />
    </sec:ifAnyGranted>

     (<g:link controller="logout">sign out</g:link>)

</sec:ifLoggedIn>

</body>
</html>