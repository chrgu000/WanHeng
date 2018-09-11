package com.cgwas.common.utils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.MapUtils;

/**
 * Map工具类 <br />
 * 更多工具请使用 {@link MapUtils}
 * 
 * @author liuxin 2011-9-14
 * @version 1.0 RoofMapUtils.java liuxin 2011-9-14
 */
public class RoofMapUtils {

	/**
	 * 将Map中指定key的value转换字符串中的"<", ">" 为 "&lt;", "&gt;"
	 * 
	 * @param map
	 *            需要转换的Map
	 * @param key
	 *            指定key
	 * @return 转换后Map
	 */
	public static final void escape(Map<String, Object> map, String key) {
		Object o = map.get(key);
		if (o == null || !(o instanceof String)) {
			return;
		}
		String s = (String) o;
		s = RoofStringUtils.escape(s);
		map.put(key, s);
	}

	public static final void keyToCamelCase(List<Map<String, Object>> mapList) {
		keyToCamelCase(mapList, null);
	}

	public static final void keyToCamelCase(List<Map<String, Object>> mapList, String separator) {
		for (Map<String, Object> map : mapList) {
			keyToCamelCase(map, separator);
		}
	}

	public static void keyToCamelCase(Map<String, Object> map) {
		keyToCamelCase(map, null);
	}

	public static void keyToCamelCase(Map<String, Object> map, String separator) {
		for (Entry<String, Object> entry : map.entrySet()) {
			map.remove(entry.getKey());
			map.put(RoofStringUtils.toCamelCase(entry.getKey(), separator), entry.getValue());
		}
	}
	
	/**
	 * 利用反射实现Map对象和Object对象之间转化 (类型必须完全匹配)
	 * @param map
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static <T> T mapToBean(Map<String, Object> map, Class<T> obj) throws Exception {  
	    if (map == null) {  
	        return null;  
	    }  
	    Set<Entry<String, Object>> sets = map.entrySet();  
	    T t = obj.newInstance();  
	    Method[] methods = obj.getDeclaredMethods();  
	    for (Entry<String, Object> entry : sets) {  
	        String str = entry.getKey();  
	        String setMethod = "set" + str.substring(0, 1).toUpperCase() + str.substring(1);  
	        for (Method method : methods) {  
	            if (method.getName().equals(setMethod)) {  
	                method.invoke(t, entry.getValue());  
	            }  
	        }  
	    }  
	    return t;  
	}  

}
