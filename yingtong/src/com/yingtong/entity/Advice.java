package com.yingtong.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Advice implements Serializable {
private Integer id;
private String advice;
private Timestamp time;
private String adviser;
private String tel;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getAdvice() {
	return advice;
}
public void setAdvice(String advice) {
	this.advice = advice;
}
public Timestamp getTime() {
	return time;
}
public void setTime(Timestamp time) {
	this.time = time;
}
public String getAdviser() {
	return adviser;
}
public void setAdviser(String adviser) {
	this.adviser = adviser;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
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
	Advice other = (Advice) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}
@Override
public String toString() {
	return "Advice [id=" + id + ", advice=" + advice + ", time=" + time
			+ ", adviser=" + adviser + ", tel=" + tel + "]";
}

}
