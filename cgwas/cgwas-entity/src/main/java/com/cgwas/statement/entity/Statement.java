package com.cgwas.statement.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： s_statement <br/>
 *         描述：s_statement <br/>
 */
@SuppressWarnings("serial")
public class Statement implements Serializable {
	protected Long id;// 流水id
	protected String money_type;// 流水币种
	protected String bus_type;// bus_type
	protected Double money;// money
	protected Double balance_money;// balance_money
	protected String status;// 流水状态
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date create_date;// 创建日期
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date finish_date;// 到账日期

	protected Long user_id; // 用户id

	protected String bank_num; // 银行卡号

	public Statement() {
		super();
	}

	public Statement(Long id) {
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

	public String getMoney_type() {
		return money_type;
	}

	public void setMoney_type(String money_type) {
		this.money_type = money_type;
	}

	public String getBus_type() {
		return bus_type;
	}

	public void setBus_type(String bus_type) {
		this.bus_type = bus_type;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getBalance_money() {
		return balance_money;
	}

	public void setBalance_money(Double balance_money) {
		this.balance_money = balance_money;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getFinish_date() {
		return finish_date;
	}

	public void setFinish_date(Date finish_date) {
		this.finish_date = finish_date;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getBank_num() {
		return bank_num;
	}

	public void setBank_num(String bank_num) {
		this.bank_num = bank_num;
	}

}
