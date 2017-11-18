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
<link rel="stylesheet" type="text/css" href="../mobile/css/orderb.css">
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
function send(){
	$("#f").submit();
}
</script>
</head>
<body>
	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
	<form action="../order/addOrder1.do" method="post" id="f"
		onsubmit="return check()">
		<input type="hidden" name="car_id" value="${rent_car.id }">
		<input type="hidden" name="status" value="1" />
		<input type="hidden" name="buyAddr" value="${selectCarInfo1.buyAddr }">
		<input type="hidden" name="sendAddr" value="${selectCarInfo1.sendAddr }">
		<div id="all">
			<div class="ordera">
				<div class="order1">
					<dl>
						<dt>
							<img src="../${rent_car.path }">
						</dt>
						<dd>
							<span>${rent_car.name }</span>
							<p>${rent_car.place }厢｜${rent_car.autoNum
								}自动｜乘坐${rent_car.seatNum }人</p>
						</dd>
					</dl>
				</div>
				<div class="order2">
					<dl>
						<dt>取车:</dt>
						<dd>${selectCarInfo1.buyAddr }</dd>
					</dl>
					<dl>
						<dt>还车:</dt>
						<dd>${selectCarInfo1.sendAddr }</dd>
					</dl>
				</div>
				<div class="order3">
					<dl>
						<dd>
							<span>取车时间</span>
							<p>
								<fmt:formatDate value="${selectCarInfo1.buyTime }"
									pattern="MM-dd" />
							</p>
							<span><fmt:formatDate value="${selectCarInfo1.buyTime }"
									pattern="E  HH:mm" /> </span> <input type="hidden" name="buyTime"
								value="<fmt:formatDate value="${selectCarInfo1.buyTime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
						</dd>
						<dt>
							<p>${selectCarInfo1.days }天</p>
							<input type="hidden" name="days" value="${selectCarInfo1.days }">
						</dt>
						<dd>
							<span>还车时间</span>
							<p>
								<fmt:formatDate value="${selectCarInfo1.sendTime }"
									pattern="MM-dd" />
							</p>
							<span><fmt:formatDate value="${selectCarInfo1.sendTime }"
									pattern="E  HH:mm" /> </span> <input type="hidden" name="sendTime"
								value="<fmt:formatDate value="${selectCarInfo1.sendTime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
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
						<b><span>￥</span> <fmt:formatNumber
								value="${rent_car.favourable_price*selectCarInfo1.days }"
								groupingUsed="false" maxFractionDigits="2" /> </b> <input
							type="hidden" name="basic_rent_price"
							value="<fmt:formatNumber
								value="${rent_car.favourable_price*selectCarInfo1.days }"
								groupingUsed="false" maxFractionDigits="2" />">
					</dd>
				</dl>
				<dl>
					<dt>保险费:</dt>
					<dd>
						<b><span>￥</span>${selectCarInfo1.days*30 }</b> <input
							type="hidden" name="basic_insure_price"
							value="${selectCarInfo1.days*30 }">
					</dd>
				</dl>
				<dl>
					<dt>手续费:</dt>
					<dd>
						<b><span>￥</span>20</b> <input type="hidden"
							name="procedure_price" value="20" />
					</dd>
				</dl>
				<dl>
					<dt>合计费用:</dt>
					<dd>
						<b><span>￥</span> <fmt:formatNumber
								value="${rent_car.favourable_price*selectCarInfo1.days+selectCarInfo1.days*30+20 }"
								groupingUsed="false" maxFractionDigits="2" /> </b> <input
							type="hidden" name="total_price"
							value="	<fmt:formatNumber
								value="${rent_car.favourable_price*selectCarInfo1.days+selectCarInfo1.days*30+20 }"
								groupingUsed="false" maxFractionDigits="2" />">
					</dd>
				</dl>
			</div>
			 <div class="fp-tit"><p>开具发票 </p><a href="javascript:;" ><i>&nbsp;</i></a></div>
			<div class="orderb c" id="orderb" >
				<dl>
					<dt>发票抬头:</dt>
					<dd>
						<input type="text" placeholder="请输入发票抬头" name="invoice"
							id="invoice">
					</dd>
				</dl>
				<dl>
					<dt>联系人:</dt>
					<dd>
						<input type="text" placeholder="请输入姓名" name="receiver"
							id="receiver">
					</dd>
				</dl>
				<dl>
					<dt>联系方式:</dt>
					<dd>
						<input type="text" placeholder="请输入手机号码" name="tel" id="tel"
							>
					</dd>
				</dl>
				<dl>
					<dt>发票地址:</dt>
					<dd>
						<input type="text" placeholder="请输入寄送发票地址" name="receiver_address"
							id="receiver_address">
					</dd>
				</dl>
			</div>
			<div class="qd-bottom">
				<div class="qd-bottomnr">
					<p>
						合计金额:<span><i>￥</i>
						<fmt:formatNumber
								value="${rent_car.favourable_price*selectCarInfo1.days+selectCarInfo1.days*30+20 }"
								groupingUsed="false" maxFractionDigits="2" />
						</span>
					</p>
					<a href="#" onclick="send()">确定</a>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
