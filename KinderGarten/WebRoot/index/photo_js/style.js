
//返回按钮
function goBack(){
    window.history.back();
}
var body_h=$(window).height();
var top_h=$(".nav_top").outerHeight(true);
var foot_h=$("#foot").outerHeight(true);
var h=body_h-top_h-foot_h;
$(".border").height(h-35);