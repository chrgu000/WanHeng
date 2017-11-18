package com.jxc.entity;

import java.sql.Timestamp;

public class User {
private Integer id;
private String tel;
private Timestamp regist_time;
private String open_id;
private String path;
private String nickname;
private double money;

public double getMoney() {
	return money;
}
public void setMoney(double money) {
	this.money = money;
}
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
public Timestamp getRegist_time() {
	return regist_time;
}
public void setRegist_time(Timestamp regist_time) {
	this.regist_time = regist_time;
}
public String getOpen_id() {
	return open_id;
}
public void setOpen_id(String open_id) {
	this.open_id = open_id;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}

@Override
public String toString() {
	return "User [id=" + id + ", tel=" + tel + ", regist_time=" + regist_time
			+ ", open_id=" + open_id + ", path=" + path + ", nickname="
			+ nickname + ", money=" + money + "]";
}

}
