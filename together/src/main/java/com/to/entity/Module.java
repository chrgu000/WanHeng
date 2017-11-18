package com.to.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 角色模版
 */
@Data
public class Module {
    private Short mid;
    private String name;//模版名称
    private String url;//模版路径
    private String id;
    private Short pmid;//父模版id
    private Short nflag;//模版级别
    private Short num;//序号
    private List<Module> children;
	public Short getMid() {
		return mid;
	}
	public void setMid(Short mid) {
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Short getPmid() {
		return pmid;
	}
	public void setPmid(Short pmid) {
		this.pmid = pmid;
	}
	public Short getNflag() {
		return nflag;
	}
	public void setNflag(Short nflag) {
		this.nflag = nflag;
	}
	public Short getNum() {
		return num;
	}
	public void setNum(Short num) {
		this.num = num;
	}
	public List<Module> getChildren() {
		return children;
	}
	public void setChildren(List<Module> children) {
		this.children = children;
	}
    
}
