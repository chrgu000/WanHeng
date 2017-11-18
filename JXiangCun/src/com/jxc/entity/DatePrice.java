package com.jxc.entity;

import java.sql.Date;

public class DatePrice {
	private Integer id;
	private Integer product_id;
	private Date date;
	private double original_price;
	private double favourable_price;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	@Override
	public String toString() {
		return "DatePrice [id=" + id + ", product_id=" + product_id
				+ ", original_price=" + original_price + ", favourable_price="
				+ favourable_price + "]";
	}

}
