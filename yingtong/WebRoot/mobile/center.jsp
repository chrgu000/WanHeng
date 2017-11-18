<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盈通汽车</title>

<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;"
	name="viewport" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="black" name="apple-mobile-web-app-status-bar-style" />
<meta content="telephone=no" name="format-detection" />

<link rel="stylesheet" type="text/css" href="css/css.css">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/fastclick.js"></script>
<script type="text/javascript">
function invalidate(){
	location.href="../index/login_out1.jsp"
}
</script>
</head>
<%@ include file="../mobile/login_chk.jsp"%>
<body>

	<div id="all">
		<div class="geren-tit">
			<dl>
				<dt>
					<img src="${user.path }">
				</dt>
				<dd>
					<a href="#">${user.tel }</a>
				</dd>
			</dl>
		</div>
		<div class="geren-btn" style="margin:0;">
			<a href="../order/showOrderCenter.do" class="gr1"><p>我的订单</p>
			</a> <a href="../order/getMyFaPiao.do" class="gr2"><p>我的发票</p>
			</a>
		</div>
		<div class="geren-btn">
			<a href="../about/helpcenter.do" class="gr3"><p>帮助中心</p>
			</a> <a href="../mobile/fankui.jsp" class="gr4"><p>用户反馈</p>
			</a>
		</div>
		<div class="geren-btn">
			<a href="../mobile/password.jsp" class="gr5"><p>修改密码</p>
			</a>
		</div>
		<div class="geren-btn">
			<a href="../mobile/myInfoUpdate.jsp" class="gr5"><p>个人信息</p>
			</a>
		</div>
		<div class="baocun">
			<input type="button" value="退出登录" onclick="invalidate()">
		</div>

	</div>
	<script src="js/js.js"></script>
</body>
</html>
