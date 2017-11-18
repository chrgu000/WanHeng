package com.jxc.entity;

import java.sql.Timestamp;

public class Support {
private Integer id;
private String path;
private String nickname;
private Timestamp support_time;
private Integer product_id;
private Product product;

public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
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
public Timestamp getSupport_time() {
	return support_time;
}
public void setSupport_time(Timestamp support_time) {
	this.support_time = support_time;
}
public Integer getProduct_id() {
	return product_id;
}
public void setProduct_id(Integer product_id) {
	this.product_id = product_id;
}
@Override
public String toString() {
	return "Support [id=" + id + ", path=" + path + ", nickname=" + nickname
			+ ", support_time=" + support_time + ", product_id=" + product_id
			+ "]";
}

}
