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
<title>地址管理</title>
</head><%@ include file="login/login_chk.jsp"%>
<body>
	<div
		style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
		<div id="">
			<div class="pro_sou">
				<a
					href="${pageContext.request.contextPath}/address/toAddAddress.do?titleAddr_id=${sessionScope.titleAddr_id}"
					style="float:left; margin-left:0px;margin-right: 10px;"
					class="btns distribution" id="add_item" title=" 添加一个站点"><em
					id="add_item_em">+</em> 添加一个站点</a><a class="btns distribution"
					style="float:left; margin-left:0px;"
					href="javascript:history.back(-1)">返回</a>
			</div>
			<div id="list_wrap" class="rel " style="width:98%;margin-top: 17px">
				<table style="width:100%">
					<tr
						style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
						<td style="width:20%;" class="xiahuaxian">站点名称</td>
						<td style="width:20%;" class="xiahuaxian">地址</td>
						<td style="width:15%;" class="xiahuaxian">手机</td>
						<td style="width:25%;" class="xiahuaxian">营业时间</td>
						<td style="width:5%;" class="xiahuaxian">序列号</td>
						<td style="width:15%;" class="xiahuaxian">操作</td>
					</tr>
					<c:forEach items="${addresses}" var="a">
						<tr
							style="text-align:center; line-height:30px; border-bottom:1px solid #CCC; font-size:13px;">
							<td class="xiahuaxian">${a.addName}</td>
							<td class="xiahuaxian">${a.address}</td>
							<td class="xiahuaxian">${a.tel}</td>
							<td class="xiahuaxian">${a.businessHour}</td>
							<td class="xiahuaxian">${a.num }</td>
							<td class="xiahuaxian"><a href="${pageContext.request.contextPath}/address/toUpdateAddress.do?id=${a.id}">修改信息</a>
								&nbsp;|&nbsp; 
							<a href="${pageContext.request.contextPath}/address/deleteAddressById.do?id=${a.id}" onclick="return confirm('删除之后数据将不可恢复，确认删除吗？');">删除</a></td>
						</tr>
					</c:forEach>
					<tr style="text-align:center; line-height:60px; ">
						<td colspan="11"> 
						第${page.currentPage}页/共${page.totalPage}页&nbsp;&nbsp;
  				<a class="inline_b btns_2" href="${pageContext.request.contextPath}/address/address_list.do?currentPage=1">首页</a>&nbsp;&nbsp;
  				<a class="inline_b btns_2" href="${pageContext.request.contextPath}/address/address_list.do?currentPage=${page.currentPage-1==0?1:page.currentPage-1}">上一页</a>&nbsp;&nbsp;
  				<c:forEach begin="1" end="${page.totalPage}" var="num">
  					<a class="inline_b btns_2" style="width:30px;"href="${pageContext.request.contextPath}/address/address_list.do?currentPage=${num}">${num }</a>
  				</c:forEach>
  				&nbsp;&nbsp;
  				<a class="inline_b btns_2" href="${pageContext.request.contextPath}/address/address_list.do?currentPage=${page.currentPage+1>page.totalPage?page.totalPage:page.currentPage+1}">下一页</a>&nbsp;&nbsp;
  				<a class="inline_b btns_2" href="${pageContext.request.contextPath}/address/address_list.do?currentPage=${page.totalPage}">尾页</a>
  				<a class="inline_b btns_2" style="height:32px;width:60px;float: right;" href="javascript:jump()">跳转</a>
  				<select id="s1" class="inline_b btns_2" style="height:35px;width:60px;float: right;">
  					<c:forEach begin="1" end="${page.totalPage}" var="num">
  						<option value="${num}" 	<c:if test="${page.currentPage==num }">selected</c:if> style="font-szie:14px;color:#434343;">第${num}页</option>
  					</c:forEach>
  				</select>				
  				<script type="text/javascript">
  					function jump(){
						var num = document.getElementById("s1").value;
						window.location.href="${pageContext.request.contextPath}/address/address_list.do?currentPage="+num;
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