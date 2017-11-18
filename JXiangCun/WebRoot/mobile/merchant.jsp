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
<link rel="stylesheet" type="text/css"
	href="../mobile/css/weixinshow.css">
<link rel="stylesheet" type="text/css" href="../mobile/css/css.css">
<link rel="stylesheet" href="../mobile/css/swiper.min.css">

<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/fastclick.js"></script>
<script type="text/javascript">
	var newaddress = {}
	newaddress.ealert = function(msg) {
		$('.warnMsg').html(msg);
		$('.error').show();
		$('.error').fadeIn(400).delay(800).fadeOut(400);
	}
	newaddress.eclear = function() {
		$('.warnMsg').html('');
	}
	function checkPhone(id) {
		var tel = $("#tel").val();
		$("#product_id").attr("value", id);
		if (!tel) {
			$(".phonetc").css("display", "block");
		} else {
			location.href = "../user/toSubmit.do?product_id="
					+ $("#product_id").val();
		}
	}
	function checkTel() {
		var reg = /^1[3|4|5|7|8]\d{9}$/;
		var value = $("#tel").val();
		if (!reg.test(value)) {
			newaddress.ealert("手机号格式不正确");
			return false;
		}
		return true;
	}
	$(function() {
		//获取手机验证码
		var timer = 0;
		var ti = 60;
		$('.yzmbtn').click(function() {
			if (checkTel()) {
				$(this).css('display', 'none');
				$(this).siblings('span').css('display', 'block').html('还剩60秒');
				timer = setInterval(yzm, 1000);
				$.get("../user/getTelCode.do", {
					tel : $("#tel").val()
				}, function(data) {
					if (data == 0) {
						alert("获取验证码过于频繁!");
					}
				}, 'json');
			}
		});
		function yzm() {
			ti--;
			if (ti == 0) {
				$('.yzmbtn').css('display', 'block').siblings('span').css(
						'display', 'none');
				clearInterval(timer);
				ti = 60;
			} else {
				$('.yzmspan').html("还剩" + ti + "秒");
			}
		}
	});
