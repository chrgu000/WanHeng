package com.cgwas.menuInfo.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.cgwas.menuInfo.entity.MenuInfo;
import com.cgwas.menuInfo.entity.MenuInfoVo;
import com.cgwas.menuInfo.service.api.IMenuInfoService;
import com.cgwas.rolePrivilege.dao.api.IRolePrivilegeDao;
import com.cgwas.rolePrivilege.service.api.IRolePrivilegeService;
/**
 * 
 * @author yubangqiong
 *
 */
@Controller
@RequestMapping("cgwas/menuInfoAction")
public class MenuInfoAction {
	private IMenuInfoService menuInfoService;
	@Autowired
	private IRolePrivilegeService rolePrivilegeService;
	@Autowired
	private IRolePrivilegeDao rolePrivilegeDao;
	
	private static final Logger LOG = Logger.getLogger(MenuInfoAction.class);
	@RequestMapping("/index")
	public String index() {
		return "/dsoul/menuInfo/menuInfo_index.jsp";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/list")
	public Result list(String data, HttpServletRequest request, Model model) {
		LOG.info("------------------------------------加载MenuInfoAction.list()方法--------------------------------------");
		Map<String, Object> map=new HashMap<String, Object>();
		data="{'page':{'pageSize':'3','pageNo':'0'},'object':{'menu_id':1}}";
		try {
			JSONObject json=JSON.parseObject(data);
			JSON object=(JSON) JSON.toJSON(json.get("object"));
			MenuInfoVo menuInfoVo=JSONObject.toJavaObject(object, MenuInfoVo.class) ;
			Map<String,String> params=(Map<String, String>) json.get("page");
			Page page = PageUtils.createPage(params);
			page = menuInfoService.page(page, menuInfoVo);
			map = new HashMap<String,Object>();
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", page.getDataList());
			return new Result("MC00000", map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("00001", map);
		}
	}
	
	
	@RequestMapping("/update_page")
	public String update_page(MenuInfo menuInfo, Model model) {
		menuInfo = menuInfoService.load(menuInfo);
		model.addAttribute("menuInfo", menuInfo);
		return "/dsoul/menuInfo/menuInfo_update.jsp";
	}

	@ResponseBody
	@RequestMapping("/detail_page")
	public String detail_page(MenuInfo menuInfo, Model model) {
		Map<String, Object> map=new HashMap<String, Object>();
		return "/dsoul/menuInfo/menuInfo_detail.jsp";
	}
	
	@ResponseBody
	@RequestMapping("/create")
	public Result create(String data) {
		LOG.info("------------------------------------加载MenuInfoAction.create()方法--------------------------------------");
		data="{'object':{'privilege_name':'删除权限','privilege_url':'update.json','privilege_mark':'UPDATE','menu_id':1}}";
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			JSONObject param=JSON.parseObject(data);
			JSON object=(JSON) JSON.toJSON(param.get("object"));
			MenuInfoVo menuInfoVo=JSONObject.toJavaObject(object, MenuInfoVo.class) ;
			menuInfoService.save(menuInfoVo);
			return new Result("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("MC00000", map);
		}
	}
	
	@RequestMapping("/update")
	public Result update(String data,HttpServletResponse response) {
		LOG.info("------------------------------------加载MenuInfoAction.update()方法--------------------------------------");
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");  
		//data="{'object':{'id':8,'privilege_name':'删除权限','privilege_url':'update.json','privilege_mark':'UPDATE','menu_id':1}}";
		try {
			//JSONObject param=JSON.parseObject(data);
			//JSON object=(JSON) JSON.toJSON(param.get("object"));
			//MenuInfoVo menuInfoVo=JSONObject.toJavaObject(object, MenuInfoVo.class) ;
			//if (menuInfoVo != null) {
				//menuInfoService.updateIgnoreNull(menuInfoVo);
				return new Result("保存成功!");
			//} else {
				//return new Result("数据传输失败!");
			//}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("数据传输失败!");
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	@Transactional
	public Result delete(MenuInfo menuInfo,String id) {
		LOG.info("------------------------------------加载MenuInfoAction.delete()方法--------------------------------------");
		id="10,11";
		String[] ids=id.split(",");
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("ids", ids);
		//状态标识是根据对应的字段删除数据
		param.put("status", 2);
		try {
			//TODO 执行删除权限相关表的信息操作
			menuInfoService.deleteAll(param,menuInfo);
			return new Result("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("CC00000", "");
		}
	}

	@Autowired(required = true)
	public void setMenuInfoService(
			@Qualifier("menuInfoService") IMenuInfoService menuInfoService) {
		this.menuInfoService = menuInfoService;
	}
}
