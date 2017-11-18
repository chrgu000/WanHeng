package com.dq.entity;

/**
 * 产品拥有的规格属性
 * 
 * @author Administrator
 *
 */
public class Proatt {
	private Integer paid;
	private Integer pid;
	private Integer att_id;
	private Integer sort;
	private String addtime;
	private Integer delflag = 1;

	private Att att;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Att getAtt() {
		return att;
	}

	public void setAtt(Att att) {
		this.att = att;
	}

 
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	 
	public Integer getPaid() {
		return paid;
	}

	public void setPaid(Integer paid) {
		this.paid = paid;
	}

	public Integer getAtt_id() {
		return att_id;
	}

	public void setAtt_id(Integer attId) {
		att_id = attId;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getDelflag() {
		return delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}
}
