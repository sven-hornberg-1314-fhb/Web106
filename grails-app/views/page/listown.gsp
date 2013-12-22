<table class="pure-table pure-table-horizontal">
<tr>
	<th style="text-align: left">Name</th><th><!-- Edit --></th><th><!-- Delete --></th>
</tr>
<g:each var="page" in="${pages}">
<tr>
	<td style="width: 70%;text-align: left">${page.title}</td>
	<td><g:link class="pure-button" action="edit" id="${page.id}"><i class="fa fa-pencil-square-o"></i></g:link></td>
	<td><g:link class="pure-button" action="delete" id="${page.id}" ><i class="fa fa-trash-o"></i></g:link></td>
</tr>
</g:each>
</table>