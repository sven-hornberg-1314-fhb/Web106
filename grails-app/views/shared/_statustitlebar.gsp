<div style="padding: 5px">
    <div id="userinfo">
    <sec:ifAnyGranted roles="ROLE_USER, ROLE_SUPERUSER">
       Angemeldet als: <sec:username /> <br>
    </sec:ifAnyGranted>

    Arbeitsgruppe: ${session?.activeWorkGroupName}<br>
    Webseite: ${session?.activeWebsiteName}
    </div>
</div>