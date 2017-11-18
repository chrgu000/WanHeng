package com.dq.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * 订单实体类
 * 
 * @author 杨俊
 * 
 */
public class GroupOrder implements Serializable {
	private Integer id;
	private String order_num;// 订单号
	private Timestamp sendtimes;//快递时间
	private Timestamp paytimes;//支付时间
	private Timestamp join_time;// 下单时间
	private String status;// 订单状态
	private double total_price;//实付款
	private double mail;//邮费
	private Integer user_ticket_id;//用户所用优惠券id
	private String address;//收件人地址
	private Ticket ticket;
	private Integer delflag;
	private String youbian;//邮编
	private String tel;//收件人联系电话
	private String name;//收件人姓名
	private Integer user_id;// 买家id
	private User user;//买家对象
	private String express;//快递号
	private String code;//物流编码
	private String payType;//支付类型
	private String message;
	private String appId;// 微信支付相关参数
	private String timeStamp;
	private String nonceStr;
	private String packageValue;
	private String signType;
	private String paySign;
	private String sendUrl;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double totalPrice) {
		total_price = totalPrice;
	}

	public Integer getDelflag() {
		return delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


	  
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String orderNum) {
		order_num = orderNum;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

 
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer userId) {
		user_id = userId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageValue() {
		return packageValue;
	}

	public void setPackageValue(String packageValue) {
		this.packageValue = packageValue;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String getSendUrl() {
		return sendUrl;
	}

	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl;
	}
 
	public String getSendtimes() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(sendtimes==null)
			return null;
		return sdf.format(sendtimes);
	}

	public void setSendtimes(Timestamp sendtimes) {
		this.sendtimes = sendtimes;
	}

	public String getPaytimes() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(paytimes==null)
			return null;
		return sdf.format(paytimes);
	}

	public void setPaytimes(Timestamp paytimes) {
		this.paytimes = paytimes;
	}

	public double getMail() {
		return mail;
	}

	public void setMail(double mail) {
		this.mail = mail;
	}

	public Integer getUser_ticket_id() {
		return user_ticket_id;
	}

	public void setUser_ticket_id(Integer userTicketId) {
		user_ticket_id = userTicketId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getYoubian() {
		return youbian;
	}

	public void setYoubian(String youbian) {
		this.youbian = youbian;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Override
	public String toString() {
		return "GroupOrder [address=" + address + ", appId=" + appId
				+ ", code=" + code + ", delflag=" + delflag + ", express="
				+ express + ", id=" + id + ", join_time=" + join_time
				+ ", mail=" + mail + ", message=" + message + ", name=" + name
				+ ", nonceStr=" + nonceStr + ", order_num=" + order_num
				+ ", packageValue=" + packageValue + ", paySign=" + paySign
				+ ", payType=" + payType + ", paytimes=" + paytimes
				+ ", sendUrl=" + sendUrl + ", sendtimes=" + sendtimes
				+ ", signType=" + signType + ", status=" + status + ", tel="
				+ tel + ", ticket=" + ticket + ", timeStamp=" + timeStamp
				+ ", total_price=" + total_price + ", user=" + user
				+ ", user_id=" + user_id + ", user_ticket_id=" + user_ticket_id
				+ ", youbian=" + youbian + "]";
	}

}
