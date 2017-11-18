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
	<script type="text/javascript" src="../admin/js/rili.js"></script>
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
function checkPrice(){
	var reg=/^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$/;
	var bool=true;
	var value=$("#price").val().trim();
	if(!reg.test(value)){
		$("#priceError").text("格式不正确，保留小数两位的价格!");
		$("#priceError").css("display","block");
		bool=false;
	}
	return bool;
}
function checkCh(){
	var ch=$("#ch").val();
	if(!ch){
		alert("请选择标题!");
		return false;
	}
	return  true;
}
function checkEndDate(){
	var bool=true;
	var value=$("#endDate").val().trim();
	if(!value){
		$("#endDateError").text("截止日期不能为空!");
		$("#endDateError").css("display","block");
		bool=false;
	}
	return bool;
}
function checkContent(){
	var bool=true;
	var value=$("#content").val().trim();
	if(!value){
		$("#contentError").text("内容不能为空!");
		$("#contentError").css("display","block");
		bool=false;
	}
	return bool;
}
function check(){
	if(checkPrice()&&checkCh()&&checkEndDate()&&checkContent()){
		return true;
	}
	return false;
}
</script><%@ include file="login/login_chk.jsp"%>
<body>
	<form action="../ticket/addTicket.do" name="regForm"
		onSubmit="return check()" method="post">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="i_do_wrap">
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">面值:</p>
					<input type="text" id="price" name="price" onblur="checkPrice()"
						onfocus="removeError('price')" class="block input left tline"
						placeholder="面值" />&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="priceError"></label>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">所属标题</p>
					<select name="title_id" class="block input left tline" id="ch">
					<option value="">---请选择所属标题---</option>
					<c:forEach items="${titles }" var="t">
						<option value="${t.id }">${t.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">截止日期:</p>
					<input type="text" id="endDate" name="endDate" 
						onfocus="removeError('endDate')" class="block input left tline"
						placeholder="截止日期" />&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="endDateError"></label>
				</div>
					<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">内容:</p>
					<input type="text" id="content" name="content" onblur="checkContent()"
						onfocus="removeError('content')" class="block input left tline"
						placeholder="内容" />&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="contentError"></label>
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
		<script type="text/javascript" src="../admin/laydate.dev.js">
</script>
		<script type="text/javascript" src="../admin/laydate.js">
</script>
	<script type="text/javascript">
document.getElementById('endDate').onclick = function() {
	laydate( {
		elem : '#endDate'
	});
}
</script>
</body>
</html>