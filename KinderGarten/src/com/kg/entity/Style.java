package com.kg.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class Style {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String content;
	private Date create_time;
	private String imgUrl;
	private Integer garden_id;
	private Integer teacher_id;
    private Garden garden;
    private Teacher teacher;
    private List<StylePic> stylePics;
    
	public List<StylePic> getStylePics() {
		return stylePics;
	}

	public void setStylePics(List<StylePic> stylePics) {
		this.stylePics = stylePics;
	}

	public Garden getGarden() {
		return garden;
	}

	public void setGarden(Garden garden) {
		this.garden = garden;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreate_time() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (create_time == null)
			return null;
		return sdf.format(create_time);
	}

	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getGarden_id() {
		return garden_id;
	}

	public void setGarden_id(Integer gardenId) {
		garden_id = gardenId;
	}

	public Integer getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Integer teacherId) {
		teacher_id = teacherId;
	}

	@Override
	public String toString() {
		return "Style [content=" + content + ", create_time=" + create_time
				+ ", garden=" + garden + ", garden_id=" + garden_id + ", id="
				+ id + ", imgUrl=" + imgUrl + ", teacher=" + teacher
				+ ", teacher_id=" + teacher_id + ", title=" + title + "]";
	}

}
