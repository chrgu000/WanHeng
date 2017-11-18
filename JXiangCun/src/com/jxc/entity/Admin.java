package com.jxc.entity;

public class Admin {
private Integer id;
private String username;
private String password;
private Integer power_id;
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
	return "Admin [id=" + id + ", password=" + password + ", power_id="
			+ power_id + "]";
}

}
