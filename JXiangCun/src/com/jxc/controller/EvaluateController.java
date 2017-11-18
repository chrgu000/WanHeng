package com.jxc.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jxc.entity.Evaluate;
import com.jxc.entity.Merchant;
import com.jxc.page.EvaluatePage;
import com.jxc.service.EvaluateService;
import com.jxc.service.MerchantService;


@Controller
@RequestMapping("/evaluate")
@Scope("prototype")
@SessionAttributes("evaluatePage")
public class EvaluateController {
	@Resource
	private EvaluateService service;
    @Resource 
    private MerchantService mservice;
    
	@RequestMapping("/evaluate_list.do")
	public String findAllAdviceAction(ModelMap map, EvaluatePage page) {
		Integer rows = service.findRows(page);
		page.setRows(rows);
		List<Evaluate> evaluates = service.findEvaluateByPage(page);
		  List<Merchant> merchants=mservice.findAllMerchant();
		  map.put("merchants",merchants);
		map.put("evaluatePage", page);
		map.put("evaluates", evaluates);
		return "admin/evaluate";
	}
	@RequestMapping("/addEvaluate.do")
	public String addAdviceAction(Evaluate evaluate,ModelMap map,HttpSession session){
		Timestamp createDate=new Timestamp(System.currentTimeMillis());
		evaluate.setCreateDate(createDate);
		map.put("msg", "建议成功提交,谢谢您!");
		return "";
	}

	@RequestMapping("/deleteEvaluate.do")
	public String deleteAction(Integer id){
		service.deleteEvaluate(id);
		return "redirect:../evalaute/evaluate_list.do";
	}
}
