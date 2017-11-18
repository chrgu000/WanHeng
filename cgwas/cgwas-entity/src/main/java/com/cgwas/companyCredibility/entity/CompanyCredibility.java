package com.cgwas.companyCredibility.entity;

import javax.persistence.Id;

import java.util.Date;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_company_credibility <br/>
 *         描述：公司信誉表 <br/>
 */
@SuppressWarnings("serial")
public class CompanyCredibility implements Serializable {
	protected Long id;// 主键
	protected String cedibility;// 公司信誉度
	protected String match_degree;// 任务相符度
	protected Integer rating_points;// 等级积分
	protected String good_at;// 公司擅长
	protected Long company_id;// 成长等级id
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date creat_time;// 创建时间
	protected Long update_tiems; // 改变次数

	public CompanyCredibility() {
		super();
	}

	public CompanyCredibility(Long id) {
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

	public String getCedibility() {
		return cedibility;
	}

	public void setCedibility(String cedibility) {
		this.cedibility = cedibility;
	}

	public String getMatch_degree() {
		return match_degree;
	}

	public void setMatch_degree(String match_degree) {
		this.match_degree = match_degree;
	}

	public Integer getRating_points() {
		return rating_points;
	}

	public void setRating_points(Integer rating_points) {
		this.rating_points = rating_points;
	}

	public String getGood_at() {
		return good_at;
	}

	public void setGood_at(String good_at) {
		this.good_at = good_at;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
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
