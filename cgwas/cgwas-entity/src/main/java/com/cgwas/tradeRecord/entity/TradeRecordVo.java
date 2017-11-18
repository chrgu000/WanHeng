package com.cgwas.tradeRecord.entity;

import java.util.List;

import com.cgwas.schedule.entity.ScheduleVo;

/**
 * @author 模版生成 <br/>
 *         表名： u_trade_record <br/>
 *         描述：交易记录表 <br/>
 */
 @SuppressWarnings("serial")
public class TradeRecordVo extends TradeRecord {
	private List<ScheduleVo> scheduleList;
	
	public TradeRecordVo() {
		super();
	}

	public TradeRecordVo(Long id) {
		super();
		this.id = id;
	}

	public List<ScheduleVo> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<ScheduleVo> scheduleList) {
		this.scheduleList = scheduleList;
	}


}
