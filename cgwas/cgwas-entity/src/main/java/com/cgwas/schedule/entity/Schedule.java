package com.cgwas.schedule.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： u_schedule <br/>
 *         描述：进度时间表 <br/>
 */
@SuppressWarnings("serial")
public class Schedule implements Serializable {
	protected Long id;// id
	protected Long trade_record_id;// 交易记录表id
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date create_time;// 创建时间

	public Schedule() {
		super();
	}

	public Schedule(Long id) {
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
	
	public Long getTrade_record_id() {
		return trade_record_id;
	}
	public void setTrade_record_id(Long trade_record_id) {
		this.trade_record_id = trade_record_id;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
