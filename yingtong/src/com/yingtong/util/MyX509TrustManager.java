package com.yingtong.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 信任管理�?
 * 
 * @author 李欣�?
 * @date 2014-11-21下午9:15:08
 */
public class MyX509TrustManager implements X509TrustManager {

	// �?��客户端证�?
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

	// �?��服务器端证书
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

	// 返回受信任的X509证书数组
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
