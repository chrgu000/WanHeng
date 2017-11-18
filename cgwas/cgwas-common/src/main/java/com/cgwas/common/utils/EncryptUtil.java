package com.cgwas.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

/**
 * 加密工具类
 * 
 * @author Lingwh
 * 
 */
public class EncryptUtil {
	/**
	 * 加密方法
	 * 
	 * @param msg
	 * @return
	 */
	public String getEncryptMsg(String msg) {
		if(msg==null){// 空置换
			msg="";
		}
		Logger log = Logger.getLogger("");

		String md5 = this.getMD5(msg);
		if (md5 == null) {
			log.info("MD5转换出错");
			return null;
		}
		String sha = this.getSha1(md5);

		if (sha == null) {
			log.info("SHA转换出错");
			return null;
		}
		return sha;

	}

	/**
	 * SHA加密
	 * 
	 * @param str
	 * @return
	 */
	private String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'w', 'a', 'n', 'h', 'e', 'g' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 对字符串md5加密
	 * 
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private String getMD5(String str) {

		// 生成一个MD5加密计算摘要
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			return new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

	}

}
