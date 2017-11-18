package com.cgwas.company.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.userGroup.entity.UserGroup;
import com.cgwas.userGroup.service.api.IUserGroupService;

@Controller
@RequestMapping("cgwas/companyAction")
public class GroupAction {
	private IUserGroupService userGroupService = null;

	/**
	 * 添加用户组（R53）
	 * 
	 * @param userGroup
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addUserGroup")
	public Result addUserGroup(UserGroup userGroup, HttpServletRequest request,
			HttpServletResponse response) {
		// 设置默认参数
		userGroup.setType("0");
		userGroup.setIs_delete('N');
		userGroupService.save(userGroup);
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 获取用户组列表（R54）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserGroupList")
	public Result getUserGroupList(Long start, UserGroup userGroup,
			String pageFlag, HttpServletRequest request, String sortColumn,
			String sortType, HttpServletResponse response) {
		Page page = null;
		Long total = 0L;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = userGroupService.getUserGroupListCount(userGroup);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = userGroupService.getUserGroupListCount(userGroup);
			// 设置记录总数
			page.setTotal(total);
			if ("next".equals(pageFlag)) { // 上一页下一页
				page.nextPage();
			} else {
				page.prePage();
			}
		}
		// 排序参数
		page.setSortColumn(sortColumn);
		page.setSortType(sortType);
		// 获取用户组列表
		List<UserGroup> groupList = userGroupService.getUserGroupList(
				userGroup, page);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("groupList", groupList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 获取用户信息（R55）
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserGroupInfo")
	public Result getUserGroupInfo(Long id, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取用户信息
		UserGroup userGroup = userGroupService.getUserGroupById(id);
		return new Result(Boolean.TRUE, "成功!", userGroup);
	}

	/**
	 * 更新用户组信息 （R56）
	 * 
	 * @param userGroup
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateUserGroupInfo")
	public Result updateUserGroupInfo(UserGroup userGroup,
			HttpServletRequest request, HttpServletResponse response) {
		userGroupService.updateUserGroupInfo(userGroup);
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 批量删除用户组信息 （R57）
	 * 
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("batchDeleteUserGroup")
	public Result batchDeleteUserGroup(String ids, HttpServletRequest request,
			HttpServletResponse response) {
		String[] str1 = ids.split(",");// 将String[] 转换为long[]
		List<Long> str2 = new ArrayList<Long>();
		for (int i = 0; i < str1.length; i++) {
			str2.add(Long.valueOf(str1[i]));
		}
		if (str2.size() == 0) {
			return new Result("RY0019", null); // 数组为空
		}
		userGroupService.batchDeleteUserGroup(str2);
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 添加管理组信息（R58）
	 * 
	 * @param userGroup
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addCompanyGroup")
	public Result addCompanyGroup(UserGroup userGroup,
			HttpServletRequest request, HttpServletResponse response) {
		// 设置默认参数
		userGroup.setType("1");
		userGroup.setIs_delete('N');
		userGroupService.save(userGroup);
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 获取管理组列表（R59）
	 * 
	 * @param start
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCompanyGroupList")
	public Result getCompanyGroupList(Long start, UserGroup userGroup,
			String pageFlag, HttpServletRequest request, String sortColumn,
			String sortType, HttpServletResponse response) {
		Page page = null;
		Long total = 0l;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = userGroupService.getCompanyGroupListCount(userGroup);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = userGroupService.getCompanyGroupListCount(userGroup);
			// 设置记录总数
			page.setTotal(total);
			if ("next".equals(pageFlag)) { // 上一页下一页
				page.nextPage();
			} else {
				page.prePage();
			}
		}
		// 排序参数
		page.setSortColumn(sortColumn);
		page.setSortType(sortType);
		// 获取用户组列表
		List<UserGroup> groupList = userGroupService.getCompanyGroupList(
				userGroup, page);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("groupList", groupList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	@Autowired(required = true)
	public void setRecommendService(
			@Qualifier("userGroupService") IUserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

}
