<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	$(function(){
		if(${m==1}){
			newaddress.ealert("该车已售完,请选择其他车辆!");
		}
	});
	</script>
</head>
<body>
<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
	<div id="all">
		<div class="order-tit">
			<p>订单状态：待支付</p>
		</div>
		<div class="ordera">
			<div class="order1">
				<dl>
					<dt>
						<img src="../${car2.path }">
					</dt>
					<dd>
						<span>${car2.name }</span>
						<p>${car2.place }厢｜${car2.autoNum }自动｜乘坐${car2.seatNum }人</p>
					</dd>
				</dl>
			</div>
			<div class="order2">
				<dl>
					<dt>取车:</dt>
					<dd>${order.buyAddr }</dd>
				</dl>
				<dl>
					<dt>还车:</dt>
					<dd>${order.sendAddr }</dd>
				</dl>
			</div>
			<div class="order3">
				<dl>

					<dd>
						<span>取车时间</span>
						<p>
							<fmt:formatDate value="${order.buyTime }" pattern="MM-dd" />
						</p>
						<span><fmt:formatDate value="${order.buyTime }"
								pattern="E HH:mm" /> </span>
					</dd>
					<dt>
						<p>${order.days }天</p>
					</dt>
					<dd>
						<span>还车时间</span>
						<p>
							<fmt:formatDate value="${order.sendTime }" pattern="MM-dd" />
						</p>
						<span><fmt:formatDate value="${order.sendTime }"
								pattern="E HH:mm" /> </span>
					</dd>
				</dl>
			</div>
		</div>
		<div class="tit">
			<p>费用明细</p>
		</div>
		<div class="orderb">
			<dl>
				<dt>车辆租赁费:</dt>
				<dd>
					<b><span>￥</span>${order.basic_rent_price }</b>
				</dd>
			</dl>
			<dl>
				<dt>保险费:</dt>
				<dd>
					<b><span>￥</span>${order.basic_insure_price }</b>
				</dd>
			</dl>
			<dl>
				<dt>手续费:</dt>
				<dd>
					<b><span>￥</span>${order.procedure_price }</b>
				</dd>
			</dl>
			<dl>
				<dt>合计费用:</dt>
				<dd>
					<b><span>￥</span>${order.total_price }</b>
				</dd>
			</dl>
		</div>
		<div class="tit">
			<p>发票信息</p>
		</div>
		<div class="orderb">
			<dl>
				<dt>发票抬头:</dt>
				<dd>
					<p>${order.invoice }</p>
				</dd>
			</dl>
			<dl>
				<dt>联系人:</dt>
				<dd>
					<p>${order.receiver }</p>
				</dd>
			</dl>
			<dl>
				<dt>联系方式:</dt>
				<dd>
					<p>${order.tel }</p>
				</dd>
			</dl>
			<dl>
				<dt>发票地址:</dt>
				<dd>
					<p>${order.receiver_address }</p>
				</dd>
			</dl>
		</div>
		<div class="btn-a">
			<a
				href="javascript:location.href='../order/showOrderM2.do?order_id=${order.id }'"
				class="btn-a1">取消订单</a> <a
				href="javascript:location.href='../order/zhifuM.do?order_id=${order.id }';"
				class="btn-a2">支付订单</a>
		</div>
	</div>
</body>
</html>
