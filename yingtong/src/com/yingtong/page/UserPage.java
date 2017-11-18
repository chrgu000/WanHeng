package com.yingtong.page;

public class UserPage extends Page {
private String username;
private String tel;
private String idcard;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username.trim();
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel.trim();
}
public String getIdcard() {
	return idcard;
}
public void setIdcard(String idcard) {
	this.idcard = idcard.trim();
}

}
