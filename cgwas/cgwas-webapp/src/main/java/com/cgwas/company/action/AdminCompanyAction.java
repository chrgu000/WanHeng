package com.cgwas.company.action;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.CommonUtil;
import com.cgwas.common.utils.FileUtils;
import com.cgwas.common.utils.IdcardInfoExtractor;
import com.cgwas.common.utils.OSSFilesUtil;
import com.cgwas.company.entity.AdminCompany;
import com.cgwas.company.entity.Company;
import com.cgwas.company.service.api.ICompanyService;
import com.cgwas.companyAuthInfo.entity.AttestationCompanyAuthInfo;
import com.cgwas.companyAuthInfo.entity.CompanyAuthInfo;
import com.cgwas.companyAuthInfo.service.api.ICompanyAuthInfoService;
import com.cgwas.companyGrowth.service.api.ICompanyGrowthService;
import com.cgwas.forbid.entity.CompanyForbid;
import com.cgwas.forbid.entity.Forbid;
import com.cgwas.forbid.entity.ForbidCompany;
import com.cgwas.forbid.service.api.IForbidService;
import com.cgwas.recommend.entity.CompanyRecommend;
import com.cgwas.recommend.service.api.IRecommendService;
import com.cgwas.rewardsRecord.entity.RewardsRecord;
import com.cgwas.rewardsRecord.service.api.IRewardsRecordService;
import com.cgwas.sector.entity.Sector;
import com.cgwas.user.entity.User;
import com.cgwas.userAuthInfo.entity.UserAuthInfo;
import com.cgwas.userAuthInfo.service.api.IUserAuthInfoService;
import com.cgwas.userCompany.service.api.IUserCompanyService;
import com.cgwas.userCredibility.service.api.IUserCredibilityService;
import com.cgwas.userGrowth.entity.CompanyForGrowth;
import com.cgwas.userGrowth.service.api.IUserGrowthService;
import com.cgwas.userInfo.entity.UserInfo;
import com.cgwas.userInfo.service.api.IUserInfoService;

/**
 * 公司管理Action
 * 
 * @author Lingwh
 * 
 */
@Controller
@RequestMapping("cgwas/companyAction")
public class AdminCompanyAction {
	ICompanyService companyService = null;
	IUserCompanyService userCompanyService = null;
	private IForbidService forbidService = null;
	private IUserGrowthService userGrowthService = null;
	private IRecommendService recommendService = null;
	private ICompanyGrowthService companyGrowthService = null;
	private IRewardsRecordService rewardsRecordService = null;
	@Autowired
	private ICompanyAuthInfoService companyAuthInfoService = null;
	@Autowired
	private IUserCredibilityService userCredibilityService;
	@Autowired
	private IUserAuthInfoService userAuthInfoService;

	@Autowired
	private IUserInfoService userInfoService;

