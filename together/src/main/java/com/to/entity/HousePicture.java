package com.to.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/3/27 0027.
 * 房子图片
 */
@Data
public class HousePicture {
    private Integer id;
    private String imgUrl;//图片路径
    private String houseId;//房子id
    private String data;//图片十六进制码
    private Timestamp createTime;//创建时间
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public String getCreateTime() {
        if(createTime==null){
            return null;
        }else{
            return sdf.format(createTime);
        }
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
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
    
}
