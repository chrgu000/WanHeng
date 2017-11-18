package com.jxc.page;

public class UserPage extends Page {
private String username;
private String tel;
private String nickname;
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
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}


}
