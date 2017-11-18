package com.cgwas.space.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： z_space <br/>
 *         描述：空间表 <br/>
 */
@SuppressWarnings("serial")
public class Space implements Serializable {
	protected Long id;// 主键
	protected String space_title;// 标题
	protected Integer space_size;// 空间值
	protected Double space_price;// 空间价格
	protected String space_type;// 类型
	protected String space_content;// 详细介绍
	protected Integer effective_size;// effective_size
	protected String unit;// 单位
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected Date create_time;// 创建时间

	public Space() {
		super();
	}

	public Space(Long id) {
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
	
	public String getSpace_title() {
		return space_title;
	}
	public void setSpace_title(String space_title) {
		this.space_title = space_title;
	}
	
	public Integer getSpace_size() {
		return space_size;
	}
	public void setSpace_size(Integer space_size) {
		this.space_size = space_size;
	}
	
	public Double getSpace_price() {
		return space_price;
	}
	public void setSpace_price(Double space_price) {
		this.space_price = space_price;
	}
	
	public String getSpace_type() {
		return space_type;
	}
	public void setSpace_type(String space_type) {
		this.space_type = space_type;
	}
	
	public String getSpace_content() {
		return space_content;
	}
	public void setSpace_content(String space_content) {
		this.space_content = space_content;
	}
	
	public Integer getEffective_size() {
		return effective_size;
	}
	public void setEffective_size(Integer effective_size) {
		this.effective_size = effective_size;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
}
