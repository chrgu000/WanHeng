<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盈通后台管理系统</title>

<link href="css/base.css" type="text/css" rel="stylesheet">
	<link href="css/tab.css" type="text/css" rel="stylesheet">
		<link href="css/item.css" type="text/css" rel="stylesheet">
</head>

<body>
	<div id="all">
		<div id="hd" class="rel">
			<h1 id="hd_logo" title="">
				<img src="images/toplogo.png"  alt="" />
			</h1>
			<ul id="hd_ul">
				<li class="left"><p id="hd_u_name" class="wht_txt font14 hide"></p>
				</li>
				<%-- <li class="left" style="margin-right:15px"><a id="Num1" class="btns" style="width: 20px;line-height: 24px;margin-top: 10px;height: 24px"
					href="${pageContext.request.contextPath}/orders/showOrders.do?send=0"
					target="mainFrame">&nbsp;${sessionScope.Num}&nbsp;</a></li> --%>
				<li class="left" style="border-left:1px solid #000;"><a
					href="javascript:void(0);" id="hd_enter_im"
					class="wht_txt font14 block">欢迎您：${sessionScope.admin.username}</a>
				</li>
				<li class="left"><a
					href="${pageContext.request.contextPath}/admin/login_out.jsp"
					target="_parent" onclick="return confirm('您确定要退出后台吗？');"
					id="hd_logout_a"><img src="images/out.png" /> </a>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>
