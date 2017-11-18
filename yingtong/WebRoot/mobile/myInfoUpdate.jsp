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
<script src="../mobile/js/js.js"></script>
<link rel="stylesheet" type="text/css" href="../mobile/css/css.css">
<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/fastclick.js"></script>
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
function checkName(){
var bool=true;
var value=$("#username").val();
if(!value){
 newaddress.ealert("姓名不能为空!");
 bool=false;
}
return bool;
}
function checkIdcard(){
var bool=true;
var value=$("#idcard").val();
var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/; 
var isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/; 
if(!(isIDCard1.test(value)||isIDCard2.test(value))){
newaddress.ealert("身份证号的格式不正确!");
bool=false;
}
return bool;
}
function check(){
if(checkName()&&checkIdcard()){
return true;
}
return false;
}
</script>
</head>
<%@ include file="../mobile/login_chk.jsp"%>
<body>
	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
	<form action="../user/update.do?center=1" method="post"
		onsubmit="return check()">
		<input type="hidden" name="id" value="${user.id }">
		<div id="all">
			<div class="xinxi">
				<dl>
					<dt>姓名:</dt>
					<dd>
						<input type="text" name="username" id="username"
							value="${user.username }" placeholder="请输入真实姓名">
					</dd>
				</dl>
				<dl>
					<dt>身份证号码:</dt>
					<dd>
						<input type="text" name="idcard" id="idcard"
							value="${user.idcard }" placeholder="请输入身份证号码">
					</dd>
				</dl>
				<dl>
					<dt>手机号码:</dt>
					<dd>
						<input type="text" name="tel" id="tel" value="${user.tel }"
							placeholder="请输入手机号码">
						<!--186****5678-->
					</dd>
				</dl>

			</div>
			<div class="baocun">
				<input type="submit" value="保存">
			</div>
		</div>
	</form>
	<script src="../mobile/js/js.js"></script>
</body>
</html>
