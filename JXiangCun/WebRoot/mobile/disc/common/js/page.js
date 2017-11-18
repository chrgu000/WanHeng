// JavaScript Document

var Page = 
{
		SAFE_AJAX_DELAY : 1000,
		parameters : null,
		hash:null,
		appendingList:[],
		initParameters:function()
		{
			Page.parameters = {};
			var hash = location.search.substr(1);
			var colls = hash.split('&');
			for( var i in colls )
			{
				var val   = colls[i];
				var index = val.indexOf('=');
				if ( index > -1 )
				{
					Page.parameters[ val.substr(0,index) ] = val.substr(index+1);
				}
			}
		},
		getParameter : function (key)
		{
			if ( Page.parameters ) return Page.parameters[key];
			Page.initParameters()
			return Page.parameters[key];
		},
		initHash:function()
		{
			Page.hash = {};
			var hash = location.hash.substr(1);
			
			var colls = hash.split('&');
			for( var i in colls )
			{
				var value = colls[i].split('=');
				if ( value && value.length == 2 )
				{
					Page.hash[ value[0] ] = value[1];
				}
			}
		},
		getHash : function (key)
		{
			if( Page.hash ) return Page.hash[key];
			Page.initHash();
			return Page.hash[key];
		},
		getHost:function()
		{
			var url = location.hostname;
			if ( location.port != "" ) url = url + ':' + location.port;
			return url;
		},
		
		formatParameters : function(vars)
		{
			Page.initParameters();
			
			for(var i in vars )
				Page.parameters[i] = vars[i];
			
			console.log(Page.parameters);
			
			var query = '';
			for(var j in Page.parameters )
				query += j+'='+Page.parameters[j]+'&';
			
			return query.substring(0, query.length-1);
		},
		
		isLocalhost:function(){ return location.hostname == 'localhost' },
		
		ajaxGet : function(url, data, success, error, dataType)
		{
			return Page.ajaxCall(url,data, 'GET', success, error, dataType);
		},
		ajaxPost : function(url, data, success, error, dataType)
		{
			return Page.ajaxCall(url,data, 'POST', success, error, dataType);
		},
		ajaxCall : function(url, data, type, success, error, dataType)
		{
			var ajax = $.ajax({
					type : type, 
					url  : url,  
					cache : false, 
					dataType: dataType || 'json',
					data:data,
					success:function(data)
					{
						if( Page.errorCheck(data, error, this.dataType) ) setTimeout(function(){success(data)}, Page.SAFE_AJAX_DELAY);
					},
					error:error
				});
			return  ajax;
		},
		
		errorCheck : function (data, errorCallback, dataType)
		{
			if( dataType != 'json' ) return true;
			if( data.Code == 0 && data.Error == 0 ) return true;
			if( errorCallback ) setTimeout(function(){ errorCallback(data);}, Page.SAFE_AJAX_DELAY);
			else alert('请求错误 Error Code: ' + data.Error + ', ' + data.ErrorText);
			
			return false;
		},
		
		goto:function($url, newwindow)
		{
			if(newwindow) window.open($url);
			else location.href = $url;
		},
		
		refresh:function(){ location.reload() },
		
		compare:function(a, b)
		{
			console.log(a);
			console.log(b);
			var equals = true;
			for ( var i in a)
			{
				if ( !(i in b) || b[i] != a[i] ) equals = false;
				
				for( var j in b )
				{
					if(  !(j in a) || b[j] != a[j] ) equals = false;		
				}
			}
				
			return equals;
		},
		todo:function(v)
		{
			Page.appendingList.push(v);
		},
		onLoad : function()
		{
			if ( Page.appendingList && Page.appendingList.length )
			{
				for( var i in Page.appendingList)
				{
					eval(Page.appendingList[i]);
				}
			}
		},
		onReady:function()
		{
		},
		
		toggleTouchMove : function(flag)
		{
			if( flag ) document.body.addEventListener('touchmove', Page.toumoveListener, true);
			else document.body.removeEventListener('touchmove', Page.toumoveListener, true);
		},
		
		toumoveListener:function(event)
		{
			event.preventDefault();
		},
		
		tracking:function()
		{
			var trackData  = arguments[0];
			if( arguments.length > 1 )
				var timerCallback = arguments[1];
			
			var i = 0; total = trackData.length, copy = [];
			for ( i; i < total; i ++) copy[i] = trackData[i];
			
			var id = copy.shift();
			//tracking function
			console.log('tracking: ' + id);
			
			if ( timerCallback ) setTimeout( timerCallback, 400);
		},
		
		showPop: function(id, msg)
		{
			$('#'+id).css({width:'100%', height:'100%', top:'0', left:'0', display:'block'});
			$('#'+id+'-msg').html(msg);
			Page.toggleTouchMove(true);
		},
		
		hidePop:function (id, callback, parameters)
		{
			$('#'+id).hide();
			if(callback) callback.apply(this, parameters);
			Page.toggleTouchMove(false);
		},
		
		setCookie : function(c_name,value)
		{
			var exdate=new Date();
			expiredays = 1000000;
			exdate.setDate(exdate.getDate()+expiredays)
			document.cookie=c_name+ "=" +escape(value)+((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
		},
		
		getCookie : function(c_name)
		{
			if (document.cookie.length>0)
			{
				c_start=document.cookie.indexOf(c_name + "=")
				if (c_start!=-1)
				{ 
					c_start=c_start + c_name.length+1 
					c_end=document.cookie.indexOf(";",c_start)
					if (c_end==-1) 
					{
						c_end=document.cookie.length
					}
					return unescape(document.cookie.substring(c_start,c_end))
				} 
			}
			return "";
		},
		
		loadImages:function(images, size)
		{
			$(images).each(function(index, element) {
				Page.loadImage(element, size || 160);
			});
		},
		
		loadImage: function(element, size)
		{
			var img = new Image();
			img.onload = function()
			{
				Page.scaleImage(this,size);
				
				$(element).parent().html( this );
			}
			img.src = $(element).attr('data');
		},
		
		scaleImage:function(img, max_size)
		{
			var w, h;
			if( img.width <= max_size && img.height <= max_size )
			{
				w = img.width;
				h = img.height;
			}
			
			if( img.width/img.height > 1 )
			{
				if( img.width > max_size )
				{
					w = max_size;
					h = max_size/img.width*img.height;
				}
			}
			else
			{
				if( img.height > max_size )
				{
					h = max_size;
					w = max_size/img.height*img.width;
				}
			}
			
			img.width = w;
			img.height = h;
		},
		
		pageNavi : function( to, current, total)
		{
			if ( to == 'top' ) 			current = 1;
			else if ( to == 'prev' ) 	current = Math.max(1, current-1);
			else if ( to == 'next' ) 	current = Math.min(total, current+1);
			else if ( to == 'bottom' ) 	current = total;
			else 						current = to;
			Page.goto('index.php?'+ Page.formatParameters({current:current}) );
		},
		
		top: function()
		{
			window.scrollTo(0,0);
		},
		
		listeners:{},
		addEventListener:function(type, listener, useCapture)
		{
			if(!Page.listeners[type]) Page.listeners[type] = [];
			if(!('listener' in Page.listeners[type]))
			{
				Page.listeners[type].push(listener);
			}
		},
		
		dispatchEvent:function(eventType)
		{
			var listeners = Page.listeners[eventType];
			for ( var  i in listeners)
			{
				eval(listeners[i]);
			}
		},
		
		validateMobile:function(mobile)
		{
			if( !mobile || mobile.length != 11 )
			{
				alert('请输入正确的手机号码!');
				return false;
			}
			
			var regexp = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
			if(!regexp.test(mobile))
			{
				alert('请输入有效的手机号码！');
				return false;
			}
			
			return true;
		}
};