package com.cgwas.sector.service.api;

import java.util.List;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.sector.entity.Sector;
import com.cgwas.sector.entity.SectorVo;

public interface ISectorService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Sector sector);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Sector sector);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Sector sector);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Sector sector);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Sector sector);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Sector sector);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract SectorVo load(Sector sector);

	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract SectorVo selectForObject(Sector sector);

	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<SectorVo> selectForList(Sector sector);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Sector sector);
	/**
	 * 根据公司ID更改公司行业内容 
	 * @param sector
	 */
	public abstract void updateSectorByCompanyId(Sector sector);
	/**
	 * 根据公司ID得到行业信息
	 * @param sector
	 * @return
	 */
	public abstract Sector selectSectorByCompanyId(Sector sector);

}