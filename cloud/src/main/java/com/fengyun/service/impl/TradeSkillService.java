package com.fengyun.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.dao.ITradeSkillDao;
import com.fengyun.entity.TradeSkill;
import com.fengyun.entity.TradeSkillVo;
import com.fengyun.service.ITradeSkillService;
/**
*  Author yangjun
*/
@Service
public class TradeSkillService implements ITradeSkillService {
	private ITradeSkillDao tradeSkillDao;

	public Serializable save(TradeSkill tradeSkill){
		return tradeSkillDao.save(tradeSkill);
	}

	public void delete(TradeSkill tradeSkill){
		tradeSkillDao.delete(tradeSkill);
	}
	
	public void deleteByExample(TradeSkill tradeSkill){
		tradeSkillDao.deleteByExample(tradeSkill);
	}

	public void update(TradeSkill tradeSkill){
		tradeSkillDao.update(tradeSkill);
	}
	
	public void updateIgnoreNull(TradeSkill tradeSkill){
		tradeSkillDao.updateIgnoreNull(tradeSkill);
	}
		
	public void updateByExample(TradeSkill tradeSkill){
		tradeSkillDao.update("updateByExampleTradeSkill", tradeSkill);
	}

	public TradeSkillVo load(TradeSkill tradeSkill){
		return (TradeSkillVo)tradeSkillDao.reload(tradeSkill);
	}
	
	public TradeSkillVo selectForObject(TradeSkill tradeSkill){
		return (TradeSkillVo)tradeSkillDao.selectForObject("selectTradeSkill",tradeSkill);
	}
	
	@SuppressWarnings("unchecked")
	public List<TradeSkillVo> selectForList(TradeSkill tradeSkill){
		return (List<TradeSkillVo>)tradeSkillDao.selectForList("selectTradeSkill",tradeSkill);
	}
	
	public Page page(Page page, TradeSkill tradeSkill) {
		return tradeSkillDao.page(page, tradeSkill);
	}

	@Autowired
	public void setITradeSkillDao(
			@Qualifier("tradeSkillDao") ITradeSkillDao  tradeSkillDao) {
		this.tradeSkillDao = tradeSkillDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TradeSkill> getAllTradeSkills(Integer type_id) {
		return (List<TradeSkill>)tradeSkillDao.selectForList("getAllTradeSkills",type_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TradeSkill> getTradeSkillsBySoftwareId(Integer software_id) {
		return (List<TradeSkill>)tradeSkillDao.selectForList("getTradeSkillsBySoftwareId",software_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TradeSkill> getTradeSkillsBySoftwareIds(Map<String, Object> map) {
		return (List<TradeSkill>)tradeSkillDao.selectForList("getTradeSkillsBySoftwareIds",map);
	}

}
