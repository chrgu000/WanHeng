package com.cgwas.companyAuthInfo.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.common.utils.FileUtils;
import com.cgwas.common.utils.OSSFilesUtil;
import com.cgwas.common.utils.PinyinUtil;
import com.cgwas.company.entity.Company;
import com.cgwas.company.service.api.ICompanyService;
import com.cgwas.companyAuthInfo.dao.api.ICompanyAuthInfoDao;
import com.cgwas.companyAuthInfo.entity.AttestationCompanyAuthInfo;
import com.cgwas.companyAuthInfo.entity.CompanyAuthInfo;
import com.cgwas.companyAuthInfo.entity.CompanyAuthInfoVo;
import com.cgwas.companyAuthInfo.entity.CompanyLegalPerson;
import com.cgwas.companyAuthInfo.service.api.ICompanyAuthInfoService;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.companyFiles.service.api.ICompanyFilesService;
import com.cgwas.companySpace.entity.CompanySpace;
import com.cgwas.companySpace.service.api.ICompanySpaceService;
import com.cgwas.user.entity.User;
import com.cgwas.user.service.api.IUserService;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userCompany.service.api.IUserCompanyService;
import com.cgwas.userRole.dao.api.IUserRoleDao;
import com.cgwas.userRole.entity.UserRole;

@Service
public class CompanyAuthInfoService implements ICompanyAuthInfoService {
	private ICompanyAuthInfoDao companyAuthInfoDao;
	@Autowired
	private IUserRoleDao userRoleDao;
	@Autowired
	private IUserCompanyService userCompanyService;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private ICompanyFilesService companyFilesService;
	@Autowired
	private ICompanySpaceService companySpaceService;
	@Autowired
	private IUserService userService;

	public Serializable save(CompanyAuthInfo companyAuthInfo) {
		return companyAuthInfoDao.save(companyAuthInfo);
	}

	public void delete(CompanyAuthInfo companyAuthInfo) {
		companyAuthInfoDao.delete(companyAuthInfo);
	}

	public void deleteByExample(CompanyAuthInfo companyAuthInfo) {
		companyAuthInfoDao.deleteByExample(companyAuthInfo);
	}

	public void update(CompanyAuthInfo companyAuthInfo) {
		companyAuthInfoDao.update(companyAuthInfo);
	}

	public void updateIgnoreNull(CompanyAuthInfo companyAuthInfo) {

		// if (companyAuthInfo.getCharter_path() != null) {
		// // companyAuthInfo.setStatus("未认证");
		// }
		companyAuthInfoDao.updateIgnoreNull(companyAuthInfo);
	}

	public void updateByExample(CompanyAuthInfo companyAuthInfo) {
		companyAuthInfoDao.update("updateByExampleCompanyAuthInfo",
				companyAuthInfo);
	}

	public CompanyAuthInfoVo load(CompanyAuthInfo companyAuthInfo) {
		return (CompanyAuthInfoVo) companyAuthInfoDao.reload(companyAuthInfo);
	}

	public CompanyAuthInfoVo selectForObject(CompanyAuthInfo companyAuthInfo) {
		return (CompanyAuthInfoVo) companyAuthInfoDao.selectForObject(
				"selectCompanyAuthInfo", companyAuthInfo);
	}

	public List<CompanyAuthInfoVo> selectForList(CompanyAuthInfo companyAuthInfo) {
		return (List<CompanyAuthInfoVo>) companyAuthInfoDao.selectForList(
				"selectCompanyAuthInfo", companyAuthInfo);
	}

	public Page page(Page page, CompanyAuthInfo companyAuthInfo) {
		return companyAuthInfoDao.page(page, companyAuthInfo);
	}

	@Autowired
	public void setICompanyAuthInfoDao(
			@Qualifier("companyAuthInfoDao") ICompanyAuthInfoDao companyAuthInfoDao) {
		this.companyAuthInfoDao = companyAuthInfoDao;
	}

	@Override
	public CompanyAuthInfo getCompanyAuthInfoByCompanyId(Long company_Id) {
		return (CompanyAuthInfo) companyAuthInfoDao.selectForObject(
				"getCompanyAuthInfoByCompanyId", company_Id);
	}

	@Override
	public void updateCompanyAuthInfoByCompanyId(CompanyAuthInfo companyAuthInfo) {
		// companyAuthInfo.setCreat_time(new Date());
		// companyAuthInfo.setStatus("未认证");
		companyAuthInfoDao.update("updateCompanyAuthInfoByCompanyId",
				companyAuthInfo);
	}

