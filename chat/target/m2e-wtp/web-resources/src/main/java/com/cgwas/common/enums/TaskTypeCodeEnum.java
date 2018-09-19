/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.cgwas.common.enums;
/**
 * 类TaskTypeCodeEnum.java的实现描述：taskType枚举类
 * 
 * @author yubangqiong
 */
public enum TaskTypeCodeEnum {

						   painting(1, "原画环节"),

						   model(2, "模型环节"),

						   materialQuality(3, "材质环节"),

						   bundling(4, "绑定环节"),

						   storyBoard(5, "分镜环节"),

						   layout(6, "Layout环节"),
						   
						   dynamicCompensation(7, "动捕环节"),

						   blocking(8, "Blocking环节"),

						   act(9, "动画环节"),
						   
						   lighting(10, "灯光环节"),
						   
						   render(11, "渲染环节"),
						   
						   specialEffects(12, "特效环节"),
						   
						   compose(13, "合成环节"),
						   
						   cut(14, "剪辑环节"),
						   
						   drama(15, "剧本环节"),
						   
						   dub(16, "配音环节"),
						   
						   dStoryBoard(17, "分镜环节"),//短片分镜
                           ;

    /** 编码 */
    private Integer code;

    /** 说明 */
    private String desc;

    TaskTypeCodeEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据编码获取枚举对象
     * 
     * @param code 编码
     * @return 枚举对象
     */
    public static TaskTypeCodeEnum getEnumByCode(Integer code) {
        if (code==null) {
            return null;
        }
        for (TaskTypeCodeEnum object : TaskTypeCodeEnum.values()) {
            if (object.getCode().equals(code)) {
                return object;
            }
        }

        return null;
    }
    
    /**
     * 获取枚举对象
     * 
     * @param desc 描述
     * @return 枚举对象
     */
    public static TaskTypeCodeEnum getEnums(String enums) {
        if (enums==null) {
            return null;
        }
        for (TaskTypeCodeEnum object : TaskTypeCodeEnum.values()) {
            if (object.name().equals(enums)) {
                return object;
            }
        }
        return null;
    }
}
