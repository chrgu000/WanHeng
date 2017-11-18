// JavaScript Document
$(document).ready(function(){
	//开具发票
	$('.dd3 a').click(function(){
		if($(this).find('.cur').length==1){
			$(this).find('i').addClass('cur');
			$('#orderb').addClass('c');
		}else{
			$(this).find('i').removeClass('cur');
			$('#orderb').removeClass('c'); 
		}
	});
	//关闭登陆弹窗
	$('.off').click(function(){
		$('.denglu').fadeOut();
	});
	
	$('.dd3 a').click(function(){
		if($(this).find('.cur').length==1){
			$(this).find('i').removeClass('cur');
		}else{
			$(this).find('i').addClass('cur');
		}
	});
	
	//支付选择
	$('.zf4 a').click(function(){
		$(this).find('dt').addClass('cur');
		$(this).siblings().find('dt').removeClass('cur');
	});
	//支付弹窗
	$('.zf-tan dt a').click(function(){
		$('.zf-tan').fadeOut();
	});
	//联系信息弹窗
	$('.lx-tan dt a').click(function(){
		$('.lx-tan').fadeOut();
	});
	//地址弹窗
	$('.banner-songche dt a').click(function(){
		$('.banner-songche').fadeOut();
	});
	
	//时间选择
	$('.times').focus(function(){
		$(this).siblings('.time').css('display','block');
	});
	$('.times').blur(function(){
		setTimeout(function(){
			$('.time').css('display','none');
		},300);
	});
	
	$('.time a').click(function(){
		$(this).addClass('cur').siblings().removeClass('cur');
		$(this).parent().siblings('input').val($(this).html());
	});
	
	//长租选择
	$('.cz-tm dd input').focus(function(){
		$(this).parent().parent().siblings().css('display','block');
	});
	$('.cz-input1 dd input').blur(function(){
		setTimeout(function(){
			$('.cz-input1 .xiala').css('display','none');
		},200);
	});
	$('.cz-input3 dd input').blur(function(){
		setTimeout(function(){
			$('.cz-input3 .xiala').css('display','none');
		},200);
	});
	$('.cz-input5 dd input').blur(function(){
		setTimeout(function(){
			$('.cz-input5 .xiala').css('display','none');
		},200);
	});
	
	$('.xiala a').click(function(){
		$(this).parent().siblings('dl').find('input').val($(this).html());
	});
	$('.zj-dl3 dd input, .zj-dl4 dd input').focus(function(){
		$(this).siblings('.xiala2').css('display','block');
	});
	
	$('.zj-dl3 dd input, .zj-dl4 dd input').blur(function(){
		setTimeout(function(){
			$('.xiala2').css('display','none');
		},200);
	});
	$('.xiala2 a').click(function(){
		$(this).parent().siblings('input').val($(this).html());
	});
	
	//地图
	$('.map-list a').click(function(){
		$(".map-input input[type='text']").val($(this).find('span').html());
	});

});











