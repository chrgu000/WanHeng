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
function checkCity(){
	var bool=true;
	var value=$("#city_id").val();
	if(!value){
		bool=false;
		alert("选择城市!");
	}
	return bool;
}
function checkName(){
	var bool=true;
	var value=$("#name").val().trim();
	if(!value){
		$("#nameError").text("城市名称不能为空!");
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
function checkCh(){
	var ch=$("#ch").val();
	if(!ch){
		alert("请选择一级序列号!");
		return false;
	}
	return  true;
}
function check(){
	if(checkCity()&&checkName()&&checkCh()&&checkNum()){
		return true;
	}
	return false;
}
</script><%@ include file="login/login_chk.jsp"%>
<%
	String cityID = request.getParameter("cityID");
%>
<body>
	<form action="../area/addArea.do" name="regForm"
		onSubmit="return check()" method="post">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="i_do_wrap">
			<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">城市</p>
					<select name="city_id" id="city_id" class="block input left tline">
						<option value="">--选择城市--</option>
						<c:forEach items="${citys }" var="c">
							<option value=${c. id}>${c.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">市区名称</p>
					<input type="text" id="name" name="name" onblur="checkName()"
						onfocus="removeError('name')" class="block input left tline"
						placeholder="城市名称" />&nbsp;&nbsp;&nbsp;<label class="labelError"
						id="nameError"></label>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">一级序列号</p>
					<select name="ch" class="block input left tline" id="ch">
					<option value="">---请选择序列号---</option>
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
						<option value="D">D</option>
						<option value="E">E</option>
						<option value="F">F</option>
						<option value="G">G</option>
						<option value="H">H</option>
						<option value="I">I</option>
						<option value="J">J</option>
						<option value="K">K</option>
						<option value="L">L</option>
						<option value="M">M</option>
						<option value="N">N</option>
						<option value="O">O</option>
						<option value="P">P</option>
						<option value="Q">Q</option>
						<option value="R">R</option>
						<option value="S">S</option>
						<option value="T">A</option>
						<option value="U">T</option>
						<option value="V">V</option>
						<option value="W">W</option>
						<option value="X">X</option>
						<option value="Y">Y</option>
						<option value="Z">Z</option>
					</select>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">二级排列序号</p>
					<input type="text" id="num" name="num" onblur="checkNum()"
						onfocus="removeError('num')" class="block input left tline"
						placeholder="二级排列序号" value="0" />&nbsp;&nbsp;&nbsp;<label
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