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
	<form action="${pageContext.request.contextPath}/mark/markList.do"
		method="post">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="">
				<div class="pro_sou">
					<a href="${pageContext.request.contextPath}/mark/toAddMark.do"
						style="float:left; margin-left:0px;margin-right: 10px;"
						class="btns distribution" id="add_item" title=" 添加信息"><em
						id="add_item_em">+</em> 添加信息</a> <a class="btns distribution"
						style="float:left; margin-left:0px;margin-right:20px;"
						href="javascript:history.back(-1)">返回</a> <input type="submit"
						value="搜索" class="btn distribution"
						style="margin-left:20px;margin-right:20px ;float:left;"> 标签名称:<input
						type="text" name="name" value="${sessionScope.markPage.name }"
						class="input " style="margin-left:20px;margin-right:20px ">
						<select name="title_id" style="margin-left:20px;margin-right:20px ;float:left;">
						<option value="">--选择标题--</option>
						<c:forEach items="${titles}" var="t">
							<option value="${t.id}"
								<c:if test="${sessionScope.markPage.title_id==t.id }">selected</c:if>>
								${t.name}</option>
						</c:forEach>
					</select>
					<div id="list_wrap" class="rel " style="width:98%;margin-top: 17px">
						<table style="width:100%">
							<tr
								style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
								<td style="width:16%;" class="xiahuaxian">标签名称</td>
								<td style="width:20%;" class="xiahuaxian">序列号</td>
								<td style="width:24%;" class="xiahuaxian">是否显示</td>
								<td style="width:24%;" class="xiahuaxian">所属标题</td>
								<td style="width:24%;" class="xiahuaxian">操作</td>
							</tr>
							<c:forEach items="${marks}" var="m">
								<tr
									style="text-align:center; line-height:30px; border-bottom:1px solid #CCC; font-size:13px;">
									<td class="xiahuaxian">${m.name}</td>
									<td class="xiahuaxian">${m.num }</td>
									<td class="xiahuaxian"><c:if test="${m.isFront==1}">是</c:if>
										<c:if test="${m.isFront==0}">否</c:if></td>
										<td class="xiahuaxian">
										<c:forEach items="${m.titles}" var="t" varStatus="v">
										<c:if test="${! v.last}">${t.name}、</c:if>
										<c:if test="${v.last}">${t.name}</c:if>
										</c:forEach>
										</td>
									<td class="xiahuaxian"><a
										href="${pageContext.request.contextPath}/mark/toUpdateMark.do?id=${m.id}">修改信息</a>
										&nbsp;|&nbsp;<a
										href="${pageContext.request.contextPath}/mark/deleteMark.do?id=${m.id}"
										onclick="return confirm('删除之后数据将不可恢复，确认删除吗？');">删除</a>
									</td>
								</tr>
							</c:forEach>
							<tr style="text-align:center; line-height:60px; ">
								<td colspan="5">
									第${markPage.currentPage}页/共${markPage.totalPage}页&nbsp;&nbsp; <a
									class="inline_b btns_2"
									href="${pageContext.request.contextPath}/mark/markList.do?currentPage=1">首页</a>&nbsp;&nbsp;
									<a class="inline_b btns_2"
									href="${pageContext.request.contextPath}/mark/markList.do?currentPage=${markPage.currentPage-1==0?1:markPage.currentPage-1}">上一页</a>&nbsp;&nbsp;
									<c:forEach begin="1" end="${markPage.totalPage}" var="num">
										<a class="inline_b btns_2" style="width:30px;"
											href="${pageContext.request.contextPath}/mark/markList.do?currentPage=${num}">${num
											}</a>
									</c:forEach> &nbsp;&nbsp; <a class="inline_b btns_2"
									href="${pageContext.request.contextPath}/mark/markList.do?currentPage=${markPage.currentPage+1>markPage.totalPage?markPage.totalPage:markPage.currentPage+1}">下一页</a>&nbsp;&nbsp;
									<a class="inline_b btns_2"
									href="${pageContext.request.contextPath}/mark/markList.do?currentPage=${markPage.totalPage}">尾页</a>
									<a class="inline_b btns_2"
									style="height:32px;width:60px;float: right;"
									href="javascript:jump()">跳转</a> <select id="s1"
									class="inline_b btns_2"
									style="height:35px;width:60px;float: right;">
								
										<c:forEach begin="1" end="${markPage.totalPage}" var="num">
											<option value="${num}" 
													<c:if test="${markPage.currentPage==num}">selected</c:if>
												 style="font-szie:14px;color:#434343;">第${num}页</option>
										</c:forEach>
								</select> <script type="text/javascript">
									function jump() {
										var num = document.getElementById("s1").value;
										window.location.href = "${pageContext.request.contextPath}/mark/markList.do?currentPage="
												+ num;
									}
								</script>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>