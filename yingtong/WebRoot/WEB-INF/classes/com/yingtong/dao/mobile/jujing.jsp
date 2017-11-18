<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Map"%>
<%@page import="com.jxc.cn.CacheMgr"%>
<%@page import="com.jxc.cn.Sign"%>
<%@page import="com.jxc.util.GetWX"%>
<link rel="stylesheet" type="text/css"
	href="../mobile/css/weixinshow.css">
<link rel="stylesheet" type="text/css" href="../mobile/css/css.css">
<link rel="stylesheet" href="../mobile/css/swiper.min.css">

<script src="../mobile/js/jquery-1.9.1.min.js">
	
</script>
<script src="../mobile/js/fastclick.js">
	
</script>
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
	$(document).ready(function() {
       $('.fenxiang').click(function(){
			$(this).fadeOut();
		});
    });
	

	function fenxiang(){
		$(".fenxiang").fadeIn();
	}
</script>
</head>
<body style="background: #fff;">

	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
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
			  String   basePatha   =   request.getScheme()+"://"+request.getServerName()+"/sightSpot/jujing.do";  
			  String url=null;
			  if(request.getQueryString()==null||request.getQueryString()==""){
			  url=basePatha;}
			  else{url=basePatha+"?"+request.getQueryString();}
			  Map<String, String> ret=si.sign(jsapi_ticket,url);
	%>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<div id="all">
    	<div class="fenxiang">
            <a href="javascript:;"><img src="../mobile/images/fx.png"></a>
        </div>
		<div class="jj-all">
			<ul class="jj-show">
				<c:forEach items="${merchants }" var="m">
					<li><img src="../${m.path }">
				</c:forEach>
				<c:if test="${m=='1'}">
					<script type="text/javascript">
						newaddress.ealert("您已收藏该景点");
					</script>
				</c:if>
				<c:if test="${m=='2'}">
					<script type="text/javascript">
						newaddress.ealert("收藏成功");
					</script>
				</c:if>
			</ul>
			
			<div class="jj-xiao">
				<div class="swiper-container">
					<div class="swiper-wrapper">
						<c:forEach items="${merchants }" var="m" varStatus="v">
							<c:if test="${v.count==1 }">
								<div class="swiper-slide">
									<a
										href="javascript:location.href='../product/getProductsByMerchantId.do?merchant_id=${m.id }';"
										class="cur"><img src="../${m.path }"> </a>
								</div>
							</c:if>
							<c:if test="${v.count>1 }">
								<div class="swiper-slide">
									<a
										href="javascript:location.href='../product/getProductsByMerchantId.do?merchant_id=${m.id }';"><img
										src="../${m.path }"> </a>
								</div>
							</c:if>

						</c:forEach>
					</div>
				</div>
			</div>
			<div class="jj-time">
				<p>${sightSpot.name }</p>
			</div>
			<a href="javascript:;" class="add-btn">地区切换</a>
			<div class="jj-btn">
				<a
					href="../sightSpot/jujing.do?title_id=4&sight_spot_id=${sightSpot.id}"
					<c:if test="${sightSpot.title_id=='4' }">class="cur"</c:if>>景点</a>
				<a
					href="../sightSpot/jujing.do?title_id=1&sight_spot_id=${sightSpot.id}"
					<c:if test="${sightSpot.title_id=='1' }">class="cur"</c:if>>村游</a>
				<a
					href="../sightSpot/jujing.do?title_id=2&sight_spot_id=${sightSpot.id}"
					<c:if test="${sightSpot.title_id=='2' }">class="cur"</c:if>>乡投</a>
				<a
					href="../sightSpot/jujing.do?title_id=3&sight_spot_id=${sightSpot.id}"
					<c:if test="${sightSpot.title_id=='3' }">class="cur"</c:if>>微农</a>
			</div>
			<div class="jj-btn2">
				<a href="javascript:;" onclick="fenxiang()"><img src="../mobile/images/jj-icon1.png"> </a> <a
					href="../find/addCollect.do?sight_spot_id=${sightSpot.id}"><img
					src="../mobile/images/jj-icon2.png"> </a> <a href="http://api.map.baidu.com/marker?location=${sightSpot.latitude},${sightSpot.longitude}&title=${sightSpot.name}&content=1${sightSpot.content}&output=html&src=随便取"><img
					src="../mobile/images/jj-icon3.png"> </a>
			</div>

			<div class="add-jjall">
				<div class="add-tit">
					<a href="javascript:;" class="add-qx">取消</a>
				</div>
				<div class="add-jj">
					<ul>
						<c:forEach items="${citys}" var="c" varStatus="v">
							<li <c:if test="${v.count==1}">class="cur"</c:if>><a
								href="javascript:getSightSpotsByCityId(${c.id});">${c.name}</a>
							</li>
						</c:forEach>
					</ul>
					<script type="text/javascript">
						function getSightSpotsByCityId(id) {
							$
									.ajax({
										type : "post",
										url : "../sightSpot/getSightSpotsByCityId.json",
										dataType : "json",
										data : {
											"city_id" : id
										},
										success : function(data) {
											$("#sightspots").html("");
											for ( var i = 0; i < data.length; i++) {
												var obj = data[i];
												if (i == 0) {
													$("#sightspots")
															.append(
																	"<li  class='cur'><a href='../sightSpot/jujing.do?sight_spot_id="
																			+ obj.id
																			+ "';>"
																			+ obj.name
																			+ "</a></li>");
												} else {
													$("#sightspots")
															.append(
																	"<li ><a href='../sightSpot/jujing.do?sight_spot_id="
																			+ obj.id
																			+ "';>"
																			+ obj.name
																			+ "</a></li>");
												}
											}
										},
										error : function() {
										}
									});
						}
					</script>
					<ul id="sightspots">
						<c:forEach items="${sightspots}" var="s" varStatus="v">
							<li <c:if test="${v.count==1}"> class="cur"</c:if>><a
								href="javascript:location.href='../sightSpot/jujing.do?sight_spot_id=${s.id}';">${s.name}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>

		</div>
		<div class="bottom">
			<ul>
				<li><a href="../merchant/cunyouIndex.do" class="btn1"><p>
							首页</p> </a></li>
				<li><a href="../find/find.do" class="btn2"><p>发现</p> </a></li>
				<li class="cur"><a href="../sightSpot/jujing.do" class="btn3"><p>
							聚景</p> </a></li>
				<li><a href="../mobile/center.jsp" class="btn4"><p>我的</p> </a></li>
			</ul>
		</div>

	</div>
	<script src="../mobile/js/swiper.min.js">
		
	</script>
	<script>
		var swiper = new Swiper('.swiper-container', {
			pagination : '.swiper-pagination',
			slidesPerView : 5,
			paginationClickable : true,
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
								link : "http://jxc.zheyishu.com/sightSpot/intoStart.do?state="+'${sightSpot.id}',
								imgUrl : "http://jxc.zheyishu.com"+'/${sightSpot.path}',
							});
					// 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
					wx
							.onMenuShareTimeline({
								title : "聚乡村！",
								link : "http://jxc.zheyishu.com/sightSpot/intoStart.do?state="+'${sightSpot.id}',
								imgUrl : "http://jxc.zheyishu.com"+'/${sightSpot.path}',
							});
				});
	</script>
	<script src="../mobile/js/js.js">
		
	</script>
</body>
</html>
