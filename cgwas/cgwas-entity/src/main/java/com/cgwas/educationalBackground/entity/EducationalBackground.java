package com.cgwas.educationalBackground.entity;



import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_educational_background <br/>
 *         描述：教育背景 <br/>
 */
@SuppressWarnings("serial")
public class EducationalBackground implements Serializable {
	protected Long id;// 主键
	@DateTimeFormat(pattern="yyyy-MM-dd")
	protected Date academy_begin_date;// 入校日期
	@DateTimeFormat(pattern="yyyy-MM-dd")
	protected Date academy_end_date;// 毕业日期
	protected String academy_name;// 学校名称
	protected String department;// 科/系
	protected String degree;// 学历
	protected Long user_id;// user_id
	@DateTimeFormat(pattern="yyyy-MM-dd")
	protected Date create_time;// 创建时间
	public EducationalBackground() {
		super();
	}

	public EducationalBackground(Long id) {
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
	
	public Date getAcademy_begin_date() {
		return academy_begin_date;
	}
	public void setAcademy_begin_date(Date academy_begin_date) {
		this.academy_begin_date = academy_begin_date;
	}
	
	public Date getAcademy_end_date() {
		return academy_end_date;
	}
	public void setAcademy_end_date(Date academy_end_date) {
		this.academy_end_date = academy_end_date;
	}
	
	public String getAcademy_name() {
		return academy_name;
	}
	public void setAcademy_name(String academy_name) {
		this.academy_name = academy_name;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
