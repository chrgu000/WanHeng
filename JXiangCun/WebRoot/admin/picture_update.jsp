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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/admin/css/uploadify.css"
	type="text/css"></link>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/admin/js/jquery.uploadify.v2.0.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/admin/js/swfobject.js"></script>
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
		KE.show({
			 id : "content2",
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
		KE.show({
			 id : "content3",
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
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var time = 1;
						var times = 1;
						$("#uploadify")
								.uploadify(
										{
											'uploader' : '${pageContext.request.contextPath}/admin/swf/uploadify.swf', //flash文件的相对路径
											'script' : '${pageContext.request.contextPath}/admin/loadImgs.jsp', //后台处理程序的相对路径
											'fileDataName' : 'file', //设置上传文件名称,默认为Filedata
											'cancelImg' : '${pageContext.request.contextPath}/admin/images/cancel.png', //每一个文件上的关闭按钮图标
											'queueID' : 'div_progress', //文件队列的ID，该ID与存放文件队列的div的ID一致
											'queueSizeLimit' : 1, //当允许多文件生成时，设置选择文件的个数，默认值：999
											'fileDesc' : '*.jpg;*.gif;*.png;*.ppt;*.pdf;*.jpeg', //用来设置选择文件对话框中的提示文本        
											'fileExt' : '*.jpg;*.gif;*.png;*.ppt;*.pdf;*.jpeg', //设置可以选择的文件的类型
											'auto' : true, //设置为true当选择文件后就直接上传了，为false需要点击上传按钮才上传
											'multi' : true, //设置为true时可以上传多个文件
											'simUploadLimit' : 1, //允许同时上传的个数 默认值：1 
											'sizeLimit' : 2048000, //上传文件的大小限制
											'buttonText' : '上传图片', //浏览按钮的文本，默认值：BROWSE
											'displayData' : 'percentage', //上传队列显示的数据类型，percentage是百分比，speed是上传速度
											//回调函数
											'onComplete' : function(evt,
													queueID, fileObj, response,
													data) {
												$("#pic11")
														.html(
																"<img src=\"${pageContext.request.contextPath}/"
																		+ response
																				.trim()
																		+ "\" width=\"110\" height=\"110\"/>");
												$("#input11").val(response.trim());
												$("#pic11").removeAttr("style");
												return false;
											},
											'onError' : function(event,
													queueID, fileObj, errorObj) {
												if (errorObj.type === "File Size") {
													alert("文件最大为2M");
													$("#uploadify")
															.uploadifyClearQueue();
												}
											},
											'onQueueFull' : function(event,
													queueSizeLimit) {
												alert("最多上传" + queueSizeLimit
														+ "张图片");
												return false;
											}
										});});
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
	if(${picturePage.type==1}){
		if (document.regForm.merchant_id.value == "") {
			alert("选择商户");
			return false;
		}
	}
	if(document.regForm.path.value==""){
		alert("商户图片没有上传");
		return false;
	}
	if(document.regForm.url.value==""){
		alert("图片的应用路径不能为空");
		return false;
	}
	if(document.regForm.num.value==""){
		alert("序号不能为空");
		return false;
	}
}
</script>
<%@ include file="login/login_chk.jsp"%>
<body>
	<form
		action="${pageContext.request.contextPath}/picture/updatePicture.do"
		name="regForm" onSubmit="return check()" method="post">
		<input type="hidden" name="id" value="${picture.id }">
		<div
			style="padding-top:50px;  float:left; width:95%; padding-left:30px;">
			<div id="i_do_wrap">
				<c:if test="${picturePage.type=='1' }">
					<div class="i_do_div rel" id="i_no_sku_stock_wrap">
						<p class="i_do_tle r_txt abs font14">商户</p>
						<select name="merchant_id" class="block input left tline">
							<option value="">--选择商户--</option>
							<c:forEach items="${merchants}" var="m">
								<option value="${m.id}"
									<c:if test="${picture.merchant_id==m.id }">selected</c:if>>${m.title}</option>
							</c:forEach>
						</select>
					</div>
				</c:if>
				<input type="text" id="input11" style="display:none;" name="path"
					value="${picture.path }" />
				<div id="pic11" class="i_do_div rel" id="i_no_sku_stock_wrap">
					<img src="../${picture.path }" width="110" height="110" id="img11" />
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">图片展示</p>
					<img id="uploadify" type="file" /><span>分辨率:750*1135</span>
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">图片的应用路径</p>
					<input type="text" id="i_no_sku_stock" name="url"
						value="${picture.url }" class="block input left tline"
						placeholder="图片的应用路径" />
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap">
					<p class="i_do_tle r_txt abs font14">序号</p>
					<input type="text" id="i_no_sku_stock" name="num"
						value="${picture.num }" class="block input left tline"
						placeholder="序号" />
				</div>
				<div class="i_do_div rel" id="i_no_sku_stock_wrap"
					style="display:none">
					<p class="i_do_tle r_txt abs font14">图片的应用类型</p>
					<input type="text" id="i_no_sku_stock" name="type"
						value="${picturePage.type }" class="block input left tline"
						placeholder="图片的应用类型" />
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