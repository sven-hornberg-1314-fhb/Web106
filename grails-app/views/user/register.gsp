<html>
<head>
    <title>POP - User Registration</title>
    <meta name="layout" content="main" />
    <style>/* reset everything */
    * {
        margin:  0px;
        padding: 0px;
        border:  0px;
    }

        /* for font size settings, see also
         * http://www.alistapart.com/articles/howtosizetextincss/
         */
    html {
        font-size: 62.5%;
    }

    body {
        font-family: "Century Gothic", Verdana, sans-serif;
        text-align: center;
        font-size: 1.2em;
        line-height: 1.8em;
    }

        /* header formatting */
    h1, h2, h3, h4, h5, h6 {
        color: #004A7F;
        font-weight: normal;
        margin: 0em 0em 0.5em 0em;
    }

    h1 { font-size: 2.2em; }
    h2 { font-size: 2.0em; }
    h3 { font-size: 1.8em; }
    h4 { font-size: 1.6em; }
    h5 { font-size: 1.4em; }
    h6 { font-size: 1.2em; }

    a { color: #FF9900; text-decoration: none; }
    a:link {}
    a:visited {}
    a:hover { text-decoration: underline overline;}
    a:active {}

    div#wrapper {
        width: 800px;
        margin: 10px auto;
        text-align: left;
        background: #FFFFFF;
    }

    div#login {
        float: right;
    }

    div#footer {
        text-align: center;
        border-top: 1px solid #333333;
    }

        /* ----- simple input form ----- */
    .simpleform {
        margin: 0 auto 1.0em auto;
        width: 80%;
    }

    .simpleform fieldset {
        padding: 0.5em;
        border: solid 2px #b7ddf2;
        background: #ebf4fb;
    }

    .simpleform legend {
        color: #004A7F;
        font-size: 1.4em;
        font-weight: normal;
    }

    .simpleform p {
        margin-bottom: 1.0em;
    }

    .simpleform p.info {
        color: #666666;
        padding-bottom: 0.5em;
        border-bottom: 1px solid #b7ddf2;
        margin-bottom: 1.0em;
    }

    .simpleform label {
        width: 30%;
        clear: both;
        float: left;
        text-align: right;
        display: inline;
        font-size: 1.2em;
        font-weight: bold;
        padding: 0.3em 0em;
    }

    .simpleform div.rightcol {
        width: 68%;
        float: right;
        text-align: left;
        display: inline;
        font-size: 1.2em;
        font-weight: bold;
        padding: 0.3em 0em;
    }

    .simpleform div.rightcol input {
        font-size: 1.0em;
        border: 1px solid #aacfe4;
        width: 70%;
    }

    .simpleform div.rightcol span.info {
        font-size: 0.8em;
        font-weight: normal;
        color: #666666;
    }

    .simpleform div.rightcol input.button {
        background-color: #FFFFFF;
        border: 1px solid #999999;
        border-color: #CCCCCC #CCCCCC #999999 #999999;
        padding: 0.1em 0.0em;
        text-align: center;
        width: 33%;
    }

    .simpleform div.rightcol input.button:hover {
        background: #FF9900;
        color: #FFFFFF;
    }

    .simpleform div.rightcol input:focus,
    .simpleform div.rightcol select:focus,
    .simpleform div.rightcol textarea:focus {
        border: 1px solid gray;
    }

    .simpleform div.rightcol input.errors {
        border: 1px solid red;
    }

        /* grails messages and errors */
    .message {
        background: #f3f8fc url(../images/skin/information.png) 8px 50% no-repeat;
        border: 1px solid #b2d1ff;
        color: #006dba;
        margin: 10px 0 5px 0;
        padding: 5px 5px 5px 30px
    }

    div.errors {
        background: #fff3f3;
        border: 1px solid red;
        color: #cc0000;
        margin: 10px 0 5px 0;
        padding: 5px 0 5px 0;
    }
    div.errors ul {
        list-style: none;
        padding: 0;
    }
    div.errors li {
        background: url(../images/skin/exclamation.png) 8px 0% no-repeat;
        line-height: 16px;
        padding-left: 30px;
    }

    td.errors select {
        border: 1px solid red;
    }
    td.errors input {
        border: 1px solid red;
    }</style>
</head>
<body>

<g:form class="simpleform" url="[controller:'user',action:'register']">
    <fieldset>
        <legend>User Registration</legend>
        <p class="info">
            Complete the form below to create an account!
        </p>
        <g:hasErrors bean="${user}">
            <div class="errors">
                <g:renderErrors bean="${user}"/>
            </div>
        </g:hasErrors>

        <label for="username">Username</label>
        <div class="rightcol">
            <g:textField name="username" value="${user?.username}"
                         class="${hasErrors(bean:user,field:'username','errors')}"/>
        </div>

        <label for="password">Password</label>
        <div class="rightcol">
            <g:passwordField name="password"
                             class="${hasErrors(bean:user,field:'password','errors')}" />
        </div>

        <label for="confirm">Confirm Password</label>
        <div class="rightcol">
            <g:passwordField name="confirm"
                             class="${hasErrors(bean:user,field:'password','errors')}" />
        </div>

        <label for="email">Email</label>
        <div class="rightcol">
            <g:passwordField name="email"
                             class="${hasErrors(bean:user,field:'email','errors')}" />
        </div>

        <label for="FirstName">Vorname</label>
        <div class="rightcol">
            <g:passwordField name="FirstName"
                             class="${hasErrors(bean:user,field:'email','errors')}" />
        </div>

        <label for="LastName">Nachname</label>
        <div class="rightcol">
            <g:passwordField name="LastName"
                             class="${hasErrors(bean:user,field:'email','errors')}" />
        </div>

        <!-- TOKENS -->


        <label>&nbsp;</label>
        <div class="rightcol">
            <g:submitButton class="button" name="submitButton" value="Create Account" />
        </div>
    </fieldset>
</g:form>
</body>
</html>