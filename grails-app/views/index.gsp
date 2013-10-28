<%@ page import="pl.touk.oauth.User" %>
<!doctype html>
<html>
	<head>
        <meta name="google-site-verification" content="5at_QfUNc63siwYZNrAw33eu0VC3W20wbiAWrdCxJ_w" />
		<meta name="layout" content="bootstrap"/>
		<title>Grails OAuth Scribe Example</title>
	</head>

	<body>
        <div class="row-fluid">

            <section id="main" class="span8">

                <sec:ifNotLoggedIn>

                    <div class="page-header">
                        <h1>Sign in using social account</h1>
                    </div>

                    <div>
                        <ul class="unstyled">
                            <g:each var="entry" in="${grailsApplication.config.auth.providers}">
                                <g:set var="provider" value="${entry.key}"/>
                                <g:set var="icon" value="${entry.value}"/>
                                <li>
                                    <i class="icon-${icon}"></i>
                                    <g:link controller="auth" action="signin" params="[provider: provider]" class="signInAct hv_link_drbl social-link Header-small padded-box-tp-med">
                                        <span class="Header-medium" style="text-transform: uppercase">${provider}</span>
                                    </g:link>

                                </li>
                            </g:each>
                        </ul>
                    </div>
                </sec:ifNotLoggedIn>

                <sec:ifLoggedIn>

                    <g:set var="loggedUser" value="${User.get(sec.loggedInUserInfo(field:"id") as Long)}"/>

                    <div class="page-header">
                        <h1>Welcome ${loggedUser.username}!</h1>
                    </div>

                    <div>
                        <g:if test="${loggedUser.avatarUrl}">
                            <img src="${loggedUser.avatarUrl}" style="max-height: 150px; max-width: 150px"/>
                        </g:if>
                        <g:link controller="logout">Logout</g:link>
                    </div>

                </sec:ifLoggedIn>

			</section>
		</div>
		
	</body>
</html>
