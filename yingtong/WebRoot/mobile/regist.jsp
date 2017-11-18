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
<script src="../mobile/js/fastclick.js"></script>
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
 function checkUserName(){
	 var bool=true;
	 var value=$("#username").val().trim();
   var usernames=$("#usernames").val(); 
	 var usernamesArr=usernames.substring(1,usernames.length-1).split(",");
	 for(var i=0;i<usernamesArr.length;i++){
	  	 if(usernamesArr[i].trim()==value){
	 	newaddress.ealert("用户名已存在！");
	     	 	bool=false;
	     	 	break;
	     	 }
	    	   }
	    	if(value=="") {
	 	newaddress.ealert("用户名不能为空！");
	 		bool = false;	
	 	 } else if(value.length < 2 || value.length > 20) {
	    	newaddress.ealert("用户名长度在2 ~ 20之间！");
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
		newaddress.ealert("该手机已存在!");
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
			    $('.yzm').click(function(){
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
						 $('.yzm').css('display','block').siblings('span').css('display','none');
						clearInterval(timer);
						ti=60;
					}else{
						 $('.yzm2').html("还剩"+ti+"秒");
					}
				}
 });
 function checkPwd(){
	 var bool=true;
	 var password=$("#password").val();
	 if(!password){
		newaddress.ealert("密码不能为空!");
		 bool=false;
	 }else if(password.length<6||password.length>20){
		newaddress.ealert("密码长度在6~20之间!");
		 bool=false;
	 }
	 return bool;
 }
 function checkRePwd(){
	 var bool=true;
	 var password=$("#password").val();
	 var repassword=$("#repassword").val();
	 if(!password){
		 newaddress.ealert("确认密码不能为空!");
		 bool=false;
	 }else if(password.length<6||password.length>20){
		newaddress.ealert("确认密码长度在6~20之间!");
		 bool=false;
	 }else if(password!=repassword){
		 newaddress.ealert("两次密码不一致!");
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
 </script>
</head>
<body>
	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
	<div id="all">
			<c:if test="${!empty msg }">
								<script type="text/javascript">
newaddress.ealert("验证码错误!");
</script>
							</c:if>
		<form action="../user/regist1.do" method="post"
			onsubmit="return check()">
			<input type="hidden" id="tels" name="tels" value="${tels }" /> <input
				type="hidden" id="usernames" name="usernames" value="${usernames}" />
			<div class="denglu">
				<dl class="phone">
					<dt>
						<input type="text" name="username" id="username"
							onblur="checkUserName()" value="${u.username }"
							placeholder="请输入真实姓名">
					</dt>
					<dd></dd>
				</dl>
				<dl class="phone">
					<dt>
						<input type="text" name="tel" id="tel" placeholder="请输入手机号码"
							 value="${u.tel}">
					</dt>
					<dd>
						<a href="#;" id="getCode" class="yzm">获取验证码</a><span class="yzm2">还剩60秒</span>
					</dd>
					<c:if test="${!empty msg }">
						<script type="text/javascript">
newaddress.ealert("验证码错误!");
</script>
					</c:if>
				</dl>
				<dl class="phone">
					<dt>
						<input type="text" id="code" name="code" placeholder="请输入手机验证码">
					</dt>
					<dd></dd>
				</dl>
				<dl class="password">
					<dt>
						<input type="password" id="password" value="${u.password }"
							name="password" placeholder="请输入密码" onblur="checkPwd()">
					</dt>
					<dd></dd>
				</dl>
				<dl class="password">
					<dt>
						<input type="password" id="repassword" onblur="checkRePwd()"
							name="repassword" value="${u.password }" placeholder="请确认密码">
					</dt>
					<dd></dd>
				</dl>
			</div>
			<div class="btn-input">
				<input type="submit" value="注册">
			</div>
		</form>
	</div>
</body>
</html>
