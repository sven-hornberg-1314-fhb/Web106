<html>
<head>
    <g:render template="/shared/header" />
</head>
<body>
<div id="menu" class="pure-g">

    <div id="nav" class="pure-u-1-6">

        <g:render template="/shared/backendsidebar" />

    </div>
    <div id="main" class="pure-u-5-6">
        <div class="contentPadding">

        <g:link action="cloudS3"><div class="pure-button">Export S3</div></g:link>
        </div>
    </div>
</div>
</body>
</html>
