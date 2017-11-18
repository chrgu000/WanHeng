package com.jxc.service;

import java.util.List;
import java.util.Map;

import com.jxc.entity.Merchant;
import com.jxc.entity.Title;
import com.jxc.page.MerchantPage;
import com.jxc.page.Page;
import com.jxc.page.Select;

public interface MerchantService {
	List<Merchant> findAllMerchant();

	List<Merchant> findAllMerchantByPage(Page page);

	Integer findRows(Page page);
	
	List<Merchant> findMerchantsByTitleId(Integer title_id);
	
	boolean addMerchantMark(Map<String,Integer> map);
	
	List<Merchant> getMerchantsByPage(MerchantPage page);
	
	List<Merchant> findMerchantsBySelect(Select select);
	
	Merchant findMerchantById(Integer id);
	
	List<Title> findTitlesByMerchantId(Integer merchant_id);

	boolean addMerchant(Merchant Merchant);
	
	boolean addMerchantTitle(Map<String,Integer> map);

	boolean updateMerchant(Merchant Merchant);

	boolean deleteMerchantMark(Integer merchant_id);
	
	boolean deleteMerchantTitle(Integer merchant_id);
	
	boolean deleteMerchantById(Integer id);
}
