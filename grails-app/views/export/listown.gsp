<table class="pure-table pure-table-horizontal">
<tr>
	<th style="text-align: left">Name</th><th>Url</th><th>Upload</th><th>Letzter Upload</th>
</tr>
<g:each var="site" in="${websites}">
    <tr>
        <td style="width: 70%;text-align: left">${site.title}</td>
        <td>
        <g:if test="${site.exported}">

                <g:link target="_blank" class="pure-button"
                        url="/live/${site.paramsModel.workgroupName}/${site.paramsModel.websiteName}">
                    Live</g:link>
        </g:if>
        </td>

        <td><g:link class="pure-button" action="cloudS3export" id="${site.id}"><i class="fa fa-cloud-upload"></i></g:link></td>
        <td>${site.date}</td>
    </tr>



</g:each>

</table>