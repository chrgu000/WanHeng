package com.yingtong.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class LongRentService implements Serializable {
	private Integer id;
	private String days;// 租期
	private Integer car_num;// 租车数量
	private String  brand;// 品牌
	private Integer brand_id;
	private String buy_time;// 取车时间
	private String motorcycle;// 车型
	private String name;// 姓名
	private String relation_person;// 联系人
	private String tel;// 手机号
	private String email;// 邮箱
	private Timestamp apply_time;
	
	public Timestamp getApply_time() {
		return apply_time;
	}

	public void setApply_time(Timestamp apply_time) {
		this.apply_time = apply_time;
	}

	public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelation_person() {
		return relation_person;
	}

	public void setRelation_person(String relation_person) {
		this.relation_person = relation_person;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

 
	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public Integer getCar_num() {
		return car_num;
	}

	public void setCar_num(Integer car_num) {
		this.car_num = car_num;
	}

	 
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMotorcycle() {
		return motorcycle;
	}

	public void setMotorcycle(String motorcycle) {
		this.motorcycle = motorcycle;
	}

	public String getBuy_time() {
		return buy_time;
	}

	public void setBuy_time(String buy_time) {
		this.buy_time = buy_time;
	}

	 

	@Override
	public String toString() {
		return "LongRentService [id=" + id + ", days=" + days + ", car_num="
				+ car_num + ", brand=" + brand + ", buy_time=" + buy_time
				+ ", motorcycle=" + motorcycle + ", name=" + name
				+ ", relation_person=" + relation_person + ", tel=" + tel
				+ ", email=" + email + ", apply_time=" + apply_time + "]";
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
		LongRentService other = (LongRentService) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
