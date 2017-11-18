package com.to.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 价格
 */
@Data
public class Price {
    private Short id;
    private String content;//价格内容
    private Timestamp createTime;//添加时间
    private Timestamp modifiedTime;
    private Short num;
    private Short delflag;
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
            return null;
        }else{
            return sdf.format(modifiedTime);

        }
    }
}
