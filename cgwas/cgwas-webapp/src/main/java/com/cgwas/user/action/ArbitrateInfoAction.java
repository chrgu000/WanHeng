package com.cgwas.user.action;

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

import com.cgwas.animationlighttask.entity.AnimationLightTaskVo;
import com.cgwas.animationlighttask.service.api.IAnimationLightTaskService;
import com.cgwas.arbitrateInfo.entity.ArbitrateInfo;
import com.cgwas.arbitrateInfo.entity.ArbitrateInfoDetail;
import com.cgwas.arbitrateInfo.entity.ArbitrateInfoHead;
import com.cgwas.arbitrateInfo.service.api.IArbitrateInfoService;
import com.cgwas.arbitrateUserInfo.entity.ArbitrateImage;
import com.cgwas.arbitrateUserInfo.entity.ArbitrateUserInfo;
import com.cgwas.arbitrateUserInfo.entity.CompanyArbitrateInfo;
import com.cgwas.arbitrateUserInfo.entity.UserArbitrateInfo;
import com.cgwas.arbitrateUserInfo.service.api.IArbitrateUserInfoService;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.forbid.entity.ForbidUser;
import com.cgwas.imgInfo.entity.ImgInfo;
import com.cgwas.imgInfo.service.api.IImgInfoService;
import com.cgwas.message.entity.Message;
import com.cgwas.message.service.api.IMessageService;
import com.cgwas.message.service.api.ISendMessageService;
import com.cgwas.message.service.impl.MessageService;
import com.cgwas.message.service.impl.SendMessageService;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.user.entity.User;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userCompany.service.api.IUserCompanyService;

