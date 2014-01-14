<%@page defaultCodec="none" %>
<!DOCTYPE html>
<html>
   <head>
   <meta charset="UTF-8" />
   <title>${web106title}</title>
   <g:render template="/shared/header" />
   <g:if test="${request.requestURL.contains('localhost') == false}">
       ${web106header}
   </g:if>

   <g:if test="${request.requestURL.contains('localhost')}">
	  <style type="text/css">
	  <!--
	  -->
	 </style>

   <g:javascript library="jquery" />
   <r:require module="jquery-ui"/>


<r:layoutResources />

<script type="text/javascript">
        $(function() {

        	
        	if(document.URL.indexOf("template") == -1) {
            	$('.centermessagebox').hide();
            } 

        });
</script>
</g:if>


</head>
<body>

		<g:if test="${centermessagebox==true}">
			<g:render template="/shared/centermessagebox" />
		</g:if>

		<div id="main" class="pure-u-1">
			
			<div class="pure-g-r">
				<div id="header" class="pure-u-1 lightskyblue dropbox">
					${header}
				</div>
			</div>
			<div class="pure-g-r">
				<div id="sidebar1" class="pure-u-1-3 blue dropbox">
                    ${sidebar1}
				</div>
				<div id="content" class="pure-u-1-3 purple dropbox">
                    ${content}
				</div>
				<div id="sidebar2" class="pure-u-1-3 red dropbox">
                    ${sidebar2}
				</div>
			</div>

			<div class="pure-g-r">
				<div id="footer" class="pure-u-1-2 darkorange dropbox">
                    ${footer}
				</div>
				<div id="footer2" class="pure-u-1-2 darkgreen dropbox">
                    ${footer2}
				</div>
			</div>	
		</div>
	<r:layoutResources />
</body>
</html>