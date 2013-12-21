<!-- Sidebar -->
<div id="sidebar">

	
	<div class="pure-menu pure-menu-open">
		<ul>
            <li style="background-color: grey;height: 100px; width: 100px; margin-left: 20px;margin-top: 30px; margin-bottom: 20px">
                LOGO
            </li>
		    <li><a href="${createLink(uri: '/')}">Start</a></li>


		<li class="pure-menu-heading"><!--Benutzer--> <sec:ifAnyGranted roles="ROLE_USER, ROLE_SUPERUSER">
            <sec:username />
        </sec:ifAnyGranted></li>

        <li class="pure-menu-heading">Workgroup: ${session?.activeWorkGroupName}</li>
        <li class="pure-menu-heading">Site: ${session?.activeWebsiteName}</li>

        <li><a><g:link controller="WorkGroup">WorkGroups</g:link></a></li>

        <sec:ifLoggedIn><li><g:link controller="logout">sign out</g:link></li>   </sec:ifLoggedIn>


		<li class="pure-menu-heading">Webseite</li>
		<li><g:link controller="Website" >Website</g:link></li>		
		<li><g:link controller="Page" >Page</g:link></li>
		<li><g:link controller="Template" >Template</g:link></li>


		<li class="pure-menu-heading">Bausteine</li>
		<li><g:link controller="MenuComponent" >MenuComponent</g:link></li>
		<li><g:link controller="ContentComponent" >ContentComponent</g:link></li>



		<li class="pure-menu-heading">Module</li>
		<li><g:link controller="uploadFile" >Dateiupload</g:link></li>
		<li><a href="#">Bildergalerie</a></li>
		<li><a href="#">Export</a></li>


		<li class="pure-menu-heading">Einstellungen</li>

        <sec:ifAllGranted roles="ROLE_ADMIN">
		<li class="pure-menu-heading">Admin</li>

            <li>   <g:link controller="administration" action="index">Controllers</g:link>  </li>
            <li>    <g:link controller="administration" action="listUsers">Users</g:link>        </li>
            <li>    <g:link controller="administration" action="listRoles">Roles</g:link>       </li>
            <li>    <g:link controller="administration" action="listUserRoles">UserRoles</g:link> </li>
            <li>    <g:link controller="administration" action="clearSession">clearSession</g:link> </li>

		</ul>  </sec:ifAllGranted>
	</div>
</div>
