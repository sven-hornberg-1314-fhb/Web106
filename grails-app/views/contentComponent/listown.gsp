<table>
<tr>
	<th>Name</th><th>Inhalt</th><th><!-- Edit --></th><th><!-- Delete --></th>
</tr>
<g:each var="con" in="${contents}">
<tr>
	<td>${con.name}</td>
	<td>${con.text}</td>
	<td><g:link url="edit/${con.name}"><i class="fa fa-pencil-square-o"></i></g:link></td>
	<td><g:link url="delete/${con.name}"><i class="fa fa-trash-o"></i></g:link></td>
</tr>
</g:each>
</table>