package com.jxc.entity;

import java.util.List;

public class SightSpot {
private Integer id;
private String name;
private Integer city_id;
private Integer area_id;
private City city;
private Area area;
private String path;
private String content;
private double distance;
private double longitude;
private double latitude;
private List<Integer> titleIds;
private List<Title> titles;
private List<Merchant> merchants;
private Integer title_id;

public Integer getTitle_id() {
	return title_id;
}
public void setTitle_id(Integer titleId) {
	title_id = titleId;
}
public Integer getArea_id() {
	return area_id;
}
public void setArea_id(Integer areaId) {
	area_id = areaId;
}
public List<Integer> getTitleIds() {
	return titleIds;
}
public void setTitleIds(List<Integer> titleIds) {
	this.titleIds = titleIds;
}
public List<Title> getTitles() {
	return titles;
}
public void setTitles(List<Title> titles) {
	this.titles = titles;
}
public List<Merchant> getMerchants() {
	return merchants;
}
public void setMerchants(List<Merchant> merchants) {
	this.merchants = merchants;
}
public double getDistance() {
	return distance;
}
public void setDistance(double distance) {
	this.distance = distance;
}
public double getLongitude() {
	return longitude;
}
public void setLongitude(double longitude) {
	this.longitude = longitude;
}
public double getLatitude() {
	return latitude;
}
public void setLatitude(double latitude) {
	this.latitude = latitude;
}

public Area getArea() {
	return area;
}
public void setArea(Area area) {
	this.area = area;
}
public City getCity() {
	return city;
}
public void setCity(City city) {
	this.city = city;
}
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
public Integer getCity_id() {
	return city_id;
}
public void setCity_id(Integer city_id) {
	this.city_id = city_id;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
@Override
public String toString() {
	return "SightSpot [area=" + area + ", area_id=" + area_id + ", city="
			+ city + ", city_id=" + city_id + ", content=" + content
			+ ", distance=" + distance + ", id=" + id + ", latitude="
			+ latitude + ", longitude=" + longitude + ", merchants="
			+ merchants + ", name=" + name + ", path=" + path + ", titleIds="
			+ titleIds + ", title_id=" + title_id + ", titles=" + titles + "]";
}

}
