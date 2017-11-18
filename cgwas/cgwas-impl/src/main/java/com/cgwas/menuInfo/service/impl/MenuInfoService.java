package com.cgwas.menuInfo.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.menuInfo.dao.api.IMenuInfoDao;
import com.cgwas.menuInfo.entity.MenuInfo;
import com.cgwas.menuInfo.entity.MenuInfoVo;
import com.cgwas.menuInfo.service.api.IMenuInfoService;
import com.cgwas.menuRole.dao.api.IMenuRoleDao;
import com.cgwas.menuRole.entity.MenuRole;
import com.cgwas.positionMenu.dao.api.IPositionMenuDao;
import com.cgwas.positionMenu.entity.PositionMenu;
import com.cgwas.privilegeInfo.dao.api.IPrivilegeInfoDao;
import com.cgwas.privilegeInfo.entity.PrivilegeInfo;
import com.cgwas.privilegeInfo.service.api.IPrivilegeInfoService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class MenuInfoService implements IMenuInfoService {
	private IMenuInfoDao menuInfoDao;
	@Autowired
	private IMenuRoleDao menuRoleDao;
	@Autowired
	private IPositionMenuDao positionMenuDao;
	@Autowired
	private IPrivilegeInfoDao privilegeInfoDao;
	
	@Autowired
	private IPrivilegeInfoService privilegeInfoService;

	public Serializable save(MenuInfo menuInfo){
		return menuInfoDao.save(menuInfo);
	}

	public void delete(MenuInfo menuInfo){
		menuInfoDao.delete(menuInfo);
	}
	
	public void deleteAll(Map<String, Object> map, MenuInfo menuInfo) {
		Map<String,Object> pram= new HashMap<String, Object>();
		try {
			/**
			 * 删除菜单相关数据信息
			 */
			menuRoleDao.deleteAll(map, new MenuRole());
			positionMenuDao.deleteAll(map, new PositionMenu());
			/**
			 * 根据一个或多个menu_id获取权限主键集合
			 */
			//List<String> objectIds= privilegeInfoService.selectPrivilegeInfoIds(map);
			//put条件参数
			//pram.put("ids", objectIds);
			pram.put("status", 2);
			privilegeInfoService.deleteAll(pram, new PrivilegeInfo());
			/**
			 * 删除菜单数据信息
			 */
			menuInfoDao.deleteAll(map,menuInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deleteByExample(MenuInfo menuInfo){
		menuInfoDao.deleteByExample(menuInfo);
	}

	public void update(MenuInfo menuInfo){
		menuInfoDao.update(menuInfo);
	}
	
	public void updateIgnoreNull(MenuInfo menuInfo){
		menuInfoDao.updateIgnoreNull(menuInfo);
	}
		
	public void updateByExample(MenuInfo menuInfo){
		menuInfoDao.update("updateByExampleMenuInfo", menuInfo);
	}

	public MenuInfoVo load(MenuInfo menuInfo){
		return (MenuInfoVo)menuInfoDao.reload(menuInfo);
	}
	
	public MenuInfoVo selectForObject(MenuInfo menuInfo){
		return (MenuInfoVo)menuInfoDao.selectForObject("selectMenuInfo",menuInfo);
	}
	
	@SuppressWarnings("unchecked")
	public List<MenuInfoVo> selectForList(MenuInfo menuInfo){
		return (List<MenuInfoVo>)menuInfoDao.selectForList("selectMenuInfo",menuInfo);
	}
	
	public Page page(Page page, MenuInfo menuInfo) {
		return menuInfoDao.page(page, menuInfo);
	}

	@Autowired
	public void setIMenuInfoDao(
			@Qualifier("menuInfoDao") IMenuInfoDao  menuInfoDao) {
		this.menuInfoDao = menuInfoDao;
	}

}
