package com.cgwas.company.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cgwas.animationlighttask.entity.AnimationLightTaskVo;
import com.cgwas.animationlighttask.service.api.IAnimationLightTaskService;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.FileUtils;
import com.cgwas.common.utils.OSSFilesUtil;
import com.cgwas.company.entity.Company;
import com.cgwas.company.service.api.ICompanyService;
import com.cgwas.companyAuthInfo.entity.CompanyAuthInfo;
import com.cgwas.companyAuthInfo.entity.CompanyLegalPerson;
import com.cgwas.companyAuthInfo.service.api.ICompanyAuthInfoService;
import com.cgwas.companyCredibility.entity.CompanyCredibility;
import com.cgwas.companyCredibility.service.api.ICompanyCredibilityService;
import com.cgwas.companyEvaluation.entity.CompanyEvaluation;
import com.cgwas.companyEvaluation.entity.CompanyEvaluationVo;
import com.cgwas.companyEvaluation.service.api.ICompanyEvaluationService;
import com.cgwas.companyGrowth.entity.CompanyGrowth;
import com.cgwas.companyGrowth.service.api.ICompanyGrowthService;
import com.cgwas.message.action.MessageAction;
import com.cgwas.message.entity.MessageVo;
import com.cgwas.message.service.api.IMessageService;
import com.cgwas.message.service.api.ISendMessageService;
import com.cgwas.messageDetail.entity.MessageDetail;
import com.cgwas.messageDetail.service.api.IMessageDetailService;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.sector.entity.Sector;
import com.cgwas.sector.service.api.ISectorService;
import com.cgwas.user.entity.User;
import com.cgwas.user.service.api.IUserService;
import com.cgwas.userAuthInfo.entity.UserAuthInfo;
import com.cgwas.userAuthInfo.service.api.IUserAuthInfoService;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userCompany.service.api.IUserCompanyService;
import com.cgwas.userInfo.entity.UserInfo;
import com.cgwas.userInfo.service.api.IUserInfoService;

@Controller
@RequestMapping("cgwas/companyAction")
public class CompanyInfoAction {
	@Autowired
	private ICompanyAuthInfoService companyAuthInfoService = null;
	@Autowired
	private ICompanyService companyService = null;
	@Autowired
	private ISectorService sectorService = null;
	@Autowired
	private ICompanyCredibilityService companyCredibilityService = null;
	@Autowired
	private ICompanyGrowthService companyGrowthService = null;
	@Autowired
	private IUserService userService = null;
	@Autowired
	private IUserInfoService userInfoService = null;
	@Autowired
	private ISendMessageService sendMessageService = null;
	@Autowired
	private IUserAuthInfoService userAuthInfoService = null;

	// OSS工具类
	OSSFilesUtil oSSFilesUtil = new OSSFilesUtil();
	@Autowired
	private ICompanyEvaluationService evaluationService;
	@Autowired
	private IUserCompanyService userCompanyService;
	@Autowired
	private IModelTaskService modelTaskService;
	@Autowired
	private IAnimationLightTaskService animationLightTaskService;
	@Autowired
	private IMessageDetailService messageDetailService;
	@Autowired
	private IMessageService messageService;

