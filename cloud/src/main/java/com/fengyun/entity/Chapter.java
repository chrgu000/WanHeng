package com.fengyun.entity;

import javax.persistence.Id;

import java.util.Date;
import java.util.Map;
import java.io.Serializable;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： y_chapter <br/>
 *         描述：y_chapter <br/>
 */
@SuppressWarnings("serial")
public class Chapter implements Serializable {
	protected Long id;// 主键
	protected Long course_id;// 课程id
	protected String chapter_name;// 章节名称
	protected String vedio_url;// 视频路径
	protected String course_content;// 文本课程内容
	protected Date apply_date;// 申请时间
	@Override
	public String toString() {
		return "Chapter [id=" + id + ", course_id=" + course_id
				+ ", chapter_name=" + chapter_name + ", vedio_url=" + vedio_url
				+ ", course_content=" + course_content + ", apply_date="
				+ apply_date + ", check_status=" + check_status
				+ ", check_idea=" + check_idea + ", course_length="
				+ course_length + ", delflag=" + delflag + ", search=" + search
				+ ", video_status=" + video_status + ", course=" + course
				+ ", order_num=" + order_num + ", union_site=" + union_site
				+ "]";
	}
	protected String check_status;// 审核状态(Y为通过,N为未通过,C为审核中)
	protected String check_idea;// 审核意见
	protected String course_length;// 课程时长
	protected String delflag;// 删除标记(Y为已删除N为未删除)
    protected Map<String,Object> search;
    protected String video_status;
    protected Course course;
    protected Integer order_num; //章节序号
    protected String union_site;
    
	 
	 

	public String getUnion_site() {
		return union_site;
	}

	public void setUnion_site(String union_site) {
		this.union_site = union_site;
	}

	public Integer getOrder_num() {
		return order_num;
	}

	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}

	public String getVideo_status() {
		return video_status;
	}

	public void setVideo_status(String video_status) {
		this.video_status = video_status;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Map<String, Object> getSearch() {
		return search;
	}

	public void setSearch(Map<String, Object> search) {
		this.search = search;
	}

	public Chapter() {
		super();
	}

	public Chapter(Long id) {
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
	
	public String getChapter_name() {
		return chapter_name;
	}
	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}
	
	public String getVedio_url() {
		return vedio_url;
	}
	public void setVedio_url(String vedio_url) {
		this.vedio_url = vedio_url;
	}
	
	public String getCourse_content() {
		return course_content;
	}
	public void setCourse_content(String course_content) {
		this.course_content = course_content;
	}
	
	public Date getApply_date() {
		return apply_date;
	}
	public void setApply_date(Date apply_date) {
		this.apply_date = apply_date;
	}
	
	public String getCheck_status() {
		return check_status;
	}
	public void setCheck_status(String check_status) {
		this.check_status = check_status;
	}
	
	public String getCheck_idea() {
		return check_idea;
	}
	public void setCheck_idea(String check_idea) {
		this.check_idea = check_idea;
	}
	
	 
	
	public String getCourse_length() {
		return course_length;
	}

	public void setCourse_length(String course_length) {
		this.course_length = course_length;
	}

	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
}
