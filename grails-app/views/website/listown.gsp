<table class="pure-table pure-table-horizontal">
<tr>
	<th style="text-align: left">Name</th><th><!--Select --></th></th><th><!-- Delete --></th>
</tr>
<g:each var="website" in="${websites}">
<tr>
	<td style="width: 70%;text-align: left">${website.title}</td>
    <td><g:link class="pure-button" action="select" id="${website.id}" ><i class="fa fa-check"></i></g:link></td>
	<td><g:link  class="pure-button" action="delete" id="${website.id}" ><i class="fa fa-trash-o"></i></g:link></td>
</tr>
</g:each>
</table>