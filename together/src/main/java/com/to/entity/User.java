package com.to.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 用户
 */
@Data
public class User {
    private Integer id;
    private String tel;//手机号
    private String password;//登录密码
    private Timestamp joinTime;//注册时间
    private Timestamp modifiedTime;//最新更改时间
    private Short delflag;
    
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Short getDelflag() {
		return delflag;
	}
	public void setDelflag(Short delflag) {
		this.delflag = delflag;
	}
	public void setJoinTime(Timestamp joinTime) {
		this.joinTime = joinTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getJoinTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(joinTime==null){
            return null;
        }else{
            return sdf.format(joinTime);
        }
    }
    public String getModifiedTime() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(modifiedTime==null){
            return null;
        }else{
            return sdf.format(modifiedTime);

        }
    }
}
