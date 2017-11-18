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
function checkCity(){
	var bool=true;
	var value=$("#city_id").val();
	if(!value){
		bool=false;
		alert("选择城市!");
	}
	return bool;
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
function checkCh() {
	var ch = $("#ch").val();
	if (!ch) {
		alert("请选择一级序列号!");
		return false;
	}
	return true;
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
function check() {
	if (checkCity()&&checkName() && checkCh() && checkNum()) {
		return true;
	}
	return false;
}
</script><%@ include file="login/login_chk.jsp"%>

	<body>
		<form action="../area/updateArea.do" name="regForm"
			onSubmit="return check()" method="post">
			<input type="hidden" name="id" value="${area.id }">
			<div
				style="padding-top: 50px; float: left; width: 95%; padding-left: 30px;">
				<div id="i_do_wrap">
					<div class="i_do_div rel" id="i_no_sku_stock_wrap">
						<p class="i_do_tle r_txt abs font14">
							城市
						</p>
						<select name="city_id" id="city_id" class="block input left tline">
							<option value="">
								--选择城市--
							</option>
							<c:forEach items="${citys }" var="c">
								<option value=${c. id}
									<c:if test="${area.city_id==c.id}">selected</c:if>>
									${c.name}
								</option>
							</c:forEach>
						</select>
					</div>
					<div class="i_do_div rel" id="i_no_sku_stock_wrap">
						<p class="i_do_tle r_txt abs font14">
							市区名称
						</p>
						<input type="text" name="name" id="name" onblur="checkName()"
							onfocus="removeError('name')" class="block input left tline"
							placeholder="市区名称" value="${area.name}" />
						&nbsp;&nbsp;&nbsp;
						<label class="labelError" id="nameError"></label>
					</div>
					<div class="i_do_div rel" id="i_no_sku_stock_wrap">
						<p class="i_do_tle r_txt abs font14">
							一级序列号
						</p>
						<select name="ch" class="block input left tline" id="ch">
							<option value="">
								---请选择序列号---
							</option>
							<option value="A" <c:if test="${area.ch=='A' }">selected</c:if>>
								A
							</option>
							<option value="B" <c:if test="${area.ch=='B' }">selected</c:if>>
								B
							</option>
							<option value="C" <c:if test="${area.ch=='C' }">selected</c:if>>
								C
							</option>
							<option value="D" <c:if test="${area.ch=='D' }">selected</c:if>>
								D
							</option>
							<option value="E" <c:if test="${area.ch=='E' }">selected</c:if>>
								E
							</option>
							<option value="F" <c:if test="${area.ch=='F' }">selected</c:if>>
								F
							</option>
							<option value="G" <c:if test="${area.ch=='G' }">selected</c:if>>
								G
							</option>
							<option value="H" <c:if test="${area.ch=='H' }">selected</c:if>>
								H
							</option>
							<option value="I" <c:if test="${area.ch=='I' }">selected</c:if>>
								I
							</option>
							<option value="J" <c:if test="${area.ch=='J' }">selected</c:if>>
								J
							</option>
							<option value="K" <c:if test="${area.ch=='K' }">selected</c:if>>
								K
							</option>
							<option value="L" <c:if test="${area.ch=='L' }">selected</c:if>>
								L
							</option>
							<option value="M" <c:if test="${area.ch=='M' }">selected</c:if>>
								M
							</option>
							<option value="N" <c:if test="${area.ch=='N' }">selected</c:if>>
								N
							</option>
							<option value="O" <c:if test="${area.ch=='O' }">selected</c:if>>
								O
							</option>
							<option value="P" <c:if test="${area.ch=='P' }">selected</c:if>>
								P
							</option>
							<option value="Q" <c:if test="${area.ch=='Q' }">selected</c:if>>
								Q
							</option>
							<option value="R" <c:if test="${area.ch=='R' }">selected</c:if>>
								R
							</option>
							<option value="S" <c:if test="${area.ch=='S' }">selected</c:if>>
								S
							</option>
							<option value="T" <c:if test="${area.ch=='T' }">selected</c:if>>
								A
							</option>
							<option value="U" <c:if test="${area.ch=='U' }">selected</c:if>>
								T
							</option>
							<option value="V" <c:if test="${area.ch=='V' }">selected</c:if>>
								V
							</option>
							<option value="W" <c:if test="${area.ch=='W' }">selected</c:if>>
								W
							</option>
							<option value="X" <c:if test="${area.ch=='X' }">selected</c:if>>
								X
							</option>
							<option value="Y" <c:if test="${area.ch=='Y' }">selected</c:if>>
								Y
							</option>
							<option value="Z" <c:if test="${area.ch=='Z' }">selected</c:if>>
								Z
							</option>
						</select>
					</div>
					<div class="i_do_div rel" id="i_no_sku_stock_wrap">
						<p class="i_do_tle r_txt abs font14">
							二级序号
						</p>
						<input type="text" name="num" id="num" onblur="checkNum()"
							onfocus="removeError('num')" class="block input left tline"
							placeholder="二级序号" value="${area.num}" />
						&nbsp;&nbsp;&nbsp;
						<label class="labelError" id="numError"></label>
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