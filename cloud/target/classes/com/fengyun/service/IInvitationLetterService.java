package com.fengyun.service;

import java.io.Serializable;
import java.util.List;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.InvitationLetter;
import com.fengyun.entity.InvitationLetterVo;
import com.fengyun.entity.LetterImg;
/**
*  Author yangjun
*/
public interface IInvitationLetterService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(InvitationLetter invitationLetter);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(InvitationLetter invitationLetter);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(InvitationLetter invitationLetter);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(InvitationLetter invitationLetter);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(InvitationLetter invitationLetter);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(InvitationLetter invitationLetter);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract InvitationLetterVo load(InvitationLetter invitationLetter);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract InvitationLetterVo selectForObject(InvitationLetter invitationLetter);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<InvitationLetterVo> selectForList(InvitationLetter invitationLetter);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, InvitationLetter invitationLetter);

	public abstract void addLetterImg(LetterImg img);

	public abstract InvitationLetter getInvitationLetterById(Long id);

	public abstract InvitationLetter getLastLetterOfIsNotRead(Long userId);

	public abstract Long getUnReplyLetterNumByUserId(Long user_id);

	public abstract Long getLetterNum(Long userId);

}