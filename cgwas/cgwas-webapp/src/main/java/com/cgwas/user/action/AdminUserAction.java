package com.cgwas.user.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.CommonUtil;
import com.cgwas.forbid.entity.Forbid;
import com.cgwas.forbid.entity.ForbidUser;
import com.cgwas.forbid.entity.UserForbid;
import com.cgwas.forbid.service.api.IForbidService;
import com.cgwas.recommend.entity.Recommend;
import com.cgwas.recommend.entity.UserRecommend;
import com.cgwas.recommend.service.api.IRecommendService;
import com.cgwas.rewardsRecord.entity.RewardsRecord;
import com.cgwas.rewardsRecord.service.api.IRewardsRecordService;
import com.cgwas.user.entity.AdminUser;
import com.cgwas.user.entity.User;
import com.cgwas.user.service.api.IUserService;
import com.cgwas.userAuthInfo.entity.AttestationUserAuthInfo;
import com.cgwas.userAuthInfo.service.api.IUserAuthInfoService;
import com.cgwas.userGrowth.entity.UserForGrowth;
import com.cgwas.userGrowth.entity.UserGrowth;
import com.cgwas.userGrowth.service.api.IUserGrowthService;

/**
 * 用户管理Action
 * 
 * @author Lingwh
 * 
 */
@Controller
@RequestMapping("cgwas/userAction")
public class AdminUserAction {

	private IUserGrowthService userGrowthService = null;
	private IRewardsRecordService rewardsRecordService = null;
	private IUserService userService = null;
	private IForbidService forbidService = null;
	private IRecommendService recommendService = null;
	@Autowired
	private IUserAuthInfoService userAuthInfoService = null;

