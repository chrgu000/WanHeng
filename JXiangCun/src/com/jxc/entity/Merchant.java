package com.jxc.entity;

import java.util.List;

public class Merchant {
	private Integer id;
	private Integer city_id;
	private Integer price_id;
	private Integer sight_spot_id;
	private SightSpot sightspot;
	private City city;
	private Price price;
	private String title;
	private String sub_title;
	private String content;
	private String details;
	private double original_price;
	private double favourable_price;
	private double distance;
	private String path;
	private double longitude;
	private double latitude;
	private Integer isOk;// 点赞数
	private String predetermine;// 预定须知
	private List<Integer> markIds;
	private List<Mark> marks;
	private List<Evaluate> evaluates;
	private List<Picture> pictures;
	private List<Integer> titleIds;
	private List<Title> titles;
    private Integer area_id;
    private Area area;
    private Integer num;
    
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Integer getArea_id() {
		return area_id;
	}

	public void setArea_id(Integer areaId) {
		area_id = areaId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
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

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public List<Evaluate> getEvaluates() {
		return evaluates;
	}

	public void setEvaluates(List<Evaluate> evaluates) {
		this.evaluates = evaluates;
	}

	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

	public List<Integer> getMarkIds() {
		return markIds;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setMarkIds(List<Integer> markIds) {
		this.markIds = markIds;
	}

	public SightSpot getSightspot() {
		return sightspot;
	}

	public void setSightspot(SightSpot sightspot) {
		this.sightspot = sightspot;
	}

	public String getPredetermine() {
		return predetermine;
	}

	public void setPredetermine(String predetermine) {
		this.predetermine = predetermine;
	}

	public Integer getSight_spot_id() {
		return sight_spot_id;
	}

	public void setSight_spot_id(Integer sight_spot_id) {
		this.sight_spot_id = sight_spot_id;
	}

	public double getOriginal_price() {
		return original_price;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getIsOk() {
		return isOk;
	}

	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public Integer getPrice_id() {
		return price_id;
	}

	public void setPrice_id(Integer price_id) {
		this.price_id = price_id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setOriginal_price(double original_price) {
		this.original_price = original_price;
	}

	public double getFavourable_price() {
		return favourable_price;
	}

	public void setFavourable_price(double favourable_price) {
		this.favourable_price = favourable_price;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Merchant [area=" + area + ", area_id=" + area_id + ", city="
				+ city + ", city_id=" + city_id + ", content=" + content
				+ ", details=" + details + ", distance=" + distance
				+ ", evaluates=" + evaluates + ", favourable_price="
				+ favourable_price + ", id=" + id + ", isOk=" + isOk
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", markIds=" + markIds + ", marks=" + marks + ", num=" + num
				+ ", original_price=" + original_price + ", path=" + path
				+ ", pictures=" + pictures + ", predetermine=" + predetermine
				+ ", price=" + price + ", price_id=" + price_id
				+ ", sight_spot_id=" + sight_spot_id + ", sightspot="
				+ sightspot + ", sub_title=" + sub_title + ", title=" + title
				+ ", titleIds=" + titleIds + ", titles=" + titles + "]";
	}

}
