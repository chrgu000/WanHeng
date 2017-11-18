$(function() {
	$('#sc').calendar(
			{
				months : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
						'九月', '十月', '十一', '十二' ],
				weeks : [ '日', '一', '二', '三', '四', '五', '六' ]
			});
	$('.easyui-datebox').datebox({
		closeText : '关闭',
		currentText : '今天',
		formatter : myformatter,
		parser : myparser
	});
});
function myformatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + "-" + (m < 10 ? ('0' + m) : m) + "-" + (d < 10 ? ('0' + d) : d);
}
function myparser(s) {
	if (!s)
		return new Date();
	// var ss = (s.split('-'));
	var y = parseInt(s.substring(0, 4), 10);
	var m = parseInt(s.substring(5, 7), 10);
	var d = parseInt(s.substring(8, 10), 10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
		return new Date(y, m - 1, d);
	} else {
		return new Date();
	}
}