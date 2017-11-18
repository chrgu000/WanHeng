package com.cgwas.gRolePrivilege.service.api;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.gRolePrivilege.entity.GRolePrivilege;
import com.cgwas.gRolePrivilege.entity.GRolePrivilegeVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IGRolePrivilegeService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(GRolePrivilege gRolePrivilege);
	
	/**
	 * 更新团队角色权限
	 */
	public abstract Boolean saveAndUpdate(GRolePrivilegeVo gRolePrivilegeVo);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(GRolePrivilege gRolePrivilege);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(GRolePrivilege gRolePrivilege);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(GRolePrivilege gRolePrivilege);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(GRolePrivilege gRolePrivilege);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(GRolePrivilege gRolePrivilege);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract GRolePrivilegeVo load(GRolePrivilege gRolePrivilege);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract GRolePrivilegeVo selectForObject(GRolePrivilege gRolePrivilege);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<GRolePrivilegeVo> selectForList(GRolePrivilege gRolePrivilege);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, GRolePrivilege gRolePrivilege);

}