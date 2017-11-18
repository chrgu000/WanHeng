package com.jxc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jxc.dao.TitleDao;
import com.jxc.entity.Mark;
import com.jxc.entity.Title;
import com.jxc.page.MarkPage;
import com.jxc.service.MarkService;

@Controller
@RequestMapping("/mark")
@Scope("prototype")
@SessionAttributes("markPage")
public class MarkController {
	@Resource
	private MarkService service;
	@Resource
	private TitleDao tdao;

	@RequestMapping("/markList.do")
	public String findAllAction(MarkPage page, ModelMap map) {
		Integer rows = service.findRows(page);// ��ȡ���ݱ����������
		page.setRows(rows);
		map.addAttribute("markPage", page);
		List<Mark> marks = service.findAllMarkByPage(page);// ��ҳ��ѯƷ����Ϣ
		List<Title> titles = tdao.findAllTitle();
		map.put("titles", titles);
		map.put("marks", marks);
		return "admin/mark";// ת����Ʒ����ҳ
	}

	@RequestMapping("/toAddMark.do")
	public String toAddAction(ModelMap map) {
		List<Title> titles = tdao.findAllTitle();
		map.put("titles", titles);
		return "admin/mark_add";// ת����Ʒ�����ҳ��
	}

	@RequestMapping("/addMark.do")
	public String addAction(Mark mark) {
		service.addMark(mark);// ���Ʒ����Ϣ
		List<Integer> titleIds=mark.getTitleIds();
		for (Integer id : titleIds) {
			Map<String,Integer> map=new HashMap<String,Integer>();
			map.put("mark_id", mark.getId());
			map.put("title_id", id);
			service.addMarkTitle(map);
		}
		return "redirect:../mark/markList.do";
	}

	@RequestMapping("/toUpdateMark.do")
	public String toUpdateAction(ModelMap map, Integer id) {
		List<Title> titles = tdao.findAllTitle();
		map.put("titles", titles);
		Mark mark = service.findMarkById(id);
		map.put("mark", mark);
		return "admin/mark_update";
	}

	@RequestMapping("/updateMark.do")
	public String updateAction(Mark mark) {
		service.deleteMarkTitle(mark.getId());
		service.updateMark(mark);// �޸�Ʒ����Ϣ
		List<Integer> titleIds=mark.getTitleIds();
		for (Integer id : titleIds) {
			Map<String,Integer> map=new HashMap<String,Integer>();
			map.put("mark_id", mark.getId());
			map.put("title_id", id);
			service.addMarkTitle(map);
		}
		return "redirect:../mark/markList.do";// �ض���Ʒ����ҳ
	}

	@RequestMapping("/deleteMark.do")
	public String deleteAction(Integer id) {
		service.deleteMarkById(id);// ����idɾ��Ʒ��
		service.deleteMarkTitle(id);
		return "redirect:../mark/markList.do";// �ض���Ʒ����ҳ
	}
}
