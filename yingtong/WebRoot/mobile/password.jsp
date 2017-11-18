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
<link rel="stylesheet" type="text/css" href="css/weixinshow.css">
<link rel="stylesheet" type="text/css" href="css/css.css">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/fastclick.js"></script>
<script src="js/js.js"></script>
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
	function checkPwd() {
						var bool = true;
						var pwd = $("#password").val();
						var reg = /^[\x21-\x7E]{6,16}$/;
						if (!reg.test(pwd)) {
							newaddress.ealert("6-18位数字、字母、符号组合密码");
							bool = false;
						}
						return bool;
					}
					function checkRePwd(){
					var bool=true;
					var pwd = $("#password").val();
					var rePwd=$("#repassword").val();
					if(pwd!=rePwd){
					newaddress.ealert("确认密码与原密码不相同!");
					bool=false;
					}
					return bool;
					}
				 function check(){
				 if(checkOldPwd()&&checkPwd()&checkRePwd()){
					 return true;
				 }
				return false;
				 }
				 function checkOldPwd(){
					 var bool=true;
					 if(!$("#oldpwd").val()){
					 newaddress.ealert("原密码不能为空!");
					 bool=false;
					 }else{
					 $.ajax({url:"../user/checkOldPwd.json",type:"post",async: false,data:{"oldpwd":$("#oldpwd").val()},dataType:"json",success:function(data){
						 if(data==2){
								bool=false;
								newaddress.ealert("原密码输入错误");
							}
					 },error:function(){
						 
					 }});
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
	<div id="all">
		<form action="../user/updatePwd.do?center=1" method="post"
			onsubmit="return check()">
			<div class="orderb"
				style="border-top:1px solid #e8e8e8; margin-top:0.24em;">
				<dl>
					<dt>原密码:</dt>
					<dd>
						<input type="password" id="oldpwd" placeholder="输入原密码"
							onblur="checkOldPwd()">
					</dd>
				</dl>
				<dl>
					<dt>新密码:</dt>
					<dd>
						<input type="password" name="password" id="password"
							placeholder="6-18位数字、字母、符号组合密码" onblur="checkPwd()">
					</dd>
				</dl>
				<dl>
					<dt>确认密码:</dt>
					<dd>
						<input type="password" id="repassword" placeholder="再次输入密码"
							onblur="checkRePwd()">
					</dd>
				</dl>
			</div>
			<div class="baocun">
				<input type="submit" value="保存">
		</form>
	</div>
	</div>
</body>
</html>
