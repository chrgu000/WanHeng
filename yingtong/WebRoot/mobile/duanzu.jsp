<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盈通汽车</title>

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
<script src="../mobile/js/js.js"></script>
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
	function getPriceId(id) {
		$("#price_id").val(id);
	}
	function getVehicleId(id) {
		$("#vehicle_id").val(id);
	}
	function getBrandId(id) {
		$("#brand_id").val(id);
	}
	function send() {
		$("#send").submit();
	}
</script>
</head>
<body>
	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
	<div id="all">
		<form action="../car/getRentCar.do" method="post" id="send">
			<div class="xuanche">
				<a href="javascript:;" class="cur">按价格</a><a href="javascript:;">按车型</a><a
					href="javascript:;">按品牌</a>
			</div>
			<div class="xx-all">
				<ul>
					<li><a href="javascript:;">价格不限<i class="cur">&nbsp;</i> </a>
					</li>
					<c:forEach items="${prices }" var="p">
						<li><a href="javascript:;" onclick="getPriceId('${p.id}')">${p.price
								}<i>&nbsp;</i> </a>
						</li>
					</c:forEach>
					<input type="hidden" name="price_id" id="price_id">
					<div class="xx-btn">
						<a href="javascript:;" class="xx-btn1">取消</a><a
							href="javascript:;" class="xx-btn2" onclick="send()"><p>确认</p>
						</a>
					</div>
				</ul>
				<ul>
					<li><a href="javascript:;">车型不限<i class="cur">&nbsp;</i> </a>
					</li>
					<c:forEach items="${vehicles}" var="v">
						<li><a href="javascript:;" onclick="getVehicleId('${v.id}')">${v.vehicle_model
								}<i>&nbsp;</i> </a>
						</li>
					</c:forEach>
					<input type="hidden" name="vehicle_id" id="vehicle_id">
					<div class="xx-btn">
						<a href="javascript:;" class="xx-btn1">取消</a><a
							href="javascript:;" class="xx-btn2" onclick="send()"><p>确认</p>
						</a>
					</div>
				</ul>
				<ul>
					<li><a href="javascript:;" class="cur">品牌不限<i class="cur">&nbsp;</i>
					</a>
					</li>
					<c:forEach var="b" items="${brands }">
						<li><a href="javascript:;" onclick="getBrandId('${b.id}')">${b.name
								}<i>&nbsp;</i> </a>
						</li>
					</c:forEach>
					<input type="hidden" name="brand_id" id="brand_id">
					<div class="xx-btn">
						<a href="javascript:;" class="xx-btn1">取消</a><a
							href="javascript:;" class="xx-btn2" onclick="send()"><p>确认</p>
						</a>
					</div>
				</ul>
			</div>
		</form>
		<div class="xuan-list">
			<c:forEach items="${cars2 }" var="c">
				<dl>
					<dt>
						<img src="../${c.path }">
					</dt>
					<dd>
						<div class="xuan1">${c.name }</div>
						<div class="xuan2">${c.place }厢｜${c.autoNum
							}自动｜乘坐${c.seatNum }人</div>
						<div class="xuan3">
							<span>￥<b>${c.favourable_price }</b> </span>/日均
							<del>原价￥${c.original_price }/日均</del>
						</div>
						<c:if test="${c.rent_status=='0' }">
							<a href="#" onclick="sd('${c.id}')">租车</a>
						</c:if>
						<c:if test="${c.rent_status=='1'}">
							<span class="spanyizuche">已租完</span>
						</c:if>
					</dd>
				</dl>
				<script type="text/javascript">
					function sd(id) {
						$("#id").val(id);
						$("#form1").submit();
					}
				</script>
			</c:forEach>
			<form action="../car/rentCar1.do" method="post" id="form1">
				<input type="hidden" name="car_id" id="id">
			</form>
		</div>
	</div>
</body>
</html>
