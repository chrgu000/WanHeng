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
	<form
		action="${pageContext.request.contextPath}/motorcycle/motorcycle_list.do"
		method="post">
		<input type="hidden" name="currentPage" value="1">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="">
				<div class="pro_sou">
					<a
						href="${pageContext.request.contextPath}/motorcycle/toAddMotorcycle.do"
						style="float:left; margin-left:10px;margin-right: 10px;"
						class="btns distribution" id="add_item" title=" 添加车型"><em
						id="add_item_em">+</em> 添加车型</a><a class="btns distribution"
						style="float:left; margin-left:10px;"
						href="javascript:history.back(-1)">返回</a> <input
						class="btns distribution" type="submit" value="搜索"
						style="float:right; margin-left:10px;">
					<select name="brand_id" class="block input left tline"
						id="i_no_sku_stock">
						<option value="">--请选择品牌--</option>
						<c:forEach items="${brands }" var="b">
							<option value="${b.id }"
								<c:if test="${page.brand_id==b.id }">selected</c:if>>${b.name
								}</option>
						</c:forEach>
					</select>
				</div>
				<div id="list_wrap" class="rel " style="width:98%;margin-top: 17px">
					<table style="width:100%">
						<tr
							style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
							<td style="width:20%;" class="xiahuaxian">品牌</td>
							<td style="width:30%;" class="xiahuaxian">序列号</td>
							<td style="width:30%;" class="xiahuaxian">车型</td>
							<td style="width:25%;" class="xiahuaxian">操作</td>
						</tr>
						<c:forEach items="${motorcycles }" var="m">
							<tr style="text-align:center; line-height:60px; ">
								<td style="width:20%;" class="xiahuaxian">${m.brand.name }</td>
								<td style="width:20%;" class="xiahuaxian">${m.num}</td>
								<td style="width:30%;" class="xiahuaxian">${m.name }</td>
								<td style="width:25%;" class="xiahuaxian"><a
									href="${pageContext.request.contextPath}/motorcycle/toUpdateMotorcycle.do?id=${m.id}">修改信息</a>
									&nbsp;|&nbsp;<a
									href="${pageContext.request.contextPath}/motorcycle/deleteMotorcycleById.do?id=${m.id}"
									onclick="return confirm('删除之后数据将不可恢复，确认删除吗？');">删除</a></td>
							</tr>
						</c:forEach>
						<tr style="text-align:center; line-height:60px; ">
							<td colspan="4">
								第${page.currentPage}页/共${page.totalPage}页&nbsp;&nbsp; <a
								class="inline_b btns_2"
								href="${pageContext.request.contextPath}/motorcycle/motorcycle_list.do?currentPage=1">首页</a>&nbsp;&nbsp;
								<a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/motorcycle/motorcycle_list.do?currentPage=${page.currentPage-1==0?1:page.currentPage-1}">上一页</a>&nbsp;&nbsp;
								<c:forEach begin="1" end="${page.totalPage}" var="num">
									<a class="inline_b btns_2" style="width:30px;"
										href="${pageContext.request.contextPath}/motorcycle/motorcycle_list.do?currentPage=${num}">${num
										}</a>
								</c:forEach> &nbsp;&nbsp; <a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/motorcycle/motorcycle_list.do?currentPage=${page.currentPage+1>page.totalPage?page.totalPage:page.currentPage+1}">下一页</a>&nbsp;&nbsp;
								<a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/motorcycle/motorcycle_list.do?currentPage=${page.totalPage}">尾页</a>
								<a class="inline_b btns_2"
								style="height:32px;width:60px;float: right;"
								href="javascript:jump()">跳转</a> <select id="s1"
								class="inline_b btns_2"
								style="height:35px;width:60px;float: right;">
									<c:forEach begin="1" end="${page.totalPage}" var="num">
										<option value="${num}"<c:if test="${page.currentPage==num }">selected</c:if>  style="font-szie:14px;color:#434343;">第${num}页</option>
									</c:forEach>
							</select> <script type="text/javascript">
								function jump() {
									var num = document.getElementById("s1").value;
									window.location.href = "${pageContext.request.contextPath}/motorcycle/motorcycle_list.do?currentPage="
											+ num;
								}
							</script></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			function Search() {

				$("#Search").submit();
			}
		</script>
	</form>
</body>
</html>