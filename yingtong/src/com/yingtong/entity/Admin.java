package com.yingtong.entity;

import java.io.Serializable;

public class Admin implements Serializable{
private static final long serialVersionUID = 1L;
private Integer id;
private String username;
private String password;
private Integer power_id;//½ÇÉ«ID
private Power power;

public Power getPower() {
	return power;
}
public void setPower(Power power) {
	this.power = power;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
 
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Integer getPower_id() {
	return power_id;
}
public void setPower_id(Integer power_id) {
	this.power_id = power_id;
}
@Override
public String toString() {
	return "Admin [id=" + id + ", username=" + username + ", password="
			+ password + ",  power_id=" + power_id
			+ ", power=" + power + "]";
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
	Admin other = (Admin) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}


}
