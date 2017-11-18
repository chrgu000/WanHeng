package com.yingtong.entity;

import java.sql.Timestamp;

public class User {
private Integer id;
private String username;
private String password;
private String idcard;
private String tel;
private Timestamp regist_time;
private String open_id;
private String path;
private String nickname;
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
public Timestamp getRegist_time() {
	return regist_time;
}
public void setRegist_time(Timestamp regist_time) {
	this.regist_time = regist_time;
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
public String getIdcard() {
	return idcard;
}
public void setIdcard(String idcard) {
	this.idcard = idcard;
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
	User other = (User) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", password="
			+ password + ", idcard=" + idcard + ", tel=" + tel
			+ ", regist_time=" + regist_time + ", open_id=" + open_id
			+ ", path=" + path + ", nickname=" + nickname + "]";
}

}
