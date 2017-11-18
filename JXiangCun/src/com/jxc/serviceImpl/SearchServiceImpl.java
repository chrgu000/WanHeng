package com.jxc.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.SearchDao;
import com.jxc.entity.Search;
import com.jxc.page.Page;
import com.jxc.service.SearchService;

@Service("searchService")
@Transactional
public class SearchServiceImpl implements SearchService {
	@Resource
	private SearchDao dao;

	public List<Search> findAllSearch() {
		return dao.findAllSearch();
	}

	public List<Search> findAllSearchByPage(Page page) {
		return dao.findAllSearchByPage(page);
	}

	public Integer findRows(Page page) {
		return dao.findRows(page);
	}

	public Search findSearchById(Integer id) {
		return dao.findSearchById(id);
	}

	public boolean addSearch(Search search) {
		return dao.addSearch(search);
	}

	public boolean updateSearch(Search search) {
		return dao.updateSearch(search);
	}

	public boolean deleteSearchById(Integer id) {
		return dao.deleteSearchById(id);
	}

}
