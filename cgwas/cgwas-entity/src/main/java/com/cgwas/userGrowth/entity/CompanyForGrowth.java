package com.cgwas.userGrowth.entity;

/**
 * 修改公司成长实体类
 * 
 * @author Lingwh
 * 
 */
public class CompanyForGrowth {
	protected Long id; // ID
	protected String nickname; // 用户昵称
	protected String company_name; // 公司名字
	protected String contribute; // 贡献
	protected String flat; // 平台币

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getContribute() {
		return contribute;
	}

	public void setContribute(String contribute) {
		this.contribute = contribute;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

}
