package com.cgwas.messageDetail.service.api;



import java.util.List;
import java.util.Map;
import java.io.Serializable;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.messageDetail.entity.MessageDetail;
import com.cgwas.messageDetail.entity.MessageDetailVo;
import com.cgwas.messageDetail.entity.MessageInfo;


public interface IMessageDetailService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(MessageDetail messageDetail);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(MessageDetail messageDetail);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(MessageDetail messageDetail);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(MessageDetail messageDetail);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(MessageDetail messageDetail);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(MessageDetail messageDetail);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract MessageDetailVo load(MessageDetail messageDetail);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract MessageDetailVo selectForObject(MessageDetail messageDetail);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<MessageDetailVo> selectForList(MessageDetail messageDetail);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, MessageDetail messageDetail);
	/**
	 * 批量删除信息
	 * @param messageIdList
	 */
	public abstract void batchDeleteMessage(List<Long> messageIdList,Long userId);
	/**
	 * 搜索信息列表
	 * @param messageInfo
	 * @param page
	 * @return
	 */
	public abstract List<MessageInfo> selectMessageInfoList(MessageInfo messageInfo,Page page);
	/**
	 * 搜索信息列表（数量）
	 * @param messageInfo
	 * @param page
	 * @return
	 */
	public abstract Long selectMessageInfoListCount(MessageInfo messageInfo);
	/**
	 * 根据ID得到信息详情
	 * @param id
	 * @return
	 */
	public abstract MessageInfo getMessageInfoById(Long id);
	/**
	 *  得到信息及回复信息 
	 * @param id
	 * @return
	 */
	public abstract List<MessageInfo> getMessageInfoForRe(Long id);
	
	/**
	 * 统计各类消息未读消息的数量
	 * @param id
	 * @return
	 * 
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public abstract List<Map> selectUnReadMessageListCount(Long id);
}