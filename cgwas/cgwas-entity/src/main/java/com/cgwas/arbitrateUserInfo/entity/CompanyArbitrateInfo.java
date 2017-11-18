package com.cgwas.arbitrateUserInfo.entity;
/**
 * 公司发起仲裁实体类
 * @author Lingwh
 *
 */
public class CompanyArbitrateInfo {
	protected long id; // 仲裁人员信息id
	protected String head_pic_path; // 发起人 头像
	protected String company_name; // 公司名字
	protected double flat; // 公司信用积分
	protected String arbitrate_content; // 仲裁内容
	protected Long user_id; // 对方Id
	protected Long company_id; // 公司id
	protected String nickname; // 对方昵称
	protected String head_pic_path1; // 对方头像路径
	protected double flat1; // 对方积分数量
	protected String user_type; // 用户类型
	protected Long arbitrate_id; // 所属仲裁编号
	protected String sex; // 对方性别
	public long getId() {
		return id;
	}
	public Long getArbitrate_id() {
		return arbitrate_id;
	}
	public void setArbitrate_id(Long arbitrate_id) {
		this.arbitrate_id = arbitrate_id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHead_pic_path() {
		return head_pic_path;
	}
	public Long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	public void setHead_pic_path(String head_pic_path) {
		this.head_pic_path = head_pic_path;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public double getFlat() {
		return flat;
	}
	public void setFlat(double flat) {
		this.flat = flat;
	}
	public String getArbitrate_content() {
		return arbitrate_content;
	}
	public void setArbitrate_content(String arbitrate_content) {
		this.arbitrate_content = arbitrate_content;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHead_pic_path1() {
		return head_pic_path1;
	}
	public void setHead_pic_path1(String head_pic_path1) {
		this.head_pic_path1 = head_pic_path1;
	}
	public double getFlat1() {
		return flat1;
	}
	public void setFlat1(double flat1) {
		this.flat1 = flat1;
	}

}
