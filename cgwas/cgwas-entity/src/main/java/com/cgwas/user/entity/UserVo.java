package com.cgwas.user.entity;

/**
 * @author 模版生成 <br/>
 *         表名： u_user <br/>
 *         描述：用户表 <br/>
 */
@SuppressWarnings("serial")
public class UserVo extends User {
	protected String name;

	public UserVo() {
		super();
	}

	public UserVo(Long id) {
		super();
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
