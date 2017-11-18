<%@page import="org.apache.taglibs.standard.tag.el.core.ForEachTag"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page
	import="org.springframework.beans.factory.BeanFactory,org.springframework.context.support.FileSystemXmlApplicationContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>聚乡村后台管理系统</title>
		<link href="${pageContext.request.contextPath}/admin/css/base.css"
			type="text/css" rel="stylesheet">
			<link href="${pageContext.request.contextPath}/admin/css/tab.css"
				type="text/css" rel="stylesheet">
				<link href="${pageContext.request.contextPath}/admin/css/item.css"
					type="text/css" rel="stylesheet">
					<script type="text/javascript"
						src="${pageContext.request.contextPath}/admin/js/jquery-1.9.1.min.js">
</script>
	</head>
	<body>
		<div id="left-nav">
			<div id="nav_center">
				<div style="height: 50px;"></div>
				<div class="nav_list">
					<dl>
						<dt class="pro">
							<a href="javascript:void(0)">城市</a><i></i>
						</dt>
						<dd style="height: 30px">
							<div style="float: left; width: 150px">
								<a href="../city/cityList.do" target="mainFrame">城市管理</a>
							</div>
						</dd>
					</dl>
					<dl>
						<dt class="pro">
							<a href="javascript:void(0)">市区</a><i></i>
						</dt>
						<dd style="height: 30px">
							<div style="float: left; width: 150px">
								<a href="../area/areaList.do" target="mainFrame">市区管理</a>
							</div>
						</dd>
					</dl>
					<dl>
						<dt class="pro">
							<a href="javascript:void(0)">标签</a><i></i>
						</dt>
						<dd style="height: 30px">
							<div style="float: left; width: 150px">
								<a href="../mark/markList.do" target="mainFrame">标签管理</a>
							</div>
						</dd>
					</dl>
					<dl>
						<dt class="pro">
							<a href="javascript:void(0)">景点</a><i></i>
						</dt>
						<dd style="height: 30px">
							<div style="float: left; width: 75px">
									<a
										href="${pageContext.request.contextPath}/sightSpot/sightSpotList.do"
										target="mainFrame" style="float: left; width: 75px">景点管理</a>
							</div>
						</dd>
					</dl>
					<dl>
						<dt class="act">
							<a href="javascript:void(0)">图片</a><i></i>
						</dt>
						<dd style="height: 100px">
							<a
								href="${pageContext.request.contextPath}/picture/pictureList.do?type=2"
								target="mainFrame" style="float: left; width: 75px">首页图片</a>
							<a
								href="${pageContext.request.contextPath}/picture/pictureList.do?type=3"
								target="mainFrame" style="float: left; width: 75px">发现图片</a>
							<a
								href="${pageContext.request.contextPath}/picture/pictureList.do?type=1"
								target="mainFrame" style="float: left; width: 75px">图片管理</a>
						</dd>
					</dl>
					<dl>
						<dt class="act">
							<a href="javascript:void(0)">商户</a><i></i>
						</dt>
						<dd style="height: 30px">
							<div style="float: left; width: 75px">
									<a
										href="${pageContext.request.contextPath}/merchant/merchantList.do"
										target="mainFrame" style="float: left; width: 75px">商户管理</a>
							</div>
						</dd>
					</dl>
					<dl>
						<dt class="act">
							<a href="javascript:void(0)">产品</a><i></i>
						</dt>
						<dd style="height: 30px">
							<div style="float: left; width: 75px">
									<a
										href="${pageContext.request.contextPath}/product/productList.do"
										target="mainFrame" style="float: left; width: 75px">产品管理</a>
							</div>
						</dd>
					</dl>
					<dl>
						<dt class="act">
							<a href="javascript:void(0)">客户</a><i></i>
						</dt>
						<dd style="height: 30px">
							<div style="float: left; width: 75px">
								<a href="${pageContext.request.contextPath}/user/user_list.do"
									target="mainFrame">客户浏览</a>
							</div>
						</dd>
					</dl>
					<dl>
						<dt class="order">
							<a href="javascript:void(0)">搜索</a><i></i>
						</dt>
						<dd style="height: 30px">
							<div style="float: left; width: 150px">
								<a
									href="${pageContext.request.contextPath}/search/searchList.do"
									target="mainFrame" style="float: left; width: 60px">搜索关键词</a>
							</div>
						</dd>
					</dl>
					<dl>
						<dt class="order">
							<a href="javascript:void(0)">地址</a><i></i>
						</dt>
						<dd style="height: 30px">
							<div style="float: left; width: 150px">
								<a
									href="${pageContext.request.contextPath}/address/addressList.do"
									target="mainFrame" style="float: left; width: 60px">地址浏览</a>
							</div>
						</dd>
					</dl>
					<dl>
						<dt class="order">
							<a href="javascript:void(0)">支持</a><i></i>
						</dt>
						<dd style="height: 30px">
							<div style="float: left; width: 150px">
								<a
									href="${pageContext.request.contextPath}/support/supportList.do"
									target="mainFrame" style="float: left; width: 60px">支持浏览</a>
							</div>
						</dd>
					</dl>

					<dl>
						<dt class="order">
							<a href="javascript:void(0)">订单列表</a><i></i>
						</dt>
						<dd style="height: 100px">
							<div style="float: left; width: 75px">
								<c:forEach items="${sessionScope.titles }" var="t">
									<a
										href="${pageContext.request.contextPath}/order/order_list.do?title_id=${t.id}"
										target="mainFrame" style="float: left; width: 75px">${t.name}</a>
								</c:forEach>
							</div>
						</dd>
					</dl>
					<dl>
						<dt class="order">
							<a href="javascript:void(0)">乡村券</a><i></i>
						</dt>
						<dd style="height: 30px">
							<div style="float: left; width: 150px">
								<a
									href="${pageContext.request.contextPath}/ticket/ticketList.do"
									target="mainFrame" style="float: left; width: 60px">乡村券管理</a>
							</div>
						</dd>
					</dl>
					<dl>
						<dt class="order">
							<a href="javascript:void(0)">用户评价</a><i></i>
						</dt>
						<dd style="height: 30px">
							<div style="float: left; width: 150px">
								<a
									href="${pageContext.request.contextPath}/evaluate/evaluate_list.do"
									target="mainFrame" style="float: left; width: 60px">建议浏览</a>
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
								target="mainFrame">管理列表</a>
							<a
								href="${pageContext.request.contextPath}/admin/toUpdateAdmin.do?username=${sessionScope.admin.username}"
								target="mainFrame">修改密码</a>
						</dd>
					</dl>


				</div>

			</div>
		</div>
		<div style="height: 100px;"></div>
	</body>

	<script type="text/javascript">
$(document).ready(
		function(e) {

			$('.nav_list dl dt').click(function() {
				$(this).siblings('dd').slideToggle();
			})

			$('.nav_list dl dd a').click(
					function() {
						$(this).addClass('curb').parent().parent().siblings(
								'dl').children("dd").children('a').removeClass(
								'curb');
						$(this).siblings('a').removeClass('curb');
						$(this).parent().parent('dl').addClass('cur')
								.siblings().removeClass('cur');
					});
		});
</script>

</html>
