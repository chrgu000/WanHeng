package com.cgwas.arbitrateUserInfo.entity;

public class ArbitrateImage {
	private Long id; //主键
	private String image_path; //图片路径
	private Long arbitrate_user_id;  //仲裁用户信息id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public Long getArbitrate_user_id() {
		return arbitrate_user_id;
	}
	public void setArbitrate_user_id(Long arbitrate_user_id) {
		this.arbitrate_user_id = arbitrate_user_id;
	}

}
