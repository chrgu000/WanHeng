package com.fengyun.dao;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.TradeSkill;
/**
*  Author yangjun
*/
public interface ITradeSkillDao extends IDaoSupport {
	Page page(Page page, TradeSkill tradeSkill);
}