package com.to.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 租金
 */
@Data
public class Rent {
    private Integer id;
    private Integer price;//租金金额
    private Timestamp joinTime;//缴费时间
    private Integer compactId;//合同id
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getCompactId() {
		return compactId;
	}

	public void setCompactId(Integer compactId) {
		this.compactId = compactId;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public void setJoinTime(Timestamp joinTime) {
		this.joinTime = joinTime;
	}

	public String getJoinTime() {
        if(joinTime==null){
            return null;
        }else{
            return sdf.format(joinTime);
        }
    }

}
