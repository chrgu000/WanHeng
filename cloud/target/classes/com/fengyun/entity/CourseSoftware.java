package com.fengyun.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： y_course_software <br/>
 *         描述：y_course_software <br/>
 */
@SuppressWarnings("serial")
public class CourseSoftware implements Serializable {
	protected Long id;// 主键
	protected Long course_id;// 课程id
	protected Long software_id;// 软件id

	public CourseSoftware() {
		super();
	}

	public CourseSoftware(Long id) {
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
	
	public Long getSoftware_id() {
		return software_id;
	}
	public void setSoftware_id(Long software_id) {
		this.software_id = software_id;
	}
}
