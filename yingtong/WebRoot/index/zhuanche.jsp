<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>好买车</title>
<meta name="keywords" content="好买车" />
<meta name="description" content="好买车" />
<link href="css/public.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/js.js"></script>

<!--<script type="text/javascript" src="js/rili.js"></script>-->
<style type="text/css">
.page22{ overflow:hidden; padding-bottom:0px;}
.page22-list{ width:1110px; overflow:hidden; margin:0 auto;}
.page22-list dl{ width:29.8%; float:left; margin:35px 1.76% auto 1.76%; text-align:center;}
.page22-list dt{ height:252px;}
.page22-list dd span{ font-size:20px; color:#0D2645; line-height:30px; display:block; margin-top:20px;}
.page22-list dd p{ font-size:14px; color:#0D2645; line-height:24px; margin-top:10px;}
</style>
</head>

<body>
<div class="header">
		<div class="header-nr">
			<div class="header1">
				欢迎${user.username }光临盈通汽车
				<c:if test="${empty user }">，请<a href="../index/login.jsp">登录</a>或<a
						href="../user/toRegist.do">注册</a>
				</c:if>
			</div>
			<div class="header2">
				<c:if test="${!empty user }">
					<a href="javascript:;" class="my-zh">我的盈通</a>
					<ul>
						<li><a href="../order/showOrder.do">我的订单</a></li>
						<li><a href="../user/myInfo.do">我的帐户</a></li>
						<li><a href="../index/login_out.jsp"
							onclick="return confirm('您确定要退出吗？');" id="hd_logout_a">退出登录</a></li>
					</ul>
				</c:if>
			</div>
			<div class="header3">0571-86913303</div>
		</div>
	</div>
<div class="nav">
  <div class="nav-nr">
    <div class="logo"><a href="index.do"><img src="images/logo.png" /></a></div>
    <ul>
     	<li><a href="../index/index.do">首页</a></li>
		<li><a href="../car/duanzu.do">短租自驾</a></li>
			<li class="cur"><a href="../index/zhuanche.jsp">专车</a></li>
		<li><a href="../longRentService/longRentServiceIndex.do">长租</a>
		</li>
		<li><a href="../index/enterprise.jsp">企业租车</a></li>
		<li><a href="../order/showOrder.do">订单查询</a></li>
		<li><a href="../about/index.do?title_id=1">关于我们</a></li>
    </ul>
  </div>
</div>
<div class="banner" style="height:560px">
    <ul><li><img src="images/zcbanner.jpg" /></li></ul>
  
</div>



<div class="page22">
    <div class="k-tit2"  style="color:#0D2645">专车服务<i style="background:#F5A623">&nbsp;</i></div>
    <div class="page22-list">
    	<dl style="cursor:pointer">
        	<dt><img src="images/zcfuwu1.png" onclick="window.location='zcyizudaigou.jsp'"/></dt>
         
        </dl>
    	<dl  style="cursor:pointer">
        	<dt><img src="images/zcfuwu2.png" onclick="window.location='zcchunzulin.jsp'"/></dt>
        </dl>
    	<dl  style="cursor:pointer">
        	<dt><img src="images/zcfuwu3.png" onclick="window.location='zcsiji.jsp'"/></dt>
        </dl>
    </div>
</div>
<div class="banner" style="height:509px">
<div class="k-tit2"  style="color:#0D2645">专车优势<i  style="background:#F5A623">&nbsp;</i></div>
    <ul><li><img src="images/zcbanner2.jpg" /></li></ul>
</div>
<div>
<%@include file="../index/foot.jsp"%>
	<div class="footer-fx"><a href="#"><img src="images/icon-fx1.png" /></a><a href="#"><img src="images/icon-fx2.png" /></a><a href="#"><img src="images/icon-fx3.png" /></a><a href="#"><img src="images/icon-fx4.png" /></a></div>
    <div class="footer-bq">2016 All Rights Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>
</div>
<div class="ce-nav">
    <a href="#" class="ce1"></a>
    <a href="#" class="ce2"></a>
    <a href="#" class="ce3"></a>
</div>

<script type="text/javascript" src="laydate.dev.js"></script>
<script type="text/javascript">
laydate({
	elem: '#J-xl'
});
document.getElementById('J-xl-2').onclick = function(){
	laydate({
		elem: '#J-xl-2'
	});
}

laydate({
	elem: '#J-xl-3'
});

laydate({
	elem: document.getElementById('J-xl-4')
});
</script>
</body>
</html>
