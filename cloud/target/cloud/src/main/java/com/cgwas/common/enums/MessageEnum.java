package com.cgwas.common.enums;

/**
 * 类MessageEnum.java的实现描述：页面返回信息
 * 
 * @author yubangqiong 
 */
public enum MessageEnum {
    /*----------------系统级别----------------------*/
    /**
     * 系统异常
     */
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常"),
    /**
    * 参数异常
    */
    PARAM_ERROR("PARAM_ERROR", "参数异常"),
    
    /**
     * 任务支付失败
     */
    TASK_PAY_ERROR_01("TASK_PAY_ERROR_01", "付款失败，包含其它阶段的任务"),
    ;
      /**
       * 
       */
      private String code;
      /**
       * 
       */
      private String desc;

      private MessageEnum(String code, String desc){
          this.code = code;
          this.desc = desc;
      }

      /**
       * @return the code
       */
      public String getCode() {
          return code;
      }

      /**
       * @param code the code to set
       */
      public void setCode(String code) {
          this.code = code;
      }

      /**
       * @return the desc
       */
      public String getDesc() {
          return desc;
      }

      /**
       * @param desc the desc to set
       */
      public void setDesc(String desc) {
          this.desc = desc;
      }


}
