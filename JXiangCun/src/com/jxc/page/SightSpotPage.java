package com.jxc.page;

public class SightSpotPage extends Page {
private String name;
private Integer city_id;
private Integer area_id;
private Integer title_id;
public Integer getTitle_id() {
	return title_id;
}
public void setTitle_id(Integer title_id) {
	this.title_id = title_id;
}
public Integer getArea_id() {
	return area_id;
}
public void setArea_id(Integer areaId) {
	area_id = areaId;
}
public Integer getCity_id() {
	return city_id;
}
public void setCity_id(Integer city_id) {
	this.city_id = city_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
