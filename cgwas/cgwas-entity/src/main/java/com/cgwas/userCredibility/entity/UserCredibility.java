package com.cgwas.userCredibility.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_user_credibility <br/>
 *         描述：用户信誉表 <br/>
 */
@SuppressWarnings("serial")
public class UserCredibility implements Serializable {
	protected Long id;// 主键
	protected Double passing_rate;// 通过率
	protected Double production_speed;// 制作速度
	protected Double production_quality;// 制作质量
	protected Double rating_points;// 等级积分
	protected Long user_id;// user_id
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date creat_time;// 创建时间
	protected Long update_tiems; // 改变次数

	public UserCredibility() {
		super();
	}

	public UserCredibility(Long id) {
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

	public Double getPassing_rate() {
		return passing_rate;
	}

	public void setPassing_rate(Double passing_rate) {
		this.passing_rate = passing_rate;
	}

	public Double getProduction_speed() {
		return production_speed;
	}

	public void setProduction_speed(Double production_speed) {
		this.production_speed = production_speed;
	}

	public Double getProduction_quality() {
		return production_quality;
	}

	public void setProduction_quality(Double production_quality) {
		this.production_quality = production_quality;
	}

	public Double getRating_points() {
		return rating_points;
	}

	public void setRating_points(Double rating_points) {
		this.rating_points = rating_points;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Date getCreat_time() {
		return creat_time;
	}

	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}

	public Long getUpdate_tiems() {
		return update_tiems;
	}

	public void setUpdate_tiems(Long update_tiems) {
		this.update_tiems = update_tiems;
	}

}
