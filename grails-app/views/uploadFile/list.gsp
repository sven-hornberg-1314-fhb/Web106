<html>
<head>
    <title>Bilderübersicht</title>
    <g:render template="/shared/header" />
</head>
<body>
<div id="menu" class="pure-g">

    <div id="nav" class="pure-u-1-6">

        <g:render template="/shared/backendsidebar" />

    </div>
    <div id="main" class="pure-u-5-6">
        <div class="contentPadding">


            <table class="pure-table pure-table-horizontal">
                <tr>
                    <th style="text-align: left">Name</th><th>Url</th><th>Löschen</th>
                </tr>


                <g:each var="pic" in="${pictures}">
                    <tr>
                        <td style="width: 70%;text-align: left">${pic.name}</td>
                        <td><g:link  class="pure-button"  target="_blank" url="${pic.url}"><i class="fa fa-eye"></i></g:link> </td>
                        <td><g:link class="pure-button" action="delete" id="${pic.fileNameID}" ><i class="fa fa-trash-o"></i></g:link></td>
                    </tr>


                </g:each>

            </table>




        </div>
    </div>
</div>
</body>
</html>