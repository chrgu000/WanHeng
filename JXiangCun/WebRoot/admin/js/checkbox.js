//全选复选框事件
function doCheck(obj) {
	var inputs = document.getElementsByTagName("input");
	for (var i = 0; i < inputs.length; i++) {
		// 筛选出所有checkbox，除了全选
		if (inputs[i].type == "checkbox" && inputs[i].id != "checkall") {
			inputs[i].checked = obj.checked;
		}
	}
}
// 复选框变化，全选框变化
function tocheckson(obj) {
	// 当此复选框未被选中，则全选框不被选中
	if (obj == false) {
		document.getElementById("checkall").checked = false;
		return;
	}
	var chkInputs = getCheckBok();// 获取所有的复选框，不包括全选
	var j = 0;
	for (var i = 0; i < chkInputs.length; i++) {
		if (chkInputs[i].checked == obj)
			j++;
		else
			break;
	}
	if (j == chkInputs.length)// 当所有复选框状态相同时,給全选框赋相同状态
		document.getElementById("checkall").checked = obj;
}
// 获取所有复选框，不包括全选
function getCheckBok() {
	var inputs = document.getElementsByTagName("input");
	var chkInputs = new Array();
	var j = 0;
	for (var i = 0; i < inputs.length; i++) {
		// 筛选出所有checkbox，除了全选
		if (inputs[i].type == "checkbox" && inputs[i].id != "checkall") {
			chkInputs[j] = inputs[i];
			j++;
		}
	}
	return chkInputs;
}