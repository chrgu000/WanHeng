package com.cgwas.userPrivilege.entity;

import java.util.List;

import com.cgwas.privilegeInfo.entity.PrivilegeInfoVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author yubangqiong 模版生成 <br/>
 *         表名： u_user_privilege <br/>
 *         描述：用户权限关系表 <br/>
 */
 @SuppressWarnings("serial")
 @JsonInclude(value=Include.NON_NULL) 
public class UserPrivilegeVo extends UserPrivilege {
	 	private long[] ids;
		private String user_name;//用户id
		private String name;//用户姓名
		private List<PrivilegeInfoVo> list;//权限列表
		
		public long[] getIds() {
			return ids;
		}

		public void setIds(long[] ids) {
			this.ids = ids;
		}

		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<PrivilegeInfoVo> getList() {
			return list;
		}

		public void setList(List<PrivilegeInfoVo> list) {
			this.list = list;
		}

	public UserPrivilegeVo() {
		super();
	}

	public UserPrivilegeVo(Long id) {
		super();
		this.id = id;
	}


}
