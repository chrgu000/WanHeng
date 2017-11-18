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
<script type="text/javascript" src="../index/js/js.js" charset="utf-8"></script>
<script type="text/javascript">
 function checkUserName(){
	 var bool=true;
	 var value=$("#username").val().trim();
   var usernames=$("#usernames").val(); 
	 var usernamesArr=usernames.substring(1,usernames.length-1).split(",");
	 for(var i=0;i<usernamesArr.length;i++){
	  	 if(usernamesArr[i].trim()==value){
	      	  $("#usernameError").css("display", "");
	 		$("#usernameError").text("用户名已存在！");
	     	 	bool=false;
	     	 	break;
	     	 }
	    	   }
	    	if(value=="") {
	 		$("#usernameError").css("display", "");
	 		$("#usernameError").text("用户名不能为空！");
	 		bool = false;	
	 	 } else if(value.length < 2 || value.length > 20) {
	    		$("#usernameError").css("display", "");
	    		$("#usernameError").text("用户名长度在2 ~ 20之间！");
	    		bool=false;
	    	 } 
	    	  return bool;
 }
 function checktel(){
	 var bool=true;
	 var value=$("#tel").val().trim();
	 var tels=$("#tels").val();
	 var telsArr=tels.substring(1,tels.length-1).split(",");
	var reg=/^1[3|4|5|7|8]\d{9}$/;
	 for(var i=0;i<telsArr.length;i++){
		 if(telsArr[i].trim()==value){
			 $("#telError").css("display","");
			 $("#telError").text("手机号已存在!");
			 bool=false;
			 break;
		 }
	 }
	 if(!reg.test(value)){
		 alert("格式错误,请输入正确的手机号！");
		 bool=false;
	 }  
	 return bool;
 }
 $(function(){
		var timer=0;
		var ti=60;
	 $('.tc-2 dd a').click(function(){
		 if(checktel()){
			$(this).css('display','none');
			$(this).siblings('span').css('display','block').html('还剩60秒');
			timer=setInterval(yzm, 1000);
			$.get("../user/getTelCode.do", {
				tel : $("#tel").val()
			}, function(data) {
				if(data==0){
					alert("获取验证码过于频繁!");
				}
				}
			, 'json');
		 }
		});
		function yzm(){
			ti--;
			if(ti==0){
				 $('.tc-2 dd a').css('display','block').siblings('span').css('display','none');
				clearInterval(timer);
				ti=60;
			}else{
				 $('.tc-2 dd span').html("还剩"+ti+"秒");
			}
		}
 });
 function checkPwd(){
	 var bool=true;
	 var password=$("#password").val();
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
 function checkRePwd(){
	 var bool=true;
	 var password=$("#password").val();
	 var repassword=$("#repassword").val();
	 if(!password){
		 $("#repasswordError").css("display","");
		 $("#repasswordError").text("确认密码不能为空!");
		 bool=false;
	 }else if(password.length<6||password.length>20){
		 $("#repasswordError").css("display","");
		 $("#repasswordError").text("确认密码长度在6~20之间!");
		 bool=false;
	 }else if(password!=repassword){
		 $("#repasswordError").css("display","");
		 $("#repasswordError").text("两次密码不一致!");
		 bool=false;
	 }
	 return bool;
 }

 function check(){
	 if(checkUserName()&&checktel()&&checkPwd()&&checkRePwd()){
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
				<c:if test="${!empty user }">
					<a href="javascript:;" class="my-zh">我的盈通</a>
					<ul>
						<li><a href="../order/showOrder.do">我的订单</a></li>
						<li><a href="../user/myInfo.do">我的帐户</a></li>
						<li><a href="../index/login_out.jsp"
							onclick="return confirm('您确定要退出吗？');" id="hd_logout_a">退出登录</a></li>
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
			<div class="welcome">欢迎注册</div>
			<div class="service-24h">0571-86913303</div>
		</div>
	</div>
	<form action="../user/regist.do" method="post"
		onsubmit="return check()">
		<input type="hidden" id="tels" name="tels" value="${tels }" /> <input
			type="hidden" id="usernames" name="usernames" value="${usernames}" />
		<div class="dl-banner">
			<img src="../index/images/banner.png" />
			<div class="denglu-nr2">
				<div class="tc-1">
					<span>注册</span>
					<p>
						<i>已有帐户</i><a href="../index/login.jsp">立即登录</a>
					</p>
				</div>
				<div class="tc-2">
					<input type="text" name="username" id="username"
						value="${u.username }" onblur="checkUserName()"
						onfocus="onfocusAction('username')" placeholder="请输入真实姓名" />&nbsp;&nbsp;&nbsp;<label
						class="labelError" id="usernameError" style="display:none"></label>
					<input type="text" name="tel" id="tel" placeholder="请输入手机号码"
						value="${u.tel}" 
						onfocus="onfocusAction('tel')" />&nbsp;&nbsp;&nbsp;<label
						class="labelError" id="telError" style="display:none"></label>
					<dl>
						<dt>
							<input type="text" id="code" name="code" placeholder="请输入验证码" />
						</dt>
						<dd>
							<a href="#" id="getCode">获取验证码</a><span>还剩60秒</span>
							<c:if test="${!empty msg }">
								<script type="text/javascript">
alert('${msg}');
</script>
							</c:if>
						</dd>
					</dl>
					&nbsp;&nbsp;&nbsp;<label class="labelError" id="codeError"
						style="display:none"></label> <input type="password" id="password"
						value="${u.password }" name="password"
						placeholder="请输入6位以上数字或英文密码" onblur="checkPwd()"
						onfocus="onfocusAction('password')" /> &nbsp;&nbsp;&nbsp;<label
						class="labelError" style="display:none" id="passwordError"></label><input
						type="password" id="repassword" name="repassword"
						value="${u.password }" onblur="checkRePwd()"
						onfocus="onfocusAction('repassword')" placeholder="确认密码" />&nbsp;&nbsp;&nbsp;<label
						class="labelError" style="display:none" id="repasswordError"></label>
				</div>
				<div class="tc-4">
					<input type="submit" value="注册" />
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
