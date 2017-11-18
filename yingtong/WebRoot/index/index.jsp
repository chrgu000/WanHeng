<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盈通租车</title>
<meta name="keywords" content="好买车" />
<meta name="description" content="好买车" />
<link href="css/public.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.js">
</script>
<script type="text/javascript" src="js/js.js">
</script>
<script type="text/javascript" src="js/rili.js">
</script>
<script type="text/javascript">
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
$.ajax( {
		type : "post",
		url : "../index/getAddress.json",
		data : {
			"titleAddrId" : $("#titleAddrId").val()
		},
		async: false,
		dataType : "json",
		success : function(data) {
			$(".map-list1").html("");
			  $(".map-list1").append("<ul>");
		   for(var i=0;i<data.length;i++){
			   var obj=data[i];
			   $(".map-list1").append("	<li> <a href='javascript:;' onclick=\"getBuyAddr('"+obj.addName+"')\"><span>"+obj.addName+"</span> <p> "+obj.address+"</p> </a> 	</li>");
		   }
		   $(".map-list1").append("</ul>");
		},
	error: function(XMLHttpRequest, textStatus, errorThrown) {
alert(XMLHttpRequest.status);
alert(XMLHttpRequest.readyState);
alert(textStatus);
},
	});
}
function getSendAddress() {
	var titleAddrId = $("#titleAddrId1").val();
$.ajax( {
		type : "post",
		url : "../index/getAddress.json",
		data : {
			"titleAddrId" : $("#titleAddrId1").val()
		},
		async: false,
		dataType : "json",
		success : function(data) {
			$(".map-list2").html("");
			  $(".map-list2").append("<ul>");
		   for(var i=0;i<data.length;i++){
			   var obj=data[i];
			   $(".map-list2").append("	<li> <a href='javascript:;' onclick=\"getSendAddr('"+obj.addName+"')\"><span>"+obj.addName+"</span> <p> "+obj.address+"</p> </a> 	</li>");
		   }
		   $(".map-list2").append("</ul>");
		},
	error: function(XMLHttpRequest, textStatus, errorThrown) {
alert(XMLHttpRequest.status);
alert(XMLHttpRequest.readyState);
alert(textStatus);
},
	});
}
function check() {
	var buyTime = $("#J-xl").val();
	if (buyTime.length == 10) {
		var bTime=$("#bTime").val(buyTime + " " + $("#buyTime").val() + ":00");
		var dB=new Date(Date.parse(buyTime.replace(/-/g,"/")));
		if(!(new Date()<dB)){
		    alert("取车时间应大于当天时间!");
		    return false;
		}
	}
	var sendTime = $("#J-xl-2").val();
	if (sendTime.length == 10) {
		$("#sTime").val(sendTime + " " + $("#sendTime").val() + ":00");
	var sB=new Date(Date.parse(sendTime.replace(/-/g,"/")));
	if(!(new Date()<sB)){
		alert("还车时间应大于当天时间!");
		return false;
	}
	}
	if(checkBuyTime()&&checkSendTime()){
   	return true;
	}
	return false;
}
function checkBuyTime(){
	var bool=true;
	var value=$("#bTime").val();
	if(!value){
		alert("取车时间不能为空!");
		bool=false;
	}
	return bool;
}
function checkSendTime(){
	var bool=true;
	var value=$("#sTime").val();
	if(!value){
		alert("还车时间不能为空!");
		bool=false;
	}
	return bool;
	
}
</script>
</head>
<body>
	<%@include file="../index/header.jsp"%>
	<ul>
		<li class="cur"><a href="../index/index.do">首页</a></li>
		<li><a href="../car/duanzu.do">短租自驾</a></li>
			<li><a href="../index/zhuanche.jsp">专车</a></li>
		<li><a href="../longRentService/longRentServiceIndex.do">长租</a></li>
		<li><a href="../index/enterprise.jsp">企业租车</a></li>
		<li><a href="../order/showOrder.do">订单查询</a></li>
		<li><a href="../about/index.do?title_id=1">关于我们</a></li>
	</ul>
	</div>
	</div>
	<div class="banner">
		<ul>
			<li><img src="images/banner.png" />
			</li>
		</ul>
		<div class="lvjing">
			<img src="images/banner.png" />
			<div></div>
		</div>
		<form action="../car/duanzu.do" method="post"
			onSubmit="return check()">
			<div class="banner-xz">
				<dl class="x-dl1">
					<dt>取车:</dt>
					<dd>
						<input type="text" placeholder="输入取车地址" name="buyAddr"
							id="buyAddr" onfocus="getBuyAddr()" /> <a href="#"></a>
					</dd>
				</dl>
				<dl class="x-dl2">
					<dt>还车:</dt>
					<dd>
						<input type="text" placeholder="输入还车地址" name="sendAddr"
							id="sendAddr" onfocus="getSendAddr()" /> <a href="#"></a>
					</dd>
				</dl>
				<dl class="x-dl3">
					<dt>
						<input type="text" value="" id="J-xl" placeholder="输入取车时间" /> <a
							href="#" class="rl-icon"></a>
					</dt>
					<dd>
						<input name="buyTime" id="bTime" type="hidden" /> <input
							type="text" value="10:00" class="times" id="buyTime" />
						<div class="time">
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
				<dl class="x-dl4">
					<dt>
						<input type="text" value="" id="J-xl-2" placeholder="输入还车时间" /> <a
							href="#" class="rl-icon"></a>
					</dt>
					<dd>
						<input type="hidden" name="sendTime" id="sTime" /> <input
							type="text" value="10:00" class="times" id="sendTime" /> <i></i>
						<div class="time">
							<a href="javascript:;">01:00</a> <a href="javascript:;">02:00</a>
							<a href="javascript:;">03:00</a> <a href="javascript:;">04:00</a>
							<a href="javascript:;">05:00</a> <a href="javascript:;">06:00</a>
							<a href="javascript:;">07:00</a> <a href="javascript:;">08:00</a>
							<a href="javascript:;">09:00</a><a href="javascript:;"
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
				<div class="x-dl5">
					<input type="submit" value="立即选车" />
				</div>
			</div>
		</form>
	</div>

	<div class="banner-songche" id="buyaddr">
		<dl>
			<dt>
				<span>取车地址</span><a href="javascript:;"></a>
			</dt>
			<dd>
				<div class="map">
					<img src="images/map.jpg" />
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
					<img src="images/map.jpg" />
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

	<div class="page1">
		<div class="k-tit">
			车型大全 <i>&nbsp;</i>
		</div>
		<div class="page1-list">
			<c:forEach items="${cars }" var="c">
				<a href="#">
					<dl>
						<dt>
							<img src="../${c.path }" />
						</dt>
						<dd>
							<div class="p1-1">${c.place }厢｜${c.autoNum
								}自动｜乘坐${c.seatNum }人</div>
							<div class="p1-2">${c.name }</div>
							<div class="p1-3">
								<span>在线优惠</span> <i>￥</i><b>${c.favourable_price}</b>/日均
								<del> 原价￥${c.original_price }/日均 </del>
							</div>
						</dd>
					</dl> </a>
			</c:forEach>
		</div>
		<div class="more">
			<a href="../car/duanzu.do">更多</a>
		</div>
	</div>
	<div class="page2">
		<div class="k-tit2">
			我们的服务 <i>&nbsp;</i>
		</div>
		<div class="page2-list">
			<dl>
				<dt>
					<img src="images/ser1.png" />
				</dt>
				<dd>
					<span>短租自驾</span>
					<p>轻松取车/还车</p>
				</dd>
			</dl>
			<dl>
				<dt>
					<img src="images/ser2.png" />
				</dt>
				<dd>
					<span>长租服务</span>
					<p>长租价格更优惠</p>
				</dd>
			</dl>
			<dl>
				<dt>
					<img src="images/ser3.png" />
				</dt>
				<dd>
					<span>企业租车</span>
					<p>提供企业一站式租车服务</p>
				</dd>
			</dl>
		</div>
	</div>
	<div class="page3">
		<div class="k-tit">
			关注我们 <i>&nbsp;</i>
		</div>
		<div class="page3-1">
			<img src="images/page3-1.jpg" />
		</div>
		<div class="page3-2">
			<h5>盈通租车手机微信端</h5>
			<span>随时随地在线选车，享优惠，更方便</span> <img src="images/ewm.jpg" />
			<p>更多惊喜敬请关注手机微信端</p>
		</div>
	</div>
	<%@include file="../index/foot.jsp"%>
		<div class="footer-fx">
		<a href="#"><img src="images/icon-fx1.png" /> </a><a href="#"><img
			src="images/icon-fx2.png" /> </a><a href="#"><img
			src="images/icon-fx3.png" /> </a><a href="#"><img
			src="images/icon-fx4.png" /> </a>
	</div>
	<div class="footer-bq">2016 All Rights
		Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>
	<script type="text/javascript" src="laydate.dev.js">
</script>
	<script type="text/javascript">
laydate( {
	elem : '#J-xl'
});
document.getElementById('J-xl-2').onclick = function() {
	laydate( {
		elem : '#J-xl-2'
	});
}

laydate( {
	elem : '#J-xl-3'
});

laydate( {
	elem : document.getElementById('J-xl-4')
});
</script>
</body>
</html>
