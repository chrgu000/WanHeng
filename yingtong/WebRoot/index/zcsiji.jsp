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
    <ul><li><img src="images/zcsjbanner.jpg" /></li></ul>
  
</div>



<div class="page22" style="width:1000px; margin:0 auto">
    <div class="k-tit2"  style="color:#0D2645; padding:0; line-height:35px; font-weight:bold">你想成为超级司机吗，快来加入<span style=" color:#FF7F00">盈通专车</span>吧！</div>
	<div class="k-tit2"  style="color:#0D2645; margin:0; font-weight:bold"> 你将面对超级客户：商务精英和社会名人；</div>
  	<div class="k-tit2"  style="color:#0D2645; margin:0; font-weight:bold"> 你将获得超级待遇：<span style=" color:#FF7F00">月薪过万，保险全交；</span></div>
	<div class="k-tit2"  style="color:#0D2645; margin:0; font-weight:bold"> 费用透明，无隐形收费情况。</div>
	<div class="k-tit2"  style="color:#0D2645; margin:0; font-weight:bold"> 你将拥有超级办公室：公司统一采购并配置的<span style=" color:#FF7F00">中高端全新车辆</span>，如宝马、GL8、本田艾力绅、本田CR-V、帕萨特、凯美瑞等；</div>
	<div class="k-tit2"  style="color:#0D2645; margin:0; font-weight:bold"> 你将加入超级大家庭：我们有自己的家文化，全国各地数万名兄弟姐妹都是我们的家人；</div>
	<div class="k-tit2"  style="color:#0D2645; margin:0; font-weight:bold"> 你将迎来超级前景：专车是未来的主流出行方式，我们没有私家车也没有兼职司机！我们有的是安全、便捷、舒适的客户体验，还有工作的稳定和未来的发展机会。</div>
	
	<div class="k-tit2"  style="color:#0D2645; margin:0; font-weight:bold">我们没有苛刻的要求，只需要你有C1型以上驾照（驾龄3年及以上）并熟悉当地路况，再加上一颗爱车爱客户的心，那么你就是我们要找的超级司机！</div>
</div>
<div class="banner" style="height:207px;margin-top:30px">

    <ul style="position:relative;"><li><img src="images/zcdibu.png" /></li>
		<div style="position:absolute;top:0">
			<div style="left:505px; position:absolute;width:350px; font-size:14px; font-weight:bold">面试地址：滨江区江虹路1750号信雅达国际A座806</div>
			<div style="left:520px; position:absolute;width:400px;top:40px; font-size:14px; font-weight:bold">联系电话：17757121110，0571-86913303</div>
			<div style="left:490px; position:absolute;width:470px;top:80px; font-size:14px; font-weight:bold">面试时间：周一-周日上午9:30-12:00  下午：14:00-15:30</div>
			<div style="left:560px; position:absolute;width:470px;top:120px; font-size:14px; font-weight:bold">乘车路线：340康顺路科技馆街口下</div>
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
