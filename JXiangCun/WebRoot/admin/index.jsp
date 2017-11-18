<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>聚乡村后台管理系统</title>
<link href="css/base.css" type="text/css" rel="stylesheet">
<link href="css/tab.css" type="text/css" rel="stylesheet">
<link href="css/item.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
</head>
</head>
<%@ include file="login/login_chk.jsp"%>
<frameset rows="50,*" cols="*" frameborder="no" border="0"
	framespacing="0">
	<frame src="../admin/top.jsp" name="topFrame" scrolling="no"
		noresize="noresize" id="topFrame" title="topFrame" />
	<frameset rows="*" cols="190px,*" framespacing="0" frameborder="no"
		border="0">
		<frame src="../admin/left.jsp" name="leftFrame" scrolling="yes"
			noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="../admin/right.jsp" name="mainFrame" id="mainFrame"
			title="mainFrame" scrolling="yes" noresize="noresize" />
	</frameset>
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>