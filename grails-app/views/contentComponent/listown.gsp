<table class="pure-table pure-table-horizontal">
<tr>
	<th style="text-align: left">Name</th><th>Inhalt</th><th><!-- Edit --></th><th><!-- Delete --></th>
</tr>
<g:each var="con" in="${contents}">
<tr>
    <td style="width: 40%;text-align: left">${con.name}</td>
	<td style="width: 40%;text-align: left">${con.text}</td>
	<td><g:link action="edit" id="${con.id}"><i class="fa fa-pencil-square-o"></i></g:link></td>
	<td><g:link action="delete" id="${con.id}" ><i class="fa fa-trash-o"></i></g:link></td>
</tr>
</g:each>
</table>