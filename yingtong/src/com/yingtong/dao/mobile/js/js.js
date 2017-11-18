// JavaScript Document
//中框居上的高度

document.getElementById("all").style.fontSize = document.getElementById("all").offsetWidth/7.5+ "%" ;
//控制缩放比例
window.onresize = function(){
	document.getElementById("all").style.fontSize = document.getElementById("all").offsetWidth/7.5+ "%" ;
};



window.onscroll = function() {
    var scrollTop = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
    console.log(scrollTop);
	var top=$('.index-top').height();
	var faxian=$('.faxian').height();
	if(scrollTop>top){
		$('.index1').addClass('fixed');
	}else{
		$('.index1').removeClass('fixed');
	}
	
	if(scrollTop>faxian){
		$('.faxian1').addClass('fixed');
		$('.faxian2').addClass('fixed');
	}else{
		$('.faxian1').removeClass('fixed');
		$('.faxian2').removeClass('fixed');
	}
}



$(document).ready(function() {
	//时间选择
	$(function(){
		//赛选
		$('.sx-btn').click(function(){
			if($(this).find('.cur').length>0){
				$(this).find('i').removeClass('cur');
				$('.index2 dl').animate({top:'0'});
			}else{
				$(this).find('i').addClass('cur');
				$('.index2 dl').animate({top:'-0.88em'});
			}
		});
		//展开
		$('.zhankai').click(function(){
			if($(this).find('.cur').length>0){
				$(this).find('i').removeClass('cur');
				$('.faxian2').css('height','0.8em');
			}else{
				$(this).find('i').addClass('cur');
				$('.faxian2').css('height','auto');
			}
		});
		//被人邀请他弹窗
		$('.zhe').click(function(){
			$(this).parent().fadeOut();
		});
		//评价
		$('.pj-btn a').click(function(){
			$('.pj-btn li').removeClass('cur');
			for(var p=0;p<$(this).parent().index()+1;p++){
				$('.pj-btn li').eq(p).addClass('cur');
			}
		});
		//聚景
		$('.jj-xiao a').click(function(){
			$(this).addClass('cur').parent().siblings().find('a').removeClass('cur');
			$('.jj-show li').eq($(this).parent().index()).fadeIn().siblings().fadeOut();
		});
		//聚景切换地址
		$('.add-btn').click(function(){
			$('.add-jjall').slideDown(300);
		});
		$('.add-qx,.add-qr').click(function(){
			$('.add-jjall').slideUp(300);
		});
		$('.add-jj li').click(function(){
			$(this).addClass('cur').siblings().removeClass('cur');
		});
		
		//图片轮播显示
		$('.cunyou3 dt').click(function(){
			$('#img-all').css('z-index','99').css('opacity','1');
		});
		$('#img-all').click(function(){
			$(this).css('z-index','0').css('opacity','0');
		});

	});
});




