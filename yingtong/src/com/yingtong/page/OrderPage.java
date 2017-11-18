package com.yingtong.page;

public class OrderPage extends Page {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status;
	private String order_num;
	private Integer user_id;
    private String username;
    
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "OrderPage [status=" + status + ", order_num=" + order_num
				+ ", user_id=" + user_id + ", username=" + username + "]";
	}

}
