package com.cgwas.companyPlugin.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyPlugin.dao.api.ICompanyPluginDao;
import com.cgwas.companyPlugin.entity.CompanyPlugin;
import com.cgwas.companyPlugin.entity.CompanyPluginVo;
import com.cgwas.companyPlugin.service.api.ICompanyPluginService;
/**
*  Author yangjun
*/
@Service
public class CompanyPluginService implements ICompanyPluginService {
	@Autowired
	private ICompanyPluginDao companyPluginDao;
	public Serializable save(CompanyPlugin companyPlugin){
		return companyPluginDao.save(companyPlugin);
	}

	public void delete(CompanyPlugin companyPlugin){
		companyPluginDao.delete(companyPlugin);
	}
	
	public void deleteByExample(CompanyPlugin companyPlugin){
		companyPluginDao.deleteByExample(companyPlugin);
	}

	public void update(CompanyPlugin companyPlugin){
		companyPluginDao.update(companyPlugin);
	}
	
	public void updateIgnoreNull(CompanyPlugin companyPlugin){
		companyPluginDao.updateIgnoreNull(companyPlugin);
	}
		
	public void updateByExample(CompanyPlugin companyPlugin){
		companyPluginDao.update("updateByExampleCompanyPlugin", companyPlugin);
	}

	public CompanyPluginVo load(CompanyPlugin companyPlugin){
		return (CompanyPluginVo)companyPluginDao.reload(companyPlugin);
	}
	
	public CompanyPluginVo selectForObject(CompanyPlugin companyPlugin){
		return (CompanyPluginVo)companyPluginDao.selectForObject("selectCompanyPlugin",companyPlugin);
	}
	
	public List<CompanyPluginVo> selectForList(CompanyPlugin companyPlugin){
		return (List<CompanyPluginVo>)companyPluginDao.selectForList("selectCompanyPlugin",companyPlugin);
	}
	
	public Page page(Page page, CompanyPlugin companyPlugin) {
		return companyPluginDao.page(page, companyPlugin);
	}
}
