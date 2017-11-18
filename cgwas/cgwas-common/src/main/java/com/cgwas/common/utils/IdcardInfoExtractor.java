package com.cgwas.common.utils;
import java.text.SimpleDateFormat;   
import java.util.Calendar;   
import java.util.Date;   
import java.util.GregorianCalendar;   
import java.util.HashMap;   
import java.util.Map;   
import java.util.Set;   
  
/**  
 *         <p>  
 *         类说明:提取身份证相关信息  
 *         </p>  
 */  
public class IdcardInfoExtractor {   
    // 省份   
    private String province;   
    // 城市   
    private String city;   
    // 区县   
    private String region;   
    // 年份   
    private int year;   
    // 月份   
    private int month;   
    // 日期   
    private int day;   
    // 性别   
    private String gender;   
    // 出生日期   
    private Date birthday;   
  
    private Map<String, String> cityCodeMap = new HashMap<String, String>() {   
        {   
            this.put("11", "110000");   
            this.put("12", "120000");   
            this.put("13", "130000");   
            this.put("14", "140000");   
            this.put("15", "150000");   
            this.put("21", "210000");   
            this.put("22", "220000");   
            this.put("23", "230000");   
            this.put("31", "310000");   
            this.put("32", "320000");   
            this.put("33", "330000");   
            this.put("34", "340000");   
            this.put("35", "350000");   
            this.put("36", "360000");   
            this.put("37", "370000");   
            this.put("41", "410000");   
            this.put("42", "420000");   
            this.put("43", "430000");   
            this.put("44", "440000");   
            this.put("45", "450000");   
            this.put("46", "460000");   
            this.put("50", "500000");   
            this.put("51", "510000");   
            this.put("52", "520000");   
            this.put("53", "530000");   
            this.put("54", "540000");   
            this.put("61", "610000");   
            this.put("62", "620000");   
            this.put("63", "630000");   
            this.put("64", "640000");   
            this.put("65", "650000");   
            this.put("71", "710000");   
            this.put("81", "810000");   
            this.put("82", "820000");   
            this.put("91", "910000");   
        }   
    };   
       
    private IdcardValidator validator = null;   
       
    /**  
     * 通过构造方法初始化各个成员属性  
     */  
    public IdcardInfoExtractor(String idcard) {   
        try {   
            validator = new IdcardValidator();   
            if (validator.isValidatedAllIdcard(idcard)) {   
                if (idcard.length() == 15) {   
                    idcard = validator.convertIdcarBy15bit(idcard);   
                }   
                // 获取省份   
                String provinceId = idcard.substring(0, 2);   
                Set<String> key = this.cityCodeMap.keySet();   
                for (String id : key) {   
                    if (id.equals(provinceId)) {   
                        this.province = this.cityCodeMap.get(id);   
                        break;   
                    }   
                }   
  
                // 获取性别   
                String id17 = idcard.substring(16, 17);   
                if (Integer.parseInt(id17) % 2 != 0) {   
                    this.gender = "1";   
                } else {   
                    this.gender = "2";   
                }   
  
                // 获取出生日期   
                String birthday = idcard.substring(6, 14);   
                Date birthdate = new SimpleDateFormat("yyyyMMdd")   
                        .parse(birthday);   
                this.birthday = birthdate;   
                GregorianCalendar currentDay = new GregorianCalendar();   
                currentDay.setTime(birthdate);   
                this.year = currentDay.get(Calendar.YEAR);   
                this.month = currentDay.get(Calendar.MONTH) + 1;   
                this.day = currentDay.get(Calendar.DAY_OF_MONTH);   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
    }   
  
    /**  
     * @return the province  
     */  
    public String getProvince() {   
        return province;   
    }   
  
    /**  
     * @return the city  
     */  
    public String getCity() {   
        return city;   
    }   
  
    /**  
     * @return the region  
     */  
    public String getRegion() {   
        return region;   
    }   
  
    /**  
     * @return the year  
     */  
    public int getYear() {   
        return year;   
    }   
  
    /**  
     * @return the month  
     */  
    public int getMonth() {   
        return month;   
    }   
  
    /**  
     * @return the day  
     */  
    public int getDay() {   
        return day;   
    }   
  
    /**  
     * @return the gender  
     */  
    public String getGender() {   
        return gender;   
    }   
  
    /**  
     * @return the birthday  
     */  
    public Date getBirthday() {   
        return this.birthday;   
    }   
  
    @Override  
    public String toString() {   
        return  this.province +","+ this.gender + ","  
                + this.birthday;   
    }   
    public static void main(String[] args) {   
        String idcard = "320311770706001";   
        IdcardInfoExtractor ie = new IdcardInfoExtractor(idcard);   
        System.out.println(ie.toString());   
    }   
} 