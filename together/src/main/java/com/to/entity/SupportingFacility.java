package com.to.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 配套设施
 */
@Data
public class SupportingFacility {
    private Short id;
    private String content;//配套设施名称
    private Timestamp createTime;//添加时间
    private Timestamp modifiedTime;//最新编辑时间
    private Short delflag;//删除标记1为已删除 2为未删除
    private Short num;//序号
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public Short getId() {
		return id;
	}
	public void setId(Short id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Short getDelflag() {
		return delflag;
	}
	public void setDelflag(Short delflag) {
		this.delflag = delflag;
	}
	public Short getNum() {
		return num;
	}
	public void setNum(Short num) {
		this.num = num;
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
            return null;
        }else{
            return sdf.format(modifiedTime);
        }
    }

}
