package com.dq.entity;

/**
 * 产品sku信息
 * 
 * @author Administrator
 *
 */
public class Skuinfo {
	private Integer skuid;
	private String attr_ids;
	private String attr_titles;
	private Integer pid;
    private Integer number;
    private Integer limit_number;
    private double favourable_price;
    private double original_price;
    private String code;
    private Integer integral;//积分
	private Integer delflag = 1;
	private String addtime;
	

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getLimit_number() {
		return limit_number;
	}

	public void setLimit_number(Integer limitNumber) {
		limit_number = limitNumber;
	}

	public double getFavourable_price() {
		return favourable_price;
	}

	public void setFavourable_price(double favourablePrice) {
		favourable_price = favourablePrice;
	}

	public double getOriginal_price() {
		return original_price;
	}

	public void setOriginal_price(double originalPrice) {
		original_price = originalPrice;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAttr_titles() {
		return attr_titles;
	}

	public void setAttr_titles(String attr_titles) {
		this.attr_titles = attr_titles;
	}

 

	public String getAttr_ids() {
		return attr_ids;
	}

	public void setAttr_ids(String attr_ids) {
		this.attr_ids = attr_ids;
	}


	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
 
 
	public Integer getSkuid() {
		return skuid;
	}

	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}
 

	public Integer getDelflag() {
		return delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	@Override
	public String toString() {
		return "Skuinfo [addtime=" + addtime + ", attr_ids=" + attr_ids
				+ ", attr_titles=" + attr_titles + ", code=" + code
				+ ", delflag=" + delflag + ", favourable_price="
				+ favourable_price + ", integral=" + integral
				+ ", limit_number=" + limit_number + ", number=" + number
				+ ", original_price=" + original_price + ", pid=" + pid
				+ ", skuid=" + skuid + "]";
	}
}
