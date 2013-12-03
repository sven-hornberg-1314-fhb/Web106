<!-- Sidebar -->
<div id="sidebar">
	<div style="background-color: grey;height: 100px; width: 100px; margin-left: 60px;margin-top: 30px">
		LOGO
	</div>
	
	<ul>
		<ul>
		<li><a href="${createLink(uri: '/')}">Start</a></li>
		</ul>

		<ul><b>Benutzer</b><br><br>		
			<li><g:link controller="WorkGroup">WorkGroups</g:link></li>
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
		<li>Dateiupload</li>
		<li>Bildergalerie</li>
		<li>Export</li>
		</ul>

		<ul><b>Einstellungen</b><br><br>
		</ul>
		<ul><b>Admin</b><br><br>
		</ul>
	</ul>
</div>