	@ResponseBody
	/**
	 * 添加公司(R14)
	 * @param company
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addCompany")
	public Result addCompany(Company company, HttpServletRequest request,
			HttpServletResponse response, Sector sector,
			CompanyAuthInfo companyAuthInfo, UserAuthInfo userAuthInfo,
			MultipartFile charter_paths, MultipartFile idcard_pic_paths,
			MultipartFile idcard_pic_path_backs, String legal_person) throws IOException {

		User loginUser = this.getLoginUser(request);
		Result retn = new Result(Boolean.TRUE, "成功", null);

		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		String idCardNum = userAuthInfo.getIdcard().trim();
		// IdcardValidator iv = new IdcardValidator();

		// // 判断身份证合法性
		// if (!(iv.isValidate18Idcard(idCardNum) || iv
		// .isValidate18Idcard(idCardNum))) {
		// return new Result("RY0071", null); // 身份证不合法
		// }
		//
		// if(idCardNum.length()!=15&&idCardNum.length()!=18){
		//
		// return new Result("RY0071", null); // 身份证不合法
		// }

		Long idcount = userAuthInfoService.getHaveIdCard(userAuthInfo
				.getIdcard());
		if (idcount > 0) {
			return new Result("RY0066", null); // 身份证已经被使用
		}

		// base64转图片

		if (!idcard_pic_paths.isEmpty()) { // 保存用户身份证正面
//			byte[] fileByte = FileUtils
//					.base64ToByte(idcard_pic_path.toString());
			String savePath2 = FileUtils.getSaveUserPath(loginUser.getUuid(),
					"idcard_pic");
			OSSFilesUtil.uploadDocumentByString(savePath2, idcard_pic_paths.getBytes());
			userAuthInfo.setIdcard_pic_path(savePath2);
		} else {

			return new Result("RY0056", null); // 身份证正面上传错误
		}

		// base64转图片
		if (!idcard_pic_path_backs.isEmpty()) { // 保存用户身份证反面
//			byte[] fileByte = FileUtils.base64ToByte(idcard_pic_path_back
//					.toString());
			String savePath3 = FileUtils.getSaveUserPath(loginUser.getUuid(),
					"idcard_pic_back");
			OSSFilesUtil.uploadDocumentByString(savePath3, idcard_pic_path_backs.getBytes());
			userAuthInfo.setIdcard_pic_path_back(savePath3);
		} else {

			return new Result("RY0057", null); // 身份证反面上传错误
		}

		// 保存用户信息
		String province = idCardNum.substring(0, 2) + "0000";
		String city = idCardNum.substring(0, 4) + "00";
		String area = idCardNum.substring(0, 6);
		UserInfo saveUserInfo = new UserInfo();
		saveUserInfo.setProvince(province);
		saveUserInfo.setCity(city);
		saveUserInfo.setArea(area);
		IdcardInfoExtractor info = new IdcardInfoExtractor(idCardNum);
		String sex = info.getGender();

		// 赋值并保存
		saveUserInfo.setSex(sex);
		saveUserInfo.setBirth(info.getBirthday());
		saveUserInfo.setUser_id(loginUser.getId());
		userInfoService.updateIgnoreNull(saveUserInfo);

		// 认证信息
		userAuthInfo.setUser_id(loginUser.getId());
		userAuthInfo.setStatus("未认证");
		// 空则创建
		userInfoService.getUserInfoById(loginUser.getId());
		UserInfo userInfo = new UserInfo();
		userInfo.setName(legal_person);
		userInfo.setUser_id(loginUser.getId());
		// 保存信息
		userInfoService.updateUserInfoByUserId(userInfo);

		userAuthInfoService.updateIgnoreNull(userAuthInfo);
		StringBuffer base64s = new StringBuffer();
		base64s.append(CommonUtil.byte2Base64StringFun(charter_paths.getBytes()));
		// 保存公司及附属信息
		retn = companyService.saveCompany(loginUser.getId(), "1", company,
				sector, companyAuthInfo, loginUser.getUuid(), base64s);
		return retn;
	}

	/**
	 * 添加公司(文件表单)(R14A)
	 * 
	 * @param company
	 * @param request
	 * @param response
	 * @param sector
	 * @param companyAuthInfo
	 * @param userAuthInfo
	 * @param charter_path
	 * @param idcard_pic_path
	 * @param idcard_pic_path_back
	 * @param legal_person
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("addCompanyA")
	public Result addCompanyA(Company company, HttpServletRequest request,
			HttpServletResponse response, Sector sector,
			CompanyAuthInfo companyAuthInfo, UserAuthInfo userAuthInfo,
			MultipartFile charter_path, MultipartFile idcard_pic_path,
			MultipartFile idcard_pic_path_back, String legal_person)
			throws IOException {

		User loginUser = this.getLoginUser(request);
		Result retn = new Result(Boolean.TRUE, "成功", null);

		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		String idCardNum = userAuthInfo.getIdcard();
		// IdcardValidator iv = new IdcardValidator();

		// // 判断身份证合法性
		// if (!(iv.isValidate18Idcard(idCardNum) || iv
		// .isValidate18Idcard(idCardNum))) {
		// return new Result("RY0071", null); // 身份证不合法
		// }

		// if(idCardNum.length()!=15&&idCardNum.length()!=18){
		//
		// return new Result("RY0071", null); // 身份证不合法
		// }
		// base64转图片
		if (!idcard_pic_path.isEmpty()) {
			byte[] fileByte = idcard_pic_path.getBytes();
			String savePath2 = FileUtils.getSaveUserPath(loginUser.getUuid(),
					"idcard_pic");
			OSSFilesUtil.uploadDocumentByString(savePath2, fileByte);
			userAuthInfo.setIdcard_pic_path(savePath2);
		} else {

			return new Result("RY0056", null); // 身份证正面上传错误
		}

		// base64转图片
		if (idcard_pic_path_back.isEmpty()) { // 保存用户身份证反面
			byte[] fileByte = idcard_pic_path_back.getBytes();
			String savePath3 = FileUtils.getSaveUserPath(loginUser.getUuid(),
					"idcard_pic_back");
			OSSFilesUtil.uploadDocumentByString(savePath3, fileByte);
			userAuthInfo.setIdcard_pic_path_back(savePath3);
		} else {

			return new Result("RY0057", null); // 身份证反面上传错误
		}

		// 保存用户信息
		String province = idCardNum.substring(0, 2) + "0000";
		String city = idCardNum.substring(0, 4) + "00";
		String area = idCardNum.substring(0, 6);
		UserInfo saveUserInfo = new UserInfo();
		saveUserInfo.setProvince(province);
		saveUserInfo.setCity(city);
		saveUserInfo.setArea(area);
		IdcardInfoExtractor info = new IdcardInfoExtractor(idCardNum);
		String sex = info.getGender();

		// 赋值并保存
		saveUserInfo.setSex(sex);
		saveUserInfo.setBirth(info.getBirthday());
		saveUserInfo.setUser_id(loginUser.getId());
		userInfoService.updateIgnoreNull(saveUserInfo);

		Long idcount = userAuthInfoService.getHaveIdCard(userAuthInfo
				.getIdcard());
		if (idcount > 0) {
			return new Result("RY0066", null); // 身份证已经被使用
		}

		// 认证信息
		userAuthInfo.setUser_id(loginUser.getId());
		userAuthInfo.setStatus("未认证");
		// 空则创建
		userInfoService.getUserInfoById(loginUser.getId());
		UserInfo userInfo = new UserInfo();
		userInfo.setName(legal_person);
		userInfo.setUser_id(loginUser.getId());
		// 保存信息
		userInfoService.updateUserInfoByUserId(userInfo);

		userAuthInfoService.updateIgnoreNull(userAuthInfo);
		charter_path.getBytes();
		// 保存公司及附属信息
		retn = companyService.saveCompanyA(loginUser.getId(), "1", company,
				sector, companyAuthInfo, loginUser.getUuid(), charter_path);
		return retn;
	}

	/**
	 * 获取可禁用公司列表（R25）
	 * 
	 * @param start
	 *            开始记录数
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getForbidCompanyList")
	public Result getForbidCompanyList(Long start, String pageFlag,
			HttpServletRequest request, ForbidCompany forbidCompany,
			String sortColumn, String sortType, HttpServletResponse response) {
		Page page = null;
		Long total = 0l;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = forbidService.getCompanyListForForbidCount(forbidCompany);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = forbidService.getCompanyListForForbidCount(forbidCompany);
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
		List<ForbidCompany> companyList = forbidService
				.getCompanyListForForbid(forbidCompany, page);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart());
		retn.put("companyList", companyList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total);

		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 得到可修改公司积分列表（R29）
	 * 
	 * @param start
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getGrowthCompany")
	public Result getGrowthCompany(Long start, String pageFlag,
			CompanyForGrowth companyForGrowth, String sortColumn,
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
			total = userGrowthService
					.getCompanyListForGrowthCount(companyForGrowth);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = userGrowthService
					.getCompanyListForGrowthCount(companyForGrowth);
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
		// 获取公司成长积分列表
		List<CompanyForGrowth> companyList = userGrowthService
				.getCompanyListForGrowth(companyForGrowth, page);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("companyList", companyList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);
	};

	/**
	 * 得到公司推荐列表（R34）
	 * 
	 * @param start
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getRecommendCompanyList")
	public Result getRecommendCompanyList(Long start, String pageFlag,
			CompanyRecommend companyRecommend, String sortColumn,
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
			total = recommendService
					.getCompanyListForRecommendCount(companyRecommend);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = recommendService
					.getCompanyListForRecommendCount(companyRecommend);
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
		// 获取公司推荐列表
		List<CompanyRecommend> companyList = recommendService
				.getCompanyListForRecommend(page, companyRecommend);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("companyList", companyList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 批量删除公司（R41）
	 * 
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("batchDeleteCompany")
	public Result batchDeleteCompany(String companyIdList,
			HttpServletRequest request, HttpServletResponse response) {
		// 将String[] 转换为long[]
		String[] idList = companyIdList.split(",");
		List<Long> deleteCompanyIdList = new ArrayList<Long>();
		for (int i = 0; i < idList.length; i++) {
			deleteCompanyIdList.add(Long.valueOf(idList[i]));
		}
		companyService.batchDeleteCompany(deleteCompanyIdList);
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 公司成长积分奖罚（R42）
	 * 
	 * @param action
	 * @param growthNum
	 * @param companyId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("reduceOrAddGrowthCompany")
	public Result reduceOrAddGrowthCompany(String action, Integer growthNum,
			Long companyId, RewardsRecord rewardsRecord,
			HttpServletRequest request, HttpServletResponse response) {
		if (rewardsRecord == null) { // 判断记录信息是否为空
			return new Result("RY0017", null);
		}
		// 处理奖罚请求
		companyGrowthService.reduceOrAddGrowthCompany(action, growthNum,
				companyId, rewardsRecord); // 改变用户积分

		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 查看公司奖罚记录(R43)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCompanyRewardsRecordList")
	public Result getCompanyRewardsRecordList(Long company_id,
			HttpServletRequest request, HttpServletResponse response) {
		// 得到公司奖罚记录列表
		List<RewardsRecord> retnList = rewardsRecordService
				.getRewardsRecordListByCompanyId(company_id);
		return new Result(Boolean.TRUE, "成功!", retnList);

	}

	/**
	 * 禁用公司(R44)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("forbidConpany") public Result forbidConpany(Long
	 * company_id, Forbid forbid, HttpServletRequest request,
	 * HttpServletResponse response) { Forbid newForbid = new Forbid();
	 * newForbid.setType(0L); // 1 用户 0 公司 newForbid.setFor_id(company_id);
	 * newForbid.setCreat_time(new Date()); forbidService.save(newForbid);
	 * return new Result(Boolean.TRUE, "成功!", null); }
	 */

