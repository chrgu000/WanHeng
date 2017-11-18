package com.yingtong.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Address implements Serializable {
private Integer id;
private String addName;//服务点
private String address;//地址
private String tel;//手机
private String businessHour;//营业时间;
private Integer num;//序列号
Integer titleAddr_id;//市区id
private TitleAddr titleAddr;//市区对象
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getAddName() {
	return addName;
}
public void setAddName(String addName) {
	this.addName = addName;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}

public String getBusinessHour() {
	return businessHour;
}
public void setBusinessHour(String businessHour) {
	this.businessHour = businessHour;
}
public Integer getNum() {
	return num;
}
public void setNum(Integer num) {
	this.num = num;
}
public Integer getTitleAddr_id() {
	return titleAddr_id;
}
public void setTitleAddr_id(Integer titleAddr_id) {
	this.titleAddr_id = titleAddr_id;
}
public TitleAddr getTitleAddr() {
	return titleAddr;
}
public void setTitleAddr(TitleAddr titleAddr) {
	this.titleAddr = titleAddr;
}
@Override
public String toString() {
	return "Address [id=" + id + ", addName=" + addName + ", address="
			+ address + ", tel=" + tel + ", businessHour=" + businessHour
			+ ", num=" + num + ", titleAddr_id=" + titleAddr_id
			+ ", titleAddr=" + titleAddr + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Address other = (Address) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}

}
