package com.cgwas.user.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.CommonUtil;
import com.cgwas.common.utils.EmailSendUtil;
import com.cgwas.common.utils.EncryptUtil;
import com.cgwas.common.utils.MobileUtil;
import com.cgwas.company.service.api.ICompanyService;
import com.cgwas.message.service.api.ISendMessageService;
import com.cgwas.message.service.impl.SendMessageService;
import com.cgwas.role.entity.RoleVo;
import com.cgwas.role.service.api.IRoleService;
import com.cgwas.user.entity.User;
import com.cgwas.user.entity.UserVo;
import com.cgwas.user.service.api.IUserService;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userInfo.entity.UserInfo;
import com.cgwas.userInfo.service.api.IUserInfoService;
import com.cgwas.userQuestion.entity.UserQuestion;
import com.cgwas.userQuestion.service.api.IUserQuestionService;
import com.cgwas.walletInfo.entity.WalletInfo;
import com.cgwas.walletInfo.service.api.IWalletInfoService;

/**
 * 登录Action
 * 
 * @author Lingwh
 * 
 */
@Controller
@RequestMapping("cgwas/userAction")
public class LoginAction {
	private IUserService userService = null;
	private IUserInfoService userInfoService = null;
	private IUserQuestionService userQuestionService = null;
	EncryptUtil encryptUtil = new EncryptUtil(); // 加密类
	private MobileUtil mobileUtil = new MobileUtil();
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IWalletInfoService walletInfoService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private ISendMessageService sendMessageService;
	
