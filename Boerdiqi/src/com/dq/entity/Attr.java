package com.dq.entity;

/**
 * 规格属性
 * 
 * @author Administrator
 *
 */
public class Attr {
	private Integer attr_id;
	private Integer att_id;
	private String v;
	private String addtime;
	private Integer delflag = 1;
	private boolean check = false;

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public Integer getAttr_id() {
		return attr_id;
	}

	public void setAttr_id(Integer attrId) {
		attr_id = attrId;
	}

	public Integer getAtt_id() {
		return att_id;
	}

	public void setAtt_id(Integer attId) {
		att_id = attId;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
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
