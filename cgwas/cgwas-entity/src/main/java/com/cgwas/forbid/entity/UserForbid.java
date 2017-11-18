package com.cgwas.forbid.entity;

import java.util.Date;

public class UserForbid {
	protected String nickname; // 昵称
	protected String content; // 禁用内容
	protected String reason; // 禁用原因
	protected Date creat_time; // 创建时间
	protected String baner; // 禁用人
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getCreat_time() {
		return creat_time;
	}

	public void setCreat_time(Date creat_time) {
		this.creat_time = creat_time;
	}

	public String getBaner() {
		return baner;
	}

	public void setBaner(String baner) {
		this.baner = baner;
	}

}
