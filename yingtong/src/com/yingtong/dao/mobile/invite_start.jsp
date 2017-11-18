<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Map"%>
<%@page import="com.jxc.cn.CacheMgr"%>
<%@page import="com.jxc.cn.Sign"%>
<%@page import="com.jxc.util.GetWX"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"
	name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>聚乡村</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/disc/css/zepto.fullpage.css"
	type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/disc/css/global.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/swiper.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/mobile/css/css.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/mobile/css/style.css">
	<script src="${pageContext.request.contextPath}/mobile/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/mobile/disc/wx/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/mobile/disc/common/js/page.js"></script>
<script src="${pageContext.request.contextPath}/mobile/disc/lib/zepto.min.js"></script>
<script src="${pageContext.request.contextPath}/mobile/disc/lib/zepto.fullpage.js"></script>
<script src="${pageContext.request.contextPath}/mobile/js/fastclick.js"></script>
<style type="text/css">
.cover{width:100%;height:100%;}
.wp{display:none;}
</style>
</head>
<body>
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
			  String   basePatha   =   request.getScheme()+"://"+request.getServerName()+"/order/addOrder.do";  
			  String url=null;
			  if(request.getQueryString()==null||request.getQueryString()==""){
			  url=basePatha;}
			  else{url=basePatha+"?"+request.getQueryString();}
			  Map<String, String> ret=si.sign(jsapi_ticket,url);
	%>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<div id="all">
		<div class="yiyan1">
			<p>${product.merchant.title }</p>
		</div>
		<div class="yiyan2">
			<h5>${product.title }</h5>
			<!--     	<p>入住 07月10日   入住 07月10日   共3晚</p> -->
			<i>邀请${order.free_num }人支持免单</i> <span><fmt:formatDate
					value="${order.endDate }" pattern="yyyy-Mm-dd" />截止,体验前请电话预约</span>
		</div>

		<div class="yaoqing2">
			<span>免费体验(流程)</span>
			<ul>
				<li><i>1</i>
					<p>选商品</p></li>
				<li ><i>2</i>
					<p>提交</p></li>
				<li class="cur"><i>3</i>
					<p>邀请好友</p></li>
				<li><i>4</i>
					<p>
						达到人数<br>免单成功
					</p></li>
			</ul>
		</div>
		<div class="order-show">
			<div class="order5" style="border-top:0;">
				<p>
					还差${order.free_num }人，
					<fmt:formatDate value="${order.endDate }" pattern="yyyy-MM-dd" />
					截止
				</p>
			</div>
		</div>
		<div class="bottom2">
			<div class="bottom2-nr">
				<a href="#" class="fanhui">&nbsp;</a>
				<p class="haixu">还需邀请${order.spare_num }人</p>
				<a href="#" class="yuding">邀请好友</a>
			</div>
		</div>
		<!--弹窗-->
		<div class="yq-tanc">
			<a href="javascript:;" class="zhe">&nbsp;</a>
			<dl>
				<dt>${user.nickname }发起了邀请，点支持就能让他免单</dt>
				<dd>
					<div class="yqtc01">
						<div class="yqtc02">
							<img src="../${product.path }">
						</div>
						<div class="yqtc03">
							<span>${product.title }</span>
							<p>${product.sub_title }</p>
							<i>￥<em>0</em>&nbsp;邀请${order.free_num }人支持免单</i>
						</div>
					</div>
					<a href="#" class="tc-btn">支持</a>
				</dd>
			</dl>
		</div>
	</div>
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
								desc : "支持我,帮我免单！",
								link : "http://jxc.zheyishu.com/order/invite.do?order_id="+'${order.id}',
								imgUrl : "http://jxc.zheyishu.com"+'/${product.path}',
							});
					// 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
					wx
							.onMenuShareTimeline({
								title : "聚乡村！",
								link : "http://jxc.zheyishu.com/order/invite.do?order_id="+'${order.id}',
								imgUrl : "http://jxc.zheyishu.com"+'/${product.path}',
							});
				});
	</script>
	<script src="${pageContext.request.contextPath}/mobile/js/js.js"></script>
</body>
</html>
