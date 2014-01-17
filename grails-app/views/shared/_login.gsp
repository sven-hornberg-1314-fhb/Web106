
<div id='login' class="contentPadding">

    <div >
        <i class="fa fa-info-circle"></i> Der Login über Twitter ist nach einer Umstellung momentan nicht möglich.
    </div>

    <br>
    <div class="inner loginbox">
        <div class='fheader'>Wählen Sie einen Login Anbieter aus</div>

        <g:if test='${flash.message}'>
            <div class='login_message'>${flash.message}</div>
        </g:if>

        <div class="pure-u-1 content">

                <oauth:connect provider="google"><img src="${resource(dir:'images/assets', file:'google.png')}"/></oauth:connect>&nbsp;

                <!--<oauth:connect provider="twitter">-->
                    <img class="greyout" src="${resource(dir:'images/assets', file:'twitter.png')}"/>
                <!--</oauth:connect>-->
        </div>
        <div class="pure-u-1 content">
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