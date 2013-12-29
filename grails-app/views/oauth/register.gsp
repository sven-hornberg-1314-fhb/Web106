<html>
<head>

    <title>Registrierung</title>
    <style type='text/css' media='screen'>
    #login {
        margin: 15px 0px;
        padding: 0px;
        text-align: center;
    }

    #login .inner {
        width: 540px;
        padding-bottom: 6px;
        margin: 60px auto;
        text-align: left;
        border: 1px solid #aab;
        background-color: #f0f0fa;

    }

    #login .inner .fheader {
        padding: 18px 26px 14px 26px;
        background-color: #f7f7ff;
        margin: 0px 0 14px 0;
        color: #2e3741;
        font-size: 18px;
        font-weight: bold;
    }


    </style>



    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/grids-min.css">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet" />
</head>

<body>







<div id='login'>
    <div class='inner'>


        <g:if test='${flash.message}'>
            <div class='login_message'>${flash.message}</div>
        </g:if>

        <g:if test='${!session.step}'>
            ${session.setAttribute('step','Step1')}
        </g:if>

        <g:if test="${session.step=='Step1'}">
            <div class='fheader'>Schritt 1 - Wähle deinen Login Anbieter</div>
            <div class="pure-u-1 content">
             <p>
            <oauth:connect provider="google"><img src="${resource(dir:'images/assets', file:'google.png')}"/></oauth:connect>&nbsp;
            <oauth:connect provider="twitter"><img src="${resource(dir:'images/assets', file:'twitter.png')}"/></oauth:connect>
            </p>
            </div>
        </g:if>

        <g:if test="${session.step=='Step2'}">

            <div class='fheader'>Schritt 2 - Ergänze zusätzliche Informationen</div>

            <g:form class="pure-form pure-form-aligned" url="[controller:'oauth',action:'register']">
                <fieldset>

                    <g:hasErrors bean="${user}">
                        <div class="errors">
                            <g:renderErrors bean="${user}"/>
                        </div>
                    </g:hasErrors>

                    <div class="pure-control-group">
                        <label for="username">Benutzername</label>
                        <g:textField name="username" value="${user?.username}"
                                     class="${hasErrors(bean:user,field:'username','errors')}" placeholder="Benutzername"/>
                    </div>

                    <div class="pure-control-group">
                        <label for="email">Emailadresse</label>
                        <g:textField name="email" value="${user?.email}"
                                     class="${hasErrors(bean:user,field:'username','errors')}" placeholder="Email"/>
                    </div>

                    <div class="pure-control-group">
                        <label for="username">Vorname</label>
                        <g:textField name="FirstName" value="${user?.firstName}"
                                     class="${hasErrors(bean:user,field:'firstName','errors')}" placeholder="Vorname"/>
                    </div>

                    <div class="pure-control-group">
                        <label for="username">Nachname</label>
                        <g:textField name="LastName"  value="${user?.lastName}"
                                     class="${hasErrors(bean:user,field:'lastName','errors')}" placeholder="Nachname"/>
                    </div>

                    <div class="pure-control-group">
                        <label for="username">Token</label>
                        <g:each in="${user?.tokens}" var="entry">
                           ${entry.key} = ${entry.value}
                        </g:each>
                    </div>

                    <button type="submit" class="pure-button pure-button-primary">Account erstellen</button>
                </fieldset>
            </g:form>

        </g:if>
        <g:link controller="logout" action="index"><button class="pure-button pure-button-primary">Zurück</button></button></g:link>

    </div>
</div>
<script type='text/javascript'>
    <!--
    (function() {
        document.forms['loginForm'].elements['j_username'].focus();
    })();
    // -->
</script>
</body>
</html>
