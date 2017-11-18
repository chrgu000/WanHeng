<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盈通租车</title>
<meta name="keywords" content="好买车" />
<meta name="description" content="好买车" />
<link href="../index/css/public.css" rel="stylesheet" type="text/css" />
<link href="../index/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../index/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="../index/js/js.js"></script>
</head>
<script type="text/javascript">
      $(function(){
    	  var id='${status}';
    	           if(id=="1"){
    	        	 $("#open").addClass("cur");
    	        	 $("#all").removeClass("cur");
    	          	 $("#maintain").removeClass("cur");
    	          	 $("#cancel").removeClass("cur");
    	          	 $("#finish").removeClass("cur");
    	         }else if(id=="2"){
    	        	 $("#cancel").addClass("cur");
    	        	 $("#all").removeClass("cur");
    	          	 $("#open").removeClass("cur");
    	          	 $("#maintain").removeClass("cur");
    	         }else if(id=="3"){
    	        	 $("#maintain").addClass("cur");
    	        	 $("#all").removeClass("cur");
    	          	 $("#open").removeClass("cur");
    	          	 $("#cancel").removeClass("cur");
    	         }else if(id=="4"){
     	        $("#finish").addClass("cur");
   	        	 $("#open").removeClass("cur");
   	          	 $("#maintain").removeClass("cur");
   	          	 $("#cancel").removeClass("cur");
   	             $("#all").removeClass("cur");
    	         } else{
    	        	 $("#all").addClass("cur");
       	        	 $("#open").removeClass("cur");
       	          	 $("#maintain").removeClass("cur");
       	          	 $("#cancel").removeClass("cur");
       	             $("#finish").removeClass("cur");
    	         }
    	   });
       
        </script>
        <%@ include file="../index/login_chk.jsp"%>
<body style="background:#f6f8fa;">
	<%@include file="../index/header.jsp"%>
	<ul>
		<li><a href="../index/index.do">首页</a></li>
		<li><a href="../car/duanzu.do">短租自驾</a></li>
		<li><a href="../index/zhuanche.jsp">专车</a></li>
		<li><a href="../longRentService/longRentServiceIndex.do">长租</a>
		</li>
		<li><a href="../index/enterprise.jsp">企业租车</a></li>
		<li class="cur"><a href="../order/showOrder.do">订单查询</a></li>
		<li><a href="../about/index.do?title_id=1">关于我们</a></li>
	</ul>
	</div>
	</div>

	<div class="ny-all">
		<div class="ny-left">
			<dl>
				<dt>
					<a href="#">我的帐户</a>
				</dt>
				<dd>
					<a href="../user/myInfo.do">我的信息</a>
				</dd>
				<dd>
					<a href="../user/toUpdatePwd.do">修改密码</a>
				</dd>
			</dl>
			<dl>
				<dt>
					<a href="#">我的订单</a>
				</dt>
				<dd class="cur">
					<a href="../order/showOrder.do">短租自驾</a>
				</dd>
			</dl>
			<dl>
				<dt>
					<a href="#">会员条款</a>
				</dt>
				<c:forEach items="${articles5 }" var="a">
					<dd>
						<a
							href="javascript:location.href='../about/index.do?id=${a.id }';">${a.titles
							}</a>
					</dd>
				</c:forEach>
			</dl>
		</div>
		<div class="ny-right">
			<div class="order1">
				<div class="order1-a">我的订单</div>
			</div>
			<div class="order2">
				<ul>
					<li id="all"><a href="../order/showOrder.do?status=">全部订单</a>
					</li>

					<li id="open"><a href="../order/showOrder.do?status=1" >预约中</a>
					</li>

					<li id="maintain"><a href="../order/showOrder.do?status=3">租赁中</a>
					</li>
					<li id="cancel"><a href="../order/showOrder.do?status=2">已取消</a>
					</li>
					<li id="finish"><a href="../order/showOrder.do?status=4">已完成</a>
					</li>
				</ul>
			</div>
			
			<div class="order3">
				<div class="order3-tit">
					<p class="order-tit1">订单信息</p>
					<p class="order-tit2">取还车信息</p>
					<p class="order-tit3">总计</p>
					<p class="order-tit4">订单状态</p>
				</div>
				<ul>
					<c:forEach items="${orders }" var="o">
						<li>
							<div class="order3-a">
								<dl>
									<dt>
										<img src="../${o.car.path }" />
									</dt>
									<dd>
										<span>${o.car.name }</span>
										<p>${o.car.place }厢｜${o.car.autoNum }自动｜乘坐${o.car.seatNum
											}人</p>
									</dd>
								</dl>
							</div>
							<div class="order3-b">
								<p>取车：${o.buyAddr }</p>
								<span><fmt:formatDate value="${o.buyTime }"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</span>
								<p>还车：${o.sendAddr }</p>
								<span><fmt:formatDate value="${o.sendTime }"
										pattern="yyyy--MM-dd HH:mm:ss" />
								</span>
							</div>
							<div class="order3-c">
								<p>
									￥<span>${o.total_price }</span>
								</p>
							</div>
							<div class="order3-d">
								<span style="margin-top:25px;"><c:if
										test="${o.status=='1' }"><span style="cursor:pointer" onclick="window.location.href='../order/zhifu.do?order_id=${o.id }'">待支付</span></c:if>
									<c:if test="${o.status=='2' }">已取消</c:if>
									<c:if test="${o.status=='3'}">租赁中</c:if>
									<c:if test="${o.status=='4'}">已完成</c:if>
								</span><a href="../order/checkOrder.do?order_id=${o.id }"
									class="order-btn2">查看订单</a>
							</div></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<c:if test="${order eq 't' }">
		<form action="../user/login1.do?order=t" method="post">
			<div class="denglu" style="display:block">
				<div class="denglu-nr">
					<a href="javascript:;" class="off">&nbsp;</a>
					<div class="tc-1">
						<span>登录</span>
						<p>
							<i>没有帐户</i><a href="../user/toRegist.do">立即注册</a>
						</p>
					</div>
					<div class="tc-2">
						<input type="text" name="tel" placeholder="请输入手机号" /><input
							type="password" name="password" placeholder="请输入密码" />
					</div>
					<div class="tc-3">
						<a href="../index/findBackPwd.jsp">忘记密码？</a>
					</div>
					<div class="tc-4">
						<input type="submit" value="登录" />
					</div>
					<h3 style="color:red;margin-left:135px;margin-top:20px">${message
						}</h3>
				</div>
			</div>
			</form>
	</c:if>
	<%@include file="../index/foot.jsp"%>
	<div class="footer-fx">
			<a href="#"><img src="../index/images/icon-fx1.png" /> </a><a href="#"><img
				src="../index/images/icon-fx2.png" /> </a><a href="#"><img
				src="../index/images/icon-fx3.png" /> </a><a href="#"><img
				src="../index/images/icon-fx4.png" /> </a>
		</div>
	<div class="footer-bq">2016 All Rights
		Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>
	<div class="ce-nav">
		<a href="#" class="ce1"></a> <a href="#" class="ce2"></a> <a href="#"
			class="ce3"></a>
	</div>

</body>
</html>
