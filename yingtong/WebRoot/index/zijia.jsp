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
<script type="text/javascript" src="../index/jedate/jedate.js"></script>
<script type="text/javascript">
	function getVehicleId(id, index, vrow) {
		$("#vehicle_id").val(id);
		$("#cur1").removeClass("cur");
		for ( var i = 0; i <= vrow; i++) {
			if (i != index) {
				$("#vehicle" + i).removeClass("cur");
			} else {
				$("#vehicle" + i).addClass("cur");
			}
		}
	}
	function removeVrow(vrow) {
		$("#cur1").addClass("cur");
		$("#vehicle_id").val("");
		for ( var i = 0; i < vrow; i++) {
			$("#vehicle" + i).removeClass("cur");
		}
	}
	function getBrandId(id, index, brow) {
		$("#brand_id").val(id);
		$("#cur2").removeClass("cur");
		for ( var i = 0; i <= brow; i++) {
			if (i != index) {
				$("#brand" + i).removeClass("cur");
			} else {
				$("#brand" + i).addClass("cur");
			}
		}
	}
	function removeBrow(brow) {
		$("#cur2").addClass("cur");
		$("#brand_id").val("");
		for ( var i = 0; i < brow; i++) {
			$("#brand" + i).removeClass("cur");
		}
	}
	function getPriceId(id, index, prow) {
		$("#price_id").val(id);
		$("#cur3").removeClass("cur");
		for ( var i = 0; i <= prow; i++) {
			if (i != index) {
				$("#price" + i).removeClass("cur");
			} else {
				$("#price" + i).addClass("cur");
			}
		}
	}
	function removeProw(prow) {
		$("#cur3").addClass("cur");
		$("#price_id").val("");
		for ( var i = 0; i < prow; i++) {
			$("#price" + i).removeClass("cur");
		}
	}
	function getBuyAddr(addName) {
		if (!addName) {
			$("#buyaddr").css("display", "block");
		} else {
			$("#buyAddr").val(addName);
			$("#buyaddr").css("display", "none");
		}
	}
	function getSendAddr(addName) {
		if (!addName) {
			$("#sendaddr").css("display", "block");
		} else {
			$("#sendaddr").css("display", "none");
			$("#sendAddr").val(addName);
		}
	}

	function getBuyAddress() {
		var titleAddrId = $("#titleAddrId").val();
		$.ajax({
			type : "post",
			url : "../index/getAddress.json",
			data : {
				"titleAddrId" : $("#titleAddrId").val()
			},
			async : false,
			dataType : "json",
			success : function(data) {
				$(".map-list1").html("");
				$(".map-list1").append("<ul>");
				for ( var i = 0; i < data.length; i++) {
					var obj = data[i];
					$(".map-list1").append(
							"	<li> <a href='javascript:;' onclick=\"getBuyAddr('"
									+ obj.addName + "')\"><span>" + obj.addName
									+ "</span> <p> " + obj.address
									+ "</p> </a> 	</li>");
				}
				$(".map-list1").append("</ul>");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			},
		});
	}
	function getSendAddress() {
		var titleAddrId = $("#titleAddrId1").val();
		$.ajax({
			type : "post",
			url : "../index/getAddress.json",
			data : {
				"titleAddrId" : $("#titleAddrId1").val()
			},
			async : false,
			dataType : "json",
			success : function(data) {
				$(".map-list2").html("");
				$(".map-list2").append("<ul>");
				for ( var i = 0; i < data.length; i++) {
					var obj = data[i];
					$(".map-list2").append(
							"	<li> <a href='javascript:;' onclick=\"getSendAddr('"
									+ obj.addName + "')\"><span>" + obj.addName
									+ "</span> <p> " + obj.address
									+ "</p> </a> 	</li>");
				}
				$(".map-list2").append("</ul>");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			},
		});
	}
	function check() {
		var buyTime = $("#J-xl").val().substring(0, 10);
		if (buyTime.length == 10) {
			$("#bTime").val(buyTime + " " + $("#buyTime").val() + ":00");
			var dB = new Date(Date.parse(buyTime.replace(/-/g, "/")));
			if (new Date() > dB) {
				alert("取车时间应大于当天时间!");
				return false;
			}
		}
		var sendTime = $("#J-xl-2").val().substring(0, 10);
		if (sendTime.length == 10) {
			$("#sTime").val(sendTime + " " + $("#sendTime").val() + ":00");
			var sB = new Date(Date.parse(sendTime.replace(/-/g, "/")));
			if (new Date() > sB) {
				alert("还车时间应大于当天时间!");
				return false;
			}
		}
		if (checkBuyTime() && checkSendTime()) {
			$("#form2").submit();
		}
	}
	function checkBuyTime() {
		var bool = true;
		var value = $("#bTime").val();
		if (!value) {
			alert("取车时间不能为空!");
			bool = false;
		}
		return bool;
	}
	function checkSendTime() {
		var bool = true;
		var value = $("#sTime").val();
		if (!value) {
			alert("还车时间不能为空!");
			bool = false;
		}
		return bool;
	}
	$(function() {
		var buyTime = $("#bTime").val();
		var sendTime = $("#sTime").val();
		$("#J-xl").val(buyTime.substring(0, 10));
		$("#J-xl-2").val(sendTime.substring(0, 10));
		if (buyTime) {
			$("#buyTime").val(buyTime.substring(11, 16));
		}
		if (sendTime) {
			$("#sendTime").val(sendTime.substring(11, 16));
		}
		$("#vehicle").val($("#vehicle_id").val());
		$("#brand").val($("#brand_id").val());
		$("#price").val($("#price_id").val());
		$("#SAddr").val($("#sendAddr").val());
		$("#BAddr").val($("#buyAddr").val());
	});
