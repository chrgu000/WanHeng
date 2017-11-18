package com.cgwas.userGroup.entity;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户组实体类
 * 
 * @author Lingwh
 * 
 */
@SuppressWarnings("serial")
public class UserGroup implements Serializable {
	protected Long id;// 主键
	protected String honor;// 头衔
	protected Integer starts;// 星星数
	protected String type;// type
	protected Integer min_integral;// 积分范围
	protected Integer max_integral;// max_integral
	protected String honor_color;// 头衔颜色
	protected char is_delete; // 删除标识

	public UserGroup() {
		super();
	}

	public UserGroup(Long id) {
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

	public String getHonor() {
		return honor;
	}

	public void setHonor(String honor) {
		this.honor = honor;
	}

	public Integer getStarts() {
		return starts;
	}

	public void setStarts(Integer starts) {
		this.starts = starts;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getMin_integral() {
		return min_integral;
	}

	public void setMin_integral(Integer min_integral) {
		this.min_integral = min_integral;
	}

	public Integer getMax_integral() {
		return max_integral;
	}

	public void setMax_integral(Integer max_integral) {
		this.max_integral = max_integral;
	}

	public String getHonor_color() {
		return honor_color;
	}

	public void setHonor_color(String honor_color) {
		this.honor_color = honor_color;
	}

	public char getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(char is_delete) {
		this.is_delete = is_delete;
	}

}
