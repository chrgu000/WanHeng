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
<title>盈通后台管理系统</title>
</head><%@ include file="login/login_chk.jsp"%>
<body>
	<form
		action="${pageContext.request.contextPath}/enterprise/enterprise_list.do">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="">
				<div class="pro_sou">
					<input type="submit" value="搜索" class="btns distribution"
						style="margin-left:20px;margin-right:120px;float:right ">
					企业账户名:<input type="text" name="account"
						value="${sessionScope.enterprisePage.account }" class="input "
						style="margin-left:20px;margin-right:20px;"> 公司名称:<input
						type="text" name="name"
						value="${sessionScope.enterprisePage.name }" class="input "
						style="margin-left:20px;margin-right:20px;"> 联系人:<input
						type="text" name="relation_person"
						value="${sessionScope.enterprisePage.relation_person}"
						class="input " style="margin-left:20px;margin-right:20px;">

				</div>
				<div id="list_wrap" class="rel " style="width:98%;margin-top: 17px">
					<table style="width:100%">
						<tr
							style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
							<td style="width:14%;" class="xiahuaxian">企业账户名</td>
							<td style="width:10%;" class="xiahuaxian">公司名称</td>
							<td style="width:10%;" class="xiahuaxian">公司地址</td>
							<td style="width:10%;" class="xiahuaxian">联系人</td>
							<td style="width:10%;" class="xiahuaxian">联系电话</td>
							<td style="width:10%;" class="xiahuaxian">邮箱</td>
								<td style="width:20%;" class="xiahuaxian">申请时间</td>
							<td style="width:16%;" class="xiahuaxian">操作</td>
						</tr>

						<c:forEach items="${enterprises}" var="e">
							<tr
								style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
								<td style="width:14%;" class="xiahuaxian" rowspan=”3“>${e.account}</td>
								<td style="width:10%;" class="xiahuaxian">${e.name }</td>
								<td style="width:10%;" class="xiahuaxian">${e.address }</td>
								<td style="width:10%;" class="xiahuaxian">${e.relation_person
									}</td>
								<td style="width:10%;" class="xiahuaxian">${e.tel }</td>
								<td style="width:10%;" class="xiahuaxian">${e.email }</td>
									<td style="width:20%;" class="xiahuaxian"><fmt:formatDate value="${e.apply_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td style="width:16%; class="xiahuaxian"><a
									href="${pageContext.request.contextPath}/enterprise/deleteEnterpriseById.do?id=${e.id}"
									onclick="return confirm('删除之后数据将不可恢复，确认删除吗？');">删除</a></td>
							</tr>
						</c:forEach>
						<tr style="text-align:center; line-height:60px; ">
							<td colspan="11">
								第${enterprisePage.currentPage}页/共${enterprisePage.totalPage}页&nbsp;&nbsp;
								<a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/enterprise/enterprise_list.do?currentPage=1">首页</a>&nbsp;&nbsp;
								<a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/enterprise/enterprise_list.do?currentPage=${enterprisePage.currentPage-1==0?1:enterprisePage.currentPage-1}">上一页</a>&nbsp;&nbsp;
								<c:forEach begin="1" end="${enterprisePage.totalPage}" var="num">
									<a class="inline_b btns_2" style="width:30px;"
										href="${pageContext.request.contextPath}/enterprise/enterprise_list.do?currentPage=${num}">${num
										}</a>
								</c:forEach> &nbsp;&nbsp; <a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/enterprise/enterprise_list.do?currentPage=${enterprisePage.currentPage+1>enterprisePage.totalPage?enterprisePage.totalPage:enterprisePage.currentPage+1}">下一页</a>&nbsp;&nbsp;
								<a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/enterprise/enterprise_list.do?currentPage=${enterprisePage.totalPage}">尾页</a>
								<a class="inline_b btns_2"
								style="height:32px;width:60px;float: right;"
								href="javascript:jump()">跳转</a> <select id="s1"
								class="inline_b btns_2"
								style="height:35px;width:60px;float: right;">
									<c:forEach begin="1" end="${enterprisePage.totalPage}"
										var="num">
										<option value="${num}" 	<c:if test="${enterprisePage.currentPage==num }">selected</c:if> style="font-szie:14px;color:#434343;">第${num}页</option>
									</c:forEach>
							</select> <script type="text/javascript">
								function jump() {
									var num = document.getElementById("s1").value;
									window.location.href = "${pageContext.request.contextPath}/enterprise/enterprise_list.do?currentPage="
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