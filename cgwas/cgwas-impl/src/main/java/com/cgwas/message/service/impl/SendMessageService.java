package com.cgwas.message.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgwas.animationlighttask.entity.AnimationLightTaskVo;
import com.cgwas.animationlighttask.service.api.IAnimationLightTaskService;
import com.cgwas.arbitrateInfo.entity.ArbitrateInfo;
import com.cgwas.arbitrateInfo.service.api.IArbitrateInfoService;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.CommonUtil;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.company.entity.Company;
import com.cgwas.company.service.api.ICompanyService;
import com.cgwas.message.entity.Message;
import com.cgwas.message.entity.MessageVo;
import com.cgwas.message.service.api.IMessageService;
import com.cgwas.message.service.api.ISendMessageService;
import com.cgwas.messageDetail.entity.MessageDetail;
import com.cgwas.messageDetail.service.api.IMessageDetailService;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.project.entity.Project;
import com.cgwas.project.service.api.IProjectService;
import com.cgwas.subproject.entity.SubProject;
import com.cgwas.subproject.service.api.ISubProjectService;
import com.cgwas.subproject.service.impl.SubProjectService;
import com.cgwas.user.entity.User;
import com.cgwas.user.service.api.IUserService;
import com.cgwas.user.service.impl.UserService;
import com.cgwas.userCompany.service.api.IUserCompanyService;
@Service
public class SendMessageService implements ISendMessageService{
	
	@Autowired
	private IModelTaskService modelTaskService;
	@Autowired
	private IMessageDetailService messageDetailService;
	@Autowired
	private IAnimationLightTaskService animationLightTaskService;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private ISubProjectService subProjectService;
	@Autowired
	private IMessageService messageService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IArbitrateInfoService arbitrateInfoService;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IUserCompanyService userCompanyService;
	
	/**
		 *  生成任务接受成功的消息(M1)
		 * @param task_id
		 * @param task_type
		 * @param request
		 * @return
		 */
	@Override	
	@Transactional
	public boolean createReceiveTaskMessage(Long task_id,String task_type,Long loginUser_id ){
	//	    User loginUser =  (User)request.getSession().getAttribute("loginUser");
	     	StringBuffer msgContent = new StringBuffer();   //消息内容
	    	String pattern_number;
	    	Long  user_id = null;   //接受任务者的id
	//    	String companyName;
	    	Project project;
	    	SubProject subProject ;
	    	String projectName;
	    	Long project_id;// 所属项目id
	//    	Long company_id;
	    	if("1".equals(task_type)){
	    		ModelTaskVo modelTaskVo=modelTaskService.getDetails(task_id); //获取任务详情
	    		project_id=modelTaskVo.getProject_id();
	    		if(modelTaskVo.getIs_parent_poject().equals("1")){
	    			project=projectService.getProjectDetails(project_id);
		    		projectName=project.getName();  //项目名称
	    		}else{
	    			subProject=subProjectService.getProjectDetails(project_id);
	    			projectName=subProject.getName();
	    		}
	    		
	    		pattern_number=modelTaskVo.getPattern_number();  //任务编号
	//    		company_id=modelTaskVo.getCompany_id();  //得到公司ID 
	    		user_id=modelTaskVo.getUser_id();  //接受任务者的id
	    		
	    	}else{
	    		AnimationLightTaskVo animationLightTaskVo=animationLightTaskService.getDetails(task_id);  //获取任务详情
	    		pattern_number=animationLightTaskVo.getPattern_number();  //任务编号
	    		user_id=animationLightTaskVo.getUser_id();
	//   		    company_id=animationLightTaskVo.getCompany_id();  //得到公司ID 
	   		   project_id=animationLightTaskVo.getProject_id();
	    		project=projectService.getProjectDetails(project_id);
	    		projectName=project.getName();  //项目名称
	    		pattern_number=animationLightTaskVo.getPattern_number();  //任务编号
	    	}
	    	
//	    	Company company = new Company();
//			company.setId(company_id);  
//			company = companyService.selectForObject(company);  //获取公司信息
//			companyName = company.getCompany_name();   //公司名字
			
			msgContent.append("您接受了项目《"+projectName+"》"+"中的编号为“"+pattern_number+"”的任务");
			Message messageVo =new Message();
			// 保存信息
			messageVo.setContent(msgContent.toString());
			// 赋值基本信息
			messageVo.setMessage_type(ConstantUtil.MESSAGE_WORKORDER);
			
			messageVo.setTitle("您接受了项目《"+projectName+"》"+"中的编号为“"+pattern_number+"”的任务");
			messageVo.setContent(msgContent.toString());
			messageVo.setSend_time(new Date());
			messageVo.setFor_id(task_id);
			//IAnimationLightTaskService messageService;
//			messageService.save(messageVo);
			MessageDetail messageDetail = new MessageDetail();
			// 赋值接收方信息
			messageDetail.setIs_delete(ConstantUtil.MESSAGE_DELETE_N);   
			messageDetail.setMessage_id(messageVo.getId());
			messageDetail.setRead_state(ConstantUtil.MESSAGE_STATE_N);
			messageDetail.setSend_time(new Date());
			messageDetail.setSend_id(loginUser_id);  //发件人
			messageDetail.setUser_id(user_id);     //收件人
//			messageDetailService.save(messageDetail);
			messageService.sndMessAge(messageVo, messageDetail);
	    	return true;
	    	
	    }
	
