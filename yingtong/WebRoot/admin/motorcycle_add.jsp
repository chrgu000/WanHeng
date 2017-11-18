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
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/admin/tiancheng/kindeditor-min.js"></script>
<script type="text/javascript">
KE.show({
			 id : "content1",
		     width : "800px",
		     height : "370px",		    
		     resizeMode : 1,
		     allowFileManager : true,
		     /*图片上传的SERVLET路径*/
		     imageUploadJson : "${pageContext.request.contextPath}/uploadImage.html", 
		     /*图片管理的SERVLET路径*/     
		     fileManagerJson : "${pageContext.request.contextPath}/uploadImgManager.html",
		     /*允许上传的附件类型*/
		     accessoryTypes : "doc|xls|pdf|txt|ppt|rar|zip",
		     /*附件上传的SERVLET路径*/
		     accessoryUploadJson : "${pageContext.request.contextPath}/uploadAccessory.html"
		});
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
		$("#numError").text("序号为数字!");
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
	String articleClassID = request.getParameter("articleClassID");
%>
<body>
	<form action="${pageContext.request.contextPath}/motorcycle/addMotorcycle.do"
		name="regForm" onSubmit="return check()" method="post">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="i_do_wrap">
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">车型</p>
					<input type="text" id="name" name="name" class="block input left tline" onblur="checkName()" onfocus="removeError('name')"
						placeholder="车型" />&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="nameError"></label>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">序列号</p>
					<input type="text" id="num" name="num" class="block input left tline"  onblur="checkNum()" onfocus="removeError('num')"
						placeholder="序列号" />&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="numError"></label>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">所属品牌</p>
					<select name="brand_id" 	class="block input left tline"   id="i_no_sku_stock">
					<c:forEach items="${brands }" var="b">
						<option value="${b.id }" <c:if test="${motorcycle.brand_id==b.id }">selected</c:if>>${b.name }</option>
					</c:forEach>
					</select>
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