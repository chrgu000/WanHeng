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
	<form
		action="${pageContext.request.contextPath}/product/productList.do">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="">
				<div class="pro_sou">
					<a
						href="${pageContext.request.contextPath}/product/toAddProduct.do"
						style="float:left; margin-left:0px;margin-right: 10px;"
						class="btns distribution" id="add_item" title=" 添加信息"><em
						id="add_item_em">+</em> 添加信息</a><a class="btns distribution"
						style="float:left; margin-right:30px;"
						href="javascript:history.back(-1)">返回</a> <input type="submit"
						value="搜索" class="btns distribution"
						style="margin-left:20px;margin-right:20px;float:left ">
					标题:<input type="text" name="title"
						value="${sessionScope.productPage.title }" class="input "
						style="margin-left:20px;margin-right:20px;">
					<p class="i_do_tle r_txt abs font14"></p>
					<select name="merchant_id"
						style="margin-left:20px;margin-right:20px ;float:left;width:120px">
						<option value="">--选择商户--</option>
						<c:forEach items="${merchants}" var="m">
							<option value="${m.id}"
								<c:if test="${sessionScope.productPage.merchant_id==m.id }">selected</c:if>>
								${m.title}</option>
						</c:forEach>
					</select>
				</div>
				<div id="list_wrap" class="rel " style="width:98%;margin-top: 17px">
					<table style="width:100%">
						<tr
							style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
							<td style="width:5%;" class="xiahuaxian">商户名称</td>
							<td style="width:10%;" class="xiahuaxian">图片展示</td>
							<td style="width:5%;" class="xiahuaxian">标题</td>
							<td style="width:20%;" class="xiahuaxian">子标题</td>
							<td style="width:8%;" class="xiahuaxian">指定日期现价</td>
							<td style="width:9%;" class="xiahuaxian">周一~周四现价</td>
							<td style="width:9%;" class="xiahuaxian">周五~周日现价</td>
							<td style="width:5%;" class="xiahuaxian">是否免费</td>
							<td style="width:10%;" class="xiahuaxian">免费截止时间</td>
								<td style="width:10%;" class="xiahuaxian">免单数量</td>
							<td style="width:10%;" class="xiahuaxian" rowspan=“3”>操作</td>
						</tr>

						<c:forEach items="${products}" var="p">
							<tr
								style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
								<td style="width:5%;" class="xiahuaxian">${p.merchant.title}</td>
								<td style="width:10%;" class="xiahuaxian"><img
									src="../${p.path }" style="width:110px; height:110px" />
								</td>
								<td style="width:5%;" class="xiahuaxian">${p.title}</td>
								<td style="width:20%;" class="xiahuaxian">${p.sub_title}</td>
								<td style="width:8%;" class="xiahuaxian">${p.datePrices[0].favourable_price}</td>
								<td style="width:9%;" class="xiahuaxian">${p.datePrices[1].favourable_price}</td>
								<td style="width:9%;" class="xiahuaxian">${p.datePrices[2].favourable_price}</td>
								<td style="width:5%;" class="xiahuaxian"><c:if
										test="${p.isFree=='1' }">免费</c:if> <c:if
										test="${p.isFree=='0' }">不免费</c:if></td>
								<td style="width:10%;" class="xiahuaxian"><fmt:formatDate
										value="${p.endDate }" pattern="yyyy-MM-dd" />
								</td>
										<td style="width:10%;" class="xiahuaxian">${p.free_num }</td>
								<td class="xiahuaxian" style="width:10%;"><a
									href="${pageContext.request.contextPath}/product/toUpdateProduct.do?id=${p.id}">修改</a>
									&nbsp;&nbsp;|&nbsp;&nbsp;<a
									href="${pageContext.request.contextPath}/product/deleteProduct.do?id=${p.id}"
									onclick="return confirm('删除之后数据将不可恢复，确认删除吗？');">删除</a>
								</td>
							</tr>
						</c:forEach>
						<tr style="text-align:center; line-height:60px; ">
							<td colspan="11">
								第${productPage.currentPage}页/共${productPage.totalPage}页&nbsp;&nbsp;
								<a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/product/productList.do?currentPage=1">首页</a>&nbsp;&nbsp;
								<a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/product/productList.do?currentPage=${productPage.currentPage-1==0?1:productPage.currentPage-1}">上一页</a>&nbsp;&nbsp;
								<c:forEach begin="1" end="${productPage.totalPage}" var="num">
									<a class="inline_b btns_2" style="width:30px;"
										href="${pageContext.request.contextPath}/product/productList.do?currentPage=${num}">${num
										}</a>
								</c:forEach> &nbsp;&nbsp; <a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/product/productList.do?currentPage=${productPage.currentPage+1>productPage.totalPage?productPage.totalPage:productPage.currentPage+1}">下一页</a>&nbsp;&nbsp;
								<a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/product/productList.do?currentPage=${productPage.totalPage}">尾页</a>
								<a class="inline_b btns_2"
								style="height:32px;width:60px;float: right;"
								href="javascript:jump()">跳转</a> <select id="s1"
								class="inline_b btns_2"
								style="height:35px;width:60px;float: right;">
									<c:forEach begin="1" end="${productPage.totalPage}" var="num">
										<option value="${num}"
											<c:if test="${productPage.currentPage==num }">selected</c:if>
											style="font-szie:14px;color:#434343;">第${num}页</option>
									</c:forEach>
							</select> <script type="text/javascript">
								function jump() {
									var num = document.getElementById("s1").value;
									window.location.href = "${pageContext.request.contextPath}/product/productList.do?currentPage="
											+ num;
								}
							</script></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>