	/**
	 * 生成评价成功的消息(M2)
	 * @param send_id
	 * @param recieve_id
	 * @param request
	 * @return
	 */
	@Override	
	@Transactional
	public boolean  evaluateMessage(Long send_id,Long recieve_id) {
			User user=userService.getUserById(send_id);
			Message messageVo =new Message();
			// 保存信息
			Calendar calendar = Calendar.getInstance();  
			calendar.setTime(new Date());
			try {
				messageVo.setTitle("用户"+user.getUsername()+"于"+calendar.get(Calendar.YEAR)+"."+(calendar.get(Calendar.MONTH) + 1)+"."+calendar.get(Calendar.DAY_OF_MONTH)
				                      +"对你作出了评价");
				messageVo.setContent("用户"+user.getUsername()+"于"+calendar.get(Calendar.YEAR)+"."+(calendar.get(Calendar.MONTH) + 1)+"."+calendar.get(Calendar.DAY_OF_MONTH)
				                      +"对你作出了评价");
				// 赋值基本信息
				messageVo.setMessage_type(ConstantUtil.MESSAGE_EVALUATE);   //评价消息
				messageVo.setSend_time(new Date());
				messageVo.setFor_id(send_id);
				//IAnimationLightTaskService messageService;
				messageService.save(messageVo);
				MessageDetail messageDetail = new MessageDetail();
				// 赋值接收方信息
				messageDetail.setIs_delete(ConstantUtil.MESSAGE_DELETE_N);
				messageDetail.setMessage_id(messageVo.getId());
				messageDetail.setRead_state(ConstantUtil.MESSAGE_STATE_N);
				messageDetail.setSend_time(new Date());
				messageDetail.setSend_id(send_id);  //发件人
				messageDetail.setUser_id(recieve_id);     //收件人
				messageDetailService.save(messageDetail);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
		}
	/**
	 * 生成欢迎消息 （M3）
	 * @param user_id
	 * @return
	 */
	@Override
	@Transactional
	public boolean welcomeMessage(Long user_id) {
		Message messageVo =new Message();
		try {
			messageVo.setTitle("欢迎加入动画梦工厂");
			                      
			messageVo.setContent("欢迎加入动画梦工厂");
			// 赋值基本信息
			messageVo.setMessage_type(ConstantUtil.MESSAGE_SYSTEM);   //系统消息
			messageVo.setSend_time(new Date());
			messageVo.setFor_id(0L);
			//IAnimationLightTaskService messageService;
			messageService.save(messageVo);
			MessageDetail messageDetail = new MessageDetail();
			// 赋值接收方信息
			messageDetail.setIs_delete(ConstantUtil.MESSAGE_DELETE_N);
			messageDetail.setMessage_id(messageVo.getId());
			messageDetail.setRead_state(ConstantUtil.MESSAGE_STATE_N);
			messageDetail.setSend_time(new Date());
			messageDetail.setSend_id(1L);  //发件人  超级管理员
			messageDetail.setUser_id(user_id);     //收件人
			messageDetailService.save(messageDetail);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * 创建审批认证不通过的消息(M4)  用于R27 R28
	 */
	@Override
	@Transactional
	public   boolean sendNoPass(MessageVo messageVo) {
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
		messageVo.setFor_id(0L);
//		messageService.save(messageVo);
		MessageDetail messageDetail = new MessageDetail();
		// 赋值接收方信息
		messageDetail.setIs_delete(ConstantUtil.MESSAGE_DELETE_N);
		messageDetail.setMessage_id(messageVo.getId());
		messageDetail.setRead_state(ConstantUtil.MESSAGE_STATE_N);
		messageDetail.setSend_time(new Date());
		messageDetail.setSend_id(messageVo.getUser_id());  
		messageDetail.setUser_id(messageVo.getSendId());
		// messageVo.setContent(content);

		// 保存信息
		// message.setSend_time(new Date());
		// message.
//		messageDetailService.save(messageDetail);
		messageService.sndMessAge(messageVo, messageDetail);
		return true;
	}
	
	/**
	 * 创建审批认证通过的消息(M5)  用于R27 R28
	 * @param messageVo
	 * @return
	 */
	@Override
	@Transactional
	public   boolean sendPass(MessageVo messageVo) {   //passFlag, 
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
		messageDetail.setSend_id(messageVo.getUser_id());  
		messageDetail.setUser_id(messageVo.getSendId());  //接受者
		// messageVo.setContent(content);

		// 保存信息
		// message.setSend_time(new Date());
		// message.
//		messageDetailService.save(messageDetail);
		messageService.sndMessAge(messageVo, messageDetail);
		return true;
	}
	
	/**
	 * 创建邀请证据的信息 (M6)
	 * 
	 * @param messageVo
	 * @param userName
	 * @return
	 */
	@Override
	@Transactional
	public boolean askProofInfo(Long user_id, Long recieve_id, Long arbitrate_id) {
/*		//arbitrate_id;  仲裁id      arbitrate_type 仲裁发起类型 / user /company

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
			userNames = user.getUsername();

			company.setId(aTask.getCompany_id());
			company = companyService.selectForObject(company);
			companyName = company.getCompany_name();
			taskName = aTask.getPattern_number();
		}
		// 拼接内容
		StringBuffer msgContent = new StringBuffer();
		if (!"user".equals(messageVo.getArbitrate_type())) { // 若为公司则找出法人id
			msgContent.append("管理员：【" + userName + "】邀请您对【" + userNames
					+ "】参与的【" + taskName + "】任务的仲裁,请您提供相关仲裁质料已便仲裁团进行裁决。");
			sendId = userCompanyService.companygetUser(company.getId()).getUse_id(); // 法人id
		} else {
			msgContent.append("管理员：【" + userName + "】邀请您参与【" + companyName
					+ "】发起的【" + taskName + "】任务的仲裁,请您提供相关仲裁质料已便仲裁团进行裁决。");
			sendId = user.getId(); // 人员id
		}
		// 保存信息
//		messageVo.setContent(msgContent.toString());
		// 赋值基本信息
	*/	
		Message messageVo =new Message();
		messageVo.setMessage_type(ConstantUtil.MESSAGE_ARBITRATEMESSAGE);
		messageVo.setTitle("您需要提交一份证据以保证仲裁的公正");
		
		messageVo.setManipulate_status(ConstantUtil.MESSAGE_UNCOMMITTED); //待提交证据的操作标志
		
		messageVo.setContent("您需要提交一份证据以保证仲裁的公正");
		messageVo.setSend_time(new Date());
		messageVo.setFor_id(arbitrate_id);
//		messageService.save(messageVo);
		MessageDetail messageDetail = new MessageDetail();
		// 赋值接收方信息
		messageDetail.setIs_delete(ConstantUtil.MESSAGE_DELETE_N);
		messageDetail.setMessage_id(messageVo.getId());
		messageDetail.setRead_state(ConstantUtil.MESSAGE_STATE_N);
		messageDetail.setSend_time(new Date());
		messageDetail.setSend_id(user_id);    //发件人
		messageDetail.setUser_id(recieve_id);   //收件人
		// messageVo.setContent(content);

		// 保存信息
		// message.setSend_time(new Date());
		// message.
//		messageDetailService.save(messageDetail);
		messageService.sndMessAge(messageVo, messageDetail);
		
		return true;

	}
	/**
	 * 生成仲裁反馈消息 (M7)
	 * @param user_id
	 * @param recieve_id
	 * @param arbitrate_id
	 * @return
	 */
	@Override
	@Transactional
	public boolean feedbackMessage(Long user_id,Long recieve_id,Long arbitrate_id){    // 当前登录人的id 和  接受仲裁人的id
		MessageVo messageVo=new MessageVo();                                         //仲裁id
		messageVo.setMessage_type(ConstantUtil.MESSAGE_SYSTEM);
		messageVo.setTitle("您提交的证据有了新的反馈");
//		messageVo.setManipulate_status(ConstantUtil.MESSAGE_UNCOMMITTED); //待提交证据的操作标志
		messageVo.setContent("您提交的证据有了新的反馈");
		messageVo.setSend_time(new Date());
		messageVo.setFor_id(arbitrate_id);
//		messageService.save(messageVo);
		MessageDetail messageDetail = new MessageDetail();
		// 赋值接收方信息
		messageDetail.setIs_delete(ConstantUtil.MESSAGE_DELETE_N);
		messageDetail.setMessage_id(messageVo.getId());
		messageDetail.setRead_state(ConstantUtil.MESSAGE_STATE_N);
		messageDetail.setSend_time(new Date());
		messageDetail.setSend_id(user_id);    //发件人
		messageDetail.setUser_id(recieve_id);   //收件人
		messageService.sndMessAge(messageVo, messageDetail);
		return true;
		
	}

}
