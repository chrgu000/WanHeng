package com.cgwas.gRolePreinstall.service.api;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.gRolePreinstall.entity.GRolePreinstall;
import com.cgwas.gRolePreinstall.entity.GRolePreinstallVo;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IGRolePreinstallService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(GRolePreinstall gRolePreinstall);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(GRolePreinstall gRolePreinstall);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(GRolePreinstall gRolePreinstall);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(GRolePreinstall gRolePreinstall);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(GRolePreinstall gRolePreinstall);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(GRolePreinstall gRolePreinstall);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract GRolePreinstallVo load(GRolePreinstall gRolePreinstall);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract GRolePreinstallVo selectForObject(GRolePreinstall gRolePreinstall);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<GRolePreinstallVo> selectForList(GRolePreinstall gRolePreinstall);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, GRolePreinstall gRolePreinstall);

}