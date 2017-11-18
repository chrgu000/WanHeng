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
 
 function checkTel(){
var bool=true;
var value=$("#tel").val();
var reg=/^1[3|4|5|7|8]\d{9}$/;
if(!reg.test(value)){
bool=false;
newaddress.ealert("手机号格式不正确!");
}
else {
	$.ajax({type:"post" ,url:"../user/getUserByTel.json",data:{tel:value},dataType:"json",async: false,success:function(data){
     if(data==0){
    	 bool=false;
  	  newaddress.ealert("该手机用户不存在!");
     }
	},error:function(){}});
	 }
return bool;
}
 $(function(){
    	 var timer=0;
    		var ti=60;
    	    $('.yzm').click(function(){
    	    	if(checkTel()){
    			$(this).css('display','none');
    			$(this).siblings('span').css('display','block').html('还剩60秒');
    			timer=setInterval(yzm, 1000); $.get("../user/getTelCode.do", {
					tel : $("#tel").val()
				}, function(data) {
					if(data==0){
						newaddress.ealert("获取验证码过于频繁!");
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
 if(checkTel()&&checkPwd()&&checkRePwd()){
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
		<form action="../user/findbackPwd1.do" method="post"
			onsubmit="return check()">
			<div class="denglu">
				<dl class="phone">
					<dt>
						<input type="text" name="tel" id="tel"    value="${u.tel }"
							placeholder="请输入手机号码">
					</dt>
					<dd>
						<a href="#" id="getCode"class="yzm">获取验证码</a><span
							class="yzm2">还剩60秒</span>
					</dd>
				</dl>
				<c:if test="${!empty msg }">
					<script type="text/javascript">
newaddress.ealert(msg);
</script>
				</c:if>
				<dl class="phone">
					<dt>
						<input type="text" name="code" id="code" placeholder="请输入手机验证码">
					</dt>
					<dd></dd>
				</dl>
				<dl class="password">
					<dt>
						<input type="password" placeholder="请输入密码" name="password"
							value="${u.password }" id="password" onblur="checkPwd()">
					</dt>
					<dd></dd>
				</dl>
				<dl class="password">
					<dt>
						<input type="password" value="${u.password }" id="repassword"
							onfocus="onfocusAction('repassword')" onblur="checkRePwd()"
							placeholder="请确认密码">
					</dt>
					<dd></dd>
				</dl>
			</div>
			<div class="btn-input">
				<input type="submit" value="确定">
			</div>
		</form>
	</div>
	<c:if test="${info  eq  '1' }">
		<script type="text/javascript">
	setTimeout(function(){
		location.href="../mobile/login.jsp"
	}
	,2000)
		 newaddress.ealert("恭喜你,密码设置成功!");
</script>
	</c:if>
</body>
<script src="../mobile/js/js.js"></script>
</html>
