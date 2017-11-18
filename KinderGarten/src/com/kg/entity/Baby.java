package com.kg.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * baby实体类
 * 
 * @author 杨俊
 * 
 */
public class Baby implements Serializable {
	private Integer id;
	private String username;// 用户名
	private String password;// 登陆密码
	private Integer garden_id;// 园区id
	private Garden garden;// 园区对象
	private String tel;// 手机号
	private String sex;// 性别
	private String birth_date;// 生日
	private String introduce;// 简介
	private String expectation;//对宝贝的期望
	private String header_pic_path;// 头像路径
	private Timestamp join_time;// 注册时间
	private Integer teacher_id;// 老师id
	private Teacher teacher;// 老师对象
	private String father_name;// 父亲名称
	private String father_tel;// 父亲联系方式
	private String mother_name;// 母亲名称
	private String mother_tel;// 母亲联系方式
	private String emergency_contact_name;// 紧急联系人姓名
	private String emergency_contact_tel;// 紧急联系人联系方式
	private Integer state;// 审核状态
    private String endDate;
    private String isStudy;
    private String small_name;//小名
    private String address;//家庭住址
    private String content;//过敏史/病史
    private String code;
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getExpectation() {
		return expectation;
	}

	public void setExpectation(String expectation) {
		this.expectation = expectation;
	}

	public String getSmall_name() {
		return small_name;
	}

	public void setSmall_name(String smallName) {
		small_name = smallName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsStudy() {
		return isStudy;
	}

	public void setIsStudy(String isStudy) {
		this.isStudy = isStudy;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getGarden_id() {
		return garden_id;
	}

	public void setGarden_id(Integer gardenId) {
		garden_id = gardenId;
	}

	public Garden getGarden() {
		return garden;
	}

	public void setGarden(Garden garden) {
		this.garden = garden;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birthDate) {
		birth_date = birthDate;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getHeader_pic_path() {
		return header_pic_path;
	}

	public void setHeader_pic_path(String headerPicPath) {
		header_pic_path = headerPicPath;
	}

	public String getJoin_time() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(join_time==null)
			return null;
		return sdf.format(join_time);
	}

	public void setJoin_time(Timestamp joinTime) {
		join_time = joinTime;
	}

	public Integer getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Integer teacherId) {
		teacher_id = teacherId;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String fatherName) {
		father_name = fatherName;
	}

	public String getFather_tel() {
		return father_tel;
	}

	public void setFather_tel(String fatherTel) {
		father_tel = fatherTel;
	}

	public String getMother_name() {
		return mother_name;
	}

	public void setMother_name(String motherName) {
		mother_name = motherName;
	}

	public String getMother_tel() {
		return mother_tel;
	}

	public void setMother_tel(String motherTel) {
		mother_tel = motherTel;
	}

	public String getEmergency_contact_name() {
		return emergency_contact_name;
	}

	public void setEmergency_contact_name(String emergencyContactName) {
		emergency_contact_name = emergencyContactName;
	}

	public String getEmergency_contact_tel() {
		return emergency_contact_tel;
	}

	public void setEmergency_contact_tel(String emergencyContactTel) {
		emergency_contact_tel = emergencyContactTel;
	}

	public Integer getState() {
		return state;
	}

	@Override
	public String toString() {
		return "Baby [address=" + address + ", birth_date=" + birth_date
				+ ", content=" + content + ", emergency_contact_name="
				+ emergency_contact_name + ", emergency_contact_tel="
				+ emergency_contact_tel + ", endDate=" + endDate
				+ ", father_name=" + father_name + ", father_tel=" + father_tel
				+ ", garden=" + garden + ", garden_id=" + garden_id
				+ ", header_pic_path=" + header_pic_path + ", id=" + id
				+ ", introduce=" + introduce + ", isStudy=" + isStudy
				+ ", join_time=" + join_time + ", mother_name=" + mother_name
				+ ", mother_tel=" + mother_tel + ", password=" + password
				+ ", sex=" + sex + ", small_name=" + small_name + ", state="
				+ state + ", teacher=" + teacher + ", teacher_id=" + teacher_id
				+ ", tel=" + tel + ", username=" + username + "]";
	}

}
