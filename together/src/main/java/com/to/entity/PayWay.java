package com.to.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 付款方式
 */
@Data
public class PayWay {
    private Short id;
    private String content;//付款方式
    private Timestamp createTime;//创建时间
    private Timestamp modifiedTime;//最新编辑时间
    private Short num;//序号
    private Short number;
    private Short delflag;//删除标记，1为已删除2为未删除
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
	public Short getNumber() {
		return number;
	}
	public void setNumber(Short number) {
		this.number = number;
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

}
