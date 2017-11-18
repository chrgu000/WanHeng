package com.jxc.entity;

public class Picture {
private Integer id;
private String path;//图片路径
private Integer merchant_id;
private String url;//图片用途的路径
private String type;//图片的应用类型
private Integer num;
private Merchant merchant;

public Integer getNum() {
	return num;
}
public void setNum(Integer num) {
	this.num = num;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}

public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}

public Merchant getMerchant() {
	return merchant;
}
public void setMerchant(Merchant merchant) {
	this.merchant = merchant;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public Integer getMerchant_id() {
	return merchant_id;
}
public void setMerchant_id(Integer merchant_id) {
	this.merchant_id = merchant_id;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}

@Override
public String toString() {
	return "Picture [id=" + id + ", path=" + path + ", merchant_id="
			+ merchant_id + ", url=" + url + ", type=" + type + ", merchant="
			+ merchant + "]";
}

}
