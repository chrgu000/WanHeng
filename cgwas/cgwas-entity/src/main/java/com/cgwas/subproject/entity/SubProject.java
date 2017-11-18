package com.cgwas.subproject.entity;

import javax.persistence.Id;

import java.util.Date;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>
 *         s 表名： p_sub_project <br/>
 *         描述：子项目 <br/>
 */
@SuppressWarnings("serial")
public class SubProject implements Serializable {
	protected Long id;// 主键
	protected Long project_id;// 根父项目id
	protected Long sub_parent_project_id;// 父项目id
	protected Integer total;// 集数
	protected String project_status_id;// 项目状态
	protected String duration;// 时长
	protected String name;// 标题
	protected String cover_path;// 本集封面路径
	protected String introduce;// 本集简介
	protected String project_folder;// 项目文件夹
	protected String creater_name;// 创建者姓名
	protected Long user_id;// 创建者id
	protected Date create_time;// 创建时间
	protected String head_pic_path;// 创建者头像
	protected Date modified_time;// 最新修改时间
	protected Date begin_time;// 开始时间
	protected Date end_time;// 结束时间
	private String is_parent;// 是否有子项目
	private Long company_id;// 公司id
	protected Date actual_begin_time;//实际开始时间
	protected Date actual_end_time;//实际结束时间
	protected int usedDate;//实际用时

	public int getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(int usedDate) {
		this.usedDate = usedDate;
	}

	public Date getActual_begin_time() {
		return actual_begin_time;
	}

	public void setActual_begin_time(Date actual_begin_time) {
		this.actual_begin_time = actual_begin_time;
	}

	public Date getActual_end_time() {
		return actual_end_time;
	}

	public void setActual_end_time(Date actual_end_time) {
		this.actual_end_time = actual_end_time;
	}

	public String getHead_pic_path() {
		return head_pic_path;
	}

	public void setHead_pic_path(String head_pic_path) {
		this.head_pic_path = head_pic_path;
	}

	public Long getSub_parent_project_id() {
		return sub_parent_project_id;
	}

	public void setSub_parent_project_id(Long sub_parent_project_id) {
		this.sub_parent_project_id = sub_parent_project_id;
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public String getIs_parent() {
		return is_parent;
	}

	public void setIs_parent(String is_parent) {
		this.is_parent = is_parent;
	}

	public SubProject() {
		super();
	}

	public SubProject(Long id) {
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

	public Long getProject_id() {
		return project_id;
	}

	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getProject_status_id() {
		return project_status_id;
	}

	public void setProject_status_id(String project_status_id) {
		this.project_status_id = project_status_id;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCover_path() {
		return cover_path;
	}

	public void setCover_path(String cover_path) {
		this.cover_path = cover_path;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getProject_folder() {
		return project_folder;
	}

	public void setProject_folder(String project_folder) {
		this.project_folder = project_folder;
	}

	public String getCreater_name() {
		return creater_name;
	}

	public void setCreater_name(String creater_name) {
		this.creater_name = creater_name;
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

	public Date getModified_time() {
		return modified_time;
	}

	public void setModified_time(Date modified_time) {
		this.modified_time = modified_time;
	}

	public Date getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	@Override
	public String toString() {
		return "SubProject [id=" + id + ", project_id=" + project_id
				+ ", total=" + total + ", project_status_id="
				+ project_status_id + ", duration=" + duration + ", name="
				+ name + ", cover_path=" + cover_path + ", introduce="
				+ introduce + ", project_folder=" + project_folder
				+ ", creater_name=" + creater_name + ", user_id=" + user_id
				+ ", create_time=" + create_time + ", modified_time="
				+ modified_time + ", begin_time=" + begin_time + ", end_time="
				+ end_time + ", is_parent=" + is_parent + ", company_id="
				+ company_id + "]";
	}

}
