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
<script type="text/javascript" src="../index/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="../index/js/jquery.md5.js"></script>
<script type="text/javascript" src="../index/js/js.js"></script>
<script type="text/javascript">
					function checkPwd() {
						var bool = true;
						var pwd = $("#password").val();
						var reg = /^[\x21-\x7E]{6,16}$/;
						if (!reg.test(pwd)) {
							alert("6-18位数字、字母、符号组合密码");
							bool = false;
						}
						return bool;
					}
					function checkRePwd(){
					var bool=true;
					var pwd = $("#password").val();
					var rePwd=$("#repassword").val();
					if(pwd!=rePwd){
					alert("确认密码与原密码不相同!");
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
					 alert("原密码不能为空!");
					 bool=false;
					 }else{
					 $.ajax({url:"../user/checkOldPwd.json",type:"post",async: false,data:{"oldpwd":$("#oldpwd").val()},dataType:"json",success:function(data){
							 if(data==2){
								bool=false;
								alert("原密码输入错误");
							}
					 },error:function(){
						 
					 }});
					}
					 return bool;
				 }
				</script>
</head>
<body style="background:#f6f8fa;">
	<%@include file="../index/header.jsp"%>
	<ul>
		<li><a href="../index/index.do">首页</a></li>
		<li><a href="../car/duanzu.do">短租自驾</a></li>
			<li><a href="../index/zhuanche.jsp">专车</a></li>
		<li><a href="../longRentService/longRentServiceIndex.do">长租</a></li>
		<li><a href="../index/enterprise.jsp">企业租车</a></li>
		<li><a href="../order/showOrder.do">订单查询</a></li>
		<li class="cur"><a href="../about/index.do?title_id=1">关于我们</a></li>
	</ul>
	</div>
	</div>
	<div class="ny-all">
		<div class="ny-left">
			<dl>
				<dt>
					<a href="#">我的帐户</a>
				</dt>
				<dd>
					<a href="../user/myInfo.do">我的信息</a>
				</dd>
				<dd class="cur">
					<a href="../user/toUpdatePwd.do">修改密码</a>
				</dd>
			</dl>
			<dl>
				<dt>
					<a href="#">我的订单</a>
				</dt>
				<dd>
					<a href="../order/showOrder.do">短租自驾</a>
				</dd>
			</dl>
			<dl>
				<dt>
					<a href="#">会员条款</a>
				</dt>
				<c:forEach items="${articles4 }" var="a">
					<dd>
						<a
							href="javascript:location.href='../about/index.do?id=${a.id }';">${a.titles
							}</a>
					</dd>
				</c:forEach>
			</dl>
		</div>
		<form action="../user/updatePwd.do" method="post"
			onsubmit="return check()">
			<div class="ny-right">
				<div class="xinxi-a">
					<div class="xinxi-tit">我的信息</div>
					<dl>
						<dt>原密码</dt>
						<dd>
							<input type="password" id="oldpwd" placeholder="输入原密码"
								onblur="checkOldPwd()"><span>核对身份后，才可修改密码</span>
						</dd>
					</dl>
					<dl>
						<dt>新密码</dt>
						<dd>
							<input type="password" name="password" id="password"
								placeholder="6-18位数字、字母、符号组合密码" onblur="checkPwd()"><span>请牢记新密码</span>
						</dd>
					</dl>
					<dl>
						<dt>确认密码</dt>
						<dd>
							<input type="password" id="repassword" placeholder="再次输入密码"
								onblur="checkRePwd()"><span>请再次输入密码</span>
						</dd>
					</dl>

				</div>
				<div class="tijiao">
					<input type="submit" value="保存" />
				</div>
			</div>
		</form>
	</div>
		<%@include file="../index/foot.jsp" %>
	<div class="footer-bq">2016 All Rights
		Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>
	<div class="ce-nav">
		<a href="#" class="ce1"></a> <a href="#" class="ce2"></a> <a href="#"
			class="ce3"></a>
	</div>

</body>
</html>
