<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript" src="../mobile/js/date.js"></script>
<script type="text/javascript" src="../mobile/js/iscroll.js"></script>
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
	$(function() {
		$('#beginTime').date({
		});
		$('#endTime').date({
		});
	});
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
				$(".add-list").html("");
				$(".add-list").append("<ul>");
				for ( var i = 0; i < data.length; i++) {
					var obj = data[i];
					$(".add-list").append(
							"	<li> <a href='javascript:;' onclick=\"getBuyAddr('"
									+ obj.addName + "')\"><span>" + obj.addName
									+ "</span> <p> " + obj.address
									+ "</p> </a> 	</li>");
				}
				$(".add-list").append("</ul>");
				$('.add-tc .add-list a').click(function() {
					$(this).addClass('cur').siblings('a').removeClass('cur');
					$('.add-tc').fadeOut();
					$('.qc-input').val($(this).find('span').html());
				});
				$('.add-tc2 .add-list a').click(function() {
					$(this).addClass('cur').siblings('a').removeClass('cur');
					$('.add-tc2').fadeOut();
					$('.hc-input').val($(this).find('span').html());
				});
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
				$(".add-list1").html("");
				$(".add-list1").append("<ul>");
				for ( var i = 0; i < data.length; i++) {
					var obj = data[i];
					$(".add-list1").append(
							"	<li> <a href='javascript:;' onclick=\"getSendAddr('"
									+ obj.addName + "')\"><span>" + obj.addName
									+ "</span> <p> " + obj.address
									+ "</p> </a> 	</li>");
				}
				$(".add-list1").append("</ul>");
				$('.add-tc .add-list1 a').click(function() {
					$(this).addClass('cur').siblings('a').removeClass('cur');
					$('.add-tc').fadeOut();
					$('.qc-input').val($(this).find('span').html());
				});
				$('.add-tc2 .add-list1 a').click(function() {
					$(this).addClass('cur').siblings('a').removeClass('cur');
					$('.add-tc2').fadeOut();
					$('.hc-input').val($(this).find('span').html());
				});
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			},
		});
	} 
	                 function getAddr(){
	                	 var buyAddr=$("#buyAddr").val();
	                	 if(!buyAddr){
	                		 newaddress.ealert("取车地址不能为空!");
	                		 return false;
	                	 }
	                	 var sendAddr=$("#sendAddr").val();
	                	 if(!sendAddr){
	                		 newaddress.ealert("还车地址不能为空!");
	                		 return false;
	                	 }
	                	 return true;
	                 }
					function getDays() {
						setTimeout(function(){
							var beginDate;
							var endDate;
							var beginTime = $("#beginTime").val().substring(0,10)+" 00:00:00";
							var endTime = $("#endTime").val().substring(0,10)+" 00:00:00";
<%--						if(beginTime.length==12){--%>
<%--							newaddress.ealert("取车时间不能为空!");--%>
<%--							return false;--%>
<%--						}--%>
<%--						if(endTime.length==12){--%>
<%--							newaddress.ealert("还车时间不能为空!");--%>
<%--							return false;--%>
<%--						}--%>
							if (beginTime.length == 19&&endTime.length == 19) {
								var arr = beginTime.split(/[- :]/);
								beginDate = new Date(arr[0], arr[1]-1, arr[2], arr[3], arr[4], arr[5]); 
								if (new Date() > beginDate) {
									newaddress.ealert("取车时间应大于当天时间!");
									return false;
								}
								$("#beginTime").val(beginTime);
								var ar =endTime.split(/[- :]/);
								endDate = new Date(ar[0], ar[1]-1, ar[2], ar[3], ar[4], ar[5]); 
								if (new Date() > endDate) {
									newaddress.ealert("还车时间应大于当天时间!");
									return false;
								}
								$("#endTime").val(endTime);
							var num = (endDate - beginDate) / (1000 * 3600 * 24);
							if(num<0){
								newaddress.ealert("还车时间应大于取车时间!");
								return false;
							}
							var days = parseInt(Math.ceil(num));
							$("#days").text(days + "天");
							$("#day").val(days);
							return true;
						}else{
							return false;
						}
						},400);
					
					}
					function getDay() {
							var beginDate;
							var endDate;
							var beginTime = $("#beginTime").val().substring(0,10)+" 00:00:00";
							var endTime = $("#endTime").val().substring(0,10)+" 00:00:00";
						if(beginTime.length==12){
							newaddress.ealert("取车时间不能为空!");
							return false;
						}
						if(endTime.length==12){
							newaddress.ealert("还车时间不能为空!");
							return false;
						}
							if (beginTime.length == 19&&endTime.length == 19) {
								var arr = beginTime.split(/[- :]/);
								beginDate = new Date(arr[0], arr[1]-1, arr[2], arr[3], arr[4], arr[5]); 
								if (new Date() > beginDate) {
									newaddress.ealert("取车时间应大于当天时间!");
									return false;
								}
								$("#beginTime").val(beginTime);
								var ar =endTime.split(/[- :]/);
								endDate = new Date(ar[0], ar[1]-1, ar[2], ar[3], ar[4], ar[5]); 
								if (new Date() > endDate) {
									newaddress.ealert("还车时间应大于当天时间!");
									return false;
								}
								$("#endTime").val(endTime);
							var num = (endDate - beginDate) / (1000 * 3600 * 24);
							if(num<0){
								newaddress.ealert("还车时间应大于取车时间!");
								return false;
							}
							var days = parseInt(Math.ceil(num));
							$("#days").text(days + "天");
							$("#day").val(days);
							return true;
						}else{
							return false;
						}
					
					}
					function check(){
						if(getAddr()&&getDay()){
							return true;
						}
						return false;
					}
				</script>
