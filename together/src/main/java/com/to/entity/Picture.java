package com.to.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 图片实体类
 * 
 * @author 杨俊
 * 
 */
@Data
public class Picture implements Serializable {
	private Integer id;
	private String data;
	private String imgUrl;// 图片路径
	private Timestamp createTime;// 上传时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
}
