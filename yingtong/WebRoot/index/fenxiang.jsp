<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Map"%>
<%@page import="com.yingtong.cn.CacheMgr"%>
<%@page import="com.yingtong.cn.Sign"%>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />
<title>盈通租车</title>
<link rel="stylesheet" href="disc/css/zepto.fullpage.css">
<link rel="stylesheet" href="disc/css/global.css">
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="disc/wx/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="disc/common/js/page.js"></script>
<script src="disc/lib/zepto.min.js"></script>
<script src="disc/lib/zepto.fullpage.js"></script>
<script>
		var phoneWidth = parseInt(window.screen.width);
		var phoneScale = phoneWidth/720;
		
		var ua = navigator.userAgent;
		if (/Android (\d+\.\d+)/.test(ua)){
		var version = parseFloat(RegExp.$1);
		// andriod 2.3
		if(version>2.3){
			document.write('<meta name="viewport" content="width=640, minimum-scale = '+phoneScale+', maximum-scale = '+phoneScale+', target-densitydpi=device-dpi">');
		// andriod 2.3以上
		}else{
			document.write('<meta name="viewport" content="width=640, target-densitydpi=device-dpi">');
		}
		// 其他系统
		} else {
		document.write('<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">');
		}
	var startPage = isNaN(parseInt(Page.getParameter('p'))) ? (location.hostname == '192.168.1.100' ? 10:0):parseInt(Page.getParameter('p'));
	//startPage = 0;
	var lockMoving = false;
	
	function bootstrap()
	{		
		$('.wp').show();
		$('.wp').css({opacity:1}, 1000);
			
		$('.cover').animate({opacity:0}, 200, 'easeOut', function(){
			
			$('.cover').remove();
			
			$('.wp-inner').fullpage({
				drag: true,
				start: startPage,
				duration: 300,
				page: '.section',
				loop:true,
				change:function(o)
				{
					call('change'+o.next,o);
				},
				afterChange:function(o)
				{
					var removeIndex,addIndex;
					removeIndex = o.cur;
					addIndex = o.next;
					
					if( removeIndex != -1 )
					{
						$('.section').eq(removeIndex).find('img').each(function(index, element) {
							$(element).addClass( 'hide' );
						});
						$('.section').eq(removeIndex).find('div').each(function(index, element) {
							$(element).addClass( 'hide' );
						});
					}
					
					if( addIndex != -1 )
					{
						$('.section').eq(addIndex).find('img').each(function(index, element) {
							$(element).removeClass( 'hide' );
						});
						$('.section').eq(addIndex).find('div').each(function(index, element) {
							$(element).removeClass( 'hide' );
						});
					}
					
					call('afterChange'+o.next,o);
				}
			});
			
		});
	}
	
	
		
	function call(f,o)
	{
		var valid = true;
		try{ eval(f) }
		catch(e)
		{valid=false;return}
		finally
		{if(!valid) return}
		if( typeof(eval(f)) != undefined )
		{
			(window[f]).apply(this,[o]);
		}
	}
	
	
	var sound;
  </script>
<style type="text/css">
.cover {
	width: 100%;
	height: 100%;
}

.wp {
	display: none;
}
</style>
</head>

