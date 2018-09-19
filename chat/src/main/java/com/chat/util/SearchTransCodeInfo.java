package com.chat.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SearchTransCodeInfo extends BaseTransCode {

	public static void main(String[] args) throws IOException {
		System.out.println(doExeUrl("0f52b7e55fc14f8c8cac9103976ebe4a"));
		String s1="cgwas%2Fcloud%2Fvideos%2Fwmv.mp4";
		String s2="cgwas/cloud/videos/wmv111.mp4";
		
		System.out.println(s1.equals(s2));
    }
    public static String doExeUrl(String jobIds){
    	 //生成私有参数，不同API需要修改
        Map<String, String> privateParams = generatePrivateParamters(jobIds);
        //生成公共参数，不需要修改
        Map<String, String> publicParams = generatePublicParamters();
        //生成OpenAPI地址，不需要修改
        String URL = generateOpenAPIURL(publicParams, privateParams);
        System.out.println(URL);
        String result=HttpUtil.doGet(URL);
    	return result;
    }
    /**
     * 生成视频点播OpenAPI私有参数
     * 不同API需要修改此方法中的参数
     *
     * @return
     */
    private static Map<String, String> generatePrivateParamters(String jobIds) {
        // 接口私有参数列表, 不同API请替换相应参数
        Map<String, String> privateParams = new HashMap<String,String>();
        privateParams.put("Action", "QueryJobList");
        privateParams.put("JobIds", jobIds);
        return privateParams;
    }

}
