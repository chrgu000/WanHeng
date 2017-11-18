package com.jxc.entity;

public class Selfdom {
private Integer id;
private String selfdom;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getSelfdom() {
	return selfdom;
}
public void setSelfdom(String selfdom) {
	this.selfdom = selfdom;
}
@Override
public String toString() {
	return "Selfdom [id=" + id + ", selfdom=" + selfdom + "]";
}

}
