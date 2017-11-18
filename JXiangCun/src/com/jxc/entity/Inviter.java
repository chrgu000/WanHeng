package com.jxc.entity;

public class Inviter {
private Integer id;
private String open_id;
private String nickname;
private String url;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getOpen_id() {
	return open_id;
}
public void setOpen_id(String open_id) {
	this.open_id = open_id;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
@Override
public String toString() {
	return "Inviter [id=" + id + ", open_id=" + open_id + ", nickname="
			+ nickname + ", url=" + url + "]";
}

}
