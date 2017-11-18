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
	<script type="text/javascript" src="../index/js/qrcode.js"></script>
		<script type="text/javascript">
			var timer;
			timer = setTimeout(function() {
				location.href = "../order/showOrder2.do?order_id=${order.id }";
			}, 3600000);
			function getPayWay(way){
				if(way==1){
					$("#payWay1").val(1);
				}else if(way==2){
					$("#payWay2").val(2);
				}
			}
			function pay(){
				var v1=	$("#payWay1").val();
				var v2=	$("#payWay2").val();
				if(v1==1&&v2!=2){
					$("#f1").submit();
				}else if(v1!=1&&v2==2){
					$("#qrcode").html("");
					$("#denglu").css("display","block");
						var qrcode = new QRCode(document.getElementById("qrcode"), {
							width : 200,//设置宽高
							height : 200
						});
						qrcode.makeCode('${orderzf.urlCode}');
						var d= window.setInterval(function(){
								$.ajax({
									url : "${pageContext.request.contextPath }/order/checkPay.json",
									data : {"id":'${orderzf.id}'},
									type : "post",
									dataType : "text",
									success : function(data) {
										if(data==3){
											location.href="../order/payFinished.do?id=${orderzf.id}";
										}
									}
								});
						}, 1000);
				}
			}
		</script>
</head>
<body style="background:#f6f8fa;">
	<%@include file="../index/header.jsp"%>
	<ul>
		<li><a href="../index/index.do">首页</a>
		</li>
		<li class="cur"><a href="../car/duanzu.do">短租自驾</a>
		</li>
			<li><a href="../index/zhuanche.jsp">专车</a></li>
		<li><a href="../longRentService/longRentServiceIndex.do">长租</a></li>
		<li><a href="../index/enterprise.jsp">企业租车</a>
		</li>
		<li><a href="../order/showOrder.do">订单查询</a>
		</li>
		<li><a href="../about/index.do?title_id=1">关于我们</a>
		</li>
	</ul>
	</div>
	</div>
	<div class="zhifu">
		<div class="zhifu1">
			<div class="zf1">
				<img src="../index/images/icon-zf1.png" /><em>提交订单成功</em><span>请您在1小时内完成支付，否则本次订单将自动取消。</span><span>应付总价:<i>￥</i><b>${orderzf.total_price
						}</b> </span>
			</div>
			<div class="zf2">订单号：${orderzf.order_num } |
				租车人：${orderzf.user.username } | 租期：${orderzf.days }天</div>
		</div>
		<div class="dd2">
			<ul>
				<li>
					<div class="dd2-a">
						<dl>
							<dt>
								<img src="../${orderzf.car.path }">
							</dt>
							<dd>
								<span>${orderzf.car.name }</span>
								<p>${orderzf.car.place }厢｜${orderzf.car.autoNum
									}自动｜乘坐${orderzf.car.seatNum }人</p>
							</dd>
						</dl>
					</div>
					<div class="dd2-b">
						<p>取车：${orderzf.buyAddr }</p>
						<span><fmt:formatDate value="${orderzf.buyTime }"
								pattern="yyyy-MM-dd HH:mm:ss" /> </span>
						<p>还车：${orderzf.sendAddr }</p>
						<span><fmt:formatDate value="${orderzf.sendTime }"
								pattern="yyyy-MM-dd HH:mm:ss" /> </span>
					</div>
					<div class="dd2-c">租期${orderzf.days }天</div>
				</li>
			</ul>
		</div>
	
		<div class="denglu" style="display:none;"id="denglu">
				<div class="denglu-nr"style="height:200px;width:250px">
					<a href="javascript:;" class="off">&nbsp;</a>
						<div id="qrcode" title="" style="position:relative; left:70%; top:60%; width:400px; height:300px; margin-left:-150px; margin-top:-100px;">
	</div>
				</div>
			</div>
		<div class="dd3">付款方式</div>
		<div class="zf4">
			<a href="javascript:;" onclick="getPayWay(1)"><dl>
					<dt >&nbsp;</dt>
					<dd>
						<img src="../index/images/zf-zfb.png" />
					</dd>
				</dl> </a> <a href="javascript:;" onclick="getPayWay(2)"><dl>
					<dt>&nbsp;</dt>
					<dd>
						<img src="../index/images/zf-wx.png" />
					</dd>
				</dl> </a>
		</div>
		<input type="hidden" id="payWay1" /> 
		<input type="hidden" id="payWay2" />
		<div class="zf5">
			<form action="../index/alipayapi.jsp" id="f1" method="post">
				<input type="hidden" name="order_num" value="${orderzf.order_num }" />
				<input type="hidden" name="total_price"
					value="${orderzf.total_price }" /> <input type="hidden" name="tel"
					value="${orderzf.user.tel }" /> <a href="#" onclick="pay()">支付订单</a>
			</form>
		</div>
	</div>
	<%@include file="../index/foot.jsp"%>
	<div class="footer-fx">
		<a href="#"><img src="../index/images/icon-fx1.png" /> </a><a
			href="#"><img src="../index/images/icon-fx2.png" /> </a><a href="#"><img
			src="../index/images/icon-fx3.png" /> </a><a href="#"><img
			src="../index/images/icon-fx4.png" /> </a>
	</div>
	<div class="footer-bq">2016 All Rights
		Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>
	<div class="ce-nav">
		<a href="#" class="ce1"></a> <a href="#" class="ce2"></a> <a href="#"
			class="ce3"></a>
	</div>
</body>
</html>
