package com.jxc.dao;

import java.util.List;

import com.jxc.entity.Search;
import com.jxc.page.Page;


public interface SearchDao {
	List<Search> findAllSearch();

	List<Search> findAllSearchByPage(Page page);

	Integer findRows(Page page);

	Search findSearchById(Integer id);

	boolean addSearch(Search search);

	boolean updateSearch(Search search);

	boolean deleteSearchById(Integer id);
}
