package com.jxc.entity;

public class City {
private Integer id;
private String name;//��������
private Integer num;//�������к�
private String ch;//һ�����к�
private String hot_city;//���ų���

 
 
public String getCh() {
	return ch;
}
public void setCh(String ch) {
	this.ch = ch;
}
public String getHot_city() {
	return hot_city;
}
public void setHot_city(String hot_city) {
	this.hot_city = hot_city;
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
	return "City [id=" + id + ", name=" + name + ", num=" + num + ", ch=" + ch
			+ ", hot_city=" + hot_city + "]";
}

}
