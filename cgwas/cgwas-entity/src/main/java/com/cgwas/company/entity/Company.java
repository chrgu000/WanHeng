package com.cgwas.company.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 公司实体类
 * 
 * @author Lingwh
 * 
 */
@SuppressWarnings("serial")
public class Company implements Serializable {
	protected Long id;// 主键
	protected String company_name;// 企业名称
	protected String company_type;// 企业类型
	protected String legal_person;// 法定代表人
	protected String idcard_pic_path;// 法人身份证照路径
	protected String head_pic_path;// 公司头像路径
	protected String code;// 统一社会信用代码
	protected String registered_assets;// z注册资本
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date establish_date;// 成立日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date company_begin_date;// 营业开始日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date company_end_date;// 营业结束日期
	protected String province;// 所在省
	protected String city;// 所在市
	protected String register_office;// 登记机关
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date date_approved;// 核准日期
	protected String status;// 等级状态
	protected String business_scope;// 经营范围
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date regist_date;// 平台注册日期
	protected char is_delete;// 是否删除（Y/N）
	protected String area;// param2
	protected String address;// param3
	protected String param4;// param4
	protected String param5;// param5
	protected String param6;// param6
	protected String param7;// param7

	public Company() {
		super();
	}

	public Company(Long id) {
		super();
		this.id = id;
	}

	@Id
	// 主键
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_type() {
		return company_type;
	}

	public void setCompany_type(String company_type) {
		this.company_type = company_type;
	}

	public String getLegal_person() {
		return legal_person;
	}

	public void setLegal_person(String legal_person) {
		this.legal_person = legal_person;
	}

	public String getIdcard_pic_path() {
		return idcard_pic_path;
	}

	public void setIdcard_pic_path(String idcard_pic_path) {
		this.idcard_pic_path = idcard_pic_path;
	}

	public String getHead_pic_path() {
		return head_pic_path;
	}

	public void setHead_pic_path(String head_pic_path) {
		this.head_pic_path = head_pic_path;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRegistered_assets() {
		return registered_assets;
	}

	public void setRegistered_assets(String registered_assets) {
		this.registered_assets = registered_assets;
	}

	public Date getEstablish_date() {
		return establish_date;
	}

	public void setEstablish_date(Date establish_date) {
		this.establish_date = establish_date;
	}

	public Date getCompany_begin_date() {
		return company_begin_date;
	}

	public void setCompany_begin_date(Date company_begin_date) {
		this.company_begin_date = company_begin_date;
	}

	public Date getCompany_end_date() {
		return company_end_date;
	}

	public void setCompany_end_date(Date company_end_date) {
		this.company_end_date = company_end_date;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegister_office() {
		return register_office;
	}

	public void setRegister_office(String register_office) {
		this.register_office = register_office;
	}

	public Date getDate_approved() {
		return date_approved;
	}

	public void setDate_approved(Date date_approved) {
		this.date_approved = date_approved;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBusiness_scope() {
		return business_scope;
	}

	public void setBusiness_scope(String business_scope) {
		this.business_scope = business_scope;
	}

	public Date getRegist_date() {
		return regist_date;
	}

	public void setRegist_date(Date regist_date) {
		this.regist_date = regist_date;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}

	public String getParam5() {
		return param5;
	}

	public void setParam5(String param5) {
		this.param5 = param5;
	}

	public String getParam6() {
		return param6;
	}

	public void setParam6(String param6) {
		this.param6 = param6;
	}

	public String getParam7() {
		return param7;
	}

	public void setParam7(String param7) {
		this.param7 = param7;
	}

	public char getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(char is_delete) {
		this.is_delete = is_delete;
	}

}
