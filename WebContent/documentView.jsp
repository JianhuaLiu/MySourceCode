<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="initial-scale=1,user-scalable=no,maximum-scale=1,width=device-width" />
<style type="text/css" media="screen">
html,body {
	height: 100%;
}

body {
	margin: 0;
	padding: 0;
	overflow: auto;
}

#flashContent {
	display: none;
}
</style>
<link rel="stylesheet" type="text/css" href="css/flexpaper.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.extensions.min.js"></script>
<script type="text/javascript" src="js/flexpaper.js"></script>
<script type="text/javascript" src="js/flexpaper_handlers_debug.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id="documentViewer" class="flexpaper_viewer"
			style="position: absolute; left: 10px; top: 10px; width: 800px; height: 650px"></div>

		<script type="text/javascript">
			function getDocumentUrl(document) {
				return "php/services/view.php?doc={doc}&format={format}&page={page}"
						.replace("{doc}", document);
			}

			var startDocument = "Paper";
			var fileName = "<%=session.getAttribute("fileName")%>";
			//alter(result);
			

			$('#documentViewer').FlexPaperViewer({
				config : {

					SWFFile : 'DocumentViewSources/swf/'+fileName+'.swf',

					Scale : 0.6,
					ZoomTransition : 'easeOut',
					ZoomTime : 0.5,
					ZoomInterval : 0.2,
					FitPageOnLoad : true,
					FitWidthOnLoad : false,
					FullScreenAsMaxWindow : false,
					ProgressiveLoading : false,
					MinZoomSize : 0.2,
					MaxZoomSize : 5,
					SearchMatchAll : false,

					RenderingOrder : 'flash',

					ViewModeToolsVisible : true,
					ZoomToolsVisible : true,
					NavToolsVisible : true,
					CursorToolsVisible : true,
					SearchToolsVisible : true,
					WMode : 'window',
					localeChain : 'en_US'
				}
			});
		</script>


		<script type="text/javascript">
			var url = window.location.href.toString();

			if (location.length == 0) {
				url = document.URL.toString();
			}

			if (url.indexOf("file:") >= 0) {
				jQuery('#documentViewer')
						.html(
								"<div style='position:relative;background-color:#ffffff;width:420px;font-family:Verdana;font-size:10pt;left:22%;top:20%;padding: 10px 10px 10px 10px;border-style:solid;border-width:5px;'><img src='http://flexpaper.devaldi.com/resources/warning_icon.gif'>&nbsp;<b>You are trying to use FlexPaper from a local directory.</b><br/><br/> FlexPaper needs to be copied to a web server before the viewer can display its document properlty.<br/><br/>Please copy the FlexPaper files to a web server and access the viewer through a http:// url.</div>");
			}
		</script>
</body>
</html>