	/**
	 * 用户信息成长（自己）查询(R15)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserGrowth")
	public Result getUserGrowth(HttpServletRequest request,
			HttpServletResponse response) {

		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}

		UserGrowth retnGrowth = userGrowthService
				.getUserGrowthByUserId(loginUser.getId());
		if (retnGrowth == null) { // 检测非空
			// 新建该用户成长信息
			UserGrowth newUserGrowth = new UserGrowth();
			newUserGrowth.setUser_id(loginUser.getId());
			newUserGrowth.setFlat(0);
			newUserGrowth.setPrestige(0);
			userGrowthService.save(newUserGrowth);
			return new Result(Boolean.TRUE, "成功!", null);
		}

		return new Result(Boolean.TRUE, "成功!", retnGrowth); // 返回用户详细信息

	}

	/**
	 * 用户信息成长（公司）查询(R16)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserGrowthByUserId")
	public Result getUserGrowthByUserId(String userId,
			HttpServletRequest request, HttpServletResponse response) {

		UserGrowth retnGrowth = userGrowthService.getUserGrowthByUserId(Long
				.parseLong(userId));
		if (retnGrowth == null) { // 检测非空
			// 新建该用户成长信息
			UserGrowth newUserGrowth = new UserGrowth();
			newUserGrowth.setUser_id(Long.parseLong(userId));
			newUserGrowth.setFlat(0);
			newUserGrowth.setPrestige(0);
			userGrowthService.save(newUserGrowth);
			return new Result(Boolean.TRUE, "成功!", null);
		}
		return new Result(Boolean.TRUE, "成功!", retnGrowth); // 返回用户成长信息
	}

	/**
	 * 用户信息成长积分奖罚(R17)
	 * 
	 * @param action
	 *            动作（add/reduce/clean）
	 * @param growthNum
	 *            奖罚数量
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("reduceOrAddGrowth")
	public Result reduceOrAddGrowth(String action, Integer growthNum,
			Long userId, HttpServletRequest request,
			RewardsRecord rewardsRecord, HttpServletResponse response) {

		if (rewardsRecord == null) { // 检测添加记录参数
			return new Result("RY0012", null); // 参数为空

		}
		RewardsRecord newRewardsRecord = rewardsRecord;
		newRewardsRecord.setFlat(growthNum);
		newRewardsRecord.setUse_id(userId);
		newRewardsRecord.setTime(new Date());
		Integer prestige = 0;
		if ("reduce".equals(action)) {
			newRewardsRecord.setType("罚");
			if(rewardsRecord!=null&&rewardsRecord.getPrestige()!=null){
				prestige = -rewardsRecord.getPrestige();
			}
			
			
		} else if ("clear".equals(action)) {
			newRewardsRecord.setType("清");
			newRewardsRecord.setFlat(0);
			prestige = 0;
		} else {
			newRewardsRecord.setType("奖");
			prestige = rewardsRecord.getPrestige();
		}

		// 记录
		rewardsRecordService.save(newRewardsRecord);

		// 加减积分
		userGrowthService.reduceOrAddGrowth(action, growthNum, userId,prestige);

		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 获取自己奖罚记录（R18）
	 * 
	 * @param rewardsRecord
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserRewardsRecordList")
	public Result getUserRewardsRecordList(HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		List<RewardsRecord> retnList = rewardsRecordService
				.getRewardsRecordListByUserId(loginUser.getId());

		return new Result(Boolean.TRUE, "成功!", retnList);

	}

	/**
	 * 批量删除用户(R21)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("batchDeleteUser")
	public Result batchDeleteUser(String userIdList,
			HttpServletRequest request, HttpServletResponse response) {
		// 将String[] 转换为long[]
		String[] idList = userIdList.split(",");
		List<Long> deleteUserIdList = new ArrayList<Long>();
		for (int i = 0; i < idList.length; i++) {
			deleteUserIdList.add(Long.valueOf(idList[i]));
		}
		userService.batchDeleteUser(deleteUserIdList);
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 禁止用户（R22）
	 * 
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("forbidUser")
	public Result forbidUser(Forbid forbid, HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		
		if(!CommonUtil.checkAdmin(request)){
			
			return new Result("MS0005", null); // 非管理员
		}
		
		// 添加一条禁止记录
		Forbid newForbid = forbid;
		newForbid.setUser_id(loginUser.getId());
		newForbid.setCreat_time(new Date());
		newForbid.setType(1L); // 1 用户 0 公司
		forbidService.save(newForbid);
		return new Result(Boolean.TRUE, "成功!", null);

	}

	/**
	 * 获取用户被禁记录(用户)(R23)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserForbidList")
	public Result getUserForbidList(HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 获取自己被禁止记录
		List<UserForbid> retn = forbidService.getUserForbidList(loginUser
				.getId());

		return new Result(Boolean.TRUE, "成功!", retn);

	}

	/**
	 * 获取用户被禁记录(管理者)(R24)
	 * 
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserForbidListByuserId")
	public Result getUserForbidListByuserId(Long userId,
			HttpServletRequest request, HttpServletResponse response) {
		// 获取自己被禁止记录
		List<UserForbid> retn = forbidService.getUserForbidList(userId);

		return new Result(Boolean.TRUE, "成功!", retn);

	}

	/**
	 * 获取可禁用用户列表(R26)
	 * 
	 * @param start
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getForbidUserList")
	public Result getForbidUserList(Long start, String pageFlag,
			ForbidUser forbidUser, String sortColumn, String sortType,
			HttpServletRequest request, HttpServletResponse response) {
		Page page = null;
		Long total = 0l;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = forbidService.getUserListForForbidCount(forbidUser);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = forbidService.getUserListForForbidCount(forbidUser);
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
		// 获取可禁用公司列表
		List<ForbidUser> userList = forbidService.getUserListForForbid(
				forbidUser, page);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 赋值当前记录数
		retn.put("userList", userList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量

		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 得到可修改用户积分列表（R28）
	 * 
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getGrowthUser")
	public Result getGrowthUser(Long start, String pageFlag,
			UserForGrowth growth, String sortColumn, String sortType,
			HttpServletRequest request, HttpServletResponse response) {
		Page page = null;
		Long total = 0l;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = userGrowthService.getUserListForGrowthCount(growth);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = userGrowthService.getUserListForGrowthCount(growth);
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

		// 获取用户成长积分列表
		List<UserForGrowth> userList = userGrowthService.getUserListForGrowth(
				growth, page);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 赋值当前记录数
		retn.put("userList", userList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);
	};

	/**
	 * 用户和公司推荐(R31)
	 * 
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("userAndCompanyRecommend")
	public Result userAndCompanyRecommend(Recommend recommend,
			HttpServletRequest request, HttpServletResponse response) {
		
		Recommend retn = recommendService.getRecommendById(recommend);
		if (retn == null) { // 若为空则保存一条
			retn = new Recommend();
			retn.setRelation_id(recommend.getRelation_id());
			retn.setType(recommend.getType());
			retn.setPriority("1");
			retn.setStatus("1");
			recommendService.save(recommend);
		}
		recommendService.updateIgnoreNull(recommend);
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 更改用户推荐状态(R32)
	 * 
	 * @param recommend
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateUserAndCompanyRecommend")
	public Result updateUserAndCompanyRecommend(Recommend recommend,
			HttpServletRequest request, HttpServletResponse response) {
		if (recommend.getType() == null) {
			return new Result("RY0015", null); // 推荐状态缺失
		} else if (!"公司".equals(recommend.getType())
				&& !"人员".equals(recommend.getType())) {
			return new Result("RY0016", null); // 更改推荐类型为空
		}
		recommendService.updateUserAndCompanyRecommend(recommend);
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 得到修改用户推荐列表 (R33)
	 * 
	 * @param start
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getRecommendUserList")
	public Result getRecommendUserList(Long start, String pageFlag,
			UserRecommend userRecommend, String sortColumn, String sortType,
			HttpServletRequest request, HttpServletResponse response) {
		Page page = null;
		Long total = 0l;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = recommendService
					.getUserListForRecommendCount(userRecommend);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = recommendService
					.getUserListForRecommendCount(userRecommend);
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
		// 获取用户推荐列表
		List<UserRecommend> userList = recommendService
				.getUserListForRecommend(userRecommend, page);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 赋值当前记录数
		retn.put("userList", userList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);

	}

	/**
	 * 根据用户或公司ID查询是否被禁用(R45)
	 * 
	 * @param id
	 *            查询的ID
	 * @param type
	 *            查询的类型
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("checkForbidUserOrCompany")
	public Result checkForbidUserOrCompany(Long id, Long type,
			HttpServletRequest request, HttpServletResponse response) {
		Forbid forbid = new Forbid();
		forbid.setType(type);
		forbid.setFor_id(id);

		Forbid retnForbid = forbidService.getNewForbid(forbid);
		if (retnForbid == null) {// 判断是否被禁用
			return new Result(Boolean.TRUE, "没有禁用", null);
		}

		Date date = new Date();
		if (retnForbid.getValidity().getTime() > date.getTime()) { // 还未到解禁日期
			return new Result(Boolean.TRUE, "正在禁用日期", retnForbid); // 返回禁用结果
		} else {
			return new Result(Boolean.TRUE, "没有禁用", null);
		}
	}

	/**
	 * 得到用户认证信息列表（R83）
	 * 
	 * @param start
	 * @param pageFlag
	 * @param attestationUserAuthInfo
	 * @param sortColumn
	 * @param sortType
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserAuthInfoList")
	public Result getUserAuthInfoList(Long start, String pageFlag,
			AttestationUserAuthInfo attestationUserAuthInfo, String sortColumn,
			String sortType, HttpServletRequest request,
			HttpServletResponse response) {
		Page page = null;
		Long total = 0l;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = userAuthInfoService
					.getUserAuthInfoListCount(attestationUserAuthInfo);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = userAuthInfoService
					.getUserAuthInfoListCount(attestationUserAuthInfo);
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
		// 获取公司列表
		List<AttestationUserAuthInfo> userList = userAuthInfoService
				.getUserAuthInfoList(attestationUserAuthInfo, page);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("userList", userList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);

	}

	/**
	 * 获取管理用户列表（R87）
	 * 
	 * @param start
	 * @param pageFlag
	 * @param adminUser
	 * @param sortColumn
	 * @param sortType
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserListForAdmin")
	public Result getUserListForAdmin(Long start, String pageFlag,
			AdminUser adminUser, String sortColumn, String sortType,
			HttpServletRequest request, HttpServletResponse response) {
		Page page = null;
		Long total = 0l;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = userService.getAdminUserListCount(adminUser);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = userService.getAdminUserListCount(adminUser);
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
		// 获取公司列表
		List<AdminUser> userList = userService
				.getAdminUserList(adminUser, page);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("userList", userList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 根据id得到推荐详情(R110)
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getRecommendById")
	public Result getRecommendById(Recommend recommend,
			HttpServletRequest request, HttpServletResponse response) {

		Recommend retn = recommendService.getRecommendById(recommend);
		if (retn == null) { // 若为空则保存一条
			retn = new Recommend();
			retn.setRelation_id(recommend.getRelation_id());
			retn.setType(recommend.getType());
			retn.setPriority("1");
			retn.setStatus("1");
			recommendService.save(retn);
		}
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 检测登录状态
	 * 
	 * @param request
	 * @return
	 */
	private User getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession(); // session
		return (User) session.getAttribute("loginUser");
	}

	@Autowired(required = true)
	public void setUserGrowthService(
			@Qualifier("userGrowthService") IUserGrowthService userGrowthService) {
		this.userGrowthService = userGrowthService;
	}

	@Autowired(required = true)
	public void setRewardsRecordService(
			@Qualifier("rewardsRecordService") IRewardsRecordService rewardsRecordService) {
		this.rewardsRecordService = rewardsRecordService;
	}

	@Autowired(required = true)
	public void setUserService(
			@Qualifier("userService") IUserService userService) {
		this.userService = userService;
	}

	@Autowired(required = true)
	public void setForbidService(
			@Qualifier("forbidService") IForbidService forbidService) {
		this.forbidService = forbidService;
	}

	@Autowired(required = true)
	public void setRecommendService(
			@Qualifier("recommendService") IRecommendService recommendService) {
		this.recommendService = recommendService;
	}

}
