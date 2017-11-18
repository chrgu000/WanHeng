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
	src="${pageContext.request.contextPath}/admin/js/jquery-1.9.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聚乡村后台管理系统</title>
</head><%@ include file="login/login_chk.jsp"%>
<body>
	<form action="${pageContext.request.contextPath}/evaluate/evaluate_list.do"
		method="post">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="">
				<div class="pro_sou">
				<select name="merchant_id" style="margin-right:100px;float:left;">
						<option value="">--选择商户--</option>
						<c:forEach items="${merchants }" var="m">
							<option value="${m.id}"
								<c:if test="${sessionScope.evaluatePage.merchant_id==m.id }">selected</c:if>>
								${m.title}</option>
						</c:forEach>
					</select>评价者昵称:<input
						type="text" name="nickname" value="${evaluatePage.nickname }" class="input "
						style="margin-left:20px;margin-right:20px ">
						<input type="submit" value="搜索" class="btn distribution"
						style="margin-left:20px;margin-right:20px ;"> 
					<div id="list_wrap" class="rel " style="width:98%;margin-top: 17px">
						<table style="width:100%">
							<tr
								style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
								<td style="width:10%;" class="xiahuaxian">商户标题:</td>
								<td style="width:10%;" class="xiahuaxian">评价者昵称:</td>
								<td style="width:20%;" class="xiahuaxian">评价者头像</td>
								<td style="width:30%;" class="xiahuaxian">评价内容</td>
								<td style="width:10%;" class="xiahuaxian">评价时间</td>
								<td style="width:20%;" class="xiahuaxian">操作</td>
							</tr>
							<c:forEach items="${evaluates}" var="e">
								<tr
									style="text-align:center; line-height:30px; border-bottom:1px solid #CCC; font-size:13px;">
									<td class="xiahuaxian" style="width:10%;">${e.merchant.title}</td>
									<td class="xiahuaxian" style="width:10%;">${e.nickname}</td>
								<td class="xiahuaxian" style="width:20%;"><img
									src="../${e.path }" style="width:110px; height:110px" />
									</td>
										<td style="width:30%;" class="xiahuaxian">${e.content }</td>
									<td style="width:10%;"  class="xiahuaxian"><fmt:formatDate value="${e.createDate }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td class="xiahuaxian" style="width:10%;"></a> &nbsp;&nbsp;<a
										href="${pageContext.request.contextPath}/evaluate/deleteEvaluate.do?id=${e.id}"
										onclick="return confirm('删除之后数据将不可恢复，确认删除吗？');">删除</a></td>
								</tr>
							</c:forEach>
							<tr style="text-align:center; line-height:60px; ">
								<td colspan="5">
									第${evaluatePage.currentPage}页/共${evaluatePage.totalPage}页&nbsp;&nbsp; <a
									class="inline_b btns_2"
									href="${pageContext.request.contextPath}/evaluate/evaluate_list.do?currentPage=1">首页</a>&nbsp;&nbsp;
									<a class="inline_b btns_2"
									href="${pageContext.request.contextPath}/evaluate/evaluate_list.do?currentPage=${evaluatePage.currentPage-1==0?1:evaluatePage.currentPage-1}">上一页</a>&nbsp;&nbsp;
									<c:forEach begin="1" end="${evaluatePage.totalPage}" var="num">
										<a class="inline_b btns_2" style="width:30px;"
											href="${pageContext.request.contextPath}/evaluate/evaluate_list.do?currentPage=${num}">${num
											}</a>
									</c:forEach> &nbsp;&nbsp; <a class="inline_b btns_2"
									href="${pageContext.request.contextPath}/evaluate/evaluate_list.do?currentPage=${evaluatePage.currentPage+1>evaluatePage.totalPage?evaluatePage.totalPage:evaluatePage.currentPage+1}">下一页</a>&nbsp;&nbsp;
									<a class="inline_b btns_2"
									href="${pageContext.request.contextPath}/evaluate/evaluate_list.do?currentPage=${evaluatePage.totalPage}">尾页</a>
									<a class="inline_b btns_2"
									style="height:32px;width:60px;float: right;"
									href="javascript:jump()">跳转</a> <select id="s1"
									class="inline_b btns_2"
									style="height:35px;width:60px;float: right;">
										<c:forEach begin="1" end="${evaluatePage.totalPage}" var="num">
											<option value="${num}"
												<c:if test="${evaluatePage.currentPage==num }">selected</c:if>
												style="font-szie:14px;color:#434343;">第${num}页</option>
										</c:forEach>
								</select> <script type="text/javascript">
									function jump() {
										var num = document.getElementById("s1").value;
										window.location.href = "${pageContext.request.contextPath}/evaluate/evaluate_list.do?currentPage="
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