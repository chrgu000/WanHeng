<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>租车</title>

<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" /> 
<meta content="black" name="apple-mobile-web-app-status-bar-style" /> 
<meta content="telephone=no" name="format-detection" />
<link rel="stylesheet" type="text/css"
	href="../mobile/css/weixinshow.css">
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
</script>
</head>
<%@ include file="../mobile/login_chk.jsp"%>
<body>
<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
	<c:if test="${!empty msg }">
	<script type="text/javascript">
	newaddress.ealert('${msg}');
</script>
	</c:if>
<div id="all">
    <div class="orderb" style="border-top:1px solid #e8e8e8; margin-top:0.24em;">
    <c:forEach items="${orders }" var="order">
    	<dl><dt> 订单编号:</dt><dd><p>${order.order_num }</p></dd></dl>
    	<dl><dt>发票抬头:</dt><dd><p>${order.invoice }</p></dd></dl>
    	<dl><dt>联系人:</dt><dd><p>${order.receiver }</p></dd></dl>
    	<dl><dt>联系方式:</dt><dd><p>${order.tel }</p></dd></dl>
    	<dl><dt>发票地址:</dt><dd><p>${order.receiver_address }</p></dd></dl>
    	</c:forEach>
    </div>
    <div class="baocun"><input type="button" value="新增发票信息" onclick="addfapiao()"><!--<a href="#">新增发票信息</a>--></div>
    <script type="text/javascript">
    function addfapiao(){
    	location.href="../order/toAddFaPiao.do";
    }
    </script>
</div>
<script src="js/js.js"></script>
</body>
</html>