	@Override
	public Serializable authenticationCompanyAuthInfo(Long conpanyId,
			Boolean status, Long userid) {
		CompanyAuthInfo companyAuthInfo = new CompanyAuthInfo();
		companyAuthInfo.setCompany_id(conpanyId);

		if (status) { // 判断已认证 / 不通过
			companyAuthInfo.setStatus("已认证");
			UserRole entity = new UserRole();
			entity.setRole_id(2L);

			UserCompany returnCompany = userCompanyService
					.companygetUser(conpanyId);

			entity.setUser_id(returnCompany.getUse_id());

			userRoleDao.delete("deleteByExampleUserRole", entity); // // 删除原有

			userRoleDao.save(entity);
			entity.setRole_id(3L);
			entity.setId(null);
			// 删除人员角色
			userRoleDao.delete("deleteByExampleUserRole", entity);

			Company sCompany = new Company();
			sCompany.setId(conpanyId);
			sCompany = companyService.selectForObject(sCompany);
			/**
			 * 创建公司文件夹
			 */
			CompanyFilesVo companyFilesVo = new CompanyFilesVo();
			companyFilesVo.setFor_id(conpanyId);
			companyFilesVo.setFile_type(ConstantUtil.ZC_COMPANY);
			companyFilesVo = companyFilesService
					.selectForObject(companyFilesVo);
			if (companyFilesVo == null) {
				companyFilesVo = new CompanyFilesVo();
				companyFilesVo.setCompany_id(conpanyId);
				companyFilesVo.setParent_id(0l);
				companyFilesVo.setFor_id(conpanyId);
				companyFilesVo.setFile_name(PinyinUtil
						.getHeadCharSpell(sCompany.getCompany_name())
						+ "_"
						+ conpanyId);
				companyFilesVo.setFile_url(PinyinUtil.getHeadCharSpell(sCompany
						.getCompany_name()) + "_" + conpanyId);
				companyFilesVo.setIs_file("Y");
				companyFilesVo.setCreator_id(returnCompany.getUse_id());
				companyFilesVo.setFile_type(ConstantUtil.ZC_COMPANY);
				companyFilesService.save(companyFilesVo);
				/**
				 * 获取系统初始空间
				 */
				CompanySpace space = new CompanySpace();
				CompanySpace companySpace = new CompanySpace();
				space = companySpaceService.getInitSpace(space);
				/**
				 * 认证公司后添加默认初始空间
				 */
				companySpace.setCompany_id(conpanyId);
				companySpace.setInit_space(space.getInit_space());
				companySpaceService.save(companySpace);
			}
			UserCompany userRetn = userCompanyService.companygetUser(conpanyId);
			User retn = userService.getUserById(userRetn.getUse_id());

			OSSFilesUtil.addFile(FileUtils.getSaveProjectPath(retn.getUuid(),
					companyFilesVo.getFile_name()));
		} else {
			companyAuthInfo.setStatus("不通过");
		}
		return companyAuthInfoDao.update("authenticationCompanyAuthInfo",
				companyAuthInfo);
	}

	@Override
	public CompanyAuthInfo selectCompanyAuthInfoByCompanyId(
			CompanyAuthInfo companyAuthInfo) {
		CompanyAuthInfo authInfo = (CompanyAuthInfo) companyAuthInfoDao
				.selectForObject("selectCompanyAuthInfoByCompanyId",
						companyAuthInfo);

		if (authInfo == null) { // 空创建
			authInfo = new CompanyAuthInfo();
			authInfo.setStatus("待提交");
			authInfo.setCompany_id(companyAuthInfo.getCompany_id());
			this.save(authInfo);
		}

		return (CompanyAuthInfo) companyAuthInfoDao.selectForObject(
				"selectCompanyAuthInfoByCompanyId", companyAuthInfo);
	}

	@Override
	public List<AttestationCompanyAuthInfo> getCompanyAuthInfoList(
			AttestationCompanyAuthInfo attestationCompanyAuthInfo, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("attestationCompanyAuthInfo", attestationCompanyAuthInfo);
		map.put("page", page);
		return (List<AttestationCompanyAuthInfo>) companyAuthInfoDao
				.selectForList("getCompanyAuthInfoList", map);
	}

	public long getCompanyAuthInfoListCount(
			AttestationCompanyAuthInfo attestationCompanyAuthInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("attestationCompanyAuthInfo", attestationCompanyAuthInfo);
		return (Long) companyAuthInfoDao.selectForObject(
				"getCompanyAuthInfoListCount", map);
	}

	@Override
	public CompanyLegalPerson getCompanyLegalPerson(Long company_id) {
		return (CompanyLegalPerson) companyAuthInfoDao.selectForObject(
				"getCompanyLegalPerson", company_id);
	}

}
