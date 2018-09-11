package com.cgwas.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang3.StringUtils;

public class PinyinUtil {
	// 将汉字转换为全拼
	public static String getFullSpell(String src) {

		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (java.lang.Character.toString(t1[i]).matches(
						"[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t4 += t2[0];
				} else
					t4 += java.lang.Character.toString(t1[i]);
			}
			// System.out.println(t4);
			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return t4;
	}

	// 返回中文的首字母
	public static String getHeadCharSpell(String str) {
		String temp = "";
		String demo = "";
		String convert = "";
		if (StringUtils.isEmpty(str)) {
			return demo;
		}
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		for (int i = 0; i < convert.length(); i++) {// convert目前为小写首字母,下面是将小写首字母转化为大写
			System.out.println(convert.charAt(i));
			if (convert.charAt(i) >= 'a' && convert.charAt(i) <= 'z' ||convert.charAt(i) >= 'A' && convert.charAt(i) <= 'Z') {
				temp = convert.substring(i, i + 1).toUpperCase();
				demo += temp;
			} else if (checkNum(convert.charAt(i))) {
				demo += convert.charAt(i) + "";

			}
		}
		return demo;
	}

	// 将字符串转移为ASCII码
	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			// System.out.println(Integer.toHexString(bGBK[i]&0xff));
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}

	public static void main(String[] args) {

		String cnStr = "AAA你11好sd123&&&";
		// System.out.println(getFullSpell(cnStr));
		System.out.println(getHeadCharSpell(cnStr));
	}

	/**
	 * char to num
	 * 
	 * @param word
	 * @return
	 */
	private static boolean checkNum(char word) {
		char[] checkList = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		for (char c : checkList) {

			if (word == c) {
				return true;
			}
		}

		return false;

	}
}