package com.yingtong.test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
@Scope("prototype")

public class TestController {
	@RequestMapping("/test")
 public String testAction(){
			return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="
					+"wx9e75c97e5aaec9af"
					+ "&redirect_uri=http%3a%2f%2f"
					+"www.yt-car.cn"
					+ "%2forder%2fshowOrder.do&response_type=code&scope=snsapi_userinfo"+ "#wechat_redirect";
 }
}
