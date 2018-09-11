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
public enum TempletFileCodeEnum {

						   EARLIER(1, "earlier.xlsx"),
						   LATER(2, "later.xlsx"),
						   PROJECT(3, "subproject.xls"),
						   STORYBOARD(4, "分镜.xlsx"),
						   SUBPROJECT_FIELD(5, "subproject_field.xls"),
						   FIELD_LATER(6, "field_later.xlsx"),
                           ;

    /** 编码 */
    private Integer code;

    /** 说明 */
    private String desc;

    TempletFileCodeEnum(Integer code, String desc){
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
    public static TempletFileCodeEnum getEnumByCode(Integer code) {
        if (code==null) {
            return null;
        }
        for (TempletFileCodeEnum object : TempletFileCodeEnum.values()) {
            if (object.getCode().equals(code)) {
                return object;
            }
        }

        return null;
    }
}
