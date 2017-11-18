// JavaScript Document
//中框居上的高度

window.onload = function(){
	document.getElementById("all").style.fontSize = document.getElementById("all").offsetWidth/7.5+ "%" ;
}
//控制缩放比例
window.onresize = function(){
	document.getElementById("all").style.fontSize = document.getElementById("all").offsetWidth/7.5+ "%" ;
};



$(document).ready(function() {
	//选择
	$('.xx-all ul li').mousedown(function(){
		$(this).find('i').addClass('cur');
		$(this).siblings().find('i').removeClass('cur');
	});
	$('.xuanche a').mousedown(function(){
		$(this).addClass('cur').siblings().removeClass('cur');
		$('.xx-all').css('display','block').children('ul').eq($(this).index()).css('display','block').siblings().css('display','none');
	});
	//开具发票
	$('.fp-tit a').click(function(){
		if($(this).find('.cur').length==1){
			$(this).find('i').removeClass('cur');
		}else{
			$(this).find('i').addClass('cur');
		}
	});
	
	//支付选择
	$('.zhifu a').click(function(){
		$(this).addClass('cur').siblings('a').removeClass('cur');
	});
	
	
	
	 
	
	//取车
	$('.qc-btn').click(function(){
		$('.add-tc').fadeIn();
	});
	$('.qc-input').focus(function(){
		$('.add-tc').fadeIn();
	});
	//还车
	$('.hc-btn').click(function(){
		$('.add-tc2').fadeIn();
	});
	$('.hc-input').focus(function(){
		$('.add-tc2').fadeIn();
	});
	//传递地址信息
	$('.add-tc .add-list a').click(function(){
		$(this).addClass('cur').siblings('a').removeClass('cur');
		$('.add-tc').fadeOut();
		$('.qc-input').val($(this).find('span').html());
	});
	$('.add-tc2 .add-list1 a').click(function(){
		$(this).addClass('cur').siblings('a').removeClass('cur');
		$('.add-tc2').fadeOut();
		$('.hc-input').val($(this).find('span').html());
	});
});




