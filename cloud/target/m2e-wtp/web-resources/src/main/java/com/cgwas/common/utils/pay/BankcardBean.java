package com.cgwas.common.utils.pay;

public class BankcardBean {
	private String ret_code;

    private String ret_msg;
    private String sign_type;
	private String sign;
	
	private String card_no; //银行卡号
	private String bank_code; //银行编号
	private String bank_name; // 银行名字
	private String  card_type; //银行卡类型 2-储蓄卡 3-信用卡
	private Long bank_detail_id;
	
   

	public Long getBank_detail_id() {
		return bank_detail_id;
	}

	public void setBank_detail_id(Long bank_detail_id) {
		this.bank_detail_id = bank_detail_id;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getRet_code()
    {
        return ret_code;
    }

    public void setRet_code(String ret_code)
    {
        this.ret_code = ret_code;
    }

    public String getRet_msg()
    {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg)
    {
        this.ret_msg = ret_msg;
    }
	
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	
}