	// 文件工具类
	/**
	 * 根据公司ID得到公司认证信息(R35)
	 * 
	 * @param companyId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCompanyAuthInfoStatus")
	public Result getCompanyAuthInfoStatus(Long companyId,
			HttpServletRequest request, HttpServletResponse response) {

		// 获取认证状态
		CompanyAuthInfo companyAuthInfo = companyAuthInfoService
				.getCompanyAuthInfoByCompanyId(companyId);
		return new Result(Boolean.TRUE, "成功!", companyAuthInfo);
	};

	/**
	 * 更改公司基本信息(R36)
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateCompany")
	public Result updateCompany(Company company, HttpServletRequest request,
			HttpServletResponse response, StringBuffer head_pic_path) {

		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 赋值公司id
		HttpSession session = request.getSession(); // session
//		Long companyId = (Long) session.getAttribute("sessionCompany");
//		if (companyId == null) {
//
//			return new Result("RY0065", null); // 无选中公司
//		}
		company.setId(company.getId());

		if (head_pic_path != null && !"".equals(head_pic_path)) { // 保存头像路径
			byte[] fileByte = FileUtils.base64ToByte(head_pic_path.toString());
			String path = FileUtils.getSaveCompanyPath(loginUser.getUuid(),
					"head_pic_path", company.getId());

			OSSFilesUtil.uploadDocumentByString(path, fileByte);
			company.setHead_pic_path(path);
		}

		companyService.updateCompanyByCompanyId(company);

		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 更改公司行业信息(R37)
	 * 
	 * @param sector
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateSector")
	public Result updateSector(Sector sector, HttpServletRequest request,
			HttpServletResponse response) {

		sectorService.updateSectorByCompanyId(sector);
		return new Result(Boolean.TRUE, "成功!", null);

	}

	/**
	 * 更改公司认证信息(R38)
	 * 
	 * @param companyAuthInfo
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("updateCompanyAuthInfo")
	public Result updateCompanyAuthInfo(CompanyAuthInfo companyAuthInfo,
			HttpServletRequest request, HttpServletResponse response,
			MultipartFile charter_paths) throws IOException {
		
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 保存图片
		if (charter_paths!=null&&!charter_paths.isEmpty()) { // 保存营业执照
			// byte[] fileByte =
			// FileUtils.base64ToByte(charter_path.toString());
			String path = FileUtils.getSaveCompanyPath(loginUser.getUuid(),
					"charter", companyAuthInfo.getCompany_id());
			OSSFilesUtil.uploadDocumentByString(path, charter_paths.getBytes());
			companyAuthInfo.setCharter_path(path);
		} 
//		else {
//
//			return new Result("RY0055", null); // 营业执照上传错误
//		}
		companyAuthInfo.setStatus("未认证");
		companyAuthInfoService
				.updateCompanyAuthInfoByCompanyId(companyAuthInfo);
		return new Result(Boolean.TRUE, "成功!", null);

	}

	/**
	 * 认证公司信息(R39)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("authenticationCompanyAuthInfo")
	public Result authenticationCompanyAuthInfo(Long companyId, Boolean status,
			HttpServletRequest request, HttpServletResponse response) {
		// 得到法人公司法人
		UserCompany retn = userCompanyService.companygetUser(companyId);
		if (retn == null || retn.getUse_id() == null) {
			return new Result("RY0063", null); // 无法人信息

		}

		// 通过人员
		userAuthInfoService.updateUserAuthInfoStatus(retn.getUse_id(), status);

		// 通过公司
		companyAuthInfoService.authenticationCompanyAuthInfo(companyId, status,
				retn.getUse_id());
		if (!status) { // 不通过发送信息

			MessageVo mv = new MessageVo();
			UserCompany uc = userCompanyService.companygetUser(companyId);
			mv.setSendId(uc.getUse_id());
			mv.setPassFlag("company");
			MessageAction ma = new MessageAction();
			sendMessageService.sendNoPass(mv);

		}else{
			MessageVo mv = new MessageVo();
			UserCompany uc = userCompanyService.companygetUser(companyId);
			mv.setSendId(uc.getUse_id());
			mv.setPassFlag("company");
			MessageAction ma = new MessageAction();
			sendMessageService.sendPass(mv);
			
		}
		return new Result(Boolean.TRUE, "成功!", null);

	}

	/**
	 * 得到公司所有信息(R40)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getAllCompanyInfo")
	public Result getAllCompanyInfo(Long companyId, HttpServletRequest request,
			HttpServletResponse response) {
		// 得到公司信息
		Company searchConpany = new Company();
		searchConpany.setId(companyId);
		searchConpany = companyService.selectCompanyById(searchConpany);
		// 得到公司认证信息
		CompanyAuthInfo searchCompanyAuthInfo = new CompanyAuthInfo();
		searchCompanyAuthInfo.setCompany_id(companyId);
		searchCompanyAuthInfo = companyAuthInfoService
				.selectCompanyAuthInfoByCompanyId(searchCompanyAuthInfo);
		// 得到公司行业信息
		Sector searchSector = new Sector();
		searchSector.setCompany_id(companyId);
		searchSector = sectorService.selectSectorByCompanyId(searchSector);

		// 得到法人信息
		CompanyLegalPerson legalPerson = companyAuthInfoService
				.getCompanyLegalPerson(companyId);
		// 得到信誉度信息
		CompanyCredibility companyCredibility = companyCredibilityService
				.GetCompanyCredibilityByCompanyId(companyId);
		CompanyGrowth companyGrowth = companyGrowthService
				.getCompanyGrowthByCompanyId(companyId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("conpany", searchConpany);
		map.put("companyAuthInfo", searchCompanyAuthInfo);
		map.put("sector", searchSector);
		map.put("legalPerson", legalPerson);
		map.put("companyCredibility", companyCredibility);
		map.put("companyGrowth", companyGrowth);
		return new Result(Boolean.TRUE, "成功!", map);
	}

	/**
	 * 编辑公司(R100)
	 * 
	 * @param company
	 * @param user
	 * @param userInfo
	 * @param authInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateAllCompanyInfo")
	public Result updateAllCompanyInfo(Company company, User user,
			UserInfo userInfo, UserAuthInfo userAuthInfo,
			CompanyAuthInfo authInfo, Sector sector, StringBuffer charter_path,
			StringBuffer head_pic_path, String sector_id) {
		// 获取所属人员id
		UserCompany rent = userCompanyService.companygetUser(company.getId());

		if (rent == null) {

			return new Result("RY0063", null); // 无此法人
		}
		// 根据人员找uuid
		User users = userService.getUserById(rent.getId());

		if (charter_path != null && !"".equals(charter_path.toString())) { // 保存营业执照
			byte[] fileByte = FileUtils.base64ToByte(charter_path.toString());
			String path = FileUtils.getSaveCompanyPath(users.getUuid(),
					"charter", company.getId());
			OSSFilesUtil.uploadDocumentByString(path, fileByte);
			authInfo.setCharter_path(path);

		}
		if (head_pic_path != null && !"".equals(head_pic_path.toString())) { // 保存头像路径
			byte[] fileByte = FileUtils.base64ToByte(head_pic_path.toString());
			String path2 = FileUtils.getSaveCompanyPath(users.getUuid(),
					"head_pic_path", company.getId());
			OSSFilesUtil.uploadDocumentByString(path2, fileByte);
			company.setHead_pic_path(path2);
		}

		// 更改行业信息
		sector.setCompany_id(company.getId());
		sector.setContent(sector_id);

		sectorService.selectSectorByCompanyId(sector); // 非空检测

		sectorService.updateIgnoreNull(sector);
		// 得到法人信息
		CompanyLegalPerson legalPerson = companyAuthInfoService
				.getCompanyLegalPerson(company.getId());
		Long user_id = legalPerson.getId();// 法人id
		user.setId(user_id);

		userService.updateIgnoreNull(user); // 法人信息
		Company retnCompany = company;
		retnCompany.setCompany_name(null);
		retnCompany = companyService.selectForObject(retnCompany);
		if (retnCompany != null && retnCompany.getCompany_name() != null) { // 公司名字只可改一次
			company.setCompany_name(null);
		}

		companyService.updateIgnoreNull(company); // 公司信息
		authInfo.setCompany_id(company.getId());
		authInfo.setId(null);

		companyAuthInfoService.selectCompanyAuthInfoByCompanyId(authInfo); // 非空检测

		companyAuthInfoService.updateIgnoreNull(authInfo);
		userInfo.setUser_id(user_id);// 用户详细信息
		userInfo.setId(null);

		userAuthInfoService.getUserAuthInfoById(userInfo.getUser_id()); // 空创建
		userInfoService.getUserInfoById(userInfo.getUser_id());// 空创建

		userInfoService.updateIgnoreNull(userInfo);
		userAuthInfo.setUser_id(user_id);// 用户认证信息
		userAuthInfo.setId(null);
		userAuthInfo.setStatus(null);
		userAuthInfoService.updateUserAuthInfoById(userAuthInfo);
		return new Result(Boolean.TRUE, "成功!", null);

	}

	/**
	 * 创建公司评论(R130)
	 * 
	 * @param evaluation
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("createdCompanyEvaluation")
	public Result createdUserEvaluation(CompanyEvaluation evaluation,
			HttpServletRequest request, HttpServletResponse response) {
		// HttpSession session = request.getSession();
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		evaluation.setUser_id(loginUser.getId());
		evaluation.setCreate_time(new Date());
		evaluation.setStatus("1");
		Long company_id;
		// evaluation.setCompany_id(mTaskc.getCompany_id());
		

		// 验证法人
		// UserCompany uc =
		// userCompanyService.companygetUser(mTaskc.getCompany_id());
		// if(uc.getCompany_id()!=loginUser.getId()){
		//
		// return new Result("RY0075", null); // 不是法人
		// }
		// Long companyId = (Long) session.getAttribute("projectCompany");
		// evaluation.setCompany_id(companyId);

		if (evaluation.getTask_type() == 1L) {// 将人员的变为已评论
			ModelTaskVo mTaskc = new ModelTaskVo();
			mTaskc.setId(evaluation.getTask_id());
			mTaskc = modelTaskService.selectForObject(mTaskc);
			if (!mTaskc.getUser_id().equals(loginUser.getId())) {

				return new Result("RY0076", null); // 非此任务执行者
			}
			if (!"待评价".equals(mTaskc.getTask_status())) {
				return new Result("RY0077", null); // 当前任务不可评论
			}
//			
			company_id=mTaskc.getCompany_id();
			UserCompany userCompany=userCompanyService.companygetUser(company_id);  //根据公司id得到法人id
			if(sendMessageService.evaluateMessage(loginUser.getId(), userCompany.getUse_id()))
				evaluationService.save(evaluation);// 添加评论

			ModelTaskVo mTask = new ModelTaskVo();
			mTask.setId(evaluation.getTask_id());
			mTask.setStage("已完成");
			mTask.setTask_status("已完成");
			modelTaskService.updateModelTask(mTask);// 更改任务
			evaluation.setCompany_id(mTaskc.getCompany_id());
			
			
		} else { // 灯光已评论

			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setStage("已完成");
			aTask.setId(evaluation.getTask_id());
			aTask.setTask_status("已完成");

			AnimationLightTaskVo aTaskc = new AnimationLightTaskVo();
			aTaskc.setId(evaluation.getTask_id());
			aTaskc = animationLightTaskService
					.getAnimationLightTaskById(aTaskc);
			if (!loginUser.getId().equals(aTaskc.getMaker().getUser_id())) {

				return new Result("RY0076", null); // 非此任务执行者
			}
			if (!"待评价".equals(aTaskc.getTask_status())) {
				return new Result("RY0077", null); // 当前任务不可评论
			}
			evaluation.setCompany_id(aTaskc.getCompany_id());
//			
			company_id=aTaskc.getCompany_id();
			UserCompany userCompany=userCompanyService.companygetUser(company_id);  //根据公司id得到法人id
			if(sendMessageService.evaluateMessage(loginUser.getId(), userCompany.getUse_id()))
				evaluationService.save(evaluation);// 添加评论
			
			animationLightTaskService.updateAnimationLightTask(aTask); // 更改任务

		}
		
		
		return new Result(Boolean.TRUE, "添加成功！", null);
	}

	/**
	 * 更改公司评论(R131)
	 * 
	 * @param evaluation
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateCompanyEvaluation")
	public Result updateCompanyEvaluation(CompanyEvaluation evaluation,
			HttpServletRequest request, HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		CompanyEvaluation in = new CompanyEvaluation();
		in.setId(evaluation.getId());
		in = evaluationService.selectForObject(in);
		if (in != null) {
			UserCompany userCompany=userCompanyService.companygetUser(in.getCompany_id());  //根据公司id得到法人id
			if (in.getUser_id() == loginUser.getId()) {
				evaluationService.updateIgnoreNull(evaluation);
				
				
				
				
				return new Result(Boolean.TRUE, "修改成功！", null);
			} else {
				return new Result("RY0059", null); // 无权操作
			}

		} else {

			return new Result("RY0058", null); // 没有这条
		}

	}

	/**
	 * 得到公司评论(R132)
	 * 
	 * @param evaluation
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectCompanyEvaluation")
	public Result selectCompanyEvaluation(Long start, String pageFlag,
			CompanyEvaluation evaluation, HttpServletRequest request,
			HttpServletResponse response, String sortColumn, String sortType,
			String allFlag) {
		Page page = null;
		Long total = 0l;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = evaluationService
					.getCompanyEvaluationVListCount(evaluation);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = evaluationService
					.getCompanyEvaluationVListCount(evaluation);
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
		List<CompanyEvaluationVo> evaluationList = evaluationService
				.getUserEvaluationVList(evaluation, page, allFlag);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("evaluationList", evaluationList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/*
	   private Result sendNoPass(MessageVo messageVo) { // 发送不通过
		// 拼接内容
		StringBuffer msgContent = new StringBuffer();
		if ("company".equals(messageVo.getPassFlag())) {
			msgContent.append("您提交的实名认证信息或公司信息不符合平台要求，请审核后提交正确质料!");
		} else {
			msgContent.append("您提交的实名认证信息不符合平台要求，请审核后提交正确质料!");

		}

		// 保存信息
		messageVo.setContent(msgContent.toString());
		// 赋值基本信息
		messageVo.setMessage_type("3");
		messageVo.setContent(msgContent.toString());
		messageVo.setSend_time(new Date());
		messageService.save(messageVo);
		MessageDetail messageDetail = new MessageDetail();
		// 赋值接收方信息
		messageDetail.setIs_delete('N');
		messageDetail.setMessage_id(messageVo.getId());
		messageDetail.setRead_state("N");
		messageDetail.setSend_time(new Date());
		messageDetail.setSend_id(messageVo.getSendId());
		// messageVo.setContent(content);

		// 保存信息
		// message.setSend_time(new Date());
		// message.
		messageDetailService.save(messageDetail);
		return new Result(Boolean.TRUE, "发送成功!", null);
	}
	
	private Result sendPass(MessageVo messageVo) { // 发送不通过
		// 拼接内容
		StringBuffer msgContent = new StringBuffer();
		if ("company".equals(messageVo.getPassFlag())) {
			msgContent.append("您提交的公司认证信息已经通过!");
		} else {
			msgContent.append("您提交的实名认证信息不符合平台要求，请审核后提交正确质料!");

		}

		// 保存信息
		messageVo.setContent(msgContent.toString());
		// 赋值基本信息
		messageVo.setMessage_type("3");
		messageVo.setContent(msgContent.toString());
		messageVo.setSend_time(new Date());
		messageService.save(messageVo);
		MessageDetail messageDetail = new MessageDetail();
		// 赋值接收方信息
		messageDetail.setIs_delete('N');
		messageDetail.setMessage_id(messageVo.getId());
		messageDetail.setRead_state("N");
		messageDetail.setSend_time(new Date());
		messageDetail.setSend_id(messageVo.getSendId());
		// messageVo.setContent(content);

		// 保存信息
		// message.setSend_time(new Date());
		// message.
		messageDetailService.save(messageDetail);
		return new Result(Boolean.TRUE, "发送成功!", null);
	}
	
*/
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
}
