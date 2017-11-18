package com.jxc.entity;

public class Price {
private Integer id;
private String price;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
@Override
public String toString() {
	return "Price [id=" + id + ", price=" + price + "]";
}

}
