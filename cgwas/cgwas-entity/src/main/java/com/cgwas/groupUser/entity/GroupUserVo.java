package com.cgwas.groupUser.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： p_group_user <br/>
 *         描述：项目团队成员表 <br/>
 */
 @SuppressWarnings("serial")
 @JsonInclude(value=Include.NON_NULL) 
public class GroupUserVo extends GroupUser {
	private String[] gIds;//团队成员id数组
	private String userName;//用户姓名
	private String phone;//手机号码
	private Long age;//年龄
	private String companyName;//公司名称
	private String projectName;//项目名称
	private String sex;//性别
	private String headUrl;//头像路径
	private Long userId;
	private Integer peopleNum;
	public GroupUserVo() {
		super();
	}

	public GroupUserVo(Long id) {
		super();
		this.id = id;
	}

	public String[] getGIds() {
		return gIds;
	}

	public void setGIds(String[] gIds) {
		this.gIds = gIds;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}

}
