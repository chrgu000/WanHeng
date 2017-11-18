package com.cgwas.walletInfo.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.utils.EncryptUtil;
import com.cgwas.walletInfo.dao.api.IWalletInfoDao;
import com.cgwas.walletInfo.entity.WalletInfo;
import com.cgwas.walletInfo.entity.WalletInfoVo;
import com.cgwas.walletInfo.service.api.IWalletInfoService;

@Service
public class WalletInfoService implements IWalletInfoService {
	private IWalletInfoDao walletInfoDao;
	private EncryptUtil encryptUtil = new EncryptUtil();

	public Serializable save(WalletInfo walletInfo) {
		return walletInfoDao.save(walletInfo);
	}

	public void delete(WalletInfo walletInfo) {
		walletInfoDao.delete(walletInfo);
	}

	public void deleteByExample(WalletInfo walletInfo) {
		walletInfoDao.deleteByExample(walletInfo);
	}

	public void update(WalletInfo walletInfo) {
		walletInfoDao.update(walletInfo);
	}

	public void updateIgnoreNull(WalletInfo walletInfo) {
		walletInfoDao.updateIgnoreNull(walletInfo);
	}

	public void updateByExample(WalletInfo walletInfo) {
		walletInfoDao.update("updateByExampleWalletInfo", walletInfo);
	}

	public WalletInfoVo load(WalletInfo walletInfo) {
		return (WalletInfoVo) walletInfoDao.reload(walletInfo);
	}

	public WalletInfoVo selectForObject(WalletInfo walletInfo) {
		return (WalletInfoVo) walletInfoDao.selectForObject("selectWalletInfo",
				walletInfo);
	}

	public List<WalletInfoVo> selectForList(WalletInfo walletInfo) {
		return (List<WalletInfoVo>) walletInfoDao.selectForList(
				"selectWalletInfo", walletInfo);
	}

	public Page page(Page page, WalletInfo walletInfo) {
		return walletInfoDao.page(page, walletInfo);
	}

	@Autowired
	public void setIWalletInfoDao(
			@Qualifier("walletInfoDao") IWalletInfoDao walletInfoDao) {
		this.walletInfoDao = walletInfoDao;
	}

	@Override
	public WalletInfo getUserWallet(Long user_id) {

		WalletInfo retn = (WalletInfo) walletInfoDao.selectForObject(
				"getUserWallet", user_id);
		if (retn == null) { // 若为空则创建一条
			retn = new WalletInfo();
			retn.setUser_id(user_id);
			retn.setPay_password(encryptUtil.getEncryptMsg("888888"));
			// 测试余额
			retn.setRemaining_sum(0.0);
			retn.setStatus("1");
			retn.setCurrency_type("RMB");
			walletInfoDao.save(retn);
		}
		return retn;
	}

	@Override
	public void accessUserMoney(WalletInfo walletInfo, String flag) {
		// 判断非空
		this.getUserWallet(walletInfo.getUser_id());
		// 存取判断
		if ("get".equals(flag)) {
			walletInfo.setRemaining_sum(-walletInfo.getRemaining_sum());
		}

		walletInfoDao.update("accessUserMoney", walletInfo);
	}

	@Override
	public void changePayPassword(WalletInfo walletInfo) {

		walletInfoDao.update("changePayPassword", walletInfo);
	}

}
