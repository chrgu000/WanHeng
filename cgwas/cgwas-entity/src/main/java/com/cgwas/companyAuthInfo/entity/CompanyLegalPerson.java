package com.cgwas.companyAuthInfo.entity;

import java.io.Serializable;

/**
 * 公司法人代表信息
 * 
 * @author Lingwh
 * 
 */
@SuppressWarnings("serial")
public class CompanyLegalPerson implements Serializable {

	protected Long id;   // 法人id
	protected String name; // 法人名字
	protected String tel; // 法人电话
	protected String email; // 邮箱
	protected String qq; // qq
	protected String weixin; // 微信
	protected String idcard_pic_path; // 身份证正面 
	protected String idcard_pic_path_back; // 身份证背面
	protected String idcard; // 身份证信息

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getIdcard_pic_path() {
		return idcard_pic_path;
	}

	public void setIdcard_pic_path(String idcard_pic_path) {
		this.idcard_pic_path = idcard_pic_path;
	}

	public String getIdcard_pic_path_back() {
		return idcard_pic_path_back;
	}

	public void setIdcard_pic_path_back(String idcard_pic_path_back) {
		this.idcard_pic_path_back = idcard_pic_path_back;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

}
