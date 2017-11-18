<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>聚乡村</title>

<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"
	name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />

<link rel="stylesheet" type="text/css" href="../mobile/css/css.css">

<script src="../mobile/js/jquery-1.9.1.min.js">
</script>
<script src="../mobile/js/fastclick.js">
</script>
<script type="text/javascript">
							$(function() {
								$.ajax({
									type : "post",
									url : "../find/getCollectNums.json",
									dataType : "json",
									success : function(data) {
										$("#num").text(data);
									},
									error : function() {
									}
								});
							});
						</script>
</head>
<body>

	<div id="all">
		<div class="geren1">
			<img src="${user.path}">
			<p>
				<a href="#">${user.nickname }</a>
			</p>
		</div>
		<div class="index1">
			<a href="../order/getOrdersByTitleId.do?title_id=1">人人村游</a> <a
				href="../order/getOrdersByTitleId.do?title_id=2">人人微农</a> <a
				href="../order/getOrdersByTitleId.do?title_id=3">人人乡投</a>
		</div>

		<div class="geren2">
			<a href="../mobile/qianbao.jsp"><dl class="gricon1">
					<dt>我的钱包</dt>
					<dd>￥${user.money}</dd>
				</dl> </a> <a href="../mobile/quan.html"><dl class="gricon2">
					<dt>我的乡村券</dt>
					<dd>2张</dd>
				</dl> </a> <a href="../user/collect.do"><dl class="gricon3">
					<dt>我的收藏</dt>
					<dd>
						<span id="num"></span>
					</dd>
				</dl> </a> <a href="../mobile/personal_info.jsp"><dl class="gricon4">
					<dt>设置</dt>
					<dd>&nbsp;</dd>
				</dl> </a>
		</div>
		<div class="bottom">
			<ul>
				<li><a href="../merchant/cunyouIndex.do" class="btn1"><p>
							首页</p> </a></li>
				<li><a href="../find/find.do" class="btn2"><p>发现</p> </a></li>
				<li><a href="../sightSpot/jujing.do" class="btn3"><p>
							聚景</p> </a></li>
				<li class="cur"><a href="../mobile/center.jsp" " class="btn4"><p>
							我的</p> </a></li>
			</ul>
		</div>
	</div>

	<script src="../mobile/js/js.js">
		
	</script>
</body>
</html>
