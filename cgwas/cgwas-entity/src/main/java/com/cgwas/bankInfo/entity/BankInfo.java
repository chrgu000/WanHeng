package com.cgwas.bankInfo.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_bank_info <br/>
 *         描述：银行信息表 <br/>
 */
@SuppressWarnings("serial")
public class BankInfo implements Serializable {
	protected Long id;// 主键
	protected String bank__name;// 银行名称
	protected String bank_num;// 银行账号
	protected String bank_ico;// 银行卡图标
	protected String bank_type;// 银行类型
	protected Integer sort;// 排序
	protected String preinstall_phone;// 预留手机号
	protected Long user_id;// 用户id
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date create_date;// 创建时间
	protected String status; // 状态

	public BankInfo() {
		super();
	}

	public BankInfo(Long id) {
		super();
		this.id = id;
	}

	@Id
	// 主键
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBank__name() {
		return bank__name;
	}

	public void setBank__name(String bank__name) {
		this.bank__name = bank__name;
	}

	public String getBank_num() {
		return bank_num;
	}

	public void setBank_num(String bank_num) {
		this.bank_num = bank_num;
	}

	public String getBank_ico() {
		return bank_ico;
	}

	public void setBank_ico(String bank_ico) {
		this.bank_ico = bank_ico;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getPreinstall_phone() {
		return preinstall_phone;
	}

	public void setPreinstall_phone(String preinstall_phone) {
		this.preinstall_phone = preinstall_phone;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
