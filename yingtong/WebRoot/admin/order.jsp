<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
#edit {
	margin-top: 30px;
	margin-left: 200px;
}

#edit_1 {
	margin: 0 auto;
}
</style>
<link href="${pageContext.request.contextPath}/admin/css/base.css"
	type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/admin/css/tab.css"
	type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/admin/css/item.css"
	type="text/css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/admin/css/item_do.css"
	type="text/css" rel="stylesheet" />
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/admin/js/jquery-1.9.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>盈通后台管理系统</title>
</head><%@ include file="login/login_chk.jsp"%>
<body>
	<form action="${pageContext.request.contextPath}/order/order_list.do">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="">
				<div class="pro_sou">
					<input type="submit" value="搜索" class="btns distribution"
						style="margin-left:20px;margin-right:120px;float:right ">
					订单号:<input type="text" name="order_num"
						value="${sessionScope.orderPage.order_num }" class="input "
						style="margin-left:20px;margin-right:20px;">
						租车人:<input type="text" name="username"
						value="${sessionScope.orderPage.username }" class="input "
						style="margin-left:20px;margin-right:20px;">
					<p class="i_do_tle r_txt abs font14"></p>
					<select name="status" style="width:125px">
						<option value="">--选择订单状态--</option>
						<option value="1" <c:if test="${orderPage.status=='1'}">selected</c:if>>等待付款</option>
						<option value="2"<c:if test="${orderPage.status=='2'}">selected</c:if>>已取消</option>
						<option value="3"<c:if test="${orderPage.status=='3'}">selected</c:if>>租赁中</option>
						<option value="4" <c:if test="${orderPage.status=='4'}">selected</c:if>>已完成</option>
					</select>
				</div>
				<div id="list_wrap" class="rel " style="width:98%;margin-top: 17px">
					<table style="width:100%">
						<tr
							style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
							<td style="width:10%;" class="xiahuaxian">订单号</td>
							<td style="width:10%;" class="xiahuaxian">租车人</td>
							<td style="width:10%;" class="xiahuaxian">车名</td>
							<td style="width:10%;" class="xiahuaxian">车辆展示</td>
							<td style="width:10%;" class="xiahuaxian">取车地点</td>
							<td style="width:10%;" class="xiahuaxian">还车地点</td>
							<td style="width:10%;" class="xiahuaxian">还车时间</td>
							<td style="width:10%;" class="xiahuaxian">租期</td>
							<td style="width:5%;" class="xiahuaxian">总计</td>
							<td style="width:5%;" class="xiahuaxian">订单状态</td>
							<td style="width:10%;" class="xiahuaxian" rowspan=“3”>操作</td>
						</tr>

						<c:forEach items="${orders6}" var="o">
							<tr
								style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
								<td style="width:10%;" class="xiahuaxian">${o.order_num}</td>
								<td style="width:10%;" class="xiahuaxian">${o.user.username}</td>
								<td style="width:10%;" class="xiahuaxian">${o.car.name}</td>
								<td style="width:10%;" class="xiahuaxian"><img
									src="../${o.car.path }" style="width:110px; height:110px" /></td>
								<td style="width:10%;" class="xiahuaxian">${o.car.buyAddr}</td>
								<td style="width:10%;" class="xiahuaxian">${o.car.sendAddr
									}</td>
								<td style="width:10%;" class="xiahuaxian">${o.sendTime }</td>
								<td style="width:10%;" class="xiahuaxian">${o.days }</td>
								<td style="width:5%;" class="xiahuaxian">${o.total_price }</td>
								<td style="width:5%;" class="xiahuaxian"><c:if
										test="${o.status=='1' }">等待付款</c:if> <c:if
										test="${o.status=='2' }">已取消</c:if> <c:if
										test="${o.status=='3' }">租赁中</c:if> <c:if
										test="${o.status=='4' }">已完成</c:if></td>
								<td class="xiahuaxian" style="width:10%;"><c:if
										test="${o.status!='4' }">
										<a href="../order/confirmFinished.do?id=${o.id }">确认完成</a></c:if> 
										<c:if test="${o.status=='4' }"><a href="../order/deleteOrderById.do?id=${o.id }" onclick="return confirm('删除之后数据将不可恢复，确认删除吗？');">删除</a></c:if></td>
							</tr>
						</c:forEach>
						<tr style="text-align:center; line-height:60px; ">
							<td colspan="11">
								第${orderPage.currentPage}页/共${orderPage.totalPage}页&nbsp;&nbsp; <a
								class="inline_b btns_2"
								href="${pageContext.request.contextPath}/order/order_list.do?currentPage=1">首页</a>&nbsp;&nbsp;
								<a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/order/order_list.do?currentPage=${orderPage.currentPage-1==0?1:orderPage.currentPage-1}">上一页</a>&nbsp;&nbsp;
								<c:forEach begin="1" end="${orderPage.totalPage}" var="num">
									<a class="inline_b btns_2" style="width:30px;"
										href="${pageContext.request.contextPath}/order/order_list.do?currentPage=${num}">${num
										}</a>
								</c:forEach> &nbsp;&nbsp; <a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/order/order_list.do?currentPage=${orderPage.currentPage+1>orderPage.totalPage?orderPage.totalPage:orderPage.currentPage+1}">下一页</a>&nbsp;&nbsp;
								<a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/order/order_list.do?currentPage=${orderPage.totalPage}">尾页</a>
								<a class="inline_b btns_2"
								style="height:32px;width:60px;float: right;"
								href="javascript:jump()">跳转</a> <select id="s1"
								class="inline_b btns_2"
								style="height:35px;width:60px;float: right;">
									<c:forEach begin="1" end="${orderPage.totalPage}" var="num">
										<option value="${num}"<c:if test="${orderPage.currentPage==num }">selected</c:if>  style="font-szie:14px;color:#434343;">第${num}页</option>
									</c:forEach>
							</select> <script type="text/javascript">
								function jump() {
									var num = document.getElementById("s1").value;
									window.location.href = "${pageContext.request.contextPath}/order/order_list.do?currentPage="
											+ num;
								}
							</script>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>