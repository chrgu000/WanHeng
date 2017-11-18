<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>聚乡村</title>

<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" /> 
<meta content="black" name="apple-mobile-web-app-status-bar-style" /> 
<meta content="telephone=no" name="format-detection" />

<link rel="stylesheet" type="text/css" href="../mobile/css/css.css">

<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/fastclick.js"></script>
</head>
<body>

<div id="all">
	<div class="order-all">
        <div class="order-show1"><p>支付成功</p></div>
        <div class="order-show2">
            <h5>${order.product.title }</h5>
            <p>入住<fmt:formatDate value="${order.beginTime }" var="MM月dd日"/>，退房<fmt:formatDate value="${order.endTime }" var="MM月dd日"/>，共${order.days }晚</p>
            <p>订单数量：${order.number }</p>
            <span>总价：￥${order.total_price }</span>
        </div>
        <div class="order-show3">
            <p>入住人姓名：${order.user.nickname }</p>
            <p>手机号码：${order.user.tel }</p>
        </div>
    </div>
    </div>
<script src="../mobile/js/js.js"></script>
</body>
</html>
