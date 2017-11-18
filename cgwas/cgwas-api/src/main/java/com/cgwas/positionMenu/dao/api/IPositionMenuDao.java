package com.cgwas.positionMenu.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.positionMenu.entity.PositionMenu;
/**
 * 
 * @author yubangqiong
 *
 */
public interface IPositionMenuDao extends IDaoSupport {
	Page page(Page page, PositionMenu positionMenu);
}