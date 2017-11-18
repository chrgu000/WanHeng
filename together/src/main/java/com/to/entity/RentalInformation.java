package com.to.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 新闻资讯
 */
@Data
public class RentalInformation {
    private Integer id;
    private String title;
    private String content;//资讯内容
    private Timestamp createTime;//添加时间
    private Timestamp modifiedTime;//最新编辑时间
    private Short delflag;//删除标记1为删除 2为未删除
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public SimpleDateFormat getSdf() {
		return sdf;
	}
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public Integer getId() {
        return id;
    }
    public String getCreateTime() {
        if(createTime==null){
            return  null;
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
