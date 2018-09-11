package com.cgwas.common.enums;
/**
 * 
 * @author yubangqiong
 */
public enum SegmentTempleteEnum {

			   key1("1-1", 1),

			   key2("2-1", 2),

			   key3("1-2", 3),

			   key4("2-2", 4),
               ;

    /** 编码 */
    private String code;

    /** 说明 */
    private Integer value;

    SegmentTempleteEnum(String code, Integer value){
        this.code = code;
        this.value = value;
    }
    
    public String getCode() {
    	return code;
    }

    public Integer getValue() {
        return value;
    }

    /**
     * 根据编码获取枚举对象
     * 
     * @param code 编码
     * @return 枚举对象
     */
    public static SegmentTempleteEnum getEnumByCode(String code) {
        if (code == null) {
            return null;
        }
        for (SegmentTempleteEnum object : SegmentTempleteEnum.values()) {
            if (object.getCode().equals(code)) {
                return object;
            }
        }
        
        return null;
    }
    
}
