package com.cgwas.userPrivilege.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.cgwas.userPrivilege.entity.UserPrivilegeVo;
import com.cgwas.userPrivilege.service.api.IUserPrivilegeService;
/**
 * 
 * @author yubangqiong
 *
 */
@Controller
@RequestMapping("cgwas/uPrivilegeAction")
public class UPrivilegeAction {
	@Autowired
	private IUserPrivilegeService userPrivilegeService;
	private static final Logger LOG = Logger.getLogger(UPrivilegeAction.class);

	@RequestMapping("/list")
	public @ResponseBody Result list(Long pageSize,Long pageNo, HttpServletRequest request, UserPrivilegeVo userPrivilegeVo) {
		LOG.info("------------------------------------加载UPrivilegeAction.list()方法--------------------------------------");
		Date startTime= new Date();
		if(pageSize==null){
			pageSize=20l;
		}
		if(pageNo==null){
			pageNo=1l;
		}
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			Map<String,String> params=new HashMap<String,String>();
			params.put("pageSize", pageSize+"");
			params.put("pageNo", pageNo+"");
			Page page = PageUtils.createPage(params);
			page = userPrivilegeService.page(page, userPrivilegeVo);
			map = new HashMap<String,Object>();
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", page.getDataList());
			LOG.info("接口请求时间："+(new Date().getTime()-startTime.getTime())+"毫秒");
			return new Result(Boolean.TRUE, "成功",map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	/**
	 * 添加或者更新权限
	 * @param userPrivilegeVo
	 * @return
	 */
	@RequestMapping("/create")
	public @ResponseBody Result create(UserPrivilegeVo userPrivilegeVo) {
		LOG.info("------------------------------------加载UPrivilegeAction.create()方法--------------------------------------");
		if(userPrivilegeVo.getIds()==null || userPrivilegeVo.getUser_id()==null){
			return new Result("SYS_PARAMETER",null);
		}
		try {
			boolean flag=userPrivilegeService.save(userPrivilegeVo);
			if(flag){
				return new Result("更新权限成功！");
			}
			return new Result("SYS_DATA_ERRO",null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Result delete(long[] ids) {
		LOG.info("------------------------------------加载UPrivilegeAction.delete()方法--------------------------------------");
		if(ids==null){
			return new Result("SYS_PARAMETER",null);
		}
		try {
			UserPrivilegeVo userPrivilegeVo = new UserPrivilegeVo();
			userPrivilegeVo.setIds(ids);
			/**
			 * 根据用户id删除用户所有权限关系
			 */
			userPrivilegeService.deleteAll(userPrivilegeVo);
			return new Result("删除用户权限成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
}
