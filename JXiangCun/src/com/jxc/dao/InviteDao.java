package com.jxc.dao;

import java.util.List;

import com.jxc.entity.Invite;
import com.jxc.entity.Inviter;

public interface InviteDao {
List<Invite>  findInvitesByInvite(Invite invite);

boolean addInviter(Inviter inviter);

boolean addInvite(Invite invite);

Inviter findInviterByInvite(Invite invite);
}
