<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<link rel="stylesheet" type="text/css" href="../mobile/css/css.css">

<script src="../mobile/js/jquery-1.9.1.min.js"></script>
<script src="../mobile/js/fastclick.js"></script>
</head>
<body>

	<div id="all">
		<div class="add-list">
			<c:forEach items="${addresses }" var="a" varStatus="v">
				<dl>
					<dt>
						<div class="name">${a.username }</div>
						<div class="show">
							<span>${a.tel }</span>
							<p>${a.s_province }${a.s_city }${a.s_county }${a.address }</p>
						</div>
					</dt>
					<dd>
						<!--注意：每个checkbox都要有不同的id,不然选中样式失效-->
						<p>
							<input type="checkbox" id="checkbox${v.count }" class="checkbox"
								value="${a.id }"
								<c:if test="${a.isDefault=='1' }"> checked="checked"</c:if>><label
								for="checkbox1" onclick="setDefaultAddress(${v.count})"></label>设为默认地址
						</p>
						<script type="text/javascript">
						function setDefaultAddress(count){
						  var value=$("#checkbox"+count).val();
						  location.href="../user/setDefaultAddress.do?address_id="+value;
						}
						</script>
						<a href="../user/toUpdateMyAddress.do?id=${a.id }" class="bianji">编辑</a> <a href="../user/deleteAddress.do?id=${a.id }" class="dels">删除</a>
					</dd>
				</dl>
			</c:forEach>
		</div>
		<div class="newsj">
			<a href="../mobile/address_add.jsp">新建地址 </a>
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
