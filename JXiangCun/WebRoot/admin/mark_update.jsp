<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<%@ page contentType="text/html; charset=utf-8"%>
	<head>
		<title>聚乡村后台管理系统</title>
		<link href="${pageContext.request.contextPath}/admin/css/base.css"
			type="text/css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/admin/css/tab.css"
			type="text/css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/admin/css/item.css"
			type="text/css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/admin/css/item_do.css"
			type="text/css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/admin/css/label.css"
			type="text/css" rel="stylesheet">
		<script type="text/javascript" language="javascript"
			src="${pageContext.request.contextPath}/admin/js/jquery-1.9.1.min.js">
</script>
		<link rel="stylesheet" href="css/uploadify.css" type="text/css"></link>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/admin/js/jquery.uploadify.v2.0.3.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/admin/js/swfobject.js">
</script>
		<style>
#edit {
	margin-top: 30px;
	margin-left: 200px;
}

#edit_1 {
	margin: 0 auto;
}
</style>
	</head>
	<script language="javascript">
function removeError(id) {
	$("#" + id + "Error").css("display", "none");
}
function checkName() {
	var bool = true;
	var value = $("#name").val().trim();
	if (!value) {
		$("#nameError").text("城市名称不能为空!");
		$("#nameError").css("display", "block");
		bool = false;
	}
	return bool;
}

function checkNum() {
	var bool = true;
	var reg = /^[0-9]+$/;
	var value = $("#num").val().trim();
	if (!reg.test(value)) {
		$("#numError").text("二级序号为数字!");
		$("#numError").css("display", "block");
		bool = false;
	}
	return bool;
}
function checkTitle() {
	var bool=true;
	var value = $('input:checkbox[name=titleIds]:checked');
	if (value.length == 0) {
		alert("请选择标题");
		bool=false;
	}
	return bool;
}
function check() {
	if (checkName() && checkNum() && checkTitle()) {
		return true;
	}
	return false;
}
</script><%@ include file="login/login_chk.jsp"%>

	<body>
		<form action="../mark/updateMark.do" name="regForm"
			onSubmit="return check()" method="post">
			<input type="hidden" name="id" value="${mark.id }">
			<div
				style="padding-top: 50px; float: left; width: 95%; padding-left: 30px;">
				<div id="i_do_wrap">
					<div class="i_do_div rel" id="i_no_sku_stock_wrap">
						<p class="i_do_tle r_txt abs font14">
							标签名称
						</p>
						<input type="text" name="name" id="name" onblur="checkName()"
							onfocus="removeError('name')" class="block input left tline"
							placeholder="标签名称" value="${mark.name}" />
						&nbsp;&nbsp;&nbsp;
						<label class="labelError" id="nameError"></label>
					</div>
					<div class="i_do_div rel" id="i_no_sku_stock_wrap">
						<p class="i_do_tle r_txt abs font14">
							序列号
						</p>
						<input type="text" name="num" id="num" onblur="checkNum()"
							onfocus="removeError('num')" class="block input left tline"
							placeholder="序列号" value="${mark.num}" />
						&nbsp;&nbsp;&nbsp;
						<label class="labelError" id="numError"></label>
					</div>
					<div class="i_do_div rel" id="i_no_sku_stock_wrap">
						<p class="i_do_tle r_txt abs font14">
							是否显示
						</p>
						<input type="radio" name="isFront" value="0"
							<c:if test="${mark.isFront=='0' }">checked</c:if>>
						否
						<input type="radio" name="isFront" value="1"
							<c:if test="${mark.isFront=='1' }">checked</c:if>>
						是
					</div>
					<div class="i_do_div rel" id="i_no_sku_stock_wrap">
						<p class="i_do_tle r_txt abs font14">
							所属标题
						</p>
						<c:forEach items="${titles }" var="t">
							<input type="checkbox" name="titleIds" value="${t.id }"
								<c:forEach items="${mark.titleIds}" var="m">
					<c:if test="${m==t.id}">checked</c:if>
					</c:forEach>>${t.name } 
					</c:forEach>
					</div>
					<div id="i_do_btns" style="height: 50px;">
						<div style="margin: 0 auto">
							<input type="submit" class="btns block margin_auto"
								style="float: left; margin-right: 20px;" value="提 交" />
							<a href="javascript:history.back(-1)"
								class="btns block margin_auto" style="float: left;">返 回</a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>