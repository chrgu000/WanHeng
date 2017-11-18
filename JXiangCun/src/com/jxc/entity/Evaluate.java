package com.jxc.entity;

import java.sql.Timestamp;

public class Evaluate {
private Integer id;
private String content;//评价内容
private Integer merchant_id;//商户id
private String nickname;
private String path;
private Integer num;
private Timestamp createDate;
private Merchant merchant;

public Integer getNum() {
	return num;
}
public void setNum(Integer num) {
	this.num = num;
}
public Merchant getMerchant() {
	return merchant;
}
public void setMerchant(Merchant merchant) {
	this.merchant = merchant;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Integer getMerchant_id() {
	return merchant_id;
}
public void setMerchant_id(Integer merchant_id) {
	this.merchant_id = merchant_id;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public Timestamp getCreateDate() {
	return createDate;
}
public void setCreateDate(Timestamp createDate) {
	this.createDate = createDate;
}
@Override
public String toString() {
	return "Evaluate [id=" + id + ", content=" + content + ", merchant_id="
			+ merchant_id + ", nickname=" + nickname + ", path=" + path
			+ ", num=" + num + ", createDate=" + createDate + ", merchant="
			+ merchant + "]";
}

}
