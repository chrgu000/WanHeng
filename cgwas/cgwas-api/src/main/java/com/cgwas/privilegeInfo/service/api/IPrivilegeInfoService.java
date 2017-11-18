package com.cgwas.privilegeInfo.service.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.gRole.entity.GRoleVo;
import com.cgwas.privilegeInfo.entity.PrivilegeInfo;
import com.cgwas.privilegeInfo.entity.PrivilegeInfoVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IPrivilegeInfoService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(PrivilegeInfo privilegeInfo);
	
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(PrivilegeInfo privilegeInfo);
	
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void deleteAll(Map<String,Object> map,PrivilegeInfo privilegeInfo);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(PrivilegeInfo privilegeInfo);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(PrivilegeInfo privilegeInfo);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(PrivilegeInfo privilegeInfo);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(PrivilegeInfo privilegeInfo);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract PrivilegeInfoVo load(PrivilegeInfo privilegeInfo);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract PrivilegeInfoVo selectForObject(PrivilegeInfo privilegeInfo);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<PrivilegeInfoVo> selectForList(PrivilegeInfo privilegeInfo);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<PrivilegeInfoVo> selectPrivilegeByCheck(PrivilegeInfo privilegeInfo);
	
	/**
	 * 按对象中的非空属性作为条件，查询角色权限
	 */
	@SuppressWarnings("rawtypes")
	public abstract List<Map> selectPrivilegeByRoleId(PrivilegeInfo privilegeInfo);
	
	/**
	 * 查询团队角色权限
	 */
	public abstract List<PrivilegeInfoVo> selectPrivilegeByGRoleId(PrivilegeInfo privilegeInfo);
	
	/**
	 * 按对象中的非空属性作为条件，查询团队角色权限
	 * @param privilegeInfo
	 * @return
	 */
	public abstract List<PrivilegeInfoVo> selectPrivilegeByGRole(PrivilegeInfo privilegeInfo);
	
	/**
	 * 按对象中的非空属性作为条件，查询用户权限
	 */
	@SuppressWarnings("rawtypes")
	public abstract List<Map> selectPrivilegeByUserId(PrivilegeInfo privilegeInfo);
	
	
	/**
	 * 按对象中的非空属性作为条件，查询职称权限
	 * @param privilegeInfo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public abstract List<Map> selectPrivilegeByPositionId(PrivilegeInfo privilegeInfo);
	
	/**
	 * 查询当前用户拥有的Privilege主键集合
	 */
	public abstract List<Long> selectPIdsByUserId(Long user_id);
	
	/**
	 * 根据用户获取相应项目文件权限
	 * @param gRoleVo
	 * @return
	 */
	public abstract List<PrivilegeInfoVo> selectPrivilegeListByUser(GRoleVo gRleVo);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询(权限查询)
	 */
	public abstract Page page(Page page, PrivilegeInfo privilegeInfo);

	
	/**
	 * 根据对应权限(权限查询)
	 */
	public abstract Boolean isPrivilege(Long user_id, Long privilege_id);
	
	/**
	 * 根据权限URL判断是否拥有按钮权限 
	 */
	public abstract List<PrivilegeInfoVo> isPrivilegeByUrl(PrivilegeInfoVo privilegeInfoVo);
	
}