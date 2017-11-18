package com.cgwas.userGrowth.entity;

/**
 * 修改用户成长实体类
 * 
 * @author Lingwh
 * 
 */
public class UserForGrowth {
	protected Long id; // 用户名
	protected String nickname; // 昵称
	protected String name; // 姓名
	protected String prestige;// 威望
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrestige() {
		return prestige;
	}

	public void setPrestige(String prestige) {
		this.prestige = prestige;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

}
