<!DOCTYPE html>
<html>
<head>
<title>Seite berabeiten: ${title}</title>
    <g:render template="/shared/header" />

<g:javascript library="jquery" />
<r:require module="jquery-ui"/>
<r:layoutResources />
    <link rel="stylesheet" type="text/css" href="${resource( absolute: true, dir: 'css', file: 'web106.css')}" />

</head>
<body>
<g:form class="pure-form pure-form-aligned" action="remoteSave">
    <fieldset>
        <div class="pure-control-group">
            <label for="title">Websitetitle</label>
            <g:textField name="title" placeholder="Website title" value="${title}" required="true"/>
        </div>

        <g:submitButton  name="submit" value="Submit" class="pure-button pure-button-primary"  />

    </fieldset>
</g:form>

	<r:layoutResources />
</body>
</html>
