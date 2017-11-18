package com.cgwas.repairimage.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author yangjun <br/>s
 *         表名： p_repair_image <br/>
 *         描述：返修图片表 <br/>
 */
@SuppressWarnings("serial")
public class RepairImage implements Serializable {
	protected Long id;// 主键
	protected String advice_path;// 图片路径
	protected Long repair_id;// 任务id

	public RepairImage() {
		super();
	}

	public RepairImage(Long id) {
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
	
	public String getAdvice_path() {
		return advice_path;
	}
	public void setAdvice_path(String advice_path) {
		this.advice_path = advice_path;
	}
	
	public Long getRepair_id() {
		return repair_id;
	}
	public void setRepair_id(Long repair_id) {
		this.repair_id = repair_id;
	}
}
