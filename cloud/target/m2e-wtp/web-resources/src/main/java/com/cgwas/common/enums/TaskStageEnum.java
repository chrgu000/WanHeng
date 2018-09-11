/*
 * Copyright 2016 Alibaba.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Alibaba.com.
 */
package com.cgwas.common.enums;
/**
 * 类TaskStageEnum.java的实现描述：TaskStageEnum 任务阶段枚举类
 * 
 * @author yubangqiong
 */
public enum TaskStageEnum {

   NOT_ISSUE("NOT_ISSUE", "未发布"),

   ONGOING("ONGOING", "进行中"),

   AUDITING("AUDITING", "审核中"),
   
   SOURCE_FILE("SOURCE_FILE", "源文件"),
   
   CHECKING("CHECKING", "验收中"),
   
   GIVING_UP("GIVING_UP", "放弃中"),
   
   UNPAID("UNPAID", "待付款"),
   
   FINISHED("FINISHED", "已完成"),
                           ;
    /** 编码 */
    private String code;

    /** 说明 */
    private String desc;

    TaskStageEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
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
