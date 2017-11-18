package com.dq.page;

public class OrderPage extends Page {
private String nickname;
private String status;
private Integer user_id;

public Integer getUser_id() {
	return user_id;
}
public void setUser_id(Integer userId) {
	user_id = userId;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@Override
public String toString() {
	return "OrderPage [nickname=" + nickname + ", status=" + status
			+ ", user_id=" + user_id + "]";
}

	 

}
