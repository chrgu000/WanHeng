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
<link href="../index/css/orderb.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../index/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="../index/js/js.js"></script>
</head>
<body style="background:#f6f8fa;">
	<%@include file="../index/header.jsp" %>
	<ul>
				<li><a href="../index/index.do">首页</a>
				</li>
				<li class="cur"><a href="../car/duanzu.do">短租自驾</a>
				</li>
					<li><a href="../index/zhuanche.jsp">专车</a></li>
				<li  ><a href="../longRentService/longRentServiceIndex.do">长租</a>
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
	<div class="dingdan">
		<div class="dd-left">
			<div class="zhifu1">
				<c:if test="${check_order.status=='1' }">
					<div class="zf1">
						<em>等待付款</em><span>请您在1小时内完成支付，否则本次订单将自动取消。 </span><span>应付总价:<i>￥</i><b>${check_order.total_price
								}</b> </span>
					</div>
				</c:if>
				<c:if test="${check_order.status=='2' }">
					<div class="zf1">
						<em>已取消</em><span>订单总价:<i>￥</i><b>${check_order.total_price}</b> </span>
					</div>
				</c:if>
				<c:if test="${check_order.status=='3' }">
					<div class="zf1">
						<em>租赁中</em><span>订单总价: <i>￥</i><b>${check_order.total_price
								}</b>
						</span>
					</div>
				</c:if>
				<c:if test="${check_order.status=='4' }">
					<div class="zf1">
						<em>已完成</em><span>订单总价: <i>￥</i><b>${check_order.total_price
								}</b>
						</span>
					</div>
				</c:if>
				<div class="zf2">订单号：${check_order.order_num } |
					租车人：${check_order.user.username } | 租期：${check_order.days }天</div>
			</div>
			<div class="dd1">
				<span>基本信息</span>
			</div>
			<div class="dd2">
				<ul>
					<li>
						<div class="dd2-a">
							<dl>
								<dt>
									<img src="../${check_order.car.path }">
								</dt>
								<dd>
									<span>${check_order.car.name }</span>
									<p>${check_order.car.place }厢｜${check_order.car.autoNum
										}自动｜乘坐${check_order.car.seatNum }人</p>
								</dd>
							</dl>
						</div>
						<div class="dd2-b">
							<p>取车：${check_order.buyAddr }</p>
							<span><fmt:formatDate value="${check_order.buyTime }"
									pattern="yyyy-MM-dd HH:mm" />
							</span>
							<p>还车：${check_order.sendAddr }</p>
							<span><fmt:formatDate value="${check_order.sendTime }"
									pattern="yyyy-MM-dd HH:mm" />
							</span>
						</div>
						<div class="dd2-c">租期${check_order.days }天</div></li>
				</ul>
			</div>
			<div class="dd3">费用明细</div>
			<div class="dd4">
				<dl>
					<dt>
						基本租车费<span>(已优惠${check_order.car.original_price-check_order.car.favourable_price }元)(${check_order.car.favourable_price}元* ${check_order.days }天)</span>
					</dt>
					<dd>${check_order.basic_rent_price }</dd>
				</dl>
				<dl>
					<dt>
						基本保险费<span>(30元＊${check_order.days }天)</span>
					</dt>
					<dd>${check_order.basic_insure_price }元</dd>
				</dl>
				<dl>
					<dt>手续费</dt>
					<dd>20元</dd>
				</dl>
			</div>
			<div class="dd3">
				<span>需要发票</span><a href="javascript:;"><i >&nbsp;</i>
				</a>
			</div>
			<div class="dd5 c" id="orderb"  style="height:350px">
				<dl>
					<dt>发票抬头：</dt>
					<dd>
						<input type="text" value="${check_order.invoice }"
							placeholder="个人或单位" disabled="disabled" />
					</dd>
				</dl>
				<dl>
					<dt>收件人：</dt>
					<dd>
						<input type="text" value="${check_order.receiver }"
							placeholder="收件人姓名" disabled="disabled" />
					</dd>
				</dl>
				<dl>
					<dt>手机号码：</dt>
					<dd>
						<input type="text" value="${check_order.tel }"
							placeholder="请填写手机号码" disabled="disabled" />
					</dd>
				</dl>
				<dl>
					<dt>收件地址：</dt>
					<dd>
						<input type="text" value="${check_order.receiver_address }"
							placeholder="请填写详细地址" disabled="disabled" />
					</dd>
				</dl>
			</div>
		</div>
	</div>
	<c:if test="${check_order.status=='1' }">
		<div class="zf-wbtn">
		 <a
			href="javascript:location.href='../order/showOrder2.do?order_id=${check_order.id }'"
			class="zf-wbtn1">取消订单</a> <a
			href="javascript:location.href='../order/zhifu.do?order_id=${check_order.id }';"
			class="zf-wbtn2">支付订单</a>
	</div>
</c:if>
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
