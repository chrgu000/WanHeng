package com.fengyun.video.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.fengyun.util.HttpUtil;

import sun.misc.BASE64Encoder;

/**
 *  添加APP录制配置
 */
public class CreateVideoRecord extends BaseVideoRecord{
    public static void main(String[] args) throws IOException {
        System.out.println(doExeUrl("12_26", "26"));
    }
    public static String doExeUrl(String appName,String streamName){
    	 //生成私有参数，不同API需要修改
        Map<String, String> privateParams = generatePrivateParamters(appName,streamName);
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
    private static Map<String, String> generatePrivateParamters(String appName,String streamName) {
        // 接口私有参数列表, 不同API请替换相应参数
        Map<String, String> privateParams = new HashMap<String,String>();
        privateParams.put("Action", "AddLiveAppRecordConfig");
        privateParams.put("AppName", appName);
        privateParams.put("DomainName", "zhibo.woheyun.com");
        privateParams.put("OssBucket", "yuncloudvideo");
        privateParams.put("OssEndpoint", "oss-cn-shanghai.aliyuncs.com");
        privateParams.put("RecordFormat.1.CycleDuration", "21600");
        privateParams.put("RecordFormat.1.Format", "mp4");
        privateParams.put("RecordFormat.1.OssObjectPrefix", "record/"+appName+"/"+streamName+"/{Sequence}{EscapedStartTime}{EscapedEndTime}");
        privateParams.put("RecordFormat.1.SliceOssObjectPrefix", "record/"+appName+"/"+streamName+"/{UnixTimestamp}{Sequence}");
        return privateParams;
    }
     

   
}