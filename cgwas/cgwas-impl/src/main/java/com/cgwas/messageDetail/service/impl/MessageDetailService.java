package com.cgwas.messageDetail.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.messageDetail.dao.api.IMessageDetailDao;
import com.cgwas.messageDetail.entity.MessageDetail;
import com.cgwas.messageDetail.entity.MessageDetailVo;
import com.cgwas.messageDetail.entity.MessageInfo;
import com.cgwas.messageDetail.service.api.IMessageDetailService;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MessageDetailService implements IMessageDetailService {
	private IMessageDetailDao messageDetailDao;

	public Serializable save(MessageDetail messageDetail) {
		return messageDetailDao.save(messageDetail);
	}

	public void delete(MessageDetail messageDetail) {
		messageDetailDao.delete(messageDetail);
	}

	public void deleteByExample(MessageDetail messageDetail) {
		messageDetailDao.deleteByExample(messageDetail);
	}

	public void update(MessageDetail messageDetail) {
		messageDetailDao.update(messageDetail);
	}

	public void updateIgnoreNull(MessageDetail messageDetail) {
		messageDetailDao.updateIgnoreNull(messageDetail);
	}

	public void updateByExample(MessageDetail messageDetail) {
		messageDetailDao.update("updateByExampleMessageDetail", messageDetail);
	}

	public MessageDetailVo load(MessageDetail messageDetail) {
		return (MessageDetailVo) messageDetailDao.reload(messageDetail);
	}

	public MessageDetailVo selectForObject(MessageDetail messageDetail) {
		return (MessageDetailVo) messageDetailDao.selectForObject(
				"selectMessageDetail", messageDetail);
	}

	public List<MessageDetailVo> selectForList(MessageDetail messageDetail) {
		return (List<MessageDetailVo>) messageDetailDao.selectForList(
				"selectMessageDetail", messageDetail);
	}

	public Page page(Page page, MessageDetail messageDetail) {
		return messageDetailDao.page(page, messageDetail);
	}

	@Autowired
	public void setIMessageDetailDao(
			@Qualifier("messageDetailDao") IMessageDetailDao messageDetailDao) {
		this.messageDetailDao = messageDetailDao;
	}

	@Override
	public void batchDeleteMessage(List<Long> messageIdList,Long userId) {
		// 参数列表
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", messageIdList);
		map.put("userId", userId);
		messageDetailDao.update("batchDeleteMessage", map);
	}

	@Override
	public List<MessageInfo> selectMessageInfoList(MessageInfo messageInfo,
			Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("messageInfo", messageInfo);
		return (List<MessageInfo>) messageDetailDao.selectForList(
				"selectMessageInfoList", map);
	}

	@Override
	public Long selectMessageInfoListCount(MessageInfo messageInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("messageInfo", messageInfo);
		return (Long) messageDetailDao.selectForObject("selectMessageInfoListCount",
				map);
	}

	@Override
	public MessageInfo getMessageInfoById(Long id) {
		MessageDetail messageDetail = new MessageDetail();
		messageDetail.setMessage_id(id);
		messageDetail=this.selectForObject(messageDetail);
		messageDetail.setRead_state("Y");
		messageDetail.setRead_time(new Date());
		this.updateIgnoreNull(messageDetail);
		return (MessageInfo) messageDetailDao.selectForObject(
				"getMessageInfoById", id);
	}

	@Override
	public List<MessageInfo> getMessageInfoForRe(Long id) {
		return (List<MessageInfo>) messageDetailDao.selectForList(
				"getMessageInfoForRe", id);
	}

	@Override
	public List<Map> selectUnReadMessageListCount(Long id) {
		return (List<Map>) messageDetailDao.selectForList(
				"selectUnReadMessageListCount", id);
	}

}
