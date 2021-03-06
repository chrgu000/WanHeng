<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>林总</title>

<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
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
function check(){
	var username=$("#username").val();
	if(!username){
		newaddress.ealert("用户名不能为空!")
		return false;
	}
	var reg = /^1[3|4|5|7|8]\d{9}$/;
	var value = $("#tel").val();
	if (!reg.test(value)) {
		newaddress.ealert("手机号格式不正确");
		return false;
	}
	var address=$("#address").val();
	if(!address){
		newaddress.ealert("收件地址不能为空!");
		return false;
	}
	return true;
}
function submit(){
	$("#f").submit();
}
</script>
</head>
<body>
	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
<form action="../user/updateAddress.do" method="post" onsubmit="return check()"id="f">
<div id="all">
	<div class="shuru">
	<input type="hidden" name="id" value="${address.id }" />
		<input type="hidden" name="user_id" value="${user.id }" />
    	<dl><input type="text" placeholder="输入姓名" name="username"  id="username"value="${address.username }"></dl>
    	<dl><input type="text" placeholder="输入手机号码" name="tel" id="tel" value="${address.tel }"></dl>
    	
    	<dl><select id="s_province" name="s_province">
    	<option value="${address.s_province }">${address.s_province }</option>
    	</select><select id="s_city" name="s_city" >
    		<option value="${address.s_city }">${address.s_city }</option>
    	</select><select id="s_county" name="s_county">
    		<option value="${address.s_county}">${address.s_county }</option>
    	</select></dl>
        <dl><textarea placeholder="详细地址" name="address" id="address">${address.address }</textarea></dl>
    </div>
    <div class="baocun"><a href="#" onclick="submit()">保存</a></div>
</div>
</form>
<script class="resources library" src="js/area.js" type="text/javascript"></script>
<script type="text/javascript">_init_area();</script>
<script type="text/javascript">
var Gid  = document.getElementById ;
var showArea = function(){
	$("#s_province").html("");
	$("#s_city").html("");
	$("#s_county").html("");
	Gid('show').innerHTML = "<h3>省" + Gid('s_province').value + " - 市" + 	
	Gid('s_city').value + " - 县/区" + 
	Gid('s_county').value + "</h3>"
							}
Gid('s_county').setAttribute('onchange','showArea()');
</script>
<script src="../mobile/js/js.js"></script>
</body>
</html>
