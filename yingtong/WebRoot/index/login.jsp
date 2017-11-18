<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盈通租车</title>
<meta name="keywords" content="好买车" />
<meta name="description" content="好买车" />
<link href="../index/css/public.css" rel="stylesheet" type="text/css" />
<link href="../index/css/style.css" rel="stylesheet" type="text/css" />
<link href="../index/css/item_do.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../index/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="../index/js/js.js"></script>
<script type="text/javascript">
function checktel(){
	var bool=true;
	var value=$("#tel").val().trim();
	var reg=/^1[3|4|5|7|8]\d{9}$/;
	 if(!reg.test(value)){
		 $("#telError").css("display","");
		 $("#telError").text("格式错误,请输入正确的手机号！");
		 bool=false;
	 } 
	 return bool;
}
function checkPwd(){
	var vool=true;
	var password=$("#password").val().trim();
	 if(!password){
		 $("#passwordError").css("display","");
		 $("#passwordError").text("密码不能为空!");
		 bool=false;
	 }else if(password.length<6||password.length>20){
		 $("#passwordError").css("display","");
		 $("#passwordError").text("密码长度在6~20之间!");
		 bool=false;
	 }
	 return bool;
}
function getMessage(message){
	 $("#passwordError").css("display","");
	 $("#passwordError").text(message);
}
function check(){
	if(checktel()&&checkPwd()){
		return true;
	}
	return false;
}
function onfocusAction(id){
	$("#"+id+"Error").css("display","none");
}
</script>
</head>
<body style="background:#f6f8fa;">
	<div class="header">
		<div class="header-nr">
			<div class="header1">
				欢迎${user.username }光临盈通汽车
				<c:if test="${empty user }">
					<a href="../index/login.jsp">，请登录</a>或
			<a href="../user/toRegist.do">注册</a>
				</c:if>
			</div>
			<div class="header2">
				<c:if test="${!empty user }"><a href="javascript:;" class="my-zh">我的盈通</a>
					<ul>
						<li><a href="../order/showOrder.do">我的订单</a>
						</li>
						<li><a href="../user/myInfo.do">我的帐户</a>
						</li>
						<li><a href="../index/login_out.jsp"
							onclick="return confirm('您确定要退出吗？');" id="hd_logout_a">退出登录</a>
						</li>
					</ul>
				</c:if>
			</div>
			<div class="header3">0571-86913303</div>
		</div>
	</div>
	<div class="nav">
		<div class="nav-nr">
			<div class="logo">
				<a href="${pageContext.request.contextPath}/index/index.do"><img
					src="../index/images/logo.png" /> </a>
			</div>
			<div class="welcome">欢迎登陆</div>
			<div class="service-24h">400-1234-8888</div>
		</div>
	</div>
			<form action="../user/login.do" method="post"
				onsubmit="return check()">
		<div class="dl-banner">
			<img src="../index/images/banner.png" />
			<div class="denglu-nr2">
				<div class="tc-1">
					<span>登录</span>
					<p>
						<i>没有帐户</i><a href="../user/toRegist.do">立即注册</a>
					</p>
				</div>
				<div class="tc-2">
					<input type="text" name="tel" placeholder="请输入手机号" id="tel"
						onblur="checktel()" onfocus="onfocusAction('tel')" />&nbsp;&nbsp;&nbsp;<label
						class="labelError" id="telError" style="display:none"></label> <input
						type="password" name="password" placeholder="请输入密码" id="password"
						onblur="checkPwd()" onfocus="onfocusAction('password')" />&nbsp;&nbsp;&nbsp;<label
						class="labelError" id="passwordError" style="display:none"></label>
					<c:if test="${!empty message }">
						<script type="text/javascript">
 	location.href="javascript:getMessage('${message}')";
</script>
					</c:if>
				</div>
				<div class="tc-3">
					<a href="../index/findBackPwd.jsp">忘记密码？</a>
				</div>
				<div class="tc-4">
					<input type="submit" value="登录" />
				</div>
			</div>
		</div>
	</form>
	<%@include file="../index/foot.jsp"%>
	<div class="footer-bq">2016 All Rights
		Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>

	<div class="ce-nav">
		<a href="#" class="ce1"></a> <a href="#" class="ce2"></a> <a href="#"
			class="ce3"></a>
	</div>

</body>
</html>
