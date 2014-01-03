<div id="menu" class="pure-g">



    <div id="nav" class="pure-u-1-6">

        <g:render template="/shared/backendsidebar" />

    </div>

    <div id="main" class="pure-u-5-6">

        <div class="contentPadding">

        <div class="pure-u-1">
            <div style="text-align: end; padding-right: 5px; padding-top: 5px;">
                <i id="iconinfo" class="fa fa-info-circle"> Info</i>
            </div>
            <g:render template="/shared/statustitlebar" />
        </div>

        <div class="pure-g-r">
            <div class="pure-u-2-3">
                <div style="padding: 1em;"><h1>Willkommen auf der Startseite</h1></div></div>
            <div class="pure-u-1-6"></div>
        </div>

        <div class="pure-g-r">
            <div class="pure-u-1-4">
                <div class="boxmain">Einführung <br> <br><i class="fa fa-rocket fa-3"></i></div>
            </div>
            <div class="pure-u-1-4">
                <div class="boxmain">Arbeitsgruppe <br><br>
                    <g:link controller="workGroup"><i class="fa fa-users  fa-3"></i></g:link>
                </div>
            </div>
            <div class="pure-u-1-4">
                <div class="boxmain">Website<br> <br>
                    <g:link controller="website"><i class="fa fa-file   fa-3"></i></g:link>
                </div>
            </div>
            <div class="pure-u-1-4">
                <div class="boxmain">Templates <br><br>
                    <g:link controller="template"><i class="fa fa-columns  fa-3"></i></g:link>
                </div>
            </div>

        </div>

        <div class="pure-g-r">
            <div class="pure-u-1-4">
                <div class="boxmain">Export <br> <br><g:link controller="export"><i class="fa fa-cloud-download   fa-3"></i></g:link></div>
            </div>
            <div class="pure-u-1-4">
                <div class="boxmain">Premium <br><br><i class="fa  fa-star  fa-3"></i></div>
            </div>
            <div class="pure-u-1-4">
                <div class="boxmain">Addons <br><br><i class="fa fa-plus-circle fa-3"></i></div>
            </div>
            <div class="pure-u-1-4">
                <div class="boxmain">Einstellungen <br><br><i class="fa fa-cogs fa-3"></i></div>
            </div>

        </div>
        </div>
    </div>
</div>
