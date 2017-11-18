package com.jxc.entity;

import java.util.List;

public class Mark {
private Integer id;
private String name;//标签名
private Integer num;//序号
private String isFront;//是否在首页
private Integer title_id;//村游，微农，乡投标题
private List<Integer> titleIds;
private List<Title> titles;

public List<Title> getTitles() {
	return titles;
}
public void setTitles(List<Title> titles) {
	this.titles = titles;
}
public List<Integer> getTitleIds() {
	return titleIds;
}
public void setTitleIds(List<Integer> titleIds) {
	this.titleIds = titleIds;
}
public String getIsFront() {
	return isFront;
}
public void setIsFront(String isFront) {
	this.isFront = isFront;
}
public Integer getTitle_id() {
	return title_id;
}
public void setTitle_id(Integer title_id) {
	this.title_id = title_id;
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
public Integer getNum() {
	return num;
}
public void setNum(Integer num) {
	this.num = num;
}
@Override
public String toString() {
	return "Mark [id=" + id + ", isFront=" + isFront + ", name=" + name
			+ ", num=" + num + ", titleIds=" + titleIds + ", title_id="
			+ title_id + ", titles=" + titles + "]";
}

}
