package com.yingtong.entity;

import java.io.Serializable;

public class Motorcycle implements Serializable {
private Integer id;
private String name;//车型名
private String num;//序列号
private Integer brand_id;//品牌id
private Brand brand;//品牌对象

public Brand getBrand() {
	return brand;
}
public void setBrand(Brand brand) {
	this.brand = brand;
}

public String getNum() {
	return num;
}
public void setNum(String num) {
	this.num = num;
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
public Integer getBrand_id() {
	return brand_id;
}
public void setBrand_id(Integer brand_id) {
	this.brand_id = brand_id;
}
@Override
public String toString() {
	return "Motorcycle [id=" + id + ", name=" + name + ", num=" + num
			+ ", brand_id=" + brand_id + ", brand=" + brand + "]";
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
	Motorcycle other = (Motorcycle) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}

}
