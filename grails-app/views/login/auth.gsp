<html>
<head>

	<title><g:message code="springSecurity.login.title"/></title>
	<style type='text/css' media='screen'>
	#login {
		margin: 15px 0px;
		padding: 0px;
		text-align: center;
	}

	#login .inner .fheader {
		padding: 18px 26px 14px 26px;
		background-color: #f7f7ff;
		margin: 0px 0 14px 0;
		color: #2e3741;
		font-size: 18px;
		font-weight: bold;
	}

    p{
        margin-left: 10px;
        margin-bottom: 20px
    }

	</style>

    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/pure-min.css">
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.3.0/grids-min.css">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet" />

</head>

<body>
<div id='login'>
	<div class='inner'>
		<div class='fheader'><g:message code="springSecurity.login.header"/></div>

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
</body>
</html>
