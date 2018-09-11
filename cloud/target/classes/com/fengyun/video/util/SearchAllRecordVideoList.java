package com.fengyun.video.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fengyun.util.HttpUtil;
/**
 * 查询域名下录制配置列表
 * @author 杨俊
 *
 */
public class SearchAllRecordVideoList extends BaseVideoRecord{
	public static void main(String[] args) throws IOException {
     doExeUrl("");
       
    }
    public static void doExeUrl(String appName){
    	 //生成私有参数，不同API需要修改
        Map<String, String> privateParams = generatePrivateParamters(appName);
        //生成公共参数，不需要修改
        Map<String, String> publicParams = generatePublicParamters();
        //生成OpenAPI地址，不需要修改
        String URL = generateOpenAPIURL(publicParams, privateParams);
        System.out.println(URL);
        String result=HttpUtil.doGet(URL);
        JSONObject records=(JSONObject) JSON.parse(result);
        System.out.println("TotalPage:"+records.get("TotalPage"));
        System.out.println("TotalNum:"+records.get("TotalNum"));
        System.out.println("PageSize:"+records.get("PageSize"));
        System.out.println("PageNum:"+records.get("PageNum"));
        System.out.println("RequestId:"+records.get("RequestId"));
        JSONArray rs=JSON.parseArray(((JSONObject)JSON.parse(records.get("LiveAppRecordList").toString())).get("LiveAppRecord").toString());
        for (Object object : rs) {
    		System.out.println(object);
    	}
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
        privateParams.put("Action", "DescribeLiveRecordConfig");
        privateParams.put("DomainName", "zhibo.woheyun.com");
//        privateParams.put("AppName", appName);
//        privateParams.put("Order", "asc");
//        privateParams.put("PageNum", "1");
//        privateParams.put("PageSize", "30");
        return privateParams;
    }
}
