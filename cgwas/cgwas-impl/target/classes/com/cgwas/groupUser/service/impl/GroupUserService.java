package com.cgwas.groupUser.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.companyFiles.dao.api.ICompanyFilesDao;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.companyFilesUser.entity.CompanyFilesUser;
import com.cgwas.companyFilesUser.service.api.ICompanyFilesUserService;
import com.cgwas.gRole.entity.GRoleVo;
import com.cgwas.gRole.service.api.IGRoleService;
import com.cgwas.groupUser.dao.api.IGroupUserDao;
import com.cgwas.groupUser.entity.GroupUser;
import com.cgwas.groupUser.entity.GroupUserVo;
import com.cgwas.groupUser.service.api.IGroupUserService;
import com.cgwas.logInfo.entity.LogInfo;
import com.cgwas.logInfo.service.api.ILogInfoService;
import com.cgwas.subproject.entity.SubProjectVo;
import com.cgwas.subproject.service.api.ISubProjectService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class GroupUserService implements IGroupUserService {
	private IGroupUserDao groupUserDao;
	@Autowired
	private ICompanyFilesDao companyFilesDao;
	@Autowired
	private ISubProjectService subProjectService;// 子项目
	@Autowired
	private IGRoleService gRoleService;// 角色
	@Autowired
	private ICompanyFilesUserService companyFilesUserService;//文件用户记录
	@Autowired
	private ILogInfoService logInfoService;
	
	@Transactional
	public void saveGroup(GroupUserVo groupUser){
		CompanyFilesVo companyFileVo=new CompanyFilesVo();
		CompanyFilesVo parentFileVo=new CompanyFilesVo();
		SubProjectVo sub=null;
		SubProjectVo obj=new SubProjectVo();
		obj.setId(groupUser.getProject_id());
		CompanyFilesUser companyFilesUser=null;
		/**
		 * 判断是否是父项目
		 */
		if(groupUser.getIs_parent_project().equals("0")){
			while(true){
				sub=subProjectService.selectForObject( obj);
				if(!sub.getProject_folder().equals("")){
					parentFileVo.setFor_id(sub.getId());
					parentFileVo.setFile_type(ConstantUtil.ZC_SUB_PROJECT);
					break;
				}
				/**
				 * 若当前项目为子项目
				 */
				if(sub.getSub_parent_project_id()!=null){
					obj=new SubProjectVo();
					obj.setId(sub.getSub_parent_project_id());
				}else{//否则当前子项目为子项目第一级
					parentFileVo.setFor_id(sub.getProject_id());
					parentFileVo.setFile_type(ConstantUtil.ZC_PROJECT);
					break;
				}
			}
		}else{//如果为父项目，则直接添加父项目文件权限
			parentFileVo.setFor_id(groupUser.getProject_id());
			parentFileVo.setFile_type(ConstantUtil.ZC_PROJECT);
		}
		while(true){
			companyFileVo=(CompanyFilesVo)companyFilesDao.selectForObject("selectCompanyFiles",parentFileVo);
			companyFilesUser=new CompanyFilesUser();
			companyFilesUser.setCompany_files_id(companyFileVo.getId());
			companyFilesUser.setUser_id(groupUser.getUser_id());
			companyFilesUser=companyFilesUserService.selectForObject(companyFilesUser);
			if(companyFilesUser!=null){
				break;
			}
			companyFilesUser=new CompanyFilesUser();
			companyFilesUser.setCompany_files_id(companyFileVo.getId());
			companyFilesUser.setUser_id(groupUser.getUser_id());
			companyFilesUserService.save(companyFilesUser);
			if(companyFileVo.getParent_id()==0){
				break;
			}
			parentFileVo =new CompanyFilesVo();
			parentFileVo.setId(companyFileVo.getParent_id());
		}
		/**
		 * 执行操作日志
		 */
		StringBuffer content= new StringBuffer();
		LogInfo logInfo= new LogInfo();
		logInfo.setLog_type(ConstantUtil.LOG_SAVE);
		logInfo.setTable_name("p_group_user");
		logInfo.setUser_id(groupUser.getUserId());
		logInfo.setUser_name(groupUser.getUserName());
		content.append(groupUser.getUserName());
		content.append("添加团队成员【");
		content.append(groupUser.getUser_id());
		content.append("】");
		logInfo.setContent(content.toString());
		logInfoService.save(logInfo);
		this.save(groupUser);
	}
	
	public Serializable save(GroupUser groupUser){
		groupUser.setCreate_time(new Date());
		return groupUserDao.save(groupUser);
	}

	public void delete(GroupUser groupUser){
		groupUserDao.delete(groupUser);
	}
	
	public void deleteAuditor(GroupUser groupUser){
		this.updateNum(groupUser.getId());
		groupUserDao.delete(groupUser);
	}
	
	public void deleteByExample(GroupUser groupUser){
		groupUserDao.deleteByExample(groupUser);
	}

	public void update(GroupUser groupUser){
		groupUserDao.update(groupUser);
	}
	
	public void updateIgnoreNull(GroupUser groupUser){
		groupUserDao.updateIgnoreNull(groupUser);
	}
	
	@Transactional
	public void updateSupervise(Long state,Long fid,Long sid){
		GroupUser fGroupUser=new GroupUser();
		GroupUser sGroupUser=new GroupUser();
		fGroupUser.setId(fid);
		fGroupUser=this.selectForObject(fGroupUser);
		sGroupUser.setId(sid);
		sGroupUser=this.selectForObject(sGroupUser);
		if(state==0){
			if(sGroupUser.getNum()+ 1 == fGroupUser.getNum()){
				if(fGroupUser.getNum()>=2 && sGroupUser.getNum()>=1){
					groupUserDao.update("updateNumPuss", sGroupUser);
					groupUserDao.update("updateNumSubduction", fGroupUser);
				}
				
			}
		}else if(state==1){
			if(sGroupUser.getNum() == fGroupUser.getNum() + 1 ){
				if(fGroupUser.getNum()>=1 && sGroupUser.getNum()>=2){
					groupUserDao.update("updateNumPuss", fGroupUser);
					groupUserDao.update("updateNumSubduction", sGroupUser);
				}
			}
		}
	}
	
	/**
	 * 删除审核成员后，相应层级全部更新
	 * @param state
	 * @param fid
	 * @param sid
	 */
	@Transactional
	public Boolean updateNum(Long id){
		boolean flag=false;
		try {
			GroupUser groupUser = new GroupUser();
			groupUser.setId(id);
			/**
			 * 获取当前对象
			 */
			GroupUserVo groupUserVo=this.selectForObject(groupUser);
			/**
			 * 查询当前成员所属角色
			 */
			GRoleVo groleVo= new GRoleVo();
			groleVo.setId(groupUser.getRole_id());
			groleVo=gRoleService.selectForObject(groleVo);
			if(groleVo.getRole_p_id()!=null && groleVo.getRole_p_id()==5){
				/**
				 * 查询大于当前角色下的成员对象的层级集合
				 */
				groupUser.setId(null);
				groupUser.setRole_id(groupUserVo.getRole_id());
				groupUser.setNum(groupUserVo.getNum());
				List<GroupUserVo> list=this.selectForList(groupUser);
				for (GroupUserVo obj : list) {
					groupUserDao.update("updateNumSubduction", obj);
				}
				
			}
			flag=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return flag;
	}
		
	public void updateByExample(GroupUser groupUser){
		groupUserDao.update("updateByExampleGroupUser", groupUser);
	}

	public GroupUserVo load(GroupUser groupUser){
		return (GroupUserVo)groupUserDao.reload(groupUser);
	}
	
	public GroupUserVo selectForObject(GroupUser groupUser){
		return (GroupUserVo)groupUserDao.selectForObject("selectGroupUser",groupUser);
	}
	
	public GroupUserVo getMaxNumByCheck(GroupUser groupUser){
		return (GroupUserVo)groupUserDao.selectForObject("getMaxNumByCheck",groupUser);
	}
	
	public GroupUserVo selectFabricator(GroupUser groupUser){
		return (GroupUserVo)groupUserDao.selectForObject("selectFabricator",groupUser);
	}
	
	@SuppressWarnings("unchecked")
	public List<GroupUserVo> selectForList(GroupUser groupUser){
		return (List<GroupUserVo>)groupUserDao.selectForList("selectGroupUser",groupUser);
	}
	
	@SuppressWarnings("unchecked")
	public List<GroupUserVo> selectTeamList(GroupUser groupUser){
		return (List<GroupUserVo>)groupUserDao.selectForList("selectTeamList",groupUser);
	}
	
	@SuppressWarnings("unchecked")
	public List<GroupUserVo> getAuditorList(GroupUser groupUser){
		return (List<GroupUserVo>)groupUserDao.selectForList("getAuditorList",groupUser);
	}
	
	public Page page(Page page, GroupUser groupUser) {
		return groupUserDao.page(page, groupUser);
	}

	@Autowired
	public void setIGroupUserDao(
			@Qualifier("groupUserDao") IGroupUserDao  groupUserDao) {
		this.groupUserDao = groupUserDao;
	}

}
