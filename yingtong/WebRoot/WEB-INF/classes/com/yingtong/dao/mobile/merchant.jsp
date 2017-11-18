<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Map"%>
<%@page import="com.jxc.cn.CacheMgr"%>
<%@page import="com.jxc.cn.Sign"%>
<%@page import="com.jxc.util.GetWX"%>
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
<link rel="stylesheet" type="text/css"
	href="../mobile/css/weixinshow.css">
<link rel="stylesheet" type="text/css" href="../mobile/css/css.css">
<link rel="stylesheet" href="../mobile/css/swiper.min.css">

<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/fastclick.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
       $('.fenxiang').click(function(){
			$(this).fadeOut();
		});
    });
	function fenxiang(){
		$(".fenxiang").fadeIn();
	}
	
	var newaddress = {}
	newaddress.ealert = function(msg) {
		$('.warnMsg').html(msg);
		$('.error').show();
		$('.error').fadeIn(400).delay(800).fadeOut(400);
	}
	newaddress.eclear = function() {
		$('.warnMsg').html('');
	}
	function checkPhone(id) {
		var tel = $("#tel").val();
		$("#product_id").attr("value", id);
		if (!tel) {
			$(".phonetc").css("display", "block");
		} else {
			location.href = "../user/toSubmit.do?product_id="
					+ $("#product_id").val();
		}
	}
	function checkTel() {
		var reg = /^1[3|4|5|7|8]\d{9}$/;
		var value = $("#tel").val();
		if (!reg.test(value)) {
			newaddress.ealert("手机号格式不正确");
			return false;
		}
		return true;
	}
	$(function() {
		//获取手机验证码
		var timer = 0;
		var ti = 60;
		$('.yzmbtn').click(function() {
			if (checkTel()) {
				$(this).css('display', 'none');
				$(this).siblings('span').css('display', 'block').html('还剩60秒');
				timer = setInterval(yzm, 1000);
				$.get("../user/getTelCode.do", {
					tel : $("#tel").val()
				}, function(data) {
					if (data == 0) {
						alert("获取验证码过于频繁!");
					}
				}, 'json');
			}
		});
		function yzm() {
			ti--;
			if (ti == 0) {
				$('.yzmbtn').css('display', 'block').siblings('span').css(
						'display', 'none');
				clearInterval(timer);
				ti = 60;
			} else {
				$('.yzmspan').html("还剩" + ti + "秒");
			}
		}
	});
</script>
<style>
.swiper-slide { /* Center slide text vertically */
	display: -webkit-box;
	display: -ms-flexbox;
	display: -webkit-flex;
	display: flex;
	-webkit-box-pack: center;
	-ms-flex-pack: center;
	-webkit-justify-content: center;
	justify-content: center;
	-webkit-box-align: center;
	-ms-flex-align: center;
	-webkit-align-items: center;
	align-items: center;
}
</style>
</head>
<%
		Sign si= new Sign();
			  String[] args=null;
			   String access_token="";
			    CacheMgr cacheMgr=CacheMgr.getInstance();
			     Object object = cacheMgr.getCache("access_token"); 
			      if(object!=null){
			     	access_token=(String)object;
			     } 
			  String appid="wxcbc3a5073330147a";
			  String secret="eae3313909ca82bd0b39ac1391e5ccfb";
			    String apiurl2="https://api.weixin.qq.com/cgi-bin/ticket/getticket";
			     String type="jsapi";
			     String jsapi_ticket="";
			   Object object_ticket= cacheMgr.getCache("jsapi_ticket"); 
			      if(object_ticket!=null){
			     	jsapi_ticket=(String)object_ticket;
			     } 
			  String   basePatha   =   request.getScheme()+"://"+request.getServerName()+"/product/getProductsByMerchantId.do";  
			  String url=null;
			  if(request.getQueryString()==null||request.getQueryString()==""){
			  url=basePatha;}
			  else{url=basePatha+"?"+request.getQueryString();}
			  Map<String, String> ret=si.sign(jsapi_ticket,url);
	%>
