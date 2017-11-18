package com.cgwas.gRole.entity;

import java.util.List;

import com.cgwas.groupUser.entity.GroupUserVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： p_g_role <br/>
 *         描述：团队角色表 <br/>
 */
 @SuppressWarnings("serial")
 @JsonInclude(value=Include.NON_NULL) 
public class GRoleVo extends GRole {
	private Long fid;
	private Long sid;
	private String state;
	private Long user_id;
	private String user_name;
	private List<GroupUserVo> groupUserList;
	public GRoleVo() {
		super();
	}

	public GRoleVo(Long id) {
		super();
		this.id = id;
	}

	public List<GroupUserVo> getGroupUserList() {
		return groupUserList;
	}

	public void setGroupUserList(List<GroupUserVo> groupUserList) {
		this.groupUserList = groupUserList;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


}
