package com.yingtong.entity;

import java.io.Serializable;

public class Brand implements Serializable {//品牌
private Integer id;
private String name;//品牌名
private Integer num;//序列号

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
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
 
@Override
public String toString() {
	return "Brand [id=" + id + ", name=" + name + ", num=" + num + "]";
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
	Brand other = (Brand) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}

}
