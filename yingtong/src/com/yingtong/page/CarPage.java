package com.yingtong.page;

public class CarPage extends Page {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String name;
private Integer price_id;
private Integer brand_id;
private String state="0";


public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name.trim();
}
public Integer getPrice_id() {
	return price_id;
}
public void setPrice_id(Integer price_id) {
	this.price_id = price_id;
}
public Integer getBrand_id() {
	return brand_id;
}
public void setBrand_id(Integer brand_id) {
	this.brand_id = brand_id;
}
@Override
public String toString() {
	return "CarPage [name=" + name + ", price_id=" + price_id + ", brand_id="
			+ brand_id + ", state=" + state + "]";
}

}