	/**
	 * 使用手机号注册(R1)
	 * 
	 * @param user
	 *            用户
	 * @param verCode
	 *            验证码
	 * @param request
	 * @return 返回信息
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("register")
	public Result register(User user, String verCode,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Result retn = new Result(Boolean.TRUE, "成功", null);

		HttpSession session = request.getSession();
		// 参数验证
		if (user.getTel() == null) { // 电话号
			return new Result("RY0001", null);
		}

		// 得到数据列表
		List<Integer> retnList = userService.getNumByTel(user);
		if (retnList.size() < 1) {
			return new Result("RY0009", null);
		}
		// 检测帐户是否存在
		Integer haveNum = retnList.get(0);
		if (haveNum > 0) {
			return new Result("RY0005", null);
		}

		if (user.getPassword() == null) { // 密码
			return new Result("RY0002", null);
		}
		String sessionVerCode = (String) session
				.getAttribute("registerVerCode");// 验证码
		String sessionVerTel = (String) session.getAttribute("registerVerTel");// 审请的手机号
		if (sessionVerTel == null || "".equals(sessionVerTel)
				|| sessionVerCode == null || "".equals(sessionVerCode)
				|| !sessionVerTel.equals(user.getTel())) { // 验证码
			return new Result("RY0003", null);
		} else if (!sessionVerCode.equals(verCode)) {
			return new Result("RY0004", null);
		}

		// 用户添加
		user.setUuid(UUID.randomUUID().toString());// uuid
		user.setRegist_time(new Date()); // 注册时间
		user.setIs_delete('N');
		user.setLogin_times(0);
		// 加密密码
		user.setPassword(encryptUtil.getEncryptMsg(user.getPassword()));
		
		userService.save(user);
		sendMessageService.welcomeMessage(user.getId());
		// 空则创建
		userInfoService.getUserInfoById(user.getId());

		UserInfo userInfo = new UserInfo();
		userInfo.setUser_id(user.getId());
		userInfo.setHead_pic_path("cgwas/images/sc/page/userDefHeard.png");

		userInfoService.updateUserInfoByUserId(userInfo);
		// 添加日志
		// 清空缓存
		session.removeAttribute("registerVerCode");
		session.removeAttribute("registerVerTel");
//		session.setAttribute("registerVerCode", null);
//		session.setAttribute("registerVerTel", null);
		return retn;
	}

	/**
	 * 获取注册验证码(R2)
	 * 
	 * @param tel
	 *            手机号
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("registerGetVerCode")
	public Result registerGetVerCode(String tel, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(); // session

		Result retn = new Result(Boolean.TRUE, "成功", null);
		User user = new User();
		// 参数验证
		if (tel == null || "".equals(tel)) { // 电话号
			return new Result("RY0001", null);

		}
		// 检测帐户是否存在
		user.setTel(tel);
		// 得到数据列表
		List<Integer> retnList = userService.getNumByTel(user);
		if (retnList.size() < 1) {
			return new Result("RY0009", null);
		}
		// 检测帐户是否存在
		Integer haveNum = retnList.get(0);
		if (haveNum > 0) {
			return new Result("RY0005", null);
		}
		// 获取手机验证吗
		mobileUtil.getTelCode(tel, "192699", "registerVer", response, request);

		// 清空缓存
		session.removeAttribute("code");
//		session.setAttribute("code", null);

		// 成功
		return retn;
	}

	/**
	 * 检查手机号是否已经注册（R3）
	 * 
	 * @param tel
	 *            手机号
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("checkTelExistence")
	public Result checkTelExistence(String tel, String username,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Result retn = new Result(Boolean.TRUE, "成功", null);
		if (username != null && CommonUtil.isContainChinese(username)) {
			return new Result("RY0069", null); // 输入的用户名不可为中文
		}
		if (username != null && username.length() < 3) {

			return new Result("RY0070", null); // 输入的用户名不可为中文
		}

		User user = new User();
		// 检测帐户是否存在
		user.setTel(tel);
		user.setUsername(username);
		// 得到数据列表
		List<Integer> retnList = userService.getNumByTel(user);
		if (retnList.size() < 1) {
			return new Result("RY0009", null);
		}
		// 检测帐户是否存在
		Integer haveNum = retnList.get(0);
		if (haveNum > 0) {
			return new Result("RY0005", null);
		}
		// 成功
		return retn;
	}

	/**
	 * 用户登录（R4）
	 * 
	 * @param account
	 *            账号
	 * @param password
	 *            密码
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("userLogin")
	public Result userLogin(String account, String password,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession(); // session
		session.setMaxInactiveInterval(60 * 60 * 3);
		// 参数验证
		if (account == null) { // 电话号
			return new Result("RY0001", null);
		}
		if (password == null) { // 密码
			return new Result("RY0002", null);
		}
		// 登录
		User user = null;
		List<User> retnList = userService.getUserByAccount(account);
		if (retnList.size() > 0) {
			user = retnList.get(0);
		}
		// 加密密码
		password = encryptUtil.getEncryptMsg(password);
		if (user == null) {
			return new Result("RY0006", null); // 未注册

		} else if (user.getIs_delete() == 'Y') {
			return new Result("RY0013", null); // 该账号已被禁用
		} else if (!user.getPassword().equals(password)) {
			return new Result("RY0007", null); // 密码错误
		}
		// 获取当前时间到参数
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		user.setLast_login_time(sdf.format(new Date()));
		/**
		 * 判断当前超级管理员有无权限
		 */
		if(user.getId()==1 && !user.getIp().equals(CommonUtil.getIpAddr(request))){
			return new Result("SYS_NO_LOGIN_ERROR", null); // 登录权限受限
		}else{
			user.setIp(CommonUtil.getIpAddr(request));
		}
		// 记录登录时间 更新登录次数
		userService.updateByLastLoginTime(user);

