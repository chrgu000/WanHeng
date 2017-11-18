package com.to.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 房子装修情况
 */
 
public class DecorateSituation {
    private Integer id;
    private String content;//装修情况
    private Timestamp createTime;//创建时间
    private Timestamp modifiedTime;//最新编辑时间
    private Short num;//序号
    private Short delflag;//删除标记1为删除，
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Short getNum() {
		return num;
	}
	public void setNum(Short num) {
		this.num = num;
	}
	public Short getDelflag() {
		return delflag;
	}
	public void setDelflag(Short delflag) {
		this.delflag = delflag;
	}
	public SimpleDateFormat getSdf() {
		return sdf;
	}
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getCreateTime() {
        if(createTime==null){
            return null;
        }else{
            return sdf.format(createTime);
        }
    }
    public String getModifiedTime() {
        if(modifiedTime==null){
            return  null;
        }else{
            return sdf.format(modifiedTime);
        }

    }
}
