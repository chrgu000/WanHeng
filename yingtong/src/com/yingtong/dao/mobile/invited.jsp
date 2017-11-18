<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		$(function(){
				var endDate=$("#endDate").val();
				var end=endDate.substring(0,10);
				var ed=new Date(Date.parse(end.replace(/-/g,"/")));
				var isInvite=$("#isInvite").val();
				if(ed<new Date()){
					$(".yq-tanc").css("display", "none");
					newaddress.ealert("截止日期已到,不能参与支持!");
					$.ajax({type:"post",url:"../order/updateOrder.json",dataType:"json",data:{"order_id":${order.id}},success:function(data){},error:function(){}});
					return;
				}
				if(isInvite==2){
					$(".yq-tanc").css("display", "none");
					newaddress.ealert("截止日期已到,不能参与支持!");
					$("#p").html("未达到邀请人数，免单失败");
				}else if(isInvite==1){
					$(".yq-tanc").css("display", "none");
				}
		});
		
		</script>
</head>
<body>
	<DIV class="error none" style="text-align: center">
		<DIV class="warnMsg" style="text-align: center"></DIV>
	</DIV>
	<div id="all">
		<div class="yiyan1">
			<p>${merchant.title }</p>
		</div>
		<div class="yiyan2">
			<h5>${product.title }</h5>
			<p>
				入住
				<fmt:formatDate value="${order.beginTime }" pattern="yyyy-MM-dd" />
				入住
				<fmt:formatDate value="${order.endTime }" pattern="yyyy-MM-dd" />
				共${order.days }晚
			</p>
			<i>邀请${order.free_num }人支持免单</i> <span><fmt:formatDate
					value="${order.endDate }" pattern="MM-dd" />截止,体验前请电话预约</span>
		</div>
		<c:if test="${! empty msg }">
			<script type="text/javascript">
				newaddress.ealert('${msg}');
			</script>
		</c:if>
		<div class="yaoqing2">
			<span>免费体验(流程)</span>
			<ul>
				<li><i>1</i>
					<p>选商品</p>
				</li>
				<li><i>2</i>
					<p>提交</p>
				</li>

				<li <c:if test="${order.isInvite=='0'}">class="cur"</c:if>><i>3</i>
					<p>邀请好友</p>
				</li>
				<li <c:if test="${order.isInvite=='1'}">class="cur"</c:if>><i>4</i>
					<p>
						达到人数<br>免单成功
					</p>
				</li>
			</ul>
		</div>
		<input type="hidden" name="endDate" id="endDate"
			value="${order.endDate }"> <input type="hidden"
			name="isInvite" id="isInvite" value="${order.isInvite }">
		<div class="order-show">
			<div class="order5" style="border-top:0;">
				<c:if test="${num  gt 0}">
					<p>
						还差${num }人，
						<fmt:formatDate value="${order.endDate }" pattern="MM-dd" />
						截止
					</p>
				</c:if>
				<c:if test="${order.isInvite=='1' }">
					<p>达到邀请人数,免单成功!</p>
				</c:if>
				<p id="p"></p>
			</div>
			<div class="order6">
				<dl class="cur">
					<dt>
						<img src="${user.path }">
						<p>${user.nickname }</p>
					</dt>
					<dd>
						<fmt:formatDate value="${order.createTime }" pattern="MM-dd HH:mm" />
						邀请
					</dd>
				</dl>
				<c:forEach items="${invites}" var="i">
					<dl>
						<dt>
							<img src="${i.inviter.url }">
							<p>${i.inviter.nickname }</p>
						</dt>
						<dd>
							<fmt:formatDate value="${i.createDate }" pattern="MM:dd HH:mm" />
							支持
						</dd>
					</dl>
				</c:forEach>
			</div>
		</div>
		<div class="bottom2">
			<div class="bottom2-nr">
				<a href="#" class="fanhui">&nbsp;</a>
				<p class="haixu"><c:if test="${order.spare_num>=0}">还需邀请${order.spare_num}人</c:if></p>
				<a href="#" class="yuding" onclick="join()">我要参加</a>
			</div>
		</div>
		<script type="text/javascript">
			function join() {
				location.href = "../user/intoStart.do?state=" + ${merchant.id}+"&order_id=" + ${order.id};}
			function support() {
				$(".yq-tanc").css("display", "block");
				location.href = "../user/intoStart.do?order_id="
						+ '${order.id}';
			}
			$(function(){
				if(${b==1}){
					$(".yq-tanc").css("display", "none");
				}
			});
		</script>
		<!--弹窗-->
		<div class="yq-tanc" style="display:block;">
			<a href="javascript:;" class="zhe">&nbsp;</a>
			<dl>
				<dt>${user.nickname }发起了邀请，点支持就能让他免单</dt>
				<dd>
					<div class="yqtc01">
						<div class="yqtc02">
							<img src="../${product.path }">
						</div>
						<div class="yqtc03">
							<span>${product.title }</span>
							<p>${product.sub_title }</p>
							<i>￥<em>0</em>&nbsp;邀请${order.free_num }人支持免单</i>
						</div>
					</div>
					<a href="#" class="tc-btn" onclick="support()">支持</a>
				</dd>
			</dl>
		</div>
	</div>

	<script src="../mobile/js/js.js"></script>
</body>
</html>
