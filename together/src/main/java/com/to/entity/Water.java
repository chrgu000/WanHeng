package com.to.entity;

import lombok.Data;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 水费
 */
@Data
public class Water {
    private Integer id;
    private Double price;//水费金额
    private Date joinTime;//缴费时间
    private Integer compactId;//合同id
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    
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


	public void setId(Integer id) {
		this.id = id;
	}


	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}


	public Integer getId() {
        return id;
    }


    public String getJoinTime() {
        if(joinTime==null){
            return null;
        }else{
            return sdf.format(joinTime);
        }
    }
}
