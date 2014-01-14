
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

      <div class="contentPadding">

          <div class="pure-menu pure-menu-open pure-menu-vertical">
             <g:link class="pure-button" action="list">Dokumentenliste</g:link></li>

          </div>


          <div class="content scaffold-create" role="main">
                <h3>Neues Bild hochladen</h3>
                <div>Info: Zur Zeit werden nur png und jpg Dateien unterstützt</div><br>
                <g:if test="${flash.message}"><div class="message" role="status">${flash.message}</div></g:if>
                <g:uploadForm action="uploadImage">
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
</div>

</body>

</html>