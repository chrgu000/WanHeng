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
public class IntegralOrder implements Serializable {
	private Integer id;
	private String order_num;// 订单号
	private Timestamp sendtimes;// 快递时间
	private Timestamp join_time;// 下单时间
	private String status;// 订单状态
	private String address;// 收件人地址
	private Integer delflag;
	private String youbian;// 邮编
	private String tel;// 收件人联系电话
	private String name;// 收件人姓名
	private Integer user_id;// 买家id
	private User user;// 买家对象
	private String express;// 快递号
	private String code;// 物流编码
	private Integer num;//产品数量
	private Integer integral;//商品兑换所有积分
	private Integer integral_product_id;
	private IntegralProduct integralProduct;
    private String message;
    
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Integer getIntegral_product_id() {
		return integral_product_id;
	}

	public void setIntegral_product_id(Integer integralProductId) {
		integral_product_id = integralProductId;
	}

	public IntegralProduct getIntegralProduct() {
		return integralProduct;
	}

	public void setIntegralProduct(IntegralProduct integralProduct) {
		this.integralProduct = integralProduct;
	}

	public Integer getDelflag() {
		return delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (join_time == null)
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

	public String getSendtimes() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (sendtimes == null)
			return null;
		return sdf.format(sendtimes);
	}

	public void setSendtimes(Timestamp sendtimes) {
		this.sendtimes = sendtimes;
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

	@Override
	public String toString() {
		return "IntegralOrder [address=" + address + ", code=" + code
				+ ", delflag=" + delflag + ", express=" + express + ", id="
				+ id + ", integral=" + integral + ", integralProduct="
				+ integralProduct + ", integral_product_id="
				+ integral_product_id + ", join_time=" + join_time
				+ ", message=" + message + ", name=" + name + ", num=" + num
				+ ", order_num=" + order_num + ", sendtimes=" + sendtimes
				+ ", status=" + status + ", tel=" + tel + ", user=" + user
				+ ", user_id=" + user_id + ", youbian=" + youbian + "]";
	}

}
