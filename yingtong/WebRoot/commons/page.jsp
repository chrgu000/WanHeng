<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- 分页的东东 -->
  				第${page.pagenum}页/共${page.totalpage}页&nbsp;&nbsp;
  				<a class="inline_b btns_2" href="${pageContext.request.contextPath}/${page.url}&pagenum=1">首页</a>&nbsp;&nbsp;
  				<a class="inline_b btns_2" href="${pageContext.request.contextPath}/${page.url}&pagenum=${page.pagenum-1==0?1:page.pagenum-1}">上一页</a>&nbsp;&nbsp;
  				<c:forEach begin="${page.startPage}" end="${page.endPage}" var="num">
  					<a class="inline_b btns_2" style="width:30px;"href="${pageContext.request.contextPath}/${page.url}&pagenum=${num}">${num }</a>
  				</c:forEach>
  				&nbsp;&nbsp;
  				<a class="inline_b btns_2" href="${pageContext.request.contextPath}/${page.url}&pagenum=${page.pagenum+1>page.totalpage?page.totalpage:page.pagenum+1}">下一页</a>&nbsp;&nbsp;
  				<a class="inline_b btns_2" href="${pageContext.request.contextPath}/${page.url}&pagenum=${page.totalpage}">尾页</a>
  				<a class="inline_b btns_2" style="height:32px;width:60px;float: right;" href="javascript:jump()">跳转</a>
  				<select id="s1" class="inline_b btns_2" style="height:35px;width:60px;float: right;">
  					<c:forEach begin="1" end="${page.totalpage}" var="num">
  						<option value="${num}" ${page.pagenum==num?'selected="selected"':''} style="font-szie:14px;color:#434343;">第${num}页</option>
  					</c:forEach>
  				</select>				
  				<script type="text/javascript">
  					function jump(){
						var num = document.getElementById("s1").value;
						window.location.href="${pageContext.request.contextPath}/${page.url}&pagenum="+num;
  					}
  				</script>