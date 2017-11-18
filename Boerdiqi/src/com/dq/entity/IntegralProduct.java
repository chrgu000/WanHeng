package com.dq.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 产品实体类
 * 
 * @author 杨俊
 * 
 */
public class IntegralProduct implements Serializable {
	private Integer id;
	private String name;// 产品migncheng
	private String details;// 产品介绍
	private String imgUrl;//产品图片路径
    private String isOnline;//是否上架
    private Integer nums;//销量
	private Timestamp join_time;// 产品添加时间
	private Integer delflag;
    private Integer integral;//兑换产品所需积分
    private String code;//商品编码
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Integer getDelflag() {
		return delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}
 
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
  
	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
 
	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}
 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	 
	public String getJoin_time() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (join_time == null)
			return null;
		return sdf.format(join_time);
	}

	public void setJoin_time(Timestamp joinTime) {
		join_time = joinTime;
	}

	@Override
	public String toString() {
		return "IntegralProduct [code=" + code + ", delflag=" + delflag
				+ ", details=" + details + ", id=" + id + ", imgUrl=" + imgUrl
				+ ", integral=" + integral + ", isOnline=" + isOnline
				+ ", join_time=" + join_time + ", name=" + name + ", nums="
				+ nums + "]";
	}

}
