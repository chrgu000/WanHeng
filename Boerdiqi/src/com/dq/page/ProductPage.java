package com.dq.page;

public class ProductPage extends Page {
private String name;
private Integer big_type_id;
private Integer small_type_id;
private Integer standard_id;
private Integer isOnline;
private String status;

public Integer getStandard_id() {
	return standard_id;
}
public void setStandard_id(Integer standardId) {
	standard_id = standardId;
}
public Integer getBig_type_id() {
	return big_type_id;
}
public void setBig_type_id(Integer bigTypeId) {
	big_type_id = bigTypeId;
}
public Integer getSmall_type_id() {
	return small_type_id;
}
public void setSmall_type_id(Integer smallTypeId) {
	small_type_id = smallTypeId;
}
public Integer getIsOnline() {
	return isOnline;
}
public void setIsOnline(Integer isOnline) {
	this.isOnline = isOnline;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
public String toString() {
	return "ProductPage [big_type_id=" + big_type_id + ", isOnline=" + isOnline
			+ ", name=" + name + ", small_type_id=" + small_type_id
			+ ", standard_id=" + standard_id + ", status=" + status + "]";
}
}