</head>
<body>
	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
	<div id="all">
		<form action="../car/duanzu1.do" method="post"
			onsubmit="return check()">
			<input type="hidden" name="short_rent" value="1">
			<div class="car-add">
				<div class="add-input">
					<dl>
						<dt>取车:</dt>
						<dd>
							<input type="text" placeholder="请输入取车地址" name="buyAddr" 
								id="buyAddr" class="qc-input"><a href="javascript:;"
								class="qc-btn">&nbsp;</a>
						</dd>
					</dl>
					<dl>
						<dt>还车:</dt>
						<dd>
							<input type="text" placeholder="请输入还车地址" name="sendAddr"
								id="sendAddr" class="hc-input"><a href="javascript:;"
								class="hc-btn">&nbsp;</a>
						</dd>
					</dl>
				</div>
				<div class="add-time">
					<dl>
						<dd>
							<span>取车时间</span><input type="text" value="年月日" id="beginTime" onmouseout="getDays()"  
								name="buyTime"><select><option>上午</option>
								<option>中午</option>
								<option>下午</option>
							</select>
						</dd>
						<dt>
							<p id="days">0天</p>
						</dt>
						<dd>
							<span>还车时间</span><input type="text" value="年月日" id="endTime" onmouseout="getDays()"  
								name="sendTime"><select><option>上午</option>
								<option>中午</option>
								<option>下午</option>
							</select>
						</dd>
					</dl>
					<input type="hidden" name="days" id="day">
				</div>
			</div>
			<div class="btn-input">
				<input value="立即选车" type="submit">
			</div>
		</form>
		<div class="add-tc">
			<div class="add-map">
				<img src="../mobile/images/map.jpg" style="width:100%;">
			</div>
			<div class="add-ss">
				<select name="titleAddrId" class="map-input1" id="titleAddrId"
					onchange="getBuyAddress()">
					<c:forEach items="${titleAddrs}" var="t">
						<option value="${t.id}" <c:if test="${t.id==1 }">selected</c:if>>
							${t.titleAddr}</option>
					</c:forEach>
				</select>
			</div>
			<div class="add-list">
				<c:forEach items="${addresses }" var="a">
					<a href="javascript:;"> <span>${a.addName }</span>
						<p>${a.address }</p> </a>
				</c:forEach>
			</div>
		</div>

		<div class="add-tc2">
			<div class="add-map">
				<img src="../mobile/images/map.jpg" style="width:100%;">
			</div>
			<div class="add-ss">
				<select name="titleAddrId1" class="map-input1" id="titleAddrId1"
					onchange="getSendAddress()">
					<c:forEach items="${titleAddrs}" var="t">
						<option value="${t.id}" <c:if test="${t.id==1 }">selected</c:if>>
							${t.titleAddr}</option>
					</c:forEach>
				</select>
			</div>
			<div class="add-list1">
				<c:forEach items="${addresses }" var="a">
					<a href="javascript:;"> <span>${a.addName }</span>
						<p>${a.address }</p> </a>
				</c:forEach>
			</div>
		</div>
		<div id="datePlugin"></div>
	</div>
</body>
</html>
