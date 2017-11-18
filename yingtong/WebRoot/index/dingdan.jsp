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
<link href="css/public.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/orderb.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/js.js"></script>
<script type="text/javascript">
function checkInvoice(){
	var bool=true;
	var value=$("#invoice").val();
	if(!value){
		bool=false;
		alert("发票抬头不能为空!");
	}
	return bool;
}
function checkReceiver(){
	var bool=true;
	var value=$("#receiver").val();
	if(!value){
		bool=false;
		alert("收件人不能为空!");
	}
	return bool;
}
function checkTel(){
	var bool=true;
	var reg=/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
	var value=$("#tel").val();
	if(!reg.test(value)){
		bool=false;
		alert("手机号格式错误!");
	}
	return bool;
}
function checkReceiver_address(){
	var bool=true;
	var value=$("#receiver_address").val();
	if(!value){
		bool=false;
		alert("发票地址不能为空!");
	}
	return bool;
}
function check(){
	if(!$("#orderb").hasClass('c')){
		if(checkInvoice()&&checkReceiver()&&checkTel()&&checkReceiver_address()){
		return true;
		}else{
			return false;
		}
	}
	return true;
}
</script>
</head>
<body style="background:#f6f8fa;">
	<%@include file="../index/header.jsp"%>
	<ul>
		<li><a href="../index/index.do">首页</a></li>
		<li><a href="../car/duanzu.do">短租自驾</a></li>
			<li><a href="../index/zhuanche.jsp">专车</a></li>
		<li><a href="../longRentService/longRentServiceIndex.do">长租</a>
		</li>
		<li><a href="../index/enterprise.jsp">企业租车</a></li>
		<li class="cur"><a href="../order/showOrder.do">订单查询</a></li>
		<li><a href="../about/index.do?title_id=1">关于我们</a></li>
	</ul>
	</div>
	</div>
	<form action="${pageContext.request.contextPath}/order/addOrder.do"
		method="post" onsubmit="return check()">
		<input type="hidden" name="car_id" value="${car.id }" />
		<input type="hidden" name="buyAddr" value="${selectCar.buyAddr }"/>
		<input type="hidden" name="sendAddr" value="${selectCar.sendAddr }"/>
		<div class="dingdan">
			<div class="dd-left">
				<div class="dd1">
					<span>基本信息</span>
				</div>
				<div class="dd2">
					<ul>
						<li>
							<div class="dd2-a">
								<dl>
									<dt>
										<img src="../${car.path }">
									</dt>
									<dd>
										<span>${car.name }</span>
										<p>${car.place }厢｜${car.autoNum }自动｜乘坐${car.seatNum }人</p>
									</dd>
								</dl>
							</div>
							<div class="dd2-b">
								<p>取车：${selectCar.buyAddr }</p>
								<input type="hidden" name="buyTime"
									value=" <fmt:formatDate value="${selectCar.buyTime }" pattern="yyyy-MM-dd HH:mm:ss" /> " />
								<span> <fmt:formatDate value="${selectCar.buyTime }"
										pattern="yyyy-MM-dd HH:mm:ss" /> </span>
								<p>还车：${selectCar.sendAddr }</p>
								<input type="hidden" name="sendTime"
									value=" <fmt:formatDate value="${selectCar.sendTime }"
										pattern="yyyy-MM-dd HH:mm:ss" />" />
								<span> <fmt:formatDate value="${selectCar.sendTime }"
										pattern="yyyy-MM-dd HH:mm:ss" /> </span>
							</div> <input type="hidden" name="status" value="1" />
							<div class="dd2-c">
								租期：
								<c:choose>
									<c:when test="${selectCar.days gt 0 }">${selectCar.days }</c:when>
									<c:otherwise>${car.days }</c:otherwise>
								</c:choose>
								天
							</div>
						</li>
					</ul>
				</div>
				<div class="dd3">费用明细</div>
				<div class="dd4">
					<dl>

						<dt>
							基本租车费<span>(已优惠${car.original_price-car.favourable_price
								}元)(${car.favourable_price}元*<c:choose>
									<c:when test="${selectCar.days gt 0 }">${selectCar.days }	<input
											type="hidden" name="days" value="${selectCar.days }" />
									</c:when>
									<c:otherwise>${car.days }	<input type="hidden"
											name="days" value="${car.days }" />
									</c:otherwise>
								</c:choose>天)</span>
						</dt>
						<dd>
							<c:choose>
								<c:when test="${selectCar.days gt 0 }">${car.favourable_price*selectCar.days }<input
										type="hidden" name="basic_rent_price"
										value="${car.favourable_price*selectCar.days}" />
								</c:when>
								<c:otherwise>${car.favourable_price*car.days }<input
										type="hidden" name="basic_rent_price"
										value="${car.favourable_price*car.days }" />
								</c:otherwise>
							</c:choose>
							元
						</dd>
					</dl>
					<dl>
						<dt>
							基本保险费<span>(30元＊<c:choose>
									<c:when test="${selectCar.days gt 0 }">${selectCar.days }</c:when>
									<c:otherwise>${car.days }</c:otherwise>
								</c:choose>天)</span>
						</dt>
						<dd>
							<c:choose>
								<c:when test="${selectCar.days gt 0 }">${30*selectCar.days }<input
										type="hidden" name="basic_insure_price"
										value="${30*selectCar.days }" />
								</c:when>
								<c:otherwise>${30*car.days }<input type="hidden"
										name="basic_insure_price" value="${30*car.days }" />
								</c:otherwise>
							</c:choose>
							元
						</dd>
					</dl>
					<dl>
						<dt>手续费</dt>
						<dd>
							20元 <input type="hidden" name="procedure_price" value="20" />
						</dd>

					</dl>
				</div>
				<div class="dd3">
					<span>需要发票</span><a href="javascript:;"><i>&nbsp;</i>
					</a>
				</div>
				<div class="dd5 c"id="orderb" >
					<dl>
						<dt>发票抬头：</dt>
						<dd>
							<input type="text" name="invoice" id="invoice"
								placeholder="个人或单位" />
						</dd>
					</dl>
					<dl>
						<dt>收件人：</dt>
						<dd>
							<input type="text" name="receiver" id="receiver"
								placeholder="收件人姓名" />
						</dd>
					</dl>
					<dl>
						<dt>手机号码：</dt>
						<dd>
							<input type="text" name="tel" id="tel" onblur="checkTel()"
								placeholder="请填写手机号码" />
						</dd>
					</dl>
					<dl>
						<dt>收件地址：</dt>
						<dd>
							<input type="text" name="receiver_address" id="receiver_address"
								placeholder="请填写详细地址" />
						</dd>
					</dl>
				</div>
				<div class="dd6">
					<span>订单总价：</span>
					<p>
						<c:choose>
							<c:when test="${selectCar.days gt 0 }">
								<input type="hidden" name="total_price"
									value="<fmt:formatNumber type="number" value="${car.favourable_price*selectCar.days+30*selectCar.days+20 }" maxFractionDigits="2"  groupingUsed="false"/>" /> ￥<b><fmt:formatNumber type="number" value="${car.favourable_price*selectCar.days+30*selectCar.days+20 }" groupingUsed="false"  maxFractionDigits="2"/></b>
							</c:when>
							<c:otherwise>
								<input type="hidden" name="total_price"
									value="	<fmt:formatNumber type="number" value="${car.favourable_price*car.days+30*car.days+20 }" groupingUsed="false" maxFractionDigits="2"/>" /> ￥<b><fmt:formatNumber type="number" value="${car.favourable_price*car.days+30*car.days+20 }" groupingUsed="false" maxFractionDigits="2"/></b>
							</c:otherwise>
						</c:choose>

					</p>
					<input type="submit" value="提交订单" />
				</div>
			</div>
			<div class="dd-right">
				<div class="tg-guize">
					<dl>
						<dt>退改规则</dt>
						<dd>
							<p>超时计费：</p>
							<p>1.非经盈通租车书面同意，本车不得续租。到期未还车辆，除需正常支付租金外，您还需按超期部分租金的300%支付违约金，并且大方租车有权强行收回车辆。</p>
							<p>2.超时4小时以上，按一天计费。不足4小时（含），按每小时50元收费。</p>
						</dd>
						<dd>
							<p>温馨提示：</p>
							<p>1.本订单不限公里数, 超时费 30 元/小时, 预授权3000元(可退), 违章押金 2000元(可退)</p>
							<p>2.本订单仅为客户租车预约登记，提交该订单后，客户需到门店办理具体租车手续，具体权利义务以签署的书面合同为准。</p>
						</dd>
					</dl>
				</div>
			</div>

		</div>
	</form>
	<%@include file="../index/foot.jsp"%>
	<div class="footer-fx">
		<a href="#"><img src="images/icon-fx1.png" /> </a><a href="#"><img
			src="images/icon-fx2.png" /> </a><a href="#"><img
			src="images/icon-fx3.png" /> </a><a href="#"><img
			src="images/icon-fx4.png" /> </a>
	</div>
	<div class="footer-bq">2016 All Rights
		Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>

	<div class="ce-nav">
		<a href="#" class="ce1"></a> <a href="#" class="ce2"></a> <a href="#"
			class="ce3"></a>
	</div>

</body>
</html>
