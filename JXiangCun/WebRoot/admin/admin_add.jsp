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
<script type="text/javascript" language="javascript"
	src="${pageContext.request.contextPath}/admin/js/jquery-1.9.1.min.js"></script>
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
	function check() {
		if (document.regForm.username.value == "") {
			alert("管理员名不能为空");
			return false;
		}
		var names=document.getElementById("names").value+"";
		var nameArr=names.substring(1, names.length-1).split(",");
		for(var i=0;i<nameArr.length;i++){
		if(document.regForm.username.value ==nameArr[i]){
		alert("用户名已存在");
		return false;
		}
		}
		if(document.regForm.password.value==""){
			alert("密码不能为空");
			return false;
		}
		if(document.regForm.repassword.value==""){
			alert("确认密码不能为空");
			return false;
		}
		if(document.regForm.password.value!=document.regForm.repassword.value){
			alert("两次密码输入不一致");
			return false;
		}
	 
	}
</script><%@ include file="login/login_chk.jsp"%>
<body>
	<form action="${pageContext.request.contextPath}/admin/addAdmin.do"
		name="regForm" onSubmit="return check()" method="post">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="i_do_wrap">
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<input id="names" type="hidden" value="${names }">
					<p class="i_do_tle r_txt abs font14">用户名</p>
					<input type="text" id="i_no_sku_stock" name="username"
						class="block input left tline" placeholder="用户名" />
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">密码</p>
					<input type="password" id="i_no_sku_stock" name="password"
						class="block input left tline" placeholder="密码" />
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">确认密码</p>
					<input type="password" id="i_no_sku_stock" name="repassword"
						class="block input left tline" placeholder="确认密码" />
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">权限</p>
					<input type="radio" name="power_id" id="power" value="2" checked>超级管理员
					<input type="radio" name="power_id" id="power" value="1">管理员
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