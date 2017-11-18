package com.kg.entity;


public class PicVo {
private Integer baby_id;
private  String type;
private String num;
private Integer type_id;
private Integer pageIndex;
private Integer pageSize=15;
private Integer begin;

public Integer getPageIndex() {
	return pageIndex;
}
public void setPageIndex(Integer pageIndex) {
	this.pageIndex = pageIndex;
}
public Integer getPageSize() {
	return pageSize;
}
public void setPageSize(Integer pageSize) {
	this.pageSize = pageSize;
}
public Integer getBegin() {
	return (pageIndex - 1) * pageSize;
}
public void setBegin(Integer begin) {
	this.begin = begin;
}
public Integer getType_id() {
	return type_id;
}
public void setType_id(Integer typeId) {
	type_id = typeId;
}
public Integer getBaby_id() {
	return baby_id;
}
public void setBaby_id(Integer babyId) {
	baby_id = babyId;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getNum() {
	return num;
}
public void setNum(String num) {
	this.num = num;
}
@Override
public String toString() {
	return "PicVo [baby_id=" + baby_id + ", num=" + num + ", type=" + type
			+ ", type_id=" + type_id + "]";
}
}
