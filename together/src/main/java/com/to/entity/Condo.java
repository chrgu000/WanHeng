package com.to.entity;

import lombok.Data;


import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 物业费
 */
 
    public class Condo {
    private Integer id;
    private Double price;//物业费金额
    private Date joinTime;//缴费时间
    private Integer compactId;//合同id
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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

	public void setJoinTime(Date joinTime) {
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
