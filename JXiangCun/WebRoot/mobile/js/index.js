/**
 * Created by Administrator on 2016/5/17.
 */

/**弹簧搜索框**/
$(document).ready(function(){
    $(".sou").click(function(){
        $(".kuang").animate({width:'toggle'});
    });
});

function checkAll()
{
    var newslist = document.all['newslist'];
    if(newslist.length){
        for(var i=0;i<newslist.length;i++)
        {
            newslist[i].checked = true;
        }
    }else{
        newslist.checked = true;
    }
}
function uncheckAll()
{
    var newslist = document.all['newslist'];
    if(newslist.length){
        for(var i=0;i<newslist.length;i++)
        {
            newslist[i].checked = false;
        }
    }else{
        newslist.checked = false;
    }
}
function multipleDelete()
{
    var num = 0;
    var newslist = document.all['newslist'];
    if(newslist.length){
        for(var i=0;i<newslist.length;i++)
        {
            if(newslist[i].checked == true)
            {
                num ++;
            }
        }
    }else{
        if(newslist.checked == true){
            num ++ ;
        }
    }
    if(num == 0){
        alert('Please select delete item');
    }
    if(num >0){
        document.BuCodeSearch.action = '<%=EusUtil.getPage("lookup.generic.bucode.delete.s")%>';
        document.BuCodeSearch.submit();
    }
}














//轮播图banner
$(document).ready(function(){
    //手势右滑 回到上一个画面
    $('#myCarousel').bind('swiperight swiperightup swiperightdown',function(){
        $("#myCarousel").carousel('prev');
    });
    //手势左滑 进入下一个画面
    $('#myCarousel').bind('swipeleft swipeleftup swipeleftdown',function(){
        $("#myCarousel").carousel('next');
    })
});






$(document).ready(function(){

    /**页面切换 **/
    $('#fansone').click(function(){
        $('#centone').fadeOut("fast");
        $('#fansshu').fadeIn("fast");

    });

});


/**表情隐藏显示**/
$(document).ready(function(){
    $('.emotion') .click(function(){
        $('#enter').toggleClass("t")
    });
    $('.search') .click(function(){
        $('#enter').removeClass("t")
    });
});







//获取短信验证码
$(function  () {
    var validCode=true;
    $(".msgs").click (function  () {
        var time=30;
        var code=$(this);
        if (validCode) {
            validCode=false;
            code.addClass("msgs1");
            var t=setInterval(function  () {
                time--;
                code.html(time+"秒");
                if (time==0) {
                    clearInterval(t);
                    code.html("重新获取");
                    validCode=true;
                    code.removeClass("msgs1");

                }
            },1000)
        }
    })
});
































//$(function () {
//    $('[data-toggle="popover"]').popover()
//});
//
//$(document).ready(function () {
//    //自定义popover显示的内容
//    $('#mypopover').popover({
//
//        html : true,
//        trigger:'hover',
//
//        title: function() {
//            return $("#popover-head").html();
//        },
//        content: function() {
//            return $("#popover-content").html();
//        }
//    });
//
//});





//$(function(){
//    $(".thumbnail").on("click",function(){
//        var val = $(this).attr('class');
//        $(this).addClass('on');
//        $(".box").stop(true,true).delay(100).animate({'top':'0'},100).css({"display":"block"});
//        $(".i").stop(true,true).delay(100).animate({'top':'0' },100).css({"display":"none"});
//        document.getElementById("type").value="1";
//
//    });
//    $(".a").on("click",function(){
//        var val = $(this).attr('class');
//        $(this).addClass('on');
//        $(".box").stop(true,true).delay(100).animate({'top':'0' },100).css({"display":"none"});
//        $(".i").stop(true,true).delay(100).animate({'top':'0'},100).css({"display":"block"});
//        document.getElementById("type").value="2";
//
//    });
//});