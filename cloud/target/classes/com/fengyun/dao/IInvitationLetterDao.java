package com.fengyun.dao;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.InvitationLetter;
/**
*  Author yangjun
*/
public interface IInvitationLetterDao extends IDaoSupport {
	Page page(Page page, InvitationLetter invitationLetter);
}