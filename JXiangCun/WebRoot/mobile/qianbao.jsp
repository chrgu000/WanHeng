<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

		<script src="../mobile/js/jquery-1.9.1.min.js">
</script>
		<script src="../mobile/js/fastclick.js">
</script>
		<script src="../mobile/js/WeixinApi.js">
</script>
		<script src="../mobile/js/jweixin-1.0.0.js">
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
function chongzhi() {
	$("#m").css("display", "block");
}
function addMoney(value) {
	location.href = "../order/addMoney.do?money=" + value;
}
</script>
	</head>
	<body>
		<DIV class="error none" style="text-align: center">
			<DIV class="warnMsg" style="text-align: center"></DIV>
		</DIV>
		<div id="all">
			<div class="qianbao">
				<dl>
					<dt>
						<img src="../mobile/images/qianbao.png">
					</dt>
					<dd>
						<span>￥${user.money }</span>
						<p>
							余额
						</p>
					</dd>
				</dl>
			</div>
			<div class="money1">
				<a href="#" onclick="chongzhi()">充值</a>
			</div>
			<div class="money2">
				<a href="#">提现</a>
			</div>
			<div class="bottom2">
				<div class="bottom2-nr">
					<a href="#" class="fanhui">&nbsp;</a>
				</div>
			</div>
			<div class="money-tc" id="m"
				<c:if test="${a==1}">style="display:block"</c:if>>
				<a href="javascript:;" class="zhe">&nbsp;</a>
				<dl>
					<dt>
						充值
					</dt>
					<dd>
						<input type="text" placeholder="请输入充值金额" id="money"
							value="${money}" onchange="addMoney(this.value)">
						<a href="#" id="weixinpay">确认</a>
					</dd>
				</dl>
			</div>

		</div>
		<%
			Sign si = new Sign();
			String access_token = "";
			CacheMgr cacheMgr = CacheMgr.getInstance();
			Object object = cacheMgr.getCache("access_token");
			if (object != null) {
				access_token = (String) object;
			}
			String jsapi_ticket = "";
			Object object_ticket = cacheMgr.getCache("jsapi_ticket");
			if (object_ticket != null) {
				jsapi_ticket = (String) object_ticket;
			}
			String basePatha = "http://" + GetWX.getPro("yuming")
					+ "/order/addMoney.do";
			String redirectUrlf = request.getRequestURI();
			String url = null;
			if (request.getQueryString() == null
					|| request.getQueryString() == "") {
				url = basePatha/* +redirectUrlf */;
			} else {
				url = basePatha/* +redirectUrlf */+ "?"
						+ request.getQueryString();
			}
			//System.out.println(url);
			Map<String, String> ret = si.sign(jsapi_ticket, url);
		%>
		<script type="text/javascript">
// 微信配置
wx.config({
	    debug:false, 
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
            	   "appId" : '${order.appId}',
                   "timeStamp":'${order.timeStamp}', 
                   "nonceStr" : '${order.nonceStr}',
                   "package" : '${order.packageValue}',
                   "signType" : "MD5",
                   "paySign" :  '${order.paySign}'
               
               },function(res){                        
               if(res.err_msg == "get_brand_wcpay_request:ok" ){
            	   $.ajax({type:"post",url:"../order/updateMoney.json",dataType:"json",data:{"money":$("#money").val()},success:function(data){
            		   alert(data);
            		if(data==1){
            			newaddress.ealert("充值成功!");
            		}   
            	   },error:function(){}});
               }else if(res.err_msg == "get_brand_wcpay_request:cancel" ){
               	newaddress.ealert("充值取消");
				$("#weixinpay").removeAttr("disabled");
               }else {
               	newaddress.ealert("充值失败");
				$("#weixinpay").removeAttr("disabled");
			}
           });  
    });
});
</script>
		<script src="../mobile/js/js.js">
</script>
	</body>
</html>
