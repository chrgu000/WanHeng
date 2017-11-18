package com.cgwas.user.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.cgwas.animationlighttask.entity.AnimationLightTaskVo;
import com.cgwas.animationlighttask.service.api.IAnimationLightTaskService;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.CommonUtil;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.common.utils.EmailSendUtil;
import com.cgwas.common.utils.FileUtils;
import com.cgwas.common.utils.MobileUtil;
import com.cgwas.common.utils.OSSFilesUtil;
import com.cgwas.company.service.api.ICompanyService;
import com.cgwas.educationalBackground.entity.EducationalBackground;
import com.cgwas.educationalBackground.service.api.IEducationalBackgroundService;
import com.cgwas.freezeRecord.service.api.IFreezeRecordService;
import com.cgwas.message.action.MessageAction;
import com.cgwas.message.entity.Message;
import com.cgwas.message.entity.MessageVo;
import com.cgwas.message.service.api.IMessageService;
import com.cgwas.message.service.api.ISendMessageService;
import com.cgwas.messageDetail.entity.MessageDetail;
import com.cgwas.messageDetail.service.api.IMessageDetailService;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.project.entity.UserProject;
import com.cgwas.project.service.api.IProjectService;
import com.cgwas.tradeRecord.entity.TradeRecord;
import com.cgwas.tradeRecord.service.api.ITradeRecordService;
import com.cgwas.user.entity.User;
import com.cgwas.user.entity.UserVo;
import com.cgwas.user.service.api.IUserService;
import com.cgwas.userAuthInfo.entity.UserAuthInfo;
import com.cgwas.userAuthInfo.service.api.IUserAuthInfoService;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userCompany.service.api.IUserCompanyService;
import com.cgwas.userCredibility.entity.UserCredibility;
import com.cgwas.userCredibility.service.api.IUserCredibilityService;
import com.cgwas.userEvaluation.entity.UserEvaluation;
import com.cgwas.userEvaluation.entity.UserEvaluationVo;
import com.cgwas.userEvaluation.service.api.IUserEvaluationService;
import com.cgwas.userGrowth.entity.UserGrowth;
import com.cgwas.userGrowth.service.api.IUserGrowthService;
import com.cgwas.userInfo.entity.UserInfo;
import com.cgwas.userInfo.service.api.IUserInfoService;
import com.cgwas.userQuestion.service.api.IUserQuestionService;
import com.cgwas.workExperience.entity.WorkExperience;
import com.cgwas.workExperience.service.api.IWorkExperienceService;

/**
 * 用户信息管理
 * 
 * @author Lingwh
 * 
 */
@Controller
@RequestMapping("cgwas/userAction")
public class UserInfoAction {

	IUserInfoService userInfoService = null;
	IUserAuthInfoService userAuthInfoService = null;
	IWorkExperienceService workExperienceService = null;
	IEducationalBackgroundService educationalBackgroundService = null;
	IUserService userService = null;
	IProjectService projectService = null;
	ITradeRecordService tradeRecordService = null;
	@Autowired
	private IUserGrowthService growthService;
	@Autowired
	private IUserEvaluationService evaluationService;

	@Autowired
	private IUserCredibilityService userCredibilityService;
	@Autowired
	private ISendMessageService sendMessageService;
	@Autowired
	private IFreezeRecordService freezeRecordService;
	@Autowired
	private IUserQuestionService userQuestionService;
	// OSS工具类
	OSSFilesUtil oSSFilesUtil = new OSSFilesUtil();
	@Autowired
	private ICompanyService companyService;
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

