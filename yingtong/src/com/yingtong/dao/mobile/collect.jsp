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

<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/fastclick.js"></script>
</head>
<body>

<div id="all">
    
    <div class="index3">
    	<ul>
    	<c:forEach items="${merchants}" var="m">
        	<li>
            	<a href="../product/getProductsByMerchantId.do?merchant_id=${m.id }">
                    <dl>
                        <dt><img src="../${m.path }"></dt>
                        <dd>
                        	<div class="index3a">
                            	<h5>${m.title }</h5>
                            	<p>
                            	<c:forEach items="${m.marks }" var="mm"><span>${mm.name }</span></c:forEach>
                            </div>
                             <div class="index3b">
                            	<div><del>￥${m.original_price }</del><b>￥${m.favourable_price }</b></div>
                            	<p>${m.distance }公里</p>
                            </div>
                        </dd>
                    </dl>
                </a>
                <div class="zan"><a href="javascript:;"><i class="cur">&nbsp;</i></a><p>${merchant.isOk }</p></div>
            </li>
            </c:forEach>
            <c:forEach items="${sightSpots }" var="s">
        	<li>
            	<a href="../sightSpot/jujing.do?sight_spot_id=${s.id }">
                    <dl>
                        <dt><img src="../${s.path }"></dt>
                        <dd>
                        	<div class="index3a">
                            	<h5>${s.name }</h5>
                            </div>
                            <div class="index3b">
                            	<p>${s.distance }公里</p>
                            </div>
                        </dd>
                    </dl>
                </a>
            </li>
            </c:forEach>
        </ul>
    </div>
    
    <div class="bottom2">
    	<div class="bottom2-nr">
        	<a href="#" class="fanhui">&nbsp;</a>
        </div>
    </div>
    
</div>

<script src="../mobile/js/js.js"></script>
</body>
</html>
