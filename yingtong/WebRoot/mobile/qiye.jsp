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

<link rel="stylesheet" type="text/css" href="../mobile/css/css.css">
<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../mobile/css/weixinshow.css">
		<script src="../mobile/js/js.js"></script>
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
function checkAccount(){
var bool=true;
var value=$("#account").val().trim();
if(!value){
 newaddress.ealert("账户名不能为空!");
 bool=false;
}
if(value.length>16){
 newaddress.ealert("长度不超过16位英文字母或数字!");
 bool=false;
}
return bool;
}
function checkName(){
var bool=true;
var value=$("#name").val().trim();
if(!value){
newaddress.ealert("公司名称不能为空!");
 bool=false;
}
return bool;
}
function checkAddress(){
var bool=true;
var value=$("#address").val().trim();
if(!value){
newaddress.ealert("公司地址不能为空!");
 bool=false;
}
return bool;
}
function checkRelationPerson(){
var bool=true;
var value=$("#relation_person").val().trim();
if(!value){
 newaddress.ealert("联系人不能为空!");
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
if(value&&!(reg.test(value))){
newaddress.ealert("联系电话格式不正确!");
 bool=false;
}
for(var i=0;i<telsArr.length;i++){
	if(telsArr[i].trim()==value){
		newaddress.ealert("联系电话已存在!");
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
 newaddress.ealert("邮箱格式不正确!");
 bool=false;
 }
return bool;
}
function check(){
if(checkAccount()&&checkName()&&checkAddress()&&checkRelationPerson()&&checktel()&&checkEmail()){
return true;
}
return false;
}
</script>
</head>
<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
<body>
	<form action="../enterprise/addEnterprise1.do" method="post" onsubmit="return check()">
	<input type="hidden" id="tels" value="${tels }">
		<div id="all">
			<div class="qi-zu">
				<img src="../mobile/images/qizu.png">
			</div>
			<div class="qi-zutit">
				<p>
					四大优势<i></i>
				</p>
			</div>
			<div class="qizu-list">
				<a href="#">
					<dl>
						<dt>
							<img src="../mobile/images/qiye01.png">
						</dt>
						<dd>
							<span>价格优惠</span>
							<p>
								价格比其他平台<br>更有竞争力
							</p>
						</dd>
					</dl> </a> <a href="#">
					<dl>
						<dt>
							<img src="../mobile/images/qiye02.png">
						</dt>
						<dd>
							<span>提车快</span>
							<p>
								当天办理<br>当天提车
							</p>
						</dd>
					</dl> </a> <a href="#">
					<dl>
						<dt>
							<img src="../mobile/images/qiye03.png">
						</dt>
						<dd>
							<span>选择多</span>
							<p>
								新车、二手车<br>皆可办理
							</p>
						</dd>
					</dl> </a> <a href="#">
					<dl>
						<dt>
							<img src="../mobile/images/qiye04.png">
						</dt>
						<dd>
							<span>服务全</span>
							<p>
								原厂质保<br>一周内可退换
							</p>
						</dd>
					</dl> </a>
			</div>
			<div class="qi-zutit">
				<p>
					申请会员<i></i>
				</p>
			</div>
			<div class="qizet-a">
				<p>如果您或您的企业还不是我们的企业用户，请填写如下信息，我们的客服会尽快联系您：</p>
			</div>
			<div class="xinxi" style="margin:0;">
				<dl>
					<dt>企业账户名:</dt>
					<dd>
						<input type="text" name="account" id="account"
							value="${e.account }" placeholder="长度不超过16位英文字母或数字" />
					</dd>
				</dl>
				<dl>
					<dt>公司名称:</dt>
					<dd>
						<input type="text" name="name" id="name" value="${e.name }"
							placeholder="请与营业执照保持一致" />
					</dd>
				</dl>
				<dl>
					<dt>公司地址:</dt>
					<dd>
						<input type="text" name="address" id="address"
							value="${e.address }" placeholder="请填写办公地址" />
					</dd>
				</dl>
				<dl>
					<dt>联系人:</dt>
					<dd>
						<input type="text" name="relation_person" id="relation_person"
							  value="${e.relation_person }" placeholder="请填写联系人姓名" />
					</dd>
				</dl>
				<dl>
					<dt>联系方式:</dt>
					<dd>
						<input type="text" name="tel" id="tel" value="${e.tel }"
							placeholder="请填写联系电话" />
					</dd>
				</dl>
				<dl>
					<dt>E-mail:</dt>
					<dd>
						<input type="text" name="email" id="email" value="${e.email }"
							placeholder="请填写电子邮箱地址" />
					</dd>
				</dl>
			</div>
			<div class="qi-btn">
				<div class="btn-input">
					<input value="提交" type="submit">
				</div>
			</div>
	</form>
	</div>
</body>
</html>
