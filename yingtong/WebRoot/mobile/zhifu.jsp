<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Map"%>
<%@page import="com.yingtong.cn.CacheMgr"%>
<%@page import="com.yingtong.cn.Sign"%>
<%@page import="com.yingtong.util.GetWX"%>
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
<script src="../mobile/js/WeixinApi.js"></script>
<script src="../mobile/js/jweixin-1.0.0.js"></script>
<%--<script type="text/javascript">--%>
<%--	function send() {--%>
<%--		$("#form").submit();--%>
<%--	}--%>
<%--</script>--%>
</head>
<body>
	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
	<SCRIPT>
var newaddress = {}
newaddress.ealert = function(msg) {
		$('.warnMsg').html(msg);
		$('.error').show();
		$('.error').fadeIn(400).delay(1500).fadeOut(400);
	}
newaddress.eclear = function() {
	$('.warnMsg').html('');
}
</SCRIPT>
	<div id="all">
		<div class="tit">
			<p>费用明细</p>
		</div>
		<div class="zhifu">
<%--		<a href="#" onclick="send()">支付宝支付<i>&nbsp;</i> </a>--%>
		 <a href="javascript:;" id="weixinpay">微信支付<i>&nbsp;</i> </a>
		</div>
		<div class="qd-bottom">
			<div class="qd-bottomnr">
				<p>
					支付金额:<span><i>￥</i>${orderzf.total_price }</span>
				</p>
<%--				<form action="../index/alipayapi.jsp" method="post" id="form">--%>
<%--					<input type="hidden" name="order_num" value="${orderzf.order_num }" />--%>
<%--					<input type="hidden" name="total_price"--%>
<%--						value="${orderzf.total_price }" /> <input type="hidden"--%>
<%--						name="tel" value="${orderzf.user.tel }" /> --%>
<%--				</form>--%>
			</div>
		</div>
	</div>
	<%
		Sign si= new Sign();
		   String access_token="";
		    CacheMgr cacheMgr=CacheMgr.getInstance();
		     Object object = cacheMgr.getCache("access_token"); 
		      if(object!=null){
		     	access_token=(String)object;
		     } 
		  String jsapi_ticket="";
		   Object object_ticket= cacheMgr.getCache("jsapi_ticket"); 
		      if(object_ticket!=null){
		     	jsapi_ticket=(String)object_ticket;
		     } 
		   String basePatha = "http://"+GetWX.getPro("yuming")+"/order/zhifuM.do";
		  String redirectUrlf=request.getRequestURI();
		  String url=null;
		  if(request.getQueryString()==null||request.getQueryString()==""){
		  url=basePatha/* +redirectUrlf */;}
		  else{url=basePatha/* +redirectUrlf */+"?"+request.getQueryString();}
		  //System.out.println(url);
		  Map<String, String> ret=si.sign(jsapi_ticket,url);
	%>
	<script type="text/javascript">
	// 微信配置
	wx.config({
	    debug: false, 
	    appId: "<%=GetWX.getPro("appid")%>", 
	    timestamp: '<%=ret.get("timestamp")%>', 
	    nonceStr: '<%=ret.get("nonceStr")%>', 
	    signature: '<%=ret.get("signature")%>',
		jsApiList : [ 'hideOptionMenu']
			// 功能列表，我们要使用JS-SDK的什么功能
	});
	wx.ready(function() {
		// 1 判断当前版本是否支持指定 JS 接口，支持批量判断
		wx.checkJsApi({
			jsApiList : [ 'hideOptionMenu'],
		});
		wx.hideOptionMenu();
	});
</script>
	<script type="text/javascript">
$(document).ready(function() {
	$("#weixinpay").click(function(){
           $("#weixinpay").attr("disabled","true");
           if(parseInt(${ordergroup.agent})<5){
               newaddress.ealert("您的微信版本低于5.0无法使用微信支付");
			$("#weixinpay").removeAttr("disabled");
               return false;
           }
           WeixinJSBridge.invoke('getBrandWCPayRequest',{  
<%--               "appId" : "wx7fd1f69e1fbcfb20",--%>
<%--               "timeStamp":"1469711925092", --%>
<%--               "nonceStr" : "gvsFWLQLTOnZGM1l",--%>
<%--               "package" : "prepay_id=wx20160728211845f40975714b0378670819",--%>
<%--               "signType" : "MD5",--%>
<%--               "paySign" :  "29C7D988DC90170200A32BDE84EF325E"--%>
            	   "appId" : '${orderzf.appId}',
                   "timeStamp":'${orderzf.timeStamp}', 
                   "nonceStr" : '${orderzf.nonceStr}',
                   "package" : '${orderzf.packageValue}',
                   "signType" : "MD5",
                   "paySign" :  '${orderzf.paySign}'
               
               },function(res){                        
               if(res.err_msg == "get_brand_wcpay_request:ok" ){
                   window.location.href='${orderzf.sendUrl}';
               }else if(res.err_msg == "get_brand_wcpay_request:cancel" ){
               	newaddress.ealert("支付取消");
				$("#weixinpay").removeAttr("disabled");
               }else {
               	newaddress.ealert("支付失败");
				$("#weixinpay").removeAttr("disabled");
			}
           });  
    });
});
</script>
</body>
</html>
