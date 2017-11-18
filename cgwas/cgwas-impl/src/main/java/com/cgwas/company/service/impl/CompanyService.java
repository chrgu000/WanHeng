package com.cgwas.company.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.common.utils.FileUtils;
import com.cgwas.common.utils.OSSFilesUtil;
import com.cgwas.company.dao.api.ICompanyDao;
import com.cgwas.company.entity.AdminCompany;
import com.cgwas.company.entity.Company;
import com.cgwas.company.entity.CompanyVo;
import com.cgwas.company.service.api.ICompanyService;
import com.cgwas.companyAuthInfo.entity.CompanyAuthInfo;
import com.cgwas.companyAuthInfo.service.api.ICompanyAuthInfoService;
import com.cgwas.sector.entity.Sector;
import com.cgwas.sector.service.api.ISectorService;
import com.cgwas.userCompany.dao.api.IUserCompanyDao;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userCompany.service.api.IUserCompanyService;

@Service
public class CompanyService implements ICompanyService {
	private ICompanyDao companyDao;
	IUserCompanyService userCompanyService = null;
	ISectorService sectorService = null;
	ICompanyAuthInfoService companyAuthInfoService = null;
	private IUserCompanyDao userCompanyDao;

	public Serializable save(Company company) {
		return companyDao.save(company);
	}

	public void delete(Company company) {
		companyDao.delete(company);
	}

	public void deleteByExample(Company company) {
		companyDao.deleteByExample(company);
	}

	public void update(Company company) {
		companyDao.update(company);
	}

	public void updateIgnoreNull(Company company) {
		companyDao.updateIgnoreNull(company);
	}

	public void updateByExample(Company company) {
		companyDao.update("updateByExampleCompany", company);
	}

	public CompanyVo load(Company company) {
		return (CompanyVo) companyDao.reload(company);
	}

	public CompanyVo selectForObject(Company company) {
		return (CompanyVo) companyDao.selectForObject("selectCompany", company);
	}

	public List<CompanyVo> selectForList(Company company) {
		return (List<CompanyVo>) companyDao.selectForList("selectCompany",
				company);
	}

	public Page page(Page page, Company company) {
		return companyDao.page(page, company);
	}

