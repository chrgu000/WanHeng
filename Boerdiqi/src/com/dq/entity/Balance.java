package com.dq.entity;

public class Balance {
private Integer id;
private Integer user_id;//用户id
private User user;
private String news;//交易详情
private String join_time;//交易时间
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getUser_id() {
	return user_id;
}
public void setUser_id(Integer userId) {
	user_id = userId;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public String getNews() {
	return news;
}
public void setNews(String news) {
	this.news = news;
}
public String getJoin_time() {
	return join_time;
}
public void setJoin_time(String joinTime) {
	join_time = joinTime;
}
@Override
public String toString() {
	return "Balance [id=" + id + ", join_time=" + join_time + ", news=" + news
			+ ", user=" + user + ", user_id=" + user_id + "]";
}


}
