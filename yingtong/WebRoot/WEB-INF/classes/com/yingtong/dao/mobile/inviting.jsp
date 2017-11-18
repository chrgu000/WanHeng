<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/fastclick.js"></script>
</head>
<body>

<div id="all">
	<div class="yiyan1"><p>${product.merchant.title }</p></div>
    <div class="yiyan2">
    	<h5>${product.title }</h5>
<!--     	<p>入住 07月10日   入住 07月10日   共3晚</p> -->
    	<i>邀请20人支持免单</i>
    	<span><fmt:formatDate value="${product.endDate }" pattern="yyyy-MM-dd" />截止,体验前请电话预约</span>
    </div>
    
    <div class="yaoqing2">
    	<span>免费体验(流程)</span>
        <ul>
        	<li><i>1</i><p>选商品</p></li>
        	<li><i>2</i><p>提交</p></li>
        	<li class="cur"><i>3</i><p>邀请好友</p></li>
        	<li><i>4</i><p>达到人数<br>免单成功</p></li>
        </ul>
    </div>
    <div class="order-show">
        <div class="order5" style="border-top:0;"><p>还差16人，7月10日截止</p></div>
        <div class="order6">
        	<dl class="cur"><dt><img src="images/header1.jpg"><p>kk</p></dt><dd>07-01 16:00 邀请</dd></dl>
        	<dl><dt><img src="images/header2.jpg"><p>kk</p></dt><dd>07-01 17:00 支持</dd></dl>
        	<dl><dt><img src="images/header3.jpg"><p>kk</p></dt><dd>07-01 17:00 支持</dd></dl>
        	<dl><dt><img src="images/header4.jpg"><p>kk</p></dt><dd>07-01 17:00 支持</dd></dl>
        </div>
    </div>
    <div class="bottom2">
    	<div class="bottom2-nr">
        	<a href="#" class="fanhui">&nbsp;</a>
        	<p class="haixu">还需邀请16人</p>
        	<a href="#" class="yuding">邀请好友</a>
        </div>
    </div>
    <!--弹窗-->
    <div class="yq-tanc">
    	<a href="javascript:;" class="zhe">&nbsp;</a>
    	<dl>
        	<dt>kk发起了邀请，点支持就能让他免单</dt>
            <dd>
            	<div class="yqtc01">
                	<div class="yqtc02"><img src="images/cyimg1.jpg"></div>
                    <div class="yqtc03">
                    	<span>兰花山景房</span>
                        <p>副标题</p>
                        <i>￥<em>0</em>&nbsp;邀请20人支持免单</i>
                    </div>
                </div>
                <a href="#" class="tc-btn">支持</a>
            </dd>
        </dl>
    </div>
</div>

<script src="../mobile/js/js.js"></script>
</body>
</html>
