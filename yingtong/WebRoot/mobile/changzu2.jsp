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
<link rel="stylesheet" type="text/css" href="../mobile/css/weixinshow.css">
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
function checkName(){
	var bool=true;
	var value=$("#name").val();
	if(!value){
		newaddress.ealert("公司名称不能为空");
		bool=false;
	}
	return bool;
}
function checkRelation(){
	var bool=true;
	var value=$("#relation_person").val();
	if(!value){
		newaddress.ealert("联系人不能为空");
		bool=false;
	}
	return bool;
}

function checkTel(){
	var bool=true;
	var value=$("#tel").val();
	var tels=$("#tels").val();
	var telsArr=tels.substring(1,tels.length-1).split(",");
	for(var i=0;i<telsArr.length;i++){
		if(value==telsArr[i].trim()){
			newaddress.ealert("该手机号已存在!");
			bool=false;
			break;
		}
	}
	var reg=/^1[3|4|5|7|8]\d{9}$/;
	 if(value&&!reg.test(value)){
		 newaddress.ealert("格式错误,请输入正确的手机号！");
		 bool=false;
	 } 
	return bool;
}
function checkEmail(){
	var bool=true;
	var value=$("#email").val();
	var reg= /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(value&&!reg.test(value)){
		newaddress.ealert("邮箱格式不正确!");
		bool=false;
	}
		return bool;
}
 function check(){
	 if(checkName()&&checkRelation()&&checkTel()&&checkEmail()){
		 return true;
	 }
	 return false;
 }
</script>
</head>
<body>
	<div id="all">
	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
		<form action="../longRentService/addlongRentService2.do" method="post"
			onsubmit="return check()">
			<input type="hidden" id="tels" value=${tels }>
			<DIV class="error none" style="text-align: center">
				<DIV class="warnMsg" style="text-align: center"></DIV>
			</DIV>
			<div class="xinxi">
				<dl>
					<dt>公司名称:</dt>
					<dd>
						<input type="text" name="name" id="name" placeholder="请输入公司名称">
					</dd>
				</dl>
				<dl>
					<dt>联系人:</dt>
					<dd>
						<input type="text" name="relation_person" id="relation_person"
							placeholder="请填写联系人姓名">
					</dd>
				</dl>
				<dl>
					<dt>联系方式:</dt>
					<dd>
						<input type="text" name="tel" id="tel" placeholder="请填写联系电话">
					</dd>
				</dl>
				<dl>
					<dt>E-mail:</dt>
					<dd>
						<input type="text" placeholder="请填写电子邮箱" name="email" id="email">
					</dd>
				</dl>
			</div>
			<div class="btn-input2">
				<input type="submit" value="确定">
			</div>
		</form>
	</div>
</body>
</html>
