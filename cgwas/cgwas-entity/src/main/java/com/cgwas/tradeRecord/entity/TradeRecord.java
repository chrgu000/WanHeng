package com.cgwas.tradeRecord.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 资金记录
 * 
 * @author Lingwh
 * 
 */
@SuppressWarnings("serial")
public class TradeRecord implements Serializable {
	protected Long id;// 主键
	protected Double trade_price;// 交易金额
	private String trade_order_no;// 交易订单号
	protected String trade_type;// 交易类型
	protected String trade_content;// 交易内容
	protected String trade_state;// 交易状态
	protected Long for_id;// 所属对象
	protected Long user_id;// 用户id
	protected String bank_num;//银行卡号
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date trade_time;// 交易时间

	public TradeRecord() {
		super();
	}

	public TradeRecord(Long id) {
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

	public Double getTrade_price() {
		return trade_price;
	}

	public void setTrade_price(Double trade_price) {
		this.trade_price = trade_price;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getTrade_content() {
		return trade_content;
	}

	public void setTrade_content(String trade_content) {
		this.trade_content = trade_content;
	}

	public Long getFor_id() {
		return for_id;
	}

	public void setFor_id(Long for_id) {
		this.for_id = for_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Date getTrade_time() {
		return trade_time;
	}

	public void setTrade_time(Date trade_time) {
		this.trade_time = trade_time;
	}

	public String getTrade_order_no() {
		return trade_order_no;
	}

	public void setTrade_order_no(String trade_order_no) {
		this.trade_order_no = trade_order_no;
	}

	public String getTrade_state() {
		return trade_state;
	}

	public void setTrade_state(String trade_state) {
		this.trade_state = trade_state;
	}

	public String getBank_num() {
		return bank_num;
	}

	public void setBank_num(String bank_num) {
		this.bank_num = bank_num;
	}
}
