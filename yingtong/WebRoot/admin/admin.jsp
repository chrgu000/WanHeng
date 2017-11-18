<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
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
<link href="${pageContext.request.contextPath}/admin/css/base.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/admin/css/tab.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/admin/css/item.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/admin/css/item_do.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/admin/js/jquery-1.9.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>盈通后台管理系统</title> 
</head><%@ include file="login/login_chk.jsp"%>
<script language="javascript" type="text/javascript">
	function check(selectedID,nowID){
		if(selectedID==nowID){
			alert("你无权删除自己");
			return false;
		}
		return window.confirm("删除之后数据将不可恢复，确认删除吗？");
	}
</script>
<body>
	<div
		style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
		<div id="">
			<a href="${pageContext.request.contextPath}/admin/toAddAdmin.do"
				class="btns distribution" id="add_item" title="添加管理员"
				style="display:block; margin-left:17px; width:100px; font-size:14px; height:34px; line-height:34px;"><em
				id="add_item_em">+</em> 添加管理员</a>
			<div id="list_wrap" class="rel " style="width:98%;margin-top: 17px">
				<table style="width:100%">
					<tr
						style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
						<td style="width:20%;" class="xiahuaxian">编号</td>
						<td style="width:25%;" class="xiahuaxian">管理员</td>
						<td style="width:30%;" class="xiahuaxian">管理员类型</td>
						<td style="width:25%;" class="xiahuaxian">管理</td>
					</tr>
					<c:forEach items="${admins}" var="a">
						<tr
							style="text-align:center; line-height:30px; border-bottom:1px solid #CCC; font-size:13px;">
							<td class="xiahuaxian">${a.id}</td>
							<td class="xiahuaxian">${a.username}</td>
							<td class="xiahuaxian">  ${a.power.name } 	</td>
							<td class="xiahuaxian"><a
								href="${pageContext.request.contextPath}/admin/deleteAdmin.do?id=${a.id}"
								onclick="return check('${a.id}','${sessionScope.admin.id}')">删除</a>
							</td>
						</tr>
					</c:forEach>
					<tr style="text-align:center; line-height:60px; ">
						<td colspan="4">
							第${page.currentPage}页/共${page.totalPage}页&nbsp;&nbsp;
  				<a class="inline_b btns_2" href="${pageContext.request.contextPath}/admin/findAllAdminByPage.do?currentPage=1">首页</a>&nbsp;&nbsp;
  				<a class="inline_b btns_2" href="${pageContext.request.contextPath}/admin/findAllAdminByPage.do?currentPage=${page.currentPage-1==0?1:page.currentPage-1}">上一页</a>&nbsp;&nbsp;
  				<c:forEach begin="1" end="${page.totalPage}" var="num">
  					<a class="inline_b btns_2" style="width:30px;"href="${pageContext.request.contextPath}/admin/findAllAdminByPage.do?currentPage=${num}">${num }</a>
  				</c:forEach>
  				&nbsp;&nbsp;
  				<a class="inline_b btns_2" href="${pageContext.request.contextPath}/admin/findAllAdminByPage.do?currentPage=${page.currentPage+1>page.totalPage?page.totalPage:page.currentPage+1}">下一页</a>&nbsp;&nbsp;
  				<a class="inline_b btns_2" href="${pageContext.request.contextPath}/admin/findAllAdminByPage.do?currentPage=${page.totalPage}">尾页</a>
  				<a class="inline_b btns_2" style="height:32px;width:60px;float: right;" href="javascript:jump()">跳转</a>
  				<select id="s1" class="inline_b btns_2" style="height:35px;width:60px;float: right;">
  					<c:forEach begin="1" end="${page.totalPage}" var="num">
  						<option value="${num}" 	<c:if test="${page.currentPage==num }">selected</c:if> style="font-szie:14px;color:#434343;">第${num}页</option>
  					</c:forEach>
  				</select>				
  				<script type="text/javascript">
  					function jump(){
						var num = document.getElementById("s1").value;
						window.location.href="${pageContext.request.contextPath}/admin/findAllAdminByPage.do?currentPage="+num;
  					}
  				</script>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>