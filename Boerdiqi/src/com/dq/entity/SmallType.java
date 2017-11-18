package com.dq.entity;

import java.io.Serializable;
/**
 * 小类别实体类
 * @author 杨俊
 *
 */
public class SmallType implements Serializable {
private Integer id;
private String name;//小分类名称
private Integer big_type_id;//大分类id
private BigType bigtype;
private Integer num;//排列序号
private String isHot;//是否热门
public Integer getId() {
	return id;
}
public BigType getBigtype() {
	return bigtype;
}
public void setBigtype(BigType bigtype) {
	this.bigtype = bigtype;
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
public Integer getBig_type_id() {
	return big_type_id;
}
public void setBig_type_id(Integer bigTypeId) {
	big_type_id = bigTypeId;
}
public Integer getNum() {
	return num;
}
public void setNum(Integer num) {
	this.num = num;
}
public String getIsHot() {
	return isHot;
}
public void setIsHot(String isHot) {
	this.isHot = isHot;
}
@Override
public String toString() {
	return "SmallType [big_type_id=" + big_type_id + ", bigtype=" + bigtype
			+ ", id=" + id + ", isHot=" + isHot + ", name=" + name + ", num="
			+ num + "]";
}

}
