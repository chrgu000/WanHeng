package com.fengyun.video.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fengyun.util.HttpUtil;
/**
 * 删除APP录制配置
 * @author 杨俊
 *
 */
public class DeleteVideoRecord extends BaseVideoRecord{
	public static void main(String[] args) throws IOException {
        System.out.println(doExeUrl("15_111"));
    }
    public static String doExeUrl(String appName){
    	 //生成私有参数，不同API需要修改
        Map<String, String> privateParams = generatePrivateParamters(appName);
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
    private static Map<String, String> generatePrivateParamters(String appName) {
        // 接口私有参数列表, 不同API请替换相应参数
        Map<String, String> privateParams = new HashMap<String,String>();
        privateParams.put("Action", "DeleteLiveAppRecordConfig");
        privateParams.put("AppName", appName);
        privateParams.put("DomainName", "zhibo.woheyun.com");
        return privateParams;
    }
   
}
