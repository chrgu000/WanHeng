package com.jxc.entity;

import java.sql.Timestamp;


public class News {
private Integer id;
private String content;
private Timestamp createTime;
private String type;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Timestamp getCreateTime() {
	return createTime;
}
public void setCreateTime(Timestamp createTime) {
	this.createTime = createTime;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}

}
