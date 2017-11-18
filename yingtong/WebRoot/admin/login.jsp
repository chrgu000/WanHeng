<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="author" content="">
<meta name="keywords" content="">
<meta name="description" content="">
<link href="${pageContext.request.contextPath}/admin/css/base.css"
	type="text/css" rel="stylesheet">
<title>盈通后台管理系统</title>
<link href="${pageContext.request.contextPath}/admin/css/login.css"
	type="text/css" rel="stylesheet">
</head>
<body>
	<div class="login_all">
		<div id="login_wrap">
			<div id="login_l" class="rel">
				<h1 id="login_logo" title="">&nbsp;</h1>
			</div>
			<div id="login_r">
				<b class="bt">登录</b>
				<form action="${pageContext.request.contextPath}/admin/login.do"
					method="post">
					<p>
						<input type="text" id="tele" name="username" placeholder="用户名"
							value="">
					</p>
					<p>
						<input type="password" id="tele" name="password" placeholder="密码"
							value="">
					</p>
					<c:if test="${!empty message}">
						<div class="pass_fg" style="text-align:center;">
							<p style="font-size: 15px;color: red;border: medium;">${message}</p>
						</div>
					</c:if>

					<input type="submit" class="btns block" id="login_submit"
						value="登 录">
				</form>
			</div>
		</div>
	</div>

</body>
</html>