	@Autowired
	public void setICompanyDao(@Qualifier("companyDao") ICompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	@Override
	public Result saveCompany(Long userId, String relation, Company company,
			Sector sector, CompanyAuthInfo companyAuthInfo,String uuid,StringBuffer charter_path) {

		// 保存公司
		company.setIs_delete('N');
		company.setRegist_date(new Date());
		company.setIdcard_pic_path("");
		// 保存默认公司头像
		company.setHead_pic_path(ConstantUtil.Def_COMPANY_PTTH);
		companyDao.save(company);
		// 保存图片
		if (charter_path != null&&!"".equals(charter_path.toString())) { // 保存营业执照
			byte[] fileByte = FileUtils.base64ToByte(charter_path.toString());
			String path = FileUtils.getSaveCompanyPath(uuid, "charter", company.getId());
			OSSFilesUtil.uploadDocumentByString(path,
					fileByte);
			companyAuthInfo.setCharter_path(path);
		}else{
			
			return new Result("RY0055", null); // 营业执照上传错误
		}
	
		
		// 保存关系
		UserCompany userCompany = new UserCompany();
		userCompany.setUse_id(userId);
		userCompany.setRelation(relation);
		userCompany.setCompany_id(company.getId());
		userCompanyService.save(userCompany);
		// 保存公司行业
		Sector companySector = new Sector();
		companySector = sector;
		companySector.setCompany_id(company.getId());
		sectorService.save(companySector);

		// 保存公司认证信息
		CompanyAuthInfo newCompanyAuthInfo = new CompanyAuthInfo();
		newCompanyAuthInfo = companyAuthInfo;
		newCompanyAuthInfo.setCreat_time(new Date());
		newCompanyAuthInfo.setCompany_id(company.getId());
		newCompanyAuthInfo.setStatus("未认证");
		companyAuthInfoService.save(newCompanyAuthInfo);
		return  new Result(Boolean.TRUE, "成功", null);
	}

	@Override
	public Result saveCompanyA(Long userId, String relation, Company company,
			Sector sector, CompanyAuthInfo companyAuthInfo, String uuid,
			MultipartFile charter_path) {
		company.setIs_delete('N');
		company.setRegist_date(new Date());
		company.setIdcard_pic_path("");
		// 保存默认公司头像
		company.setHead_pic_path(ConstantUtil.Def_COMPANY_PTTH);
		companyDao.save(company);
		// 保存图片
		if (!charter_path.isEmpty()) { // 保存营业执照
			byte[] fileByte = null;
			try {
				fileByte = charter_path.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String path = FileUtils.getSaveCompanyPath(uuid, "charter", company.getId());
			OSSFilesUtil.uploadDocumentByString(path,
					fileByte);
			companyAuthInfo.setCharter_path(path);
		}else{
			
			return new Result("RY0055", null); // 营业执照上传错误
		}
	
		
		// 保存关系
		UserCompany userCompany = new UserCompany();
		userCompany.setUse_id(userId);
		userCompany.setRelation(relation);
		userCompany.setCompany_id(company.getId());
		userCompanyService.save(userCompany);
		// 保存公司行业
		Sector companySector = new Sector();
		companySector = sector;
		companySector.setCompany_id(company.getId());
		sectorService.save(companySector);

		// 保存公司认证信息
		CompanyAuthInfo newCompanyAuthInfo = new CompanyAuthInfo();
		newCompanyAuthInfo = companyAuthInfo;
		newCompanyAuthInfo.setCreat_time(new Date());
		newCompanyAuthInfo.setCompany_id(company.getId());
		newCompanyAuthInfo.setStatus("未认证");
		companyAuthInfoService.save(newCompanyAuthInfo);
		return  new Result(Boolean.TRUE, "成功", null);
	}

	
	@Autowired(required = true)
	public void setUserCompanyService(
			@Qualifier("userCompanyService") IUserCompanyService userCompanyService) {
		this.userCompanyService = userCompanyService;
	}

	@Autowired(required = true)
	public void setSectorService(
			@Qualifier("sectorService") ISectorService sectorService) {
		this.sectorService = sectorService;
	}

	@Autowired(required = true)
	public void setCompanyAuthInfoService(
			@Qualifier("companyAuthInfoService") ICompanyAuthInfoService companyAuthInfoService) {
		this.companyAuthInfoService = companyAuthInfoService;
	}

	@Override
	public void updateCompanyByCompanyId(Company company) {
		companyDao.update("updateCompanyByCompanyId", company);
	}

	@Override
	public Company selectCompanyById(Company company) {
		return (Company) companyDao.selectForObject("selectCompanyById",
				company);
	}

	@Override
	public void batchDeleteCompany(List<Long> companyIdList) {
		companyDao.update("batchDeleteCompany", companyIdList);
	}

	@Override
	public List<Company> selectCompanyByUserId(long user_id) {
		return (List<Company>) companyDao.selectForList(
				"selectCompanyByUserId", user_id);
	}

	@Override
	public List<AdminCompany> getAdminCompanyList(AdminCompany adminCompany,
			Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminCompany", adminCompany);
		map.put("page", page);
		return (List<AdminCompany>) companyDao.selectForList(
				"getAdminCompanyList", map);
	}

	@Override
	public Long getAdminCompanyListCount(AdminCompany adminCompany) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminCompany", adminCompany);
		return (Long) companyDao.selectForObject("getAdminCompanyListCount",
				map);
	}

	@Override
	public List<UserCompany> getHaveCompanyByUserId(Long user_id,Long status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		if(status==1){
			
			map.put("status", "已认证");
		}else{
			map.put("status", "其它");
			
		}

		
		return (List<UserCompany>) companyDao.selectForList(
				"getHaveCompanyByUserId", map);
	}

	
}
