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

<link rel="stylesheet" type="text/css" href="css/css.css">
<link rel="stylesheet" href="css/swiper.min.css">

<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/fastclick.js"></script>
</head>
<body>

<div id="all">
	<div class="index-top">
    	<div class="swiper-container">
            <div class="swiper-wrapper">
                <div class="swiper-slide"><img src="images/banner.jpg"></div>
                <div class="swiper-slide"><img src="images/banner.jpg"></div>
                <div class="swiper-slide"><img src="images/banner.jpg"></div>
            </div>
            <!-- Add Pagination -->
            <div class="swiper-pagination"></div>
        </div>
        <div class="index-ss">
        	<select><option>杭州</option></select>
        	<div class="ss-tianxie">
            	<input type="text" placeholder="搜索">
                <input type="submit" value="">
                <a href="#"><!--扫码--></a>
            </div>
            <a href="#" class="xiaoxi cur"><!--有消息--></a>
            <!--<a href="#" class="xiaoxi"></a>没有消息-->
        </div>
    </div>
    <div class="index1">
    	<a href="#">人人村游</a>
        <a href="#">人人微农</a>
        <a href="#" class="cur">人人乡投</a>
    </div>
    <div class="index2">
    	<dl>
        	<dt>寻觅一片山水享受生活</dt>
            <dd>
            	<select><option>智能范围</option></select>
                <select><option>价格不限</option></select>
                <select><option>智能排序</option></select>
                <select><option>个性筛选</option></select>
            </dd>
        </dl>
        <a href="javascript:;" class="sx-btn"><i></i></a>
    </div>
    <div class="index3">
    	<ul>
        	<li>
            	<a href="#">
                    <dl>
                        <dt><img src="images/pro1.jpg"><p>免费体验</p></dt>
                        <dd>
                        	<div class="index3c">
                            	<h5>桐庐－剑涌庄民宿</h5>
                            	<p>民宿介绍民宿介绍民宿介绍民宿介绍民宿介绍民宿介绍……</p>
                            </div>
                        </dd>
                    </dl>
                </a>
                <div class="zan"><a href="javascript:;"><i>&nbsp;</i></a><p>188</p></div>
            </li>
        	<li>
            	<a href="#">
                    <dl>
                        <dt><img src="images/pro1.jpg"><p>免费体验</p></dt>
                        <dd>
                        	<div class="index3c">
                            	<h5>桐庐－剑涌庄民宿</h5>
                            	<p>民宿介绍民宿介绍民宿介绍民宿介绍民宿介绍民宿介绍……</p>
                            </div>
                        </dd>
                    </dl>
                </a>
                <div class="zan"><a href="javascript:;"><i>&nbsp;</i></a><p>188</p></div>
            </li>
        </ul>
    </div>
    <div class="bottom">
    	<ul>
        	<li class="cur"><a href="#" class="btn1"><p>首页</p></a></li>
        	<li><a href="#" class="btn2"><p>发现</p></a></li>
        	<li><a href="#" class="btn3"><p>聚景</p></a></li>
        	<li><a href="#" class="btn4"><p>我的</p></a></li>
        </ul>
    </div>
</div>
<style type="text/css">
.swiper-pagination{ font-size:1px;}
.swiper-pagination-bullet{ background:rgba(255,255,255,0.5);}
.swiper-pagination-bullet-active{ background:#2BA31B;}
</style>
<script src="js/swiper.min.js"></script>
<script>
var swiper = new Swiper('.swiper-container', {
	pagination: '.swiper-pagination',
	paginationClickable: true,
	loop: true,
	autoplay:3000
});
</script>
<script src="js/js.js"></script>
</body>
</html>
