package com.cgwas.visitor.entity;

import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： s_visitor <br/>
 *         描述：s_visitor <br/>
 */
@SuppressWarnings("serial")
public class Visitor implements Serializable {
	protected Long id;// id
	protected String ip;// 来自ip
	protected String country;// 国家
	protected String region;// 省份
	protected String city;// 城市
	protected String area;// 地区
	protected String county;// 区/县
	protected String isp;// 供应商
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date visit_time;// 访问时间
	protected String status;// 状态

	public Visitor() {
		super();
	}


	


	public Long getId() {
		return id;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public Date getVisit_time() {
		return visit_time;
	}

	public void setVisit_time(Date visit_time) {
		this.visit_time = visit_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
