package com.kg.entity;

import java.io.Serializable;

/**
 * 图片类型实体类
 * 
 * @author 杨俊
 * 
 */
public class PicType implements Serializable {
	private Integer id;
	private String type;// 类型

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "PicType [id=" + id + ", type=" + type + "]";
	}

}
