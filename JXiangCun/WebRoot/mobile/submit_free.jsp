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
	<div class="yaoqing1">
    	<dl>
        	<dt><img src="../${product.path }"></dt>
            <dd>
            	<div class="yq01"><span>${product.title }</span><p>${product.sub_title }</p></div>
                <div class="yq02"><i>￥<em>0</em></i><p>邀请20人支持免单</p></div>
            </dd>
        </dl>
    </div>
    <div class="jiezhi"><p><fmt:formatDate value="${product.endDate}" pattern="yyyy-MM-dd"/>截止,体验前请电话预约</p></div>
    <div class="yaoqing2">
    	<span>免费体验(流程)</span>
        <ul>
        	<li><i>1</i><p>选商品</p></li>
        	<li class="cur"><i>2</i><p>提交</p></li>
        	<li><i>3</i><p>邀请好友</p></li>
        	<li><i>4</i><p>达到人数<br>免单成功</p></li>
        </ul>
    </div>
    <div class="bottom2">
    	<div class="bottom2-nr">
        	<a href="#" class="fanhui">&nbsp;</a>
        	<p class="haixu">总价：<b>￥0</b></p>
        	<a href="javascript:location.href='../order/addOrder.do?product_id=${product.id }'" class="yuding" >提交</a>
        </div>
    </div>
</div>

<script src="../mobile/js/js.js"></script>
</body>
</html>
