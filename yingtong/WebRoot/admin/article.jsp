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
<title>文章管理</title>
</head><%@ include file="login/login_chk.jsp"%>
<body>
	<div
		style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
		<div id="">
			<div class="pro_sou">
<%--				<a href="${pageContext.request.contextPath}/article/toAddArticle.do"--%>
<%--					style="float:left; margin-left:10px;margin-right: 10px;"--%>
<%--					class="btns distribution" id="add_item" title=" 添加文章"><em--%>
<%--					id="add_item_em">+</em> 添加文章</a>--%>
					<a class="btns distribution"
					style="float:left; margin-left:10px;"
					href="javascript:history.back(-1)">返回</a>
			</div>
			<div id="list_wrap" class="rel " style="width:98%;margin-top: 17px">
				<table style="width:100%">
					<tr
						style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
						<td style="width:20%;" class="xiahuaxian">大标题</td>
						<td style="width:30%;" class="xiahuaxian">标题</td>
						<td style="width:25%;" class="xiahuaxian">操作</td>
					</tr>
					<c:forEach items="${articles }" var="a">
						<tr
							style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
							<td style="width:20%;" class="xiahuaxian">${a.title.content}</td>
							<td style="width:30%;" class="xiahuaxian">${a.titles }</td>
							<td style="width:25%;" class="xiahuaxian"><a
								href="${pageContext.request.contextPath}/article/toUpdateArticle.do?id=${a.id}">修改</a>
<%--								&nbsp;&nbsp;|&nbsp;&nbsp;--%>
<%--								<a--%>
<%--								href="${pageContext.request.contextPath}/article/deleteArticleById.do?id=${a.id}"--%>
<%--								onclick="return confirm('删除之后数据将不可恢复，确认删除吗？');">删除</a>--%>
							</td>
						</tr>
					</c:forEach>
					<tr style="text-align:center; line-height:60px; ">
						<td colspan="14"></td>
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
</body>
</html>