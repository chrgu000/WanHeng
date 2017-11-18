package com.jxc.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.MerchantDao;
import com.jxc.entity.Merchant;
import com.jxc.entity.Title;
import com.jxc.page.MerchantPage;
import com.jxc.page.Page;
import com.jxc.page.Select;
import com.jxc.service.MerchantService;

@Service("merchantService")
@Transactional
public class MerchantServiceImpl implements MerchantService {
@Resource
private MerchantDao dao;

	public List<Merchant> findAllMerchant() {
		 
		return dao.findAllMerchant();
	}

	public List<Merchant> findAllMerchantByPage(Page page) {
	 
		return dao.findAllMerchantByPage(page);
	}

	public Integer findRows(Page page) {
		 
		return dao.findRows(page);
	}
 
	public Merchant findMerchantById(Integer id) {
		 
		return dao.findMerchantById(id);
	}

	public boolean addMerchant(Merchant Merchant) {
		 
		return dao.addMerchant(Merchant);
	}

	public boolean updateMerchant(Merchant Merchant) {
	 
		return dao.updateMerchant(Merchant);
	}

	public boolean deleteMerchantById(Integer id) {
	 
		return dao.deleteMerchantById(id);
	}

	public List<Merchant> findMerchantsByTitleId(Integer title_id) {
		return dao.findMerchantsByTitleId(title_id);
	}

	public boolean addMerchantMark(Map<String, Integer> map) {
		 
		return dao.addMerchantMark(map);
	}

	public boolean deleteMerchantMark(Integer merchant_id) {
		return dao.deleteMerchantMark(merchant_id);
	}

	public List<Merchant> findMerchantsBySelect(Select select) {
		return dao.findMerchantsBySelect(select);
	}

	public List<Merchant> getMerchantsByPage(MerchantPage page) {
		return dao.getMerchantsByPage(page);
	}

	public boolean deleteMerchantTitle(Integer merchantId) {
		return dao.deleteMerchantTitle(merchantId);
	}

	public boolean addMerchantTitle(Map<String, Integer> map) {
		return dao.addMerchantTitle(map);
	}

	public List<Title> findTitlesByMerchantId(Integer merchantId) {
		return dao.findTitlesByMerchantId(merchantId);
	}

}
