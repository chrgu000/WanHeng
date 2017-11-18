package com.cgwas.rolePrivilege.service.api;

import java.util.List;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.rolePrivilege.entity.RolePrivilege;
import com.cgwas.rolePrivilege.entity.RolePrivilegeVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IRolePrivilegeService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Boolean save(RolePrivilegeVo rolePrivilegeVo);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(RolePrivilege rolePrivilege);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(RolePrivilege rolePrivilege);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(RolePrivilege rolePrivilege);
	
	/**
	 * 先删除所有关系数据，然后进行循环添加关系数据
	 */
	public void updateRolePrivilege(RolePrivilegeVo rolePrivilegeVo);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(RolePrivilege rolePrivilege);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(RolePrivilege rolePrivilege);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract RolePrivilegeVo load(RolePrivilege rolePrivilege);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract RolePrivilegeVo selectForObject(RolePrivilege rolePrivilege);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<RolePrivilegeVo> selectForList(RolePrivilege rolePrivilege);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, RolePrivilege rolePrivilege);

}