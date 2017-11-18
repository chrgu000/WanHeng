package com.dq.entity;

import java.io.Serializable;
/**
 * 地址实体类
 * @author 杨俊
 *
 */
public class Address implements Serializable {
private Integer id;
private Integer user_id;//用户id
private User user;
private String username;//收件人姓名
private String tel;//收件人手机号
private String province;//省份
private String city;//城市
private String area;//区
private String addr;//收件人详细地址
private String isDefault;//是否为默认地址
public Integer getId() {
	return id;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getUser_id() {
	return user_id;
}
public void setUser_id(Integer userId) {
	user_id = userId;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
}
public String getAddr() {
	return addr;
}
public void setAddr(String addr) {
	this.addr = addr;
}
public String getIsDefault() {
	return isDefault;
}
public void setIsDefault(String isDefault) {
	this.isDefault = isDefault;
}
@Override
public String toString() {
	return "Address [addr=" + addr + ", area=" + area + ", city=" + city
			+ ", id=" + id + ", isDefault=" + isDefault + ", province="
			+ province + ", tel=" + tel + ", user=" + user + ", user_id="
			+ user_id + ", username=" + username + "]";
}

}
