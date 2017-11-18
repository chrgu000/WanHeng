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
 function checkTel(){
var bool=true;
var value=$("#tel").val();
var reg=/^1[3|4|5|7|8]\d{9}$/;
if(!reg.test(value)){
bool=false;
alert("手机号格式不正确!");
}else{
	$.ajax({type:"post" ,url:"../user/getUserByTel.json",data:{tel:value},dataType:"json",async: false,success:function(data){
   if(data==0){
	   bool=false;
	   alert("该手机用户不存在!");
   }
	},error:function(){}});
}

return bool;
}
  $(function(){
 var timer=0;
var ti=60;
 $('.tc-2 dd a').click(function(){
	 if(checkTel()){
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
, 'json');}
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
 if(checkTel()&&checkPwd()&&checkRePwd()){
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
				<a href="../index/index.do"><img src="../index/images/logo.png" /> </a>
			</div>
			<div class="welcome">找回密码</div>
			<div class="service-24h">0571-86913303</div>
		</div>
	</div>
	<form action="../user/findbackPwd.do" method="post"
		onsubmit="return check()">
		<div class="tc-2">
			<ul>
				<li><input type="text" placeholder="输入手机号码" name="tel" id="tel"
					value="${u.tel }"/>
					<dl>
						<dt>
							<input type="text" id="code" name="code" placeholder="请输入验证码" />
						</dt>
						<dd>
							<a href="#" id="getCode">获取验证码</a><span>还剩60秒</span>
							<c:if test="${!empty msg }">
								<script type="text/javascript">
alert("验证码错误!");
</script>
							</c:if>
						</dd>
					</dl>
					</li>
					<li><input type="password" placeholder="输入新密码" name="password"
						value="${u.password }" id="password"
						onfocus="onfocusAction('password')" onblur="checkPwd()" />&nbsp;&nbsp;&nbsp;<label
						class="labelError" id="passwordError" style="display:none"></label>
				</li>
					<li><input type="password" placeholder="再次输入新密码"
						value="${u.password }" id="repassword"
						onfocus="onfocusAction('repassword')" onblur="checkRePwd()" />&nbsp;&nbsp;&nbsp;<label
						class="labelError" style="display:none" id="repasswordError"></label>
				</li>
			</ul>
			<div class="tijiao">
				<input value="保存" type="submit">
			</div>
		</div>
	</form>
	<c:if test="${info  eq  '1' }">
		<div class="zf-tan" id="laa">
			<dl>
				<dt>
					<span>支付提示</span><a href="javascript:;"></a>
				</dt>
				<dd>
					<div class="zf-div3">
						<p>恭喜您，密码设置成功！</p>
					</div>
					<div class="zf-div4">
						<a href="../car/duanzu.do" class="zf-btn3">马上租车</a><a
							href="../index/index.do" class="zf-btn4">返回首页</a>
					</div>
				</dd>
			</dl>
		</div>
	</c:if>
	<%@include file="../index/foot.jsp"%>
	<div class="footer-bq">2016 All Rights
		Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>

	<div class="ce-nav">
		<a href="#" class="ce1"></a> <a href="#" class="ce2"></a> <a href="#"
			class="ce3"></a>
	</div>

</body>
</html>
