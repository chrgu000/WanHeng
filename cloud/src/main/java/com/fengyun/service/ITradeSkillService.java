package com.fengyun.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.TradeSkill;
import com.fengyun.entity.TradeSkillVo;
/**
*  Author yangjun
*/
public interface ITradeSkillService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(TradeSkill tradeSkill);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(TradeSkill tradeSkill);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(TradeSkill tradeSkill);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(TradeSkill tradeSkill);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(TradeSkill tradeSkill);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(TradeSkill tradeSkill);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract TradeSkillVo load(TradeSkill tradeSkill);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract TradeSkillVo selectForObject(TradeSkill tradeSkill);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<TradeSkillVo> selectForList(TradeSkill tradeSkill);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, TradeSkill tradeSkill);

	public abstract List<TradeSkill> getAllTradeSkills(Integer type_id);

	public abstract List<TradeSkill> getTradeSkillsBySoftwareId(
			Integer software_id);

	public abstract List<TradeSkill> getTradeSkillsBySoftwareIds(
			Map<String, Object> map);

}