package com.jxc.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Product {
	private Integer id;
	private String title;
	private String sub_title;
	private String pointDate;
	private double day1_original_price;
	private double day1_favourable_price;
	private double day5_original_price;
	private double day5_favourable_price;
	private double original_price;
	private double favourable_price;
	private String path;
	private Integer merchant_id;
	private String isFree;
	private String isUse;
	private String endDate;
	private Integer free_num;
	private Merchant merchant;
	private List<DatePrice> datePrices;
	private List<Integer> titleIds;
	private List<Title> titles;

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

	public List<DatePrice> getDatePrices() {
		return datePrices;
	}

	public void setDatePrices(List<DatePrice> datePrices) {
		this.datePrices = datePrices;
	}

	public Date getPointDate() {
		if (pointDate != null && pointDate.length() == 10) {
			return Date.valueOf(pointDate);
		} else if (pointDate != null && pointDate.length() > 10) {
			pointDate = pointDate.substring(0, 10);
			return Date.valueOf(pointDate);
		}
		return null;
	}

	public void setPointDate(String pointDate) {
		this.pointDate = pointDate;
	}

	public double getDay1_original_price() {
		return day1_original_price;
	}

	public void setDay1_original_price(double day1_original_price) {
		this.day1_original_price = day1_original_price;
	}

	public double getDay1_favourable_price() {
		return day1_favourable_price;
	}

	public void setDay1_favourable_price(double day1_favourable_price) {
		this.day1_favourable_price = day1_favourable_price;
	}

	public Integer getFree_num() {
		return free_num;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public double getDay5_original_price() {
		return day5_original_price;
	}

	public void setDay5_original_price(double day5_original_price) {
		this.day5_original_price = day5_original_price;
	}

	public double getDay5_favourable_price() {
		return day5_favourable_price;
	}

	public void setDay5_favourable_price(double day5_favourable_price) {
		this.day5_favourable_price = day5_favourable_price;
	}

	public Timestamp getEndDate() {
		if (endDate != null && endDate.length() > 0) {
			endDate = endDate.substring(0, 10);
			StringBuffer sb = new StringBuffer(endDate);
			sb.append(" 00:00:00");
			return Timestamp.valueOf(sb.toString());
		}
		return null;
	}

	public void setFree_num(Integer free_num) {
		this.free_num = free_num;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
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

	public double getOriginal_price() {
		return original_price;
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

	public Integer getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(Integer merchant_id) {
		this.merchant_id = merchant_id;
	}

	@Override
	public String toString() {
		return "Product [datePrices=" + datePrices + ", day1_favourable_price="
				+ day1_favourable_price + ", day1_original_price="
				+ day1_original_price + ", day5_favourable_price="
				+ day5_favourable_price + ", day5_original_price="
				+ day5_original_price + ", endDate=" + endDate
				+ ", favourable_price=" + favourable_price + ", free_num="
				+ free_num + ", id=" + id + ", isFree=" + isFree + ", isUse="
				+ isUse + ", merchant=" + merchant + ", merchant_id="
				+ merchant_id + ", original_price=" + original_price
				+ ", path=" + path + ", pointDate=" + pointDate
				+ ", sub_title=" + sub_title + ", title=" + title
				+ ", titleIds=" + titleIds + ", titles=" + titles + "]";
	}

}
