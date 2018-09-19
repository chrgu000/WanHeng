package com.chat.entity;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable{
	private Long id;
	private Date time;
	private String nickname;
	private Long chapter_id;
	private Long course_id;
	private String message;
	private Long friend_id;
	private Long user_id;
	private String readstate;
	private Long group_id;
	
	public Long getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Long group_id) {
		this.group_id = group_id;
	}
	public String getReadstate() {
		return readstate;
	}
	public void setReadstate(String readstate) {
		this.readstate = readstate;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", time=" + time + ", nickname="
				+ nickname + ", chapter_id=" + chapter_id + ", course_id="
				+ course_id + ", message=" + message + ", friend_id="
				+ friend_id + ", user_id=" + user_id + ", readstate="
				+ readstate + ", group_id=" + group_id + "]";
	}
	public Long getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(Long friend_id) {
		this.friend_id = friend_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Long getChapter_id() {
		return chapter_id;
	}
	public void setChapter_id(Long chapter_id) {
		this.chapter_id = chapter_id;
	}
	public Long getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