@Controller
@RequestMapping("cgwas/userAction")
public class ArbitrateInfoAction {
	@Autowired
	private IArbitrateInfoService arbitrateInfoService = null;
	@Autowired
	private IArbitrateUserInfoService arbitrateUserInfoService = null;
	@Autowired
	private IArbitrateInfoService arbitrateService = null;
	@Autowired
	private IModelTaskService modelTaskService;
	@Autowired
	private IAnimationLightTaskService animationLightTaskService;
	@Autowired
	private IUserCompanyService userCompanyService;
	@Autowired
	private ISendMessageService sendMessageService;
	@Autowired
	private  IMessageService messageService;
	/**
	 * 获取仲裁记录数量和完成数量(R76)
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getArbitrateInfoCount")
	public Result getArbitrateInfoCount() {
		// 总数
		Long allCount = arbitrateInfoService.getArbitrateInfoCount(null);
		// 已经审核数
		Long finishCount = arbitrateInfoService.getArbitrateInfoCount("结束");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allCount", allCount);
		map.put("finishCount", finishCount);

		return new Result(Boolean.TRUE, "成功!", map);
	}

	/**
	 * 获取仲裁记录列表(R77)
	 * 
	 * @param arbitrateInfoDetail
	 * @param start
	 * @param pageFlag
	 * @param forbidUser
	 * @param sortColumn
	 * @param sortType
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getArbitrateInfoList")
	public Result getArbitrateInfoList(ArbitrateInfoDetail arbitrateInfoDetail,
			Long start, String pageFlag, ForbidUser forbidUser,
			String sortColumn, String sortType, HttpServletRequest request,
			HttpServletResponse response) {
		Long total = 0L;
		Page page = null;
		if (pageFlag == null) { // 判断是否是翻页操作
			Long startPage = start;
			if (startPage == null) { // 当开始页数为空时 赋值为0
				startPage = 0L;
			}
			page = new Page(startPage);// 页码参数
			total = arbitrateInfoService
					.getArbitrateInfoListCount(arbitrateInfoDetail);
			// 设置记录总数
			page.setTotal(total);
		} else {
			page = new Page(Page.DEFAULT_LIMIT * (start - 1));// 获取开始页面
			total = arbitrateInfoService
					.getArbitrateInfoListCount(arbitrateInfoDetail);
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
		List<ArbitrateInfoDetail> userList = arbitrateInfoService
				.getArbitrateInfoList(arbitrateInfoDetail, page);
		Map<String, Object> retn = new HashMap<String, Object>();
		retn.put("start", page.getStart()); // 赋值当前记录数
		retn.put("userList", userList); // 数据
		retn.put("pageNum", page.getPageCount()); // 页面数量
		retn.put("total", total); // 页面数量
		return new Result(Boolean.TRUE, "成功!", retn);

	}

	/**
	 * 获取仲裁详细信息(R78)
	 * 
	 * @param user_type
	 * @param arbitrate_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getArbitrateUserInfo")
	public Result getArbitrateUserInfo( Long arbitrate_id) {

		// 查询人员仲裁公司
//		if (user_type =='U') {
			// 发起方
			UserArbitrateInfo initiateUser = new UserArbitrateInfo();
			List<ArbitrateImage> initiateImages=null;
			List<ArbitrateImage> refuteImages=null;
			//被告方
			UserArbitrateInfo refuteUser = new UserArbitrateInfo();
			// 获取仲裁信息
			UserArbitrateInfo in = new UserArbitrateInfo();
			in.setUser_type('A');
			in.setArbitrate_id(arbitrate_id);
			// 获取仲裁信息发起方信息
			List<UserArbitrateInfo> retn = arbitrateUserInfoService
					.getUserArbitrateInfo(in);
			if (retn != null) {
				initiateUser = retn.get(0);
				initiateImages=arbitrateInfoService.getArbitrateImgByAUserId(initiateUser.getId());  //获取图片
			} else {
				return new Result("RY0033", null);// 仲裁发起方信息为空
			}
		
			// 反驳方列表
			in.setUser_type('D');
			List<UserArbitrateInfo> refuteUserList = arbitrateUserInfoService
					.getUserArbitrateInfo(in);
			if (refuteUserList != null) {
				refuteUser = refuteUserList.get(0);
				refuteImages=arbitrateInfoService.getArbitrateImgByAUserId(refuteUser.getId()); //获取图片
			} else {
				return new Result("RY0000", null);// 仲裁被告方信息为空
			}
			// 封装数据返回前台
			
			
			HashMap<String, Object> datas = new HashMap<String, Object>();
			datas.put("initiateUser", initiateUser);
//			datas.put("supportUserList", supportUserList);
			datas.put("refuteUser", refuteUser);
			datas.put("initiateImages", initiateImages);  //放入图片
			datas.put("refuteImages", refuteImages);
			Message message=new Message();
			message.setFor_id(arbitrate_id);
			Long messageCount=messageService.getArbitrateMessageCount(message);
//			System.out.println("messageCount   "+messageCount);
			datas.put("messageCount", messageCount);

			return new Result(Boolean.TRUE, "获取成功!", datas);
	}

	/**
	 * 获取仲裁图片列表(R79)
	 * 
	 * @param ids
	 * @return
	 */
	/*@ResponseBody
	@RequestMapping("getArbitrateImgList")
	public Result getArbitrateImgList(String ids) {
		ImgInfo searchImgInfo = new ImgInfo();
		searchImgInfo.setImg_type("1");
		List<ImgInfo> retn = iImgInfoService.getImgInfoById(searchImgInfo, ids);
		return new Result(Boolean.TRUE, "获取成功!", retn);
	}*/

	
	
