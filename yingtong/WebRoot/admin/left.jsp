<%@page import="org.apache.taglibs.standard.tag.el.core.ForEachTag"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page
	import="com.yingtong.entity.TitleAddr,com.yingtong.dao.TitleAddrDao,java.util.List"%>
<%@page
	import="org.springframework.beans.factory.BeanFactory,org.springframework.context.support.FileSystemXmlApplicationContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盈通后台管理系统</title>
<link href="${pageContext.request.contextPath}/admin/css/base.css"
	type="text/css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/admin/css/tab.css"
		type="text/css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/admin/css/item.css"
			type="text/css" rel="stylesheet">
			<script type="text/javascript"
				src="${pageContext.request.contextPath}/admin/js/jquery-1.9.1.min.js"></script>
</head>
<body>
	<div id="left-nav">
		<div id="nav_center">
			<div style="height:50px;"></div>
			<div class="nav_list">
				<dl>
					<dt class="pro">
						<a href="javascript:void(0)">品牌</a><i></i>
					</dt>
					<dd style="height: 30px">
						<div style="float: left;width: 150px">
							<a href="../brand/brandList.do" target="mainFrame">品牌管理</a>
						</div>
					</dd>
				</dl>
				<dl>
					<dt class="pro">
						<a href="javascript:void(0)">车型</a><i></i>
					</dt>
					<dd style="height: 30px">
						<div style="float: left;width: 150px">
							<a
								href="${pageContext.request.contextPath}/motorcycle/motorcycle_list.do"
								target="mainFrame">车型管理</a>
						</div>
					</dd>
				</dl>
				
					<dl>
					<dt class="act">
						<a href="javascript:void(0)">地址信息</a><i></i>
					</dt>
					<dd style="height: 330px">
						<div style="float: left;width: 75px">
							<c:forEach items="${sessionScope.titleAddrs }" var="t">
								<a
									href="${pageContext.request.contextPath}/address/address_list.do?titleAddr_id=${t.id}"
									target="mainFrame" style="float: left;width: 75px">${t.titleAddr}</a>
							</c:forEach>
						</div>
					</dd>
				</dl>
				<dl>
					<dt class="act">
						<a href="javascript:void(0)">租车</a><i></i>
					</dt>
					<dd style="height: 330px">
						<div style="float: left;width: 75px">
							<c:forEach items="${sessionScope.titleAddrs }" var="t">
								<a
									href="${pageContext.request.contextPath}/car/car_list.do?titleAddr_id=${t.id}"
									target="mainFrame" style="float: left;width: 75px">${t.titleAddr}</a>
							</c:forEach>
						</div>
					</dd>
				</dl>
				<dl>
					<dt class="act">
						<a href="javascript:void(0)">客户管理</a><i></i>
					</dt>
					<dd style="height: 90px">
						<div style="float: left;width: 75px">
							<a href="${pageContext.request.contextPath}/user/user_list.do"
								target="mainFrame" >短租客户</a>
							<a href="${pageContext.request.contextPath}/longRentService/longRentService_list.do"
								target="mainFrame">长租客户</a>
	                        <a href="${pageContext.request.contextPath}/enterprise/enterprise_list.do"
								target="mainFrame">企业客户</a>
						</div>
					</dd>
				</dl>
				<dl>
					<dt class="order">
						<a href="javascript:void(0)">文章列表</a><i></i>
					</dt>
					<dd style="height: 30px">
						<div style="float: left;width: 150px">
							<a
								href="${pageContext.request.contextPath}/article/article_list.do"
								target="mainFrame" style="float: left;width: 60px">文章管理</a>
						</div>
					</dd>
				</dl>
				<dl>
					<dt class="order">
						<a href="javascript:void(0)">订单列表</a><i></i>
					</dt>
					<dd style="height:30px">
						<div style="float: left;width: 150px">
								<a href="${pageContext.request.contextPath}/order/order_list.do"
									target="mainFrame" style="float: left;width: 60px">订单管理</a>
						</div>
					</dd>
				</dl>
				<dl>
					<dt class="order">
						<a href="javascript:void(0)">用户建议</a><i></i>
					</dt>
					<dd style="height:30px">
						<div style="float: left;width: 150px">
								<a href="${pageContext.request.contextPath}/about/advice_list.do"
									target="mainFrame" style="float: left;width: 60px">建议浏览</a>
						</div>
					</dd>
				</dl>
				<dl>
					<dt class="user">
						<a href="javascript:void(0)">管理员</a><i></i>
					</dt>
					<dd>
						<a
							href="${pageContext.request.contextPath}/admin/findAllAdminByPage.do"
							target="mainFrame">管理列表</a> <a
							href="${pageContext.request.contextPath}/admin/toUpdateAdmin.do?username=${sessionScope.admin.username}"
							target="mainFrame">修改密码</a>
					</dd>
				</dl>


			</div>

		</div>
	</div>
	<div style="height:100px;"></div>
</body>

<script type="text/javascript">
	$(document).ready(
			function(e) {

				$('.nav_list dl dt').click(function() {
					$(this).siblings('dd').slideToggle();
				})

				$('.nav_list dl dd a').click(
						function() {
							$(this).addClass('curb').parent().parent()
									.siblings('dl').children("dd")
									.children('a').removeClass('curb');
							$(this).siblings('a').removeClass('curb');
							$(this).parent().parent('dl').addClass('cur')
									.siblings().removeClass('cur');
						});
			});
</script>

</html>
