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
	restAPI.init("sandboxapp.cloopen.com", "8883");// ��ʼ����������ַ�Ͷ˿ڣ���ʽ���£���������ַ����Ҫдhttps://
	restAPI.setAccount("aaf98f894d7439d8014d9370459d1636", "c30590757309461e868aee414872fc65");// ��ʼ�����ʺź����ʺ�TOKEN
	restAPI.setAppId("8aaf0708559f32dd0155a4d65a9904fe");// ��ʼ��Ӧ��ID
	result = restAPI.sendTemplateSMS("18768143568", "97694", new String[] {
			"123456" });

	System.out.println("SDKTestSendTemplateSMS result=" + result);
	if ("000000".equals(result.get("statusCode"))) {
		
		// �����������data������Ϣ��map��
		HashMap<String, Object> data = (HashMap<String, Object>) result
				.get("data");
		Set<String> keySet = data.keySet();
		for (String key : keySet) {
			Object object = data.get(key);
			System.out.println(key + " = " + object);
		}
	} else {
		// �쳣�������������ʹ�����Ϣ
		System.out.println("������=" + result.get("statusCode") + " ������Ϣ= "
				+ result.get("statusMsg"));
	}
}
}
