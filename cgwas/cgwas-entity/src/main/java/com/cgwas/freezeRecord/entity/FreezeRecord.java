package com.cgwas.freezeRecord.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_freeze_record <br/>
 *         描述：u_freeze_record <br/>
 */
@SuppressWarnings("serial")
public class FreezeRecord implements Serializable {
	protected Long id;// 主键
	protected Double freeze_price;// 冻结金额
	protected String freeze_state;// 冻结状态(Y/N)
	protected String consume_state;// 消费状态(Y/N)
	protected String trade_type;// 冻结类型
	protected String task_type;// 任务类型
	protected Long user_id;// 用户id
	protected Long task_id;// 任务id
	protected Long company_id;// 所属公司id
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date create_time;// 创建时间

	public FreezeRecord() {
		super();
	}

	public FreezeRecord(Long id) {
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

	public Double getFreeze_price() {
		return freeze_price;
	}

	public void setFreeze_price(Double freeze_price) {
		this.freeze_price = freeze_price;
	}

	public String getFreeze_state() {
		return freeze_state;
	}

	public void setFreeze_state(String freeze_state) {
		this.freeze_state = freeze_state;
	}

	public String getConsume_state() {
		return consume_state;
	}

	public void setConsume_state(String consume_state) {
		this.consume_state = consume_state;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getTask_type() {
		return task_type;
	}

	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getTask_id() {
		return task_id;
	}

	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
