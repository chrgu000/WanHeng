package com.cgwas.message.action;



import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cgwas.animationlighttask.entity.AnimationLightTaskVo;
import com.cgwas.animationlighttask.service.api.IAnimationLightTaskService;
import com.cgwas.arbitrateInfo.entity.ArbitrateInfo;
import com.cgwas.arbitrateInfo.service.api.IArbitrateInfoService;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.CommonUtil;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.company.entity.Company;
import com.cgwas.company.entity.CompanyVo;
import com.cgwas.company.service.api.ICompanyService;
import com.cgwas.message.entity.Message;
import com.cgwas.message.entity.MessageVo;
import com.cgwas.message.service.api.IMessageService;
import com.cgwas.message.service.api.ISendMessageService;
import com.cgwas.messageDetail.entity.MessageDetail;
import com.cgwas.messageDetail.entity.MessageInfo;
import com.cgwas.messageDetail.service.api.IMessageDetailService;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.user.entity.User;
import com.cgwas.user.service.api.IUserService;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userCompany.service.api.IUserCompanyService;
import com.sun.mail.util.MailSSLSocketFactory;


@Controller
@RequestMapping("cgwas/sendMessageAction")
public class SendMessageAction {
	@Autowired
	private ICompanyService companyService;//公司
	@Autowired
	private IMessageService messageService;
	@Autowired
	private IMessageDetailService messageDetailService;
	@Autowired
	private ISendMessageService sendMessageService;
	@Autowired
	private IArbitrateInfoService arbitrateInfoService;
	@Autowired
	private IModelTaskService modelTaskService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAnimationLightTaskService animationLightTaskService;
	@Autowired
	private IUserCompanyService userCompanyService;
	/**
	 * 发送邀请消息 To User  (M11)
	 * @author zmq
	 * 
	 */
	@ResponseBody
	@RequestMapping("/companyInviteMessage")
	public Result CompanyInviteMessage(Long user_id,HttpServletRequest request) {
		User loginUser =  (User)request.getSession().getAttribute("loginUser");
		Long company_id=(Long) request.getSession().getAttribute("sessionCompany");  //公司id
		
//		Long company_id=1L;
		
		if (company_id== null|| user_id== null) {
			return new Result("SYS_PARAMETER", null);
		}
		/**
		 * 查询公司
		 */
		CompanyVo companyVo = new CompanyVo();
		companyVo.setId(company_id);
		companyVo = companyService.selectForObject(companyVo);
		
		//给User发消息
		Message messageToUser=new Message();
		messageToUser.setFor_id(company_id);
		messageToUser.setMessage_type(ConstantUtil.MESSAGE_JOIN_USER);   //员工邀请
		
		messageToUser.setTitle(companyVo.getCompany_name()+"邀请您加入该公司。");
		messageToUser.setContent(companyVo.getCompany_name()+"邀请您加入该公司。");
		
		messageToUser.setManipulate_status(ConstantUtil.MESSAGE_JOIN_EMPLOYEE);    //设置为邀请操作的信息
		
		MessageDetail detailToUser = new MessageDetail();
		detailToUser.setRead_state(ConstantUtil.MESSAGE_STATE_N);  //未读
		detailToUser.setSend_id(loginUser.getId());// 发送者id
		//detailToUser.setSend_id(3L);
		detailToUser.setUser_id(user_id);// 接收者id
		messageService.sndMessAge(messageToUser, detailToUser);//存消息
		
		return new Result(Boolean.TRUE,"邀请信息发送成功",null);
			
	}
	
