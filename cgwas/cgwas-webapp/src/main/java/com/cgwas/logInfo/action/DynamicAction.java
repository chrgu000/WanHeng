package com.cgwas.logInfo.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.cgwas.logInfo.entity.LogInfoVo;
import com.cgwas.logInfo.service.api.ILogInfoService;
import com.cgwas.user.entity.User;
/**
 * 
 * @author yubangqiong
 *
 */
@Controller
@RequestMapping("cgwas/dynamicAction")
public class DynamicAction {
	@Autowired
	private ILogInfoService logInfoService;

	@RequestMapping("/index")
	public String index() {
		return "/dsoul/logInfo/logInfo_index.jsp";
	}

	@RequestMapping("/list")
	public Result list(String pageSize, String pageNo, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page page=null;
		User user = (User)request.getSession().getAttribute("loginUser");
		if(user==null){
			user= new User();
			user.setId(17l);
		}
		LogInfoVo logInfo= new LogInfoVo();
		Map<String, String> params = new HashMap<String, String>();
		params.put("pageSize", pageSize);
		params.put("pageNo", pageNo);
		page = PageUtils.createPage(params);
		logInfo.setUser_id(user.getId());
		page = logInfoService.page(page, logInfo);
		map = new HashMap<String, Object>();
		map.put("total", page.getTotal());
		map.put("pageSize", page.getLimit());
		map.put("pageNo", page.getCurrentPage());
		map.put("dataList", page.getDataList());
		return new Result(Boolean.TRUE, "成功", map);
		
	}
}
