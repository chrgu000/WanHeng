<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>聚乡村</title>

<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" /> 
<meta content="black" name="apple-mobile-web-app-status-bar-style" /> 
<meta content="telephone=no" name="format-detection" />

<link rel="stylesheet" type="text/css" href="css/css.css">

<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/fastclick.js"></script>
</head>
<body>

<div id="all">
	<div class="ziliao">
    	<a href="#"><dl class="headers"><dt>头像</dt><dd><img src="${user.path }"></dd></dl></a>
    	<a href="#"><dl><dt>用户名</dt><dd>${user.nickname}</dd></dl></a>
    	<a href="#"><dl><dt>手机号</dt><dd>${user.tel }</dd></dl></a>
    	<a href="${pageContext.request.contextPath}/user/toMyAddress.do"><dl><dt>收货地址</dt><dd>&nbsp;</dd></dl></a>
    </div>
    <div class="bottom2">
    	<div class="bottom2-nr">
        	<a href="#" class="fanhui">&nbsp;</a>
        </div>
    </div>
</div>

<script src="js/js.js"></script>
</body>
</html>
