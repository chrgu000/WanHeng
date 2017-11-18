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
	<form action="${pageContext.request.contextPath}/car/car_list.do">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="">
				<div class="pro_sou">
					<a
						href="${pageContext.request.contextPath}/car/toAddCar.do?titleAddr_id=${sessionScope.titleAddr_id}"
						style="float:left; margin-left:0px;margin-right: 10px;"
						class="btns distribution" id="add_item" title=" 添加一辆车辆"><em
						id="add_item_em">+</em> 添加一辆车辆</a><a class="btns distribution"
						style="float:left; margin-right:30px;"
						href="javascript:history.back(-1)">返回</a> <input type="submit"
						value="搜索" class="btns distribution"
						style="margin-left:20px;margin-right:120px;float:right ">
					车名:<input type="text" name="name"
						value="${sessionScope.carPage.name }" class="input "
						style="margin-left:20px;margin-right:20px;">
					<p class="i_do_tle r_txt abs font14"></p>
					<input type="radio" name="state" value="1"
						<c:if test="${sessionScope.carPage.state==1 }">checked</c:if>>在首页
					<input type="radio" name="state" value="0"
						<c:if test="${sessionScope.carPage.state==0 }">checked</c:if>>不在首页
					<select name="brand_id">
						<option value="">--选择品牌--</option>
						<c:forEach items="${brands }" var="b">
							<option value=${b. id}
								<c:if test="${sessionScope.carPage.brand_id==b.id }">selected</c:if>>${b.name
								}</option>
						</c:forEach>
					</select> <select name="price_id">
						<option value="">--选择价格--</option>
						<c:forEach items="${prices }" var="p">
							<option value="${p.id}"
								<c:if test="${sessionScope.carPage.price_id==p.id }">selected</c:if>>
								${p.price}</option>
						</c:forEach>
					</select>
				</div>
				<div id="list_wrap" class="rel " style="width:98%;margin-top: 17px">
					<table style="width:100%">
						<tr
							style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
							<td style="width:10%;" class="xiahuaxian">车名</td>
							<td style="width:8%;" class="xiahuaxian">品牌</td>
							<td style="width:8%;" class="xiahuaxian">优惠价格</td>
							<td style="width:10%;" class="xiahuaxian">原价</td>
							<td style="width:20%;" class="xiahuaxian">车辆展示</td>
							<td style="width:10%;" class="xiahuaxian">取车地点</td>
							<td style="width:10%;" class="xiahuaxian">还车地点</td>
							<td style="width:5%;" class="xiahuaxian">租期</td>
							<td style="width:5%;" class="xiahuaxian">是否首页</td>
							<td style="width:5%;" class="xiahuaxian">剩余车辆</td>
							<td style="width:14%;" class="xiahuaxian" rowspan=“3”>操作</td>
						</tr>

						<c:forEach items="${cars}" var="c">
							<tr
								style="text-align:center; line-height:40px; border-bottom:1px solid #CCC">
								<td style="width:10%;" class="xiahuaxian">${c.name}</td>
								<td style="width:8%;" class="xiahuaxian">${c.brand.name}</td>
								<td style="width:8%;" class="xiahuaxian">${c.favourable_price}</td>
								<td style="width:10%;" class="xiahuaxian">${c.original_price}</td>
								<td style="width:20%;" class="xiahuaxian"><img
									src="../${c.path }" style="width:110px; height:110px" /></td>
								<td style="width:10%;" class="xiahuaxian">${c.buyAddr }</td>
								<td style="width:10%;" class="xiahuaxian">${c.sendAddr }</td>
								<td style="width:5%;" class="xiahuaxian">${c.days }天</td>
								<td style="width:5%;" class="xiahuaxian"><c:choose>
										<c:when test="${c.state==1 }">是</c:when>
										<c:otherwise>否</c:otherwise>
									</c:choose></td>
								<td style="width:5%;" class="xiahuaxian"><c:if
										test="${c.spare_num=='0' }">已售完</c:if>
									<c:if test="${c.spare_num!='0' }">${c.spare_num }</c:if>
								</td>
								<td class="xiahuaxian" style="width:14%;"><a
									href="${pageContext.request.contextPath}/car/toUpdateCar.do?id=${c.id}&titleAddr_id=${sessionScope.titleAddr_id}">修改</a>
									&nbsp;&nbsp;|&nbsp;&nbsp;<a
									href="${pageContext.request.contextPath}/car/deleteCarById.do?id=${c.id}"
									onclick="return confirm('删除之后数据将不可恢复，确认删除吗？');">删除</a></td>
							</tr>
						</c:forEach>
						<tr style="text-align:center; line-height:60px; ">
							<td colspan="11">
								第${carPage.currentPage}页/共${carPage.totalPage}页&nbsp;&nbsp; <a
								class="inline_b btns_2"
								href="${pageContext.request.contextPath}/car/car_list.do?currentPage=1">首页</a>&nbsp;&nbsp;
								<a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/car/car_list.do?currentPage=${carPage.currentPage-1==0?1:carPage.currentPage-1}">上一页</a>&nbsp;&nbsp;
								<c:forEach begin="1" end="${carPage.totalPage}" var="num">
									<a class="inline_b btns_2" style="width:30px;"
										href="${pageContext.request.contextPath}/car/car_list.do?currentPage=${num}">${num
										}</a>
								</c:forEach> &nbsp;&nbsp; <a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/car/car_list.do?currentPage=${carPage.currentPage+1>carPage.totalPage?carPage.totalPage:carPage.currentPage+1}">下一页</a>&nbsp;&nbsp;
								<a class="inline_b btns_2"
								href="${pageContext.request.contextPath}/car/car_list.do?currentPage=${carPage.totalPage}">尾页</a>
								<a class="inline_b btns_2"
								style="height:32px;width:60px;float: right;"
								href="javascript:jump()">跳转</a> <select id="s1"
								class="inline_b btns_2"
								style="height:35px;width:60px;float: right;">
									<c:forEach begin="1" end="${carPage.totalPage}" var="num">
										<option value="${num}"
											<c:if test="${carPage.currentPage==num }">selected</c:if>
											style="font-szie:14px;color:#434343;">第${num}页</option>
									</c:forEach>
							</select> <script type="text/javascript">
								function jump() {
									var num = document.getElementById("s1").value;
									window.location.href = "${pageContext.request.contextPath}/car/car_list.do?currentPage="
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