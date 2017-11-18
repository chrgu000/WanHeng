package com.cgwas.privilegeInfo.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.gRole.entity.GRoleVo;
import com.cgwas.positionPrivilege.dao.api.IPositionPrivilegeDao;
import com.cgwas.positionPrivilege.entity.PositionPrivilege;
import com.cgwas.privilegeInfo.dao.api.IPrivilegeInfoDao;
import com.cgwas.privilegeInfo.entity.PrivilegeInfo;
import com.cgwas.privilegeInfo.entity.PrivilegeInfoVo;
import com.cgwas.privilegeInfo.service.api.IPrivilegeInfoService;
import com.cgwas.rolePrivilege.dao.api.IRolePrivilegeDao;
import com.cgwas.rolePrivilege.entity.RolePrivilege;
/**
 * 
 * @author yubangqiong
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class PrivilegeInfoService implements IPrivilegeInfoService {
	private IPrivilegeInfoDao privilegeInfoDao;
	@Autowired
	private IRolePrivilegeDao rolePrivilegeDao;
	@Autowired
	private IPositionPrivilegeDao positionPrivilegeDao;

	public Serializable save(PrivilegeInfo privilegeInfo){
		return privilegeInfoDao.save(privilegeInfo);
	}
	
	public void delete(PrivilegeInfo privilegeInfo){
		privilegeInfoDao.delete(privilegeInfo);
	}
	
	@Transactional
	public void deleteAll(Map<String,Object> map,PrivilegeInfo privilegeInfo){
		try {
			/**
			 * 删除权限相关数据信息
			 */
			rolePrivilegeDao.deleteAll(map, new RolePrivilege());
			positionPrivilegeDao.deleteAll(map, new PositionPrivilege());
			/**
			 * 删除权限数据信息
			 */
			privilegeInfoDao.deleteAll(map,privilegeInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteByExample(PrivilegeInfo privilegeInfo){
		privilegeInfoDao.deleteByExample(privilegeInfo);
	}

	public void update(PrivilegeInfo privilegeInfo){
		privilegeInfoDao.update(privilegeInfo);
	}
	
	public void updateIgnoreNull(PrivilegeInfo privilegeInfo){
		privilegeInfoDao.updateIgnoreNull(privilegeInfo);
	}
	
	public void updateByExample(PrivilegeInfo privilegeInfo){
		privilegeInfoDao.update("updateByExamplePrivilegeInfo", privilegeInfo);
	}

	public PrivilegeInfoVo load(PrivilegeInfo privilegeInfo){
		return (PrivilegeInfoVo)privilegeInfoDao.reload(privilegeInfo);
	}
	
	public PrivilegeInfoVo selectForObject(PrivilegeInfo privilegeInfo){
		return (PrivilegeInfoVo)privilegeInfoDao.selectForObject("selectPrivilegeInfo",privilegeInfo);
	}
	
	public List<PrivilegeInfoVo> selectForList(PrivilegeInfo privilegeInfo){
		return (List<PrivilegeInfoVo>)privilegeInfoDao.selectForList("selectPrivilegeInfo",privilegeInfo);
	}
	
	public List<PrivilegeInfoVo> selectPrivilegeByCheck(PrivilegeInfo privilegeInfo){
		return (List<PrivilegeInfoVo>)privilegeInfoDao.selectForList("selectPrivilegeByCheck",privilegeInfo);
	}
	
	public List<PrivilegeInfoVo> selectPrivilegeByGRoleId(PrivilegeInfo privilegeInfo){
		return (List<PrivilegeInfoVo>)privilegeInfoDao.selectForList("selectPrivilegeByGRoleId",privilegeInfo);
	}
	
	public List<PrivilegeInfoVo> selectPrivilegeByGRole(PrivilegeInfo privilegeInfo){
		return (List<PrivilegeInfoVo>)privilegeInfoDao.selectForList("selectPrivilegeByGRole",privilegeInfo);
	}
	
	public List<Map> selectPrivilegeByRoleId(PrivilegeInfo privilegeInfo){
		return (List<Map>)privilegeInfoDao.selectForList("selectPrivilegeByRoleId",privilegeInfo);
	}
	
	public List<Map> selectPrivilegeByUserId(PrivilegeInfo privilegeInfo){
		return (List<Map>)privilegeInfoDao.selectForList("selectPrivilegeByUserId",privilegeInfo);
	}
	
	public List<Map> selectPrivilegeByPositionId(PrivilegeInfo privilegeInfo){
		return (List<Map>)privilegeInfoDao.selectForList("selectPrivilegeByPositionId",privilegeInfo);
	}
	
	public Page page(Page page, PrivilegeInfo privilegeInfo) {
		return privilegeInfoDao.page(page, privilegeInfo);
	}
	@Autowired
	public void setIPrivilegeInfoDao(
			@Qualifier("privilegeInfoDao") IPrivilegeInfoDao  privilegeInfoDao) {
		this.privilegeInfoDao = privilegeInfoDao;
	}
	
	@Override
	public List<PrivilegeInfoVo> selectPrivilegeListByUser(GRoleVo gRleVo){
		return (List<PrivilegeInfoVo>)privilegeInfoDao.selectForList("selectPrivilegeListByUser",gRleVo);
	}
	
	@Override
	public List<Long> selectPIdsByUserId(Long user_id){
		/**
		 * 根据用户角色获取所有权限的id
		 */
		List<Long> list= (List<Long>)privilegeInfoDao.selectForList("selectPIdsByRoleId",user_id);
		/**
		 * 根据用户id获取所有权限的id
		 */
		List<Long> list2= (List<Long>)privilegeInfoDao.selectForList("selectPIdsByUserId",user_id);
		/**
		 * 去重权限id
		 */
		for (Long string : list2) {
			if(Collections.frequency(list, string)<1){
				list.add(string);
			}
		}
		return list;
	}
	
	@Override
	public Boolean isPrivilege(Long user_id,Long privilege_id){
		/**
		 * 根据用户角色获取所有权限的id
		 */
		List<Long> list= (List<Long>)privilegeInfoDao.selectForList("selectPIdsByRoleId",user_id);
		/**
		 * 根据用户id获取所有权限的id
		 */
		List<Long> list2= (List<Long>)privilegeInfoDao.selectForList("selectPIdsByUserId",user_id);
		list.addAll(list2);
		
		if(list.contains(privilege_id)){  
			return true;
        } 
		return false;
	}
	
	@Override
	public List<PrivilegeInfoVo> isPrivilegeByUrl(PrivilegeInfoVo privilegeInfo){
		List<PrivilegeInfoVo> list=(List<PrivilegeInfoVo>)privilegeInfoDao.selectForList("isPrivilegeByUrl",privilegeInfo);
		return list;
	}
}
