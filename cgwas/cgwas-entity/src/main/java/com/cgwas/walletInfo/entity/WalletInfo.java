package com.cgwas.walletInfo.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_wallet_info <br/>
 *         描述：钱包信息表 <br/>
 */
@SuppressWarnings("serial")
public class WalletInfo implements Serializable {
	protected Long id;// 主键
	protected Double remaining_sum;// 余额
	protected String currency_type;// 货币种类
	protected Long user_id;// 用户id
	protected String pay_password; // 支付密码
	protected String status; // 钱包状态

	public WalletInfo() {
		super();
	}

	public WalletInfo(Long id) {
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

	public Double getRemaining_sum() {
		return remaining_sum;
	}

	public void setRemaining_sum(Double remaining_sum) {
		this.remaining_sum = remaining_sum;
	}

	public String getCurrency_type() {
		return currency_type;
	}

	public void setCurrency_type(String currency_type) {
		this.currency_type = currency_type;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getPay_password() {
		return pay_password;
	}

	public void setPay_password(String pay_password) {
		this.pay_password = pay_password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
