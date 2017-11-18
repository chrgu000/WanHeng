package com.jxc.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Order {
private Integer id;
private String order_num;
private String beginTime;
private String endTime;
private Timestamp createTime;
private String status;
private double total_price;
private Integer days;
private Integer user_id;
private String title;
private Integer number;//订单数量
private Integer product_id;
private Integer merchant_id;
private Integer title_id;
private String isFree;
private String isInvite;
private Integer spare_num;
private Integer free_num;
private Timestamp endDate;
private Product product;
private Merchant merchant;
private User user;
//**********微信配置参数
private String appId;
private String timeStamp;
private String nonceStr;
private String packageValue;
private String signType;
private String paySign;
private String sendUrl;
private String agent;

public Integer getFree_num() {
	return free_num;
}
public void setFree_num(Integer free_num) {
	this.free_num = free_num;
}
public Timestamp getEndDate() {
	return endDate;
}
public void setEndDate(Timestamp endDate) {
	this.endDate = endDate;
}
public Integer getSpare_num() {
	return spare_num;
}
public void setSpare_num(Integer spare_num) {
	this.spare_num = spare_num;
}
public Timestamp getCreateTime() {
	return createTime;
}
public void setCreateTime(Timestamp createTime) {
	this.createTime = createTime;
}
public Integer getTitle_id() {
	return title_id;
}
public void setTitle_id(Integer title_id) {
	this.title_id = title_id;
}
public String getIsInvite() {
	return isInvite;
}
public void setIsInvite(String isInvite) {
	this.isInvite = isInvite;
}
public Integer getProduct_id() {
	return product_id;
}
public void setProduct_id(Integer product_id) {
	this.product_id = product_id;
}
public Integer getMerchant_id() {
	return merchant_id;
}
public void setMerchant_id(Integer merchant_id) {
	this.merchant_id = merchant_id;
}
public String getIsFree() {
	return isFree;
}
public void setIsFree(String isFree) {
	this.isFree = isFree;
}
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public Merchant getMerchant() {
	return merchant;
}
public void setMerchant(Merchant merchant) {
	this.merchant = merchant;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getOrder_num() {
	return order_num;
}
public void setOrder_num(String order_num) {
	this.order_num = order_num;
}
public Timestamp getBeginTime() {
	if(beginTime!=null&&beginTime.length()<=11){
		StringBuffer sb=new StringBuffer(beginTime);
		sb.append("00:00:00");
		return Timestamp.valueOf(sb.toString());
	}else if(beginTime!=null&&beginTime.length()>11){
		return Timestamp.valueOf(beginTime);
	}
	return null;
}
public void setBeginTime(String beginTime) {
	this.beginTime = beginTime;
}
public Timestamp getEndTime() {
	if(endTime!=null&&endTime.length()<=11){
		StringBuffer sb=new StringBuffer(endTime);
		sb.append("00:00:00");
		return Timestamp.valueOf(sb.toString());
	}else if(endTime!=null&&endTime.length()>11){
		return Timestamp.valueOf(endTime);
	}
	return null;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public double getTotal_price() {
	return total_price;
}
public void setTotal_price(double total_price) {
	this.total_price = total_price;
}
public Integer getDays() {
	return days;
}
public void setDays(Integer days) {
	this.days = days;
}
public Integer getUser_id() {
	return user_id;
}
public void setUser_id(Integer user_id) {
	this.user_id = user_id;
}

public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public Integer getNumber() {
	return number;
}
public void setNumber(Integer number) {
	this.number = number;
}

public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
 
public String getAppId() {
	return appId;
}
public void setAppId(String appId) {
	this.appId = appId;
}
public String getTimeStamp() {
	return timeStamp;
}
public void setTimeStamp(String timeStamp) {
	this.timeStamp = timeStamp;
}
public String getNonceStr() {
	return nonceStr;
}
public void setNonceStr(String nonceStr) {
	this.nonceStr = nonceStr;
}
public String getPackageValue() {
	return packageValue;
}
public void setPackageValue(String packageValue) {
	this.packageValue = packageValue;
}
public String getSignType() {
	return signType;
}
public void setSignType(String signType) {
	this.signType = signType;
}
public String getPaySign() {
	return paySign;
}
public void setPaySign(String paySign) {
	this.paySign = paySign;
}
public String getSendUrl() {
	return sendUrl;
}
public void setSendUrl(String sendUrl) {
	this.sendUrl = sendUrl;
}
public String getAgent() {
	return agent;
}
public void setAgent(String agent) {
	this.agent = agent;
}
@Override
public String toString() {
	return "Order [id=" + id + ", order_num=" + order_num + ", beginTime="
			+ beginTime + ", endTime=" + endTime + ", createTime=" + createTime
			+ ", status=" + status + ", total_price=" + total_price + ", days="
			+ days + ", user_id=" + user_id + ", title=" + title + ", number="
			+ number + ", product_id=" + product_id + ", merchant_id="
			+ merchant_id + ", title_id=" + title_id + ", isFree=" + isFree
			+ ", isInvite=" + isInvite + ", spare_num=" + spare_num
			+ ", free_num=" + free_num + ", endDate=" + endDate + ", product="
			+ product + ", merchant=" + merchant + ", user=" + user
			+ ", appId=" + appId + ", timeStamp=" + timeStamp + ", nonceStr="
			+ nonceStr + ", packageValue=" + packageValue + ", signType="
			+ signType + ", paySign=" + paySign + ", sendUrl=" + sendUrl
			+ ", agent=" + agent + "]";
}
public static void main(String[] args) {
	String s="2010-9-3 00:00:00";
	System.out.println(Timestamp.valueOf(s));
}


}
