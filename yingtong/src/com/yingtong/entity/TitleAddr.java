package com.yingtong.entity;

import java.io.Serializable;
import java.util.List;

public class TitleAddr implements Serializable{
private Integer id;
private String titleAddr;//杭州市区
private List<Address> addresses;//地址列表

public List<Address> getAddresses() {
	return addresses;
}
public void setAddresses(List<Address> addresses) {
	this.addresses = addresses;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getTitleAddr() {
	return titleAddr;
}
public void setTitleAddr(String titleAddr) {
	this.titleAddr = titleAddr;
}
@Override
public String toString() {
	return "TitleAddr [id=" + id + ", titleAddr=" + titleAddr + ", addresses="
			+ addresses + "]";
}

}
