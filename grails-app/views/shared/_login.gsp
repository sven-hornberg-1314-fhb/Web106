
<div id='login'>
    <div class='inner'>
        <div class='fheader'>Wählen Sie einen Login Anbieter aus</div>

        <g:if test='${flash.message}'>
            <div class='login_message'>${flash.message}</div>
        </g:if>

        <div class="pure-u-1 content">
            <p>
                <oauth:connect provider="google"><img src="${resource(dir:'images/assets', file:'google.png')}"/></oauth:connect>&nbsp;
                <oauth:connect provider="twitter"><img src="${resource(dir:'images/assets', file:'twitter.png')}"/></oauth:connect>
            </p>
            <g:link controller="logout" action="index"><button class="pure-button" >Zurück</button></button></g:link>
        </div>

    </div>
</div>
<script type='text/javascript'>
    <!--
    (function() {
        document.forms['loginForm'].elements['j_username'].focus();
    })();
    // -->
</script>