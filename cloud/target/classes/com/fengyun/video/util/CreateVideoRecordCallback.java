package com.fengyun.video.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fengyun.util.HttpUtil;

/**
 * 添加录制回调配置
 * @author 杨俊
 *
 */
public class CreateVideoRecordCallback extends BaseVideoRecord{
	 public static void main(String[] args) throws IOException {
	        System.out.println(doExeUrl());
	    }
	    public static String doExeUrl(){
	    	 //生成私有参数，不同API需要修改
	        Map<String, String> privateParams = generatePrivateParamters();
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
	    private static Map<String, String> generatePrivateParamters() {
	        // 接口私有参数列表, 不同API请替换相应参数
	        Map<String, String> privateParams = new HashMap<String,String>();
	        privateParams.put("Action", "AddLiveRecordNotifyConfig");
	        privateParams.put("NotifyUrl", "https://yunketang.woheyun.com/cloud/com/courseAction/recordCallback.action");
	        privateParams.put("DomainName", "zhibo.woheyun.com");
	        return privateParams;
	    }
}