	/**
	 * 发送接收或者拒绝邀请的消息(M12)
	 * @param message_id
	 * @param button
	 * @param request
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/acceptOrReject")   
	public Result AcceptInvitation(Long message_id,boolean button,HttpServletRequest request) {
		User loginUser =  (User)request.getSession().getAttribute("loginUser");
		MessageInfo messageInfo=messageDetailService.getMessageInfoById(message_id);  //根据消息id得到消息详情
				
		Message messageToCompany=new Message();
		Company company=new Company();
		company.setId(messageInfo.getFor_id());
		company=companyService.selectCompanyById(company);  //根据公司id得到公司信息
		
		messageToCompany.setFor_id(messageInfo.getUser_id());  //设置为user_id
		
		messageToCompany.setMessage_type(ConstantUtil.MESSAGE_SYSTEM);  //系统类型
		
		MessageDetail  detailToCompany=new MessageDetail();
		detailToCompany.setRead_state(ConstantUtil.MESSAGE_STATE_N);  //未读
		detailToCompany.setSend_id(messageInfo.getUser_id());   // 发送者id
		detailToCompany.setUser_id(messageInfo.getSend_id());  //  接受者id 把消息详情中的发送者设置为此处的接受者
		
		if(button==true){
			messageToCompany.setTitle("用户"+loginUser.getUsername()+"应您的邀请加入了"+company.getCompany_name());
			messageToCompany.setContent("用户"+loginUser.getUsername()+"应您的邀请加入了"+company.getCompany_name());
//			messageToCompany.setManipulate_status(manipulate_status);
//			messageToCompany.setTitle("用户111应您的邀请加入了"+company.getCompany_name());
//			messageToCompany.setContent("用户111应您的邀请加入了"+company.getCompany_name());
			messageService.sndMessAge(messageToCompany, detailToCompany);//存消息
		
			return new Result(Boolean.TRUE,"接受邀请信息发送成功",null);
		}else{
			messageToCompany.setTitle("用户"+loginUser.getUsername()+"拒绝了加入"+company.getCompany_name()+"的邀请");
			messageToCompany.setContent("用户"+loginUser.getUsername()+"拒绝加入了"+company.getCompany_name()+"的邀请");
//			messageToCompany.setTitle("用户111拒绝了加入"+company.getCompany_name()+"的邀请");
//			messageToCompany.setContent("用户111拒绝加入了"+company.getCompany_name()+"的邀请");
			messageService.sndMessAge(messageToCompany, detailToCompany);//存消息
			return new Result(Boolean.TRUE,"拒绝邀请信息发送成功",null);
		}
	}
	
	
	/**
	 * 创建仲裁信息
	 * 
	 * @param messageVo
	 * @param request
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("createdArbitrateInfo")   // arbitrate_id 仲裁id   arbitrate_type 仲裁类型
	private Result createdArbitrateInfo(MessageVo messageVo,
			HttpServletRequest request, String userName) {
		if (!CommonUtil.checkAdmin(request)) {
			return new Result("MS0005", null); // 非管理员
		}

		// 查询仲裁信息
		ArbitrateInfo sArbitrateInfo = new ArbitrateInfo();
		sArbitrateInfo.setId(messageVo.getArbitrate_id());
		sArbitrateInfo = arbitrateInfoService.selectForObject(sArbitrateInfo);

		String companyName = ""; // 仲裁公司名
		String userNames = ""; // 仲裁用户名
		String taskName = ""; // 任务名字
		// 公司信息
		Company company = new Company();
		// 用户信息
		User user = new User();
		Long task_id = sArbitrateInfo.getTask_id();
		String task_type = sArbitrateInfo.getTask_type();
		Long sendId = 0L;
		if ("1".equals(task_type)) {// 得到任务信息
			ModelTaskVo modalTask = new ModelTaskVo();
			modalTask.setId(task_id);
			modalTask = modelTaskService.getModelTaskById(modalTask);

			// 得到双方信息
			user = userService.getUserById(modalTask.getMaker().getUser_id());
			userNames = user.getNickname();

			company.setId(modalTask.getCompany_id());
			company = companyService.selectForObject(company);
			companyName = company.getCompany_name();
			taskName = modalTask.getName();
		} else {
			AnimationLightTaskVo aTask = new AnimationLightTaskVo();
			aTask.setId(task_id);
			aTask = animationLightTaskService.getAnimationLightTaskById(aTask);

			// 得到双方信息
			user = userService.getUserById(aTask.getMaker().getUser_id());
			userNames = user.getNickname();

			company.setId(aTask.getCompany_id());
			company = companyService.selectForObject(company);
			companyName = company.getCompany_name();
			taskName = aTask.getPattern_number();
		}
		// 拼接内容
		StringBuffer msgContent = new StringBuffer();
		if (!"user".equals(messageVo.getArbitrate_type())) { // 若为公司则找出法人id
			msgContent.append("【" + userName + "】对于【" + taskName + "】任务发起仲裁,请您提供相关仲裁质料已便仲裁团进行裁决。");
			sendId = userCompanyService.companygetUser(company.getId())
					.getUse_id(); // 法人id
		} else {
			msgContent.append("【" + userName + "】对于【" + taskName + "】任务发起仲裁,请您提供相关仲裁质料已便仲裁团进行裁决。");
			sendId = user.getId(); // 人员id
		}
		// 保存信息
		messageVo.setContent(msgContent.toString());
		// 赋值基本信息
		messageVo.setMessage_type(ConstantUtil.MESSAGE_ARBITRATEMESSAGE);
		messageVo.setTitle("您需要提交一份证据以保证仲裁的公正");
		messageVo.setManipulate_status(ConstantUtil.MESSAGE_UNCOMMITTED); //待提交证据的操作标志
		messageVo.setContent(msgContent.toString());
		messageVo.setSend_time(new Date());
		messageVo.setFor_id(sArbitrateInfo.getId());
//		messageService.save(messageVo);
		MessageDetail messageDetail = new MessageDetail();
		// 赋值接收方信息
		messageDetail.setIs_delete(ConstantUtil.MESSAGE_DELETE_N);
		messageDetail.setMessage_id(messageVo.getId());
		messageDetail.setRead_state(ConstantUtil.MESSAGE_STATE_N);
		messageDetail.setSend_time(new Date());
		messageDetail.setSend_id(sendId);    //发件人
		messageDetail.setUser_id(user.getId());   //收件人
		// messageVo.setContent(content);

		// 保存信息
		// message.setSend_time(new Date());
		// message.
//		messageDetailService.save(messageDetail);
		messageService.sndMessAge(messageVo, messageDetail);
		
		return new Result(Boolean.TRUE, "发送成功!", null);

	}
	/**
	 * 发送邀请证据的消息（M13）
	 * @param request
	 * @param recieve_id  
	 * @param arbitrate_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/askProof")   
	public Result  askProof(HttpServletRequest request,Long recieve_id,Long arbitrate_id) {
		User loginUser =  (User)request.getSession().getAttribute("loginUser");
		sendMessageService.askProofInfo(loginUser.getId(), recieve_id, arbitrate_id);
		return new Result(Boolean.TRUE, "发送成功!", null);
	}
	
	

}
