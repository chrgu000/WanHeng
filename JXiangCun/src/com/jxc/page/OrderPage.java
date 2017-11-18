package com.jxc.page;

public class OrderPage extends Page {
	private String status;
	private String order_num;
	private Integer user_id;
    private String username;
    private String isFree;
    private String tel;
    private Integer title_id;
    
	public Integer getTitle_id() {
		return title_id;
	}

	public void setTitle_id(Integer title_id) {
		this.title_id = title_id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
				+ ", user_id=" + user_id + ", username=" + username
				+ ", isFree=" + isFree + ", tel=" + tel + ", title_id="
				+ title_id + "]";
	}

}
