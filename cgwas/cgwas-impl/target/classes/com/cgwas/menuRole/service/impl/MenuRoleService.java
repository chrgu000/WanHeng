package com.cgwas.menuRole.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.menuRole.dao.api.IMenuRoleDao;
import com.cgwas.menuRole.entity.MenuRole;
import com.cgwas.menuRole.entity.MenuRoleVo;
import com.cgwas.menuRole.service.api.IMenuRoleService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class MenuRoleService implements IMenuRoleService {
	private IMenuRoleDao menuRoleDao;

	public Serializable save(MenuRole menuRole){
		return menuRoleDao.save(menuRole);
	}

	public void delete(MenuRole menuRole){
		menuRoleDao.delete(menuRole);
	}
	
	public void deleteAll(Map<String, Object> map, MenuRole menuRole) {
		menuRoleDao.deleteAll(map,menuRole);
		
	}
	
	public void deleteByExample(MenuRole menuRole){
		menuRoleDao.deleteByExample(menuRole);
	}

	public void update(MenuRole menuRole){
		menuRoleDao.update(menuRole);
	}
	
	public void updateIgnoreNull(MenuRole menuRole){
		menuRoleDao.updateIgnoreNull(menuRole);
	}
		
	public void updateByExample(MenuRole menuRole){
		menuRoleDao.update("updateByExampleMenuRole", menuRole);
	}

	public MenuRoleVo load(MenuRole menuRole){
		return (MenuRoleVo)menuRoleDao.reload(menuRole);
	}
	
	public MenuRoleVo selectForObject(MenuRole menuRole){
		return (MenuRoleVo)menuRoleDao.selectForObject("selectMenuRole",menuRole);
	}
	
	public List<MenuRoleVo> selectForList(MenuRole menuRole){
		return (List<MenuRoleVo>)menuRoleDao.selectForList("selectMenuRole",menuRole);
	}
	
	public Page page(Page page, MenuRole menuRole) {
		return menuRoleDao.page(page, menuRole);
	}

	@Autowired
	public void setIMenuRoleDao(
			@Qualifier("menuRoleDao") IMenuRoleDao  menuRoleDao) {
		this.menuRoleDao = menuRoleDao;
	}

}
