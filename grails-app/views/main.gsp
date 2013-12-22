<%@page import="org.scribe.model.OAuthRequest; org.scribe.model.Token" %>
<%@ page import="uk.co.desirableobjects.oauth.scribe.OauthService" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
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

    #userinfo {
        z-index: 5;
        position: absolute;
        margin-left: 10%;
        visibiility: collapse;
        background-color: honeydew;
        padding: 15px;
        visibility: hidden;
    }

    <sec:ifNotLoggedIn>
        body {
            background-color: #444444;
        }

        .contentBlack {
            color: #ffffff;
        }

        .content {
            background-color: #ffffff;
        }
    </sec:ifNotLoggedIn>


    </style>
<r:require module="jquery"></r:require>
<r:layoutResources></r:layoutResources>
    <script type="text/javascript">
        $(function() {

            $('#iconinfo').hover(
                    function () {
                        $('#userinfo').css('visibility','visible').fadeIn(500);
                    },
                    function () {
                        $('#userinfo').fadeOut(500).hide();
                    }
            );

        });
    </script>
</head>
<body>

<sec:ifNotLoggedIn>
    <g:render template="/shared/mainNotLoggedIn" />
</sec:ifNotLoggedIn>


<sec:ifLoggedIn>
       <g:render template="/shared/mainLoggedIn" />
</sec:ifLoggedIn>

</body>
</html>