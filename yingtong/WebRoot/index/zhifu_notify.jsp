<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
</head>
<body style="background:#f6f8fa;">
	<%@include file="../index/header.jsp"%>
	<ul>
		<li><a href="../index/index.do">首页</a></li>
		<li class="cur"><a href="../car/duanzu.do">短租自驾</a></li>
			<li><a href="../index/zhuanche.jsp">专车</a></li>
		<li><a href="../longRentService/longRentServiceIndex.do">长租</a>
		</li>
		<li><a href="../index/enterprise.jsp">企业租车</a></li>
		<li><a href="../order/showOrder.do">订单查询</a></li>
		<li><a href="../about/index.do?title_id=1">关于我们</a></li>
	</ul>
	</div>
	</div>
	<div class="zhifu">
		<div class="zhifu1">
			<div class="zf1">
				<img src="../index/images/icon-zf1.png" /><em>支付订单成功</em><span>应付总价:<i>￥</i><b>${order6.total_price
						}</b>
				</span>
			</div>
			<div class="zf2">订单号：${order6.order_num } |
				租车人：${order6.user.username } | 租期：${order6.days }天</div>
		</div>
		<div class="dd2">
			<ul>
				<li>
					<div class="dd2-a">
						<dl>
							<dt>
								<img src="../index/images/cx01.jpg">
							</dt>
							<dd>
								<span>${order6.car.name }</span>
								<p>${order6.car.place }厢｜${order6.car.autoNum
									}自动｜乘坐${order6.car.seatNum }人</p>
							</dd>
						</dl>
					</div>
					<div class="dd2-b">
						<p>取车：${order6.buyAddr }</p>
						<span><fmt:formatDate value="${order6.buyTime }"
								pattern="yyyy-MM-dd HH:mm:ss" /> </span>
						<p>还车：${order6.sendAddr }</p>
						<span><fmt:formatDate value="${order6.sendTime }"
								pattern="yyyy-MM-dd HH:mm:ss" /> </span>
					</div>
					<div class="dd2-c">租期${order6.days }天</div></li>
			</ul>
		</div>
		<div class="dd3">付款方式</div>
		<div class="zf4">
			<a href="javascript:;"><dl>
					<dt class="cur">&nbsp;</dt>
					<dd>
						<img src="../index/images/zf-zfb.png" />
					</dd>
				</dl>
			</a> <a href="javascript:;"><dl>
					<dt>&nbsp;</dt>
					<dd>
						<img src="../index/images/zf-wx.png" />
					</dd>
				</dl>
			</a>
		</div>
	</div>
	<div class="zf-tan">
	<dl>
    	<dt><span>支付提示</span><a href="javascript:;"></a></dt>
        <dd>
        	<div class="zf-div2"><p>支付成功，查看订单</p><a href="../order/checkOrder.do?order_id=${order6.id }&status=3" class="zf-btn2">查看订单</a></div>
        </dd>
    </dl>
</div>
	<%@include file="../index/foot.jsp" %>
	<div class="footer-fx">
		<a href="#"><img src="../index/images/icon-fx1.png" />
		</a><a href="#"><img src="../index/images/icon-fx2.png" />
		</a><a href="#"><img src="../index/images/icon-fx3.png" />
		</a><a href="#"><img src="../index/images/icon-fx4.png" />
		</a>
	</div>
	<div class="footer-bq">2016 All Rights
		Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>
	<div class="ce-nav">
		<a href="#" class="ce1"></a> <a href="#" class="ce2"></a> <a href="#"
			class="ce3"></a>
	</div>
</body>
</html>
