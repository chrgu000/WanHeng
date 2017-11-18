<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>租车</title>

<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" /> 
<meta content="black" name="apple-mobile-web-app-status-bar-style" /> 
<meta content="telephone=no" name="format-detection" />

<link rel="stylesheet" type="text/css" href="../mobile/css/css.css">
<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/fastclick.js"></script>
<script src="../mobile/js/js.js"></script>

</head>
<%@ include file="../mobile/login_chk.jsp"%>
<body>

<div id="all">
    <div class="order-nav">
    	<a href="../order/showOrderCenter.do" id="a" class="cur">全部</a>
    	<a href="../order/showOrderCenter.do?status=1" id="open">待支付</a>
    	<a href="../order/showOrderCenter.do?status=3" id="maintain">租赁中</a>
    	<a href="../order/showOrderCenter.do?status=4" id="finish">已完成</a>
    	<a href="../order/showOrderCenter.do?status=2" id="cancel">已取消</a>
    	<script type="text/javascript">
      $(function(){
    	  var id='${status}';
    	           if(id=="1"){
    	        	 $("#open").addClass("cur");
    	        	 $("#a").removeClass("cur");
    	          	 $("#maintain").removeClass("cur");
    	          	 $("#cancel").removeClass("cur");
    	          	 $("#finish").removeClass("cur");
    	         }else if(id=="2"){
    	        	 $("#cancel").addClass("cur");
    	        	 $("#a").removeClass("cur");
    	          	 $("#open").removeClass("cur");
    	          	 $("#maintain").removeClass("cur");
    	          	 $("#finish").removeClass("cur");
    	         }else if(id=="3"){
    	        	 $("#maintain").addClass("cur");
    	        	 $("#a").removeClass("cur");
    	          	 $("#open").removeClass("cur");
    	          	 $("#cancel").removeClass("cur");
    	          	 $("#finish").removeClass("cur");
    	         }else if(id=="4"){
     	        $("#finish").addClass("cur");
   	        	 $("#open").removeClass("cur");
   	          	 $("#maintain").removeClass("cur");
   	          	 $("#cancel").removeClass("cur");
   	             $("#a").removeClass("cur");
    	         } else{
    	        	 $("#a").addClass("cur");
       	        	 $("#open").removeClass("cur");
       	          	 $("#maintain").removeClass("cur");
       	          	 $("#cancel").removeClass("cur");
       	             $("#finish").removeClass("cur");
    	         }
    	   });
       
        </script>
    </div>
    
    <div class="order-my">
    	<ul>
    	<c:forEach items="${orders }" var="o">
            <li>
            	<div class="order-hao"><p>订单号：${o.order_num }</p><span><c:if
										test="${o.status=='1' }"><a href="../order/zhifuM.do?order_id=${o.id }" style="backgroun:#F5A623;">待支付</a></c:if>
									<c:if test="${o.status=='2' }">已取消</c:if>
									<c:if test="${o.status=='3'}">租赁中</c:if>
									<c:if test="${o.status=='4'}">已完成</c:if></span></div>
                <div class="order1">
                    <dl>
                        <dt><img src="../${o.car.path }"></dt>
                        <dd>
                            <span>${o.car.name }</span>
                            <p>${o.car.place }厢｜${o.car.autoNum }自动｜乘坐${o.car.seatNum }人</p>
                        </dd>
                    </dl>
                </div>
                <div class="order2">
                    <dl><dt>取车:</dt><dd>${o.buyAddr }</dd></dl>
                    <dl><dt>还车:</dt><dd>${o.sendAddr }</dd></dl>
                </div>
                <div class="order3">
                   	<dl>
					<dd>
						<span>取车时间</span>
						<p>
							<fmt:formatDate value="${o.buyTime }" pattern="MM-dd" />
						</p>
						<span><fmt:formatDate value="${o.buyTime }"
								pattern="E HH:mm" /> </span>
					</dd>
					<dt>
						<p>${o.days }天</p>
					</dt>
					<dd>
						<span>还车时间</span>
						<p>
							<fmt:formatDate value="${o.sendTime }" pattern="MM-dd" />
						</p>
						<span><fmt:formatDate value="${o.sendTime }"
								pattern="E HH:mm" /> </span>
					</dd>
				</dl>
                </div>
            </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
