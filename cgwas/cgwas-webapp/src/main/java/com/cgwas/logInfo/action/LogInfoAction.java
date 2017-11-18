package com.cgwas.logInfo.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.cgwas.logInfo.entity.LogInfo;
import com.cgwas.logInfo.service.api.ILogInfoService;
/**
 * 
 * @author yubangqiong
 *
 */
@Controller
@RequestMapping("cgwas/logoInfoAction")
public class LogInfoAction {
	private ILogInfoService logInfoService;

	@RequestMapping("/index")
	public String index() {
		return "/dsoul/logInfo/logInfo_index.jsp";
	}

	@RequestMapping("/list")
	public String list(LogInfo logInfo, HttpServletRequest request, Model model) {
		Page page = PageUtils.createPage(request);
		page = logInfoService.page(page, logInfo);
		model.addAttribute("page", page);
		model.addAllAttributes(PageUtils.createPagePar(page));
		return "/dsoul/logInfo/logInfo_list.jsp";
	}
	
	
	@RequestMapping("/create_page")
	public String create_page(Model model) {
		LogInfo logInfo = new LogInfo();
		model.addAttribute("logInfo", logInfo);
		return "/dsoul/logInfo/logInfo_create.jsp";
	}
	
	@RequestMapping("/update_page")
	public String update_page(LogInfo logInfo, Model model) {
		logInfo = logInfoService.load(logInfo);
		model.addAttribute("logInfo", logInfo);
		return "/dsoul/logInfo/logInfo_update.jsp";
	}

	@RequestMapping("/detail_page")
	public String detail_page(LogInfo logInfo, Model model) {
		logInfo = logInfoService.load(logInfo);
		model.addAttribute("logInfo", logInfo);
		return "/dsoul/logInfo/logInfo_detail.jsp";
	}

	@RequestMapping("/create")
	public @ResponseBody Result create(LogInfo logInfo) {
		if (logInfo != null) {
			logInfoService.save(logInfo);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	
	@RequestMapping("/update")
	public @ResponseBody Result update(LogInfo logInfo) {
		if (logInfo != null) {
			logInfoService.updateIgnoreNull(logInfo);
			return new Result("保存成功!");
		} else {
			return new Result("数据传输失败!");
		}
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Result delete(LogInfo logInfo) {
		// TODO 有些关键数据是不能物理删除的，需要改为逻辑删除
		logInfoService.delete(logInfo);
		return new Result("删除成功!");
	}

	@Autowired(required = true)
	public void setLogInfoService(
			@Qualifier("logInfoService") ILogInfoService logInfoService) {
		this.logInfoService = logInfoService;
	}
}
