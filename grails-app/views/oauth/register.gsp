<html>
<head>
    <title>Registrierung</title>
    <g:render template="/shared/header" />

</head>

<body>

<g:if test='${flash.message}'>
    <div class='login_message'>${flash.message}</div>
</g:if>

<g:if test='${!session.step}'>
    ${session.setAttribute('step','Step1')}
</g:if>

<g:if test="${session.step=='Step1'}">
    <g:render template="/shared/login" />
</g:if>

<g:if test="${session.step=='Step2'}">

<div id='login'>
    <div class='inner'>
            <div class='fheader'>Schritt 2 - Ergänzen Sie zusätzliche Informationen</div>

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
                                     class="${hasErrors(bean:user,field:'email','errors')}" placeholder="Email"/>
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
                        <label for="tokens">Login Anbieter</label>
                        <g:each  in="${user?.tokens}" name="tokens" var="entry">

                            <g:if test="${entry.key == 'twitter'}">
                                <img name="tokens" src="${resource(dir:'images/assets', file:'twitter.png')}"/>
                            </g:if>
                            <g:if test="${entry.key == 'google'}">
                                <img name="tokens" src="${resource(dir:'images/assets', file:'google.png')}"/>
                            </g:if>

                        </g:each>

                    </div>

                    <button type="submit" class="pure-button pure-button-primary">Account erstellen</button>
                </fieldset>
            </g:form>
        <g:link controller="logout" action="index"><button class="pure-button pure-button-primary">Zurück</button></button></g:link>
    </div>
</div>
</g:if>
<script type='text/javascript'>
    <!--
    (function() {
        document.forms['loginForm'].elements['j_username'].focus();
    })();
    // -->
</script>
</body>
</html>
