<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盈通租车</title>
<meta name="keywords" content="好买车" />
<meta name="description" content="好买车" />
<link href="css/public.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/js.js"></script>
</head>
<body>
<div class="header">
		<%@include file="../index/header.jsp" %>
		<ul>
				<li><a href="../index/index.do">首页</a>
				</li>
				<li><a href="../car/duanzu.do">短租自驾</a>
				</li>
					<li><a href="../index/zhuanche.jsp">专车</a></li>
				<li ><a href="../longRentService/longRentServiceIndex.do">长租</a>
				</li>
				<li  class="cur"><a href="../index/enterprise.jsp">企业租车</a>
				</li>
				<li><a href="../order/showOrder.do">订单查询</a>
				</li>
				<li><a href="../about/index.do?title_id=1">关于我们</a>
				</li>
			</ul>
		</div>
	</div>
<div class="qiye-banner"><a href="../enterprise/toAddEnterprise.do">申请企业帐户</a></div>

<div class="k-tit">服务优势<i>&nbsp;</i></div>

<div class="ser-ys">
	<dl><dt><img src="images/Group1.png" /></dt><dd><span>价格优惠</span><p>价格比其他平台<br>更有竞争力</p></dd></dl>
	<dl><dt><img src="images/Group2.png" /></dt><dd><span>提车快</span><p>当天办理<br>当天提车</p></dd></dl>
	<dl><dt><img src="images/Group3.png" /></dt><dd><span>选择多</span><p>新车、二手车<br>皆可办理</p></dd></dl>
	<dl><dt><img src="images/Group4.png" /></dt><dd><span>服务全</span><p>原厂质保<br>一周内可退换</p></dd></dl>
</div>

<div class="k-tit">服务客户<i>&nbsp;</i></div>

<div class="ser-kh">
	<ul>
    	<li><img src="images/kehu1.jpg" /></li>
    	<li><img src="images/kehu2.jpg" /></li>
    	<li><img src="images/kehu3.png" /></li>
    	<li><img src="images/kehu4.png" /></li>
    	<li><img src="images/kehu5.png" /></li>
    	<li><img src="images/kehu6.png" /></li>
    	<li><img src="images/kehu7.png" /></li>
    	<li><img src="images/kehu8.png" /></li>
    	<li><img src="images/kehu9.png" /></li>
    	<li><img src="images/kehu10.png" /></li>
    </ul>
</div>

<div class="footer">
	<%@include file="../index/foot.jsp" %>
	<div class="footer-fx"><a href="#"><img src="images/icon-fx1.png" /></a><a href="#"><img src="images/icon-fx2.png" /></a><a href="#"><img src="images/icon-fx3.png" /></a><a href="#"><img src="images/icon-fx4.png" /></a></div>
    <div class="footer-bq">2016 All Rights Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>
</div>
<div class="ce-nav">
    <a href="#" class="ce1"></a>
    <a href="#" class="ce2"></a>
    <a href="#" class="ce3"></a>
</div>
</body>
</html>
