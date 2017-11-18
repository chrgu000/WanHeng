package com.jxc.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.MarkDao;
import com.jxc.entity.Mark;
import com.jxc.page.Page;
import com.jxc.service.MarkService;
@Service("markService")
@Transactional
public class MarkServiceImpl implements MarkService{
	@Resource
private MarkDao dao;
	public List<Mark> findAllMark() {
		return dao.findAllMark();
	}

	public List<Mark> findAllMarkByPage(Page page) {
		return dao.findAllMarkByPage(page);
	}

	public Integer findRows(Page page) {
		return dao.findRows(page);
	}


	public Mark findMarkById(Integer id) {
		return dao.findMarkById(id);
	}

	public boolean addMark(Mark Mark) {
		return dao.addMark(Mark);
	}

	public boolean updateMark(Mark Mark) {
		return dao.updateMark(Mark);
	}

	public boolean deleteMarkById(Integer id) {
		return dao.deleteMarkById(id);
	}

	public List<Mark> findMarksByTitleId(Integer title_id) {
		return dao.findMarksByTitleId(title_id);
	}

	public List<Mark> findFiveMarks() {
		return dao.findFiveMarks();
	}

	public boolean addMarkTitle(Map<String, Integer> map) {
		return dao.addMarkTitle(map);
	}

 


	public List<Mark> findMarkByTitleIds(Map<String, List<Integer>> maps) {
		return dao.findMarkByTitleIds(maps);
	}

	public boolean deleteMarkTitle(Integer markId) {
		return dao.deleteMarkTitle(markId);
	}

}
