package com.kg.entity;

import java.io.Serializable;

/**
 * 园区实体类
 * 
 * @author 杨俊
 * 
 */
public class Garden implements Serializable {
	private Integer id;
	private String name;// 园区名称
	private String introduce;// 园区简介
    private String code;
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Override
	public String toString() {
		return "Garden [id=" + id + ", introduce=" + introduce + ", name="
				+ name + "]";
	}

}
