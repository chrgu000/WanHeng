package com.fengyun.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： y_course_trade_skill <br/>
 *         描述：y_course_trade_skill <br/>
 */
@SuppressWarnings("serial")
public class CourseTradeSkill implements Serializable {
	protected Long id;// 主键
	protected Long course_id;// 课程id
	protected Long trade_skill_id;// 行业技能id

	public CourseTradeSkill() {
		super();
	}

	public CourseTradeSkill(Long id) {
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
	
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	
	public Long getTrade_skill_id() {
		return trade_skill_id;
	}
	public void setTrade_skill_id(Long trade_skill_id) {
		this.trade_skill_id = trade_skill_id;
	}
}
