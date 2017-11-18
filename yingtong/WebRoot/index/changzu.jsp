<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公司长期租车|个人长期租车</title>
<meta name="keywords" content="好买车" />
<meta name="description" content="好买车" />
<link href="../index/css/public.css" rel="stylesheet" type="text/css" />
<link href="../index/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../index/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="../index/js/js.js"></script>
<script type="text/javascript">
function checkDays(){
	var bool=true;
	var value=$("#days").val();
	if(!value){
		alert("租赁时长不能为空!")
		bool=false;
	}
	return bool;
}
function checkCarNum(){
	var bool=true;
	var reg=/^[0-9]+$/
	var value=$("#car_num").val();
	 if(!reg.test(value)){
		alert("用车数量要为数字!");
		bool=false;
	}
	return bool;
}
function checkBrand(){
	var bool=true;
	var value=$("#brand").val();
	if(!value){
		alert("品牌不能为空!")
		bool=false;
	}
	return bool;
}
function checkBuyTime(){
	var bool=true;
	var value=$("#J-xl").val();
	if(!value){
		alert("取车时间不能为空!");
		bool=false;
	}
	var sB=new Date(Date.parse(value.replace(/-/g,"/")));
	if(!(new Date()<sB)){
		alert("取车时间应大于当天时间!");
		bool=false;
	}
	return bool;
}
function checkMotorcycle(){
	var bool=true;
	var value=$("#motorcycle").val();
	if(!value){
		alert("车型不能为空!");
		bool=false;
	}
	return bool;
}
function checkName(){
	var bool=true;
	var value=$("#name").val();
	if(!value){
		alert("公司名称不能为空");
		bool=false;
	}
	return bool;
}
function checkRelation(){
	var bool=true;
	var value=$("#relation_person").val();
	if(!value){
		alert("联系人不能为空");
		bool=false;
	}
	return bool;
}

function checkTel(){
	var bool=true;
	var value=$("#tel").val();
	var tels=$("#tels").val();
	var telsArr=tels.substring(1,tels.length-1).split(",");
	for(var i=0;i<telsArr.length;i++){
		if(value==telsArr[i].trim()){
			alert("该手机号已存在!");
			bool=false;
			break;
		}
	}
	var reg=/^1[3|4|5|7|8]\d{9}$/;
	 if(!reg.test(value)){
		 alert("格式错误,请输入正确的手机号！");
		 bool=false;
	 } 
	return bool;
}
function checkEmail(){
	var bool=true;
	var value=$("#email").val();
	var reg= /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(value&&!reg.test(value)){
		alert("邮箱格式不正确!");
		bool=false;
	}
		return bool;
}
 function revese(){
	  if(checkDays()&&checkCarNum()&&checkBrand()&&checkBuyTime()&&checkMotorcycle()){
		  $("#la").css("display","block");
	  }
 }
 function check(){
	 if(checkName()&&checkRelation()&&checkTel()&&checkEmail()){
		 return true;
	 }
	 return false;
 }
</script>
<script type="text/javascript">
function putBrand(id){
	$.ajax( {
		type : "post",
		url : "../longRentService/getMotorcycles.json",
		data : {
			"brand_id" : id
		},
		async: false,
		dataType : "json",
		success : function(data) {
			$("#xiala").html("");
		   for(var i=0;i<data.length;i++){
			   var obj=data[i];
			   $("#xiala").append("<a href='javascript:;'>"+obj.name+"</a>");
		   }
			$('.xiala a').click(function(){
				$(this).parent().siblings('dl').find('input').val($(this).html());
			});
		},
		
	error: function(XMLHttpRequest, textStatus, errorThrown) {
		
	}
	});
}
</script>
</head>
<body>
	<%@include file="../index/header.jsp"%>
	<ul>
		<li><a href="../index/index.do">首页</a></li>
		<li><a href="../car/duanzu.do">短租自驾</a></li>
			<li><a href="../index/zhuanche.jsp">专车</a></li>
		<li class="cur"><a
			href="../longRentService/longRentServiceIndex.do">长租</a></li>
		<li><a href="../index/enterprise.jsp">企业租车</a></li>
		<li><a href="../order/showOrder.do">订单查询</a></li>
		<li><a href="../about/index.do?title_id=1">关于我们</a></li>
	</ul>
	</div>
	</div>
	<form action="../longRentService/addlongRentService.do" method="post"
		onsubmit="return check()">
		<input type="hidden" name="tels" id="tels" value="${tels }" />
		<div class="cz-banner">
			<div class="cz-tm">
				<div class="cz-tit">长租服务</div>
				<div class="cz-input1">
					<dl>
						<dt>租赁时长:</dt>
						<dd>
							<input type="text" name="days" id="days" value="1个月" />
						</dd>
					</dl>
					<div class="xiala">
						<a href="javascript:;">1个月</a> <a href="javascript:;">2个月</a> <a
							href="javascript:;">3个月</a> <a href="javascript:;">4个月</a> <a
							href="javascript:;">5个月</a> <a href="javascript:;">6个月</a> <a
							href="javascript:;">7个月</a> <a href="javascript:;">8个月</a> <a
							href="javascript:;">9个月</a> <a href="javascript:;">10个月</a><a
							href="javascript:;">11个月</a> <a href="javascript:;">12个月</a>

					</div>
				</div>
				<div class="cz-input2">
					<dl>
						<dt>用车数量:</dt>
						<dd>
							<input type="text" name="car_num" id="car_num"
								placeholder="输入用车数量" />
						</dd>
					</dl>
				</div>
				<div class="cz-input3">
					<dl>
						<dt>品牌：</dt>
						<dd>
							<input type="text" name="brand" id="brand" placeholder="选择品牌" />
						</dd>
					</dl>
					<div class="xiala">
						<c:forEach items="${brands }" var="b">
							<a href="javascript:putBrand('${b.id}');">${b.name }</a>
						</c:forEach>
					</div>
				</div>
				<div class="cz-input4">
					<dl>
						<dt>取车时间:</dt>
						<dd>
							<input type="text" name="buy_time" class="buy_time"
								placeholder="选择取车时间" id="J-xl" />
						</dd>
					</dl>
				</div>
				<div class="cz-input5">
					<dl>
						<dt>车型：</dt>
						<dd>
							<input type="text" name="motorcycle"value="" id="motorcycle"/>
						</dd>
					</dl>
					<div class="xiala" id="xiala">
						<c:forEach items="${motorcycles }" var="m">
							<a href="javascript:;">${m.name }</a>
						</c:forEach>
					</div>
				</div>
				<div class="cz-input6">
					<input type="button" value="开始预订" onclick="revese()" />
				</div>
			</div>
		</div>
		<div class="lx-tan" style="display:none" id="la">
			<dl>
				<dt>
					<span>支付提示</span><a href="javascript:;"></a>
				</dt>
				<dd>
					<div>
						<p>企业/个人：</p>
						<input type="text" name="name" id="name" placeholder="输入企业或个人名称" />
					</div>
					<div>
						<p>*联系人：</p>
						<input type="text" name="relation_person" id="relation_person"
							placeholder="输入联系人名称（必填项）" />
					</div>
					<div>
						<p>*联系电话：</p>
						<input type="text" name="tel" id="tel" placeholder="输入联系手机号码（必填项）" />
					</div>
					<div>
						<p>E-mail：</p>
						<input type="text" name="email" id="email"
							placeholder="输入E-mail地址" />
					</div>
					<div>
						<c:if test="${message=='success' }">
							<script type="text/javascript">
					alert("申请账户成功!");
					location.href="../index/index.do";
