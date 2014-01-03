<table class="pure-table pure-table-horizontal">
<tr>
	<th style="text-align: left">Name</th><th><!-- Edit --></th>
</tr>
<g:each var="site" in="${websites}">
    <tr>
        <td style="width: 70%;text-align: left">${site.title}</td>
        <td><g:link class="pure-button" action="cloudS3export" id="${site.id}"><i class="fa fa-pencil-square-o"></i></g:link></td>
    </tr>
</g:each>
</table>