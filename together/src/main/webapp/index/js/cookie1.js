function getCookie() {
	$.ajax( {
		url : "../user/checkLogin.do",
		type : "post",
		data : {},
		dataType : "json",
		success : function(data) {
			if (data == 0) {
				$.pop.tips("您的会话已经过期,请重新登录");
				setTimeout(function() {
					location.href = "login.html"
				}, 1000);
			}
		},
		error : function() {
		}
	});
}
$(function() {
	getCookie();
});