package com.yingtong.page;

public class AdvicePage extends Page {
private String adviser;
private String tel;
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
public String toString() {
	return "AdvicePage [adviser=" + adviser + ", tel=" + tel + "]";
}
 

}
