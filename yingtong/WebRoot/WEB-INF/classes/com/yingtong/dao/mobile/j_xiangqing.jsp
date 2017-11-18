<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>聚乡村</title>

<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" /> 
<meta content="black" name="apple-mobile-web-app-status-bar-style" /> 
<meta content="telephone=no" name="format-detection" />

<link rel="stylesheet" type="text/css" href="../mobile/css/css.css">
<link rel="stylesheet" href="../mobile/css/swiper.min.css">

<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/fastclick.js"></script>
</head>
<body style="background:#fff;">
<style>

.swiper-slide {
	/* Center slide text vertically */
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

<div id="all" style="display:block">
	<div class="jj-all" style="display:block">
<%--    	<ul class="jj-show">--%>
<%--        	<li><img src="images/jj-img.jpg"></li>--%>
<%--        	<li><img src="images/jj-img.jpg"></li>--%>
<%--        	<li><img src="images/jj-img.jpg"></li>--%>
<%--        	<li><img src="images/jj-img.jpg"></li>--%>
<%--        	<li><img src="images/jj-img.jpg"></li>--%>
<%--        	<li><img src="images/jj-img.jpg"></li>--%>
<%--        	<li><img src="images/jj-img.jpg"></li>--%>
<%--        </ul>--%>
<%--        <div class="jj-xiao">--%>
<%--        	<div class="swiper-container">--%>
<%--                <div class="swiper-wrapper">--%>
<%--                    <div class="swiper-slide"><a href="javascript:;" class="cur"><img src="images/jj-img.jpg"></a></div>--%>
<%--                    <div class="swiper-slide"><a href="javascript:;"><img src="images/jj-img.jpg"></a></div>--%>
<%--                    <div class="swiper-slide"><a href="javascript:;"><img src="images/jj-img.jpg"></a></div>--%>
<%--                    <div class="swiper-slide"><a href="javascript:;"><img src="images/jj-img.jpg"></a></div>--%>
<%--                    <div class="swiper-slide"><a href="javascript:;"><img src="images/jj-img.jpg"></a></div>--%>
<%--                    <div class="swiper-slide"><a href="javascript:;"><img src="images/jj-img.jpg"></a></div>--%>
<%--                    <div class="swiper-slide"><a href="javascript:;"><img src="images/jj-img.jpg"></a></div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="jj-time"><p>安吉浙北大峡谷</p></div>--%>
<%--        <a href="javascript:;" class="add-btn">地区切换</a>--%>
<%--        <div class="jj-btn">--%>
<%--        	<a href="#" class="cur">村游</a>--%>
<%--            <a href="#">乡投</a>--%>
<%--            <a href="#">微农</a>--%>
<%--        </div>--%>
<%--        <div class="jj-btn2">--%>
<%--        	<a href="#"><img src="images/jj-icon1.png"></a>--%>
<%--        	<a href="#"><img src="images/jj-icon2.png"></a>--%>
<%--        	<a href="#"><img src="images/jj-icon3.png"></a>--%>
<%--        </div>--%>
<%--        --%>
<%--        <div class="add-jjall">--%>
<%--        	<div class="add-tit"><a href="javascript:;" class="add-qx">取消</a><a href="javascript:;" class="add-qr">确认</a></div>--%>
<%--        	<div class="add-jj">
<%--            	<ul>--%>
<%--                	<li class="cur"><a href="#">丽水</a></li>--%>
<%--                	<li><a href="#">台州</a></li>--%>
<%--                	<li><a href="#">金华</a></li>--%>
<%--                	<li><a href="#">宁波</a></li>--%>
<%--                	<li><a href="#">杭州</a></li>--%>
<%--                	<li><a href="#">宁波</a></li>--%>
<%--                	<li><a href="#">丽水</a></li>--%>
<%--                	<li><a href="#">台州</a></li>--%>
<%--                	<li><a href="#">金华</a></li>--%>
<%--                	<li><a href="#">宁波</a></li>--%>
<%--                	<li><a href="#">杭州</a></li>--%>
<%--                	<li><a href="#">宁波</a></li>--%>
<%--                </ul>--%>
<%--                <ul>--%>
<%--                	<li class="cur"><a href="#">狮子山村</a></li>--%>
<%--                	<li><a href="#">南浔古镇</a></li>--%>
<%--                	<li><a href="#">浙北大峡谷</a></li>--%>
<%--                	<li><a href="#">安吉大竹海</a></li>--%>
<%--                	<li><a href="#">安吉天荒坪</a></li>--%>
<%--                	<li><a href="#">莫干山景区</a></li>--%>
<%--                	<li><a href="#">狮子山村</a></li>--%>
<%--                	<li><a href="#">南浔古镇</a></li>--%>
<%--                	<li><a href="#">浙北大峡谷</a></li>--%>
<%--                	<li><a href="#">安吉大竹海</a></li>--%>
<%--                	<li><a href="#">安吉天荒坪</a></li>--%>
<%--                	<li><a href="#">莫干山景区</a></li>--%>
<%--                </ul>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        --%>
        <div class="tw-show"  style="display:block">
        	<div class="cunyou2"  style="display:block">
                <p>在线预定</p>
                <a href="#">&nbsp;</a>
            </div>
            <div class="show2"  style="display:block">
            <c:forEach items="${products }" var="p">
                <img src="../${p.path }">
                <p>${p.sub_title }</p>
                </c:forEach>
            </div>
        </div>
        
    </div>
    
    <div class="bottom">
    	<ul>
        	<li><a href="../merchant/cunyouIndex.do" class="btn1"><p>首页</p>
				</a></li>
				<li><a href="../mobile/find.html" class="btn2"><p>发现</p> </a>
				</li>
				<li class="cur"><a href="../sightSpot/jujing.do" class="btn3"><p>聚景</p>
				</a></li>
				<li><a href="../mobile/center.jsp" " class="btn4"><p>我的</p>
				</a></li>
        </ul>
    </div>
   
</div>
<script src="../mobile/js/swiper.min.js"></script>
<script>
var swiper = new Swiper('.swiper-container', {
	pagination: '.swiper-pagination',
	slidesPerView: 5,
	paginationClickable: true,
});
</script>
<script src="../mobile/js/js.js"></script>
</body>
</html>
