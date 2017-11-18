package com.jxc.entity;

import java.sql.Timestamp;

public class Invite {
private Integer id;
private Integer product_id;
private Integer user_id;
private Integer invite_id;
private Integer order_id;
private String open_id;
private Timestamp createDate;
private Product product;
private User user;
private Inviter inviter;

public String getOpen_id() {
	return open_id;
}
public void setOpen_id(String open_id) {
	this.open_id = open_id;
}
public Integer getOrder_id() {
	return order_id;
}
public void setOrder_id(Integer order_id) {
	this.order_id = order_id;
}
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public Inviter getInviter() {
	return inviter;
}
public void setInviter(Inviter inviter) {
	this.inviter = inviter;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getProduct_id() {
	return product_id;
}
public void setProduct_id(Integer product_id) {
	this.product_id = product_id;
}
public Integer getUser_id() {
	return user_id;
}
public void setUser_id(Integer user_id) {
	this.user_id = user_id;
}
public Integer getInvite_id() {
	return invite_id;
}
public void setInvite_id(Integer invite_id) {
	this.invite_id = invite_id;
}
public Timestamp getCreateDate() {
	return createDate;
}
public void setCreateDate(Timestamp createDate) {
	this.createDate = createDate;
}
@Override
public String toString() {
	return "Invite [id=" + id + ", product_id=" + product_id + ", user_id="
			+ user_id + ", invite_id=" + invite_id + ", order_id=" + order_id
			+ ", createDate=" + createDate + ", product=" + product + ", user="
			+ user + ", inviter=" + inviter + "]";
}

}
