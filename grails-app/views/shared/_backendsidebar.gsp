<!-- Sidebar -->
<div id="sidebar">
	<div style="background-color: grey;height: 100px; width: 100px; margin-left: 60px;margin-top: 30px">
		LOGO

	</div>
	
	<ul>
		<ul>
		<li><a href="${createLink(uri: '/')}">Start</a></li>
		</ul>

		<ul><b><!--Benutzer--> <sec:ifAnyGranted roles="ROLE_USER, ROLE_SUPERUSER">
            <sec:username />
        </sec:ifAnyGranted></b><br><br>
			<li><g:link controller="WorkGroup">WorkGroups</g:link></li>
            <sec:ifLoggedIn><li><g:link controller="logout">sign out</g:link></li>   </sec:ifLoggedIn>
		</ul>

		<ul><b>Webseite</b><br><br>
		<li><g:link controller="Website" >Website</g:link></li>		
		<li><g:link controller="Page" >Page</g:link></li>
		<li><g:link controller="Template" >Template</g:link></li>
		</ul>

		<ul><b>Bausteine</b><br><br>	
		<li><g:link controller="MenuComponent" >MenuComponent</g:link></li>
		<li><g:link controller="ContentComponent" >ContentComponent</g:link></li>
		</ul>


		<ul><b>Module</b><br><br>
		<li><g:link controller="uploadFile" >Dateiupload</g:link></li>
		<li>Bildergalerie</li>
		<li>Export</li>
		</ul>

		<ul><b>Einstellungen</b><br><br>
		</ul>
        <sec:ifAllGranted roles="ROLE_ADMIN">
		<ul><b>Admin</b><br><br>

            <li>   <g:link controller="administration" action="index">Controllers</g:link><br/>  </li>
            <li>    <g:link controller="administration" action="listUsers">Users</g:link><br/>        </li>
            <li>    <g:link controller="administration" action="listRoles">Roles</g:link><br/>       </li>
            <li>    <g:link controller="administration" action="listUserRoles">UserRoles</g:link><br/> </li>
            <li>    <g:link controller="administration" action="clearSession">clearSession</g:link><br/> </li>

		</ul>  </sec:ifAllGranted>
	</ul>
</div>
