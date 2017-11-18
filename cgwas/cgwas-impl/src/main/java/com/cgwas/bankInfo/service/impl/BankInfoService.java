package com.cgwas.bankInfo.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.bankInfo.dao.api.IBankInfoDao;
import com.cgwas.bankInfo.entity.BankInfo;
import com.cgwas.bankInfo.entity.BankInfoVo;
import com.cgwas.bankInfo.service.api.IBankInfoService;
import com.cgwas.common.dataaccess.api.Page;

@Service
public class BankInfoService implements IBankInfoService {
	private IBankInfoDao bankInfoDao;

	public Serializable save(BankInfo bankInfo) {
		return bankInfoDao.save(bankInfo);
	}

	public void delete(BankInfo bankInfo) {
		bankInfoDao.delete(bankInfo);
	}

	public void deleteByExample(BankInfo bankInfo) {
		bankInfoDao.deleteByExample(bankInfo);
	}

	public void update(BankInfo bankInfo) {
		bankInfoDao.update(bankInfo);
	}

	public void updateIgnoreNull(BankInfo bankInfo) {
		bankInfoDao.updateIgnoreNull(bankInfo);
	}

	public void updateByExample(BankInfo bankInfo) {
		bankInfoDao.update("updateByExampleBankInfo", bankInfo);
	}

	public BankInfoVo load(BankInfo bankInfo) {
		return (BankInfoVo) bankInfoDao.reload(bankInfo);
	}

	public BankInfoVo selectForObject(BankInfo bankInfo) {
		return (BankInfoVo) bankInfoDao.selectForObject("selectBankInfo",
				bankInfo);
	}

	public List<BankInfoVo> selectForList(BankInfo bankInfo) {
		return (List<BankInfoVo>) bankInfoDao.selectForList("selectBankInfo",
				bankInfo);
	}

	public Page page(Page page, BankInfo bankInfo) {
		return bankInfoDao.page(page, bankInfo);
	}

	@Autowired
	public void setIBankInfoDao(
			@Qualifier("bankInfoDao") IBankInfoDao bankInfoDao) {
		this.bankInfoDao = bankInfoDao;
	}

	@Override
	public List<BankInfo> getUserBankList(Long user_id) {
		return (List<BankInfo>) bankInfoDao.selectForList("getUserBankList",
				user_id);
	}

	@Override
	public void updateStatusBankById(String status, Long id, Long user_id) {
		BankInfo bankInfo = new BankInfo();
		bankInfo.setId(id);
		bankInfo.setStatus(status);
		bankInfo.setUser_id(user_id);
		bankInfoDao.update("updateStatusBankById", bankInfo);
	}

	@Override
	public void updateBankById(BankInfo bankInfo) {
		bankInfoDao.update("updateBankById", bankInfo);
	}

	@Override
	public BankInfo getBankInfoByUserId(BankInfo bankInfo) {
		return (BankInfo) bankInfoDao.selectForObject("getBankInfoByUserId",
				bankInfo);
	}

}
