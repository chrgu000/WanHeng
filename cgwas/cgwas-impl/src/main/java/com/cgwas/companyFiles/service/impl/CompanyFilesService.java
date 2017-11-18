package com.cgwas.companyFiles.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgwas.animationlighttask.dao.api.IAnimationLightTaskDao;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.common.utils.OSSFilesUtil;
import com.cgwas.companyFiles.dao.api.ICompanyFilesDao;
import com.cgwas.companyFiles.entity.CompanyFiles;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.companyFiles.service.api.ICompanyFilesService;
import com.cgwas.companyFilesUser.entity.CompanyFilesUser;
import com.cgwas.companyFilesUser.entity.CompanyFilesUserVo;
import com.cgwas.companyFilesUser.service.api.ICompanyFilesUserService;
import com.cgwas.companySpace.entity.CompanySpaceVo;
import com.cgwas.companySpace.service.api.ICompanySpaceService;
import com.cgwas.gRole.entity.GRoleVo;
import com.cgwas.gRole.service.api.IGRoleService;
import com.cgwas.groupUser.entity.GroupUser;
import com.cgwas.groupUser.service.api.IGroupUserService;
import com.cgwas.modeltask.dao.api.IModelTaskDao;
import com.cgwas.spaceOrder.entity.SpaceOrderVo;
import com.cgwas.spaceOrder.service.api.ISpaceOrderService;
import com.cgwas.userCompany.service.api.IUserCompanyService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class CompanyFilesService implements ICompanyFilesService {
	@Autowired
	private ICompanyFilesDao companyFilesDao;
	@Autowired
	private IModelTaskDao modelTaskDao;
	@Autowired
	private IAnimationLightTaskDao animationLightTaskDao;
	@Autowired
	private IGroupUserService groupUserService;
	@Autowired
	private ICompanyFilesUserService companyFilesUserService;
	@Autowired
	private IGRoleService gRoleService;
	@Autowired
	private IUserCompanyService userCompanyService;
	@Autowired
	private ICompanySpaceService companySpaceService;
	@Autowired
	private ISpaceOrderService spaceOrderService;
	private List<Long> ids = null;
	private List<String> fileUrls = null;

	public Long getTotalTolume(Long company_id){
		// 根据公司id查询分配的初始空间
		CompanySpaceVo companySpaceVo = new CompanySpaceVo();
		companySpaceVo.setCompany_id(company_id);
		companySpaceVo = companySpaceService.selectForObject(companySpaceVo);
		long initSpace =0;
		if (companySpaceVo != null) {
			initSpace = companySpaceVo.getInit_space();
		}
		// 根据公司id查询公司购买的空间大小
		SpaceOrderVo spaceOrderVo = new SpaceOrderVo();
		spaceOrderVo.setCompany_id(company_id);
		// 当前时间
		spaceOrderVo.setEffective_time(new Date());
		long sumSpace = spaceOrderService.getSumSpace(spaceOrderVo);
		// 总容量
		return sumSpace + initSpace;
	}
	public Serializable save(CompanyFiles companyFiles){
		companyFiles.setSort(0);
		companyFiles.setCreate_time(new Date());
		companyFiles.setUpdate_time(new Date());
		return companyFilesDao.save(companyFiles);
	}

	public void delete(CompanyFiles companyFiles){
		companyFilesDao.delete(companyFiles);
	}
	
	public void deleteAll(CompanyFiles companyFiles){
		companyFilesDao.update("deleteCompanyFilesAll", companyFiles);
	}
	
	public void deleteByExample(CompanyFiles companyFiles){
		companyFilesDao.deleteByExample(companyFiles);
	}

	public void update(CompanyFiles companyFiles){
		companyFilesDao.update(companyFiles);
	}
	
	public void updateIgnoreNull(CompanyFiles companyFiles){
		companyFilesDao.updateIgnoreNull(companyFiles);
	}
		
	public void updateByExample(CompanyFiles companyFiles){
		companyFilesDao.update("updateByExampleCompanyFiles", companyFiles);
	}

	public CompanyFilesVo load(CompanyFiles companyFiles){
		return (CompanyFilesVo)companyFilesDao.reload(companyFiles);
	}
	
	/**
	 * 制作者接收的任务审核通过后给制作者添加相应的文件权限
	 */
	@Transactional
	public Boolean addGroleProvilege(CompanyFilesVo companyFiles){
		CompanyFilesVo param = new CompanyFilesVo();
		param.setFor_id(companyFiles.getFor_id());
		if(companyFiles.getTask_type()==null && companyFiles.getTask_type().equals("")){
			return false;
		}
		if(companyFiles.getTask_type().equals("建模")){
			param.setTask_type(ConstantUtil.MODEL_TASK);
		}else{
			param.setTask_type(ConstantUtil.ANIMATION_LIGHT_TASK);
		}
		CompanyFilesVo object = this.selectForObject(param);
		if(object==null){
			return false;
		}
		CompanyFilesVo obj = new CompanyFilesVo();
		CompanyFilesUser companyFilesUser= new CompanyFilesUser();
		GroupUser groupUser= new GroupUser();
		GRoleVo gRole = new GRoleVo();
		CompanyFilesUser entity= null;
		try {
			/**
			 * 获取角色
			 */
			gRole.setIs_parent_poject(companyFiles.getIs_parent_project());
			gRole.setProject_id(companyFiles.getProject_id());
			/**
			 * 制作者
			 */
			gRole.setRole_p_id(5l);
			gRole=gRoleService.selectForObject(gRole);
			if(gRole==null){
				return false;
			}
			while(true){
				companyFilesUser.setId(null);
				companyFilesUser.setCompany_files_id(object.getId());
				companyFilesUser.setUser_id(companyFiles.getUser_id());
				entity=companyFilesUserService.selectForObject(companyFilesUser);
				if(entity!=null){
					break;
				}
				companyFilesUserService.save(companyFilesUser);
				if(object.getParent_id()==0){
					break;
				}
				obj.setId(object.getParent_id());
				object=this.selectForObject(obj);
			}
			
			//参数注入
			groupUser.setIs_parent_project(companyFiles.getIs_parent_project());
			groupUser.setProject_id(companyFiles.getProject_id());
			groupUser.setUser_id(companyFiles.getUser_id());
			/**
			 * 判断该用户接这个任务之前是否已经接到过该项目下的任务
			 */
			groupUser=groupUserService.selectFabricator(groupUser);
			if(groupUser==null){
				groupUser=new GroupUser();
				groupUser.setIs_parent_project(companyFiles.getIs_parent_project());
				groupUser.setProject_id(companyFiles.getProject_id());
				groupUser.setUser_id(companyFiles.getUser_id());
				groupUser.setCompany_id(companyFiles.getCompany_id());
				groupUser.setNum(0);
				groupUser.setRole_id(gRole.getId());
				groupUserService.save(groupUser);
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 制作者放弃任务审核通过后或者完成任务后移除相应的文件权限
	 */
	@Transactional
	public Boolean removeGroleProvilege(CompanyFilesVo companyFiles){
		CompanyFilesVo object = this.selectForObject(companyFiles);
		CompanyFilesVo obj = new CompanyFilesVo();
		CompanyFilesUserVo companyFilesUserVo= new CompanyFilesUserVo();
		List<CompanyFilesUserVo> list= null;
		try {
			if(companyFiles.getUser_id()!=null){
				while(object.getParent_id()!=null){
					companyFilesUserVo.setId(null);
					//删除制作者指定任务的文件夹权限关系
					companyFilesUserVo.setCompany_files_id(object.getId());
					companyFilesUserVo.setUser_id(companyFiles.getUser_id());
					companyFilesUserService.delete(companyFilesUserVo);
					//根据父id查询列表数据
					companyFilesUserVo.setParent_id(object.getParent_id());
					list=companyFilesUserService.selectCompanyFilesUserByCheck(companyFilesUserVo);
					if(list.size()>0){
						break;
					}
					if(object.getParent_id()==0){
						break;
					}
					obj.setId(object.getParent_id());
					object=this.selectForObject(obj);
				}
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public CompanyFilesVo selectForObject(CompanyFiles companyFiles){
		return (CompanyFilesVo)companyFilesDao.selectForObject("selectCompanyFiles",companyFiles);
	}
	
	@SuppressWarnings("unchecked")
	public List<CompanyFilesVo> selectForList(CompanyFiles companyFiles){
		return (List<CompanyFilesVo>)companyFilesDao.selectForList("selectCompanyFiles",companyFiles);
	}
	
	@SuppressWarnings("unchecked")
	public List<CompanyFilesUserVo> selectCpmpanyFilesUsers(CompanyFilesUser companyFilesUser){
		return (List<CompanyFilesUserVo>)companyFilesDao.selectForList("selectCpmpanyFilesUsers",companyFilesUser);
	}
	
	@SuppressWarnings("unchecked")
	public List<CompanyFilesVo> selectList(CompanyFiles companyFiles){
		return (List<CompanyFilesVo>)companyFilesDao.selectForList("selectList",companyFiles);
	}
	
	public Page page(Page page, CompanyFiles companyFiles) {
		return companyFilesDao.page(page, companyFiles);
	}
	
	@Override
	public Page pageForCompany(Page page, CompanyFiles companyFiles) {
		return companyFilesDao.pageForCompany(page, companyFiles);
	}

	@Override
	public Page pageForGRole(Page page, CompanyFiles companyFiles) {
		return companyFilesDao.pageForGRole(page, companyFiles);
	}

	/**
	 *获取文件夹id和oss下路径
	 * @param companyFiles
	 * @return
	 */
	private void getMap(CompanyFilesVo companyFilesVo) {
		CompanyFilesVo objVo= new CompanyFilesVo();
		/**
		 * 拼接文件夹路径
		 */
		String uuid=userCompanyService.getCompanyUserUUID(companyFilesVo.getCompany_id());
		StringBuffer fileUrl=new StringBuffer(ConstantUtil.USER_PATH);
		fileUrl.append(uuid);
		fileUrl.append("/data/");
		fileUrl.append(companyFilesVo.getFile_url());
		if(companyFilesVo.getIs_file().equals(ConstantUtil.IS_FILE)){
			fileUrl.append("/");
		}
		String companyFileUrl=fileUrl.toString();
		ids.add(companyFilesVo.getId());
		if(companyFilesVo.getIs_file().equals(ConstantUtil.IS_FILE)){
			fileUrls.add(companyFileUrl);
			objVo = new CompanyFilesVo();
			objVo.setParent_id(companyFilesVo.getId());
			List<CompanyFilesVo> list= this.selectForList(objVo);
			if(list.size()>0){
				for (CompanyFilesVo vo : list) {
					getMap(vo);
				}
			}
		}else{
			companyFileUrl=companyFileUrl.substring(0, companyFileUrl.length()-1);
			fileUrls.add(companyFileUrl);
		}
	}

	@Override
	@Transactional
	public void deleteRelationByFileUrl(String file_url) {
		ids = new ArrayList<Long>();
		fileUrls=new ArrayList<String>();
		CompanyFilesVo companyFilesVo= new CompanyFilesVo();
		companyFilesVo.setFile_url(file_url);
		companyFilesVo=this.selectForObject(companyFilesVo);
		if(companyFilesVo!=null){
			/**
			 * 递归获取ids和 fileUrls
			 */
			getMap(companyFilesVo);
			/**
			 * 删除OSS中的文件以及文件夹
			 */
			OSSFilesUtil.deleteFiles(fileUrls);
			/**
			 * 删除数据表中的对应记录
			 */
			companyFilesVo= new CompanyFilesVo();
			companyFilesVo.setIds(ids);
			this.deleteAll(companyFilesVo);
			companyFilesUserService.deleteAll(ids);
		}
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> selectCompanyFilesMap(CompanyFiles companyFiles) {
		return (List<Map>)companyFilesDao.selectForList("selectCompanyFilesMap",companyFiles);
	}

}
