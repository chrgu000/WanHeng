package com.jxc.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.InviteDao;
import com.jxc.entity.Invite;
import com.jxc.entity.Inviter;
import com.jxc.service.InviteService;
@Service("inviteService")
@Transactional
public class InviteServiceImpl implements InviteService {
@Resource 
private InviteDao dao;
	public List<Invite> findInvitesByInvite(Invite invite) {
		return dao.findInvitesByInvite(invite);
	}

	public boolean addInviter(Inviter inviter) {
		return dao.addInviter(inviter);
	}

	public boolean addInvite(Invite invite) {
		return dao.addInvite(invite);
	}

	public Inviter findInviterByInvite(Invite invite) {
		return dao.findInviterByInvite(invite);
	}

}
