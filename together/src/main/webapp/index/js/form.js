/**
 * Created by Administrator on 2017/3/31.
 */

//默认第一个显示focus
$(document).ready(function() {
    $("#sn1").focus();
    //自动跳到下一个输入框
    $("input[name^='sn']").each(function() {
        $(this).keyup(function(e) {
            e = window.event || e;
            var k = e.keyCode || e.which;
            if (k == 8) {   //8是空格键
                if ($(this).val().length < 1) {
                    $(this).prev().focus();
                    $(this).prev().focus(function() {
                        var obj = e.srcElement ? e.srcElement: e.target;
                        if (obj.createTextRange) { //IE浏览器
                            var range = obj.createTextRange();
                            range.moveStart("character", 3);
                            range.collapse(true);
                            range.select();
                        }
                    });
                }
            } else {
                if ($(this).val().length > 0) {
                    $(this).next().focus();
                }
            }
        })
    });

    $("input[type='text'][id^='sn']").bind('keyup',
        function() {
            var len = $("#sn1").val().length + $("#sn2").val().length + $("#sn3").val().length ;
            if (len == 16) device_verify();
        });
});
//
$(".check_box li").click(function () {
    $(this).toggleClass("active");
});
$(".radio_box li").click(function () {
    $(this).toggleClass("active");
    $(this).siblings("li").removeClass("active")
});


//select下拉框
$('[name="nice-select"]').click(function(e){
    $('[name="nice-select"]').find('.black').hide();
    $(this).find('.black').fadeIn();
    $(this).find('.select_center').animate({bottom:'0'},"500");
    $(this).toggleClass("nice-select-red");
    $(this).siblings(".nice-select").removeClass("nice-select-red");
    e.stopPropagation();
});

$(document).click(function(){
    $('[name="nice-select"] .black').hide();

});
$('[name="nice-select"] a').click(function (e) {
    $('[name="nice-select"] .black').hide();
    $('[name="nice-select"] .select_center').animate({bottom:'-100%'},"fast");
    $(this).parents('[name="nice-select"]').removeClass("nice-select-red");
    e.stopPropagation();
});