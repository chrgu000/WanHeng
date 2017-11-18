package com.cgwas.rewardsRecord.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_rewards_record <br/>
 *         描述：用户奖罚记录表 <br/>
 */
@SuppressWarnings("serial")
public class RewardsRecord implements Serializable {
	protected Long id;// 主键
	protected Integer prestige;// 威望值
	protected Integer contribute;// 贡献值
	protected Integer flat;// 平台币
	protected String reason;// 奖罚原因
	protected String type;// type
	protected Long use_id;// 用户id
	protected Long company_id;// 公司id
	protected String name;// 操作者
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date time;// 操作时间

	public RewardsRecord() {
		super();
	}

	public RewardsRecord(Long id) {
		super();
		this.id = id;
	}
	
	@Id// 主键
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
	
	public Integer getContribute() {
		return contribute;
	}
	public void setContribute(Integer contribute) {
		this.contribute = contribute;
	}
	
	public Integer getFlat() {
		return flat;
	}
	public void setFlat(Integer flat) {
		this.flat = flat;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Long getUse_id() {
		return use_id;
	}
	public void setUse_id(Long use_id) {
		this.use_id = use_id;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
