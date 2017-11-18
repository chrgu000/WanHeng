package com.cgwas.project.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Id;

/**
 * @author yangjun <br/>
 *         表名： p_project <br/>
 *         描述：项目表 <br/>
 */
@SuppressWarnings("serial")
public class Project implements Serializable {
	protected Long id;// 主键
	protected String name;// 项目名称
	protected String project_no;// 项目代号
	protected Long project_status_id;// 项目状态
	protected Long project_type_id;// 项目类型
	protected String cover_path;// 项目封面路径
	protected String introduce;// 项目简介
	protected String color;//项目颜色
	protected Integer total;// 项目总集数
	protected String  frame_rate;// 帧数率
	protected String resolution;// 分辨率
	protected String project_folder;// 项目文件夹
	protected Long budget;// 项目预算
	protected Date begin_time;// 开始日期
	protected Date end_time;// 结束日期
	protected String bullet_films;// 出品公司
	protected String creater_name;// 创建者姓名
	protected Long user_id;// 创建者id
	protected String head_pic_path;//创建者头像
	protected Long company_id;// 所属公司id
	protected Date create_time;// 创建时间
	protected Date modified_time;// 最新编辑时间
	protected int usedDate;//实际用时
    protected String slider_img;//轮播图
    protected String show_img;//展示图
    protected String recommend_img;//推荐图
    
	public String getSlider_img() {
		return slider_img;
	}

	public void setSlider_img(String slider_img) {
		this.slider_img = slider_img;
	}

	public String getShow_img() {
		return show_img;
	}

	public void setShow_img(String show_img) {
		this.show_img = show_img;
	}

	public String getRecommend_img() {
		return recommend_img;
	}

	public void setRecommend_img(String recommend_img) {
		this.recommend_img = recommend_img;
	}

	public int getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(int usedDate) {
		this.usedDate = usedDate;
	}
	public String getHead_pic_path() {
		return head_pic_path;
	}

	public void setHead_pic_path(String head_pic_path) {
		this.head_pic_path = head_pic_path;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", project_no="
				+ project_no + ", project_status_id=" + project_status_id
				+ ", project_type_id=" + project_type_id + ", cover_path="
				+ cover_path + ", introduce=" + introduce + ", color=" + color
				+ ", total=" + total + ", frame_rate=" + frame_rate
				+ ", resolution=" + resolution + ", project_folder="
				+ project_folder + ", budget=" + budget + ", begin_time="
				+ begin_time + ", end_time=" + end_time + ", bullet_films="
				+ bullet_films + ", creater_name=" + creater_name
				+ ", user_id=" + user_id + ", company_id=" + company_id
				+ ", create_time=" + create_time + ", modified_time="
				+ modified_time + "]";
	}

	public Project() {
		super();
	}

	public Project(Long id) {
		super();
		this.id = id;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Id// 主键
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProject_no() {
		return project_no;
	}
	public void setProject_no(String project_no) {
		this.project_no = project_no;
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
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public Long getProject_status_id() {
		return project_status_id;
	}

	public void setProject_status_id(Long project_status_id) {
		this.project_status_id = project_status_id;
	}

	public Long getProject_type_id() {
		return project_type_id;
	}

	public void setProject_type_id(Long project_type_id) {
		this.project_type_id = project_type_id;
	}

 

	public String getFrame_rate() {
		return frame_rate;
	}

	public void setFrame_rate(String frame_rate) {
		this.frame_rate = frame_rate;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getProject_folder() {
		return project_folder;
	}
	public void setProject_folder(String project_folder) {
		this.project_folder = project_folder;
	}
	
	public Long getBudget() {
		return budget;
	}

	public void setBudget(Long budget) {
		this.budget = budget;
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
	
	public String getBullet_films() {
		return bullet_films;
	}
	public void setBullet_films(String bullet_films) {
		this.bullet_films = bullet_films;
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
	
	public Date getModified_time() {
		return modified_time;
	}
	public void setModified_time(Date modified_time) {
		this.modified_time = modified_time;
	}
}
