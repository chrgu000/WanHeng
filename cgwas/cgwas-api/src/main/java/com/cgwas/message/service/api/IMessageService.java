package com.cgwas.message.service.api;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.message.entity.Message;
import com.cgwas.message.entity.MessageVo;
import com.cgwas.messageDetail.entity.MessageDetail;
public interface IMessageService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Message message);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Message message);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Message message);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Message message);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Message message);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Message message);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract MessageVo load(Message message);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract MessageVo selectForObject(Message message);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<MessageVo> selectForList(Message message);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Message message);
	
	/**
	 * 发送信息
	 * @param message
	 * @param messageDetail
	 * @return
	 */
	public abstract void sndMessAge(Message message,MessageDetail messageDetail);
	
	/**
	 * 获取仲裁消息的数量
	 */
	public abstract Long getArbitrateMessageCount(Message message);
}