package com.cgwas.visitor.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： s_visitor <br/>
 *         描述：s_visitor <br/>
 */
@SuppressWarnings("serial")
public class VisitorVo extends Visitor {
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date startTime;// 开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date endTime;// 结束时间

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public VisitorVo() {
		super();
	}

}
