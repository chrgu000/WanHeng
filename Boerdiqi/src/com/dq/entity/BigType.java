package com.dq.entity;

import java.io.Serializable;
/**
 * 大分类实体类
 * @author 杨俊
 *
 */
public class BigType implements Serializable {
private Integer id;
private String name;//商品类别名称
private Integer num;//排序序号
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getNum() {
	return num;
}
public void setNum(Integer num) {
	this.num = num;
}
@Override
public String toString() {
	return "BigType [id=" + id + ", name=" + name + ", num=" + num + "]";
}

}