<body style="background:#fff;">
	<div class="fenxiang">
            <a href="javascript:;"><img src="../mobile/images/fx.png"></a>
        </div>
	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
	<input type="hidden" name="tel" id="tel" value="${user.tel }">
	
    <div id="img-all">
    	<div class="swiper-container2">
            <div class="swiper-wrapper">
            	<div class="swiper-slide"><img src="../mobile/images/cyimg1.jpg"></div>
            	<div class="swiper-slide"><img src="../mobile/images/cyimg2.jpg"></div>
            	<div class="swiper-slide"><img src="../mobile/images/cyimg1.jpg"></div>
            	<div class="swiper-slide"><img src="../mobile/images/cyimg2.jpg"></div>
            </div>
        </div>
    </div>
    <c:if test="${m=='1'}">
					<script type="text/javascript">
						newaddress.ealert("您已收藏该商户");
					</script>
				</c:if>
				<c:if test="${m=='2'}">
					<script type="text/javascript">
						newaddress.ealert("收藏成功");
					</script>
				</c:if>
    <div id="all">
		<div class="jj-all">
			<ul class="jj-show">
				<c:forEach items="${merchant.pictures }" var="p" varStatus="v">
					<li><img src="../${p.path }"></li>
				</c:forEach>
			</ul>
			<div class="jj-xiao">
				<div class="swiper-container">
					<div class="swiper-wrapper">
						<c:forEach items="${merchant.pictures }" var="p" varStatus="v">
							<c:choose>
								<c:when test="${v.count==1 }">
									<div class="swiper-slide">
										<a href="#" class="cur"><img src="../${p.path }"> </a>
									</div>
								</c:when>
								<c:otherwise>
									<div class="swiper-slide">
										<a href="#"><img src="../${p.path }"> </a>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="jj-time">
				<p>${merchant.title }</p>
			</div>
			<a href="javascript:;" class="add-btn">${merchant.sightspot.name
				}</a>
			<div class="jj-btn2">
				<a href="javascript:;" onclick="fenxiang()"><img src="../mobile/images/jj-icon1.png"></a>
                <a href="../find/addCollect.do?merchant_id=${merchant.id}"><img src="../mobile/images/jj-icon2.png"></a>
             <a href="http://api.map.baidu.com/marker?location=${merchant.latitude},${merchant.longitude}&title=${merchant.title}&content=1${merchant.sub_title}&output=html&src=随便取"><img
					src="../mobile/images/jj-icon3.png"> </a>
			</div>
			<div class="tw-show">
				<div class="cunyou2">
					<p>详情</p>
					<a href="javascript:;">&nbsp;</a>
				</div>
				<div class="show2">
					<c:forEach items="${products }" var="p">
						<img src="../${p.path }">
						<p>${p.sub_title }</p>
					</c:forEach>
				</div>
				<div class="show-tit">
					<p>地图</p>
					<i>&nbsp;</i>
				</div>
				<div class="map">
					<img src="../mobile/images/show-img4.jpg" style="width:100%;">
				</div>
				<div class="show-tit">
					<p>评价</p>
					<i>&nbsp;</i>
				</div>
				<div class="pinglun">
					<c:forEach items="${merchant.evaluates }" var="e">
						<dl>
							<dt>
								<img src="../${e.path}">
								<p>${e.nickname }</p>
								<div>
									<c:forEach begin="1" end="${e.num}" var="num">
										<i class="cur">&nbsp;</i>
									</c:forEach>
									<c:if test="${e.num lt 5 }">
										<c:forEach begin="${e.num+1}" end="5" var="num">
											<i>&nbsp;</i>
										</c:forEach>
									</c:if>
								</div>
							</dt>
							<dd>
								<div>${e.content }</div>
								<p>
									<f:formatDate value="${e.createDate }" pattern="yyyy-MM-dd" />
								</p>
							</dd>
						</dl>
					</c:forEach>
				</div>
			</div>
			<div class="yd-show">
				<div class="cunyou2">
					<p>在线预定</p>
					<a href="javascript:;">&nbsp;</a>
				</div>
				<div class="cunyou3">
					<c:forEach items="${products }" var="p">
						<dl>
							<dt>
								<img src="../${p.path }">
							</dt>
							<dd>
								<h5>${p.title }</h5>
								<p>${p.sub_title }</p>
								<a href="#" onclick="checkPhone('${p.id}')">马上体验</a>
								<div>
									<del>￥${p.original_price }</del>
									<b>￥${p.favourable_price }</b>
								</div>
							</dd>
						</dl>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="phonetc" style="display:none;">
			<a href="javascript:;" class="zhe">&nbsp;</a>
			<c:if test="${!empty message  }">
				<script type="text/javascript">
					newaddress.ealert('${message }');
				</script>
			</c:if>
			<dl>
				<dt>手机验证</dt>
				<dd>
					<form action="../user/checkTelCode.do" method="post">
						<input type="hidden" name="product_id" id="product_id" value="">
						<div class="inputs souji">
							<input type="text" name="tel" id="tel" placeholder="请输入手机号码"><span
								class="yzmspan">还剩60秒</span><a href="javascript:;"
								class="yzmbtn">获取验证码</a>
						</div>
						<div class="inputs yzm">
							<input type="text" name="num" placeholder="请输入验证码">
						</div>
						<div class="submits">
							<input type="submit" value="确认">
						</div>
					</form>
				</dd>
			</dl>
		</div>
		<div class="bottom2">
			<div class="bottom2-nr">
				<a href="#" class="fanhui">&nbsp;</a> <a href="javascript:;"
					class="yuding">图文详情</a> <a href="javascript:;" class="tuwen">在线预定</a>
			</div>
		</div>

	</div>
	
    

	<script src="../mobile/js/swiper.min.js"></script>
	<script type="text/javascript">
		var swiper = new Swiper('.swiper-container', {
			pagination : '.swiper-pagination',
			slidesPerView : 5,
			paginationClickable : true,
		});
		
		var swiper = new Swiper('.swiper-container2', {
			pagination: '.swiper-pagination',
			paginationClickable: true
		});

    

		//图文详情
		$('.yuding').click(function() {
			$('.tw-show').slideDown();
			$('.yd-show').css("display", "none");
			$('.tw-show').css("display", "block");
		});
		$('.tuwen').click(function() {
			$('.yd-show').slideDown();
			$('.yd-show').css("display", "block");
			$('.tw-show').css("display", "none");
		});
		$('.cunyou2 a').click(function() {
			$(this).parent().parent().slideUp();
		});
	</script>
	<script type="text/javascript">
