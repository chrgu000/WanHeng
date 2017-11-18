package com.cgwas.userGrowth.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户成长实体类
 * @author Lingwh
 *
 */
@SuppressWarnings("serial")
public class UserGrowth implements Serializable {
	protected Long id;// 主键
	protected Integer prestige;// 威望
	protected Integer flat;// 平台币
	protected Long user_id;// 成长级别id

	public UserGrowth() {
		super();
	}

	public UserGrowth(Long id) {
		super();
		this.id = id;
	}

	@Id
	// 主键
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPrestige() {
		return prestige;
	}

	public void setPrestige(Integer prestige) {
		this.prestige = prestige;
	}

	public Integer getFlat() {
		return flat;
	}

	public void setFlat(Integer flat) {
		this.flat = flat;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
}
