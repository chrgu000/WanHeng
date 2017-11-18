package com.dq.entity;

/**
 * 产品拥有的规格属性
 * 
 * @author Administrator
 *
 */
public class Proattr {
	private Integer parid;
	private Integer pid;
	private Integer att_id;
	private Integer attr_id;
	private String addtime;
	private Integer delflag = 1;

 
 
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getParid() {
		return parid;
	}

	public void setParid(Integer parid) {
		this.parid = parid;
	}

	public Integer getAtt_id() {
		return att_id;
	}

	public void setAtt_id(Integer attId) {
		att_id = attId;
	}

	public Integer getAttr_id() {
		return attr_id;
	}

	public void setAttr_id(Integer attrId) {
		attr_id = attrId;
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
