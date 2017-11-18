package com.jxc.entity;

public class Address {
private Integer id;
private String name;// 收件人
private String tel;//手机号
private String address;//地址
private String province;
private String city;
private String area;
private String isDefault;//是否为默认地址
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
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
public String getIsDefault() {
	return isDefault;
}
public void setIsDefault(String isDefault) {
	this.isDefault = isDefault;
}
@Override
public String toString() {
	return "Address [id=" + id + ", name=" + name + ", tel=" + tel
			+ ", address=" + address + ", province=" + province + ", city="
			+ city + ", area=" + area + ", isDefault=" + isDefault + "]";
}

}
