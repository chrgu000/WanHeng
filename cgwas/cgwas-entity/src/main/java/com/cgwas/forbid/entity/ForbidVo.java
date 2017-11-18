package com.cgwas.forbid.entity;

/**
 * 用户禁止拓展实体类
 * 
 * @author Lingwh
 * 
 */
@SuppressWarnings("serial")
public class ForbidVo extends Forbid {
	protected Long num;

	public ForbidVo() {
		super();
	}

	public ForbidVo(Long id) {
		super();
		this.id = id;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

}
