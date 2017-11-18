package com.jxc.dao;

import java.util.List;
import java.util.Map;

import com.jxc.entity.Merchant;
import com.jxc.entity.Title;
import com.jxc.page.MerchantPage;
import com.jxc.page.Page;
import com.jxc.page.Select;

public interface MerchantDao {
	List<Merchant> findAllMerchant();

	List<Merchant> findAllMerchantByPage(Page page);

	List<Merchant> findMerchantsByTitleId(Integer title_id);
	
	List<Merchant> getMerchantsByPage(MerchantPage page);
	
	Integer findRows(Page page);

	Merchant findMerchantById(Integer id);
	
	List<Merchant> findMerchantsBySelect(Select select);
	
	List<Title> findTitlesByMerchantId(Integer merchant_id);
	
	boolean addMerchantMark(Map<String,Integer> map);
	
	boolean addMerchantTitle(Map<String,Integer> map);

	boolean addMerchant(Merchant Merchant);

	boolean updateMerchant(Merchant Merchant);

	boolean deleteMerchantById(Integer id);
	
	boolean deleteMerchantMark(Integer merchant_id);
	
	boolean deleteMerchantTitle(Integer merchant_id);
}
