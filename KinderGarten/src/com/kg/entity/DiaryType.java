package com.kg.entity;

import java.io.Serializable;

public class DiaryType implements Serializable{
private Integer id;
private String type;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
@Override
public String toString() {
	return "DiaryType [id=" + id + ", type=" + type + "]";
}

}