// 微信配置 
wx.config({
    debug: false, 
    appId: "wxcbc3a5073330147a", 
    timestamp: '<%=ret.get("timestamp")%>', 
    nonceStr: '<%=ret.get("nonceStr")%>', 
    signature: '<%=ret.get("signature")%>',
			jsApiList : [ 'onMenuShareTimeline', 'onMenuShareAppMessage' ]
		// 功能列表，我们要使用JS-SDK的什么功能
		});
		wx
				.ready(function() {
					// 1 判断当前版本是否支持指定 JS 接口，支持批量判断

					wx.checkJsApi({
						jsApiList : [ 'getNetworkType', 'previewImage' ],
					});
					// 2. 分享接口
					// 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
					wx
							.onMenuShareAppMessage({
								title : "聚乡村!",
								desc : "聚景！",
								link : "http://jxc.zheyishu.com/product/intoStart.do?state="+'${merchant.id}',
								imgUrl : "http://jxc.zheyishu.com"+'/${merchant.path}',
							});
					// 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
					wx
							.onMenuShareTimeline({
								title : "聚乡村！",
								link : "http://jxc.zheyishu.com/product/intoStart.do?state="+'${merchant.id}',
								imgUrl : "http://jxc.zheyishu.com"+'/${merchant.path}',
							});
				});
	</script>
	<script src="../mobile/js/js.js"></script>
</body>
</html>
