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
			src="${pageContext.request.contextPath}/admin/js/jquery-1.9.1.min.js">
</script>
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/admin/css/uploadify.css"
			type="text/css"></link>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/admin/js/jquery.uploadify.v2.0.3.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/admin/js/swfobject.js">
</script>
		<script type="text/javascript" charset="utf-8"
			src="${pageContext.request.contextPath}/admin/tiancheng/kindeditor-min.js">
</script>
		<script type="text/javascript">
KE
		.show( {
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
KE
		.show( {
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
KE
		.show( {
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
										'cancelImg' : 'images/cancel.png', //每一个文件上的关闭按钮图标
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
										'onComplete' : function(evt, queueID,
												fileObj, response, data) {
											$("#pic11")
													.html(
															"<img src=\"../"
																	+ response
																			.trim()
																	+ "\" width=\"110\" height=\"110\"/>");
											$("#input11").val(response.trim());
											$("#pic11").removeAttr("style");
											return false;
										},
										'onError' : function(event, queueID,
												fileObj, errorObj) {
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
									});
				});
</script>
		<script language="javascript">
function check() {
	var reg = /^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$/;
	var reg1 = /^\d+$/;
	if (document.regForm.merchant_id.value == "") {
		alert("选择商户");
		return false;
	}
	if (document.regForm.title.value == "") {
		alert("标题不能为空");
		return false;
	}
	if (document.regForm.sub_title.value == "") {
		alert("子标题不能为空");
		return false;
	}
	var bool = true;
	var New = document.getElementsByName("isFree");
	var value;
	for ( var i = 0; i < New.length; i++) {
		if (New.item(i).checked) {
			value = New.item(i).getAttribute("value");
			break;
		} else {
			continue;
		}
	}
	if (value == 0) {
		if (!reg.test(document.regForm.original_price.value)) {
			bool = false;
			alert("格式不正确，保留小数两位的价格");
			return bool;
		}
		if (!reg.test(document.regForm.favourable_price.value)) {
			bool = false;
			alert("格式不正确，保留小数两位的价格");
			return bool;
		}
		if (!reg.test(document.regForm.day1_original_price.value)) {
			bool = false;
			alert("格式不正确，保留小数两位的价格");
			return bool;
		}
		if (!reg.test(document.regForm.day1_favourable_price.value)) {
			bool = false;
			alert("格式不正确，保留小数两位的价格");
			return bool;
		}
		if (!reg.test(document.regForm.day5_original_price.value)) {
			bool = false;
			alert("格式不正确，保留小数两位的价格");
			return bool;
		}
		if (!reg.test(document.regForm.day5_favourable_price.value)) {
			bool = false;
			alert("格式不正确，保留小数两位的价格");
			return bool;
		}
	} else {
		$("#original_price").val(0);
		$("#favourable_price").val(0);
		$("#day1_original_price").val(0);
		$("#day1_favourable_price").val(0);
		$("#day5_original_price").val(0);
		$("#day5_favourable_price").val(0);
		if (!reg1.test(document.regForm.free_num.value)) {
			alert("免单数量为数字");
			bool = false;
			return bool;
		}
	}
	if (document.regForm.path.value == "") {
		alert("图片没有上传");
		return false;
	}
	var value = $('input:checkbox[name=titleIds]:checked');
	if (value.length == 0) {
		alert("请选择标题");
		return false;
	}
	return bool;
}
</script>
		<script type="text/javascript">
$(function check() {
	checkFree(0);
});
function checkFree(value) {
	if (value == 0) {
		$("#i_no_sku_stock_wrap1").css("display", "block");
		$("#i_no_sku_stock_wrap2").css("display", "block");
		$("#i_no_sku_stock_wrap5").css("display", "block");
		$("#i_no_sku_stock_wrap6").css("display", "block");
		$("#i_no_sku_stock_wrap7").css("display", "block");
		$("#i_no_sku_stock_wrap8").css("display", "block");
		$("#i_no_sku_stock_wrap9").css("display", "block");
		$("#i_no_sku_stock_wrap3").css("display", "none");
		$("#i_no_sku_stock_wrap4").css("display", "none");
	} else {
		$("#i_no_sku_stock_wrap1").css("display", "none");
		$("#i_no_sku_stock_wrap2").css("display", "none");
		$("#i_no_sku_stock_wrap5").css("display", "none");
		$("#i_no_sku_stock_wrap6").css("display", "none");
		$("#i_no_sku_stock_wrap7").css("display", "none");
		$("#i_no_sku_stock_wrap8").css("display", "none");
		$("#i_no_sku_stock_wrap9").css("display", "none");
		$("#i_no_sku_stock_wrap3").css("display", "block");
		$("#i_no_sku_stock_wrap4").css("display", "block");
	}
}
function getTitles() {
	var value = $("#merchant_id").val();
	$.ajax( {
		type : "post",
		url : "../product/getTitles.json",
		dataType : "json",
		data : {
			"merchant_id" : value
		},
		success : function(data) {
			$("#title").html("");
			for ( var i = 0; i < data.length; i++) {
				var obj = data[i];
					$("#title").append(
						"<input type='checkbox' name='titleIds' value='"
								+ obj.id + "'>" + obj.name);
			}
		},
		error : function() {
		}
	});
	}
</script>
		<%@ include file="login/login_chk.jsp"%>
		<body>
			<form
				action="${pageContext.request.contextPath}/product/addProduct.do"
				name="regForm" onSubmit="return check()" method="post">
				<div
					style="padding-top: 50px; float: left; width: 95%; padding-left: 30px;">
					<div id="i_do_wrap">
						<div class="i_do_div rel" id="i_no_sku_stock_wrap">
							<p class="i_do_tle r_txt abs font14">
								商户
							</p>
							<select name="merchant_id" id="merchant_id"
								class="block input left tline" onchange="getTitles()">
								<option value="">
									--选择商户--
								</option>
								<c:forEach items="${merchants}" var="m">
									<option value="${m.id}">
										${m.title}
									</option>
								</c:forEach>
							</select>
						</div>
						<div class="i_do_div rel" id="i_no_sku_stock_wrap">
							<p class="i_do_tle r_txt abs font14">
								标题
							</p>
							<input type="text" id="i_no_sku_stock" name="title"
								class="block input left tline" placeholder="标题" />
						</div>
						<div class="i_do_div rel" id="i_no_sku_stock_wrap">
							<p class="i_do_tle r_txt abs font14">
								子标题
							</p>
							<input type="text" id="i_no_sku_stock" name="sub_title"
								class="block input left tline" placeholder="子标题" />
						</div>
						<div class="i_do_div rel" id="i_no_sku_stock_wrap">
							<p class="i_do_tle r_txt abs font14">
								是否免费
							</p>
							<input type="radio" name="isFree" value="1" id="isFree"
								onclick="checkFree(this.value)">
							是
							<input type="radio" name="isFree" value="0" id="isFree"
								onclick="checkFree(this.value)" checked>
							否
						</div>
						<div id="i_no_sku_stock_wrap9">
							<div class="i_do_div rel" id="i_no_sku_stock_wrap">
								<p class="i_do_tle r_txt abs font14">
									指定日期:
								</p>
								<input type="text" name="pointDate" id="pointDate"
									class="block input left tline" placeholder="指定日期" />
								&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						<div id="i_no_sku_stock_wrap1">
							<div class="i_do_div rel" id="i_no_sku_stock_wrap">
								<p class="i_do_tle r_txt abs font14">
									原价
								</p>
								<input type="text" id="original_price" name="original_price"
									class="block input left tline" placeholder="原价" />
							</div>
						</div>
						<div id="i_no_sku_stock_wrap2">
							<div class="i_do_div rel" id="i_no_sku_stock_wrap">
								<p class="i_do_tle r_txt abs font14">
									现价
								</p>
								<input type="text" id="favourable_price" name="favourable_price"
									class="block input left tline" placeholder="现价" />
							</div>
						</div>
						<div id="i_no_sku_stock_wrap7">
							<div class="i_do_div rel" id="i_no_sku_stock_wrap">
								<p class="i_do_tle r_txt abs font14">
									周一~~周四原价
								</p>
								<input type="text" id="day1_original_price"
									name="day1_original_price" class="block input left tline"
									placeholder="周一~~周四原价" />
							</div>
						</div>
						<div id="i_no_sku_stock_wrap8">
							<div class="i_do_div rel" id="i_no_sku_stock_wrap">
								<p class="i_do_tle r_txt abs font14">
									周一~~周四现价
								</p>
								<input type="text" id="day1_favourable_price"
									name="day1_favourable_price" class="block input left tline"
									placeholder="周一~~周四现价" />
							</div>
						</div>
						<div id="i_no_sku_stock_wrap5">
							<div class="i_do_div rel" id="i_no_sku_stock_wrap">
								<p class="i_do_tle r_txt abs font14">
									周五~~周日原价
								</p>
								<input type="text" id="day5_original_price"
									name="day5_original_price" class="block input left tline"
									placeholder="周五~~周日原价" />
							</div>
						</div>
						<div id="i_no_sku_stock_wrap6">
							<div class="i_do_div rel" id="i_no_sku_stock_wrap">
								<p class="i_do_tle r_txt abs font14">
									周五~~周日现价
								</p>
								<input type="text" id="day5_favourable_price"
									name="day5_favourable_price" class="block input left tline"
									placeholder="周五~~周日现价" />
							</div>
						</div>
						<div class="i_do_div rel" id="i_no_sku_stock_wrap3">
							<p class="i_do_tle r_txt abs font14">
								免费截止日期:
							</p>
							<input type="text" id="endDate" name="endDate"
								class="block input left tline" placeholder="截止日期" />
						</div>
						<div class="i_do_div rel" id="i_no_sku_stock_wrap4">
							<p class="i_do_tle r_txt abs font14">
								免单数量:
							</p>
							<input type="text" id="free_num" name="free_num"
								class="block input left tline" placeholder="免单数量" />
						</div>

						<input type="text" id="input11" style="display: none;" name="path"
							value="" />
						<div id="pic11" style="display: none;" class="i_do_div rel"
							id="i_no_sku_stock_wrap"></div>
						<div class="i_do_div rel" id="i_no_sku_stock_wrap">
							<p class="i_do_tle r_txt abs font14">
								图片展示
							</p>
							<img id="uploadify" type="file" />
							<span>分辨率:702*280</span>
						</div>
						<div class="i_do_div rel" id="i_no_sku_stock_wrap">
							<p class="i_do_tle r_txt abs font14">
								所属标题
							</p>
							<div id="title">
							</div>
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
document.getElementById('pointDate').onclick = function() {
	laydate( {
		elem : '#pointDate'
	});
}
</script>
		</body>
</html>