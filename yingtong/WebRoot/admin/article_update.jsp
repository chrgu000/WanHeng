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
	function check() {
		if (document.regForm.titles.value == "") {
 			alert("标题不能为空");
 			return false;
 		}
 
 		if (document.regForm.content.value == "") {
 			alert("文章内容不能为空");
 			return false;
 		}
	}
</script><%@ include file="login/login_chk.jsp"%>
<body>
	<form
		action="${pageContext.request.contextPath}/article/updateArticle.do"
		name="regForm" onSubmit="return check()" method="post">
		<input type="hidden" name="id" value="${article.id }">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="i_do_wrap">
				<div class="i_do_div rel" id="i_no_sku_stock_wrap"
					>
					<p class="i_do_tle r_txt abs font14">大标题</p>
					<select name="title_id" class="block input left tline" >
						<c:forEach items="${titles}" var="t">
							<option value="${t.id }" <c:if test="${article.title_id==t.id}">selected</c:if>>${t.content}</option>
						</c:forEach>
					</select>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">标题</p>
					<input type="text" id="i_no_sku_stock" name="titles"
						class="block input left tline" value="${article.titles}"
					 />
				</div>
			 
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">文章内容</p>
					<textArea id="content1" name="content"
						style="width:800px;height:600px;">${article.content} </textArea>
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