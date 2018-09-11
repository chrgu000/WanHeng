package com.fengyun.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.dao.IInvitationLetterDao;
import com.fengyun.entity.InvitationLetter;
import com.fengyun.entity.InvitationLetterVo;
import com.fengyun.entity.LetterImg;
import com.fengyun.service.IInvitationLetterService;
/**
*  Author yangjun
*/
@Service
public class InvitationLetterService implements IInvitationLetterService {
	private IInvitationLetterDao invitationLetterDao;

	public Serializable save(InvitationLetter invitationLetter){
		return invitationLetterDao.save(invitationLetter);
	}

	public void delete(InvitationLetter invitationLetter){
		invitationLetterDao.delete(invitationLetter);
	}
	
	public void deleteByExample(InvitationLetter invitationLetter){
		invitationLetterDao.deleteByExample(invitationLetter);
	}

	public void update(InvitationLetter invitationLetter){
		invitationLetterDao.update(invitationLetter);
	}
	
	public void updateIgnoreNull(InvitationLetter invitationLetter){
		invitationLetterDao.updateIgnoreNull(invitationLetter);
	}
		
	public void updateByExample(InvitationLetter invitationLetter){
		invitationLetterDao.update("updateByExampleInvitationLetter", invitationLetter);
	}

	public InvitationLetterVo load(InvitationLetter invitationLetter){
		return (InvitationLetterVo)invitationLetterDao.reload(invitationLetter);
	}
	
	public InvitationLetterVo selectForObject(InvitationLetter invitationLetter){
		return (InvitationLetterVo)invitationLetterDao.selectForObject("selectInvitationLetter",invitationLetter);
	}
	
	public List<InvitationLetterVo> selectForList(InvitationLetter invitationLetter){
		return (List<InvitationLetterVo>)invitationLetterDao.selectForList("selectInvitationLetter",invitationLetter);
	}
	
	public Page page(Page page, InvitationLetter invitationLetter) {
		return invitationLetterDao.page(page, invitationLetter);
	}

	@Autowired
	public void setIInvitationLetterDao(
			@Qualifier("invitationLetterDao") IInvitationLetterDao  invitationLetterDao) {
		this.invitationLetterDao = invitationLetterDao;
	}

	@Override
	public void addLetterImg(LetterImg img) {
		invitationLetterDao.save("addLetterImg", img);
		
	}

	@Override
	public InvitationLetter getInvitationLetterById(Long id) {
		return (InvitationLetterVo)invitationLetterDao.selectForObject("getInvitationLetterById",id);
	}

	@Override
	public InvitationLetter getLastLetterOfIsNotRead(Long userId) {
		return (InvitationLetterVo)invitationLetterDao.selectForObject("getLastLetterOfIsNotRead",userId);
	}

	@Override
	public Long getUnReplyLetterNumByUserId(Long user_id) {
		return (Long)invitationLetterDao.selectForObject("getUnReplyLetterNumByUserId",user_id);
	}

	@Override
	public Long getLetterNum(Long userId) {
		return (Long)invitationLetterDao.selectForObject("getLetterNum",userId);
		}

}
