package com.cgwas.userInfo.entity;



import javax.persistence.Id;
import java.util.Date;
import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 模版生成 <br/>
 *         表名： u_user_info <br/>
 *         描述：用户信息表 <br/>
 */
@SuppressWarnings("serial")
public class UserInfo implements Serializable {
	protected Long id;// 主键
	protected String sex;// 性别
	protected String name;// 姓名
	protected String nation;// 民族
	@DateTimeFormat(pattern="yyyy-MM-dd")
	protected Date birth;// 生日
	protected String head_pic_path;// 用户头像路径
	protected String email;// 邮箱
	protected String qq;// qq
	protected String weixin;// 微信号
	protected Long user_id;// user_id
	protected String province;// province
	protected String city;// city
	protected String area;// area
	protected String address;// address
	protected Double money; //钱包

	public UserInfo() {
		super();
	}

	public UserInfo(Long id) {
		super();
		this.id = id;
	}
	
	@Id// 主键
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public String getHead_pic_path() {
		return head_pic_path;
	}
	public void setHead_pic_path(String head_pic_path) {
		this.head_pic_path = head_pic_path;
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
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
	
}