		// 将返回结果保存到session
		UserVo retnUser = new UserVo();
		retnUser.setId(user.getId());
		retnUser.setNickname(user.getNickname());
		retnUser.setUuid(user.getUuid());
		retnUser.setTel(user.getTel());
		retnUser.setUsername(user.getUsername());
		// 得到用户信息
		UserInfo retnInfo = new UserInfo();
		List<UserInfo> retnInfoList = userInfoService.getUserInfoById(user
				.getId());
		if (retnInfoList.size() > 0) {
			// 得到用户详细信息
			retnInfo = retnInfoList.get(0);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		// 隐藏电话号
		String hideAccount = retnUser.getTel().substring(0,
				retnUser.getTel().length() - 4)
				+ "****";
		retnUser.setTel(hideAccount);// 隐藏session

		map.put("nickname", retnUser.getNickname()); // 用户基本信息
		map.put("uuid", retnUser.getUuid());
		map.put("id", retnUser.getId());
		map.put("userName", retnUser.getUsername());
		map.put("headPic", retnInfo.getHead_pic_path());
		map.put("tel", hideAccount);
		RoleVo roleVo = roleService.selectForUserId(user.getId());
		if (roleVo != null) {
			map.put("adminFlag", roleVo.getRole_type()); // 管理员Flag ADMIN
															// COMPANY MENBER
			session.setAttribute("adminFlag", roleVo.getRole_type());
		} else {
			map.put("adminFlag", "OTHER"); // 管理员Flag ADMIN COMPANY MENBER
			session.setAttribute("adminFlag", "OTHER");
		}
		// 清空选中公司
		try {
			
			session.removeAttribute("sessionCompany");
			session.removeAttribute("projectCompany");
//			session.setAttribute("sessionCompany", null);
//			session.setAttribute("projectCompany", null);
			
		} catch (Exception e) {
		     e.printStackTrace();
		}
		
		/**
		 * 查看是否拥有公司
		 */
		List<UserCompany> haveCompany = companyService.getHaveCompanyByUserId(
				user.getId(), 1L);

		if (haveCompany.size() > 0) {
			session.setAttribute("sessionCompany", haveCompany.get(0)
					.getCompany_id());
			session.setAttribute("projectCompany", haveCompany.get(0)
					.getCompany_id());
		}

		session.setAttribute("loginUser", retnUser);

		return new Result(Boolean.TRUE, "成功", map); // 返回结果;
	}