<body onLoad="bootstrap()">

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


	<div class="cover">
		<img src="images/loding.gif">
	</div>
	<div class="wp">
		<div class="wp-inner">
			<!--page1-->
			<div class="section page page1">
				<img class="pos-abs hide logo" src="images/top.png" /> <img
					class="pos-abs hide p1_1" src="images/font.png" /> <img
					class="pos-abs hide p1_2" src="images/fu.png" />
			</div>

			<!--品牌介绍-->
			<div class="section page page2">
				<img class="pos-abs hide logo2" src="images/logo.png" /> <img
					class="pos-abs hide p2_1" src="images/p1_1.png" /> <img
					class="pos-abs hide p2_2" src="images/p1-2.png" /> <img
					class="pos-abs hide p2_21" src="images/tm2_you.png" /> <img
					class="pos-abs hide p2_22" src="images/footbg.png" /> <img
					class="pos-abs hide p2_23" src="images/footfu.png" />
			</div>

			<!--产业链-->
			<div class="section page page3">
				<img class="pos-abs hide logo2" src="images/logo.png" /> <img
					class="pos-abs hide p3_1" src="images/p3-1.png" />
				<div class="pos-abs hide p3">
					<img class="pos-abs hide p2_2" src="images/p2-2.png" /> <img
						class="pos-abs hide p2_21" src="images/p2-21.png" /> <img
						class="pos-abs hide p2_22" src="images/p2-22.png" /> <img
						class="pos-abs hide p2_23" src="images/p2-23.png" /> <img
						class="pos-abs hide p2_24" src="images/p2-24.png" /> <img
						class="pos-abs hide p2_25" src="images/p2-25.png" /> <img
						class="pos-abs hide p2_26" src="images/p2-26.png" /> <img
						class="pos-abs hide p2_27" src="images/p2-27.png" /> <img
						class="pos-abs hide p2_28" src="images/p2-28.png" />
				</div>
				<img class="pos-abs hide p3_2" src="images/footbg.png" /> <img
					class="pos-abs hide p3_21" src="images/footfu.png" />
			</div>


			<!--荣誉证书-->
			<div class="section page page3">
				<img class="pos-abs hide logo2" src="images/logo.png" /> <img
					class="pos-abs hide p3_1 p3_10" src="images/ry.png" /> <img
					class="pos-abs hide p3_11" src="images/ryfont.png" /> <img
					class="pos-abs hide p3_12" src="images/p3-2.png" /> <img
					class="pos-abs hide p3_2" src="images/footbg.png" /> <img
					class="pos-abs hide p3_21" src="images/footfu.png" />
			</div>




			<!--玉米油-->
			<div class="section page page14">
				<img class="pos-abs hide logo3" src="images/logo2.png" /> <img
					class="pos-abs hide p12_1" src="images/p14_font.png" /> <img
					class="pos-abs hide p11_222" src="images/yumi.png" />
				<div class="pos-abs hide p4_1">
					<img class="pos-abs hide p14_2" src="images/p14_1.png" />
				</div>
			</div>

			<!--葵花籽油-->
			<div class="section page page5">
				<img class="pos-abs hide logo3" src="images/logo2.png" /> <img
					class="pos-abs hide p5_1" src="images/p5_left.png" /> <img
					class="pos-abs hide p5_21" src="images/p5_font.png" /> <img
					class="pos-abs hide p5_23" src="images/p5_fontb.png" /> <img
					class="pos-abs hide p5_22" src="images/p5_h.png" /> <img
					class="pos-abs hide p4_24" src="images/p4_spu.png" />
			</div>


			<!--天天五谷-->
			<div class="section page page4">
				<img class="pos-abs hide logo3" src="images/logo2.png" />

				<div class="pos-abs hide p4_1">
					<img class="pos-abs  p4_12" src="images/p4_left.png" />
				</div>

				<img class="pos-abs hide p4_21" src="images/p4_font.png" /> <img
					class="pos-abs hide p4_22" src="images/p4_shui.png" /> <img
					class="pos-abs hide p4_23" src="images/p4_fontb.png" /> <img
					class="pos-abs hide p4_24" src="images/p4_spu.png" />
			</div>


			<!--菜籽油-->
			<div class="section page page8">
				<img class="pos-abs hide logo3" src="images/logo2.png" /> <img
					class="pos-abs hide p8_1" src="images/tm5_1.png" /> <img
					class="pos-abs hide p8_21" src="images/tm5_2.png" /> <img
					class="pos-abs hide p8_23" src="images/tm5_zi.png" /> <img
					class="pos-abs hide p4_24" src="images/p4_spukh.png" />
			</div>


			<!--花生油-->
			<div class="section page page6">
				<img class="pos-abs hide logo3" src="images/logo2.png" /> <img
					class="pos-abs hide p6_1" src="images/p6_left.png" /> <img
					class="pos-abs hide p6_21" src="images/p6_font.png" /> <img
					class="pos-abs hide p6_22" src="images/p6_qiang.png" /> <img
					class="pos-abs hide p6_23" src="images/p6_fontb.png" /> <img
					class="pos-abs hide p4_24" src="images/p4_spuhs.png" />
			</div>


			<!--大豆油-->
			<div class="section page page12">
				<img class="pos-abs hide logo3" src="images/logo2.png" /> <img
					class="pos-abs hide p12_1" src="images/tm9_1.png" /> <img
					class="pos-abs hide p11_222" src="images/tm9_zi.png" /> <img
					class="pos-abs hide p12_2" src="images/tm9_2.png" /> <img
					class="pos-abs hide p4_24" src="images/p4_spudd.png" /> <img
					src="images/bottom.png" class="pos-abs hide p11_23">
			</div>




			<!--小莫香油-->
			<div class="section page page9">
				<img class="pos-abs hide logo3" src="images/logo2.png" /> <img
					class="pos-abs hide p9_1" src="images/tm10_1.png" /> <img
					class="pos-abs hide p9_21" src="images/tm10_2.png" /> <img
					class="pos-abs hide p9_22" src="images/tm10_zi.png" /> <img
					class="pos-abs hide p4_24" src="images/p4_spuzm.png" />
			</div>


			<!--稻米油-->
			<div class="section page page7">
				<img class="pos-abs hide logo3" src="images/logo2.png" /> <img
					class="pos-abs hide p7_1" src="images/p7-1.png" /> <img
					class="pos-abs hide p7_21" src="images/p7-3.png" /> <img
					class="pos-abs hide p7_22" src="images/p7-2.png" /> <img
					class="pos-abs hide p7_24" src="images/p7-24.png" /> <img
					class="pos-abs hide p4_24" src="images/p4_spudm.png" />

			</div>


			<!--安达露西-->
			<div class="section page page11">
				<img class="pos-abs hide logo3" src="images/logo2.png" /> <img
					class="pos-abs hide p11_21" src="images/tm8_1.png" /> <img
					class="pos-abs hide p11_221" src="images/tm8_2.png" /> <img
					class="pos-abs hide p11_222" src="images/tm8_zi.png" /> <img
					class="pos-abs hide p4_24" src="images/p4_sputk.png" /> <img
					src="images/bottom.png" class="pos-abs hide p11_23">
			</div>

			<!--橄榄油喷雾装-->
			<div class="section page page10">
				<img class="pos-abs hide logo3" src="images/logo2.png" /> <img
					class="pos-abs hide p10_1" src="images/tm7_1.png" /> <img
					class="pos-abs hide p10_21" src="images/tm7_2.png" /> <img
					class="pos-abs hide p10_22" src="images/tm7_zi.png" /> <img
					class="pos-abs hide p10_23" src="images/tm7_left.png" /> <img
					class="pos-abs hide p10_24" src="images/tm7_right.png" /> <img
					class="pos-abs hide p4_24" src="images/p4_spugl.png" />
			</div>


			<!--白糖	-->
			<div class="section page page15">
				<img class="pos-abs hide logo3" src="images/logo2.png" /> <img
					class="pos-abs hide p11_21" src="images/p15fonttop.png" /> <img
					class="pos-abs hide p11_211" src="images/p15right.png" /> <img
					class="pos-abs hide p11_221" src="images/p15left.png" /> <img
					class="pos-abs hide p11_222" src="images/p15font%20.png" /> <img
					class="pos-abs hide p4_24" src="images/pfontb.png" />
			</div>



			<div class="section page page13">
				<img class="pos-abs hide logo" src="images/logo.png" /> <img
					class="pos-abs hide p13_1" src="images/lastyun.png" /> <img
					class="pos-abs hide p13_2" src="images/last_font.png" /> <img
					class="pos-abs hide p13_222" src="images/lastcent.png" /> <img
					class="pos-abs hide p13_3" onClick="Page.refresh();"
					src="images/lasts.png" />
			</div>
		</div>
	</div>
	<span class="start"><b></b> </span>

	<div style="display:none">
		<audio src="disc/m.mp3"></audio>
	</div>

	<script type="text/javascript">
		
	function change0()
	{
		if( !sound ) 
		{
			sound = new Audio();
			sound.autoplay = true;
			sound.preload = true;
			sound.loop = true;
			sound.src = 'disc/m.mp3';
			sound.play()
		}

	}	
    </script>

	<script src="js/jquery-1.7.2.js"></script>

	<script type="text/javascript">
// 微信配置 
wx.config({
    debug: true, 
    appId: "wx7fd1f69e1fbcfb20", 
    timestamp: '<%=ret.get("timestamp")%>', 
    nonceStr: '<%=ret.get("nonceStr")%>', 
    signature: '<%=ret.get("signature")%>
		',
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
								title : "中粮产品 安全放心 粽享幸福味道",
								desc : "端午节送什么？点开就知道！",
								link : "http://events.the1stmedia.com/fulinmen/index.jsp",
								imgUrl : "http://events.the1stmedia.com/fulinmen/images/logo.jpg",
							});
					// 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
					wx
							.onMenuShareTimeline({
								title : "端午节送什么？点开就知道！",
								link : "http://events.the1stmedia.com/fulinmen/index.jsp",
								imgUrl : "http://events.the1stmedia.com/fulinmen/images/logo.jpg",
							});
				});
	</script>
</body>
</html>