package com.cgwas.space.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： z_space <br/>
 *         描述：空间表 <br/>
 */
 @SuppressWarnings("serial")
 @JsonInclude(value=Include.NON_NULL) 
public class SpaceVo extends Space {
	private String discount_price;//折扣价格
	public SpaceVo() {
		super();
	}

	public SpaceVo(Long id) {
		super();
		this.id = id;
	}

	public String getDiscount_price() {
		return discount_price;
	}

	public void setDiscount_price(String discount_price) {
		this.discount_price = discount_price;
	}


}
