package com.dq.entity;

import java.util.List;

/**
 * 商品规格
 * 
 * @author Administrator
 *
 */
public class Att {
	private Integer att_id;
	private String attr_title;
	private String addtime;
	private Integer delflag = 1;
	private boolean check = false;

	private List<Attr> attr_vs;

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public List<Attr> getAttr_vs() {
		return attr_vs;
	}

	public void setAttr_vs(List<Attr> attr_vs) {
		this.attr_vs = attr_vs;
	}

	public Integer getAtt_id() {
		return att_id;
	}

	public void setAtt_id(Integer attId) {
		att_id = attId;
	}

	public String getAttr_title() {
		return attr_title;
	}

	public void setAttr_title(String attr_title) {
		this.attr_title = attr_title;
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
