package com.chat.entity;

import java.io.Serializable;

public class Member implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String real_name;// real_name
	protected String nickname;
	protected String head_pic_url;
	protected Long friend_id;// friend_id
	protected Long user_id;// user_id
	protected Long id;// id
	protected String apply_msg;// apply_msg
	protected String reply_msg;
	protected String gender;
	protected String is_agree;
	protected String reply_agree;
	protected String state;//在线离线状态
	protected Long unReadNum;
	protected String progress_state;
	
	
	public String getProgress_state() {
		return progress_state;
	}

	public void setProgress_state(String progress_state) {
		this.progress_state = progress_state;
	}

	public Long getUnReadNum() {
		return unReadNum;
	}

	public void setUnReadNum(Long unReadNum) {
		this.unReadNum = unReadNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReply_agree() {
		return reply_agree;
	}

	public void setReply_agree(String reply_agree) {
		this.reply_agree = reply_agree;
	}

	 
	protected String tel;
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIs_agree() {
		return is_agree;
	}

	public void setIs_agree(String is_agree) {
		this.is_agree = is_agree;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getReply_msg() {
		return reply_msg;
	}

	public void setReply_msg(String reply_msg) {
		this.reply_msg = reply_msg;
	}

	public Member(String real_name, String nickname) {
		super();
		this.real_name = real_name;
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHead_pic_url() {
		return head_pic_url;
	}

	public void setHead_pic_url(String head_pic_url) {
		this.head_pic_url = head_pic_url;
	}

	public Member() {
		super();
	}

	public Member(Long id) {
		super();
		this.id = id;
	}
	
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	public Long getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(Long friend_id) {
		this.friend_id = friend_id;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getApply_msg() {
		return apply_msg;
	}
	public void setApply_msg(String apply_msg) {
		this.apply_msg = apply_msg;
	}

	@Override
	public String toString() {
		return "Member [real_name=" + real_name + ", nickname=" + nickname
				+ ", head_pic_url=" + head_pic_url + ", friend_id=" + friend_id
				+ ", user_id=" + user_id + ", id=" + id + ", apply_msg="
				+ apply_msg + ", reply_msg=" + reply_msg + ", gender=" + gender
				+ ", is_agree=" + is_agree + ", reply_agree=" + reply_agree
				+ ", state=" + state + ", unReadNum=" + unReadNum + ", tel="
				+ tel + "]";
	}

}
