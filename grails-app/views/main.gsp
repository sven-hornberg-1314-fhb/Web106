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

<sec:ifNotLoggedIn>
    <g:link controller="oauth" action="login"><button>Login</button></g:link>
    <g:link controller="oauth" action="register"><button>Register</button></g:link>
</sec:ifNotLoggedIn>


<sec:ifLoggedIn>
<div id="menu" class="pure-g">


    
    <div id="nav" class="pure-u-1-6">
    
		<g:render template="/shared/backendsidebar" />
	
	</div>

	<div id="main" class="pure-u-5-6">

       <h1>Willkommen auf der Startseite</h1>

       ${session}  <br/>

</div>

</sec:ifLoggedIn>
<hr/>

</body>
</html>