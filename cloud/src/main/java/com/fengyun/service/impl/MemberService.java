package com.fengyun.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.dao.IMemberDao;
import com.fengyun.entity.Member;
import com.fengyun.entity.MemberVo;
import com.fengyun.entity.Message;
import com.fengyun.service.IMemberService;

/**
 * Author yangjun
 */
@Service
public class MemberService implements IMemberService {
	private IMemberDao memberDao;

	public Serializable save(Member member) {
		return memberDao.save(member);
	}

	public void delete(Member member) {
		memberDao.delete(member);
	}

	public void deleteByExample(Member member) {
		memberDao.deleteByExample(member);
	}

	public void update(Member member) {
		memberDao.update(member);
	}

	public void updateIgnoreNull(Member member) {
		memberDao.updateIgnoreNull(member);
	}

	public void updateByExample(Member member) {
		memberDao.update("updateByExampleMember", member);
	}

	public MemberVo load(Member member) {
		return (MemberVo) memberDao.reload(member);
	}

	public MemberVo selectForObject(Member member) {
		return (MemberVo) memberDao.selectForObject("selectMember", member);
	}

	@SuppressWarnings("unchecked")
	public List<MemberVo> selectForList(Member member) {
		return (List<MemberVo>) memberDao.selectForList("selectMember", member);
	}

	public Page page(Page page, Member member) {
		return memberDao.page(page, member);
	}

	@Autowired
	public void setIMemberDao(@Qualifier("memberDao") IMemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getChatMessages(Map<String, Object> query) {
		return (List<Message>) memberDao
				.selectForList("getChatMessages", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getMyFriends(Map<String, Long> query) {
		return (List<Member>) memberDao.selectForList("getMyFriends", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> searchFriend(Map<String, Object> query) {
		return (List<Member>) memberDao.selectForList("searchFriend", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> searchMyFriend(Map<String, Object> query) {
		return (List<Member>) memberDao.selectForList("searchMyFriend", query);
	}

	@Override
	public Member checkIsMyFriend(Map<String, Object> query) {
		return (Member) memberDao.selectForObject("checkIsMyFriend", query);
	}

	@Override
	public String getDefaultMessage(Map<String, Object> query) {
		return (String) memberDao.selectForObject("getDefaultMessage", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> isMyFrendOrNot(Map<String, Object> query) {
		return (List<Member>) memberDao.selectForList("isMyFrendOrNot", query);
	}

	@Override
	public Integer isExistOrNot(Member member) {
		return (Integer) memberDao.selectForObject("isExistOrNot", member);
	}

	@Override
	public void updateApplyMsg(Member member) {
		memberDao.update("updateApplyMsg", member);

	}

	@Override
	public void updateApplyState(Member member) {
		memberDao.update("updateApplyState", member);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getMyNewFriend(Map<String, Object> query) {
		return (List<Member>) memberDao.selectForList("getMyNewFriend", query);
	}

	@Override
	public void agree(Map<String, Object> query) {
		memberDao.update("agree", query);
	}

	@Override
	public void changeRealName(Map<String, Object> query) {
		memberDao.update("changeRealName", query);

	}

	@Override
	public Long getUnReadNum(Map<String, Object> select) {
		return (Long) memberDao.selectForObject("getUnReadNum", select);
	}

	@Override
	public void updateReadState(Map<String, Object> query) {
		memberDao.update("updateReadState", query);
	}

	@Override
	public void updateOnlieState(Map<String, Object> query) {
		memberDao.update("updateOnlieState", query);
	}

	@Override
	public String getOnlineState(Map<String, Object> select) {
		return (String) memberDao.selectForObject("getOnlineState", select);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getOnlyOnlineState(Map<String, Object> select) {
		return (List<String>) memberDao.selectForList("getOnlyOnlineState",
				select);
	}

	@Override
	public void changeProgressState(Map<String, Object> query) {
		memberDao.update("changeProgressState", query);

	}

	@Override
	public void deleteMyFriend(Map<String, Object> query) {
		memberDao.delete("deleteMyFriend", query);
	}

	@Override
	public void createNewGroupChat(Map<String, Object> group) {
		 memberDao.save("createNewGroupChat", group);
	}

	@Override
	public void addGroupMember(Map<String, Object> member) {
		memberDao.save("addGroupMember", member);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getMyChatGroups(Map<String, Object> query) {
		return (List<Map<String, Object>>) memberDao.selectForList(
				"getMyChatGroups", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getGroupFriendsById(Map<String, Long> query) {
		return (List<Member>) memberDao.selectForList("getGroupFriendsById",
				query);
	}

	@Override
	public Page page1(Page page, Member member) {
		return memberDao.page1(page, member);
	}

	@Override
	public Member getMemberById(Map<String, Object> member) {
		return (Member) memberDao.selectForObject("getMemberById", member);
	}

	@Override
	public void addGroupMemberInfo(Map<String, Object> member) {
           memberDao.save("addGroupMemberInfo", member);
	}

	@Override
	public Integer getGroupMemberNum(Map<String, Object> query) {
		return (Integer) memberDao.selectForObject("getGroupMemberNum", query);
	}

	@Override
	public void updateGroupMemberNum(Map<String, Object> query) {
		memberDao.update("updateGroupMemberNum", query);

	}

	@Override
	public Long getMemberIdByQuery(Map<String, Object> query) {
		return (Long) memberDao.selectForObject("getMemberIdByQuery", query);
	}

	@Override
	public void deleteGroupMember(Map<String, Object> query) {
		memberDao.delete("deleteGroupMember", query);
	}

	@Override
	public void deleteMemberById(Map<String, Object> query) {
		memberDao.delete("deleteMemberById", query);
	}

	@Override
	public void deleteGroupById(Map<String, Object> query) {
		memberDao.delete("deleteGroupById", query);
		
	}

	@Override
	public Long getMasterGroupId(Map<String, Long> query) {
		return (Long) memberDao.selectForObject("getMasterGroupId", query);
	}

	@Override
	public void updateGroupReadState(Map<String, Object> query) {
		memberDao.update("updateGroupReadState", query);
	}

	@Override
	public Long getGroupUnReadNum(Map<String, Object> select) {
		return (Long) memberDao.selectForObject("getGroupUnReadNum", select);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGroupInfoById(Map<String, Long> query) {
		return (Map<String, Object>)memberDao.selectForObject("getGroupInfoById", query);
	}

	@Override
	public void changeGroupName(Map<String, Object> query) {
		memberDao.update("changeGroupName", query);
	}

	@Override
	public void changeGroupMemberRealName(Map<String, Object> query) {
		memberDao.update("changeGroupMemberRealName", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> searchGroup(Map<String, Object> query) {
		return (List<Map<String, Object>>) memberDao.selectForList("searchGroup", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> searchGroupMembers(Map<String, Object> query) {
		return (List<Member>) memberDao.selectForList("searchGroupMembers", query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> getGroupMembers(Map<String, Object> query) {
		return (List<Member>) memberDao.selectForList("getGroupMembers", query);
	}

	@Override
	public void addMemberMessage(Map<String, Object> mess) {
		memberDao.save("addMemberMessage", mess);
	}
}
