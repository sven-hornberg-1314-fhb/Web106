
<!DOCTYPE html>
<html>
<head>
    <title>Datei hochladen</title>

    <link rel="stylesheet" href="${resource(dir: 'css', file: 'lightbox.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'ImageStylesheet.css')}" type="text/css">

    <g:render template="/shared/header" />
    <r:layoutResources />

</head>
<body>
<div id="menu" class="pure-g">

<div id="nav" class="pure-u-1-6">

    <g:render template="/shared/backendsidebar" />

</div>

  <div class="pure-u-5-6">


      <div class="pure-menu pure-menu-open pure-menu-vertical">
          <ul>
              <li><g:link class="list" action="list">Dokumentenliste</g:link></li>
          </ul>
      </div>


<div class="content scaffold-create" role="main">
    <h1 style="padding-left: 10px">Upload New Document</h1>
    <g:if test="${flash.message}"><div class="message" role="status">${flash.message}</div></g:if>
    <g:uploadForm action="upload">
        <fieldset class="form">
            <input type="file" class="pure-button" name="file" />
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="upload" class="save pure-button" value="Upload" />
        </fieldset>
    </g:uploadForm>
</div>
  </div>
</div>

</body>

</html>