	/**
	 * 得到用户详细信息（R5）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserInfo")
	public Result getUserInfo(HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		List<UserInfo> retnList = userInfoService.getUserInfoById(loginUser
				.getId());
		if (retnList.size() > 0) {
			// 得到用户详细信息
			UserInfo retn = retnList.get(0);
			return new Result(Boolean.TRUE, "成功!", retn); // 返回用户详细信息
		} else {
			// 插入一条用户详细信息
			UserInfo newUserInfo = new UserInfo();
			newUserInfo.setUser_id(loginUser.getId());
			newUserInfo.setMoney((double) 0); // 钱包金额为0
			userInfoService.save(newUserInfo);
			return new Result(Boolean.TRUE, "成功!", null); // 返回用户详细信息
		}

	}

	/**
	 * 更改用户详细信息（R6）
	 * 
	 * @param userInfo
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping("updateUserInfo")
	public Result updateUserInfo(UserInfo userInfo, HttpServletRequest request,
			HttpServletResponse response) {

		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 更新用户信息
		UserInfo updateUserInfo = userInfo;
		updateUserInfo.setUser_id(loginUser.getId());
		userInfoService.updateUserInfoByUserId(updateUserInfo);

		return new Result(Boolean.TRUE, "成功!", null); // 返回用户详细信息
	}

	/**
	 * 根据ID得到认证信息表(R7)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserAuthInfo")
	public Result getUserAuthInfo(HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		List<UserAuthInfo> retnList = userAuthInfoService
				.getUserAuthInfoById(loginUser.getId());
		if (retnList.size() > 0) {
			// 得到用户认证信息
			UserAuthInfo retn = retnList.get(0);
			return new Result(Boolean.TRUE, "成功!", retn); // 返回用户认证信息
		} else {
			// 插入一条用户认证信息
			UserAuthInfo newUserAuthInfo = new UserAuthInfo();
			newUserAuthInfo.setUser_id(loginUser.getId());
			// newUserAuthInfo.setStatus("未认证");
			// newUserAuthInfo.setCreate_time(new Date());
			userAuthInfoService.save(newUserAuthInfo);
			return new Result(Boolean.TRUE, "成功!", null);
		}
	}

	/**
	 * 更改认证信息表(用户)（R8）
	 * 
	 * @param userInfo
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping("updateUserAuthInfo")
	public Result updateUserAuthInfo(UserAuthInfo userAuthInfo,
			HttpServletRequest request, HttpServletResponse response,
			MultipartFile idcard_pic_paths, MultipartFile idcard_pic_path_backs) throws IOException {
		
		
		
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 检测身份证号是否存在
		Long idcount = userAuthInfoService.getHaveIdCard(userAuthInfo
				.getIdcard());
		if (idcount > 0) {
			return new Result("RY0066", null); // 身份证已经被使用
		}
		// base64转图片
		if (idcard_pic_paths!=null&&!idcard_pic_paths.isEmpty()) { // 保存用户身份证正面
//			byte[] fileByte = FileUtils
//					.base64ToByte(idcard_pic_path.toString());
			String savePath2 = FileUtils.getSaveUserPath(loginUser.getUuid(),
					"idcard_pic");
			oSSFilesUtil.uploadDocumentByString(savePath2, idcard_pic_paths.getBytes());
			userAuthInfo.setIdcard_pic_path(savePath2);
		}

		// base64转图片
		if (idcard_pic_path_backs!=null&&!idcard_pic_path_backs.isEmpty()) { // 保存用户身份证反面
//			byte[] fileByte = FileUtils.base64ToByte(idcard_pic_path_back
//					.toString());
			String savePath3 = FileUtils.getSaveUserPath(loginUser.getUuid(),
					"idcard_pic_back");
			oSSFilesUtil.uploadDocumentByString(savePath3, idcard_pic_path_backs.getBytes());
			userAuthInfo.setIdcard_pic_path_back(savePath3);
		}

		// 更新用户认证信息
		UserAuthInfo updateUserAuthInfo = userAuthInfo;
		updateUserAuthInfo.setUser_id(loginUser.getId());
		updateUserAuthInfo.setStatus("未认证");
		updateUserAuthInfo.setCreate_time(new Date());
		userAuthInfoService.updateUserAuthInfoById(updateUserAuthInfo);
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 根据ID得到工作经历信息(R9)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getWorkExperience")
	public Result getWorkExperience(Long user_id, HttpServletRequest request,
			HttpServletResponse response) {
		/*	User loginUser = this.getLoginUser(request);
		// Long u
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		if (!CommonUtil.checkAdmin(request)) {
			user_id = loginUser.getId();

		}*/
		List<WorkExperience> retnList = workExperienceService
				.getWorkExperienceByUserId(user_id);
		if (retnList.size() > 0) {
			// 得到用户工作信息
			/*
			 * Map<String, Object> retnWorks = new HashMap<String, Object>();
			 * for (int i = 0; i < retnList.size(); i++) {
			 * retnWorks.put("workExperience" + i, retnList.get(i)); }
			 */
			return new Result(Boolean.TRUE, "成功!", retnList); // 返回用户认证信息
		} else {
			// 插入一条用户工作信息
			/*
			 * WorkExperience newData = new WorkExperience();
			 * newData.setUser_id(loginUser.getId());
			 * workExperienceService.save(newData);
			 */
			return new Result(Boolean.TRUE, "成功!", null);
		}
	}

	/**
	 * 管理工作经历信息（R10）
	 * 
	 * @param adminType
	 *            管理类型
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("adminWorkExperience")
	public Result adminWorkExperience(String adminType,
			WorkExperience workExperience, HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		if ("add".equals(adminType)) { // 添加
			WorkExperience addData = workExperience;
			addData.setUser_id(loginUser.getId());
			addData.setCreat_time(new Date());
			workExperienceService.save(addData);
			return new Result(Boolean.TRUE, "添加成功!", null);
		} else if ("del".equals(adminType)) { // 删除
			WorkExperience delData = workExperience;
			delData.setUser_id(loginUser.getId());
			workExperienceService.delete(delData);
			return new Result(Boolean.TRUE, "删除成功!", null);
		} else if ("update".equals(adminType)) { // 更改
			WorkExperience updateData = workExperience;
			updateData.setUser_id(loginUser.getId());
			updateData.setCreat_time(new Date());
			workExperienceService.updateWorkExperienceByUserId(updateData);
			return new Result(Boolean.TRUE, "修改成功!", null);
		}

		return new Result("RY0010", null); // 未指定操作

	}

	/**
	 * 获取教育经历（R11）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getEducationalBackground")
	public Result getEducationalBackground(Long user_id,
			HttpServletRequest request, HttpServletResponse response) {
	/*	User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		if (!CommonUtil.checkAdmin(request)) { // 检查管理员
			user_id = loginUser.getId();

		}*/

		List<EducationalBackground> retnList = educationalBackgroundService
				.getEducationalBackgroundByUserId(user_id);
		if (retnList.size() > 0) {
			return new Result(Boolean.TRUE, "成功!", retnList); // 返回用户教育经历信息
		} else {
			return new Result(Boolean.TRUE, "成功!", null);
		}
	}

	/**
	 * 管理学习经历信息（R12）
	 * 
	 * @param adminType
	 * @param educationalBackground
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("adminEducationalBackground")
	public Result adminEducationalBackground(String adminType,
			EducationalBackground educationalBackground,
			HttpServletRequest request, HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		if ("add".equals(adminType)) { // 添加
			EducationalBackground addData = educationalBackground;
			addData.setUser_id(loginUser.getId());
			addData.setCreate_time(new Date());
			educationalBackgroundService.save(addData);
			return new Result(Boolean.TRUE, "添加成功!", null);
		} else if ("del".equals(adminType)) { // 删除
			EducationalBackground delData = educationalBackground;
			delData.setUser_id(loginUser.getId());
			educationalBackgroundService.delete(delData);
			return new Result(Boolean.TRUE, "删除成功!", null);
		} else if ("update".equals(adminType)) { // 更改
			EducationalBackground updateData = educationalBackground;
			updateData.setUser_id(loginUser.getId());
			updateData.setCreate_time(new Date());
			educationalBackgroundService
					.updateEducationalBackgroundByUserId(updateData);
			return new Result(Boolean.TRUE, "修改成功!", null);
		}
		return new Result("RY0011", null); // 未指定操作
	}

	/**
	 * 获取用户认证信息认证状态(R13)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserAuthInfoStatus")
	public Result getUserAuthInfoStatus(HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 得到认证状态
		String retnInfo = userAuthInfoService.getUserAuthInfoStatus(loginUser
				.getId());
		if ("已认证".equals(retnInfo)) {
			return new Result(Boolean.TRUE, "已认证", null);
		} else {
			return new Result(Boolean.TRUE, "未认证", null);
		}
	}

	/**
	 * 获取全部用户详细信息(R19)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserAllInfo")
	public Result getUserAllInfo(HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 用户基本信息
		User searchUser = new User();
		searchUser.setId(loginUser.getId());
		UserVo user = userService.selectForObject(searchUser);
		// 清除密码
		user.setPassword("");
		// 用户详情
		List<UserInfo> userInfoList = userInfoService.getUserInfoById(loginUser
				.getId());
		UserInfo userInfo = null;
		if (userInfoList != null) {
			userInfo = userInfoList.get(0);
		}
		// 用户工作经验
		List<WorkExperience> workExperienceList = workExperienceService
				.getWorkExperienceByUserId(loginUser.getId());

		// 教育背景信息
		List<EducationalBackground> educationalBackgroundList = educationalBackgroundService
				.getEducationalBackgroundByUserId(loginUser.getId());
		// 用户信用信息
		Result retnUserCredibility = this.getUserCredibility(loginUser.getId(),
				request, response);
		// 用户认证信息
		UserAuthInfo authInfo = userAuthInfoService
				.getUserAuthInfoByUserId(loginUser.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userInfo", userInfo);
		map.put("workExperience", workExperienceList);
		map.put("educationalBackground", educationalBackgroundList);
		map.put("user", user);
		map.put("userCredibility", retnUserCredibility.getData());
		map.put("authInfo", authInfo);
		return new Result(Boolean.TRUE, "成功！", map);

	}

	/**
	 * 获取用户参与项目（用户）(R20)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserJoinProject")
	public Result getUserJoinProject(HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		List<UserProject> retn = projectService.getProjectByUserIds(loginUser
				.getId());
		return new Result(Boolean.TRUE, "成功！", retn);
	}

	/**
	 * 认证用户信息(R27)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("authenticationUserAuthInfo")
	public Result authenticationUserAuthInfo(Long userId, Boolean status,
			HttpServletRequest request, HttpServletResponse response) {
		User user=this.getLoginUser(request);
		userAuthInfoService.updateUserAuthInfoStatus(userId, status);
		if (!status) {// 不通过发送信息
			MessageAction ma = new MessageAction();
			MessageVo messageVo = new MessageVo();
			messageVo.setSendId(userId);
			messageVo.setPassFlag("user");
			messageVo.setUser_id(user.getId());  //接受者
			sendMessageService.sendNoPass(messageVo);

		}else{
			MessageAction ma = new MessageAction();
			MessageVo messageVo = new MessageVo();
			messageVo.setSendId(userId);
			messageVo.setPassFlag("user");
			messageVo.setUser_id(user.getId());  //接受者
			sendMessageService.sendPass(messageVo);
			
			
		}

		return new Result(Boolean.TRUE, "成功！", null);
	}

	/**
	 * 获取用户参与项目（管理者）(R30)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserJoinProjectByUserId")
	public Result getUserJoinProjectByUserId(Long userId,
			HttpServletRequest request, HttpServletResponse response) {
		if (userId == null || "".equals(userId)) {
			return new Result("RY0014", null); // 参数为空
		}
		List<UserProject> retn = projectService.getProjectByUserIds(userId);
		return new Result(Boolean.TRUE, "成功！", retn);
	}

	/**
	 * 发送认证邮件（R60）
	 * 
	 * @param email
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("sendIdentificationEmail")
	public Result sendIdentificationEmail(String email,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		EmailSendUtil emailSendUtil = new EmailSendUtil();
		MobileUtil mobileUtil = new MobileUtil();
		Map<String, String> context = new HashMap<String, String>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date); // 日期
		String bindEmailVerCode = mobileUtil.getEmailVerCode(); // 验证码
		String tel = loginUser.getTel(); // 电话
		session.setAttribute("bindEmailVerCode", bindEmailVerCode);
		session.setAttribute("bindEmailVerId", loginUser.getId()); // 审请的id
		session.setAttribute("bindEmail", email);

		User user = userService.getUserByUUID(loginUser.getUuid());
		// 用户昵称
		String userNick = user.getNickname();
		if (user.getNickname() == null || "".equals(user.getNickname())) {
			userNick = "用户";
		}
		context.put("userName", userNick);
		context.put("userAccount", tel);
		context.put("code", bindEmailVerCode);
		context.put("date", dateStr);
		emailSendUtil.sendEmail(context, email, "激活邮件", "identification", null);
		return new Result(Boolean.TRUE, "审请成功！", null);
	}

	/**
	 * 完成认证邮件（R61）
	 * 
	 * @param email
	 * @param code
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("identificationEmail")
	public Result identificationEmail(String email, String code,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		String sessionCode = (String) session.getAttribute("bindEmailVerCode");
		Long sessionId = (Long) session.getAttribute("bindEmailVerId");
		String sessionEmail = (String) session.getAttribute("bindEmail");
		if (sessionId == null || sessionCode == null || sessionEmail == null) {
			return new Result("RY0020", null); // 未审请
		}
		if (!sessionCode.equals(code) || !loginUser.getId().equals(sessionId)
				|| !sessionEmail.equals(email)) {
			return new Result("RY0021", null); // 输入错误
		}
		UserInfo updateUserInfo = new UserInfo();
		updateUserInfo.setUser_id(loginUser.getId());
		updateUserInfo.setEmail(sessionEmail);
		userInfoService.updateUserInfoByUserId(updateUserInfo);
		// 清空缓存
		session.setAttribute("bindEmailVerCode", null);
		session.setAttribute("bindEmailVerId", null);
		session.setAttribute("bindEmail", null);
		return new Result(Boolean.TRUE, "修改成功！", null);

	}

	/**
	 * 更改手机发送验证码（R62）
	 * 
	 * @param oldTel
	 * @param tel
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("sendChangeTelCode")
	public Result sendChangeTelCode(String oldTel, String tel,
			HttpServletRequest request, HttpServletResponse response) {
		MobileUtil mobileUtil = new MobileUtil();
		HttpSession session = request.getSession();
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		User changeUser = new User();
		changeUser.setTel(tel);
		// 得到数据列表
		List<Integer> retnList = userService.getNumByTel(changeUser);
		// 检测帐户是否存在
		Integer haveNum = retnList.get(0);
		if (haveNum > 0) {
			return new Result("RY0023", null);
		}

		User user = userService.getUserById(loginUser.getId());

		// 匹配旧手机号是否正确
		if (!user.getTel().equals(oldTel)) {
			return new Result("RY0022", null);

		}
		// 保存session
		String verCode = mobileUtil.getMobileVerCode(tel);
		session.setAttribute("changeTelCode", verCode);
		session.setAttribute("changeTel", tel);

		return new Result(Boolean.TRUE, "发送成功！", null);
	}

	/**
	 * 改变自己手机号(R63)
	 * 
	 * @param tel
	 * @param code
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("changeSelfTel")
	public Result changeSelfTel(String tel, String code,
			HttpServletRequest request, HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}

		// 检测改变的手机号是否已经存在
		User changeUser = new User();
		changeUser.setTel(tel);
		List<Integer> retnList = userService.getNumByTel(changeUser);
		// 检测帐户是否存在
		Integer haveNum = retnList.get(0);
		if (haveNum > 0) {
			return new Result("RY0023", null);
		}

		HttpSession session = request.getSession();

		String sessionTel = (String) session.getAttribute("changeTel");
		String sessionCode = (String) session.getAttribute("changeTelCode");

		if (sessionTel == null || sessionCode == null) { // 判断是否审请注册
			return new Result("RY0003", null);
		}
		if (!sessionTel.equals(tel) || !sessionCode.equals(code)) { // 判断验证码是否正确
			return new Result("RY0024", null);
		}

		// 更改用户手机号
		User updateUser = new User();
		updateUser.setId(loginUser.getId());
		updateUser.setTel(tel);
		userService.updateIgnoreNull(updateUser);

		// 清空缓存
		session.setAttribute("changeTelCode", null);
		session.setAttribute("changeTel", null);
		// 请从新登录
		session.setAttribute("loginUser", null);

		return new Result(Boolean.TRUE, "更改成功！", null);

	}

	// /**
	// * 用户充值金额（R71）
	// *
	// * @param money
	// * @param request
	// * @param response
	// * @return
	// */
	// @ResponseBody
	// @RequestMapping("rechargeMoney")
	// public Result rechargeMoney(int money, HttpServletRequest request,
	// HttpServletResponse response) {
	//
	// User loginUser = this.getLoginUser(request);
	// if (loginUser == null) {
	// return new Result("RY0008", null); // 登录过期
	// }
	// List<UserInfo> infos = userInfoService.getUserInfoById(loginUser
	// .getId());
	// // 判断是否已经存在信息
	// if (infos == null || infos.size() == 0) {
	// // 插入一条用户详细信息
	// UserInfo newUserInfo = new UserInfo();
	// newUserInfo.setUser_id(loginUser.getId());
	// newUserInfo.setMoney((double) 0); // 钱包金额为0
	// userInfoService.save(newUserInfo);
	// }
	// // 充值信息修改
	// UserInfo userInfo = infos.get(0);
	// userInfo.setMoney(userInfo.getMoney() + money);
	// userInfo.setUser_id(loginUser.getId());
	//
	// // 保存充值记录
	//
	// TradeRecord tradeRecord = new TradeRecord();
	// tradeRecord.setTrade_content("充值一点钱");
	// tradeRecord.setTrade_tyoe("充值");
	// tradeRecord.setTrade_price((double) money);
	// tradeRecord.setUser_id(loginUser.getId());
	// tradeRecord.setTrade_time(new Date());
	// // 保存充值记录
	// tradeRecordService.save(tradeRecord);
	// // 更新余额
	// userInfoService.rechargeMoney(userInfo);
	//
	// return new Result(Boolean.TRUE, "充值成功！", null);
	//
	// }

	// /**
	// * 用户提现金额（R72）
	// *
	// * @param money
	// * @param request
	// * @param response
	// * @return
	// */
	// @ResponseBody
	// @RequestMapping("withdrawMoney")
	// public Result withdrawMoney(int money, HttpServletRequest request,
	// HttpServletResponse response) {
	//
	// User loginUser = this.getLoginUser(request);
	// if (loginUser == null) {
	// return new Result("RY0008", null); // 登录过期
	// }
	// List<UserInfo> infos = userInfoService.getUserInfoById(loginUser
	// .getId());
	// // 判断是否已经存在信息
	// if (infos == null || infos.size() == 0) {
	// // 插入一条用户详细信息
	// UserInfo newUserInfo = new UserInfo();
	// newUserInfo.setUser_id(loginUser.getId());
	// newUserInfo.setMoney((double) 0); // 钱包金额为0
	// userInfoService.save(newUserInfo);
	// }
	// // 充值信息修改
	// UserInfo userInfo = infos.get(0);
	// // 查询冻结金额
	// FreezeRecord freezeRecord = new FreezeRecord();
	// freezeRecord.setUser_id(loginUser.getId());
	// List<Double> userPrice = freezeRecordService
	// .getUserFreezePrice(freezeRecord);
	// double sumPrice = 0.0;
	// for (Double price : userPrice) {
	// sumPrice += price;
	// }
	//
	// if (userInfo.getMoney() < (money - sumPrice)) {// 判断余额是否充足
	// return new Result("RY0032", null); // 余额不足
	// }
	// userInfo.setMoney(userInfo.getMoney() - money);
	// userInfo.setUser_id(loginUser.getId());
	//
	// // 保存充值记录
	//
	// TradeRecord tradeRecord = new TradeRecord();
	// tradeRecord.setTrade_content("提现一点钱");
	// tradeRecord.setTrade_tyoe("提现");
	// tradeRecord.setTrade_price((double) money);
	// tradeRecord.setUser_id(loginUser.getId());
	// tradeRecord.setTrade_time(new Date());
	// // 保存充值记录
	// tradeRecordService.save(tradeRecord);
	// // 更新余额
	// userInfoService.rechargeMoney(userInfo);
	//
	// return new Result(Boolean.TRUE, "提现成功！", null);
	//
	// }

	/**
	 * 获取用户信用信息(R73)
	 * 
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserCredibility")
	public Result getUserCredibility(Long userId, HttpServletRequest request,
			HttpServletResponse response) {
		// 设置搜索条件
		UserCredibility userCredibility = new UserCredibility();
		userCredibility.setUser_id(userId);
		// 获取用户信用信息
		UserCredibility searchUserCredibility = userCredibilityService
				.selectUserCredibilityByUserId(userCredibility);
		if (searchUserCredibility == null) { // 若为空则创建一个
			UserCredibility newUserCredibility = new UserCredibility();
			newUserCredibility.setCreat_time(new Date());
			newUserCredibility.setPassing_rate(100.0);
			newUserCredibility.setProduction_quality(0.0);
			newUserCredibility.setProduction_speed(0.0);
			newUserCredibility.setRating_points(0.0);
			newUserCredibility.setUser_id(userId);
			newUserCredibility.setUpdate_tiems(0L);
			userCredibilityService.save(newUserCredibility);
			searchUserCredibility = newUserCredibility; // 搜索的为新的
		}
		return new Result(Boolean.TRUE, "获取成功！", searchUserCredibility);

	}

	/**
	 * 获取用户信用信息(用户)(R74)
	 * 
	 * @param userId
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserCredibilitySelf")
	public Result getUserCredibilitySelf(HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 设置搜索条件
		UserCredibility userCredibility = new UserCredibility();
		userCredibility.setUser_id(loginUser.getId());
		// 获取用户信用信息
		UserCredibility searchUserCredibility = userCredibilityService
				.selectUserCredibilityByUserId(userCredibility);
		if (searchUserCredibility == null) { // 若为空则创建一个
			UserCredibility newUserCredibility = new UserCredibility();
			newUserCredibility.setCreat_time(new Date());
			newUserCredibility.setPassing_rate(100.0);
			newUserCredibility.setProduction_quality(0.0);
			newUserCredibility.setProduction_speed(0.0);
			newUserCredibility.setRating_points(0.0);
			newUserCredibility.setUser_id(loginUser.getId());
			newUserCredibility.setUpdate_tiems(0L);
			userCredibilityService.save(newUserCredibility);
			searchUserCredibility = newUserCredibility; // 搜索的为新的
		}
		return new Result(Boolean.TRUE, "获取成功！", searchUserCredibility);
	}

	// /**
	// * 获取用户冻结金额信息(用户)(R75)
	// *
	// * @param request
	// * @param response
	// * @return
	// */
	// @ResponseBody
	// @RequestMapping("getUserFreezePrice")
	// public Result getUserFreezePrice(HttpServletRequest request,
	// HttpServletResponse response) {
	// User loginUser = this.getLoginUser(request);
	// if (loginUser == null) {
	// return new Result("RY0008", null); // 登录过期
	// }
	// FreezeRecord freezeRecord = new FreezeRecord();
	// freezeRecord.setUser_id(loginUser.getId());
	// List<Double> userPrice = freezeRecordService
	// .getUserFreezePrice(freezeRecord);
	// double sumPrice = 0.0;
	// for (Double price : userPrice) {
	// sumPrice += price;
	// }
	// // 获取可用金额
	// List<UserInfo> retnUser = userInfoService.getUserInfoById(loginUser
	// .getId());
	// // 非空判断
	// UserInfo info = retnUser == null ? new UserInfo() : retnUser.get(0);
	// double usePrice = info.getMoney() - sumPrice;
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("sumPrice", sumPrice);
	// map.put("usePrice", usePrice);
	// return new Result(Boolean.TRUE, "获取成功！", map);
	// }

	/**
	 * 根据用户名字获取头像列表(R80)
	 * 
	 * @param name
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserHeardPicByName")
	public Result getUserHeardPicByName(String name,
			HttpServletRequest request, HttpServletResponse response) {
		// 获取头像名字及id
		List<UserInfo> retn = userInfoService.getUserHeardPicByName(name);
		return new Result(Boolean.TRUE, "获取成功！", retn);

	}

	/**
	 * 获取全部用户详细信息(管理员)(R90)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserAllInfoAdmin")
	public Result getUserAllInfoAdmin(Long user_id, HttpServletRequest request,
			HttpServletResponse response) {
		// 用户基本信息
		User searchUser = new User();
		searchUser.setId(user_id);
		User user = userService.getUserById(user_id);
		// 清除密码
		user.setPassword("");
		// 用户详情
		List<UserInfo> userInfoList = userInfoService.getUserInfoById(user_id);
		UserInfo userInfo = null;
		if (userInfoList.size() > 0) {
			userInfo = userInfoList.get(0);
		} else {
			userInfo = new UserInfo();

		}
		// 用户工作经验
		List<WorkExperience> workExperienceList = workExperienceService
				.getWorkExperienceByUserId(user_id);

		// 教育背景信息
		List<EducationalBackground> educationalBackgroundList = educationalBackgroundService
				.getEducationalBackgroundByUserId(user_id);
		// 用户信用信息
		Result retnUserCredibility = this.getUserCredibility(user_id, request,
				response);
		// 用户认证信息
		UserAuthInfo authInfo = userAuthInfoService
				.getUserAuthInfoByUserId(user_id);
		if (authInfo == null) {
			authInfo = new UserAuthInfo();
		}
		// 用户成长信息
		UserGrowth userGrowth = growthService.getUserGrowthByUserId(user_id);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userInfo", userInfo);
		map.put("workExperience", workExperienceList);
		map.put("educationalBackground", educationalBackgroundList);
		map.put("user", user);
		map.put("userCredibility", retnUserCredibility.getData());
		map.put("authInfo", authInfo);
		map.put("userGrowth", userGrowth);
		return new Result(Boolean.TRUE, "成功！", map);
	}

	/**
	 * 编辑用户(R99)
	 * 
	 * @param userInfo
	 * @param user
	 * @param userAuthInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateAllUserInfo")
	public Result updateAllUserInfo(UserInfo userInfo, User user,
			UserAuthInfo userAuthInfo, StringBuffer head_pic_path,
			StringBuffer idcard_pic_path, StringBuffer idcard_pic_path_back) {

		String uuid = userService.getUserById(userInfo.getUser_id()).getUuid();
		// base64转图片
		if (head_pic_path != null && !"".equals(head_pic_path.toString())) { // 保存用户头像
			byte[] fileByte = FileUtils.base64ToByte(head_pic_path.toString());
			String savePath = FileUtils.getSaveUserPath(uuid, "head_pic_path");
			oSSFilesUtil.uploadDocumentByString(savePath, fileByte);
			userInfo.setHead_pic_path(savePath);
		}

		// base64转图片
		if (idcard_pic_path != null && !"".equals(idcard_pic_path.toString())) { // 保存用户身份证正面
			byte[] fileByte = FileUtils
					.base64ToByte(idcard_pic_path.toString());
			String savePath2 = FileUtils.getSaveUserPath(uuid, "idcard_pic");
			oSSFilesUtil.uploadDocumentByString(savePath2, fileByte);
			userAuthInfo.setIdcard_pic_path(savePath2);

		}

		// base64转图片
		if (idcard_pic_path_back != null
				&& !"".equals(idcard_pic_path_back.toString())) { // 保存用户身份证反面
			byte[] fileByte = FileUtils.base64ToByte(idcard_pic_path_back
					.toString());
			String savePath3 = FileUtils.getSaveUserPath(uuid,
					"idcard_pic_back");
			oSSFilesUtil.uploadDocumentByString(savePath3, fileByte);
			userAuthInfo.setIdcard_pic_path_back(savePath3);

		}

		userAuthInfoService.getUserAuthInfoById(userInfo.getUser_id()); // 空创建
		userInfoService.getUserInfoById(userInfo.getUser_id());// 空创建
		// 详细信息
		userInfoService.updateUserInfoByUserId(userInfo);
		// 基本信息
		user.setId(userInfo.getUser_id());
		userService.updateIgnoreNull(user);
		// 认证信息
		userAuthInfo.setUser_id(userInfo.getUser_id());
		userAuthInfoService.updateIgnoreNull(userAuthInfo);

		return new Result(Boolean.TRUE, "成功！", null);
	}

	/**
	 * 编辑用户全部信息(用户)（R101）
	 * 
	 * @param userInfo
	 * @param user
	 * @param userAuthInfo
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateAllUserInfoUser")
	public Result updateAllUserInfoUser(UserInfo userInfo, User user,
			UserAuthInfo userAuthInfo, HttpServletRequest request,
			HttpServletResponse response, StringBuffer head_pic_path,
			StringBuffer idcard_pic_path, StringBuffer idcard_pic_path_back) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		User retnUser = userService.getUserByUUID(loginUser.getUuid());

		if (retnUser.getUsername() == null || "".equals(retnUser.getUsername())) { // 查看是否是第一次修改
			// 得到数据列表
			List<Integer> retnList = userService.getNumByTel(user);
			if (retnList.size() < 1) {
				return new Result("RY0009", null);
			}
			// 检测帐户是否存在
			Integer haveNum = retnList.get(0);
			if (haveNum > 0) {
				return new Result("RY0060", null);
			}

		} else {
			user.setUsername(null);
		}
		// base64转图片
		if (head_pic_path != null && !"".equals(head_pic_path.toString())) { // 保存用户头像
			byte[] fileByte = FileUtils.base64ToByte(head_pic_path.toString());
			String savePath = FileUtils.getSaveUserPath(loginUser.getUuid(),
					"head_pic_path");
			oSSFilesUtil.uploadDocumentByString(savePath, fileByte);
			userInfo.setHead_pic_path(savePath);
		}

		// base64转图片
		if (idcard_pic_path != null && !"".equals(idcard_pic_path.toString())) { // 保存用户身份证正面
			byte[] fileByte = FileUtils
					.base64ToByte(idcard_pic_path.toString());
			String savePath2 = FileUtils.getSaveUserPath(loginUser.getUuid(),
					"idcard_pic");
			oSSFilesUtil.uploadDocumentByString(savePath2, fileByte);
			userAuthInfo.setIdcard_pic_path(savePath2);
		}

		// base64转图片
		if (idcard_pic_path_back != null
				&& !"".equals(idcard_pic_path_back.toString())) { // 保存用户身份证反面
			byte[] fileByte = FileUtils.base64ToByte(idcard_pic_path_back
					.toString());
			String savePath3 = FileUtils.getSaveUserPath(loginUser.getUuid(),
					"idcard_pic_back");
			oSSFilesUtil.uploadDocumentByString(savePath3, fileByte);
			userAuthInfo.setIdcard_pic_path_back(savePath3);
		}

		userInfo.setUser_id(loginUser.getId());
		userInfoService.getUserInfoById(loginUser.getId());// 空创建
		// 详细信息
		userInfoService.updateUserInfoByUserId(userInfo);
		// 基本信息
		user.setId(userInfo.getUser_id());
		userService.updateIgnoreNull(user);
		// 认证信息
		userAuthInfo.setUser_id(loginUser.getId());
		userAuthInfoService.getUserAuthInfoById(loginUser.getId()); // 空创建
		userAuthInfoService.updateIgnoreNull(userAuthInfo);
		return new Result(Boolean.TRUE, "成功！", null);

	}

	/**
	 * 获取全部用户详细信息(自己)(R102)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserAllInfoUser")
	public Result getUserAllInfoUser(HttpServletRequest request,
			HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 用户基本信息
		User searchUser = new User();
		searchUser.setId(loginUser.getId());
		UserVo user = userService.selectForObject(searchUser);
		// 清除密码
		user.setPassword("");
		// 用户详情
		List<UserInfo> userInfoList = userInfoService.getUserInfoById(loginUser
				.getId());
		UserInfo userInfo = null;
		if (userInfoList.size() > 0) {
			userInfo = userInfoList.get(0);
		} else {
			userInfo = new UserInfo();
		}
		// 用户工作经验
		List<WorkExperience> workExperienceList = workExperienceService
				.getWorkExperienceByUserId(loginUser.getId());
		// 教育背景信息
		List<EducationalBackground> educationalBackgroundList = educationalBackgroundService
				.getEducationalBackgroundByUserId(loginUser.getId());
		// 用户信用信息
		Result retnUserCredibility = this.getUserCredibility(loginUser.getId(),
				request, response);
		// 用户认证信息
		UserAuthInfo authInfo = userAuthInfoService
				.getUserAuthInfoByUserId(loginUser.getId());
		if (authInfo == null) {
			authInfo = new UserAuthInfo();
			authInfo.setStatus("待提交");
		}
		List<UserCompany> atteCompany = companyService.getHaveCompanyByUserId(
				user.getId(), 2L);
		/**
		 * 查看是否拥有公司
		 */
		List<UserCompany> haveCompany = companyService.getHaveCompanyByUserId(
				user.getId(), 1L);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userInfo", userInfo);
		map.put("workExperience", workExperienceList);
		map.put("educationalBackground", educationalBackgroundList);
		map.put("user", user);
		map.put("userCredibility", retnUserCredibility.getData());
		map.put("authInfo", authInfo);
		map.put("atteCompany", atteCompany);
		map.put("companyFlag", haveCompany);
		return new Result(Boolean.TRUE, "成功！", map);
	}

	/**
	 * 用户发送邮件（R107）
	 * 
	 * @param email
	 * @param action
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("sendEmail")
	public Result sendEmail(String title, String email, String action,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		EmailSendUtil emailSendUtil = new EmailSendUtil();
		MobileUtil mobileUtil = new MobileUtil();
		Map<String, String> context = new HashMap<String, String>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date); // 日期
		String bindEmailVerCode = mobileUtil.getEmailVerCode(); // 验证码
		String tel = loginUser.getTel(); // 电话
		session.setAttribute(action + "VerCode", bindEmailVerCode);
		session.setAttribute(action, email);

		context.put("userAccount", tel);
		context.put("code", bindEmailVerCode);
		context.put("date", dateStr);
		emailSendUtil.sendEmail(context, email, title, "identification", null);
		return new Result(Boolean.TRUE, "发送成功！", null);
	}

	/**
	 * 根据邮箱修改邮件（R108）
	 * 
	 * @param email
	 * @param action
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("uptateEmailByEmail")
	public Result uptateEmailByEmail(String email, String action, String code,
			String newEmail, String newAction, String newCode,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 验证旧邮箱
		String oldEmail = (String) session.getAttribute(action);
		String oldCode = (String) session.getAttribute(action + "VerCode");
		if (!email.equals(oldEmail)) {
			return new Result("RY0040", null); // 验证失败
		}
		if (!code.equals(oldCode)) {
			return new Result("RY0041", null); // 验证失败
		}

		// 验证新邮箱
		String newEmails = (String) session.getAttribute(newAction);
		String newCodes = (String) session.getAttribute(newAction + "VerCode");
		if (!newEmails.equals(newEmail)) {
			return new Result("RY0042", null); // 验证失败
		}
		if (!newCode.equals(newCodes)) {
			return new Result("RY0043", null); // 验证失败
		}

		UserInfo updateUserInfo = new UserInfo();
		updateUserInfo.setUser_id(loginUser.getId());
		updateUserInfo.setEmail(newEmails);
		userInfoService.updateUserInfoByUserId(updateUserInfo);

		// 清空缓存
		session.setAttribute(action, null);
		session.setAttribute(action + "VerCode", null);
		session.setAttribute(newAction, null);
		session.setAttribute(newAction + "VerCode", null);

		return new Result(Boolean.TRUE, "修改成功！", null);

	}

	/**
	 * 根据密保修改邮箱(R109)
	 * 
	 * @param answers
	 * @param email
	 * @param action
	 * @param code
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("uptateEmailByQuestion")
	public Result uptateEmailByQuestion(String answers, String email,
			String action, String code, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 检测密保问题
		User user = userService.getUserById(loginUser.getId());
		if (!userQuestionService.checkQuestion(user, answers)) // 提交数据
			return new Result("RY0027", null); // 问题错误
		// 验证新邮箱
		String newEmails = (String) session.getAttribute(action);
		String newCodes = (String) session.getAttribute(action + "VerCode");
		if (!email.equals(newEmails)) {
			return new Result("RY0042", null); // 验证失败
		}
		if (!code.equals(newCodes)) {
			return new Result("RY0043", null); // 验证失败
		}
		// 修改邮箱
		UserInfo updateUserInfo = new UserInfo();
		updateUserInfo.setUser_id(loginUser.getId());
		updateUserInfo.setEmail(newEmails);
		userInfoService.updateUserInfoByUserId(updateUserInfo);
		// 清空缓存
		session.setAttribute(action, null);
		session.setAttribute(action + "VerCode", null);
		return new Result(Boolean.TRUE, "修改成功！", null);
	}

	/**
	 * 管理工作经历信息（管理员）（R117）
	 * 
	 * @param adminType
	 *            管理类型
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("adminWorkExperienceAdmin")
	public Result adminWorkExperienceAdmin(Long user_id, String adminType,
			WorkExperience workExperience, HttpServletRequest request,
			HttpServletResponse response) {

		if ("add".equals(adminType)) { // 添加
			WorkExperience addData = workExperience;
			addData.setUser_id(user_id);
			addData.setCreat_time(new Date());
			workExperienceService.save(addData);
			return new Result(Boolean.TRUE, "添加成功!", null);
		} else if ("del".equals(adminType)) { // 删除
			WorkExperience delData = workExperience;
			delData.setUser_id(user_id);
			workExperienceService.delete(delData);
			return new Result(Boolean.TRUE, "删除成功!", null);
		} else if ("update".equals(adminType)) { // 更改
			WorkExperience updateData = workExperience;
			updateData.setUser_id(user_id);
			updateData.setCreat_time(new Date());
			workExperienceService.updateWorkExperienceByUserId(updateData);
			return new Result(Boolean.TRUE, "修改成功!", null);
		}

		return new Result("RY0010", null); // 未指定操作

	}

	/**
	 * 管理学习经历信息（管理员）（R118）
	 * 
	 * @param adminType
	 * @param educationalBackground
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("adminEducationalBackgroundAdmin")
	public Result adminEducationalBackgroundAdmin(Long user_id,
			String adminType, EducationalBackground educationalBackground,
			HttpServletRequest request, HttpServletResponse response) {
		if ("add".equals(adminType)) { // 添加
			EducationalBackground addData = educationalBackground;
			addData.setUser_id(user_id);
			addData.setCreate_time(new Date());
			educationalBackgroundService.save(addData);
			return new Result(Boolean.TRUE, "添加成功!", null);
		} else if ("del".equals(adminType)) { // 删除
			EducationalBackground delData = educationalBackground;
			delData.setUser_id(user_id);
			educationalBackgroundService.delete(delData);
			return new Result(Boolean.TRUE, "删除成功!", null);
		} else if ("update".equals(adminType)) { // 更改
			EducationalBackground updateData = educationalBackground;
			updateData.setUser_id(user_id);
			updateData.setCreate_time(new Date());
			educationalBackgroundService
					.updateEducationalBackgroundByUserId(updateData);
			return new Result(Boolean.TRUE, "修改成功!", null);
		}
		return new Result("RY0011", null); // 未指定操作
	}

	/**
	 * 创建人员评论(R127)
	 * 
	 * @param evaluation
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("createdUserEvaluation")
	public Result createdUserEvaluation(UserEvaluation evaluation,
			HttpServletRequest request, HttpServletResponse response) {
		User loginUser = this.getLoginUser(request);
		Long recieve_id;
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		
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

		// 保存用户评论
		evaluation.setCreate_time(new Date());
		evaluation.setStatus("1");

		if (evaluation.getTask_type() == 1L) {// 将人员的变为已评论
			ModelTaskVo mTask = new ModelTaskVo();
			mTask.setId(evaluation.getTask_id());
			mTask.setCompany_stage("已完成");
			mTask.setCompany_status("已完成");
			// 判断法人
			ModelTaskVo mTaskc = new ModelTaskVo();
			mTaskc.setId(evaluation.getTask_id());
			mTaskc = modelTaskService.getModelTaskById(mTaskc);
			if (mTaskc == null) {
				return new Result("RY0083", null); // 无此任务

			}
			UserCompany uc = userCompanyService.companygetUser(mTaskc
					.getCompany_id());
			if (!uc.getUse_id().equals(loginUser.getId())) {

				return new Result("RY0075", null); // 非法人
			}
			if (!"待评价".equals(mTaskc.getCompany_stage())) {
				return new Result("RY0077", null); // 当前任务不可评论
			}
			
			
			
			evaluation.setUser_id(mTaskc.getMaker().getUser_id());
			recieve_id=mTaskc.getMaker().getUser_id();
			modelTaskService.updateModelTask(mTask);// 更新任务
			evaluation.setComment_id(loginUser.getId()); // 保存评论

		} else { // 灯光已评论
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setCompany_stage("已完成");
			aTask.setId(evaluation.getTask_id());
			aTask.setCompany_status("已完成");
			
			// 判断法人
			AnimationLightTaskVo aTaskc = new AnimationLightTaskVo();
			aTaskc.setId(evaluation.getTask_id());
			aTaskc = animationLightTaskService
					.getAnimationLightTaskById(aTaskc);
			if (aTaskc == null) {
				return new Result("RY0083", null); // 无此任务

			}

			UserCompany uc = userCompanyService.companygetUser(aTaskc
					.getCompany_id());
			if (!uc.getUse_id().equals(loginUser.getId())) {
				return new Result("RY0075", null); // 非法人
			}
			if (!"待评价".equals(aTaskc.getCompany_status())) {
				return new Result("RY0077", null); // 当前任务不可评论
			}
			evaluation.setUser_id(aTaskc.getMaker().getUser_id());   //被评论着的id
			recieve_id=aTaskc.getMaker().getUser_id();
			animationLightTaskService.updateAnimationLightTask(aTask);
			evaluation.setComment_id(loginUser.getId());     //评论者id
		}
		
		/*
		 * 发送消息
		 */
		/*Message messageVo =new Message();
		// 保存信息
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(new Date());
		
		messageVo.setTitle("用户"+loginUser.getUsername()+"于"+calendar.get(Calendar.YEAR)+"."+(calendar.get(Calendar.MONTH) + 1)+"."+calendar.get(Calendar.DAY_OF_MONTH)
			                      +"对你作出了评价");
		messageVo.setContent("用户"+loginUser.getUsername()+"于"+calendar.get(Calendar.YEAR)+"."+(calendar.get(Calendar.MONTH) + 1)+"."+calendar.get(Calendar.DAY_OF_MONTH)
			                      +"对你作出了评价");
		// 赋值基本信息
		messageVo.setMessage_type(ConstantUtil.MESSAGE_EVALUATE); // 评价消息
		messageVo.setSend_time(new Date());
		messageVo.setFor_id(loginUser.getId());
		// IAnimationLightTaskService messageService;
		
		MessageDetail messageDetail = new MessageDetail();
		// 赋值接收方信息
		messageDetail.setIs_delete(ConstantUtil.MESSAGE_DELETE_N);
		messageDetail.setMessage_id(messageVo.getId());
		messageDetail.setRead_state(ConstantUtil.MESSAGE_STATE_N);
		messageDetail.setSend_time(new Date());
		messageDetail.setSend_id(loginUser.getId()); // 发件人
		messageDetail.setUser_id(recieve_id); // 收件人
		
		messageService.sndMessAge(messageVo, messageDetail);*/
		if(sendMessageService.evaluateMessage(loginUser.getId(), recieve_id)){
			evaluationService.save(evaluation);
			return new Result(Boolean.TRUE, "添加成功！", null);
		}else {
			return new Result(Boolean.TRUE, "添加失败！", null);
		}
		
		
			
	}

	/**
	 * 更改人员评论(R128)
	 * 
	 * @param evaluation
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateUserEvaluation")
	public Result updateUserEvaluation(UserEvaluation evaluation,
			HttpServletRequest request, HttpServletResponse response) {
		// User loginUser = this.getLoginUser(request);
		// if (loginUser == null) {
		// return new Result("RY0008", null); // 登录过期
		// }
		UserEvaluation in = new UserEvaluation();
		in.setId(evaluation.getId());
		in = evaluationService.selectForObject(in);
		if (in != null) {

			if (in.getComment_id() == evaluation.getComment_id()) {
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
	 * 得到人员评论(R129)
	 * 
	 * @param evaluation
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectUserEvaluation")
	public Result selectUserEvaluation(Long start, String pageFlag,
			UserEvaluation evaluation, HttpServletRequest request,
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
			total = evaluationService.getUserEvaluationVListCount(evaluation);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = evaluationService.getUserEvaluationVListCount(evaluation);
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
		List<UserEvaluationVo> evaluationList = evaluationService
				.getUserEvaluationVList(evaluation, page, allFlag);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("evaluationList", evaluationList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 数量
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 获取人员擅长(R133)
	 * 
	 * @param user_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserTag")
	public Result getUserTag(Long user_id) {
		List<UserEvaluationVo> retn = evaluationService.getUserTag(user_id);
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 查询关联公司（R137）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserHaveCompanyAll")
	public Result getUserHaveCompanyAll(Long start, Long limit,
			HttpServletRequest request, HttpServletResponse response,
			UserCompany searchCompany, String sortType, String sortColumn,
			String countflag, String sessionFlag) {
		HttpSession session = request.getSession();
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		Map<String, Object> retn = new HashMap<String, Object>();

		List<UserCompany> haveCompanys = new ArrayList<UserCompany>();
		// 查询项目 id
		String ids = "";
		String listName = ""; // 列表名字
		Long count = 0L;// 拥有数量
		// 若页码为空则查询第一页
		if (start == null) {
			start = 0L;

		}
		if (limit == null) {
			limit = 10L;
		}

		// 管理员Flag ADMIN COMPANY MENBER
		if ("COMPANY".equals(session.getAttribute("adminFlag"))) { // 公司
			listName = "haveCompany";
			haveCompanys = userCompanyService.getUserHaveCompany(
					loginUser.getId(), start, limit, searchCompany, sortType,
					sortColumn);
			count = userCompanyService.getUserHaveCompanyCount(
					loginUser.getId(), searchCompany);

		} else if ("ADMIN".equals(session.getAttribute("adminFlag"))) {
			listName = "adminCompany";
			haveCompanys = userCompanyService.getAdminCompany(start, limit,
					searchCompany, sortType, sortColumn);
			count = userCompanyService.getAdminCompanyCount(searchCompany);
		} else {
			listName = "empCompany";
			haveCompanys = userCompanyService.getUserJoinCompany(
					loginUser.getId(), start, limit, searchCompany, sortType,
					sortColumn);
			count = userCompanyService.getUserJoinCompanyCount(
					loginUser.getId(), searchCompany);
		}
		for (UserCompany userCompany : haveCompanys) {
			ids += userCompany.getId() + ",";
		}
		List<Long> list = this.getLongList(ids);
		// 查询公司项目
		List<UserCompany> projectList = new ArrayList<UserCompany>();
		// 更具flagCount查询公司对应属性
		if ("emp".equals(countflag)) {
			projectList = userCompanyService.getCompanyEmpNum(list);
		} else if ("position".equals(countflag)) {
			projectList = userCompanyService.getCompanyPositionNum(list);
		} else if ("section".equals(countflag)) {
			projectList = userCompanyService.getCompanySectionNum(list);
		} else {
			projectList = userCompanyService.getCompanyProjectNum(list);
		}

		// 赋值查询数量列
		for (int i = 0; i < haveCompanys.size(); i++) {

			for (UserCompany userCompany : projectList) {
				if (userCompany.getId() == haveCompanys.get(i).getId()) {
					haveCompanys.get(i).setProject_no(
							userCompany.getProject_no());
					break;
				}
			}
		}
		retn.put(listName, haveCompanys);
		retn.put("count", count);

		// 打印公司

		// System.out.println(haveCompanys.get(0)
		// .getCompany_id());
		// 若只有一条则保存公司id
		if (haveCompanys.size() == 1) {
			UserCompany uc = haveCompanys.get(0);
			if ("admin".equals(sessionFlag)) {
				session.setAttribute("sessionCompany", uc.getId());
			} else {
				session.setAttribute("projectCompany", uc.getId());
			}
			return new Result(Boolean.TRUE, "成功!", retn);

		}

		// List<EmployeeInfo> joinCompanys = userCompanyService
		// .getUserJoinCompany(loginUser.getId());
		//
		// retn.put("joinCompany", joinCompanys);
		return new Result(Boolean.TRUE, "成功!", retn);
	}

	/**
	 * 更改关联公司（R138）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectCompany")
	public Result selectCompany(String flag, Long companyId,
			HttpServletRequest request, HttpServletResponse response,
			String sessionFlag) {
		HttpSession session = request.getSession(); // session
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		String adminFlag = (String) session.getAttribute("adminFlag");
		UserCompany scompany = new UserCompany();
		// 如果是改变则去得该人员拥有的公司
		if ("change".equals(flag)) {
			if ("ADMIN".equals(adminFlag)) { // 是管理员则直接改
				if ("admin".equals(sessionFlag)) {
					session.setAttribute("sessionCompany", companyId);
				} else {
					session.setAttribute("projectCompany", companyId);
				}
				return new Result(Boolean.TRUE, "成功!", null);
			}

			Map<String, Object> retn = new HashMap<String, Object>();

			List<UserCompany> haveCompanys = userCompanyService
					.getUserHaveCompany(loginUser.getId(), -1L, -1L, scompany,
							null, null);

			List<UserCompany> joinCompanys = userCompanyService
					.getUserJoinCompany(loginUser.getId(), -1L, -1L, scompany,
							null, null);
			Long selectedCompany = null;
			for (UserCompany employeeInfo : joinCompanys) {
				if (employeeInfo.getId() == companyId) {
					selectedCompany = employeeInfo.getId();
					break;
				}
			}

			for (UserCompany company : haveCompanys) {
				if (company.getId() == companyId) {
					selectedCompany = company.getId();
					break;
				}
			}
			if (selectedCompany != null) {
				if ("admin".equals(sessionFlag)) {
					session.setAttribute("sessionCompany", selectedCompany);
				} else {
					session.setAttribute("projectCompany", selectedCompany);
				}

			} else {

				return new Result("RY0064", null); // 不是公司拥有者
			}

		} else if ("delete".equals(flag)) {
			if ("admin".equals(sessionFlag)) {
				session.removeAttribute("sessionCompany");
			} else {
				session.removeAttribute("projectCompany");
			}

			return new Result("RY0064", null); // 不是公司拥有者
		} else {
			Long selectedCompany = null;

			if ("admin".equals(sessionFlag)) {
				selectedCompany = (Long) session.getAttribute("sessionCompany");
			} else {
				selectedCompany = (Long) session.getAttribute("projectCompany");
			}

			if (selectedCompany == null) {
				return new Result("RY0065", null);

			} else {

				return new Result(Boolean.TRUE, "存在!", selectedCompany);
			}

		}
		return new Result(Boolean.TRUE, "成功!", null);
	}

	/**
	 * 获取用户交易信息(用户与管理员)(R140)
	 * 
	 * @param user_id
	 * @param trade_tyoe
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserAllTradePrice")
	public Result getUserAllTradePrice(Long user_id, String trade_tyoe,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // session
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		String adminFlag = (String) session.getAttribute("adminFlag");
		TradeRecord searchTradeRecord = new TradeRecord();
		Long user_ids = 0L;
		if ("ADMIN".equals(adminFlag)) {
			user_ids = user_id;
		} else {
			user_ids = loginUser.getId();
		}
		searchTradeRecord.setUser_id(user_ids);
		searchTradeRecord.setTrade_type(trade_tyoe);
		String tradeRecord = tradeRecordService
				.getUserAllTrade(searchTradeRecord);
		return new Result(Boolean.TRUE, "成功!", tradeRecord == null ? "0"
				: tradeRecord);

	}

	/**
	 * 查看好中差评论数 （ R141）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getGCBEvaluationCount")
	public Result getGCBEvaluationCount(Long user_id,
			HttpServletRequest request, HttpServletResponse response) {

		Long good = evaluationService.getGCBEvaluationCount(user_id, 1L);
		Long comm = evaluationService.getGCBEvaluationCount(user_id, 2L);
		Long bad = evaluationService.getGCBEvaluationCount(user_id, 3L);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("good", good == null ? 0 : good);
		map.put("comm", comm == null ? 0 : comm);
		map.put("bad", bad == null ? 0 : bad);

		return new Result(Boolean.TRUE, "成功!", map);
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

	/**
	 * 将String转换Long集合
	 * 
	 * @param message
	 * @return
	 */
	private List<Long> getLongList(String message) {
		if ("".equals(message)) {
			message += "-99";
		}
		String[] str1 = message.split(",");// 将String[] 转换为long[]
		List<Long> str2 = new ArrayList<Long>();

		for (int i = 0; i < str1.length; i++) {
			str2.add(Long.valueOf(str1[i]));
		}
		return str2;
	}

	/**
	 * 查询是否为管理员
	 * 
	 * @param request
	 * @return
	 */
	private boolean checkAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession(); // session
		if ("ADMIN".equals(session.getAttribute("adminFlag"))) {

			return true;
		} else {
			return false;
		}

	}

	/*private Result sendNoPass(MessageVo messageVo) { // 发送不通过
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
			msgContent.append("您提交的公司认证信息已经通过认证!");
		} else {
			msgContent.append("您提交的实名认证信息已经通过认证!");

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
	}*/
	@Autowired(required = true)
	public void setUserInfoService(
			@Qualifier("userInfoService") IUserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	@Autowired(required = true)
	public void setUserAuthInfoService(
			@Qualifier("userAuthInfoService") IUserAuthInfoService userAuthInfoService) {
		this.userAuthInfoService = userAuthInfoService;
	}

	@Autowired(required = true)
	public void setWorkExperienceService(
			@Qualifier("workExperienceService") IWorkExperienceService workExperienceService) {
		this.workExperienceService = workExperienceService;
	}

	@Autowired(required = true)
	public void setEducationalBackgroundService(
			@Qualifier("educationalBackgroundService") IEducationalBackgroundService educationalBackgroundService) {
		this.educationalBackgroundService = educationalBackgroundService;
	}

	@Autowired(required = true)
	public void setUserService(
			@Qualifier("userService") IUserService userService) {
		this.userService = userService;
	}

	@Autowired(required = true)
	public void setprojectService(
			@Qualifier("projectService") IProjectService projectService) {
		this.projectService = projectService;
	}

	@Autowired(required = true)
	public void setprojectService(
			@Qualifier("tradeRecordService") ITradeRecordService tradeRecordService) {
		this.tradeRecordService = tradeRecordService;
	}

}
