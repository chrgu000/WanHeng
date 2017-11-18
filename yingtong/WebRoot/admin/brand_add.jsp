<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ page contentType="text/html; charset=utf-8"%>
<head>
<title>盈通后台管理系统</title>
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
	src="${pageContext.request.contextPath}/admin/js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" href="css/uploadify.css" type="text/css"></link>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/admin/js/jquery.uploadify.v2.0.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/admin/js/swfobject.js"></script>
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
function removeError(id){
	$("#"+id+"Error").css("display","none");
}
function checkName(){
	var bool=true;
	var value=$("#name").val().trim();
	if(!value){
		$("#nameError").text("品牌名称不能为空!");
		$("#nameError").css("display","block");
		bool=false;
	}
	return bool;
}
function checkNum(){
	var bool=true;
	var reg=/^[0-9]+$/;
	var value=$("#num").val().trim();
	if(!reg.test(value)){
		$("#numError").text("品牌序号为数字!");
		$("#numError").css("display","block");
		bool=false;
	}
	return bool;
}
function check(){
	if(checkName()&&checkNum()){
		return true;
	}
	return false;
}
</script><%@ include file="login/login_chk.jsp"%>
<%
	String cityID = request.getParameter("cityID");
%>
<body>
	<form action="../brand/addBrand.do" name="regForm"
		onSubmit="return check()" method="post">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="i_do_wrap">
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">品牌名称</p>
					<input type="text" id="name" name="name" onblur="checkName()"
						onfocus="removeError('name')" class="block input left tline"
						placeholder="品牌名称" />&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="nameError"></label>
				</div>

				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">排列序号</p>
					<input type="text" id="num" name="num" onblur="checkNum()" onfocus="removeError('num')"
						class="block input left tline" placeholder="排列序号" value="0" />&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="numError"></label>
				</div>
				<div id="i_do_btns" style="height:50px;">
					<div style="margin:0 auto">
						<input type="submit" class="btns block margin_auto"
							style="float:left; margin-right:20px;" value="提 交" /> <a
							href="javascript:history.back(-1)" class="btns block margin_auto"
							style="float:left;">返 回</a>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>