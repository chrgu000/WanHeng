package com.cgwas.message.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgwas.animationlighttask.entity.AnimationLightTaskVo;
import com.cgwas.animationlighttask.service.api.IAnimationLightTaskService;
import com.cgwas.animationlighttask.service.impl.AnimationLightTaskService;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.company.entity.Company;
import com.cgwas.company.service.api.ICompanyService;
import com.cgwas.message.dao.api.IMessageDao;
import com.cgwas.message.entity.Message;
import com.cgwas.message.entity.MessageVo;
import com.cgwas.message.service.api.IMessageService;
import com.cgwas.messageDetail.dao.api.IMessageDetailDao;
import com.cgwas.messageDetail.entity.MessageDetail;
import com.cgwas.messageDetail.service.api.IMessageDetailService;
import com.cgwas.modeltask.entity.ModelTaskVo;
import com.cgwas.modeltask.service.api.IModelTaskService;
import com.cgwas.modeltask.service.impl.ModelTaskService;
import com.cgwas.project.entity.Project;
import com.cgwas.project.service.api.IProjectService;
import com.cgwas.project.service.impl.ProjectService;

@Service
public class MessageService implements IMessageService {
	@Autowired
	private IMessageDao messageDao;
	@Autowired
	private IMessageDetailDao messageDetailDao;
//	@Autowired
//	private ICompanyService companyService;
	@Autowired
	private IModelTaskService modelTaskService;
	@Autowired
	private IMessageDetailService messageDetailService;
	@Autowired
	private IAnimationLightTaskService animationLightTaskService;
	@Autowired
	private IProjectService projectService;

	public Serializable save(Message message) {
		return messageDao.save(message);
	}

	public void delete(Message message) {
		messageDao.delete(message);
	}

	public void deleteByExample(Message message) {
		messageDao.deleteByExample(message);
	}

	public void update(Message message) {
		messageDao.update(message);
	}

	public void updateIgnoreNull(Message message) {
		messageDao.updateIgnoreNull(message);
	}

	public void updateByExample(Message message) {
		messageDao.update("updateByExampleMessage", message);
	}

	public MessageVo load(Message message) {
		return (MessageVo) messageDao.reload(message);
	}

	public MessageVo selectForObject(Message message) {
		return (MessageVo) messageDao.selectForObject("selectMessage", message);
	}

	public List<MessageVo> selectForList(Message message) {
		return (List<MessageVo>) messageDao.selectForList("selectMessage",
				message);
	}

	public Page page(Page page, Message message) {
		return messageDao.page(page, message);
	}

	@Transactional
	@Override
	public void sndMessAge(Message message, MessageDetail messageDetail) {
		message.setSend_time(new Date());
		messageDao.save(message);
		messageDetail.setMessage_id(message.getId());
		messageDetail.setIs_delete('N');
		messageDetail.setSend_time(new Date());
		messageDetailDao.save(messageDetail);
	}
	
	/**
	 * 获取仲裁消息的数量
	 */
	@Override
	public Long getArbitrateMessageCount(Message message)	{
		return (Long) messageDao.selectForObject("getArbitrateMessageCount", message);
	}
	 
}


