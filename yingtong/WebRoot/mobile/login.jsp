<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盈通汽车</title>
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"
	name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<link href="../mobile/css/item_do.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="../mobile/css/weixinshow.css">
<link rel="stylesheet" type="text/css" href="../mobile/css/css.css">
<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/js.js"></script>
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
function checktel(){
	var bool=true;
	var value=$("#tel").val().trim();
	var reg=/^1[3|4|5|7|8]\d{9}$/;
	 if(!reg.test(value)){
		  newaddress.ealert("格式错误,请输入正确的手机号！");
		 bool=false;
	 } 
	 return bool;
}
function checkPwd(){
	var vool=true;
	var password=$("#password").val().trim();
	 if(!password){
	 newaddress.ealert("密码不能为空!");
		 bool=false;
	 }else if(password.length<6||password.length>20){
	 newaddress.ealert("密码长度在6~20之间!");
		 bool=false;
	 }
	 return bool;
}
function check(){
	if(checktel()&&checkPwd()){
		return true;
	}
	return false;
}
 
</script>
</head>
<body>
	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
	<form action="../user/login.do" method="post" onsubmit="return check()">
		<c:if test="${!empty mess }">
			<script type="text/javascript">
				newaddress.ealert('${mess}');
</script>
		</c:if>
		 
			<input type="hidden" name="center" value="<%=request.getParameter("center")%>">
			<input type="hidden" name="phone" value="1">
		<div id="all">
			<div class="denglu">
				<dl class="phone">
					<dt>
						<input type="text" name="tel" placeholder="请输入手机号" id="tel"
							onblur="checktel()" />
					</dt>
					<dd></dd>
				</dl>
				<dl class="password">
					<dt>
						<input type="password" name="password" placeholder="请输入密码"
							id="password" onblur="checkPwd()" />
					</dt>
					<dd></dd>
				</dl>
			</div>
			<div class="btn-input">
				<input type="submit" value="登录">
			</div>
			<div class="dl-a">
				<a href="../user/toRegist1.do" class="dl-a1">注册会员</a><a
					href="../mobile/findBackPwd.jsp" class="dl-a2">忘记密码？</a>
			</div>
		</div>
	</form>
</body>
</html>
