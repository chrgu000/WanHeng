package com.cgwas.rewardsRecord.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.rewardsRecord.dao.api.IRewardsRecordDao;
import com.cgwas.rewardsRecord.entity.RewardsRecord;
import com.cgwas.rewardsRecord.entity.RewardsRecordVo;
import com.cgwas.rewardsRecord.service.api.IRewardsRecordService;

@Service
public class RewardsRecordService implements IRewardsRecordService {
	private IRewardsRecordDao rewardsRecordDao;

	public Serializable save(RewardsRecord rewardsRecord) {
		return rewardsRecordDao.save(rewardsRecord);
	}

	public void delete(RewardsRecord rewardsRecord) {
		rewardsRecordDao.delete(rewardsRecord);
	}

	public void deleteByExample(RewardsRecord rewardsRecord) {
		rewardsRecordDao.deleteByExample(rewardsRecord);
	}

	public void update(RewardsRecord rewardsRecord) {
		rewardsRecordDao.update(rewardsRecord);
	}

	public void updateIgnoreNull(RewardsRecord rewardsRecord) {
		rewardsRecordDao.updateIgnoreNull(rewardsRecord);
	}

	public void updateByExample(RewardsRecord rewardsRecord) {
		rewardsRecordDao.update("updateByExampleRewardsRecord", rewardsRecord);
	}

	public RewardsRecordVo load(RewardsRecord rewardsRecord) {
		return (RewardsRecordVo) rewardsRecordDao.reload(rewardsRecord);
	}

	public RewardsRecordVo selectForObject(RewardsRecord rewardsRecord) {
		return (RewardsRecordVo) rewardsRecordDao.selectForObject(
				"selectRewardsRecord", rewardsRecord);
	}

	public List<RewardsRecordVo> selectForList(RewardsRecord rewardsRecord) {
		return (List<RewardsRecordVo>) rewardsRecordDao.selectForList(
				"selectRewardsRecord", rewardsRecord);
	}

	public Page page(Page page, RewardsRecord rewardsRecord) {
		return rewardsRecordDao.page(page, rewardsRecord);
	}

	@Autowired
	public void setIRewardsRecordDao(
			@Qualifier("rewardsRecordDao") IRewardsRecordDao rewardsRecordDao) {
		this.rewardsRecordDao = rewardsRecordDao;
	}

	@Override
	public List<RewardsRecord> getRewardsRecordListByUserId(Long use_id) {
		return (List<RewardsRecord>) rewardsRecordDao.selectForList(
				"getRewardsRecordListByUserId", use_id);
	}

	@Override
	public List<RewardsRecord> getRewardsRecordListByCompanyId(Long company_id) {
		return (List<RewardsRecord>) rewardsRecordDao.selectForList(
				"getRewardsRecordListByCompanyId", company_id);
	}

}
