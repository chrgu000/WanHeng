package com.jxc.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jxc.entity.Search;
import com.jxc.page.SearchPage;
import com.jxc.service.SearchService;

@Controller
@RequestMapping("/search")
@Scope("prototype")
@SessionAttributes("searchPage")
public class SearchController {
	@Resource
	private SearchService service;

	@RequestMapping("/searchList.do")
	public String findAllAction(SearchPage page, ModelMap map) {
		Integer rows = service.findRows(page);// ��ȡ���ݱ����������
		page.setRows(rows);
		List<Search> searchs = service.findAllSearchByPage(page);// ��ҳ��ѯƷ����Ϣ
		map.addAttribute("searchPage", page);
		map.put("searchs", searchs);
		return "admin/search";// ת����Ʒ����ҳ
	}

	@RequestMapping("/toAddSearch.do")
	public String toAddAction() {
		return "admin/search_add";// ת����Ʒ�����ҳ��
	}

	@RequestMapping("/addSearch.do")
	public String addAction(Search search) {
		service.addSearch(search);// ���Ʒ����Ϣ
		return "redirect:../search/searchList.do";
	}

	@RequestMapping("/toUpdateSearch.do")
	public String toUpdateAction(ModelMap map, Integer id) {
		Search search = service.findSearchById(id); 
		map.put("search", search);
		return "admin/search_update"; 
	}

	@RequestMapping("/updateSearch.do")
	public String updateAction(Search search) {
		service.updateSearch(search);// �޸�Ʒ����Ϣ
		return "redirect:../search/searchList.do";// �ض���Ʒ����ҳ
	}

	@RequestMapping("/deleteSearch.do")
	public String deleteAction(Integer id) {
		service.deleteSearchById(id);// ����idɾ��Ʒ��
		return "redirect:../search/searchList.do";// �ض���Ʒ����ҳ
	}
}
