<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%--防强值跳转 --%>
<c:if test="${empty sessionScope.admin }">
	<script type="text/javascript">
		alert('登录超时，请先登录！');
		top.location.href = '${pageContext.request.contextPath}/admin/login.jsp';
	</script>
</c:if>