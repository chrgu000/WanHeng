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

<link rel="stylesheet" type="text/css" href="../mobile/css/css.css">
<link rel="stylesheet" type="text/css"
	href="../mobile/css/weixinshow.css">
<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/fastclick.js"></script>

<script type="text/javascript" src="../mobile/js/date.js"></script>
<script type="text/javascript" src="../mobile/js/iscroll.js"></script>
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
	$(function(){
		$('#beginTime').date();
		$('#endTime').date({theme:"datetime"});
	});
	function checkDays(){
		var bool=true;
		var value=$("#days").val();
		if(!value){
			newaddress.ealert("租赁时长不能为空!")
			bool=false;
		}
		return bool;
	}
	function checkCarNum(){
		var bool=true;
		var reg=/^[0-9]+$/
		var value=$("#car_num").val();
		 if(!reg.test(value)){
			newaddress.ealert("用车数量要为数字!");
			bool=false;
		}
		return bool;
	}
	function checkBrand(){
		var bool=true;
		var value=$("#brand").val();
		if(!value){
			newaddress.ealert("品牌不能为空!")
			bool=false;
		}
		return bool;
	}
	function checkBuyTime(){
		var bool=true;
		var value=$("#beginTime").val();
		if(!value){
			newaddress.ealert("取车时间不能为空!");
			bool=false;
		}
		var sB=new Date(Date.parse(value.replace(/-/g,"/")));
		if(!(new Date()<sB)){
			newaddress.ealert("取车时间应大于当天时间!");
			bool=false;
		}
		return bool;
	}
	function checkMotorcycle(){
		var bool=true;
		var value=$("#xiala").val();
		if(!value){
			newaddress.ealert("车型不能为空!");
			bool=false;
		}
		return bool;
	}
	function putBrand(){
		var id=$("#brand").val();
		$.ajax( {
			type : "post",
			url : "../longRentService/getMotorcycles.json",
			data : {
				"brand_id" : id
			},
			async: false,
			dataType : "json",
			success : function(data) {
				$("#xiala").html("");
				$("#xiala").append("<option value=''>请选择<option>");
			   for(var i=0;i<data.length;i++){
				   var obj=data[i];
				   $("#xiala").append("<option value="+obj.name+">"+obj.name+"<option>");
			   }
			},
			
		error: function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
		});
	}
	function reverse(){
		  if(checkDays()&&checkBuyTime()&&checkBrand()&&checkMotorcycle()&&checkCarNum()){
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
	<form action="../longRentService/addlongRentService1.do" method="post"
		onsubmit="return reverse()">
		<div id="all">
			<div class="service">
				<dl>
					<dt>租期</dt>
					<dd>
						<select name="days" id="days"><option value="" selected>请选择</option>
							<option value="1个月">1个月</option>
							<option value="2个月">2个月</option>
							<option value="3个月">3个月</option>
							<option value="4个月">4个月</option>
							<option value="5个月">5个月</option>
							<option value="6个月">6个月</option>
							<option value="7个月">7个月</option>
							<option value="8个月">8个月</option>
							<option value="9个月">9个月</option>
							<option value="10个月">10个月</option>
							<option value="11个月">11个月</option>
							<option value="12个月">12个月</option>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>取车日期</dt>
					<dd>
						<input type="text" id="beginTime" value="年月日" name="buyTime">
					</dd>
				</dl>
			</div>
			<div class="service">
				<dl>
					<dt>品牌</dt>
					<dd>
						<select name="brand_id" id="brand" onchange="putBrand()"><option
								value="">请选择</option>
							<c:forEach var="b" items="${brands }">
								<option value="${b.id }">${b.name }</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>车型</dt>
					<dd>
						<select name="motorcycle" id="xiala"><option value="">请选择</option>
							<c:forEach items="${motorcycles }" var="m">
								<option value="${m.name }">${m.name }</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<dl>
					<dt>数量</dt>
					<dd>
						<input type="text" name="car_num" id="car_num"
							placeholder="请输入用车数量">
					</dd>
				</dl>
			</div>

			<div class="add-tc">
				<div class="add-map">
					<img src="images/map.jpg" style="width:100%;">
				</div>
				<div class="add-ss">
					<input type="text" placeholder="还车地址"><input type="submit"
						value="">
				</div>
				<div class="add-list">
					<a href="javascript:;" class="cur"> <span>当前：杭州高新软件园</span>
						<p>伟业路1号</p> </a> <a href="javascript:;"> <span>贝因美大厦</span>
						<p>南环路3758号</p> </a> <a href="javascript:;"> <span>很爱咖啡</span>
						<p>南环路3730号</p> </a> <a href="javascript:;"> <span>源越大厦</span>
						<p>南环路3730号</p> </a>
				</div>
			</div>

			<div class="btn-input2">
				<input type="submit" value="下一步">
			</div>
			<div id="datePlugin"></div>
		</div>
	</form>
</body>
</html>