	/**
	 * 判决仲裁（R81）
	 * @param flag
	 * @param id    
	 * @param user_id  仲裁中的用户id
	 * @param companyUser_id   公司接受人id 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("finishArbitrate")
	public Result finishArbitrate(String flag, Long id,Long user_id,Long companyUser_id,
			HttpServletRequest request, HttpServletResponse response) {
		arbitrateInfoService.finishArbitrate(flag, id);
//		UserCompany userCompany=userCompanyService.companygetUser(company_id);
		User loginUser =  (User)request.getSession().getAttribute("loginUser");
//		sendMessageService.feedbackMessage(152L, userCompany.getUse_id(), id);//给公司法人发
//		sendMessageService.feedbackMessage(152L, user_id, id);  //给用户发
		sendMessageService.feedbackMessage(loginUser.getId(), companyUser_id, id);//给公司法人发
		sendMessageService.feedbackMessage(loginUser.getId(), user_id, id);  //给用户发
		return new Result(Boolean.TRUE, "修改成功!", null);

	}

	/**
	 * 获取仲裁头部状态信息(R139)
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getArbitrateInfo")
	public Result getArbitrateInfo(Long id, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取任务类型
		ArbitrateInfo sArbitrateInfo = new ArbitrateInfo();
		sArbitrateInfo.setId(id);
		sArbitrateInfo = arbitrateService.selectForObject(sArbitrateInfo);
		ArbitrateInfoHead arbitrateInfoHead = new ArbitrateInfoHead();
		// 根据任务类型搜索任务

		arbitrateInfoHead = arbitrateInfoService.getArbitrateInfo(id,
				sArbitrateInfo.getTask_type());

		return new Result(Boolean.TRUE, "获取成功!", arbitrateInfoHead);
	}
/**
 * 创建仲裁信息（R144）
 * 
 * 
 * @param arbitrateInfo
 * @param arbitrate_content
 * @param sponsor
 * @param request
 * @param response
 * @return
 */
	@ResponseBody
	@RequestMapping("createArbitrate")
	public Result createArbitrate(ArbitrateInfo arbitrateInfo,String arbitrate_content, String sponsor,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		// 判断公司发起还是人员发起
		User loginUser = this.getLoginUser(request);
		if (loginUser == null) {
			return new Result("RY0008", null); // 登录过期
		}
		Long company_id = 0L;
		Long user_id = 0L;
		// 获取任务信息
		if ("1".equals(arbitrateInfo.getTask_type())) { // 建模
			// 获取任务
			ModelTaskVo modelTaskVo = new ModelTaskVo();
			modelTaskVo.setId(arbitrateInfo.getTask_id());
			modelTaskVo = modelTaskService.getModelTaskById(modelTaskVo);
			// 获取公司及人员id
			user_id = modelTaskVo.getMaker().getId();
			company_id = modelTaskVo.getCompany_id();
		} else {// 灯光
			// 获取任务
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setId(arbitrateInfo.getTask_id());
			aTask=animationLightTaskService.getAnimationLightTaskById(aTask);
			// 获取公司及人员id
			user_id = aTask.getMaker().getId();
			company_id = aTask.getCompany_id();
		}

		String adminFlag = (String) session.getAttribute("adminFlag");

		ArbitrateUserInfo arbitrateUserInfo = new ArbitrateUserInfo(); // 发起方信息

		if ("ADMIN".equals(adminFlag)) { // 管理员可选择发起方
			if ("company".equals(sponsor)) {
				return new Result(Boolean.TRUE, "你好管理员!", null);
			} else {
				return new Result(Boolean.TRUE, "你好管理员!", null);
			}

		} else if ("COMPANY".equals(adminFlag)) { // 公司发起
			UserCompany us = userCompanyService.companygetUser(company_id);

			if (!loginUser.getId().equals(us.getUse_id())) {
				return new Result("RY0075", null);// 非法人
			}
			arbitrateUserInfo.setUser_id(user_id);
			arbitrateUserInfo.setCompany_id(company_id);
			arbitrateUserInfo.setUser_type("2");
			arbitrateUserInfo.setIs_pass('N');// 默认通过
		} else { // 人员发起
			if (!loginUser.getId().equals(user_id)) {
				return new Result("RY0076", null);// 非法人
			}
			arbitrateUserInfo.setUser_id(user_id);
			arbitrateUserInfo.setCompany_id(company_id);
			arbitrateUserInfo.setUser_type("1");
			arbitrateUserInfo.setIs_pass('N');// 默认通过
			
		}

		// 初始化信息
		arbitrateInfo.setArbitrate_state("1");
		arbitrateInfo.setCreate_time(new Date());
		arbitrateInfoService.save(arbitrateInfo);
		arbitrateUserInfo.setArbitrate_id(arbitrateInfo.getId());
		arbitrateUserInfo.setArbitrate_content(arbitrate_content);
		arbitrateUserInfoService.save(arbitrateUserInfo);

		return new Result(Boolean.TRUE, "新建成功!", null);
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

}
