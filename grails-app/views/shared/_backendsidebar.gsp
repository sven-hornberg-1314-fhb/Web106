<!-- Sidebar -->
<div id="sidebar">

	
	<div class="pure-menu pure-menu-open">
		<ul>
            <li style="background-color: #aaaabb;height: 100px; width: 80%; margin-left: 20px;margin-top: 30px; margin-bottom: 20px">
                LOGO
            </li>
        <li style="background-color: #CCCCCC;"><a href="${createLink(uri: '/')}">Start</a></li>

        <li>
        <g:link class="" controller="logout">
            <i class="fa fa-sign-out "></i>
            Abmelden
        </g:link>
        </li>

        <li><g:link controller="WorkGroup">Arbeitsgruppen</g:link></li>

		<li class="pure-menu-heading" style="background-color: #CCCCCC;">Webseite</li>
		<li><g:link controller="Website" >Webseiten</g:link></li>
		<li><g:link controller="Page" >Seiten</g:link></li>
		<li><g:link controller="Template" >Templates</g:link></li>


		<li class="pure-menu-heading" style="background-color: #CCCCCC;">Bausteine</li>
		<li><g:link controller="ContentComponent" >Text</g:link></li>

		<li class="pure-menu-heading" style="background-color: #CCCCCC;">Module</li>
		<li><g:link controller="uploadFile" >Dateiupload</g:link></li>
		<li><g:link controller="image">Bildergalerie</g:link></li>
		<li><g:link controller="export">Export</g:link></li>


		<li class="pure-menu-heading" style="background-color: #CCCCCC;">Einstellungen</li>

        <sec:ifAllGranted roles="ROLE_ADMIN">
		<li class="pure-menu-heading" style="background-color: #CCCCCC;">Admin</li>

            <li>   <g:link controller="administration" action="index">Controllers</g:link>  </li>
            <li>    <g:link controller="administration" action="manageUsers">Users</g:link>        </li>
            <li>    <g:link controller="administration" action="manageRoles">Roles</g:link>       </li>
            <li>    <g:link controller="administration" action="manageUserRoles">UserRoles</g:link> </li>
            <li>    <g:link controller="administration" action="clearSession">Session bereinigen</g:link> </li>

		</ul>  </sec:ifAllGranted>
	</div>
</div>
