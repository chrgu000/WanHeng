package com.chat.util;

import java.util.HashMap;
import java.util.Map;

public class GenerateFile {
	public static Map<String, String> generate(String str) {
		Map<String, String> file = new HashMap<String, String>();
		String substring = str.substring(str.indexOf(">") + 1,
				str.lastIndexOf("<"));
		String[] arr = substring.split("&");
		for (String s : arr) {
			String[] array = s.split("=");
			file.put(array[0], array[1]);
		}
		return file;
	}
	public static void main(String[] args) {
		String data="<file>filename=yangjun.zip&filetype=zip&filesize=112&filepath=cgwas/cloud/imagges</file>";
		Map<String,String> cache=generate(data);
		System.out.println(cache);

	}
}