	/**
	 * 根据旧密码修改新密码(R64)
	 * 
	 * @param password
	 * @param newPassword
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("changePasswordByPassword")
	public Result changePasswordByPassword(String password, String newPassword,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 获取登录信息
		User user = userService.getUserById(loginUser.getId());
		if (user.getPassword().equals(encryptUtil.getEncryptMsg(password))) {
			// 更改密码
			user.setPassword(encryptUtil.getEncryptMsg(newPassword));
			userService.updateIgnoreNull(user);

			// 请从新登录
			session.removeAttribute("loginUser");
//			session.setAttribute("loginUser", null);
			return new Result(Boolean.TRUE, "更改成功!", null); // 返回结果;

		} else {
			return new Result("RY0025", null); // 密码错误
		}

	}

	/**
	 * 添加密保问题（R65）
	 * 
	 * @param userQuestion
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("addUserQuestion")
	public Result addUserQuestion(UserQuestion userQuestion,
			HttpServletRequest request, HttpServletResponse response) {

		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 查询是否已经存在密保问题
		userQuestion.setUser_id(loginUser.getId());
		UserQuestion retn = userQuestionService.selectForObject(userQuestion);
		if (retn != null) {
			return new Result("RY0026", null); // 密保问题已存在
		}

		// 加密问题
		userQuestion.setUser_id(loginUser.getId());
		userQuestion.setAnswer1(encryptUtil.getEncryptMsg(userQuestion
				.getAnswer1()));
		userQuestion.setAnswer2(encryptUtil.getEncryptMsg(userQuestion
				.getAnswer2()));
		userQuestion.setAnswer3(encryptUtil.getEncryptMsg(userQuestion
				.getAnswer3()));
		// 保存问题
		userQuestionService.save(userQuestion);

		return new Result(Boolean.TRUE, "添加成功", null); // 返回结果;
	}

	/**
	 * 更改密保问题（R66）
	 * 
	 * @param userQuestion
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateUserQuestion")
	public Result updateUserQuestion(String action, String code,
			UserQuestion userQuestion, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		User retn = userService.getUserById(loginUser.getId());
		String tel = (String) session.getAttribute(action + "Tel");
		String sessionCode = (String) session.getAttribute(action + "Code"); // session
																				// 验证码
		if (!retn.getTel().equals(tel)) {
			return new Result("RY0003", null);
		}

		if (!sessionCode.equals(code)) {

			return new Result("RY0004", null);
		}

		// 加密问题
		userQuestion.setUser_id(loginUser.getId());

		if (userQuestion.getAnswer1() != null) {
			userQuestion.setAnswer1(encryptUtil.getEncryptMsg(userQuestion
					.getAnswer1()));

		}
		if (userQuestion.getAnswer2() != null) {
			userQuestion.setAnswer2(encryptUtil.getEncryptMsg(userQuestion
					.getAnswer2()));
		}
		if (userQuestion.getAnswer3() != null) {
			userQuestion.setAnswer3(encryptUtil.getEncryptMsg(userQuestion
					.getAnswer3()));
		}

		userQuestionService.updateUserQuestionByUserId(userQuestion);
		return new Result(Boolean.TRUE, "修改成功", null); // 返回结果;

	}

	/**
	 * 根据密保问题修改密码（R67）
	 * 
	 * @param userQuestion
	 * @param tel
	 * @param newPassword
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("changePasswordByQuestion")
	public Result changePasswordByQuestion(User user, String answers,
			String newPassword, HttpServletRequest request,
			HttpServletResponse response) {

		// 加密信息
		user.setPassword(encryptUtil.getEncryptMsg(user.getPassword()));
		if (userQuestionService.checkQuestion(user, answers)) // 提交数据
			return new Result(Boolean.TRUE, "修改成功", null); // 修改成功

		return new Result("RY0027", null); // 问题错误
	}

	/**
	 * 用户得到密保问题(R68)
	 * 
	 * @param userId
	 * @param tel
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserQuestionByUser")
	public Result getUserQuestionByUser(User user) {

		UserQuestion question = userQuestionService.getUserQuestionByUser(user);

		if (question != null)// 判断是否得到
			return new Result(Boolean.TRUE, "获取成功", question);

		return new Result("RY0028", null); // 查询信息错误
	}

	/**
	 * 发送更改密码邮件（R69）
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping("sendChangePasswordEmail")
	public Result sendChangePasswordEmail(User user,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // session
		String sendEmail = userInfoService.getEmailByAccount(user);
		if (sendEmail == null) {
			return new Result("RY0029", null); // 查询信息错误
		}
		// 隐藏手机号
		String hideEmail = sendEmail.replaceAll(
				"(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");

		// 生成内容
		EmailSendUtil emailSendUtil = new EmailSendUtil();
		MobileUtil mobileUtil = new MobileUtil();
		Map<String, String> context = new HashMap<String, String>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date); // 日期
		String changePasswordEmailVerCode = mobileUtil.getEmailVerCode(); // 验证码
		String account = ""; // 账号
		// 判断是用什么找回
		if (user.getTel() != null) {
			account = user.getTel();
		} else {
			account = user.getUsername();
		}
		// 隐藏账号
		String hideAccount = account.substring(0, account.length() - 4)
				+ "****";

		session.setAttribute("changePasswordEmailVerCode",
				changePasswordEmailVerCode);
		session.setAttribute("changePasswordEmailAccount", account);
		context.put("userAccount", hideAccount);
		context.put("code", changePasswordEmailVerCode);
		context.put("date", dateStr);
		emailSendUtil.sendEmail(context, sendEmail, "密码找回邮件", "changePassword",
				null);

		return new Result(Boolean.TRUE, "发送成功!", hideEmail); // 返回结果;
	}

	/**
	 * 根据邮件更改密码（R70）
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("changePasswordByEmial")
	public Result changePasswordByEmial(User user, String code,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // session

		String verCode = (String) session
				.getAttribute("changePasswordEmailVerCode");
		String account = (String) session
				.getAttribute("changePasswordEmailAccount");
		// 判断验证码是否正确
		if (!code.equals(verCode)) {
			return new Result("RY0030", null); // 查询信息错误
		}

		// 判断账号号是否正确
		if (!(user.getTel() != null && user.getTel().equals(account))
				&& !(user.getUsername() != null && user.getUsername().equals(
						account))) {
			return new Result("RY0031", null); // 查询信息错误
		}
		// 加密密码
		user.setPassword(encryptUtil.getEncryptMsg(user.getPassword()));

		// 修改密码
		userService.updateUserByAccount(user);
		// 请从新登录
		session.removeAttribute("loginUser");
//		session.setAttribute("loginUser", null);
		// 清空缓存
		session.removeAttribute("changePasswordEmailVerCode");
		session.removeAttribute("changePasswordEmailAccount");
//		session.setAttribute("changePasswordEmailVerCode", null);
//		session.setAttribute("changePasswordEmailAccount", null);
		return new Result(Boolean.TRUE, "修改成功", null); // 返回结果;
	}

	/**
	 * 发送密码找回短信(R85)
	 * 
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("sendUpdatePasswordSMS")
	public Result sendUpdatePasswordSMS(String tel, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		User searchUser = new User();
		searchUser.setTel(tel);
		HttpSession session = request.getSession();
		// 得到数据列表
		List<Integer> retnList = userService.getNumByTel(searchUser);
		if (retnList.size() < 1) {
			return new Result("RY0009", null);
		}
		// 检测帐户是否存在
		Integer haveNum = retnList.get(0);
		if (haveNum < 1) {
			return new Result("RY0034", null);
		}
		// 获取手机验证吗
		mobileUtil.getTelCode(tel, "192699", "updatePassword", response,
				request);
		// 清空缓存
		session.removeAttribute("password");
//		session.setAttribute("password", null);
		return new Result(Boolean.TRUE, "发送成功", null); // 返回结果;
	}

	/**
	 * 根据手机号更改密码(R86)
	 * 
	 * @param tel
	 * @param password
	 * @param verCode
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updatePasswordByTel")
	public Result updatePasswordByTel(String tel, String password,
			String verCode, HttpServletRequest request,
			HttpServletResponse response) {
		// 取出session中保存的数据
		HttpSession session = request.getSession();
		String sessionCode = (String) session
				.getAttribute("updatePasswordCode");
		String sessionTel = (String) session.getAttribute("updatePasswordTel");
		if (!tel.equals(sessionTel)) {
			return new Result("RY0003", null);
		}
		if (!verCode.equals(sessionCode)) {
			return new Result("RY0004", null);
		}
		User inUser = new User();
		inUser.setTel(tel);
		inUser.setPassword(encryptUtil.getEncryptMsg(password));
		userService.updateUserByAccount(inUser);

		// 清除缓存
		session.removeAttribute("updatePasswordCode");
		session.removeAttribute("updatePasswordTel");
//		session.setAttribute("updatePasswordCode", null);
//		session.setAttribute("updatePasswordTel", null);

		return new Result(Boolean.TRUE, "更改成功", null); // 返回结果;

	}

	/**
	 * 发送手机短信(R103)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("sendTelSMSUser")
	public Result sendTelSMSUser(String action, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		User retn = userService.getUserById(loginUser.getId());
		retn.getTel();
		mobileUtil.getTelCode(retn.getTel(), "192699", action, response,
				request);
		return new Result(Boolean.TRUE, "发送成功", null);
	}

	/**
	 * 根据原有手机号更改手机号(R104)
	 * 
	 * @param action
	 * @param code
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("changeTelByTel")
	public Result changeTelByTel(String action, String code, String newAction,
			String newCode, String newTel, HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}

		// 检测手机是否存在
		User user = new User();
		user.setTel(newTel);
		// 得到数据列表
		List<Integer> retnList = userService.getNumByTel(user);
		// 检测帐户是否存在
		Integer haveNum = retnList.get(0);
		if (haveNum > 0) {
			return new Result("RY0005", null);
		}

		// 旧手机验证
		User retn = userService.getUserById(loginUser.getId());
		String tel = (String) session.getAttribute(action + "Tel");
		String sessionCode = (String) session.getAttribute(action + "Code"); // session
		if (!tel.equals(retn.getTel())) {
			return new Result("RY0035", null);
		}
		if (!sessionCode.equals(code)) {
			return new Result("RY0036", null);
		}

		// 新手机验证
		String newtels = (String) session.getAttribute(newAction + "Tel");
		String newCodes = (String) session.getAttribute(newAction + "Code"); // session
		if (!newtels.equals(newTel)) {
			return new Result("RY0037", null);
		}
		if (!newCodes.equals(newCode)) {
			return new Result("RY0038", null);
		}

		// 更改用户手机号
		User updateUser = new User();
		updateUser.setId(loginUser.getId());
		updateUser.setTel(newtels);
		userService.updateIgnoreNull(updateUser);

		// 清空缓存
		session.removeAttribute(newAction + "Tel");
		session.removeAttribute(newAction + "Code");
		session.removeAttribute(action + "Tel");
		session.removeAttribute(action + "Code");
//		session.setAttribute(newAction + "Tel", null);
//		session.setAttribute(newAction + "Code", null);
//		session.setAttribute(action + "Tel", null);
//		session.setAttribute(action + "Code", null);
		return new Result(Boolean.TRUE, "修改成功", null);
	}

	/**
	 * 发送手机短信(手机)(R105)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("sendTelSMSByTel")
	public Result sendTelSMSByTel(String tel, String action,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		mobileUtil.getTelCode(tel, "192699", action, response, request);
		return new Result(Boolean.TRUE, "发送成功", null);
	}

	/**
	 * 根据密保修改手机(R106)
	 * 
	 * @param question
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("changeTelByQuestion")
	public Result changeTelByQuestion(String answers, String newTel,
			String newAction, String newCode, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		// 检测登录
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}

		// 检测手机是否存在
		User usertel = new User();
		usertel.setTel(newTel);
		// 得到数据列表
		List<Integer> retnList = userService.getNumByTel(usertel);
		// 检测帐户是否存在
		Integer haveNum = retnList.get(0);
		if (haveNum > 0) {
			return new Result("RY0005", null);
		}

		// 检测密保问题
		User user = userService.getUserById(loginUser.getId());
		if (!userQuestionService.checkQuestion(user, answers)) // 提交数据
			return new Result("RY0027", null); // 问题错误

		// 新手机验证
		String newtels = (String) session.getAttribute(newAction + "Tel");
		String newCodes = (String) session.getAttribute(newAction + "Code"); // session
		if (!newtels.equals(newTel)) {
			return new Result("RY0037", null);
		}
		if (!newCodes.equals(newCode)) {
			return new Result("RY0038", null);
		}

		// 更改用户手机号
		User updateUser = new User();
		updateUser.setId(loginUser.getId());
		updateUser.setTel(newtels);
		userService.updateIgnoreNull(updateUser);

		// 清空缓存
		session.removeAttribute(newAction + "Tel");
		session.removeAttribute(newAction + "Code");
//		session.setAttribute(newAction + "Tel", null);
//		session.setAttribute(newAction + "Code", null);
		return new Result(Boolean.TRUE, "修改成功", null);
	}

	/**
	 * 退出登录（R111）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("loginOut")
	public Result loginOut(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(); // session
		session.removeAttribute("loginUser");
//		session.setAttribute("loginUser", null);
		return new Result(Boolean.TRUE, "退出成功", null);
	}

	/**
	 * 手机修改支付密码（用户）（R114）
	 * 
	 * @param action
	 * @param code
	 * @param tel
	 * @param password
	 * @param request
	 * @param responsef
	 * @return
	 */
	@ResponseBody
	@RequestMapping("changePayPasswordByTel")
	public Result changePayPasswordByTel(String action, String code,
			String tel, String password, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		// 检测登录
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}

