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
<script src="../mobile/js/js.js"> </script>
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
 
function check(){
	if(checkOrder()&&checkInvoice()&&checkReceiver()&&checkTel()&&checkReceiver_address()){
		return true;
	}
	return false;
}
function checkOrder(){
	var bool=true;
	var value=$("#order").val();
	if(!value){
		bool=false;
		newaddress.ealert("请选择订单!");
	}
	return bool;
}
function checkInvoice(){
	var bool=true;
	var value=$("#invoice").val();
	if(!value){
		bool=false;
		newaddress.ealert("发票抬头不能为空!");
	}
	return bool;
}
function checkReceiver(){
	var bool=true;
	var value=$("#receiver").val();
	if(!value){
		bool=false;
		newaddress.ealert("联系人不能为空!");
	}
	return bool;
}
function checkTel(){
	var bool=true;
	var reg=/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
	var value=$("#tel").val();
	if(!reg.test(value)){
		bool=false;
		newaddress.ealert("手机号格式错误!");
	}
	return bool;
}
function checkReceiver_address(){
	var bool=true;
	var value=$("#receiver_address").val();
	if(!value){
		bool=false;
		newaddress.ealert("发票地址不能为空!");
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
	<form action="../order/addFapiao.do" method="post" onsubmit="return check()">
	<div id="all">
		<div class="orderb"
			style="border-top:1px solid #e8e8e8; margin-top:0.24em;">
			<dl>
			<c:if test="${!empty msg }">
			<script type="text/javascript">
			newaddress.ealert('${msg}')
</script>
			</c:if>
			<c:if test="${!empty msg1 }">
			<script type="text/javascript">
			newaddress.ealert('${msg1}');
			setTimeout(function(){
				location.href="../order/getMyFaPiao.do";
			},1000);
			
</script>
			</c:if>
				<select name="id" id="order"><option value="">请选择订单</option>
					<c:forEach items="${orders }" var="o">
						<option value="${o.id }">${o.order_num }</option>
					</c:forEach>
				</select>
			</dl>
			<dl>
				<dt>发票抬头:</dt>
				<dd>
					<input type="text" name="invoice" id="invoice"
						placeholder="请输入发票抬头">
				</dd>
			</dl>
			<dl>
				<dt>联系人:</dt>
				<dd>
					<input type="text" name="receiver" id="receiver"
						placeholder="请输入姓名">
				</dd>
			</dl>
			<dl>
				<dt>联系方式:</dt>
				<dd>
					<input type="text" name="tel" id="tel" placeholder="请输入手机号码">
				</dd>
			</dl>
			<dl>
				<dt>发票地址:</dt>
				<dd>
					<input type="text" name="receiver_address" id="receiver_address"
						placeholder="请输入寄送发票地址">
				</dd>
			</dl>
		</div>
		<div class="baocun">
			<input type="submit" value="保存">
			<!--<a href="#">保存</a>-->
		</div>
	</div>
	</form>
</body>
</html>
