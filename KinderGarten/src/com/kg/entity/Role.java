package com.kg.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 角色实体类
 * 
 * @author 杨俊
 * 
 */
public class Role implements Serializable {
	private Integer id;
	private String name;// 角色名称
    private List<Module> modules;
    
	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
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

	@Override
	public String toString() {
		return "Role [id=" + id + ", modules=" + modules + ", name=" + name
				+ "]";
	}

}
