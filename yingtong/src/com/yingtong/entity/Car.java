package com.yingtong.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Car implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;// 车名
	private Integer vehicle_id;// 车型
	private Integer price_id;// 价格
	private Integer brand_id;// 品牌
	private Integer titleAddr_id;// 市区id
	private String path;// 图片logo路径
	private String place;// 厢数
	private String autoNum;// 自动数
	private String seatNum;// 座位数
	private double original_price;// 原价
	private double favourable_price;// 优惠价格
	private String buyAddr;// 取车地点
	private String sendAddr;// 还车地点
	private String state;// 是否在首页状态
	private Integer days;// 租期
	private Vehicle vehicle;// 车型
	private Brand brand;// 品牌
	private TitleAddr titleAddr;// 市区
	private Integer num;
	private Integer spare_num;
	private String rent_status;

	public String getRent_status() {
		return rent_status;
	}

	public void setRent_status(String rent_status) {
		this.rent_status = rent_status;
	}

	public Integer getSpare_num() {
		return spare_num;
	}

	public void setSpare_num(Integer spare_num) {
		this.spare_num = spare_num;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Integer getPrice_id() {
		return price_id;
	}

	public void setPrice_id(Integer price_id) {
		this.price_id = price_id;
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

	public Integer getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(Integer vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getAutoNum() {
		return autoNum;
	}

	public void setAutoNum(String autoNum) {
		this.autoNum = autoNum;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBuyAddr() {
		return buyAddr;
	}

	public void setBuyAddr(String buyAddr) {
		this.buyAddr = buyAddr;
	}

	public String getSendAddr() {
		return sendAddr;
	}

	public void setSendAddr(String sendAddr) {
		this.sendAddr = sendAddr;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getTitleAddr_id() {
		return titleAddr_id;
	}

	public void setTitleAddr_id(Integer titleAddr_id) {
		this.titleAddr_id = titleAddr_id;
	}

	public TitleAddr getTitleAddr() {
		return titleAddr;
	}

	public void setTitleAddr(TitleAddr titleAddr) {
		this.titleAddr = titleAddr;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", name=" + name + ", vehicle_id="
				+ vehicle_id + ", price_id=" + price_id + ", brand_id="
				+ brand_id + ", titleAddr_id=" + titleAddr_id + ", path="
				+ path + ", place=" + place + ", autoNum=" + autoNum
				+ ", seatNum=" + seatNum + ", original_price=" + original_price
				+ ", favourable_price=" + favourable_price + ", buyAddr="
				+ buyAddr + ", sendAddr=" + sendAddr + ", state=" + state
				+ ", days=" + days + ", vehicle=" + vehicle + ", brand="
				+ brand + ", titleAddr=" + titleAddr + ", num=" + num
				+ ", spare_num=" + spare_num + ", rent_status=" + rent_status
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
