<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>Web截屏控件2演示界面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link type="text/css" rel="Stylesheet" href="scp/scp.css" />
    <link type="text/css" rel="stylesheet" href="scp/skygqbox.css" />
    <script type="text/javascript" src="scp/jquery-1.4.min.js"></script>
    <script type="text/javascript" src="scp/json2.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="scp/skygqbox.js"></script>
    <script type="text/javascript" src="kindeditor-min.js" charset="utf-8"></script>
    <script type="text/javascript" src="scp/scp.app.js" charset="utf-8"></script>
    <script type="text/javascript" src="scp/scp.edge.js" charset="utf-8"></script>
    <script type="text/javascript" src="scp/scp.js" charset="utf-8"></script>
</head>
<body>
    <p>演示ScreenCapture与kindeditor3x编辑器整合</p>
    <textarea id="MyTextarea" name="MyTextarea" style="width:100%;height:400px;">此示例演示截屏控件与KindEditor3.x编辑器整合的功能。</textarea>
    <div id="scpPanel"></div>
	<script type="text/javascript" language="javascript">
        var editor_id = null;
		var scpMgr = new CaptureManager();
		scpMgr.Config["PostUrl"] = "http://localhost:8080/Capture/upload.jsp";
        scpMgr.event.postComplete = function (url) {
            var img = '<img src="' + url + "?t=" + new Date().getTime() + '"/>';            
            KE.appendHtml(editor_id, img);
        };
        scpMgr.loadAuto();

		KE.show({
		    id: 'MyTextarea',
		    items: ['source', '|', 'fullscreen', 'undo', 'redo', 'print', 'cut', 'copy', 'paste',
    'plainpaste', 'wordpaste', 'screencapture', '|', 'justifyleft', 'justifycenter', 'justifyright',
    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
    'superscript', '|', 'selectall', '-',
    'title', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold',
    'italic', 'underline', 'strikethrough', 'removeformat', '|', 'image',
    'flash', 'media', 'advtable', 'hr', 'emoticons', 'link', 'unlink', '|', 'about']
			, afterCreate: function (id){editor_id = id;}
		});
	</script>
</body>
</html>