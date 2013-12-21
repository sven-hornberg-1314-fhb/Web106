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
  <title>Startseite</title>
  <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
  <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/grids-min.css">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet" />


    <style type="text/css" media="screen">
    .boxmain {
        height: 200px;
        padding: 1em;
    }
    .fa-3 {
        font-size: 6em;
    }
    </style>

<r:layoutResources></r:layoutResources>
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



        <div class="pure-g-r">
            <div class="pure-u-2-3">
                <div style="padding: 1em;"><h1>Willkommen auf der Startseite</h1></div></div>
            <div class="pure-u-1-6"></div>
        </div>

        <div class="pure-g-r">
            <div class="pure-u-1-4">
                <div class="boxmain">Einführung <br> <br><i class="fa fa-rocket fa-3"></i></div>
            </div>
            <div class="pure-u-1-4">
                <div class="boxmain">Arbeitsgruppe <br><br>
                   <g:link controller="workGroup"><i class="fa fa-users  fa-3"></i></g:link>
                </div>
            </div>
            <div class="pure-u-1-4">
                <div class="boxmain">Website<br> <br>
                    <g:link controller="website"><i class="fa fa-file   fa-3"></i></g:link>
                </div>
            </div>
            <div class="pure-u-1-4">
                <div class="boxmain">Templates <br><br>
                    <g:link controller="template"><i class="fa fa-columns  fa-3"></i></g:link>
                </div>
            </div>

        </div>

        <div class="pure-g-r">
            <div class="pure-u-1-4">
                <div class="boxmain">Export <br> <br><i class="fa fa-cloud-download   fa-3"></i></div>
            </div>
            <div class="pure-u-1-4">
                <div class="boxmain">Premium <br><br><i class="fa  fa-star  fa-3"></i></div>
            </div>
            <div class="pure-u-1-4">
                <div class="boxmain">Addons <br><br><i class="fa fa-plus-circle fa-3"></i></div>
            </div>
            <div class="pure-u-1-4">
                <div class="boxmain">Einstellungen <br><br><i class="fa fa-cogs fa-3"></i></div>
            </div>

        </div>


        <br><br><br><br>
        <hr>
        <div class="pure-g">
            <div class="pure-u-1">
            ${session}
            </div>
        </div>
    </div>
</div>

</sec:ifLoggedIn>
<hr/>

</body>
</html>