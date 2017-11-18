<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Map"%>
<%@page import="com.yingtong.cn.CacheMgr"%>
<%@page import="com.yingtong.cn.Sign"%>
<%@page import="com.yingtong.util.GetWX"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盈通汽车</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" /> 
<meta content="black" name="apple-mobile-web-app-status-bar-style" /> 
<meta content="telephone=no" name="format-detection" />
    <link rel="stylesheet" href="disc/css/zepto.fullpage.css" type="text/css" >
    <link rel="stylesheet" href="disc/css/global.css" type="text/css" >
    <script type="text/javascript" src="disc/wx/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="disc/common/js/page.js"></script>
    <script src="disc/lib/zepto.min.js"></script>
    <script src="disc/lib/zepto.fullpage.js"></script>
<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" href="css/swiper.min.css">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/fastclick.js"></script>
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
		  String appid="wx7fd1f69e1fbcfb20";
		  String secret="004ce85fcb2de56d63c4b0c90378ff08";
		    String apiurl2="https://api.weixin.qq.com/cgi-bin/ticket/getticket";
		     String type="jsapi";
		     String jsapi_ticket="";
		   Object object_ticket= cacheMgr.getCache("jsapi_ticket"); 
		      if(object_ticket!=null){
		     	jsapi_ticket=(String)object_ticket;
		     } 
		  String   basePatha   =   request.getScheme()+"://"+request.getServerName()+"";  
		  String redirectUrlf=request.getRequestURI();
		  String url=null;
		  if(request.getQueryString()==null||request.getQueryString()==""){
		  url=basePatha+redirectUrlf;}
		  else{url=basePatha+redirectUrlf+"?"+request.getQueryString();}
		  Map<String, String> ret=si.sign(jsapi_ticket,url);
	%>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<div id="all">
    <div class="banner">
    	<div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide"><img src="images/banner.png" /></div>
                <div class="swiper-slide" onclick="window.location.href='zhuanche.jsp'"><img src="images/zcbanner.jpg" /></div>
            </div>
            <!-- Add Pagination -->
            <div class="swiper-pagination"></div>
        </div>
    </div>
    
    <div class="page">
    	<a href="../car/duanzu1.do"><dl><dt><img src="images/page1.png"></dt><dd>短租自驾</dd></dl></a>
    	<a href="../longRentService/longRentServiceIndex1.do"><dl><dt><img src="images/page2.png"></dt><dd>长租服务</dd></dl></a>
    	<a href="../enterprise/toAddEnterprise1.do"><dl><dt><img src="images/page3.png"></dt><dd>企业租车</dd></dl></a>
    	<a tel="0571-86913303"><dl><dt><img src="images/page4.png"></dt><dd>客服热线</dd></dl></a>
    	<a href="about.jsp"><dl><dt><img src="images/page5.png"></dt><dd>关于我们</dd></dl></a>
    	<a href="../user/center.do"><dl><dt><img src="images/page6.png"></dt><dd>个人中心</dd></dl></a>
    </div>
    
</div>
<script src="js/swiper.min.js"></script>
<script>
var swiper = new Swiper('.swiper-container', {
	pagination: '.swiper-pagination',
	paginationClickable: true,
	loop: true,
	autoplay:5000
});
</script>
<script src="js/js.js"></script>
<script src="js/jquery-1.10.1.min.js"></script>

<script type="text/javascript">
// 微信配置 
wx.config({
    debug: false, 
    appId: "wx7fd1f69e1fbcfb20", 
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
								title : "盈通租车",
								desc : "诚信第一，优质服务！",
								link : "http://www.yt-car.cn/mobile/index.jsp",
								imgUrl : "http://www.yt-car.cn/mobile/images/banner.png",
							});
					// 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
					wx
							.onMenuShareTimeline({
								title : "盈通租车",
								link : "http://www.yt-car.cn/mobile/index.jsp",
								imgUrl : "http://www.yt-car.cn/mobile/images/banner.png",
							});
				});
	</script>
</body>
</html>
