package com.yingtong.page;

public class LongRentServicePage extends Page {
private String name;
private String tel;
private String email;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name.trim();
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel.trim();
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email.trim();
}

}
