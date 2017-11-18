package com.dq.entity;

import java.io.Serializable;
/**
 * 订单实体类
 * 
 * @author 杨俊
 * 
 */
public class Orders implements Serializable{
private Integer id;
private Integer order_group_id;
private GroupOrder grouporder;
private Integer product_id;//产品id
private Product product;
private Integer num;//数量
private Integer sku_id;
private Skuinfo skuinfo;

public Integer getSku_id() {
	return sku_id;
}
public void setSku_id(Integer skuId) {
	sku_id = skuId;
}
public Skuinfo getSkuinfo() {
	return skuinfo;
}
public void setSkuinfo(Skuinfo skuinfo) {
	this.skuinfo = skuinfo;
}
public GroupOrder getGrouporder() {
	return grouporder;
}
public void setGrouporder(GroupOrder grouporder) {
	this.grouporder = grouporder;
}
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getOrder_group_id() {
	return order_group_id;
}
public void setOrder_group_id(Integer orderGroupId) {
	order_group_id = orderGroupId;
}
public Integer getProduct_id() {
	return product_id;
}
public void setProduct_id(Integer productId) {
	product_id = productId;
}
public Integer getNum() {
	return num;
}
public void setNum(Integer num) {
	this.num = num;
}
@Override
public String toString() {
	return "Orders [grouporder=" + grouporder + ", id=" + id + ", num=" + num
			+ ", order_group_id=" + order_group_id + ", product=" + product
			+ ", product_id=" + product_id + "]";
}

}
