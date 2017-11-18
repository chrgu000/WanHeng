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
	<%@include file="../index/header.jsp" %>
	<ul>
				<li><a href="../index/index.do">首页</a>
				</li>
				<li  class="cur"><a href="../car/duanzu.do">短租自驾</a>
				</li>
					<li><a href="../index/zhuanche.jsp">专车</a></li>
				<li ><a href="../longRentService/longRentServiceIndex.do">长租</a>
				</li>
				<li><a href="../index/enterprise.jsp">企业租车</a>
				</li>
				<li><a href="../order/showOrder.do">订单查询</a>
				</li>
				<li><a href="../about/index.do?title_id=1">关于我们</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="zhifu" style="padding-bottom:0;">
		<div class="zhifu1" style="background:#F0F0F0;">
			<div class="zf1">
				<em>已取消</em><span>订单总价::<i>￥</i><b>${order2.total_price }</b>
				</span>
			</div>
			<div class="zf2">订单号：${order2.order_num } |
				租车人：${order2.user.username } | 租期：${order2.days }天</div>
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
								<span>${order2.car.name }</span>
								<p>${order2.car.place }厢｜${order2.car.autoNum
									}自动｜乘坐${order2.car.seatNum }人</p>
							</dd>
						</dl>
					</div>
					<div class="dd2-b">
						<p>取车：${order2.buyAddr }</p>
						<span><fmt:formatDate value="${order2.buyTime }"
								pattern="yyyy-MM-dd HH:mm:ss" /> </span>
						<p>还车：${order2.sendAddr }</p>
						<span><fmt:formatDate value="${order2.sendTime }"
								pattern="yyyy-MM-dd HH:mm:ss" /> </span>
					</div>
					<div class="dd2-c">租期${order2.days }天</div></li>
			</ul>
		</div>
		<div class="dd3" style="background:#F6FAFB;">费用明细</div>
		<div class="dd4">
			<dl>
				<dt>
					基本租车费<span>(已优惠70元)</span>
				</dt>
				<dd>${order2.basic_rent_price }元</dd>
			</dl>
			<dl>
				<dt>
					基本保险费<span>(30元＊${order2.days }天)</span>
				</dt>
				<dd>${order2.basic_insure_price }元</dd>
			</dl>
			<dl>
				<dt>手续费</dt>
				<dd>20元</dd>
			</dl>
		</div>
	</div>

	<div class="zf-wbtn">
		<a
			href="javascript:window.opener=null;window.open('','_self');window.close();"
			class="zf-wbtn1">关闭订单</a>
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