		// 新手机验证
		String tels = (String) session.getAttribute(action + "Tel");
		String codes = (String) session.getAttribute(action + "Code"); // session

		if (!tel.equals(tels)) {
			return new Result("RY0049", null); // 手机号错误
		}
		if (!code.equals(codes)) {
			return new Result("RY0050", null); // 验证码错误
		}
		WalletInfo walletInfo = new WalletInfo();
		walletInfo.setUser_id(loginUser.getId());
		walletInfo.setPay_password(encryptUtil.getEncryptMsg(password));
		walletInfoService.changePayPassword(walletInfo);
		// 缓存清空
		session.removeAttribute(action + "Tel");
		session.removeAttribute(action + "Code");
//		session.setAttribute(action + "Tel", null);
//		session.setAttribute(action + "Code", null);

		return new Result(Boolean.TRUE, "更改成功", null);
	}

	/**
	 * 邮箱修改支付密码（用户）（R115）
	 * 
	 * @param email
	 * @param action
	 * @param code
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("changePayPasswordByEmail")
	public Result changePayPasswordByEmail(String email, String action,
			String code, String password, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		// 检测登录
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 验证旧邮箱
		String emails = (String) session.getAttribute(action);
		String codes = (String) session.getAttribute(action + "VerCode");

		if (!email.equals(emails)) {
			return new Result("RY0051", null); // 验证失败
		}
		if (!codes.equals(code)) {
			return new Result("RY0052", null); // 验证失败
		}
		WalletInfo walletInfo = new WalletInfo();
		walletInfo.setUser_id(loginUser.getId());
		walletInfo.setPay_password(encryptUtil.getEncryptMsg(password));
		walletInfoService.changePayPassword(walletInfo);
		// 缓存清空
		session.removeAttribute(action);
		session.removeAttribute(action + "VerCode");
//		session.setAttribute(action, null);
//		session.setAttribute(action + "VerCode", null);

		return new Result(Boolean.TRUE, "更改成功", null);
	}

	/**
	 * 密保修改支付密码（用户）（R116）
	 * 
	 * @param answers
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("changePayPasswordByQuestion")
	public Result changePayPasswordByQuestion(String answers, String password,
			HttpServletRequest request, HttpServletResponse response) {
		// 检测登录
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		// 检测密保问题
		User user = userService.getUserById(loginUser.getId());
		if (!userQuestionService.checkQuestion(user, answers)) // 提交数据
			return new Result("RY0027", null); // 问题错误

		WalletInfo walletInfo = new WalletInfo();
		walletInfo.setUser_id(loginUser.getId());
		walletInfo.setPay_password(encryptUtil.getEncryptMsg(password));
		walletInfoService.changePayPassword(walletInfo);

		return new Result(Boolean.TRUE, "更改成功", null);
	}

	/**
	 * 获取用户类型（R126）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getUserType")
	public Result getUserType(HttpServletRequest request,
			HttpServletResponse response) {
		// 检测登录
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		User user = userService.getUserByUUID(loginUser.getUuid());

		if ('A' == user.getIs_delete()) {
			return new Result(Boolean.TRUE, "管理员", "A");
		} else {
			List<UserCompany> haveCompany = companyService
					.getHaveCompanyByUserId(user.getId(), 1L);
			if (haveCompany.size() > 0) {
				return new Result(Boolean.TRUE, "公司持有人", haveCompany);
			} else {
				return new Result("RY0054", null); // 非法访问
			}

		}

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
	public void setUserQuestionService(
			@Qualifier("userQuestionService") IUserQuestionService userQuestionService) {
		this.userQuestionService = userQuestionService;
	}

	@Autowired(required = true)
	public void setUserService(
			@Qualifier("userService") IUserService userService) {
		this.userService = userService;
	}

	@Autowired(required = true)
	public void setUserInfoService(
			@Qualifier("userInfoService") IUserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
}
