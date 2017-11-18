package com.cgwas.message.action;

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

import com.cgwas.animationlighttask.service.api.IAnimationLightTaskService;
import com.cgwas.arbitrateInfo.service.api.IArbitrateInfoService;
import com.cgwas.arbitrateUserInfo.service.api.IArbitrateUserInfoService;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.common.utils.ValidateCodeImgUtil;
import com.cgwas.company.service.api.ICompanyService;
import com.cgwas.message.entity.Message;
import com.cgwas.message.entity.MessageVo;
import com.cgwas.message.service.api.IMessageService;
import com.cgwas.messageDetail.entity.MessageDetail;
import com.cgwas.messageDetail.entity.MessageInfo;
import com.cgwas.messageDetail.service.api.IMessageDetailService;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.user.entity.User;
import com.cgwas.user.service.api.IUserService;
import com.cgwas.userCompany.service.api.IUserCompanyService;
import com.cgwas.userInfo.service.api.IUserInfoService;

@Controller
@RequestMapping("cgwas/messageAction")
public class MessageAction {
	@Autowired
	private IMessageDetailService messageDetailService;
	@Autowired
	private IMessageService messageService;
	@Autowired
	private IArbitrateInfoService arbitrateInfoService;
	@Autowired
	private IArbitrateUserInfoService arbitrateUserInfoService;
	@Autowired
	private IModelTaskService modelTaskService;
	@Autowired
	private IAnimationLightTaskService animationLightTaskService;
	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserCompanyService userCompanyService;
	/**
	 * 新消息(M1)
	 * 
	 * @param messageDetail
	 * @param message
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("createMessage")
	public Result createMessage(Message message,
			String actionType, String sendIds,Long parent_id, HttpServletRequest request,
			HttpServletResponse response) {
		MessageDetail messageDetail= new MessageDetail();
		User loginUser = this.getLoginUser(request);
		// 判断是回复还是新建发送
		if ("answer".equals(actionType)) {
			if (parent_id == null)
				return new Result("MS0002", null); // 回复信息Id为空
			
			
		}
		// 定义发送时间
		Date sendTime = new Date();
		// 保存基本信息
		Message saveMessage = message;
		saveMessage.setSend_time(sendTime);
		saveMessage.setMessage_type("4");
		// 判断是回复还是新建发送
		if ("answer".equals(actionType)) {
			saveMessage.setFor_id(parent_id);
		}
		
		messageService.save(saveMessage);
		// 保存信息详情
		messageDetail.setSend_time(sendTime);
		messageDetail.setUser_id(loginUser.getId());
		messageDetail.setRead_state("N");
		messageDetail.setMessage_id(saveMessage.getId());
		messageDetail.setIs_delete('N');
		
		
		if (sendIds == null) { // 单发
			// 保存信息详情
			messageDetailService.save(messageDetail);
		} else if (sendIds != null && !"answer".equals(actionType)) { // 群发
			String[] idList = sendIds.split(",");
			Long[] idLongList = new Long[idList.length];
			for (int i = 0; i < idList.length; i++) {
				idLongList[i] = Long.parseLong(idList[i]);
			}
			for (int j = 0; j < idLongList.length; j++) {
				messageDetail.setSend_id(idLongList[j]);
				if (j > 0) {
					messageDetail.setId(messageDetail.getId() + j);
				}
				// 保存信息详情
				messageDetailService.save(messageDetail);
			}
		}
		return new Result(Boolean.TRUE, "发送成功!", null);

	}

	/**
	 * 批量删除消息(M2)
	 * 
	 * @param ids
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("batchDeleteMessage")
	public Result batchDeleteMessage(String ids, HttpServletRequest request,
			HttpServletResponse response) {
		// 检测登录
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		String[] str1 = ids.split(",");// 将String[] 转换为long[]
		List<Long> str2 = new ArrayList<Long>();

		for (int i = 0; i < str1.length; i++) {
			str2.add(Long.valueOf(str1[i]));
		}
		if (str2.size() == 0) {
			return new Result("MS0001", null); // 数组为空
		}

		messageDetailService.batchDeleteMessage(str2, loginUser.getId());

		return new Result(Boolean.TRUE, "删除成功!", null);

	}
	
	/**
	 * 信息列表  （M10）
	 * @param messageInfo
	 * @param start
	 * @param pageFlag
	 * @param sortColumn
	 * @param sortType
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("list")
	public Result list(String pageSize, String pageNo,String messageType,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		MessageVo messageVo = new MessageVo();
		// 检测登录
		User user = this.getLoginUser(request);
		/*if(user==null){
			user= new User();
			user.setId(17l);
		}*/
		Page page=null;
		Map<String, String> params = new HashMap<String, String>();
		// parent_id为null的时候说明是资产管理第一层级，初始值为0
		params.put("pageSize", pageSize);
		params.put("pageNo", pageNo);
		page = PageUtils.createPage(params);
		if(messageType==null || messageType.equals("")){
			messageType=ConstantUtil.MESSAGE_SYSTEM;
		}
		messageVo.setUser_id(user.getId());
		messageVo.setMessage_type(messageType);
		page = messageService.page(page, messageVo);
		map = new HashMap<String, Object>();
		map.put("total", page.getTotal());
		map.put("pageSize", page.getLimit());
		map.put("pageNo", page.getCurrentPage());
		map.put("dataList", page.getDataList());
		return new Result(Boolean.TRUE, "成功", map);

	}

	/**
	 * 搜索信息列表(M3)
	 * 
	 * @param messageInfo
	 * @param start
	 * @param pageFlag
	 * @param sortColumn
	 * @param sortType
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("searchMessage")
	public Result searchMessage(MessageInfo messageInfo, Long start,
			String pageFlag, String sortColumn, String sortType, Long limit,
			HttpServletRequest request, HttpServletResponse response) {
		Page page = null;
		Long pageMax = Page.DEFAULT_LIMIT;
		// 检测登录
		User loginUser = this.getLoginUser(request);
		if (limit != null && limit > 0) {
			pageMax = limit;
		}
		// 搜索自己信息
		messageInfo.setSend_id(loginUser.getId());
		messageInfo.setUser_id(loginUser.getId());
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			Long total = messageDetailService
					.selectMessageInfoListCount(messageInfo);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(pageMax * (start - 1));// 获取开始页面
			Long total = messageDetailService
					.selectMessageInfoListCount(messageInfo);
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
		page.setLimit(pageMax);
		List<MessageInfo> messageInfoList = messageDetailService
				.selectMessageInfoList(messageInfo, page);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 当前记录数
		retn.put("messageInfoList", messageInfoList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		return new Result(Boolean.TRUE, "成功!", retn);

	}

	/**
	 * 根据ID得到信息详情并更改为已读状态 (M4)
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getMessageInfoById")
	public Result getMessageInfoById(Long id, HttpServletRequest request,
			HttpServletResponse response) {
		MessageInfo messageInfo = messageDetailService.getMessageInfoById(id);
		return new Result(Boolean.TRUE, "获取成功!", messageInfo);
	}
	
	/**
	 * 根据ID得到信息并更改为已提交证据的状态 (M4A)
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("proofSubmitted")
	public Result proofSubmitted(Long id) {
		
		Message message =new Message();
		message.setId(id);
		message=messageService.selectForObject(message);
		message.setManipulate_status(ConstantUtil.MESSAGE_SUBMIT);   //把状态改为已提交证据
		messageService.update(message);
		return new Result(Boolean.TRUE, "更改成功!", message);
		
	}

	/**
	 * 获取图片验证码（M5）
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("getImgVerCode")
	public Result getImgVerCode(HttpServletRequest request,
			HttpServletResponse response, String codeName) throws IOException {
		// 将四位数字的验证码保存到Session中。
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		ValidateCodeImgUtil util = new ValidateCodeImgUtil();
		// 获取验证图片
		String code = util.getCode(response);

		if (codeName == null) { // 默认为code
			codeName = "code";

		}
		session.setAttribute(codeName, code);
		System.out.println(session.getAttribute(codeName));

		return new Result(Boolean.TRUE, "获取成功!", null);
	}

	/**
	 * 校验图片验证码（M6）
	 * 
	 * @param request
	 * @param response
	 * @param codeName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("checkVerCode")
	public Result checkVerCode(HttpServletRequest request,
			HttpServletResponse response, String codeName, String code) {
		HttpSession session = request.getSession(); // session

		String sessonCode = (String) session.getAttribute(codeName);
		if (sessonCode == null) {
			return new Result("MS0003", null); // 请审请验证码
		}
		// 转换小写
		sessonCode = sessonCode.toUpperCase();
		code = code.toUpperCase();

		if (sessonCode.equals(code)) {

			return new Result(Boolean.TRUE, "验证成功!", null);
		} else {
			return new Result("MS0004", null); // 回复信息Id为空
		}
	}

	/**
	 * 发送用户信息(M7)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping("sendMessage")
	public Result sendMessage(MessageVo messageVo, String flag,
			HttpServletRequest request, HttpServletResponse response) {
		// 检测登录
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		if ("arbitrateInvite".equals(flag)) { // 仲裁邀请
			// 仲裁邀请信息生成
			return this.createdArbitrateInfo(messageVo, request,
					loginUser.getNickname());
		} else if ("noPass".equals(flag)) {
			return this.sendPass(messageVo);
		}else if("pass".equals(flag)){
			return this.sendNoPass(messageVo);
			
		}
		return new Result(Boolean.TRUE, "发出成功!", null);
	}
*/
	/**
	 * 得到信息及回复信息(M8)
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getMessageInfoForRe")
	public Result getMessageInfoForRe(Long id, HttpServletRequest request,
			HttpServletResponse response) {

		List<MessageInfo> mList = messageDetailService.getMessageInfoForRe(id);
		return new Result(Boolean.TRUE, "查询成功!", mList);
	}
	
	/**
	 * 统计各类消息未读消息的数量 (M9)
	 * @param id
	 *  @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping("selectUnReadMessageListCount")
	public Result selectUnReadMessageListCount(HttpServletRequest request,
			HttpServletResponse response) {
		User user =(User)request.getSession().getAttribute("loginUser");
		List<Map> mList = messageDetailService.selectUnReadMessageListCount(user.getId());
		return new Result(Boolean.TRUE, "查询成功!", mList);
	}

	
	/**
	 * 创建审核通过的消息
	 * @param messageVo
	 * @return
	 */
	/*
	   private  Result sendPass(MessageVo messageVo) {
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
		messageVo.setMessage_type(ConstantUtil.MESSAGE_APPROVE);   //审批消息
		messageVo.setTitle("您提交的认证信息已通过");
		messageVo.setContent(msgContent.toString());
		messageVo.setSend_time(new Date());
//		messageService.save(messageVo);
		MessageDetail messageDetail = new MessageDetail();
		// 赋值接收方信息
		messageDetail.setIs_delete(ConstantUtil.MESSAGE_DELETE_N);
		messageDetail.setMessage_id(messageVo.getId());
		messageDetail.setRead_state(ConstantUtil.MESSAGE_STATE_N);
		messageDetail.setSend_time(new Date());
		messageDetail.setSend_id(messageVo.getSendId());
		// messageVo.setContent(content);

		// 保存信息
		// message.setSend_time(new Date());
		// message.
//		messageDetailService.save(messageDetail);
		messageService.sndMessAge(messageVo, messageDetail);
		return new Result(Boolean.TRUE, "发送成功!", null);
	}
	*/
	
	/*
	 * 创建审核不通过的消息
	 */
	/*
	private  Result sendNoPass(MessageVo messageVo) {
		// 拼接内容
		StringBuffer msgContent = new StringBuffer();
		if ("company".equals(messageVo.getPassFlag())) {
			msgContent.append("您提交的公司认证信息未通过认证!");
		} else {
			msgContent.append("您提交的实名认证信息未通过认证!");

		}
		// 保存信息
		messageVo.setContent(msgContent.toString());
		// 赋值基本信息
		messageVo.setMessage_type(ConstantUtil.MESSAGE_APPROVE);   //审批消息
		messageVo.setTitle("您提交的认证信息未通过");
		messageVo.setContent(msgContent.toString());
		messageVo.setSend_time(new Date());
//		messageService.save(messageVo);
		MessageDetail messageDetail = new MessageDetail();
		// 赋值接收方信息
		messageDetail.setIs_delete(ConstantUtil.MESSAGE_DELETE_N);
		messageDetail.setMessage_id(messageVo.getId());
		messageDetail.setRead_state(ConstantUtil.MESSAGE_STATE_N);
		messageDetail.setSend_time(new Date());
		messageDetail.setSend_id(messageVo.getSendId());
		// messageVo.setContent(content);

		// 保存信息
		// message.setSend_time(new Date());
		// message.
//		messageDetailService.save(messageDetail);
		messageService.sndMessAge(messageVo, messageDetail);
		return new Result(Boolean.TRUE, "发送成功!", null);
	}
	*/

	/*
	   private  Result send(MessageVo messageVo) {
		// 拼接内容
		StringBuffer msgContent = new StringBuffer();
		if ("company".equals(messageVo.getPassFlag())) {
			msgContent.append("您提交的公司认证信息未通过认证!");
		} else {
			msgContent.append("您提交的实名认证信息未通过认证!");

		}
		// 保存信息
		messageVo.setContent(msgContent.toString());
		// 赋值基本信息
		messageVo.setMessage_type(ConstantUtil.MESSAGE_APPROVE);   //审批消息
		messageVo.setTitle("您提交的认证信息未通过");
		messageVo.setContent(msgContent.toString());
		messageVo.setSend_time(new Date());
//		messageService.save(messageVo);
		MessageDetail messageDetail = new MessageDetail();
		// 赋值接收方信息
		messageDetail.setIs_delete(ConstantUtil.MESSAGE_DELETE_N);
		messageDetail.setMessage_id(messageVo.getId());
		messageDetail.setRead_state(ConstantUtil.MESSAGE_STATE_N);
		messageDetail.setSend_time(new Date());
		messageDetail.setSend_id(messageVo.getSendId());
		// messageVo.setContent(content);

		// 保存信息
		// message.setSend_time(new Date());
		// message.
//		messageDetailService.save(messageDetail);
		messageService.sndMessAge(messageVo, messageDetail);
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

	@Autowired(required = true)
	public void setRecommendService(
			@Qualifier("messageDetailService") IMessageDetailService messageDetailService) {
		this.messageDetailService = messageDetailService;
	}

//	@Autowired(required = true)
//	public void setRecommendService(
//			@Qualifier("messageService") IMessageService messageService) {
//		this.messageService = messageService;
//	}
	//测试生成任务领取成的消息
	/*@ResponseBody
	@RequestMapping("/createMessageInfo")
    public void createMessage(HttpServletRequest request){
    	messageService.createReceiveTaskMessage(20L, "1", request);
    }*/
}
