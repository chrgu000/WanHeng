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
<link rel="stylesheet" href="../mobile/css/swiper.min.css">

<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/fastclick.js"></script>
</head>
<body>
	<form action="../merchant/cunyouIndex.do" method="post">
		<div id="all">
			<div class="index-top">
				<div class="swiper-container">
					<div class="swiper-wrapper">
						<c:forEach items="${pictures }" var="p">
							<div class="swiper-slide">
								<img src="../${p.path }">
							</div>
						</c:forEach>
					</div>
					<!-- Add Pagination -->
					<div class="swiper-pagination"></div>
				</div>
				<div class="index-ss">
					<select name="city_id" style="color:black"><option
							value="">-城市-</option>
						<c:forEach items="${citys }" var="c">
							<option value="${c.id }"
								<c:if test="${select.city_id==c.id }">selected</c:if>>${c.name
								}</option>
						</c:forEach>
					</select>
					<div class="ss-tianxie">
						<input type="text" name="title" value="${select.title }"
							placeholder="搜索"> <input type="submit" value="">
						<a href="#"> <!--扫码--> </a>
					</div>
					<a href="#" class="xiaoxi cur"> <!--有消息--> </a>
					<!--<a href="#" class="xiaoxi"></a>没有消息-->
				</div>
			</div>
			<div class="index1">
				<a href="../merchant/cunyouIndex.do?title_id=1" <c:if test="${select.title_id==1 }">class="cur"</c:if> >人人村游</a> <a href="../merchant/cunyouIndex.do?title_id=2" <c:if test="${select.title_id==2}">class="cur"</c:if>>人人微农</a> <a href="../merchant/cunyouIndex.do?title_id=3" <c:if test="${select.title_id==3 }">class="cur"</c:if>>人人乡投</a>
			</div>
			<div class="index2">
				<dl>
					<dt>众筹一间民宿放飞梦想</dt>
					<dd>
						<select name="distance_id"><option value="">智能范围</option>
							<c:forEach items="${distances }" var="d">
								<option value="${d.id }"
									<c:if test="${select.distance_id==d.id }">selected</c:if>>${d.distance
									}</option>
							</c:forEach>
						</select> <select name="price_id"><option value="">价格不限</option>
							<c:forEach items="${prices }" var="p">
								<option value="${p.id }"
									<c:if test="${select.price_id==p.id }">selected</c:if>>${p.price
									}</option>
							</c:forEach>
						</select> <select name="sequence_id"><option value="">智能排序</option>
							<c:forEach items="${sequences }" var="s">
								<option value="${s.id }"
									<c:if test="${select.sequence_id==s.id }">selected</c:if>>${s.sequence
									}</option>
							</c:forEach>
						</select> <select name="mark_id"><option value="">个性筛选</option>
							<c:forEach items="${marks}" var="m">
								<option value="${m.id }"
									<c:if test="${select.mark_id==m.id }">selected</c:if>>${m.name
									}</option>
							</c:forEach>
						</select>
					</dd>
				</dl>
				<a href="javascript:;" class="sx-btn"><i></i> </a>
			</div>
			<div class="index3">
				<ul>
					<c:forEach items="${merchants }" var="m">
						<a
							href="../product/getProductsByMerchantId.do?merchant_id=${m.id }">
							<li>
								<dl>
									<dt>
										<img src="../${m.path }">
									</dt>
									<dd>
										<div class="index3a">
											<h5>${m.title }</h5>
											<p>
												<c:forEach items="${m.marks }" var="mm">
													<span>${mm.name }</span>
												</c:forEach>
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
								</dl>
								<div class="zan">
									<a href="javascript:;"><i>&nbsp;</i> </a>
									<p>${m.isOk}</p>
									<span style="display:none">${m.id }</span>
								</div></li> </a>
					</c:forEach>
				</ul>
			</div>
			<script type="text/javascript">
				$(function() {
					//点赞
					$('.zan a').click(function() {
						var sum = $(this).siblings('p').html();
						var id = $(this).siblings('span').html();
						if ($(this).find('.cur').length > 0) {
							$(this).find('i').removeClass('cur');
							sum--;
							$(this).siblings('p').html(sum);
						} else {
							$(this).find('i').addClass('cur');
							sum++;
							$(this).siblings('p').html(sum);
						}
						$.ajax({
							type : "post",
							url : "../merchant/updateIsOk.json",
							dataType : "json",
							data : {
								"id" : id,
								"isOk" : sum
							},
							success : function(data) {
							},
							error : function() {
							}
						});
					});
				})
			</script>
			<div class="bottom">
				<ul>
					<li class="cur"><a href="../merchant/cunyouIndex.do"
						class="btn1"><p>首页</p> </a>
					</li>
					<li><a href="../find/find.do" class="btn2"><p>发现</p></a>
					</li>
					<li><a href="../sightSpot/jujing.do" class="btn3"><p>聚景</p>
					</a>
					</li>
					<li><a href="../mobile/center.jsp" " class="btn4"><p>我的</p>
					</a> 
					</li>
				</ul>
			</div>
		</div>
	</form>
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
