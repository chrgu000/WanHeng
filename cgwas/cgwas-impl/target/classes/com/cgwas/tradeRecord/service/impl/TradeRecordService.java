package com.cgwas.tradeRecord.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.tradeRecord.dao.api.ITradeRecordDao;
import com.cgwas.tradeRecord.entity.TradeRecord;
import com.cgwas.tradeRecord.entity.TradeRecordVo;
import com.cgwas.tradeRecord.entity.TradeStatistics;
import com.cgwas.tradeRecord.service.api.ITradeRecordService;

@Service
public class TradeRecordService implements ITradeRecordService {
	private ITradeRecordDao tradeRecordDao;

	public Serializable save(TradeRecord tradeRecord) {
		return tradeRecordDao.save(tradeRecord);
	}

	public void delete(TradeRecord tradeRecord) {
		tradeRecordDao.delete(tradeRecord);
	}

	public void deleteByExample(TradeRecord tradeRecord) {
		tradeRecordDao.deleteByExample(tradeRecord);
	}

	public void update(TradeRecord tradeRecord) {
		tradeRecordDao.update(tradeRecord);
	}

	public void updateIgnoreNull(TradeRecord tradeRecord) {
		tradeRecordDao.updateIgnoreNull(tradeRecord);
	}

	public void updateByExample(TradeRecord tradeRecord) {
		tradeRecordDao.update("updateByExampleTradeRecord", tradeRecord);
	}

	public TradeRecordVo load(TradeRecord tradeRecord) {
		return (TradeRecordVo) tradeRecordDao.reload(tradeRecord);
	}

	public TradeRecordVo selectForObject(TradeRecord tradeRecord) {
		return (TradeRecordVo) tradeRecordDao.selectForObject(
				"selectTradeRecord", tradeRecord);
	}

	public List<TradeRecordVo> selectForList(TradeRecord tradeRecord) {
		return (List<TradeRecordVo>) tradeRecordDao.selectForList(
				"selectTradeRecord", tradeRecord);
	}

	public Page page(Page page, TradeRecord tradeRecord) {
		return tradeRecordDao.page(page, tradeRecord);
	}

	@Override
	public List<TradeStatistics> selectYearTrade(String type) {
		return (List<TradeStatistics>) tradeRecordDao.selectForList(
				"selectYearTrade", type);
	}

	@Autowired
	public void setITradeRecordDao(
			@Qualifier("tradeRecordDao") ITradeRecordDao tradeRecordDao) {
		this.tradeRecordDao = tradeRecordDao;
	}

	@Override
	public String getUserAllTrade(TradeRecord tradeRecord) {
		return (String) tradeRecordDao.selectForObject("getUserAllTrade",
				tradeRecord);
	}

	@Override
	public Long checkRechargeTrade(Long user_id, String order_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		map.put("order_id", order_id);
		return (Long) tradeRecordDao.selectForObject("checkRechargeTrade", map);
	}

}
