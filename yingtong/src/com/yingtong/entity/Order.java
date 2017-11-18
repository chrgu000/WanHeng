package com.yingtong.entity;

import java.sql.Timestamp;

public class Order {
private Integer id;
private String order_num;//订单号
private Integer car_id;//车辆ID
private String buyTime;//取车时间
private String sendTime;//还车时间
private String buyAddr;//取车地址
private String sendAddr;//还车地址
private String status;//订单状态
private double total_price;//总价格
private Integer days;//租期
private double basic_rent_price;
private double basic_insure_price;
private double procedure_price;
private String invoice;//单位抬头
private String receiver;//收件人
private String tel;//联系电话
private String receiver_address;//收件地址
private Timestamp car_buy_time;//下单时间
private Integer user_id;//用户ID
private String pay_status;//支付状态
private String pay_num;//支付单号
private String pay_way;//支付方式
private Car car;
private User user;
private String openid;
private String urlCode;

/****** 微信支付参数 ******/
private String appId;
private String timeStamp;
private String nonceStr;
private String packageValue;
private String signType;
private String paySign;
private String sendUrl;
private String agent;
 
public String getOpenid() {
	return openid;
}
public void setOpenid(String openid) {
	this.openid = openid;
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
public String getAgent() {
	return agent;
}
public void setAgent(String agent) {
	this.agent = agent;
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
public void setOrder_num(String order_num) {
	this.order_num = order_num;
}
public Integer getCar_id() {
	return car_id;
}
public void setCar_id(Integer car_id) {
	this.car_id = car_id;
}
 
public String getBuyAddr() {
	return buyAddr;
}
public void setBuyAddr(String buyAddr) {
	this.buyAddr = buyAddr;
}
public String getSendAddr() {
	return sendAddr;
}
public void setSendAddr(String sendAddr) {
	this.sendAddr = sendAddr;
}
public Timestamp getBuyTime() {
	if(buyTime!=null&&!buyTime.trim().equals("")){
		return Timestamp.valueOf(buyTime);
	}
	return null;
}
public void setBuyTime(String buyTime) {
	this.buyTime = buyTime;
}
public Timestamp getSendTime() {
	if(sendTime!=null&&!sendTime.trim().equals("")){
		return Timestamp.valueOf(sendTime);
	}
	return null;
}
public void setSendTime(String sendTime) {
	this.sendTime = sendTime;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

  
public double getTotal_price() {
	return total_price;
}
public void setTotal_price(double total_price) {
	this.total_price = total_price;
}
public Integer getDays() {
	return days;
}
public void setDays(Integer days) {
	this.days = days;
}
public String getInvoice() {
	return invoice;
}
public void setInvoice(String invoice) {
	this.invoice = invoice;
}
public String getReceiver() {
	return receiver;
}
public void setReceiver(String receiver) {
	this.receiver = receiver;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getReceiver_address() {
	return receiver_address;
}
public void setReceiver_address(String receiver_address) {
	this.receiver_address = receiver_address;
}
 
public double getBasic_rent_price() {
	return basic_rent_price;
}
public void setBasic_rent_price(double basic_rent_price) {
	this.basic_rent_price = basic_rent_price;
}
public double getBasic_insure_price() {
	return basic_insure_price;
}
public void setBasic_insure_price(double basic_insure_price) {
	this.basic_insure_price = basic_insure_price;
}
public double getProcedure_price() {
	return procedure_price;
}
public void setProcedure_price(double procedure_price) {
	this.procedure_price = procedure_price;
}
public Timestamp getCar_buy_time() {
	return car_buy_time;
}
public void setCar_buy_time(Timestamp car_buy_time) {
	this.car_buy_time = car_buy_time;
}
public Integer getUser_id() {
	return user_id;
}
public void setUser_id(Integer user_id) {
	this.user_id = user_id;
}
public String getPay_status() {
	return pay_status;
}
public void setPay_status(String pay_status) {
	this.pay_status = pay_status;
}
public String getPay_num() {
	return pay_num;
}
public void setPay_num(String pay_num) {
	this.pay_num = pay_num;
}
public String getPay_way() {
	return pay_way;
}
public void setPay_way(String pay_way) {
	this.pay_way = pay_way;
}

public Car getCar() {
	return car;
}
public void setCar(Car car) {
	this.car = car;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}

 
public String getUrlCode() {
	return urlCode;
}
public void setUrlCode(String urlCode) {
	this.urlCode = urlCode;
}
@Override
public String toString() {
	return "Order [id=" + id + ", order_num=" + order_num + ", car_id="
			+ car_id + ", buyTime=" + buyTime + ", sendTime=" + sendTime
			+ ", status=" + status + ", total_price=" + total_price + ", days="
			+ days + ", basic_rent_price=" + basic_rent_price
			+ ", basic_insure_price=" + basic_insure_price
			+ ", procedure_price=" + procedure_price + ", invoice=" + invoice
			+ ", receiver=" + receiver + ", tel=" + tel + ", receiver_address="
			+ receiver_address + ", car_buy_time=" + car_buy_time
			+ ", user_id=" + user_id + ", pay_status=" + pay_status
			+ ", pay_num=" + pay_num + ", pay_way=" + pay_way + ", car=" + car
			+ ", user=" + user + ", openid=" + openid + ", urlCode=" + urlCode
			+ ", appId=" + appId + ", timeStamp=" + timeStamp + ", nonceStr="
			+ nonceStr + ", packageValue=" + packageValue + ", signType="
			+ signType + ", paySign=" + paySign + ", sendUrl=" + sendUrl
			+ ", agent=" + agent + "]";
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Order other = (Order) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}

}
