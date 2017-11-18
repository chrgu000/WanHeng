/**
 * 复选框
 *
 * `Html`
 <label class="u-checkbox">
 <input name="checkbox" type="checkbox">
 </label>
 * `/Html`
 *
 * @param settings 用户设置参数
 */
;(function ($) {
    'use strict';
    $.fn.jCheckbox = function (settings) {
        /* 默认参数 */
        var _defaults = {
            checkedClass: "z-checked", // 选中状态类名
            onChange: function (element) {} // onchange回调，返回当前选中项DOM元素组
        };

        var options = $.extend(_defaults, settings || {});
        var checkboxes = this;

        checkboxes.each(function () {
            var $checkbox = $(this);

            /*---- 初始化 ----*/
            // 是否选中以input:checkbox的选中状态为准
            if($checkbox.find('input[type="checkbox"]').is(':checked')) {
                $checkbox.addClass(options.checkedClass);
            } else {
                $checkbox.removeClass(options.checkedClass);
            }

            /*---- 添加事件 ----*/
            $checkbox.on("change", function () {
                $(this).toggleClass(options.checkedClass);
                options.onChange($(this));
            });
        });
    };
})(jQuery);


//
// //判断手机系统
// var u = navigator.userAgent;
// if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
//     $(".back").show();
// } else if (u.indexOf('iPhone') > -1) {//苹果手机
//     $(".back").hide();
// }else if (u.indexOf('Windows Phone') > -1) {//winphone手机
//     alert("winphone手机");
// }
//返回按钮
function goBack(){
    window.history.back();
}


//select下拉框
$('[name="nice-select"]').click(function(e){
    $('[name="nice-select"]').find('ul').hide();
    $(this).find('ul').show();
    $(this).toggleClass("nice-select-red");
    $(this).siblings(".nice-select").removeClass("nice-select-red");
    e.stopPropagation();
});
$('[name="nice-select"] li').click(function(e){
    var val = $(this).text();
    $(this).parents('[name="nice-select"]').find('input').val(val);
    $('[name="nice-select"] ul').hide();
    $(this).parents('[name="nice-select"]').removeClass("nice-select-red");
    e.stopPropagation();
});
$(document).click(function(){
    $('[name="nice-select"] ul').hide();

});


