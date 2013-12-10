<table>
<tr>
	<th>Name</th><th>Inhalt</th><th><!--Select --></th></th><th><!-- Edit --></th><th><!-- Delete --></th>
</tr>
<g:each var="website" in="${websites}">
<tr>
	<td>${website.title}</td>
    <td><g:link action="select" id="${website.id}" ><i class="fa fa-check"></i></g:link></td>
    <td><g:link action="edit" id="${website.id}"><i class="fa fa-pencil-square-o"></i></g:link></td>
	<td><g:link action="delete" id="${website.id}" ><i class="fa fa-trash-o"></i></g:link></td>
</tr>
</g:each>
</table>