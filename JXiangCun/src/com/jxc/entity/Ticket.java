package com.jxc.entity;

import java.sql.Timestamp;

public class Ticket {
private Integer id;
private double price;
private Integer title_id;
private String endDate;
private String content;
private Integer user_id;
private String isUse;//券是否被使用过
private Timestamp useDate;//使用时间
private Title title;
private User user;

public Title getTitle() {
	return title;
}
public void setTitle(Title title) {
	this.title = title;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public Integer getTitle_id() {
	return title_id;
}
public void setTitle_id(Integer title_id) {
	this.title_id = title_id;
}

public Timestamp getEndDate() {
	endDate=endDate.trim();
	if(endDate!=null&&endDate.length()==10){
		StringBuffer sb=new StringBuffer(endDate);
		sb.append(" 00:00:00");
		return Timestamp.valueOf(sb.toString());
	}
	return Timestamp.valueOf(endDate);
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Integer getUser_id() {
	return user_id;
}
public void setUser_id(Integer user_id) {
	this.user_id = user_id;
}
public String getIsUse() {
	return isUse;
}
public void setIsUse(String isUse) {
	this.isUse = isUse;
}
public Timestamp getUseDate() {
	return useDate;
}
public void setUseDate(Timestamp useDate) {
	this.useDate = useDate;
}
@Override
public String toString() {
	return "Ticket [id=" + id + ", price=" + price + ", title_id=" + title_id
			+ ", endDate=" + endDate + ", content=" + content + ", user_id="
			+ user_id + ", isUse=" + isUse + ", useDate=" + useDate
			+ ", title=" + title + ", user=" + user + "]";
}

}
