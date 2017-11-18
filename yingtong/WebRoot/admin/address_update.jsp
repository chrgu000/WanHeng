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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/admin/css/uploadify.css"
	type="text/css"></link>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/admin/js/jquery.uploadify.v2.0.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/admin/js/swfobject.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/admin/tiancheng/kindeditor-min.js"></script>
 <script language="javascript">
 function removeError(id){
	 $("#"+id+"Error").css("display","none");
 }
 function checkAddName(){
	 var bool=true;
	 var value=$("#addname").val();
	 if(!value){
		 $("#addNameError").text("站点名称不能为空!");
		 $("#addNameError").css("display","block");
		 bool=false;
	 }
	 return bool;
 }
 function checkAddress(){
	 var bool=true;
	 var value=$("#address").val();
	 if(!value){
		 $("#addressError").text("地址不能为空!");
		 $("#addressError").css("display","block");
		 bool=false;
	 }
	 return bool;
 }
 function checkTel(){
	 var bool=true;
	 var value=$("#tel").val();
	 var reg=/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
	 if(!reg.test(value)){
		 $("#telError").text("手机号格式不正确!");
		 $("#telError").css("display","block");
		 bool=false;
	 }
	 return bool;
 }
 function checkBusinessHour(){
	 var bool=true;
	 var value=$("#businessHour").val();
	 if(!value){
		 $("#businessHourError").text("营业时间不能为空!");
		 $("#businessHourError").css("display","block");
		 bool=false;
	 }
	 return bool;
 }
 function checkNum(){
	 var bool=true;
	 var value=$("#num").val();
	 var reg=/^[0-9]+$/;
	 if(!reg.test(value)){
		 $("#numError").text("序列号应为数字!");
		 $("#numError").css("display","block");
		 bool=false;
	 }
	 return bool;
 }
 function check(){
	 if(checkAddName()&&checkAddress()&&checkTel()&&checkBusinessHour()&&checkNum()){
		 return true;
	 }
	 return false;
 }
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
<%
	String cityID = request.getParameter("cityID");
%>
  <%@ include file="login/login_chk.jsp"%>
<body>
	<form
		action="${pageContext.request.contextPath}/address/updateAddress.do"
		name="regForm" onSubmit="return check()" method="post">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="i_do_wrap">
				<div class="i_do_div rel" id="i_no_sku_stock_wrap"
				>
						<input type="hidden" name="titleAddr_id" value=${sessionScope.titleAddr_id }>
							<input type="hidden" name="id" value=${address.id }>
					<p class="i_do_tle r_txt abs font14">站点名称</p>
					<input type="text" id="addname" name="addName" onblur="checkAddName()" onfocus="removeError('addName')"
						class="block input left tline" placeholder="站点名称"
						value="${address.addName}" />&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="addNameError"></label>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">地址</p>
					<input type="text" id="address" name="address" onblur="checkAddress()" onfocus="removeError('address')"
						class="block input left tline" placeholder="地址"
						value="${address.address}" />&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="addressError"></label>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">手机</p>
					<input type="text" id="tel" name="tel"  onblur="checkTel()" onfocus="removeError('tel')"
						class="block input left tline" placeholder="手机"
						value="${address.tel}" />&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="telError"></label>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">营业时间</p>
					<input type="text" id="businessHour" name="businessHour" onblur="checkBusinessHour()" onfocus="removeError('businessHour')"
						class="block input left tline" placeholder="营业时间"
						value="${address.businessHour}" />&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="businessHourError"></label>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">序列号</p>
					<input type="text" id="num" name="num" onblur="checkNum()" onfocus="removeError('num')"
						class="block input left tline" placeholder="序列号"
						value="${address.num}" />&nbsp;&nbsp;&nbsp;<label class="labelError"
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