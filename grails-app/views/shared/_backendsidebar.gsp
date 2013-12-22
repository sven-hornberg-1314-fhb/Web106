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





        <li><g:link controller="WorkGroup">WorkGroups</g:link></li>



		<li class="pure-menu-heading" style="background-color: #CCCCCC;">Webseite</li>
		<li><g:link controller="Website" >Websiten</g:link></li>
		<li><g:link controller="Page" >Seiten</g:link></li>
		<li><g:link controller="Template" >Templates</g:link></li>


		<li class="pure-menu-heading" style="background-color: #CCCCCC;">Bausteine</li>
		<li><g:link controller="MenuComponent" >MenuComponent</g:link></li>
		<li><g:link controller="ContentComponent" >ContentComponent</g:link></li>



		<li class="pure-menu-heading" style="background-color: #CCCCCC;">Module</li>
		<li><g:link controller="uploadFile" >Dateiupload</g:link></li>
		<li><a href="#">Bildergalerie</a></li>
		<li><a href="#">Export</a></li>


		<li class="pure-menu-heading">Einstellungen</li>

        <sec:ifAllGranted roles="ROLE_ADMIN">
		<li class="pure-menu-heading" style="background-color: #CCCCCC;">Admin</li>

            <li>   <g:link controller="administration" action="index">Controllers</g:link>  </li>
            <li>    <g:link controller="administration" action="listUsers">Users</g:link>        </li>
            <li>    <g:link controller="administration" action="listRoles">Roles</g:link>       </li>
            <li>    <g:link controller="administration" action="listUserRoles">UserRoles</g:link> </li>
            <li>    <g:link controller="administration" action="clearSession">clearSession</g:link> </li>

		</ul>  </sec:ifAllGranted>
	</div>
</div>
