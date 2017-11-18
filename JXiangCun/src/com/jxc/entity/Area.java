package com.jxc.entity;

public class Area {
private Integer id;
private String name;
private String ch;
private Integer num;
private Integer city_id;
private City city;

public City getCity() {
	return city;
}
public void setCity(City city) {
	this.city = city;
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
public String getCh() {
	return ch;
}
public void setCh(String ch) {
	this.ch = ch;
}
public Integer getNum() {
	return num;
}
public void setNum(Integer num) {
	this.num = num;
}
public Integer getCity_id() {
	return city_id;
}
public void setCity_id(Integer cityId) {
	city_id = cityId;
}
@Override
public String toString() {
	return "Area [ch=" + ch + ", city=" + city + ", city_id=" + city_id
			+ ", id=" + id + ", name=" + name + ", num=" + num + "]";
}

}
