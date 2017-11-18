package com.cgwas.common.utils.bank;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.cgwas.common.utils.HttpUtils;

public class VerifyBankCard1 {

	public static void main(String[] args) {
		checkBank();
	}

	public static void checkBank() {
		String host = "https://ali-bankcard4.showapi.com";
		String path = "/bank4";
		String method = "GET";
		String appcode = "9c2f91344dd743749c14ea2ba9d43ecf";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
		// 83359fd73fe94948385f570e3c139105
		// acct_name STRING 必选 持卡人姓名
		// acct_pan STRING 必选 银行卡帐号
		// cert_id STRING 必选 开卡使用的证件号码
		// cert_type STRING 必选 开卡使用的证件类型；01:身份证，目前只支持身份证
		// needBelongArea STRING 可选 是否需要返回银行卡归属地信息
		// phone_num STRING 必选 绑定手机号
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("acct_name", "凌伟豪");
		querys.put("acct_pan", "6212261204000520897");
		querys.put("cert_id", "330482199506211811");
		querys.put("cert_type", "01");
		querys.put("needBelongArea", "true");
		querys.put("phone_num", "15658078363");

		try {
			/**
			 * 重要提示如下: HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign
			 * -java/blob/master/src
			 * /main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java 下载
			 * 
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob
			 * /master/pom.xml
			 */
			HttpResponse response = HttpUtils.doGet(host, path, method,
					headers, querys);
			System.out.println(response.toString());
			// 获取response的body
			// System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
