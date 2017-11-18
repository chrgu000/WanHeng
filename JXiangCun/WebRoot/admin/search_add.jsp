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
function checkTitle(){
	var bool=true;
	var value=$("#title").val().trim();
	if(!value){
		$("#titleError").text("搜索标题不能为空!");
		$("#titleError").css("display","block");
		bool=false;
	}
	return bool;
}
function checkChr(){
	var chr=$("#chr").val();
	if(!chr){
		$("#chrError").text("搜索内容不能为空!");
		$("#chrError").css("display","block");
		return false;
	}
	return  true;
}
function checkUrl(){
	var url=$("#url").val();
	if(!url){
		$("#urlError").text("搜索链接不能为空!");
		$("#urlError").css("display","block");
		return false;
	}
	return  true;
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
	if(checkTitle()&&checkChr()&&checkUrl()&&checkNum()){
		return true;
	}
	return false;
}
</script><%@ include file="login/login_chk.jsp"%>
<body>
	<form action="../search/addSearch.do" name="regForm"
		onSubmit="return check()" method="post">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="i_do_wrap">
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">搜索标题:</p>
					<input type="text" id="title" name="title" onblur="checkTitle()"
						onfocus="removeError('title')" class="block input left tline"
						placeholder="搜索标题" />&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="titleError"></label>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">内容</p>
					<input type="text" id="chr" name="chr" onblur="checkChr()"
						onfocus="removeError('chr')" class="block input left tline"
						placeholder="内容" />&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="chrError"></label>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">链接</p>
					<input type="text" id="url" name="url" onblur="checkUrl()"
						onfocus="removeError('url')" class="block input left tline"
						placeholder="链接" />&nbsp;&nbsp;&nbsp;<label
						class="labelError" id="urlError"></label>
				</div>
				<div  class="i_do_div rel" id="i_no_sku_stock_wrap">
				 	<p class="i_do_tle r_txt abs font14">序号</p>
					<input type="text" id="num" name="num" onblur="checkNum()"
						onfocus="removeError('num)" class="block input left tline"
						placeholder="序号" />&nbsp;&nbsp;&nbsp;<label
						class="labelError" id="numError"></label>
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