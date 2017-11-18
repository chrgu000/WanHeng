package com.yingtong.page;

import java.sql.Timestamp;

public class SelectCar {
private String buyAddr;
private String buyTime;
private String sendAddr;
private String  sendTime;
private Integer days;
private Integer vehicle_id;
private Integer brand_id;
private Integer price_id;
public String getBuyAddr() {
	return buyAddr;
}
public void setBuyAddr(String buyAddr) {
	this.buyAddr = buyAddr;
}
 
public Timestamp getBuyTime() {
	if(buyTime!=null&&!buyTime.trim().equals("")&&buyTime.length()==10){
		StringBuffer bBuyTime=new StringBuffer(buyTime);
		bBuyTime.append(" 00:00:00");
		return Timestamp.valueOf(bBuyTime.toString());
	}else if(buyTime!=null&&!buyTime.trim().equals("")){
		return Timestamp.valueOf(buyTime);
	}
	return null;
}
public void setBuyTime(String buyTime) {
	this.buyTime = buyTime;
}
 
public String getSendAddr() {
	return sendAddr;
}
public void setSendAddr(String sendAddr) {
	this.sendAddr = sendAddr;
}
public Timestamp getSendTime() {
	if(sendTime!=null&&!sendTime.trim().equals("")&&sendTime.length()==10){
		StringBuffer bSendTime=new StringBuffer(sendTime);
		bSendTime.append(" 00:00:00");
		return Timestamp.valueOf(bSendTime.toString());
	}else if(sendTime!=null&&!sendTime.trim().equals("")){
		return Timestamp.valueOf(sendTime);
	}
	return null;
}
public void setSendTime(String sendTime) {
	this.sendTime = sendTime;
	}

	public int getDays() {
		if(getSendTime()==null||getBuyTime()==null){
			return 0;
		}
	return (int)(Math.ceil(((getSendTime().getTime()-getBuyTime().getTime())/(1000*60*60*24))));
}
public void setDays(Integer days) {
	this.days = days;
}
public Integer getVehicle_id() {
	return vehicle_id;
}
public void setVehicle_id(Integer vehicle_id) {
	this.vehicle_id = vehicle_id;
}
public Integer getBrand_id() {
	return brand_id;
}
public void setBrand_id(Integer brand_id) {
	this.brand_id = brand_id;
}
public Integer getPrice_id() {
	return price_id;
}
public void setPrice_id(Integer price_id) {
	this.price_id = price_id;
}
@Override
public String toString() {
	return "SelectCar [buyAddr=" + buyAddr + ", buyTime=" + buyTime
			+ ", sendAddr=" + sendAddr + ", sendTime=" + sendTime + ", days="
			+getDays() + ", vehicle_id=" + vehicle_id + ", brand_id=" + brand_id
			+ ", price_id=" + price_id + "]";
}
public static void main(String[] args) {
	Timestamp t1=new Timestamp(System.currentTimeMillis());
	 
	System.out.println(t1);
	
}
}
