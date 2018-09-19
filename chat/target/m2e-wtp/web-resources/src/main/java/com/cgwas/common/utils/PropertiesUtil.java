package com.cgwas.common.utils;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class PropertiesUtil {
/**
 * 取配置文件的属性
 * @param path
 * @param code
 * @return
 */
	public static String load(String path, String code) {
		// 获取参数
		Properties props = new Properties();
		Resource resource = new ClassPathResource(path);
		String pz = ""; // 是否开启
		InputStream in;
		try {
			in = resource.getInputStream();
			props.load(in);
			in.close();
			pz = (String) props.get(code);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pz;

	}

}
