package com.dq.entity;

import java.io.Serializable;
/**
 * 优惠券实体类
 * @author 杨俊
 *
 */
public class Ticket implements Serializable{
private Integer id;
private String name;
private String start_time;//开始时间
private String end_time;//截止时间
private String join_time;//添加时间
private double price;//面值
private Integer num=0;//优惠券数量
private Integer unum;//每个用户可领数量
private String details;//优惠券说明'
private double minprice;//最低使用金额
private Integer state=1;//状态标记 1可领 2已领完
private Integer flag;//状态标记  1 未开始  2 进行中  3 已过期
private Integer delflag;//删除标记
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getStart_time() {
	return start_time;
}
public void setStart_time(String startTime) {
	start_time = startTime;
}
public String getEnd_time() {
	return end_time;
}
public void setEnd_time(String endTime) {
	end_time = endTime;
}
public Integer getNum() {
	return num;
}
public void setNum(Integer num) {
	this.num = num;
}
public String getDetails() {
	return details;
}
public void setDetails(String details) {
	this.details = details;
}
 
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getJoin_time() {
	return join_time;
}
public void setJoin_time(String  joinTime) {
	join_time = joinTime;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public Integer getUnum() {
	return unum;
}
public void setUnum(Integer unum) {
	this.unum = unum;
}
public double getMinprice() {
	return minprice;
}
public void setMinprice(double minprice) {
	this.minprice = minprice;
}
public Integer getState() {
	return state;
}
public void setState(Integer state) {
	this.state = state;
}
public Integer getFlag() {
	return flag;
}
public void setFlag(Integer flag) {
	this.flag = flag;
}
public Integer getDelflag() {
	return delflag;
}
public void setDelflag(Integer delflag) {
	this.delflag = delflag;
}
@Override
public String toString() {
	return "Ticket [delflag=" + delflag + ", details=" + details
			+ ", end_time=" + end_time + ", flag=" + flag + ", id=" + id
			+ ", join_time=" + join_time + ", minprice=" + minprice + ", name="
			+ name + ", num=" + num + ", price=" + price + ", start_time="
			+ start_time + ", state=" + state + ", unum=" + unum + "]";
}

}
