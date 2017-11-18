<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.1&services=true"></script>  
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=9b4501e23954c2c716736fbf9547d481"></script>
    <title>聚乡村</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body>
  <div style="width:697px;height:550px;border:#ccc solid 1px;display:none" id="dituContent" ></div>
  <%
  String state=request.getParameter("state");
  %>  
<script type="text/javascript">
try {
		if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
var map = new BMap.Map("dituContent");  
var point = new BMap.Point(116.331398,39.897445);  
map.centerAndZoom(point,12); 
map.enableScrollWheelZoom();  
var geolocation = new BMap.Geolocation();  
geolocation.getCurrentPosition(function(r){  
if(this.getStatus() == BMAP_STATUS_SUCCESS){  
var mk = new BMap.Marker(r.point);  
map.addOverlay(mk);
map.panTo(r.point);
window.location.href="merchant/intoStart.do?latitude="+r.point.lat+"&longitude="+r.point.lng+"&state="+<%=state%>;
}   else {
	window.location.href="merchant/intoStart.do?latitude=120.214585&longitude=30.21317&state="+<%=state%>;	
		}
		})
		}else{
			window.location.href="merchant/intoStart.do?latitude=120.214585&longitude=30.21317state="+<%=state%>;	
		}
		} catch (e) {
	}
</script>
 
  </body>
</html>
