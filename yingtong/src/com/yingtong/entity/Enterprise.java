package com.yingtong.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Enterprise implements Serializable {
	private Integer id;
	private String account;// 企业账户名
	private String name;// 公司名称
	private String address;// 公司地址
	private String relation_person;// 联系人
	private String tel;// 联系电话
	private String email;// 邮件
	private Timestamp apply_time;// 申请时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Timestamp getApply_time() {
		return apply_time;
	}

	public void setApply_time(Timestamp apply_time) {
		this.apply_time = apply_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		Enterprise other = (Enterprise) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Enterprise [id=" + id + ", account=" + account + ", name="
				+ name + ", address=" + address + ", relation_person="
				+ relation_person + ", tel=" + tel + ", email=" + email
				+ ", apply_time=" + apply_time + "]";
	}

}
