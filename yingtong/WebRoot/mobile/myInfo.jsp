<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>租车</title>

<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"
	name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
	<script src="../mobile/js/js.js"></script>
<link rel="stylesheet" type="text/css" href="../mobile/css/css.css">
<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/fastclick.js"></script>
</head>
<%@ include file="../mobile/login_chk.jsp"%>
<body>

	<div id="all">
		<div class="xinxi">
			<dl>
				<dt>姓名:</dt>
				<dd>${user.username }</dd>
			</dl>
			<dl>
				<dt>身份证号码:</dt>
				<dd>${user.idcard }</dd>
			</dl>
			<dl>
				<dt>手机号码:</dt>
				<dd>${user.tel }</dd>
			</dl>
		</div>

	</div>
</body>
</html>
