<%@page language="java" contentType="text/html; charset=utf-8"
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
<link rel="stylesheet" href="../mobile/css/swiper.min.css">

<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/fastclick.js"></script>
</head>
<body>

	<div id="all">
		<div class="faxian">
			<div class="swiper-container">
				<div class="swiper-wrapper">
					<c:forEach items="${pictures}" var="p">
					<div class="swiper-slide">
						<img src="../${p.path }">
					</div>
					</c:forEach>
				</div>
				<!-- Add Pagination -->
				<div class="swiper-pagination"></div>
			</div>
		</div>

		<div class="faxian1">
		<a href="../find/find.do"
					<c:if test="${empty merchantPage.mark_id }">class="cur"</c:if>>景区</a>
			<c:forEach items="${marks }" var="m">
				<a href="../find/find.do?mark_id=${m.id }"
					<c:if test="${m.id==merchantPage.mark_id }">class="cur"</c:if>>${m.name
					}</a>
			</c:forEach>
		</div>

		<div class="faxian2">
			<div class="xiala">
				<a href="../find/find.do">热门</a> 
			<c:forEach items="${sightSpots }" var="s">
				<a href="../find/find.do?sight_spot_id=${s.id }">${s.name }</a> 
				</c:forEach>
			</div>
			<a href="javascript:;" class="zhankai"><i>&nbsp;</i>
			</a>
		</div>
		<div class="index3">
			<ul>
			<c:forEach items="${merchants }" var="m">
				<li><a href="../product/getProductsByMerchantId.do?merchant_id=${m.id }">
						<dl>
							<dt>
								<img src="../${m.path }">
<!-- 								<p>免费体验</p> -->
							</dt>
							<dd>
								<div class="index3a">
									<h5>${m.title }</h5>
									<p>
									<c:forEach items="${m.marks }" var="k">
										<span>${k.name }</span></c:forEach>
									</p>
								</div>
								<div class="index3b">
									<div>
										<del>￥${m.original_price }</del>
										<b>￥${m.favourable_price }</b>
									</div>
									<p>${m.distance }公里</p>
								</div>
							</dd>
						</dl> </a>
					<div class="zan">
						<a href="javascript:;"><i>&nbsp;</i>
						</a>
						<p>${m.isOk }</p>
					</div></li>
					</c:forEach>
			</ul>
		</div>


		<div class="bottom">
			<ul>
				<li><a href="../merchant/cunyouIndex.do" class="btn1"><p>首页</p>
				</a></li>
				<li class="cur"><a href="../find/find.do" class="btn2"><p>发现</p>
				</a></li>
				<li><a href="../sightSpot/jujing.do" class="btn3"><p>聚景</p></a>
				</li>
				<li ><a href="../mobile/center.jsp" " class="btn4"><p>我的</p>
				</a></li>
			</ul>
		</div>
	</div>
	<style type="text/css">
.swiper-pagination {
	font-size: 1px;
}

.swiper-pagination-bullet {
	background: rgba(255, 255, 255, 0.5);
}

.swiper-pagination-bullet-active {
	background: #2BA31B;
}
</style>
	<script src="../mobile/js/swiper.min.js"></script>
	<script>
		var swiper = new Swiper('.swiper-container', {
			pagination : '.swiper-pagination',
			paginationClickable : true,
			loop : true,
			autoplay : 3000
		});
	</script>
	<script src="../mobile/js/js.js"></script>
</body>
</html>
