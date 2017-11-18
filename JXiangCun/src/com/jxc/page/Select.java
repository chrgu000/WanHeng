package com.jxc.page;

public class Select {
private Integer city_id;
private String title;
private Integer distance_id;
private Integer price_id;
private Integer sequence_id;
private Integer mark_id;
private Integer title_id;

public Integer getTitle_id() {
	return title_id;
}
public void setTitle_id(Integer titleId) {
	title_id = titleId;
}
public Integer getCity_id() {
	return city_id;
}
public void setCity_id(Integer city_id) {
	this.city_id = city_id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public Integer getDistance_id() {
	return distance_id;
}
public void setDistance_id(Integer distance_id) {
	this.distance_id = distance_id;
}
public Integer getPrice_id() {
	return price_id;
}
public void setPrice_id(Integer price_id) {
	this.price_id = price_id;
}
public Integer getSequence_id() {
	return sequence_id;
}
public void setSequence_id(Integer sequence_id) {
	this.sequence_id = sequence_id;
}
public Integer getMark_id() {
	return mark_id;
}
public void setMark_id(Integer mark_id) {
	this.mark_id = mark_id;
}
@Override
public String toString() {
	return "Select [city_id=" + city_id + ", distance_id=" + distance_id
			+ ", mark_id=" + mark_id + ", price_id=" + price_id
			+ ", sequence_id=" + sequence_id + ", title=" + title
			+ ", title_id=" + title_id + "]";
}

}
