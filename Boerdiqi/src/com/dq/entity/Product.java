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
public class Product implements Serializable {
	private Integer id;
	private String name;// 产品migncheng
	private String details;// 产品介绍
	private String imgUrl;//产品图片路径
	private Integer small_type_id;//小类别id
	private Integer shopnums;
	private SmallType smalltype;
	private BigType bigtype;
	private Integer big_type_id;//大类别id
	private Integer standard_id;
	private Standard standard;
    private String isOnline;//是否上架
    private double mail;//邮费
    private Integer nums;//销量
	private Timestamp join_time;// 产品添加时间
	private Integer delflag;
	private Integer num;//排序值
	private double o_price;//原价
	private double f_price;//现价
	 
	 

	public Integer getStandard_id() {
		return standard_id;
	}

	public void setStandard_id(Integer standardId) {
		standard_id = standardId;
	}

	public Standard getStandard() {
		return standard;
	}

	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	public double getO_price() {
		return o_price;
	}

	public void setO_price(double oPrice) {
		o_price = oPrice;
	}

	public double getF_price() {
		return f_price;
	}

	public void setF_price(double fPrice) {
		f_price = fPrice;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getShopnums() {
		return shopnums;
	}

	public void setShopnums(Integer shopnums) {
		this.shopnums = shopnums;
	}

	public Integer getDelflag() {
		return delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}
 

	public SmallType getSmalltype() {
		return smalltype;
	}

	public void setSmalltype(SmallType smalltype) {
		this.smalltype = smalltype;
	}

	public BigType getBigtype() {
		return bigtype;
	}

	public void setBigtype(BigType bigtype) {
		this.bigtype = bigtype;
	}

 

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getSmall_type_id() {
		return small_type_id;
	}

	public void setSmall_type_id(Integer smallTypeId) {
		small_type_id = smallTypeId;
	}

	public Integer getBig_type_id() {
		return big_type_id;
	}

	public void setBig_type_id(Integer bigTypeId) {
		big_type_id = bigTypeId;
	}

	 
	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

	public double getMail() {
		return mail;
	}

	public void setMail(double mail) {
		this.mail = mail;
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
		return "Product [big_type_id=" + big_type_id + ", bigtype=" + bigtype
				+ ", delflag=" + delflag + ", details=" + details
				+ ", f_price=" + f_price + ", id=" + id + ", imgUrl=" + imgUrl
				+ ", isOnline=" + isOnline + ", join_time=" + join_time
				+ ", mail=" + mail + ", name=" + name + ", num=" + num
				+ ", nums=" + nums + ", o_price=" + o_price + ", shopnums="
				+ shopnums + ", small_type_id=" + small_type_id
				+ ", smalltype=" + smalltype + ", standard=" + standard
				+ ", standard_id=" + standard_id + "]";
	}

}
