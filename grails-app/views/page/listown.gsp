<table>
<tr>
	<th>Name</th><th>Inhalt</th><th><!-- Edit --></th><th><!-- Delete --></th>
</tr>
<g:each var="page" in="${pages}">
<tr>
	<td>${page.title}</td>
	<td><g:link action="edit" id="${page.id}"><i class="fa fa-pencil-square-o"></i></g:link></td>
	<td><g:link action="delete" id="${page.id}" ><i class="fa fa-trash-o"></i></g:link></td>
</tr>
</g:each>
</table>