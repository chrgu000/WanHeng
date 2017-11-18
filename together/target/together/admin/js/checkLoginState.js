 $(document).ready(function(){
setTimeout(function(){
		$.ajax({
		url : "../admin/getLoginInfo.do",
		data : {},
		dataType : "json",
		type : "post",
		async: true,
		success : function(data) {
			if (data.hasError == true)  {
				BUI.Message.Alert(data.errInfo,function(){
					top.location.href="../admin/login.html";
				},'warning');
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			BUI.Message.Alert("系统繁忙，请稍后"+XMLHttpRequest.status+","+XMLHttpRequest.readyState+","+textStatus,function(){},'warning');
		}
	});
},500);
});