</script>
						</c:if>
						<input type="submit" value="提交信息" />
					</div>
				</dd>
			</dl>
		</div>
	</form>
	<div class="changz1">
		<div class="changz1-nr">
			<dl>
				<dt>
					<img src="../index/images/changz1.png" />
				</dt>
				<dd>
					<span>尊享服务</span>
					<p>优先挑选，专业贴心</p>
				</dd>
			</dl>
			<dl>
				<dt>
					<img src="../index/images/changz1.png" />
				</dt>
				<dd>
					<span>经济实惠</span>
					<p>长租性价比更高</p>
				</dd>
			</dl>
			<dl>
				<dt>
					<img src="../index/images/changz1.png" />
				</dt>
				<dd>
					<span>灵活租期</span>
					<p>随用随租，更灵活</p>
				</dd>
			</dl>
		</div>
	</div>

	<div class="k-tit">
		车型大全<i>&nbsp;</i>
	</div>

	<div class="changz2">
<%--		<ul>--%>
<%--			<c:forEach items="${cars2}" var="c" varStatus="n">--%>
<%--				<li--%>
<%--					class="chex-cz<c:choose><c:when test="${n.count%6==0 }">6</c:when><c:otherwise>${n.count%6 }</c:otherwise> </c:choose>"><a--%>
<%--					href="#"><img src="../${c.path }" />--%>
<%--						<p style="color:#f5a623">${c.name }</p> </a></li>--%>
<%--			</c:forEach>--%>
<%--		</ul>--%>
<ul>
    	<li class="chex-cz1"><a href="#"><img src="../index/images/cz-img1.jpg" /><p></p></a></li>
    	<li class="chex-cz2"><a href="#"><img src="../index/images/cz-img2.jpg" /><p></p></a></li>
    	<li class="chex-cz3"><a href="#"><img src="../index/images/cz-img3.jpg" /><p> </p></a></li>
    	<li class="chex-cz4"><a href="#"><img src="../index/images/cz-img4.jpg" /><p>大众 帕萨特</p></a></li>
    	<li class="chex-cz5"><a href="#"><img src="../index/images/cz-img5.jpg" /><p></p></a></li>
    	<li class="chex-cz6"><a href="#"><img src="../index/images/cz-img6.jpg" /><p></p></a></li>
    </ul>
	</div>


	<div class="footer">
	<%@include file="../index/foot.jsp" %>
		<div class="footer-fx">
			<a href="#"><img src="../index/images/icon-fx1.png" /> </a><a
				href="#"><img src="../index/images/icon-fx2.png" /> </a><a href="#"><img
				src="../index/images/icon-fx3.png" /> </a><a href="#"><img
				src="../index/images/icon-fx4.png" /> </a>
		</div>
		<div class="footer-bq">2016 All Rights
			Rese&nbsp;&nbsp;盈通汽车租赁(杭州)有限公司&nbsp;&nbsp;版权所有</div>
	</div>
	<div class="ce-nav">
		<a href="#" class="ce1"></a> <a href="#" class="ce2"></a> <a href="#"
			class="ce3"></a>
	</div>
	<script type="text/javascript" src="../index/laydate.dev.js"></script>
	<script type="text/javascript">
		laydate({
			elem : '#J-xl'
		});
		document.getElementById('J-xl-2').onclick = function() {
			laydate({
				elem : '#J-xl-2'
			});
		}

		laydate({
			elem : '#J-xl-3'
		});

		laydate({
			elem : document.getElementById('J-xl-4')
		});
	</script>
</body>
</html>