</script>
<style>
.swiper-slide { /* Center slide text vertically */
	display: -webkit-box;
	display: -ms-flexbox;
	display: -webkit-flex;
	display: flex;
	-webkit-box-pack: center;
	-ms-flex-pack: center;
	-webkit-justify-content: center;
	justify-content: center;
	-webkit-box-align: center;
	-ms-flex-align: center;
	-webkit-align-items: center;
	align-items: center;
}
</style>
</head>
<body style="background:#fff;">
	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
	<input type="hidden" name="tel" id="tel" value="${user.tel }">
	<div id="all">
		<div class="jj-all">
			<ul class="jj-show">
				<c:forEach items="${merchant.pictures }" var="p" varStatus="v">
					<li><img src="../${p.path }"></li>
				</c:forEach>
			</ul>
			<div class="jj-xiao">
				<div class="swiper-container">
					<div class="swiper-wrapper">
						<c:forEach items="${merchant.pictures }" var="p" varStatus="v">
							<c:choose>
								<c:when test="${v.count==1 }">
									<div class="swiper-slide">
										<a href="#" class="cur"><img src="../${p.path }"> </a>
									</div>
								</c:when>
								<c:otherwise>
									<div class="swiper-slide">
										<a href="#"><img src="../${p.path }"> </a>
									</div>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="jj-time">
				<p>${merchant.title }</p>
			</div>
			<a href="javascript:;" class="add-btn">${merchant.sightspot.name
				}</a>
			<div class="jj-btn2">
				<a href="#"><img src="../mobile/images/jj-icon1.png"> </a> <a
					href="#"><img src="../mobile/images/jj-icon2.png"> </a> <a
					href="#"><img src="../mobile/images/jj-icon3.png"> </a>
			</div>
			<div class="tw-show">
				<div class="cunyou2">
					<p>详情</p>
					<a href="javascript:;">&nbsp;</a>
				</div>
				<div class="show2">
					<c:forEach items="${products }" var="p">
						<img src="../${p.path }">
						<p>${p.sub_title }</p>
					</c:forEach>
				</div>
				<div class="show-tit">
					<p>地图</p>
					<i>&nbsp;</i>
				</div>
				<div class="map">
					<img src="../mobile/images/show-img4.jpg" style="width:100%;">
				</div>
				<div class="show-tit">
					<p>评价</p>
					<i>&nbsp;</i>
				</div>
				<div class="pinglun">
					<c:forEach items="${merchant.evaluates }" var="e">
						<dl>
							<dt>
								<img src="../${e.path}">
								<p>${e.nickname }</p>
								<div>
									<c:forEach begin="1" end="${e.num}" var="num">
										<i class="cur">&nbsp;</i>
									</c:forEach>
									<c:if test="${e.num lt 5 }">
										<c:forEach begin="${e.num+1}" end="5" var="num">
											<i>&nbsp;</i>
										</c:forEach>
									</c:if>
								</div>
							</dt>
							<dd>
								<div>${e.content }</div>
								<p>
									<f:formatDate value="${e.createDate }" pattern="yyyy-MM-dd" />
								</p>
							</dd>
						</dl>
					</c:forEach>
				</div>
			</div>
			<div class="yd-show">
				<div class="cunyou2">
					<p>在线预定</p>
					<a href="javascript:;">&nbsp;</a>
				</div>
				<div class="cunyou3">
					<c:forEach items="${products }" var="p">
						<dl>
							<dt>
								<img src="../${p.path }">
							</dt>
							<dd>
								<h5>${p.title }</h5>
								<p>${p.sub_title }</p>
								<a href="#" onclick="checkPhone('${p.id}')">马上体验</a>
								<div>
									<del>￥${p.original_price }</del>
									<b>￥${p.favourable_price }</b>
								</div>
							</dd>
						</dl>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="phonetc" style="display:none;">
			<a href="javascript:;" class="zhe">&nbsp;</a>
			<c:if test="${!empty message  }">
				<script type="text/javascript">
					newaddress.ealert('${message }');
				</script>
			</c:if>
			<dl>
				<dt>手机验证</dt>
				<dd>
					<form action="../user/checkTelCode.do" method="post">
						<input type="hidden" name="product_id" id="product_id" value="">
						<div class="inputs souji">
							<input type="text" name="tel" id="tel" placeholder="请输入手机号码"><span
								class="yzmspan">还剩60秒</span><a href="javascript:;"
								class="yzmbtn">获取验证码</a>
						</div>
						<div class="inputs yzm">
							<input type="text" name="num" placeholder="请输入验证码">
						</div>
						<div class="submits">
							<input type="submit" value="确认">
						</div>
					</form>
				</dd>
			</dl>
		</div>
		<div class="bottom2">
			<div class="bottom2-nr">
				<a href="#" class="fanhui">&nbsp;</a> <a href="javascript:;"
					class="yuding">图文详情</a> <a href="javascript:;" class="tuwen">在线预定</a>
			</div>
		</div>

	</div>


	<script src="../mobile/js/swiper.min.js"></script>
	<script type="text/javascript">
		var swiper = new Swiper('.swiper-container', {
			pagination : '.swiper-pagination',
			slidesPerView : 5,
			paginationClickable : true,
		});

		//图文详情
		$('.yuding').click(function() {
			$('.tw-show').slideDown();
			$('.yd-show').css("display", "none");
			$('.tw-show').css("display", "block");
		});
		$('.tuwen').click(function() {
			$('.yd-show').slideDown();
			$('.yd-show').css("display", "block");
			$('.tw-show').css("display", "none");
		});
		$('.cunyou2 a').click(function() {
			$(this).parent().parent().slideUp();
		});
	</script>
	<script src="../mobile/js/js.js"></script>
</body>
</html>