</script>
</head>
<body style="background:#f6f8fa;">
	<%@include file="../index/header.jsp"%>
	<ul>
		<li><a href="../index/index.do">首页</a></li>
		<li class="cur"><a href="../car/duanzu.do">短租自驾</a></li>
		<li><a href="../index/zhuanche.jsp">专车</a>
		</li>
		<li><a href="../longRentService/longRentServiceIndex.do">长租</a></li>
		<li><a href="../index/enterprise.jsp">企业租车</a></li>
		<li><a href="../order/showOrder.do">订单查询</a></li>
		<li><a href="../about/index.do?title_id=1">关于我们</a></li>
	</ul>
	</div>
	</div>
	<form action="../car/duanzu.do" method="post" id="form2">
		<div class="zijia-page1">
			<div class="zijia1">
				<dl class="zj-dl1">
					<dt>取车:</dt>
					<dd>
						<input type="text" name="buyAddr" value="${car3.buyAddr }"
							id="buyAddr" onfocus="getBuyAddr()" placeholder="输入取车地址" /><a
							href="#"></a>
					</dd>
				</dl>
				<dl class="zj-dl3">
					<dt>
						<input type="text" value="" id="J-xl" placeholder="输入取车时间" /> <a
							href="#"></a>

					</dt>
					<dd>
						<input type="hidden" name="buyTime" id="bTime"
							value="${car3.buyTime }" /> <input type="text" value="10:00"
							id="buyTime" /><a href=""></a>
						<div class="xiala2">
							<a href="javascript:;">01:00</a> <a href="javascript:;">02:00</a>
							<a href="javascript:;">03:00</a> <a href="javascript:;">04:00</a>
							<a href="javascript:;">05:00</a> <a href="javascript:;">06:00</a>
							<a href="javascript:;">07:00</a> <a href="javascript:;">08:00</a>
							<a href="javascript:;">09:00</a> <a href="javascript:;"
								class="cur">10:00</a> <a href="javascript:;">11:00</a> <a
								href="javascript:;">12:00</a> <a href="javascript:;">13:00</a> <a
								href="javascript:;">14:00</a> <a href="javascript:;">15:00</a> <a
								href="javascript:;">16:00</a> <a href="javascript:;">17:00</a> <a
								href="javascript:;">18:00</a> <a href="javascript:;">19:00</a> <a
								href="javascript:;">20:00</a> <a href="javascript:;">21:00</a> <a
								href="javascript:;">22:00</a> <a href="javascript:;">23:00</a> <a
								href="javascript:;">24:00</a>
						</div>
					</dd>
				</dl>
				<dl class="zj-dl2">
					<dt>还车:</dt>
					<dd>
						<input type="text" name="sendAddr" id="sendAddr"
							value="${car3.sendAddr }" onfocus="getSendAddr()"
							placeholder="输入还车地址" /><a href="#"></a>
					</dd>
				</dl>
				<dl class="zj-dl4">
					<dt>
						<input type="text" value="" id="J-xl-2" placeholder="输入还车时间" /> <a
							href="#"></a> <input type="hidden" name="sendTime" id="sTime"
							value="${car3.sendTime }" />
					</dt>
					<dd>
						<input type="text" value="10:00" id="sendTime" /><a href=""></a>
						<div class="xiala2">
							<a href="javascript:;">01:00</a> <a href="javascript:;">02:00</a>
							<a href="javascript:;">03:00</a> <a href="javascript:;">04:00</a>
							<a href="javascript:;">05:00</a> <a href="javascript:;">06:00</a>
							<a href="javascript:;">07:00</a> <a href="javascript:;">08:00</a>
							<a href="javascript:;">09:00</a> <a href="javascript:;"
								class="cur">10:00</a> <a href="javascript:;">11:00</a> <a
								href="javascript:;">12:00</a> <a href="javascript:;">13:00</a> <a
								href="javascript:;">14:00</a> <a href="javascript:;">15:00</a> <a
								href="javascript:;">16:00</a> <a href="javascript:;">17:00</a> <a
								href="javascript:;">18:00</a> <a href="javascript:;">19:00</a> <a
								href="javascript:;">20:00</a> <a href="javascript:;">21:00</a> <a
								href="javascript:;">22:00</a> <a href="javascript:;">23:00</a> <a
								href="javascript:;">24:00</a>
						</div>
					</dd>
				</dl>
			</div>
			<div class="zijia2">
				<a href="#" onclick="check()"><i>立即</i><i>选车</i> </a>
			</div>
		</div>
		<div class="banner-songche" id="buyaddr">
			<dl>
				<dt>
					<span>取车地址</span><a href="javascript:;"></a>
				</dt>
				<dd>
					<div class="map">
						<img src="../index/images/map.jpg" />
					</div>
					<div class="map-input">
						<select name="titleAddrId" class="map-input1" id="titleAddrId"
							onchange="getBuyAddress()">
							<c:forEach items="${titleAddrs}" var="t">
								<option value="${t.id}" <c:if test="${t.id==1 }">selected</c:if>>
									${t.titleAddr}</option>
							</c:forEach>
						</select>
					</div>
					<div class="map-list1">
						<ul>
							<c:forEach items="${addresses}" var="a">
								<li><a href="javascript:getBuyAddr('${a.addName }');"><span>${a.addName
											}</span>
										<p>${a.address }</p> </a>
								</li>
							</c:forEach>
						</ul>
						</ul>
					</div>
				</dd>
			</dl>
		</div>
		<div class="banner-songche" id="sendaddr">
			<dl>
				<dt>
					<span>还车地址</span><a href="javascript:;"></a>
				</dt>
				<dd>
					<div class="map">
						<img src="../index/images/map.jpg" />
					</div>
					<div class="map-input">
						<select name="titleAddrId1" class="map-input1" id="titleAddrId1"
							onchange="getSendAddress()">
							<c:forEach items="${titleAddrs}" var="t">
								<option value="${t.id}" <c:if test="${t.id==1 }">selected</c:if>>
									${t.titleAddr}</option>
							</c:forEach>
						</select>
					</div>
					<div class="map-list2">
						<ul>
							<c:forEach items="${addresses}" var="a">
								<li><a href="javascript:getSendAddr('${a.addName }')"><span>${a.addName
											}</span>
										<p>${a.address }</p> </a>
								</li>
							</c:forEach>
						</ul>
					</div>
				</dd>
			</dl>
		</div>
		<div class="zijia-page2">
			<div class="zijia3">
				<dl>
					<dt>类型:</dt>
					<dd>
						<a href="javascript:removeVrow(${sessionScope.vrow })" class="cur"
							id="cur1">不限</a>
						<c:forEach items="${vehicles }" var="v" varStatus="n">
							<a
								href="javascript:getVehicleId(${v.id },${n.index },${sessionScope.vrow })"
								id="vehicle${n.index }">${v.vehicle_model }</a>
							<c:if test="${v.id==car3.vehicle_id }">
								<script type="text/javascript">
									location.href = "javascript:getVehicleId(${v.id },${n.index },${sessionScope.vrow })";
								</script>
							</c:if>
						</c:forEach>
						<input type="hidden" id="vehicle_id" name="vehicle_id" />
					</dd>
				</dl>
				<dl>
					<dt>品牌:</dt>
					<dd>
						<a href="javascript:removeBrow(${sessionScope.brow })" class="cur"
							id="cur2"> 不限</a>
						<c:forEach items="${brands }" var="b" varStatus="n">
							<a
								href="javascript:getBrandId(${b.id },${n.index},${sessionScope.brow })"
								id="brand${n.index }">${b.name }</a>
							<c:if test="${car3.brand_id==b.id }">
								<script type="text/javascript">
									location.href = "javascript:getBrandId(${b.id },${n.index},${sessionScope.brow })"
								</script>
							</c:if>
						</c:forEach>
						<input type="hidden" name="brand_id" id="brand_id" />
					</dd>
				</dl>
				<dl>
					<dt>价格:</dt>
					<dd>
						<a href="javascript:removeProw(${sessionScope.prow })" class="cur"
							id="cur3">不限</a>
						<c:forEach items="${prices }" var="p" varStatus="n">
							<a
								href="javascript:getPriceId(${p.id },${n.index}, ${sessionScope.prow }) "
								id="price${n.index }">${p.price }</a>
							<c:if test="${p.id==car3.price_id }">
								<script type="text/javascript">
									location.href = "javascript:getPriceId(${p.id },${n.index}, ${sessionScope.prow })";
								</script>
							</c:if>
						</c:forEach>
						<input type="hidden" name="price_id" id="price_id" />
					</dd>
				</dl>
			</div>
		</div>
	</form>
	<div class="zijia-page3">
		<ul>
			<c:if test="${empty cars1}">
				<script type="text/javascript">
					alert("没有检索到满足需求的车辆!")
				</script>
			</c:if>
			<c:forEach items="${cars1 }" var="c" varStatus="n">
				<li>
					<div class="zj3-1">
						<img src="../${c.path }" />
					</div>
					<div class="zj3-2">
						<span>${c.name }</span>
						<p>${c.place }厢｜${c.autoNum }自动｜乘坐${seatNum }人</p>
					</div>
					<div class="zj3-3">
						<i>￥</i><b>${c.favourable_price }</b>/日均
						<del>原价￥${c.original_price }</del>
					</div>
					<div class="zj3-4">
						<c:if test="${c.rent_status=='0' }">
							<a href="#" onclick="send('${c.id}')">租车</a>
						</c:if>
						<c:if test="${c.rent_status=='1' }">
							<span>已租完</span>
						</c:if>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	<script type="text/javascript">
		function send(id) {
			$("#id").val(id);
			var buyTime = $("#J-xl").val().substring(0, 10);
			if (buyTime.length == 10) {
				$("#bTime").val(buyTime + " " + $("#buyTime").val() + ":00");
			}
			var sendTime = $("#J-xl-2").val().substring(0, 10);
			if (sendTime.length == 10) {
				$("#sTime").val(sendTime + " " + $("#sendTime").val() + ":00");
			}
			$("#BuyTime").val($("#bTime").val().substring(0, 19));
			$("#SendTime").val($("#sTime").val().substring(0, 19));
			$("#SAddr").val($("#sendAddr").val());
			$("#BAddr").val($("#buyAddr").val());
			if (!$("#BAddr").val()) {
				alert("取车地址不能为空!");
				return;
			}
			if (!$("#SAddr").val()) {
				alert("还车地址不能为空!");
				return;
			}
			if (!$("#BuyTime").val()) {
				alert("取车时间不能为空!");
				return;
			}
			if (!$("#SendTime").val()) {
				alert("还车时间不能为空!");
				return;
			}
			$("#form1").submit();
		}
	</script>
	<form action="../car/rentCar.do" method="post" id="form1">
		<input type="hidden" name="BTime" id="BuyTime" /> <input type="hidden"
			name="STime" id="SendTime" /> <input type="hidden" name="BAddr"
			id="BAddr" /> <input type="hidden" name="SAddr" id="SAddr" /> <input
			type="hidden" name="vehicle_id" id="vehicle" /> <input type="hidden"
			name="brand_id" id="brand" /> <input type="hidden" name="price_id"
			id="price" /> <input type="hidden" name="id" id="id" />
	</form>
	<c:if test="${info eq '1' }">
		<form action="../user/login1.do" method="post">
			<div class="denglu" style="display:block">
				<div class="denglu-nr">
					<a href="javascript:;" class="off">&nbsp;</a>
					<div class="tc-1">
						<span>登录</span>
						<p>
							<i>没有帐户</i><a href="../user/toRegist.do">立即注册</a>
						</p>
					</div>
					<div class="tc-2">
						<input type="text" name="tel" placeholder="请输入手机号" /><input
							type="password" name="password" placeholder="请输入密码" />
					</div>
					<div class="tc-3">
						<a href="../index/findBackPwd.jsp">忘记密码？</a>
					</div>
					<div class="tc-4">
						<input type="submit" value="登录" />
					</div>
					<h3 style="color:red;margin-left:135px;margin-top:20px">${message
						}</h3>
				</div>
			</div>
		</form>
	</c:if>
	<div class="footer">
		<%@include file="../index/foot.jsp"%>
		<div class="footer-fx">
			<a href="#"><img src="../index/images/icon-fx1.png" /> </a><a
				href="#"><img src="../index/images/icon-fx2.png" /> </a><a href="#"><img
				src="../index/images/icon-fx3.png" /> </a><a href="#"><img
				src="../index/images/icon-fx4.png" /> </a>
		</div>
		<div class="footer-bq">2016 All Rights
			Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>
	</div>
	<div class="ce-nav">
		<a href="#" class="ce1"></a> <a href="#" class="ce2"></a> <a href="#"
			class="ce3"></a>
	</div>
	<script type="text/javascript" src="../index/laydate.dev.js"></script>
	<script type="text/javascript">
		laydate({
			elem : '#J-xl'
		});
		document.getElementById('J-xl-2').onclick = function() {
			laydate({
				elem : '#J-xl-2'
			});
		}

		laydate({
			elem : '#J-xl-3'
		});

		laydate({
			elem : document.getElementById('J-xl-4')
		});
	</script>
</body>
</html>
