<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	src="${pageContext.request.contextPath}/admin/js/checkbox.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/admin/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/admin/tankuang/css/theme.css"
	media="all">
<script
	src="${pageContext.request.contextPath}/admin/tankuang/js/jquery.min.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聚乡村后台管理系统</title>
</head><%@ include file="login/login_chk.jsp"%>

<body>
	<form action="${pageContext.request.contextPath}/user/user_list.do"
		method="post">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="">
				<div class="pro_sou">
					<input type="submit" value="搜索" class="btns distribution"
						style="margin-left:20px;margin-right:20px;float:left ">
					用户名:<input type="text" name="username"
						value="${sessionScope.userPage.username }" class="input "
						style="margin-left:20px;margin-right:20px;"> 手机号:<input
						type="text" name="tel" value="${sessionScope.userPage.tel }"
						class="input " style="margin-left:20px;margin-right:20px;">
					昵称:<input type="text" name="nickname"
						value="${sessionScope.userPage.nickname}" class="input "
						style="margin-left:20px;margin-right:20px;">

				</div>
				<div id="list_wrap" class="rel " style="width:98%;margin-top: 17px">
					<table style="width:100%">
						<tr
							style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
							<td style="width:15%;" class="xiahuaxian">用户名</td>
							<td style="width:25%;" class="xiahuaxian">手机号</td>
							<td style="width:30%;" class="xiahuaxian">注册时间</td>
							<td style="width:15%;" class="xiahuaxian">头像</td>
							<td style="width:10%;" class="xiahuaxian">昵称</td>
							<td style="width:15%;" class="xiahuaxian">操作</td>


						</tr>
						<c:forEach items="${users}" var="u">
							<tr
								style="text-align:center; line-height:30px; border-bottom:1px solid #CCC; font-size:13px;">
								<td class="xiahuaxian">${u.username}</td>
								<td class="xiahuaxian">${u.tel}</td>
								<td class="xiahuaxian"><fmt:formatDate
										value="${u.regist_time }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
									<td style="width:10%;" class="xiahuaxian"><img
									src="../${u.path }" style="width:110px; height:110px" /></td>
									<td class="xiahuaxian">${u.nickname}</td>
								<td class="xiahuaxian"><a
									href="${pageContext.request.contextPath}/user/deleteUser.do?id=${u.id}"
									onclick="return confirm('删除之后会连带删除用户的订单信息，确认删除吗？');">删除</a></td>
							</tr>
						</c:forEach>
						<tr style="text-align:center; line-height:60px; ">
							<td colspan="14">
								第${userPage.currentPage}页/共${userPage.totalPage}页&nbsp;&nbsp; <a
								class="inline_b btns_2"
								href="${pageContext.request.contextPath}/user/user_list.do?currentPage=1">首页</a>&nbsp;&nbsp;
								<a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/user/user_list.do?currentPage=${userPage.currentPage-1==0?1:userPage.currentPage-1}">上一页</a>&nbsp;&nbsp;
								<c:forEach begin="1" end="${userPage.totalPage}" var="num">
									<a class="inline_b btns_2" style="width:30px;"
										href="${pageContext.request.contextPath}/user/user_list.do?currentPage=${num}">${num
										}</a>
								</c:forEach> &nbsp;&nbsp; <a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/user/user_list.do?currentPage=${userPage.currentPage+1>userPage.totalPage?userPage.totalPage:userPage.currentPage+1}">下一页</a>&nbsp;&nbsp;
								<a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/user/user_list.do?currentPage=${userPage.totalPage}">尾页</a>
								<a class="inline_b btns_2"
								style="height:32px;width:60px;float: right;"
								href="javascript:jump()">跳转</a> <select id="s1"
								class="inline_b btns_2"
								style="height:35px;width:60px;float: right;">
									<c:forEach begin="1" end="${userPage.totalPage}" var="num">
										<option value="${num}" 	<c:if test="${userPage.currentPage==num }">selected</c:if> style="font-szie:14px;color:#434343;">第${num}页</option>
									</c:forEach>
							</select> <script type="text/javascript">
								function jump() {
									var num = document.getElementById("s1").value;
									window.location.href = "${pageContext.request.contextPath}/user/user_list.do?currentPage="
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