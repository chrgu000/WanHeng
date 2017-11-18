package com.to.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 角色
 */
@Data
public class Role {
    private Short id;
    private String name;//角色名称
    private Timestamp createTime;//创建时间
    private Timestamp modifiedTime;//最新编辑时间
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Short getId() {
		return id;
	}



	public void setId(Short id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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



    public String  getModifiedTime() {
        if(modifiedTime==null){
            return  null;
        }else{
            return sdf.format(modifiedTime);

        }
    }


}
