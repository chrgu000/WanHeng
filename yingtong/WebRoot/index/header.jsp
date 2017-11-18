<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="header">
	<div class="header-nr">
		<div class="header1">
			欢迎${user.username }光临盈通汽车
			<c:if test="${empty user }">
				<a href="../index/login.jsp">，请登录</a>或
			<a href="../user/toRegist.do">注册</a>
			</c:if>
		</div>
		<div class="header2">
			<c:if test="${!empty user }">
				<a href="javascript:;" class="my-zh">我的盈通</a>
				<ul>
					<li><a href="../order/showOrder.do">我的订单</a>
					</li>
					<li><a href="../user/myInfo.do">我的帐户</a>
					</li>
					<li><a href="../index/login_out.jsp"
						onclick="return confirm('您确定要退出吗？');" id="hd_logout_a">退出登录</a>
					</li>
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