<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<style>
#edit {
	margin-top: 30px;
	margin-left: 200px;
}

#edit_1 {
	margin: 0 auto;
}
</style>
		<link href="${pageContext.request.contextPath}/admin/css/base.css"
			type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/admin/css/tab.css"
			type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/admin/css/item.css"
			type="text/css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/admin/css/item_do.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript" charset="utf-8"
			src="${pageContext.request.contextPath}/admin/js/jquery-1.9.1.min.js">
</script>
		<script type="text/javascript">
function getArea() {
	var city_id = $("#city").val();
	$.ajax( {
		type : "post",
		url : "../sightSpot/getArea.json",
		dataType : "json",
		data : {
			"city_id" : city_id
		},
		success : function(data) {
			$("#area").html("");
			$("#area").append("<option value=''>--选择区县--</option>");
			for(var i=0;i<data.length;i++){
				var obj=data[i];
				$("#area").append("<option value='"+obj.id+"'>"+obj.name+"</option>");
			}
		},
		error : function() {
		}
	});
}
</script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>聚乡村后台管理系统</title>
	</head><%@ include file="login/login_chk.jsp"%>
	<body>
		<form
			action="${pageContext.request.contextPath}/sightSpot/sightSpotList.do">
			<div
				style="padding-top: 50px; float: left; width: 95%; padding-left: 30px;">
				<div id="">
					<div class="pro_sou">
						<a
							href="${pageContext.request.contextPath}/sightSpot/toAddSightSpot.do"
							style="float: left; margin-left: 0px; margin-right: 10px;"
							class="btns distribution" id="add_item" title=" 添加一辆车辆"><em
							id="add_item_em">+</em> 添加信息</a><a class="btns distribution"
							style="float: left; margin-right: 30px;"
							href="javascript:history.back(-1)">返回</a>
						<input type="submit" value="搜索" class="btns distribution"
							style="margin-left: 20px; margin-right: 20px; float: left">
						景点名:
						<input type="text" name="name"
							value="${sessionScope.sightSpotPage.name }" class="input "
							style="margin-left: 20px; margin-right: 20px;">
						<p class="i_do_tle r_txt abs font14"></p>
						<select name="city_id"
							style="margin-left: 20px; margin-right: 20px; float: left;"
							id="city" onchange="getArea()">
							<option value="">
								--选择城市--
							</option>
							<c:forEach items="${citys }" var="c">
								<option value="${c.id}"
									<c:if test="${sessionScope.sightSpotPage.city_id==c.id }">selected</c:if>>
									${c.name}
								</option>
							</c:forEach>
						</select>
						<select name="area_id" id="area"
							style="margin-left: 20px; margin-right: 20px; float: left;">
							<option value="">
								--选择区县--
							</option>
						</select>
					</div>
					<div id="list_wrap" class="rel "
						style="width: 98%; margin-top: 17px">
						<table style="width: 100%">
							<tr
								style="text-align: center; line-height: 40px; border-bottom: 1px solid #CCC">
								<td style="width: 20%;" class="xiahuaxian">
									景点名
								</td>
								<td style="width: 10%;" class="xiahuaxian">
									所属城市
								</td>
								<td style="width: 10%;" class="xiahuaxian">
									所属区县
								</td>
								<td style="width: 20%;" class="xiahuaxian">
									景点展示
								</td>
								<td style="width: 10%;" class="xiahuaxian">
									内容
								</td>
									<td style="width:20%;" class="xiahuaxian">所属标题</td>
								<td style="width: 20%;" class="xiahuaxian" rowspan=“3”>
									操作
								</td>
							</tr>

							<c:forEach items="${sightSpots}" var="s">
								<tr
									style="text-align: center; line-height: 40px; border-bottom: 1px solid #CCC">
									<td style="width: 20%;" class="xiahuaxian">
										${s.name}
									</td>
									<td style="width: 10%;" class="xiahuaxian">
										${s.city.name}
									</td>
									<td style="width: 10%;" class="xiahuaxian">
										${s.area.name}
									</td>
									<td style="width: 20%;" class="xiahuaxian">
										<img src="../${s.path }" style="width: 110px; height: 110px" />
									</td>
									<td style="width: 10%;" class="xiahuaxian">
										${s.content}
									</td>
									<td class="xiahuaxian" style="width: 20%;">
										<c:forEach items="${s.titles}" var="t" varStatus="v">
										<c:if test="${! v.last}">${t.name}、</c:if>
										<c:if test="${v.last}">${t.name}</c:if>
										</c:forEach>
										</td>
									<td class="xiahuaxian" style="width: 20%;">
										<a
											href="${pageContext.request.contextPath}/sightSpot/toUpdateSightSpot.do?id=${s.id}">修改</a>
										&nbsp;&nbsp;|&nbsp;&nbsp;
										<a
											href="${pageContext.request.contextPath}/sightSpot/deleteSightSpot.do?id=${s.id}"
											onclick="return confirm('删除之后数据将不可恢复，确认删除吗？');">删除</a>
									</td>
								</tr>
							</c:forEach>
							<tr style="text-align: center; line-height: 60px;">
								<td colspan="11">
									第${sightSpotPage.currentPage}页/共${sightSpotPage.totalPage}页&nbsp;&nbsp;
									<a class="inline_b btns_2"
										href="${pageContext.request.contextPath}/sightSpot/sightSpotList.do?currentPage=1">首页</a>&nbsp;&nbsp;
									<a class="inline_b btns_2"
										href="${pageContext.request.contextPath}/sightSpot/sightSpotList.do?currentPage=${sightSpotPage.currentPage-1==0?1:sightSpotPage.currentPage-1}">上一页</a>&nbsp;&nbsp;
									<c:forEach begin="1" end="${sightSpotPage.totalPage}" var="num">
										<a class="inline_b btns_2" style="width: 30px;"
											href="${pageContext.request.contextPath}/sightSpot/sightSpotList.do?currentPage=${num}">${num
											}</a>
									</c:forEach>
									&nbsp;&nbsp;
									<a class="inline_b btns_2"
										href="${pageContext.request.contextPath}/sightSpot/sightSpotList.do?currentPage=${sightSpotPage.currentPage+1>sightSpotPage.totalPage?sightSpotPage.totalPage:sightSpotPage.currentPage+1}">下一页</a>&nbsp;&nbsp;
									<a class="inline_b btns_2"
										href="${pageContext.request.contextPath}/sightSpot/sightSpotList.do?currentPage=${sightSpotPage.totalPage}">尾页</a>
									<a class="inline_b btns_2"
										style="height: 32px; width: 60px; float: right;"
										href="javascript:jump()">跳转</a>
									<select id="s1" class="inline_b btns_2"
										style="height: 35px; width: 60px; float: right;">
										<c:forEach begin="1" end="${sightSpotPage.totalPage}"
											var="num">
											<option value="${num}"
												<c:if test="${sightSpotPage.currentPage==num }">selected</c:if>
												style="font-szie: 14px; color: #434343;">
												第${num}页
											</option>
										</c:forEach>
									</select>
									<script type="text/javascript">
function jump() {
	var num = document.getElementById("s1").value;
	window.location.href = "${pageContext.request.contextPath}/sightSpot/sightSpotList.do?currentPage="
			+ num;
}
</script>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>