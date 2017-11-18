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
<link href="../index/css/label.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../index/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="../index/js/js.js"></script>
<script type="text/javascript">
function onfocusAction(id){
$("#"+id+"Error").css("display","none");
}
function checkAccount(){
var bool=true;
var value=$("#account").val().trim();
if(!value){
 $("#accountError").css("display","block");
 $("#accountError").text("账户名不能为空!");
 bool=false;
}
if(value.length>16){
 $("#accountError").css("display","block");
 $("#accountError").text("长度不超过16位英文字母或数字!");
 bool=false;
}
return bool;
}
function checkName(){
var bool=true;
var value=$("#name").val().trim();
if(!value){
 $("#nameError").css("display","block");
 $("#nameError").text("公司名称不能为空!");
 bool=false;
}
return bool;
}
function checkAddress(){
var bool=true;
var value=$("#address").val().trim();
if(!value){
$("#addressError").css("display","block");
 $("#addressError").text("公司地址不能为空!");
 bool=false;
}
return bool;
}
function checkRelationPerson(){
var bool=true;
var value=$("#relation_person").val().trim();
if(!value){
$("#relation_personError").css("display","block");
 $("#relation_personError").text("联系人不能为空!");
 bool=false;
}
return bool;
}
function checktel(){
var bool=true;
var value=$("#tel").val().trim();
var tels=$("#tels").val();
var telsArr=tels.substring(1,tels.length).split(",");
var reg=/^1[3|4|5|7|8]\d{9}$/;
if(!(reg.test(value))){
$("#telError").css("display","block");
 $("#telError").text("联系电话格式不正确!");
 bool=false;
}
for(var i=0;i<telsArr.length;i++){
	if(telsArr[i].trim()==value){
		$("#telError").css("display","block");
		 $("#telError").text("联系电话已存在!");
		 bool=false;
	}
}
return bool;
}
function checkEmail(){
var bool=true;
var value=$("#email").val().trim();
var reg= /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
 if(!reg.test(value)){
 $("#emailError").css("display","block");
 $("#emailError").text("邮箱格式不正确!");
 bool=false;
 }
return bool;
}
function checkCode(){
var bool=true;
var value=$("#code").val().trim();
if(!value){
 $("#codeError").css("display","block");
 $("#codeError").text("验证码不能为空!");
 bool=false;
}
return bool;
}
function check(){
if(checkAccount()&&checkName()&&checkAddress()&&checkRelationPerson()&&checktel()&&checkEmail()&&checkCode()){
return true;
}
return false;
}
function change() {
	var img = document.getElementById("img");
	img.src = "../enterprise/createImage.do?" + Math.random();
}
</script>
</head>
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
				<li   class="cur"><a href="../index/enterprise.jsp">企业租车</a>
				</li>
				<li><a href="../order/showOrder.do">订单查询</a>
				</li>
				<li><a href="../about/index.do?title_id=1">关于我们</a>
				</li>
			</ul>
		</div>
	</div>
	<form action="../enterprise/addEnterprise.do" method="post"
		onSubmit="return check()">
		<input type="hidden" name="tels" id="tels" value="${tels}"/>
		<div class="shenqing">
			<h5>企业帐户申请</h5>
			<dl>
				<dt>企业帐户名：</dt>
				<dd>
					&nbsp;&nbsp;&nbsp;<label class="labelError" id="accountError"></label><input
						type="text" name="account" id="account" value="${e.account }"onblur="checkAccount()"
						onfocus="onfocusAction('account')"
						placeholder="长度不超过16位英文字母或数字，一经申请不可修改" />
				</dd>
			</dl>
			<dl>
				<dt>公司名称：</dt>
				<dd>
					&nbsp;&nbsp;&nbsp;<label class="labelError" id="nameError"></label><input
						type="text" name="name" id="name" value="${e.name }"onblur="checkName()"
						onfocus="onfocusAction('name')" placeholder="请与营业执照保持一致" />
				</dd>
			</dl>
			<dl>
				<dt>公司地址：</dt>
				<dd>
					&nbsp;&nbsp;&nbsp;<label class="labelError" id="addressError"></label><input
						type="text" name="address" id="address" value="${e.address }"onblur="checkAddress()"
						onfocus="onfocusAction('address')" placeholder="请填写办公地址" />
				</dd>
			</dl>
			<dl>
				<dt>联系人：</dt>
				<dd>
					&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="relation_personError"></label><input type="text"
						name="relation_person" id="relation_person"
						onblur="checkRelationPerson()"
						onfocus="onfocusAction('relation_person')" value="${e.relation_person }"placeholder="请填写联系人姓名" />
				</dd>
			</dl>
			<dl>
				<dt>联系电话：</dt>
				<dd>
					&nbsp;&nbsp;&nbsp;<label class="labelError" id="telError"></label><input
						type="text" name="tel" id="tel" value="${e.tel }"onblur="checktel()"
						onfocus="onfocusAction('tel')" placeholder="请填写联系电话" />
				</dd>
			</dl>
			<dl>
				<dt>E-mail：</dt>
				<dd>
					&nbsp;&nbsp;&nbsp;<label class="labelError" id="emailError"></label><input
						type="text" name="email" id="email" value="${e.email }"onblur="checkEmail()"
						onfocus="onfocusAction('email')" placeholder="请填写电子邮箱地址" />
				</dd>
			</dl>
			<dl>
			<c:if test="${message=='success' }">
			<script type="text/javascript">
			alert("申请账户成功!")
			location.href="../index/index.do"
</script>
			</c:if>
				<dt>验证码：</dt>
				<dd>
					<c:if test="${!empty message }">
					&nbsp;&nbsp;&nbsp;<label
							class="labelError" id="codeError" style="display:block">${message }</label>
					</c:if>
					&nbsp;&nbsp;&nbsp;<label class="labelError" id="codeError"></label><input
						type="text" name="code" id="code" onblur="checkCode('')"
						onfocus="onfocusAction('code')" placeholder="请填写图片框验证码"
						style="width:250px" />&nbsp;&nbsp;<img
						src="../enterprise/createImage.do" width="15%" height="15%"
						alt="验证码" title="点击更换另一张" id="img" onclick="change()">
				</dd>
			</dl>
			<div class="sq-tijiao">
				<input type="submit" value="确认提交" />
			</div>
		</div>
	</form>
	<div class="footer">
		<%@include file="../index/foot.jsp" %>
		<div class="footer-fx">
			<a href="#"><img src="../index/images/icon-fx1.png" /> </a><a href="#"><img
				src="../index/images/icon-fx2.png" /> </a><a href="#"><img
				src="../index/images/icon-fx3.png" /> </a><a href="#"><img
				src="../index/images/icon-fx4.png" /> </a>
		</div>
		<div class="footer-bq">2016 All Rights
			Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>
	</div>
	<div class="ce-nav">
		<a href="#" class="ce1"></a> <a href="#" class="ce2"></a> <a href="#"
			class="ce3"></a>
	</div>

</body>
</html>
