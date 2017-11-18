package com.cgwas.gRole.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.gRole.dao.api.IGRoleDao;
import com.cgwas.gRole.entity.GRole;
import com.cgwas.gRole.entity.GRoleVo;
import com.cgwas.gRole.service.api.IGRoleService;
import com.cgwas.gRolePreinstall.entity.GRolePreinstall;
import com.cgwas.gRolePreinstall.entity.GRolePreinstallVo;
import com.cgwas.gRolePreinstall.service.api.IGRolePreinstallService;
import com.cgwas.logInfo.entity.LogInfo;
import com.cgwas.logInfo.service.api.ILogInfoService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
@SuppressWarnings({"unchecked","rawtypes"})
public class GRoleService implements IGRoleService {
	@Autowired
	private IGRoleDao gRoleDao;
	@Autowired
	private IGRolePreinstallService gRolePreinstallService;
	@Autowired
	private ILogInfoService logInfoService;

	public Serializable save(GRoleVo gRole){
		/**
		 * 执行操作日志
		 */
		StringBuffer content= new StringBuffer();
		LogInfo logInfo= new LogInfo();
		logInfo.setLog_type(ConstantUtil.LOG_SAVE);
		logInfo.setTable_name("p_g_role");
		logInfo.setUser_id(gRole.getUser_id());
		logInfo.setUser_name(gRole.getUser_name());
		content.append(gRole.getUser_name());
		content.append("添加角色【");
		content.append(gRole.getRole_name());
		content.append("】");
		logInfo.setContent(content.toString());
		logInfoService.save(logInfo);
		return gRoleDao.save(gRole);
	}
	
	@Transactional
	public void saveSysRole(GRole gRole){
		GRole obj=new GRole();
		//参数注入
		obj.setIs_parent_poject(gRole.getIs_parent_poject());
		obj.setProject_id(gRole.getProject_id());
		List<GRolePreinstallVo> list=gRolePreinstallService.selectForList(new GRolePreinstall());
		for (GRolePreinstallVo gRolePreinstallVo : list) {
			gRole= new GRole();
			//参数注入
			gRole.setIs_parent_poject(obj.getIs_parent_poject());
			gRole.setProject_id(obj.getProject_id());
			gRole.setRole_name(gRolePreinstallVo.getRole_name());
			gRole.setRole_p_id(gRolePreinstallVo.getId());
			//初始值设置
			gRole.setFor_id(1l);
			gRole.setNum(0);
			//gRolePreinstallVo
			gRoleDao.save(gRole);
		}
	}

	public void delete(GRole gRole){
		gRoleDao.delete(gRole);
	}
	
	@Transactional
	public void deleteAndUpdateForId(GRoleVo gRole){
		GRole obj= new GRole();
		obj.setFor_id(gRole.getId());
		StringBuffer content= new StringBuffer();
		List<GRoleVo> list =this.selectForList(obj);
		if(list.size()>0){
			for (GRoleVo gRoleVo : list) {
				obj= new GRole();
				obj.setId(gRoleVo.getId());
				obj.setFor_id(gRole.getFor_id());
				this.updateFor_id(obj);
			}
		}
		/**
		 * 执行操作日志
		 */
		LogInfo logInfo= new LogInfo();
		logInfo.setLog_type(ConstantUtil.LOG_DELETE);
		logInfo.setTable_name("p_g_role");
		logInfo.setUser_id(gRole.getUser_id());
		logInfo.setUser_name(gRole.getUser_name());
		content.append(gRole.getUser_name());
		content.append("删除角色【");
		content.append(gRole.getRole_name());
		content.append("】");
		logInfo.setContent(content.toString());
		logInfoService.save(logInfo);
		gRoleDao.delete(gRole);
	}
	
	public void deleteByExample(GRole gRole){
		gRoleDao.deleteByExample(gRole);
	}

	public void update(GRole gRole){
		gRoleDao.update(gRole);
	}
	
	public void updateIgnoreNull(GRole gRole){
		gRoleDao.updateIgnoreNull(gRole);
	}
	
	public void updateFor_id(GRole gRole){
		gRoleDao.update("updateFor_id", gRole);
	}
		
	public void updateByExample(GRole gRole){
		gRoleDao.update("updateByExampleGRole", gRole);
	}

	public GRoleVo load(GRole gRole){
		return (GRoleVo)gRoleDao.reload(gRole);
	}
	
	public GRoleVo selectForObject(GRole gRole){
		return (GRoleVo)gRoleDao.selectForObject("selectGRole",gRole);
	}
	
	public List<GRoleVo> getGRoleListByUserId(GRole gRole){
		return (List<GRoleVo>)gRoleDao.selectForList("getGRoleListByUserId",gRole);
	}
	
	public List<GRoleVo> selectForList(GRole gRole){
		return (List<GRoleVo>)gRoleDao.selectForList("selectGRole",gRole);
	}
	
	
	public List<HashMap> selectForListMap(GRole gRole){
		return (List<HashMap>)gRoleDao.selectForList("selectGRoleMap",gRole);
	}
	public List<GRoleVo> listRelevance(GRole gRole){
		return (List<GRoleVo>)gRoleDao.selectForList("listRelevance",gRole);
	}
	
	public Page page(Page page, GRole gRole) {
		return gRoleDao.page(page, gRole);
	}

}
