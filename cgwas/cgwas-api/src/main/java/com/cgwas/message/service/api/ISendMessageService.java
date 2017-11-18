package com.cgwas.message.service.api;

import javax.servlet.http.HttpServletRequest;

import com.cgwas.common.json.entity.Result;
import com.cgwas.message.entity.Message;
import com.cgwas.message.entity.MessageVo;
import com.cgwas.messageDetail.entity.MessageDetail;

public interface ISendMessageService {
	



	/**
	 *  生成任务接受成功的消息(M1)
	 *  
	 */
	public abstract boolean createReceiveTaskMessage(Long task_id,String task_type,Long loginUser_id);

	/**
	 * 生成评价公司成功的消息 (M2)
	 * @param send_id
	 * @param recieve_id
	 * @param request
	 * @return
	 */
//	public abstract boolean evaluateCompany(Long send_id, Long recieve_id);
	
	/**
	 * 生成欢迎消息 （M3）
	 * @param user_id
	 * @return
	 */
	
	public 	abstract boolean welcomeMessage(Long user_id);
	
	/**
	 * 生成评价成功的消息
	 * @param send_id
	 * @param recieve_id
	 * @param request
	 * @return
	 */
	public 	abstract boolean evaluateMessage(Long send_id, Long recieve_id);
	
	/*
	 * 创建审核不通过的消息
	 */
	public abstract boolean sendNoPass(MessageVo messageVo);
	
	/**
	 * 创建审核通过的消息
	 * @param messageVo
	 * @return
	 */
	public abstract boolean sendPass(MessageVo messageVo);
	
	/**
	 * 创建邀请证据消息
	 * 
	 * @param messageVo
	 * @param userName
	 * @return
	 */
	public abstract boolean askProofInfo(Long user_id, Long recieve_id, Long arbitrate_id);
	/**
	 * 生成仲裁反馈消息 (M7)
	 * @param user_id
	 * @param recieve_id
	 * @param arbitrate_id
	 * @return
	 */
	boolean feedbackMessage(Long user_id, Long recieve_id, Long arbitrate_id);

	
	
}
