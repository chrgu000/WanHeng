$(".screening li").click(function () {
    $(this).css("color","#f99d27");
    $(this).siblings("li").css("color","#333");
   $(this).find("span").toggleClass("active");
   $(this).siblings("li").find("span").removeClass("active")
});
$(".Sort-Sort li").click(function () {
    $(this).css("color","#f99d27");
    $(this).siblings("li").css("color","#333")
});
//Regional开始
$(document).ready(function(){
    $(".Regional").click(function(){
        if ($('.grade-eject').hasClass('grade-w-roll')) {
            $('.grade-eject').removeClass('grade-w-roll');
        } else {
            $('.grade-eject').addClass('grade-w-roll');
        }
    });
});
$(document).ready(function(){
    $(".grade-w>li").click(function(){
        $(".grade-t").css("left","33.48%");
        $(this).css("color","#f99d27");
        $(this).siblings("li").css("color","#333")
    });
    $(".grade-t>li").click(function(){
        $(".grade-s").css("left","66.96%");
        $(this).css("color","#f99d27");
        $(this).siblings("li").css("color","#333")
    });
});
//More开始
$(document).ready(function(){
    $(".More").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')) {
            $('.Category-eject').removeClass('grade-w-roll');
        } else {
            $('.Category-eject').addClass('grade-w-roll');
        }
    });
});

$(document).ready(function(){
    $(".Category-w>li").click(function(){
        $(".Category-t").css("left","33.48%");
        $(this).css("color","#f99d27");
        $(this).siblings("li").css("color","#333")
    });
});

$(document).ready(function(){
    $(".Category-t>li").click(function(){
        $(".Category-s").css("left","66.96%");
        $(this).css("color","#f99d27");
        $(this).siblings("li").css("color","#333")
    });
});

//Sort开始

$(document).ready(function(){
    $(".Sort").click(function(){
        if ($('.Sort-eject').hasClass('grade-w-roll')) {
            $('.Sort-eject').removeClass('grade-w-roll');
        } else {
            $('.Sort-eject').addClass('grade-w-roll');
        }
    });
});
//Price开始
$(document).ready(function(){
    $(".Price").click(function(){
        if ($('.Price-eject').hasClass('grade-w-roll')) {
            $('.Price-eject').removeClass('grade-w-roll');
        } else {
            $('.Price-eject').addClass('grade-w-roll');
        }
    });
});


//判断页面是否有弹出
$(document).ready(function(){
    $(".Regional").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')){
            $('.Category-eject').removeClass('grade-w-roll');
        }else if($('.Sort-eject').hasClass('grade-w-roll')){
            $('.Sort-eject').removeClass('grade-w-roll');
        }else if($('.Price-eject').hasClass('grade-w-roll')){
            $('.Price-eject').removeClass('grade-w-roll');
        }
    });
});
$(document).ready(function(){
    $(".More").click(function(){
        if ($('.Sort-eject').hasClass('grade-w-roll')){
            $('.Sort-eject').removeClass('grade-w-roll');
        }else if($('.grade-eject').hasClass('grade-w-roll')){
            $('.grade-eject').removeClass('grade-w-roll');
        }else if($('.Price-eject').hasClass('grade-w-roll')){
            $('.Price-eject').removeClass('grade-w-roll');
        }
    });
});

$(document).ready(function(){
    $(".Sort").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')){
            $('.Category-eject').removeClass('grade-w-roll');
        }else if($('.grade-eject').hasClass('grade-w-roll')){
            $('.grade-eject').removeClass('grade-w-roll');
        }else if($('.Price-eject').hasClass('grade-w-roll')){
            $('.Price-eject').removeClass('grade-w-roll');
        }
    });
});

$(document).ready(function(){
    $(".Price").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')){
            $('.Category-eject').removeClass('grade-w-roll');
        }else if($('.grade-eject').hasClass('grade-w-roll')){
            $('.grade-eject').removeClass('grade-w-roll');
        }else if($('.Sort-eject').hasClass('grade-w-roll')){
            $('.Sort-eject').removeClass('grade-w-roll');
        }
    });
});

