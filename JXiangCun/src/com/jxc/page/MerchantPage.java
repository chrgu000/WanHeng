package com.jxc.page;

public class MerchantPage extends Page {
private Integer title_id;
private String name;
private String isFree;
private Integer price_id;
private Integer city_id;
private Integer area_id;
private Integer merchant_id;
private Integer mark_id;
private Integer sight_spot_id;

@Override
public String toString() {
	return "MerchantPage [area_id=" + area_id + ", city_id=" + city_id
			+ ", isFree=" + isFree + ", mark_id=" + mark_id + ", merchant_id="
			+ merchant_id + ", name=" + name + ", price_id=" + price_id
			+ ", sight_spot_id=" + sight_spot_id + ", title_id=" + title_id
			+ "]";
}

public Integer getArea_id() {
	return area_id;
}

public void setArea_id(Integer areaId) {
	area_id = areaId;
}

public Integer getMark_id() {
	return mark_id;
}

public void setMark_id(Integer mark_id) {
	this.mark_id = mark_id;
}

public Integer getSight_spot_id() {
	return sight_spot_id;
}

public void setSight_spot_id(Integer sight_spot_id) {
	this.sight_spot_id = sight_spot_id;
}

public Integer getMerchant_id() {
	return merchant_id;
}

public void setMerchant_id(Integer merchant_id) {
	this.merchant_id = merchant_id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getIsFree() {
	return isFree;
}

public void setIsFree(String isFree) {
	this.isFree = isFree;
}

public Integer getPrice_id() {
	return price_id;
}

public void setPrice_id(Integer price_id) {
	this.price_id = price_id;
}

public Integer getCity_id() {
	return city_id;
}

public void setCity_id(Integer city_id) {
	this.city_id = city_id;
}

public Integer getTitle_id() {
	return title_id;
}

public void setTitle_id(Integer title_id) {
	this.title_id = title_id;
}

}
