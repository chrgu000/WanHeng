package com.jxc.dao;

import java.util.List;

import com.jxc.entity.Support;
import com.jxc.page.Page;

public interface SupportDao {
	List<Support> findAllSupport();

	List<Support> findAllSupportByPage(Page page);

	Integer findRows(Page page);


	Support findSupportById(Integer id);

	boolean addSupport(Support support);

	boolean updateSupport(Support support);

	boolean deleteSupportById(Integer id);
}
