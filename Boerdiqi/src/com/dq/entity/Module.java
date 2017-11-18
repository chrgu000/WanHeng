package com.dq.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 模版实体类
 * 
 * @author 杨俊
 * 
 */
public class Module implements Serializable {
	private String id;
	private String name;// 模版内容
	private String url;// 模版url
    private Integer mid;
    private Integer pmid;
    private Integer nflag;
    private List<Module> children;
 
	public List<Module> getChildren() {
		return children;
	}

	public void setChildren(List<Module> children) {
		this.children = children;
	}

	public Integer getPmid() {
		return pmid;
	}

	public void setPmid(Integer pmid) {
		this.pmid = pmid;
	}

	public Integer getNflag() {
		return nflag;
	}

	public void setNflag(Integer nflag) {
		this.nflag = nflag;
	}

	 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Module [children=" + children + ", id=" + id + ", mid=" + mid
				+ ", name=" + name + ", nflag=" + nflag + ", pmid=" + pmid
				+ ", url=" + url + "]";
	}

}
