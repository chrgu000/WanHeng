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
<title>聚乡村后台管理系统</title>
</head><%@ include file="login/login_chk.jsp"%>
<body>
	<form action="${pageContext.request.contextPath}/city/cityList.do"
		method="post">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="">
				<div class="pro_sou">
					<a href="${pageContext.request.contextPath}/city/toAddCity.do"
						style="float:left; margin-left:0px;margin-right: 10px;"
						class="btns distribution" id="add_item" title=" 添加信息"><em
						id="add_item_em">+</em> 添加信息</a> <a class="btns distribution"
						style="float:left; margin-left:0px;margin-right:20px;"
						href="javascript:history.back(-1)">返回</a> <input type="submit"
						value="搜索" class="btn distribution"
						style="margin-left:20px;margin-right:20px ;;"> 城市名称:<input
						type="text" name="name" value="${sessionScope.cityPage.name }"
						class="input " style="margin-left:20px;margin-right:20px ">
					<div id="list_wrap" class="rel " style="width:98%;margin-top: 17px">
						<table style="width:100%">
							<tr
								style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
								<td style="width:16%;" class="xiahuaxian">城市名称</td>
								<td style="width:20%;" class="xiahuaxian">一级序列号</td>
								<td style="width:16%;" class="xiahuaxian">二级序列号</td>
								<td style="width:24%;" class="xiahuaxian">热门城市</td>
								<td style="width:24%;" class="xiahuaxian">操作</td>
							</tr>
							<c:forEach items="${citys}" var="c">
								<tr
									style="text-align:center; line-height:30px; border-bottom:1px solid #CCC; font-size:13px;">
									<td class="xiahuaxian">${c.name}</td>
									<td class="xiahuaxian">${c.ch}</td>
									<td class="xiahuaxian">${c.num }</td>
									<td class="xiahuaxian"><c:if test="${c.hot_city==1}">是</c:if><c:if test="${c.hot_city==0}">否</c:if>
									</td>
									<td class="xiahuaxian"><a
										href="${pageContext.request.contextPath}/city/toUpdateCity.do?id=${c.id}">修改信息</a>
										&nbsp;|&nbsp;<a
										href="${pageContext.request.contextPath}/city/deleteCity.do?id=${c.id}"
										onclick="return confirm('删除之后数据将不可恢复，确认删除吗？');">删除</a></td>
								</tr>
							</c:forEach>
							<tr style="text-align:center; line-height:60px; ">
								<td colspan="5">
									第${cityPage.currentPage}页/共${cityPage.totalPage}页&nbsp;&nbsp;
									<a class="inline_b btns_2"
									href="${pageContext.request.contextPath}/city/cityList.do?currentPage=1">首页</a>&nbsp;&nbsp;
									<a class="inline_b btns_2"
									href="${pageContext.request.contextPath}/city/cityList.do?currentPage=${cityPage.currentPage-1==0?1:cityPage.currentPage-1}">上一页</a>&nbsp;&nbsp;
									<c:forEach begin="1" end="${cityPage.totalPage}" var="num">
										<a class="inline_b btns_2" style="width:30px;"
											href="${pageContext.request.contextPath}/city/cityList.do?currentPage=${num}">${num
											}</a>
									</c:forEach> &nbsp;&nbsp; <a class="inline_b btns_2"
									href="${pageContext.request.contextPath}/city/cityList.do?currentPage=${cityPage.currentPage+1>cityPage.totalPage?cityPage.totalPage:cityPage.currentPage+1}">下一页</a>&nbsp;&nbsp;
									<a class="inline_b btns_2"
									href="${pageContext.request.contextPath}/city/cityList.do?currentPage=${cityPage.totalPage}">尾页</a>
									<a class="inline_b btns_2"
									style="height:32px;width:60px;float: right;"
									href="javascript:jump()">跳转</a> <select id="s1"
									class="inline_b btns_2"
									style="height:35px;width:60px;float: right;">
										<c:forEach begin="1" end="${cityPage.totalPage}" var="num">
											<option value="${num}" 	<c:if test="${cityPage.currentPage==num }">selected</c:if> style="font-szie:14px;color:#434343;">第${num}页</option>
										</c:forEach>
								</select> <script type="text/javascript">
									function jump() {
										var num = document.getElementById("s1").value;
										window.location.href = "${pageContext.request.contextPath}/city/cityList.do?currentPage="
												+ num;
									}
								</script></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>