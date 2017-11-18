<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <div class="footer-nav">
		<c:forEach items="${titles }" var="t">
			<dl>
				<dt>
					<a href="#">${t.content }</a>
				</dt>
				<c:forEach items="${articles }" var="a">
					<c:if test="${a.title.id==t.id }">
						<dd>
							<a href="javascript:location.href='../about/index.do?id=${a.id }';">${a.titles }</a>
						</dd>
					</c:if>
				</c:forEach>
			</dl>
		</c:forEach>
	</div>
	<div class="footer-bq">2016 All Rights
		Rese&nbsp;&nbsp;杭州盈通汽车租赁有限公司&nbsp;&nbsp;版权所有</div>



	<div class="ce-nav">
		<a href="#" class="ce1"></a> <a href="#" class="ce2"></a> <a href="#"
			class="ce3"></a>
	</div>
 