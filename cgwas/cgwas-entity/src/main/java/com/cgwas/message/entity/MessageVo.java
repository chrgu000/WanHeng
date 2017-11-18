package com.cgwas.message.entity;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 消息实体拓展类
 * 
 * @author Lingwh
 * 
 */
@SuppressWarnings({"serial","rawtypes"})
@JsonInclude(value=Include.NON_NULL) 
public class MessageVo extends Message {
	private Long arbitrate_id;// 仲裁id
	private String arbitrate_type; // 仲裁发起类型 / user /company
	private Long sendId;   //发送者
	private String passFlag; // 通过类型 company /user
	private Long user_id;  //接受者
	private String userName;//用户姓名(接收人姓名)
	private String senderName;//用户姓名(发送人姓名)
	private String userUrl;//用户头像(接收人头像)
	private String senderUrl;//用户头像(发送人头像)
	private String readState;//阅读状态
	private String messageFlag;//s 发送类型， r 接收类型
	private List<Map> messageDetailList;//消息详情列表
	public Long getArbitrate_id() {
		return arbitrate_id;
	}

	public void setArbitrate_id(Long arbitrate_id) {
		this.arbitrate_id = arbitrate_id;
	}

	public MessageVo() {
		super();
	}

	public MessageVo(Long id) {
		super();
		this.id = id;
	}

	public String getArbitrate_type() {
		return arbitrate_type;
	}

	public void setArbitrate_type(String arbitrate_type) {
		this.arbitrate_type = arbitrate_type;
	}

	public Long getSendId() {
		return sendId;
	}

	public void setSendId(Long sendId) {
		this.sendId = sendId;
	}

	public String getPassFlag() {
		return passFlag;
	}

	public void setPassFlag(String passFlag) {
		this.passFlag = passFlag;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Map> getMessageDetailList() {
		return messageDetailList;
	}

	public void setMessageDetailList(List<Map> messageDetailList) {
		this.messageDetailList = messageDetailList;
	}

	public String getReadState() {
		return readState;
	}

	public void setReadState(String readState) {
		this.readState = readState;
	}

	public String getMessageFlag() {
		return messageFlag;
	}

	public void setMessageFlag(String messageFlag) {
		this.messageFlag = messageFlag;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getUserUrl() {
		return userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public String getSenderUrl() {
		return senderUrl;
	}

	public void setSenderUrl(String senderUrl) {
		this.senderUrl = senderUrl;
	}

}
