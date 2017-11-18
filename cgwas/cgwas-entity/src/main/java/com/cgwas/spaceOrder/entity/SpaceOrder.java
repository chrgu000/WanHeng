package com.cgwas.spaceOrder.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： z_space_order <br/>
 *         描述：空间订单表 <br/>
 */
@SuppressWarnings("serial")
public class SpaceOrder implements Serializable {
	protected Long id;// 主键
	protected Integer space_volume;// 空间容量
	protected Double price;// 价格
	protected String order_status;// 订单状态
	protected Long company_id;// 公司id
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date create_time;// 创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date effective_time;// 有效时间

	public SpaceOrder() {
		super();
	}

	public SpaceOrder(Long id) {
		super();
		this.id = id;
	}
	
	@Id// 主键
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getSpace_volume() {
		return space_volume;
	}
	public void setSpace_volume(Integer space_volume) {
		this.space_volume = space_volume;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getOrder_status() {
		return order_status;
	}
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}
	
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public Date getEffective_time() {
		return effective_time;
	}
	public void setEffective_time(Date effective_time) {
		this.effective_time = effective_time;
	}
}
