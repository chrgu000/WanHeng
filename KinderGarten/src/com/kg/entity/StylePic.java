package com.kg.entity;

import java.io.Serializable;

public class StylePic implements Serializable{
private Integer id;
private String imgUrl;
private String min_path;
private Integer style_id;
private String create_time;


public String getMin_path() {
	return min_path;
}
public void setMin_path(String minPath) {
	min_path = minPath;
}
public String getCreate_time() {
	return create_time;
}
public void setCreate_time(String createTime) {
	create_time = createTime;
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
public Integer getStyle_id() {
	return style_id;
}
public void setStyle_id(Integer styleId) {
	style_id = styleId;
}
@Override
public String toString() {
	return "StylePic [create_time=" + create_time + ", id=" + id + ", imgUrl="
			+ imgUrl + ", min_path=" + min_path + ", style_id=" + style_id
			+ "]";
}

}
