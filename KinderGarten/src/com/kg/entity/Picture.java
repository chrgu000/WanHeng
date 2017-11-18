package com.kg.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 图片实体类
 * 
 * @author 杨俊
 * 
 */
public class Picture implements Serializable {
	private Integer id;
	private String path;// 图片路径
	private String min_path;
	private Integer type_id;// 图片类型id
	private PicType pictype;
	private Integer baby_id;// babyid
	private Baby baby;
	private Timestamp create_time;// 上传时间
	private String content;//图片标题
	

	public String getMin_path() {
		return min_path;
	}

	public void setMin_path(String minPath) {
		min_path = minPath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer typeId) {
		type_id = typeId;
	}

	public PicType getPictype() {
		return pictype;
	}

	public void setPictype(PicType pictype) {
		this.pictype = pictype;
	}

	public Integer getBaby_id() {
		return baby_id;
	}

	public void setBaby_id(Integer babyId) {
		baby_id = babyId;
	}

	public Baby getBaby() {
		return baby;
	}

	public void setBaby(Baby baby) {
		this.baby = baby;
	}

	public String getCreate_time() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (create_time == null)
			return null;
		return sdf.format(create_time);
	}

	public void setCreate_time(Timestamp createTime) {
		create_time = createTime;
	}

	@Override
	public String toString() {
		return "Picture [baby=" + baby + ", baby_id=" + baby_id + ", content="
				+ content + ", create_time=" + create_time + ", id=" + id
				+ ", min_path=" + min_path + ", path=" + path + ", pictype="
				+ pictype + ", type_id=" + type_id + "]";
	}

}
