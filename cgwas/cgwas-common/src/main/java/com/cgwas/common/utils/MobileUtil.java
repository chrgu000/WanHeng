package com.cgwas.common.utils;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.cloopen.rest.sdk.CCPRestSDK;

public class MobileUtil {
	/**
	 * 发送手机验证码
	 * 
	 * @param tel
	 * @return
	 */
	public String getMobileVerCode(String tel) {
		Logger logger = Logger.getLogger("");
		// 根据手机号发送验证码
		int varCode = ((int) ((Math.random() * 9 + 1) * 100000));
		logger.info("验证码:" + varCode);
		return "123456";
	}

	/**
	 * 发送邮箱验证码
	 * 
	 * @return
	 */
	public String getEmailVerCode() {
		Logger logger = Logger.getLogger("");
		// 根据手机号发送验证码
		int varCode = ((int) ((Math.random() * 9 + 1) * 10000000));
		logger.info("验证码:" + varCode);
		return varCode + "";
	}

	/**
	 * 发送手机验证码
	 * 
	 * @param tel
	 *            电话
	 * @param templateSMS
	 *            模版号
	 * @param codeName
	 *            验证码类型
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	public boolean getTelCode(final String tel, String templateSMS,
			String codeName, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		Logger logger = Logger.getLogger("");
		HttpSession session = request.getSession();
		// 获取参数
		Properties props = new Properties();
		Resource resource = new ClassPathResource("email.properties");
		String openSMSFlag = "false"; // 是否开启
		InputStream in;
		try {
			in = resource.getInputStream();
			props.load(in);
			in.close();
			openSMSFlag = (String) props.get("openSMSFlag");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("false".equals(openSMSFlag)) { // 判断是否开启短信验证
			logger.info("模拟短信：123456");
			session.setAttribute(codeName + "Code", "123456");
			session.setAttribute(codeName + "Tel", tel); // 审请手机
			return true;
		}

		// Integer n = (Integer) session.getAttribute("number");
		// if (number != null && number.equals(n)) {
		Integer num = ((int) ((Math.random() * 9 + 1) * 100000));
		session.setAttribute(codeName+ "Code", Integer.toString(num)); // 手机编号
		session.setAttribute(codeName + "Tel", tel); // 审请手机
		logger.info("真实短信："+num);
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("sandboxapp.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount("aaf98f894d7439d8014d9370459d1636",
				"c30590757309461e868aee414872fc65");// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId("8a216da85cf298b3015cfba8fa050400");// 初始化应用ID
		result = restAPI.sendTemplateSMS(tel, templateSMS, new String[] { num
				+ "" }); // "167498"
		if ("000000".equals(result.get("statusCode"))) {
			return true;
		} else {
			return false;
		}

	}
}
