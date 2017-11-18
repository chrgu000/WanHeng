package com.yingtong.entity;

import java.io.Serializable;

public class Phone implements Serializable{
private Integer id;
private String tel;
private Integer number;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public Integer getNumber() {
	return number;
}
public void setNumber(Integer number) {
	this.number = number;
}
@Override
public String toString() {
	return "Phone [id=" + id + ", tel=" + tel + ", number=" + number + "]";
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
	Phone other = (Phone) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}

}
