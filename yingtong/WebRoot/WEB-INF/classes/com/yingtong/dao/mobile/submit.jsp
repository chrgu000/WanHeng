<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>聚乡村</title>
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
	var beginTime,endTime;
	$('.time-list a').click(function(){
		$(this).find('em').addClass('cur');
		var t=0;
		var value1,value2,value3;
		if($('.time-list .cur').length==1){
			value1=$(this).siblings().find('.p').text()+$(this).text()+"日";
			if(value1.length<=4){
				value1=$(this).siblings().find('.p2').text()+$(this).text()+"日";
				if(value1.length<=4){
					value1=$(this).siblings().find('.p3').text()+$(this).text()+"日";
				}
			}
			$("#d1").text(value1);
			$('.time-list a').each(function(){
	            if($(this).find('.cur').length>0){
					$(this).prepend("<i>入住</i>");
					t++;
				}
	        });
		}else if($('.time-list .cur').length==2){
			 value2=$(this).siblings().find('.p').text()+$(this).text()+"日";
			if(value2.length<=4){
				 value2=$(this).siblings().find('.p2').text()+$(this).text()+"日";
				 if(value2.length<=4){
					 value2=$(this).siblings().find('.p3').text()+$(this).text()+"日";
				 }
			}
			$("#d2").text(value2);
			var d1=$("#d1").text();
			var d2=$("#d2").text();
			var t1=parseInt(d1.substring(0,d1.indexOf("月")));
			var t2=parseInt(d2.substring(0,d2.indexOf("月")));
		    var date = new Date();
		    if(t1<=t2){
				d1=date.getFullYear()+"年"+d1;
				d2=date.getFullYear()+"年"+d2;
				}else{
					d1=date.getFullYear()+"年"+d1;
					d2=date.getFullYear()+1+"年"+d2;
				}
				d1=d1.replace(/[\u4e00-\u9fa5]/g, "/");
				d1=d1.substring(0,d1.lastIndexOf("/"));
				d2=d2.replace(/[\u4e00-\u9fa5]/g, "/");
				d2=d2.substring(0,d2.lastIndexOf("/"));
				var beginDate = new Date(d1); 
				var endDate = new Date(d2);
				var days;
				if(endDate>beginDate){
				days=(endDate-beginDate)/(24*3600*1000);
				}else{
					days=(beginDate-endDate)/(24*3600*1000);
				}
				d1=d1.replace(/\//g, "-");
				beginTime=d1;
				d2=d2.replace(/\//g, "-");
				endTime=d2;
				$("#beginTime").val(beginTime);
				$("#endTime").val(endTime);
				$("#d3").text(days);
				$("#days").val(days);
			$('.time-list a').each(function(){
				$(this).find('i').remove();
				if(t==1){
					$(this).find('em').addClass('cur2');
				}else{
				}
	            if($(this).find('.cur').length>0){
					if(t<1){
						$(this).prepend("<i>入住</i>");
					}else{
						$(this).prepend("<i>离开</i>");
					}
					t++;
				}
				if(t==2){
					$(this).find('em').removeClass('cur2');
				}
	        });
		}else if($('.time-list .cur').length==3){
			value3=$(this).siblings().find('.p').text()+$(this).text()+"日";
			if(value3.length<=4){
				 value3=$(this).siblings().find('.p2').text()+$(this).text()+"日";
				 if(value3.length<=4){
					 value3=$(this).siblings().find('.p3').text()+$(this).text()+"日";
				 }
			}
			$("#d2").text(value3);
			var d1=$("#d1").text();
			var d2=$("#d2").text();
			var t1=parseInt(d1.substring(0,d1.indexOf("月")));
			var t2=parseInt(d2.substring(0,d2.indexOf("月")));
		    var date = new Date();
		    if(t1<=t2){
				d1=date.getFullYear()+"年"+d1;
				d2=date.getFullYear()+"年"+d2;
		    }else{
		    	d1=date.getFullYear()+"年"+d1;
				d2=date.getFullYear()+1+"年"+d2;
		    }
				d1=d1.replace(/[\u4e00-\u9fa5]/g, "/");
				d1=d1.substring(0,d1.lastIndexOf("/"));
				d2=d2.replace(/[\u4e00-\u9fa5]/g, "/");
				d2=d2.substring(0,d2.lastIndexOf("/"));
				var beginDate = new Date(d1); 
				var endDate = new Date(d2); 
				var days=(endDate-beginDate)/(24*3600*1000);
			    d1=d1.replace(/\//g, "-");
				beginTime=d1;
				d2=d2.replace(/\//g, "-");
				endTime=d2;
				$("#beginTime").val(beginTime);
				$("#endTime").val(endTime);
				$("#d3").text(days);
				$("#days").val(days);
			$('.time-list a').each(function() {
				$(this).find('i').remove();
				if(t==1||t==2){
					$(this).find('em').addClass('cur2');
				}
				if($(this).find('.cur').length>0){
					if(t==0){
						$(this).prepend("<i>入住</i>");
					}else if(t==1){
						$(this).find('em').removeClass('cur');
					}else if(t==2){
						$(this).prepend("<i>离开</i>");
					}
					t++;
					if(t==3){
						$(this).find('em').removeClass('cur2');
					}
				}
	        });
		}
	});
	$('.time-qx').click(function(){
		$('.time-all').fadeOut();
	});
	$('.time-qr').click(function(){
		$('.time-all').fadeOut();
	});
	$('.timerz').click(function(){
		$('.time-all').css('display','block');
		$('.time-list em').removeClass('cur').removeClass('cur2');
	});
});
		function submit(){
			$("#f1").submit();
		}
		$(function(){
			//数量
			var sums=$('.su').html();
			$('.jia').click(function(){
				sums++;
				sumz();
			});
			$('.jian').click(function(){
				sums--;
				sumz();
			});
			function sumz(){
				if(sums<1){
					sums=1;
				}
				$('.su').html(sums);
				$("#number").val(sums);
<%--				var days=$("#days").val();--%>
<%--				alert(days);--%>
<%--				alert(sums*days*${product.favourable_price});--%>
<%--				$("#total_price").val(100)--%>
<%--				$("#b").text(sums*days*${product.favourable_price});--%>
			}
		});
		function check(){
				var beginTime=$("#beginTime").val();
				var endTime=$("#endTime").val();
				if(!beginTime||!endTime){
					newaddress.ealert("请选择时间!");
					return false;
				}
				$.ajax({type:"post",url:"../product/getPrice.json",async:false,dataType:"json",data:{"beginTime":beginTime,"endTime":endTime,"product_id":${product.id}},success:function(data){
					var number=$("#number").val();
					$("#total_price").val(number*data);
					$("#b").text("￥"+(number*data));
				},error:function(){}});
				return true;
		}
		</script>
</head>
<body>
	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
	<form action="../order/addOrder1.do" method="post" id="f1"
		onsubmit="return check()">
		<input type="hidden" name="product_id" id="product_id"
			value="${product.id }"> <input type="hidden" name="beginTime"
			id="beginTime"> <input type="hidden" name="endTime"
			id="endTime"> <input type="hidden" name="total_price"
			id="total_price" value="100"> <input type="hidden"
			name="number" id="number" value="1"> <input type="hidden"
			name="days" id="days"> <input type="hidden" name="user_id"
			value="${user.id }">
	</form>
	<div id="all">
		<div class="yiyan1">
			<p>${product.merchant.title }</p>
		</div>
		<div class="yaoqing1">
			<dl>
				<dt>
					<img src="../${product.path}">
				</dt>
				<dd>
					<div class="yq01">
						<span>${product.title }</span>
						<p>${product.sub_title }</p>
					</div>
					<div class="yq02">
						<i>单价:￥<em>${product.favourable_price }</em> </i>
					</div>
				</dd>
			</dl>
		</div>
		<div class="jiezhi2">
			<a class="timerz" href="javascript:;"> 入住<span id="d1"></span>&nbsp;&nbsp;离店<span
				id="d2"></span>&nbsp;&nbsp;共<span id="d3"></span>晚</a>
		</div>
		<div class="shuliang">
			<span>数量</span>
			<div>
				<a href="javascript:;" class="jian">&nbsp;</a>
				<p class="su">1</p>
				<a href="javascript:;" class="jia">&nbsp;</a>
			</div>
		</div>

		<!--     <div class="quan"> -->
		<!--     	<a href="#"><span>可用乡村券 </span><p>-￥10</p></a> -->
		<!--     </div> -->

		<!--     <div class="weixin"> -->
		<!--     	<span>可用乡村券 </span> -->
		<!--     </div> -->

		<div class="bottom2">
			<div class="bottom2-nr">
				<a href="#" class="fanhui">&nbsp;</a>
				<p class="haixu">
					总价：<b id="b"></b>
				</p>
				<a href="#" class="yuding" onclick="submit()">提交订单</a>
			</div>
		</div>
		<div class="time-all">
			<div class="time-week">
				<i>日</i><i>一</i><i>二</i><i>三</i><i>四</i><i>五</i><i>六</i>
			</div>
			<div class="time-list">
				<ul>
					<c:forEach items="${dates }" var="d" varStatus="v">
						<c:choose>
							<c:when test="${v.count==1 }">
								<li>
									<div>
										<p class="marle${d.week } p">${d.month }月</p>
									</div> <c:forEach begin="1" end="${d.lastday }" var="n">
										<c:if test="${n != d.day && n==1}">
											<span class="marle${d.week }"><em>1</em> </span>
										</c:if>
										<c:if test="${n == d.day && n==1}">
											<a href="javascript:;" class="marle${d.week }"><em>${n}</em>
											</a>
										</c:if>
										<c:if test="${n lt d.day && n gt 1 }">
											<span><em>${n}</em> </span>
										</c:if>
										<c:if test="${n ge d.day }">
											<a href="javascript:;"><em>${n}</em> </a>
										</c:if>
									</c:forEach>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<div>
										<p class="marle${d.week } p${v.count}">${d.month }月</p>
									</div> <c:forEach begin="1" end="${d.lastday }" var="n">
										<c:if test="${ n==1}">
											<a href="javascript:;" class="marle${d.week }"><em>${n
													}</em> </a>
										</c:if>
										<c:if test="${n gt 1 }">
											<a href="javascript:;"><em>${n }</em> </a>
										</c:if>
									</c:forEach>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
			<div class="time-btn">
				<a href="javascript:;" class="time-qx">取消</a> <a href="javascript:;"
					class="time-qr">确定</a>
			</div>
		</div>
	</div>
	<script src="../mobile/js/js.js"></script>
</body>
</html>
