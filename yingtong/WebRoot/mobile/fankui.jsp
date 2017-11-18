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
<link rel="stylesheet" type="text/css"
	href="../mobile/css/weixinshow.css">
<link rel="stylesheet" type="text/css" href="../mobile/css/css.css">
<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/js.js"></script>
<script type="text/javascript">
	var newaddress = {}
	newaddress.ealert = function(msg) {
		$('.warnMsg').html(msg);
		$('.error').show();
		$('.error').fadeIn(400).delay(800).fadeOut(400);
	}
	newaddress.eclear = function() {
		$('.warnMsg').html('');
	}
	function check() {
		var bool = true;
		var value = $("#advice").val();
		if (!value) {
			newaddress.ealert("请填写建议，建议不能为空!");
			bool = false;
		}
		return bool;
	}
</script>
</head>
<%@ include file="../mobile/login_chk.jsp"%>
<body>
	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
	<form action="../about/addAdvice.do" method="post"
		onsubmit="return check()">
		<div id="all">
			<div class="fankui">
				<textarea placeholder="写下您对我们的意见或建议，我们会努力为您提供更好的服务" name="advice"
					id="advice"></textarea>
			</div>
			<div class="btn-input">
				<input type="submit" value="提交">
			</div>
		</div>
		<c:if test="${!empty msg }">
		<script type="text/javascript">
			setTimeout(function() {
     		location.href = "../mobile/index.jsp";
			}, 1000);
				newaddress.ealert('${msg}');
		</script>
	</c:if>
	</form>
	<script src="../mobile/js/js.js"></script>
</body>
</html>
