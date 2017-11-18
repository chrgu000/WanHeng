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
<script type="text/javascript" src="../index/js/js.js"></script>
<script type="text/javascript">
function checkName(){
var bool=true;
var value=$("#username").val();
if(!value){
 alert("姓名不能为空!");
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
alert("身份证号的格式不正确!");
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
<%@ include file="../index/login_chk.jsp"%>
<body style="background:#f6f8fa;">
	<%@include file="../index/header.jsp" %>
	<ul>
				<li><a href="../index/index.do">首页</a>
				</li>
				<li><a href="../car/duanzu.do">短租自驾</a>
				</li>
					<li><a href="../index/zhuanche.jsp">专车</a></li>
				<li><a href="../longRentService/longRentServiceIndex.do">长租</a>
				</li>
				<li><a href="../index/enterprise.jsp">企业租车</a>
				</li>
				<li><a href="../order/showOrder.do">订单查询</a>
				</li>
				<li   class="cur"><a href="../about/index.do?title_id=1">关于我们</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="ny-all">
		<div class="ny-left">
			<dl>
				<dt>
					<a href="#">我的帐户</a>
				</dt>
				<dd class="cur">
					<a href="../user/myInfo.do">我的信息</a>
				</dd>
				<dd>
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
				<c:forEach items="${articles }" var="a">
				<dd>
					<a href="javascript:location.href='../about/index.do?id=${a.id }';">${a.titles }</a>
				</dd>
				</c:forEach>
				 
			</dl>
		</div>
		<form action="../user/update.do" method="post" onsubmit="return check()">
		<input type="hidden" name="id" value="${user.id }"/>
		<div class="ny-right">
			<div class="xinxi-a">
				<div class="xinxi-tit">我的信息</div>
				<dl>
					<dt>姓名</dt>
					<dd>
						<input type="text" value="${user.username }"  name="username" id="username" placeholder="请输入真实姓名" onblur="checkName()"><span>真实姓名，方便租车核对身份</span>
					</dd>
				</dl>
				<dl>
					<dt>证件</dt>
					<dd>
						<input type="text" value="${user.idcard }" name="idcard"  id="idcard"placeholder="请输入身份证号码" onblur="checkIdcard()"><span>如需修改，请致电400-1234-8888</span>
					</dd>
				</dl>
				<dl>
					<dt>手机号</dt>
					<dd>
						<p>${user.tel }</p>
						<input type="hidden" name="tel" value="${user.tel }"/>
						<a href="">修改</a>
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
