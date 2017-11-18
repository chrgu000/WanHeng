<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盈通租车公司介绍_关于我们</title>
<meta name="keywords" content="好买车" />
<meta name="description" content="好买车" />
<link href="../index/css/public.css" rel="stylesheet" type="text/css" />
<link href="../index/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../index/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="../index/js/js.js"></script>
</head>
<body style="background:#f6f8fa;">
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
			<div class="logo">
				<a href="../index/index.do"><img src="../index/images/logo.png" />
				</a>
			</div>
			<ul>
				<li><a href="../index/index.do">首页</a></li>
				<li><a href="../car/duanzu.do">短租自驾</a></li>
								<li><a href="../index/zhuanche.jsp">专车</a></li>
				<li><a href="../longRentService/longRentServiceIndex.do">长租</a>
				</li>
				<li><a href="../index/enterprise.jsp">企业租车</a></li>
				<li><a href="../order/showOrder.do">订单查询</a></li>
				<li class="cur"><a href="../about/index.do?title_id=1">关于我们</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="ny-all">
		<div class="ny-left">
			<dl>
				<dt>
					<a href="#">关于</a>
				</dt>
				<dd>
					<a href="javascript:location.href='../about/index.do?id=17';">关于我们</a>
				</dd>
				<dd>
					<a href="javascript:location.href='../about/index.do?id=18';">联系我们</a>
				</dd>
			</dl>
			<dl>
				<dt>
					<a href="#">租车说明</a>
				</dt>
				<dd>
					<a href="javascript:location.href='../about/index.do?id=19';">服务时间</a>
				</dd>
				<dd>
					<a href="javascript:location.href='../about/index.do?id=20';">取还车说明</a>
				</dd>
			</dl>
			<dl>
				<dt>
					<a href="#">租车费用</a>
				</dt>
				<dd>
					<a href="javascript:location.href='../about/index.do?id=21';">价格说明</a>
				</dd>
				<dd>
					<a href="javascript:location.href='../about/index.do?id=22';">结算流程</a>
				</dd>
			</dl>
			<dl>
				<dt>
					<a href="#">会员管理</a>
				</dt>
				<dd>
					<a href="javascript:location.href='../about/index.do?id=23';">会员章程</a>
				</dd>
			</dl>
			<dl>
				<dt>
					<a href="#">帮助</a>
				</dt>
				<dd>
					<a href="javascript:location.href='../about/index.do?id=25';">新手上路</a>
				</dd>
				<dd>
					<a href="javascript:location.href='../about/index.do?id=26';">常见问题</a>
				</dd>
				<dd>
					<a href="javascript:location.href='../about/index.do?id=28';">服务规则</a>
				</dd>
			</dl>
		</div>
		<div class="ny-right">
			<div class="neiwen">
				<p>${article.content}</p>
			</div>
		</div>
	</div>
	<div class="footer-fx">
		<a href="#"><img src="../index/images/icon-fx1.png" /> </a><a
			href="#"><img src="../index/images/icon-fx2.png" /> </a><a href="#"><img
			src="../index/images/icon-fx3.png" /> </a><a href="#"><img
			src="../index/images/icon-fx4.png" /> </a>
	</div>
	<div class="footer-bq">2016 All Rights
		Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>
</body>
</html>
