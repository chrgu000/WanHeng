package com.dq.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
/**
 * 图片实体类
 * @author 杨俊
 *
 */
public class Picture implements Serializable {
private Integer id;
private Integer product_id;//商品id
private String imgUrl;//图片路径
private String type;//图片类型
private Timestamp join_time;

public String getJoin_time() {
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	if(join_time==null)
		return null;
	return sdf.format(join_time);
}
public void setJoin_time(Timestamp joinTime) {
	join_time = joinTime;
}
 
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getProduct_id() {
	return product_id;
}
public void setProduct_id(Integer productId) {
	product_id = productId;
}
public String getImgUrl() {
	return imgUrl;
}
public void setImgUrl(String imgUrl) {
	this.imgUrl = imgUrl;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
@Override
public String toString() {
	return "Picture [id=" + id + ", imgUrl=" + imgUrl + ", join_time="
			+ join_time + ", product_id=" + product_id + ", type=" + type + "]";
}

}
