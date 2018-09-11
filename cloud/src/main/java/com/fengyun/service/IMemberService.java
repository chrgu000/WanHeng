package com.fengyun.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.Member;
import com.fengyun.entity.MemberVo;
import com.fengyun.entity.Message;

/**
*  Author yangjun
*/
public interface IMemberService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Member member);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Member member);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Member member);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Member member);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Member member);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Member member);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract MemberVo load(Member member);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract MemberVo selectForObject(Member member);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<MemberVo> selectForList(Member member);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Member member);

	public abstract List<Message> getChatMessages(Map<String, Object> query);

	public abstract List<Member> getMyFriends(Map<String, Long> query);

	public abstract List<Member> searchFriend(Map<String, Object> query);

	public abstract List<Member> searchMyFriend(Map<String, Object> query);

	public abstract Member checkIsMyFriend(Map<String, Object> query);

	public abstract String getDefaultMessage(Map<String, Object> query);

	 abstract List<Member> isMyFrendOrNot(Map<String, Object> query);

	public abstract Integer isExistOrNot(Member member);

	public abstract void updateApplyMsg(Member member);

	public abstract void updateApplyState(Member member);

	public abstract List<Member> getMyNewFriend(Map<String, Object> query);

	public abstract void agree(Map<String, Object> query);

	public abstract void changeRealName(Map<String, Object> query);

	public abstract Long getUnReadNum(Map<String, Object> select);

	public abstract void updateReadState(Map<String, Object> query);

	public abstract void updateOnlieState(Map<String, Object> query);

	public abstract String getOnlineState(Map<String, Object> select);

	public abstract List<String> getOnlyOnlineState(Map<String, Object> select);

	public abstract void changeProgressState(Map<String, Object> query);

	public abstract void deleteMyFriend(Map<String, Object> query);

	public abstract void createNewGroupChat(Map<String, Object> group);

	public abstract void addGroupMember(Map<String, Object> member);

 

	public abstract List<Map<String, Object>> getMyChatGroups(
			Map<String, Object> query);

	public abstract List<Member> getGroupFriendsById(Map<String, Long> query);

	public abstract Page page1(Page page, Member member);

	public abstract Member getMemberById(Map<String, Object> member);

	public abstract void addGroupMemberInfo(Map<String, Object> member);

	public abstract Integer getGroupMemberNum(Map<String, Object> query);

	public abstract void updateGroupMemberNum(Map<String, Object> query);

	public abstract Long getMemberIdByQuery(Map<String, Object> query);

	public abstract void deleteGroupMember(Map<String, Object> query);

	public abstract void deleteMemberById(Map<String, Object> query);

	public abstract void deleteGroupById(Map<String, Object> query);

	public abstract Long getMasterGroupId(Map<String, Long> query);

	public abstract void updateGroupReadState(Map<String, Object> query);

	public abstract Long getGroupUnReadNum(Map<String, Object> select);

	public abstract Map<String, Object> getGroupInfoById(Map<String, Long> query);

	public abstract void changeGroupName(Map<String, Object> query);

	public abstract void changeGroupMemberRealName(Map<String, Object> query);

	public abstract List<Map<String, Object>> searchGroup(
			Map<String, Object> query);

	public abstract List<Member> searchGroupMembers(Map<String, Object> query);

	public abstract List<Member> getGroupMembers(Map<String, Object> query);

	public abstract void addMemberMessage(Map<String, Object> mess);

}