	/**
	 * 根据ID得到公司禁用记录（R46）
	 * 
	 * @param company_id
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCompanyForbidListByuserId")
	public Result getCompanyForbidListByuserId(Long company_id,
			HttpServletRequest request, HttpServletResponse response) {
		List<CompanyForbid> retn = forbidService
				.getConpanyForbidList(company_id);
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 获取认证公司信息表 （R82）
	 * 
	 * @param start
	 * @param pageFlag
	 * @param attestationCompanyAuthInfo
	 * @param sortColumn
	 * @param sortType
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCompanyAuthInfoList")
	public Result getCompanyAuthInfoList(Long start, String pageFlag,
			AttestationCompanyAuthInfo attestationCompanyAuthInfo,
			String sortColumn, String sortType, HttpServletRequest request,
			HttpServletResponse response) {
		Page page = null;
		Long total = 0l;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = companyAuthInfoService
					.getCompanyAuthInfoListCount(attestationCompanyAuthInfo);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = companyAuthInfoService
					.getCompanyAuthInfoListCount(attestationCompanyAuthInfo);
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
		List<AttestationCompanyAuthInfo> companyList = companyAuthInfoService
				.getCompanyAuthInfoList(attestationCompanyAuthInfo, page);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("companyList", companyList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 获取可管理公司列表（R88）
	 * 
	 * @param start
	 * @param pageFlag
	 * @param adminCompany
	 * @param sortColumn
	 * @param sortType
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCompanyListForAdmin")
	public Result getCompanyListForAdmin(Long start, String pageFlag,
			AdminCompany adminCompany, String sortColumn, String sortType,
			HttpServletRequest request, HttpServletResponse response) {
		Page page = null;
		Long total = 0l;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = companyService.getAdminCompanyListCount(adminCompany);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = companyService.getAdminCompanyListCount(adminCompany);
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
		List<AdminCompany> companyList = companyService.getAdminCompanyList(
				adminCompany, page);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("companyList", companyList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 禁用公司(R84)
	 * 
	 * @param forbid
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("forbidCompany")
	public Result forbidCompany(Forbid forbid, HttpServletRequest request,
			HttpServletResponse response) {
		// 添加一条禁止记录
		Forbid newForbid = forbid;
		newForbid.setCreat_time(new Date());
		newForbid.setType(0L); // 1 用户 0 公司
		forbidService.save(newForbid);
		return new Result(Boolean.TRUE, "成功!", null);

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
	public void setCompanyService(
			@Qualifier("companyService") ICompanyService companyService) {
		this.companyService = companyService;
	}

	@Autowired(required = true)
	public void setUserCompanyService(
			@Qualifier("userCompanyService") IUserCompanyService userCompanyService) {
		this.userCompanyService = userCompanyService;
	}

	@Autowired(required = true)
	public void setUserGrowthService(
			@Qualifier("userGrowthService") IUserGrowthService userGrowthService) {
		this.userGrowthService = userGrowthService;
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

	@Autowired(required = true)
	public void setCompanyGrowthService(
			@Qualifier("companyGrowthService") ICompanyGrowthService companyGrowthService) {
		this.companyGrowthService = companyGrowthService;
	}

	@Autowired(required = true)
	public void setRewardsRecordService(
			@Qualifier("rewardsRecordService") IRewardsRecordService rewardsRecordService) {
		this.rewardsRecordService = rewardsRecordService;
	}

}
