package com.dq.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 购物车实体类
 * 
 * @author 杨俊
 * 
 */
public class ShopCar implements Serializable {
	private Integer shopcar_id;
	private Integer product_id;// 产品id
	private Product product;//产品对象
	private Integer sku_id;
	private Skuinfo skuinfo;
	private Timestamp join_time;// 添加购物车的时间
	private Integer number;// 产品数量

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

 

	 

	public Integer getShopcar_id() {
		return shopcar_id;
	}

	public void setShopcar_id(Integer shopcarId) {
		shopcar_id = shopcarId;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer productId) {
		product_id = productId;
	}

	public String getJoin_time() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(join_time==null)
			return null;
		return sdf.format(join_time);
	}

	public void setJoin_time(Timestamp joinTime) {
		join_time = joinTime;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "ShopCar [join_time=" + join_time + ", number=" + number
				+ ", product=" + product + ", product_id=" + product_id
				+ ", shopcar_id=" + shopcar_id + ", sku_id=" + sku_id
				+ ", skuinfo=" + skuinfo + "]";
	}

}
