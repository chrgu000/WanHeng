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
    <ul><li><img src="images/zcbannerczl.jpg" /></li></ul>
  
</div>



<div class="page22">
    <div class="k-tit2"  style="color:#0D2645; padding:0; line-height:35px; font-weight:bold">押金<span style=" color:#FF7F00">1万元</span></div>
	<div class="k-tit2"  style="color:#0D2645; margin:0; font-weight:bold"> 提车便捷</div>
  	<div class="k-tit2"  style="color:#0D2645; margin:0; font-weight:bold"> 提供做<span style=" color:#FF7F00">滴滴账号</span>服务</div>
	<div class="k-tit2"  style="color:#0D2645; margin:0; font-weight:bold"> 公司配备车辆低至<span style=" color:#FF7F00">3500元/月</span></div>
	<div class="k-tit2"  style="color:#0D2645; margin:0; font-weight:bold"> 所有品牌任君选择</div>
	<div class="k-tit2"  style="color:#0D2645; margin:0; font-weight:bold"> 本人驾驶证<span style=" color:#FF7F00">1年以上</span></div>
	
</div>
<div class="banner" style="height:207px;margin-top:30px">

    <ul style="position:relative;"><li><img src="images/zcdibu.png" /></li>
		<div style="position:absolute;top:0">
			<div style="left:605px; position:absolute;width:150px; font-size:14px; font-weight:bold">所需资料</div>
			<div style="left:560px; position:absolute;width:200px;top:40px; font-size:14px; font-weight:bold">自然人（身份证、驾驶证）</div>
			<div style="left:590px; position:absolute;width:150px;top:80px; font-size:14px; font-weight:bold">无违法犯罪记录</div>
		</div>
	</ul>
  
</div>

	<%@include file="../index/foot.jsp"%>
	<div class="footer-bq">2016 All Rights
		Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>
	<div class="ce-nav">
		<a href="#" class="ce1"></a> <a href="#" class="ce2"></a> <a href="#"
			class="ce3"></a>
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
