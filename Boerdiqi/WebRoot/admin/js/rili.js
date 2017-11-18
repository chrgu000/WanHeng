
// JavaScript Document
$(document).ready(function(){
	var timerl="2016-07-03-7";
	var month=timerl.substr(5,2);
	var day=timerl.substr(8,2);
	var week=timerl.substr(11,1);
	var month_num=0;
	rili();
	function rili(){
		if(month==01 || month==03 || month==05 || month==07 || month==08 || month==10 || month==12){
			month_num=31;
		}else if(month==04 ||month==06 || month==09 || month==11){
			month_num=30;
		}else{
			month_num=31;
		}
		var day_list="";
		for(var i=1;i<month_num;i++){
			if(i<=day){
				if(i==1){
					if(week==7){
						day_list=day_list+"<span>"+i+"</span>";
					}else{
						day_list=day_list+"<span style='margin-left:"+41*(week)+"px'>"+i+"</span>";
					}
				}else{
					day_list=day_list+"<span>"+i+"</span>";
				}
			}else{
				day_list=day_list+"<a href='javascript:;'>"+i+"</a>";
			}
		}
		//生成
		$('.rl-list').html(day_list);
	}
	
	function rili2(){
		if(dq_month==01 || dq_month==03 || dq_month==05 || dq_month==07 || dq_month==08 || dq_month==10 || dq_month==12){
			month_num=31;
		}else if(dq_month==04 || dq_month==06 || dq_month==09 || dq_month==11){
			month_num=30;
		}else{
			month_num=31;
		}
		var day_list="";
		for(var i=1;i<month_num;i++){
			if(i<=day){
				if(i==1){
					if(week==7){
						day_list=day_list+"<span>"+i+"</span>";
					}else{
						day_list=day_list+"<span style='margin-left:"+41*(week)+"px'>"+i+"</span>";
					}
				}else{
					day_list=day_list+"<span>"+i+"</span>";
				}
			}else{
				day_list=day_list+"<a href='javascript:;'>"+i+"</a>";
			}
		}
		//生成
		$('.rl-list').html(day_list);
	}

	//切换月份
	var dq_month=timerl.substr(5,2);
	$('.rl-left').click(function(){
		if(dq_month!=month){
			dq_month--;
			rili2();
		}
	});
	$('.rl-right').click(function(){
		dq_month++;
		rili2();
	});
	//选择日期
	$('.rl-list a').click(function(){
		$(this).addClass('cur').siblings().removeClass('cur');
	});

});































