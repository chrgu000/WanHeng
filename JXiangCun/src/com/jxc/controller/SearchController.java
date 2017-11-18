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
		Integer rows = service.findRows(page);// 获取数据表的数据行数
		page.setRows(rows);
		List<Search> searchs = service.findAllSearchByPage(page);// 分页查询品牌信息
		map.addAttribute("searchPage", page);
		map.put("searchs", searchs);
		return "admin/search";// 转发到品牌首页
	}

	@RequestMapping("/toAddSearch.do")
	public String toAddAction() {
		return "admin/search_add";// 转发到品牌添加页面
	}

	@RequestMapping("/addSearch.do")
	public String addAction(Search search) {
		service.addSearch(search);// 添加品牌信息
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
		service.updateSearch(search);// 修改品牌信息
		return "redirect:../search/searchList.do";// 重定向到品牌首页
	}

	@RequestMapping("/deleteSearch.do")
	public String deleteAction(Integer id) {
		service.deleteSearchById(id);// 根据id删除品牌
		return "redirect:../search/searchList.do";// 重定向到品牌首页
	}
}
