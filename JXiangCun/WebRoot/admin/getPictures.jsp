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
	<form action="${pageContext.request.contextPath}/picture/pictureList.do">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="">
				<div id="list_wrap" class="rel " style="width:98%;margin-top: 17px">
					<table style="width:100%">
						<tr
							style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
							<td style="width:20%;" class="xiahuaxian">商户名称</td>
							<td style="width:20%;" class="xiahuaxian">图片展示</td>
							<td style="width:20%;" class="xiahuaxian">图片的应用路径</td>
							<td style="width:20%;" class="xiahuaxian">图片的应用类型</td>
						</tr>

						<c:forEach items="${pictures}" var="p">
							<tr
								style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
								<td style="width:20%;" class="xiahuaxian">${p.merchant.title}</td>
								<td style="width:20%;" class="xiahuaxian"><img
									src="../${p.path }" style="width:110px; height:110px" /></td>
								<td style="width:20%;" class="xiahuaxian">${p.url}</td>
								<td style="width:20%;" class="xiahuaxian">${p.type}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>