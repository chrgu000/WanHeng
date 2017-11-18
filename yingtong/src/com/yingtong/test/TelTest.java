package com.yingtong.test;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSDK;

public class TelTest {
	public static Integer getRandomNum(){
		return null;
	}
public static void main(String[] args) {
	HashMap<String, Object> result = null;
	CCPRestSDK restAPI = new CCPRestSDK();
	restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
	restAPI.setAccount("aaf98f894d7439d8014d9370459d1636", "c30590757309461e868aee414872fc65");// 初始化主帐号和主帐号TOKEN
	restAPI.setAppId("8aaf0708559f32dd0155a4d65a9904fe");// 初始化应用ID
	result = restAPI.sendTemplateSMS("18768143568", "97694", new String[] {
			"123456" });

	System.out.println("SDKTestSendTemplateSMS result=" + result);
	if ("000000".equals(result.get("statusCode"))) {
		
		// 正常返回输出data包体信息（map）
		HashMap<String, Object> data = (HashMap<String, Object>) result
				.get("data");
		Set<String> keySet = data.keySet();
		for (String key : keySet) {
			Object object = data.get(key);
			System.out.println(key + " = " + object);
		}
	} else {
		// 异常返回输出错误码和错误信息
		System.out.println("错误码=" + result.get("statusCode") + " 错误信息= "
				+ result.get("statusMsg"));
	}
}
}
